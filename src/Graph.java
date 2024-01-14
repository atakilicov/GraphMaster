import javax.print.attribute.IntegerSyntax;
import java.io.*;
import java.util.*;

public class Graph {


        private static final int MAX_VERTICES = 100;
        private static int[][] adjacencyMatrix;
        private  static HashTable vertexTable;
        private static int numVertices;

        public Graph() {
            adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
            vertexTable = new HashTable();
            numVertices = 0;




        }
    //--------------------**** O P E R A T I O N S ****--------------------
        public  void readGraphFromFile(String filename) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    readLine(line);
                }
            } catch (IOException e) {
                System.out.println("an error ocured while reading!");
            }
        }

        private  void readLine(String line) {
            String[] parca = line.split("->");
            if (parca.length == 2) {
                String vertex = parca[0].trim();
                String[] edges = parca[1].split(",");

                if (vertexTable.getValue(vertex) == -1) {
                    vertexTable.insert(vertex, numVertices++);
                }

                for (String edge : edges) {
                    String[] edgeParts = edge.split(":");
                    if (edgeParts.length == 2) {
                        String endVertex = edgeParts[0].trim();
                        int weight = Integer.parseInt(edgeParts[1].trim());

                        if (vertexTable.getValue(endVertex) == -1) {
                            vertexTable.insert(endVertex, numVertices++);
                        }

                        int startIndex = vertexTable.getValue(vertex);
                        int endIndex = vertexTable.getValue(endVertex);
                        adjacencyMatrix[startIndex][endIndex] = weight;
                    }
                }
            }
        }



        public  void printAdjacencyMatrix() {

            System.out.println("Adjacency Matrix:");
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }


        public  Boolean isThereAPath(String v1 , String v2){
            return adjacencyMatrix[vertexTable.getValue(v1)][vertexTable.getValue(v2)]!=0;

        }

        public  void BFSfromTo(String v1, String v2){
            boolean [] visited = new boolean[adjacencyMatrix.length];
            LinkedList<Integer> queue = new LinkedList<Integer>();
            visited[vertexTable.getValue(v1)] = true;

            queue.add(vertexTable.getValue(v1));

            while (queue.size()!=0) {
                int tmp = queue.poll();
                System.out.println(tmp + " ");
                //

                Iterator<Integer> i = ;
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n]) {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }

        public  void DFSfromTo(String v1, String v2){

        }

        public  void WhatIsShortestPathLength(String v1, String v2){



        }

        public  int NumberOfSimplePaths(String v1, String v2){

        }

        public  String Neighbors(String v1){
            String nbrs = null;
            int i,e =0;
            boolean a = true;
            for (int w = 0)
               i = adjacencyMatrix[vertexTable.getValue(v1)][e];
                if (i!=0){
                    nbrs+=vertexTable.gethashnode(e).getKey();
                    i++;

                }

            }



        }

    public String HighestDegree() {
        int high = adjacencyMatrix[0][0];
        int v1 = 0, v2 = 0;

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] > high) {
                    high = adjacencyMatrix[i][j];
                    v1 = i;
                    v2 = j;
                }
            }
        }


        return vertexTable.gethashnode(v1).getKey()+"and" + vertexTable.gethashnode(v2).getKey() + " has highest degree of " + high;
    }




    public  boolean IsDirected() {

    }



        public  boolean AreTheyAdjacent(String v1, String v2){

        }

        public  boolean Istherecycle(String v1){

        }

        public  int NumberOfVerticesInComponent(String v1){

        }




    }
