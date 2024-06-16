package org.example;

import java.util.ArrayList;

public class Node {
    Integer value;
    Node previousNode;
    String name;

    private final int x;
    private final int y;
    private boolean visited;
    private final ArrayList<Edge> edges = new ArrayList<>();

    public Node(String name, int x, int y) {
        this.visited = false;
        this.value =Integer.MAX_VALUE;
        this.previousNode = null;
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void addEdge(Edge edge) {
        edges.add(edge);
       // edge.nextNode.edges.add(new Edge(edge.weight, this));
    }
    ArrayList<Edge> getEdges() {
        return edges;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void calculateNodesWeight() {
        for (Edge edge : edges) {
            if(edge.weight+this.value < edge.nextNode.value){
                edge.nextNode.value =edge.weight+this.value;
                edge.nextNode.previousNode=this;
            }
        }

    }


}
