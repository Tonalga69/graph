package org.example;

public class Edge {
    Integer weight;
    Node nextNode;

    public Edge(Integer weight, Node nextNode){
        this.weight=weight;
        this.nextNode=nextNode;
    }
}
