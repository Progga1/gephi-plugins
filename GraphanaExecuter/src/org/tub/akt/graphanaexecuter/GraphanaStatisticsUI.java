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
import org.gephi.statistics.spi.StatisticsBuilder;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;
import scriptinterface.execution.returnvalues.ExecutionReturn;
import system.GraphanaAccess;
import system.GraphanaInitializer;
import view.VisualizingUserInterface;
import view.callassistant.ArgumentsPanel;

//@ServiceProvider (service=StatisticsUI.class)
 public class GraphanaStatisticsUI implements StatisticsUI{

    private String name;
    private String shortDescription;
    private String displayName;
    private String key;
    private GraphanaStatistics graphanaStcs;
    private VisualizingUserInterface userInterface;
    
    protected static GraphanaAccess graphanaAccess;
    private GraphOperation graphOperation;
    private ArgumentsPanel argPanel = null;
    
    public GraphanaStatisticsUI(String key,String displayName) {
        this.key = key;
        this.displayName = displayName;
        if(graphanaAccess==null) {
            userInterface = new GraphanaGephiUI();
            graphanaAccess = new GraphanaAccess(userInterface);
            GraphanaInitializer.registerDefaultArgumentComponents(userInterface);
        }
       // this.graphOperation = createGraphOperation();
        this.graphOperation = (GraphOperation)graphanaAccess.getMainControl().getOperationSet().getOperation(key);
        this.graphOperation.initialize(graphanaAccess.getMainControl().getScriptSystem());
        if(graphOperation.getParameters().getParamCount(false)>0) {
            argPanel = new ArgumentsPanel();
            argPanel.init(graphOperation.getSignature(), ((VisualizingUserInterface)graphanaAccess.getUserInterface()).getArgumentComponentManager());
        }else
            argPanel = null;
    }
    
    public GraphanaStatisticsUI(String key) {
        this(key,key);
    }
    
    protected GraphOperation createGraphOperation() {
        return new AlgosMiscellaneous().new AlgoGetGreedyVertexCover();
    }
    
    public final GraphOperation getGraphOperation() {
        return graphOperation;
    }
    
    @Override
    public JPanel getSettingsPanel() {
        return argPanel;
    }

    @Override
    public void setup(Statistics ststcs) {
        graphanaStcs = (GraphanaStatistics)ststcs;
        graphanaStcs.setup(graphOperation, argPanel);
    }

    @Override
    public void unsetup() {
        graphanaStcs = null;
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return GraphanaStatistics.class;
    }

    @Override
    public String getValue() {
        ExecutionReturn result = graphanaStcs.getResult();
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
        return displayName;
    }

    @Override
    public String getShortDescription() {
        return "Graphana "+displayName;
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
