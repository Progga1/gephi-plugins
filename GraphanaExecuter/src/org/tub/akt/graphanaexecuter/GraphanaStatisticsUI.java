/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.graphanaexecuter;

import graphana.operationsystem.GraphOperation;
import graphana.script.bindings.GraphanaScriptSystem;
import javax.swing.JPanel;
import operations.graphoperations.algorithms.AlgosMiscellaneous;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;
import scriptinterface.execution.returnvalues.ExecutionReturn;
import system.GraphanaAccess;
import system.GraphanaInitializer;
import view.VisualizingUserInterface;
import view.callassistant.ArgumentsPanel;

 public class GraphanaStatisticsUI implements StatisticsUI{

    private GraphanaStatistics graphanaAlgo;
    private VisualizingUserInterface userInterface;
    
    protected static GraphanaAccess graphanaAccess;
    private GraphOperation graphOperation;
    private ArgumentsPanel argPanel = null;
    
    public GraphanaStatisticsUI() {
        if(graphanaAccess==null) {
            userInterface = new GraphanaGephiUI();
            graphanaAccess = new GraphanaAccess(userInterface);
            GraphanaInitializer.registerDefaultArgumentComponents(userInterface);
        }
        this.graphOperation = createGraphOperation();
        this.graphOperation.initialize(graphanaAccess.getMainControl().getScriptSystem());
        if(graphOperation.getParameters().getParamCount(false)>0) {
            argPanel = new ArgumentsPanel();
            argPanel.init(graphOperation.getSignature(), ((VisualizingUserInterface)graphanaAccess.getUserInterface()).getArgumentComponentManager());
        }else
            argPanel = null;
    }
    
    protected GraphOperation createGraphOperation() {
        return new AlgosMiscellaneous().new AlgoGetGreedyVertexCover();
    }
    
    public GraphOperation getGraphOperation() {
        return graphOperation;
    }
    
    @Override
    public JPanel getSettingsPanel() {
        return argPanel;
    }

    @Override
    public void setup(Statistics ststcs) {
        graphanaAlgo = (GraphanaStatistics)ststcs;
        graphanaAlgo.setup(graphOperation, argPanel);
    }

    @Override
    public void unsetup() {
        graphanaAlgo = null;
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return GraphanaStatistics.class;
    }

    @Override
    public String getValue() {
        ExecutionReturn result = graphanaAlgo.getResult();
        if(result==null)
            return "";
        String res = result.getStringRepresentation();
        if(res.length()>10) {
            res = res.substring(0, 7)+"...";
        }
        return res;
    }

    @Override
    public String getDisplayName() {
        return "Graphana algorithm";
    }

    @Override
    public String getShortDescription() {
        return "Graphana algorithm";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_NETWORK_OVERVIEW;
    }

    @Override
    public int getPosition() {
        return 0;
    }
    
}
