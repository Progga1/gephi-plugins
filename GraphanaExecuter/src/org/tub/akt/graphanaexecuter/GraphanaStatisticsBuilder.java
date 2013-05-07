/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.graphanaexecuter;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

public class GraphanaStatisticsBuilder implements StatisticsBuilder{

    private GraphanaStatistics statistics;
    
    public GraphanaStatisticsBuilder() {
        this.statistics = new GraphanaStatistics();
    }
    
    @Override
    public String getName() {
        return "Graphana algorithm";
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return GraphanaStatistics.class;
    }
    
}
