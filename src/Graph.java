import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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



        public  void constructAdjacencyMatrix() {

            System.out.println("Adjacency Matrix:");
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    System.out.print(adjacencyMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }


        public  Boolean isThereAPath(String v1 , String v2){

        }

        public  void BFSfromTo(String v1, String v2){

        }

        public  void DFSfromTo(String v1, String v2){

        }

        public  int WhatIsShortestPathLength(String v1, String v2){

        }

        public  int NumberOfSimplePaths(String v1, String v2){

        }

        public  String Neighbors(String v1){

        }

        public  String HighestDegree(){

        }

        public  boolean IsDirected(){

        }

        public  boolean AreTheyAdjacent(String v1, String v2){

        }

        public  boolean Istherecycle(String v1){

        }

        public  int NumberOfVerticesInComponent(String v1){

        }




    }
