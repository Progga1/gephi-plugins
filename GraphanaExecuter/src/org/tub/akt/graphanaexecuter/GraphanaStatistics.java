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
import system.GraphanaInitializer;
import view.VisualizingUserInterface;
import view.callassistant.ArgumentsPanel;

public abstract class GraphanaStatistics implements Statistics{

    public static GraphanaAccess graphanaAccess;
    public static VisualizingUserInterface userInterface;
    
    private ExecutionReturn result;
    private GraphOperation graphOperation;
    private ArgumentsPanel argPanel;
    
    protected GraphanaStatistics() {
        if(graphanaAccess==null) {
            userInterface = new GraphanaGephiUI();
            graphanaAccess = new GraphanaAccess(userInterface);
            GraphanaInitializer.registerDefaultArgumentComponents(userInterface);
        }
        graphOperation = getOperation();
    }
    
    protected abstract String getOperationKey();
    
    protected GraphOperation getOperation() {
        return this.graphOperation = (GraphOperation)graphanaAccess.getMainControl().getOperationSet().getOperation(getOperationKey());
        //return new AlgosMiscellaneous().new AlgoGetGreedyVertexCover();
    }
    
    @Override
    public void execute(GraphModel graphModel, AttributeModel attributeModel) {
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
        //if(argPanel!=null)
        //    arguments = argPanel.getEvaluatedArguments().toArray(arguments);//System.out.println("PAANEEEL: "+arguments[0]);
        result = graphOperation.execute(jungGraph, mainControl, arguments, null, graphOperation.getMainKey());
    }
    
    @Override
    public String getReport() {
        return result.getStringRepresentation();
    }

    public ExecutionReturn getResult() {
        return result;
    }

    public void setup(ArgumentsPanel argPanel) {
        this.argPanel = argPanel;
        
        this.graphOperation.initialize(graphanaAccess.getMainControl().getScriptSystem());
    }
    
    public String getName() {
        return graphOperation.getSignature().getMainKey();
    }
    
    public GraphOperation getGraphOperation() {
        return graphOperation;
    }

}