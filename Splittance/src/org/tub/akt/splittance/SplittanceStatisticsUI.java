/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.splittance;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
 public class SplittanceStatisticsUI implements StatisticsUI{

    private SplittanceStatistics splittanceAlgo;
    
    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics ststcs) {
        splittanceAlgo = (SplittanceStatistics)ststcs;
    }

    @Override
    public void unsetup() {
        splittanceAlgo = null;
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return SplittanceStatistics.class;
    }

    @Override
    public String getValue() {
       return ""+splittanceAlgo.getEdgeModificationCount();
    }

    @Override
    public String getDisplayName() {
        return "Splittance";
    }

    @Override
    public String getShortDescription() {
        return "Splittance of the graph";
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
