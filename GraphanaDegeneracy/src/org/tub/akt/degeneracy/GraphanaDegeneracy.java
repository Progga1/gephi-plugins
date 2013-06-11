/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.degeneracy;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.ServiceProvider;


@ServiceProvider (service=StatisticsBuilder.class)
public class GraphanaDegeneracy extends GraphanaStatisticsBuilder {
    

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
