/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.splittance;

import java.util.ArrayList;
import java.util.LinkedList;
import org.gephi.data.attributes.api.AttributeColumn;
import org.gephi.data.attributes.api.AttributeModel;
import org.gephi.data.attributes.api.AttributeOrigin;
import org.gephi.data.attributes.api.AttributeRow;
import org.gephi.data.attributes.api.AttributeTable;
import org.gephi.data.attributes.api.AttributeType;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.UndirectedGraph;
import org.gephi.statistics.spi.Statistics;

public class SplittanceStatistics implements Statistics{

    private int addedEdgeCount,removedEdgeCount,removedLoopCount;
    
    @Override
    public void execute(GraphModel graphModel, AttributeModel attributeModel) {
        UndirectedGraph graph = graphModel.getUndirectedGraph();
        
        addedEdgeCount = 0;
        removedEdgeCount = 0;
        removedLoopCount = 0;
        
        //REMOVE LOOPS
        for (Node node : graph.getNodes().toArray()) {
            Edge loop = graph.getEdge(node,node);
            if(loop!=null) {
                graph.removeEdge(loop);
                removedLoopCount++;
            }
        }
        
        //INIT ATTRIBUTE TABLES
        //Node
        AttributeTable nodeTable = attributeModel.getNodeTable();
        AttributeColumn modCol = nodeTable.getColumn("splittance_nodes");
        if (modCol == null) {
            modCol = nodeTable.addColumn("splittance_nodes", "Splittance", AttributeType.STRING, AttributeOrigin.COMPUTED, "Undefined");
        }
        //Edge
        AttributeTable edgeTable = attributeModel.getEdgeTable();
        AttributeColumn edgeCol = edgeTable.getColumn("splittance_edges");
        if (edgeCol == null) {
            edgeCol = edgeTable.addColumn("splittance_edges", "Splittance", AttributeType.STRING, AttributeOrigin.COMPUTED, "Unmodified");
        }
        
        //SORT VERTICES BY DEGREE
        //Find max degree
        int maxDegree = 0;
        for (Node node : graph.getNodes()) {
            int degree = graph.getDegree(node);
            if(degree>maxDegree)
                maxDegree = degree;
        }
        
        //Init bucket
        ArrayList<LinkedList<Node>> nodeBucket = new ArrayList<LinkedList<Node>>(maxDegree+1);
        for(int i=0;i<=maxDegree;i++) {
            nodeBucket.add(new LinkedList<Node>());
        }
        
        //Insert nodes into bucket descendent
        for(Node node : graph.getNodes()) {
            nodeBucket.get(maxDegree-graph.getDegree(node)).add(node);
        }
        
        //FIND SPLIT INDEX
        int splitIndex = 0;
        int k=0;
        for(int i=0;i<=maxDegree;i++) {
            int count = nodeBucket.get(i).size();
            int degree = maxDegree-i;
            k += count;
            if(degree>=k-1)
                splitIndex = i;
        }
        
        int n1Index = 0;
        for(int i=0;i<=splitIndex;i++) {
            for(Node node1:nodeBucket.get(i)) {
                AttributeRow nodeRow = (AttributeRow) node1.getNodeData().getAttributes();
                nodeRow.setValue(modCol, "In clique");
                int n2Index = 0;
                for(int j=0;j<=splitIndex;j++) {
                    for(Node node2:nodeBucket.get(j)) {
                        if(node1!=node2 && !graph.isAdjacent(node1, node2)) {
                            graph.addEdge(node1,node2);
                            Edge edge = graph.getEdge(node1, node2);
                            AttributeRow edgeRow = (AttributeRow) edge.getEdgeData().getAttributes();
                            edgeRow.setValue(edgeCol, "Added");
                            addedEdgeCount++;
                        }
                        n2Index++;
                    }
                }
                n1Index++;
            }
        }
        
        n1Index = 0;
        for(int i=splitIndex+1;i<maxDegree;i++) {
            for(Node node1:nodeBucket.get(i)) {
                AttributeRow nodeRow = (AttributeRow) node1.getNodeData().getAttributes();
                nodeRow.setValue(modCol, "Independent");
                int n2Index = 0;
                for(int j=splitIndex+1;j<maxDegree;j++) {
                    for(Node node2:nodeBucket.get(j)) {
                        if(n2Index>n1Index) {
                            Edge edge = graph.getEdge(node1, node2);
                            if(edge!=null) {
                                AttributeRow edgeRow = (AttributeRow) edge.getEdgeData().getAttributes();
                                edgeRow.setValue(edgeCol, "Removed");
                                removedEdgeCount++;
                            }
                        }
                        n2Index++;
                    }
                }
                n1Index++;
            }
            
        }

    }

    public int getEdgeModificationCount() {
        return addedEdgeCount+removedEdgeCount;
    }
    
    @Override
    public String getReport() {
        String result = "Edge modifications: "+getEdgeModificationCount()+" (added: "+addedEdgeCount+", removed: "+removedEdgeCount+")";
        if(removedLoopCount>0)
            result += "\n"+removedLoopCount+" loops had to be removed";
        return result;
    }

}