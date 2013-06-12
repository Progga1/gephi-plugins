/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.graphana;

import graphana.operationsystem.GraphOperation;
import operations.graphoperations.algorithms.AlgosMiscellaneous;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;
import org.tub.akt.graphanaexecuter.GraphanaStatistics;
import org.tub.akt.graphanaexecuter.GraphanaStatisticsUI;

//@ServiceProvider(service = StatisticsUI.class)
public class DegeneracyUI extends GraphanaStatisticsUI {
    
    public DegeneracyUI() {
        super(new DegeneracyStatistics());
    }
    
    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return DegeneracyStatistics.class; 
    }
}
