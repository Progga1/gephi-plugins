/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tub.akt.graphanaexecuter.gephibinding;

import graphana.graphs.GraphLibrary;
import graphana.graphs.exceptions.InvalidGraphConfigException;
import graphana.model.GraphConfiguration;
import org.gephi.graph.api.*;

/**
 *
 * @author Progga
 */
public class GephiLib extends GraphLibrary<Node, Edge>{
    
    private static int STATUS_ID = 0;
    private static int DATA_ID = 1;
    
    private GraphModel graphModel;
    private Graph graph;
    private DirectedGraph dirGraph;
    private UndirectedGraph undirGraph;

    @Override
    protected void derivCreateGraph(boolean directed, boolean weighted, boolean forceSimple) throws InvalidGraphConfigException {
        throw new RuntimeException("Graph creation not supported");
    }

    @Override
    protected GraphConfiguration getDefaultGraphConfiguration() {
        return new GraphConfiguration(false,false,false);
    }

    @Override
    public int getVertexCount() {
        return graph.getNodeCount();
    }

    @Override
    public int getEdgeCount() {
        return graph.getEdgeCount();
    }

    @Override
    protected Node derivAddVertex() {
        throw new RuntimeException("Adding vertices not supported");
    }

    @Override
    protected Edge derivAddEdge(Node vertex1, Node vertex2) {
        throw new RuntimeException("Adding edges not supported");
    }

    @Override
    public Iterable<Node> getVertices() {
        return graph.getNodes();
    }

    @Override
    public Iterable<Edge> getEdges() {
        return graph.getEdges();
    }

    @Override
    public Iterable<Edge> getOutgoingEdges(Node vertex) {
        if(directed)
            return dirGraph.getOutEdges(vertex);
        else
            return graph.getEdges(vertex);
    }

    @Override
    public Iterable<Edge> getIngoingEdges(Node vertex) {
        if(directed)
            return dirGraph.getInEdges(vertex);
        else
            return graph.getEdges(vertex);
    }

    @Override
    public int getOutgoingEdgeCount(Node vertex) {
       if(directed)
            return dirGraph.getInDegree(vertex);
        else
            return graph.getDegree(vertex);
    }

    @Override
    public int getIngoingEdgeCount(Node vertex) {
       if(directed)
            return dirGraph.getOutDegree(vertex);
        else
            return graph.getDegree(vertex);
    }
    
    @Override
    public Iterable<Node> getNeighbors(Node vertex) {
        return graph.getNeighbors(vertex);
    }

    @Override
    public Node derivGetStartVertex(Edge edge) {
        return edge.getSource();
    }

    @Override
    public Node derivGetEndVertex(Edge edge) {
        return edge.getTarget();
    }

    @Override
    protected double derivGetEdgeWeight(Edge edge) {
        return edge.getWeight();
    }

    @Override
    protected void derivSetEdgeWeight(Edge edge, double weight) {
        edge.setWeight((float)weight);
    }

    @Override
    public String getLibName() {
        return "Gephi";
    }

    @Override
    public Object getInternalGraph() {
        return graph;
    }

    @Override
    protected void derivRemoveVertex(Node vertex) {
        graph.removeNode(vertex);
    }

    @Override
    protected void derivRemoveEdge(Edge edge) {
        graph.removeEdge(edge);
    }

    @Override
    public Edge getEdge(Node vertex1, Node vertex2) {
        return graph.getEdge(vertex1, vertex2);
    }

    @Override
    protected void derivSetVertexIdent(Node vertex, String ident) {
        throw new RuntimeException("Identifiers not supported");
    }

    @Override
    public String getVertexIdent(Node vertex) {
        throw new RuntimeException("Identifiers not supported");
    }

    @Override
    public Object getVertexStatus(Node vertex) {
        return vertex.getNodeData().getAttributes().getValue(STATUS_ID);
    }

    @Override
    public void setVertexStatus(Node vertex, Object status) {
        vertex.getNodeData().getAttributes().setValue(STATUS_ID, status);
    }

    @Override
    public Object getEdgeStatus(Edge edge) {
        return edge.getEdgeData().getAttributes().getValue(STATUS_ID);
    }

    @Override
    public void setEdgeStatus(Edge edge, Object status) {
        edge.getEdgeData().getAttributes().setValue(STATUS_ID, status);
    }

    @Override
    public Object getVertexData(Node vertex) {
        return vertex.getNodeData().getAttributes().getValue(DATA_ID);
    }

    @Override
    public void setVertexData(Node vertex, Object data) {
        vertex.getNodeData().getAttributes().setValue(DATA_ID, data);
    }

    @Override
    public Object getEdgeData(Edge edge) {
        return edge.getEdgeData().getAttributes().getValue(DATA_ID);
    }

    @Override
    public void setEdgeData(Edge edge, Object data) {
        edge.getEdgeData().getAttributes().setValue(DATA_ID, data);
    }

    public void createFromExistingGraph(GraphModel graphModel) {
        this.graphModel = graphModel;
        this.graph = graphModel.getGraph();
        directed = graph instanceof DirectedGraph;
        if(directed)
            this.dirGraph = (DirectedGraph)graph;
        else
            this.undirGraph = (UndirectedGraph)graph;
        weighted = true;
    }
}
