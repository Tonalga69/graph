package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Main extends JPanel {
    static String startNode = "A";
    static String endNode = "E";
    static String smallestNodeName;
    static JButton button = new JButton("Press");
    static boolean isFinished=false;


    static Map<String, Node> nodes = Map.of(
            "A", new Node("A", 50, 75),
            "B", new Node("B", 250, 50),
            "C", new Node("C", 250, 150),
            "D", new Node("D", 50, 200),
            "E", new Node("E", 550, 250),
            "F", new Node("F", 550, 50)
    );

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dijkstra");
        Main main = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(main);
        main.add(button);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

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
        smallestNodeName = startNode;

        button.addActionListener(e -> {
            Timer timer = new Timer(2000, null);
            timer.addActionListener(e1 -> {
                final boolean res = calculateNodesWeight(nodes, smallestNodeName, endNode);
                if (res) {
                    timer.stop();
                   isFinished=true;
                   main.repaint();
                    return;
                }
                smallestNodeName = getSmallestNodeNotVisited(nodes).name;
                main.repaint();
            });


            timer.start();

        });


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("Start Node: " + startNode, 10, 10);
        g.drawString("End Node: " + endNode, 10, 30);
        if(isFinished){
           paintPath(g);
           return;
        }
        drawNodes(g);

    }



    private static void drawNodes(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(0, 0, 800, 800);
        g.setColor(Color.BLACK);
        for (Node node : nodes.values()) {
            if (node.isVisited()) {
                g.setColor(Color.RED);
            }
            g.fillOval(node.getX(), node.getY(), 30, 30);
            g.setColor(Color.white);
            g.drawString(node.name, node.getX() + 10, node.getY() + 15);
            g.setColor(Color.BLACK);
            if (node.value != Integer.MAX_VALUE) {
                g.drawString(String.valueOf(node.value), node.getX() + 15, node.getY() - 15);
            }
            for (Edge edge : node.getEdges()) {
                g.drawLine(node.getX() + 15, node.getY() + 15, edge.nextNode.getX() + 15, edge.nextNode.getY() + 15);
                g.drawString(String.valueOf(edge.weight), (node.getX() + edge.nextNode.getX()) / 2, (node.getY() + edge.nextNode.getY()) / 2);
            }

        }
    }

    static boolean calculateNodesWeight(Map<String, Node> nodes, String startNode, String endNode) {
        if (startNode.equals(endNode)) {
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

    static void paintPath(Graphics g) {
        drawNodes(g);
        Node node = nodes.get(endNode);
        while (node != null) {
            g.setColor(Color.GREEN);
            g.fillOval(node.getX(), node.getY(), 30, 30);
            g.setColor(Color.white);
            g.drawString(node.name, node.getX() + 10, node.getY() + 15);
            if(node.previousNode==null){
                break;
            }
            g.setColor(Color.GREEN);
            g.drawLine(node.getX() + 15, node.getY() + 15, node.previousNode.getX() + 15, node.previousNode.getY() + 15);
            node = node.previousNode;
        }

    }


}