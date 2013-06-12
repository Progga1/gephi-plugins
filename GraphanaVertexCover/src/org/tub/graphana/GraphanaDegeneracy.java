/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.graphana;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;
import org.tub.akt.graphanaexecuter.GraphanaStatistics;
import org.tub.akt.graphanaexecuter.GraphanaStatisticsBuilder;

@ServiceProvider (service=StatisticsBuilder.class)
public class GraphanaDegeneracy extends GraphanaStatisticsBuilder {
    

    public GraphanaDegeneracy() {
        statistics = new DegeneracyStatistics();
    }
    
    @Override
    public String getName() {
        return "Degeneracy";
    }
    
}
