package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Examen mamalon!");


        ArrayList<Edge> graph1 = new ArrayList<Edge>(
              List.of(
                      new Edge(3, new Node("A"), new Node("B")),
                      new Edge(2, new Node("B"), new Node("C")),
                      new Edge(1, new Node("C"), new Node("D")),
                      new Edge(4, new Node("D"), new Node("E")),
                      new Edge(5, new Node("E"), new Node("A")),
                      new Edge(6, new Node("A"), new Node("C"))
              )
        );
        boolean[][] matriz1 ={
                {false, false, true, false, false},
                {false, true, false, true, false},
                {true, false, true, true, true},
                {false, true, true, false, false},
                {false, false, true, false, false}
        };

        boolean[][] matriz2={
                {true, false, false, true, false},
                {false, true, true, true, false},
                {false, true, true, false, true},
                {true, true, false, false,  false},
                {false, false, true, false, false}
        };

        boolean[][] matriz3={
                {false, true, false,false, false},
                {true, true, true, true, true},
                {false, true, false, true, false},
                {false, true, true, false, false},
                {false, true, false, false, true}

        };

        boolean[][] matriz4={
                {true, true, false, false, false},
                {true, true, false, true, true},
                {false, false, false, false, true},
                {false, true, false, true, false},
                {false, true, true, false, false}
        };

        boolean[][] matriz5={
                {false, true, false, false, false},
                {true, false, false, true, true},
                {false, false, true, false, true},
                {false, false, false, true, true},
                {false, true, true, true, false}
        };
        System.out.println("Matriz 1");
        ArrayList<ArrayList<Boolean>> matrizDinamica1 = convertirAMatrizDinamica(matriz1);
        imprimirMatriz(matrizDinamica1);
        System.out.println("Matriz 2");
        ArrayList<ArrayList<Boolean>> matrizDinamica2 = convertirAMatrizDinamica(matriz2);
        imprimirMatriz(matrizDinamica2);
        System.out.println("Matriz 3");
        ArrayList<ArrayList<Boolean>> matrizDinamica3 = convertirAMatrizDinamica(matriz3);
        imprimirMatriz(matrizDinamica3);
        System.out.println("Matriz 4");
        ArrayList<ArrayList<Boolean>> matrizDinamica4 = convertirAMatrizDinamica(matriz4);
        imprimirMatriz(matrizDinamica4);
        System.out.println("Matriz 5");
        ArrayList<ArrayList<Boolean>> matrizDinamica5 = convertirAMatrizDinamica(matriz5);
        imprimirMatriz(matrizDinamica5);



    }

    public static ArrayList<ArrayList<Boolean>> convertirAMatrizDinamica(boolean[][] matrizEstatica) {
        ArrayList<ArrayList<Boolean>> matrizDinamica = new ArrayList<>();
        for (int i = 0; i < matrizEstatica.length; i++) {
            matrizDinamica.add(new ArrayList<>());
            for (int j = 0; j < matrizEstatica[i].length; j++) {
                matrizDinamica.get(i).add(matrizEstatica[i][j]);
            }
        }
        return matrizDinamica;
    }

    public static void imprimirMatriz(ArrayList<ArrayList<Boolean>> matriz) {
        for (int i = 0; i < matriz.size(); i++) {
            System.out.print(i + " ");
            for (int j = 0; j < matriz.get(i).size(); j++) {
                System.out.print(matriz.get(i).get(j) ? "true" : "false");
                if (j < matriz.get(i).size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }





}