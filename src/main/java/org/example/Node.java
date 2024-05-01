package org.example;

import java.util.ArrayList;

public class Node {
    Integer weight;
    Node previousNode;
    String name;
    private boolean visited;
    private ArrayList<Edge> edges = new ArrayList<Edge>();

    public Node(String name) {
        this.visited = false;
        this.weight =Integer.MAX_VALUE;
        this.previousNode = null;
        this.name = name;
    }



    void addEdge(Edge edge) {
        edges.add(edge);
        edge.nextNode.edges.add(new Edge(edge.weight, this));
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void calculateNodesWeight() {
        for (Edge edge : edges) {
            if(edge.weight+this.weight< edge.nextNode.weight){
                edge.nextNode.weight=edge.weight+this.weight;
                edge.nextNode.previousNode=this;
            }
        }

    }

    public void setEdges(ArrayList<Edge> edges) {
        this.edges = edges;
    }
}
