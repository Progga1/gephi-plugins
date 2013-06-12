/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.graphanaexecuter;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

//@ServiceProvider (service=StatisticsBuilder.class)
public class GraphanaStatisticsBuilder implements StatisticsBuilder{

    protected GraphanaStatistics statistics;
    
    public GraphanaStatisticsBuilder(GraphanaStatistics statistics) {
        this.statistics = statistics;
    }
    
    @Override
    public String getName() {
        return statistics.getName();
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return statistics.getClass();
    }
    
}
