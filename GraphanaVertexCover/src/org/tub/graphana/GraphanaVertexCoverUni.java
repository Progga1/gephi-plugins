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

public class GraphanaVertexCoverUni {
    
    @ServiceProvider (service=StatisticsBuilder.class)
    public static class GraphanaVertexCover extends GraphanaStatisticsBuilder {

        public GraphanaVertexCover() {
            statistics = new VertexCoverStatistics();
        }

        @Override
        public String getName() {
            return "Vertex Cover";
        }

        @Override
        public Class<? extends Statistics> getStatisticsClass() {
            //return VertexCoverStatistics.class;
            return VertexCoverStatistics.class;
        }

    }
    
    @ServiceProvider(service = StatisticsUI.class)
    public static class VertexCoverUI extends GraphanaStatisticsUI {

        public VertexCoverUI() {
            super("vertexCover");
        }

        @Override
        public Class<? extends Statistics> getStatisticsClass() {
            return VertexCoverStatistics.class;
        }
    }
    
    public static class VertexCoverStatistics extends GraphanaStatistics {
    
    }
    
}
