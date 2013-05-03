/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.splittance;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider (service=StatisticsBuilder.class)
public class SplittanceMetric implements StatisticsBuilder{

    private SplittanceStatistics statistics;
    
    public SplittanceMetric() {
        this.statistics = new SplittanceStatistics();
    }
    
    @Override
    public String getName() {
        return "Splittance";
    }

    @Override
    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return SplittanceStatistics.class;
    }
    
}
