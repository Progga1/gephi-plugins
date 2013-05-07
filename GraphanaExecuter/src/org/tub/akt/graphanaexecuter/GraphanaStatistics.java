/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.graphanaexecuter;

import graphana.ExecutionManager;
import graphana.UserInterface;
import graphana.operationsystem.GraphOperation;
import java.util.ArrayList;
import java.util.LinkedList;
import libraries.jung.JungLib;
import operations.graphoperations.algorithms.AlgosGraphStdProperties;
import operations.graphoperations.algorithms.AlgosMiscellaneous;
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
import scriptinterface.defaulttypes.GInteger;
import scriptinterface.execution.returnvalues.ExecutionReturn;
import system.GraphanaAccess;
import view.VisualizingUserInterface;
import view.callassistant.ArgumentsPanel;

public class GraphanaStatistics implements Statistics{

    private ExecutionReturn result;
    private GraphOperation graphOperation;
    private ArgumentsPanel argPanel;
    
    @Override
    public void execute(GraphModel graphModel, AttributeModel attributeModel) {
        GraphanaAccess graphanaAccess = GraphanaStatisticsUI.graphanaAccess;
        ExecutionManager mainControl = graphanaAccess.getMainControl();
        
        UndirectedGraph gephiGraph = graphModel.getUndirectedGraph();

        JungLib jungGraph = new JungLib();
        jungGraph.createGraph(false, false, false);
        for(Node node:gephiGraph.getNodes()) {
            jungGraph.addVertex(""+node.getId());
        }
        
        for(Edge edge:gephiGraph.getEdges()) {
            jungGraph.addEdge(jungGraph.getVertexByIdent(""+edge.getSource().getId()), jungGraph.getVertexByIdent(""+edge.getTarget().getId()));
        }
       
        ExecutionReturn[] arguments = new ExecutionReturn[0];
        if(argPanel!=null)
            arguments = argPanel.getEvaluatedArguments().toArray(arguments);
        result = graphOperation.execute(jungGraph, mainControl, arguments, null, graphOperation.getMainKey());
    }
    
    @Override
    public String getReport() {
        return result.getStringRepresentation();
    }

    public ExecutionReturn getResult() {
        return result;
    }

    public void setup(GraphOperation graphOperation,ArgumentsPanel argPanel) {
        this.graphOperation = graphOperation;
    }

}