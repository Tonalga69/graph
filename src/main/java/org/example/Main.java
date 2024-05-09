package org.example;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Buscador de nodos!");
        Scanner scanner = new Scanner(System.in);
        String startNode;
        String endNode;

        System.out.println("Por favor, ingrese el nodo de inicio A-F: ");
        startNode=scanner.next();
        System.out.println("Por favor, ingrese el nodo final A-F: ");
        endNode=scanner.next();


        Map<String, Node> nodes = Map.of(
                "A", new Node("A"),
                "B", new Node("B"),
                "C", new Node("C"),
                "D", new Node("D"),
                "E", new Node("E"),
                "F", new Node("F")
        );
        nodes.get("A").addEdge(new Edge(1, nodes.get("B")));
        nodes.get("A").addEdge(new Edge(6, nodes.get("C")));
        nodes.get("A").addEdge(new Edge(3, nodes.get("D")));
        nodes.get("B").addEdge(new Edge(8, nodes.get("E")));
        nodes.get("B").addEdge(new Edge(9, nodes.get("F")));
        nodes.get("B").addEdge(new Edge(4, nodes.get("C")));
        nodes.get("C").addEdge(new Edge(2, nodes.get("D")));
        nodes.get("C").addEdge(new Edge(12, nodes.get("E")));
        nodes.get("E").addEdge(new Edge(1, nodes.get("F")));

        nodes.get(startNode).value = 0;
        String smallestNodeName = startNode;

        while (true) {
           final boolean res=calculateNodesWeight(nodes, smallestNodeName, endNode);
           if(res){
               break;
           }
           smallestNodeName =getSmallestNodeNotVisited(nodes).name;
        }

        printPath(nodes, endNode);

    }

    static  boolean calculateNodesWeight(Map<String, Node> nodes, String startNode, String endNode) {
        if(startNode.equals(endNode)) {
            return true;
        }
        nodes.get(startNode).setVisited(true);
        nodes.get(startNode).calculateNodesWeight();
        return false;
    }

    static Node getSmallestNodeNotVisited(Map<String, Node> nodes) {
        Node smallestNode = null;
        Integer smallestWeight = Integer.MAX_VALUE;
        for (Node node : nodes.values()) {
            if (!node.isVisited() && (smallestNode == null || node.value < smallestWeight)) {
                smallestNode = node;
                smallestWeight = node.value;
            }
        }
        return smallestNode;
    }

    static void printPath(Map<String, Node> nodes, String endNode) {
        Node node = nodes.get(endNode);
        System.out.println("El peso total es: " + node.value);
        System.out.println("El camino más corto es: ");
        while (node != null) {
            System.out.println(node.name + " -> ");
            node = node.previousNode;
        }
    }




}