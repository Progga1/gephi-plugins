/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.graphana;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;
import org.tub.akt.graphanaexecuter.GraphanaStatistics;
import org.tub.akt.graphanaexecuter.GraphanaStatisticsUI;

@ServiceProvider(service = StatisticsUI.class)
public class VertexCoverUI extends GraphanaStatisticsUI {
    
    @Override
    public String getDisplayName() {
        return "Vertex Cover";
    }

    @Override
    public String getShortDescription() {
        return "Graphana Vertex Cover";
    }
    
    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return VertexCoverStatistics.class;
    }
}
