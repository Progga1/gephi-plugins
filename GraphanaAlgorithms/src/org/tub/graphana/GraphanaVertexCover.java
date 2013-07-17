/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.graphana;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;
import org.tub.akt.graphanaexecuter.GraphanaStatistics;
import org.tub.akt.graphanaexecuter.GraphanaStatisticsBuilder;
import org.tub.akt.graphanaexecuter.GraphanaStatisticsUI;

public class GraphanaVertexCover {
    
    public static class OpStatistics extends GraphanaStatistics {
        @Override
        protected String getOperationKey() {
            return "vertexCover";
        }
    }
    
    private static OpStatistics opStatistics = new OpStatistics();
    
    @ServiceProvider (service=StatisticsBuilder.class)
    public static class OpStatisticsBuilder extends GraphanaStatisticsBuilder {
        public OpStatisticsBuilder() {
            super(opStatistics);
        }
    }
    
    @ServiceProvider(service = StatisticsUI.class)
    public static class OpUserInterface extends GraphanaStatisticsUI {
        public OpUserInterface() {
            super(opStatistics);
        }
    }
    
}
