import java.io.*;
import java.util.*;

public class Graph {


    private static final int MAX_VERTICES = 100;
    private static int[][] adjacencyMatrix;
    private static HashTable vertexTable;
    private static int numVertices;

    String getName(int i){ //helper function to obtain cleaner code
        return vertexTable.table[i].getKey();

    }


    public Graph() {
        adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
        vertexTable = new HashTable();
        numVertices = 0;


    }

    //--------------------**** O P E R A T I O N S ****--------------------
    public void readGraphFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null)
                readLine(line);
        } catch (IOException e) {
            System.out.println("an error ocured while reading!");
        }
    }

    private void readLine(String line) {
        String[] parca = line.split("->");
        if (parca.length == 2) {
            String vertex = parca[0].trim();
            String[] edges = parca[1].split(",");

            if (vertexTable.getValue(vertex) == -1)
                vertexTable.insert(vertex, numVertices++);


            for (String edge : edges) {
                String[] edgeParts = edge.split(":");
                if (edgeParts.length == 2) {
                    String endVertex = edgeParts[0].trim();
                    int weight = Integer.parseInt(edgeParts[1].trim());

                    if (vertexTable.getValue(endVertex) == -1)
                        vertexTable.insert(endVertex, numVertices++);


                    int startIndex = vertexTable.getValue(vertex);
                    int endIndex = vertexTable.getValue(endVertex);
                    adjacencyMatrix[startIndex][endIndex] = weight;
                }
            }
        }
    }


    public void printAdjacencyMatrix() {

        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++)
                System.out.print(adjacencyMatrix[i][j] + " ");
            System.out.println();
        }
    }


    public Boolean isThereAPath(String v1, String v2) {
        return adjacencyMatrix[vertexTable.getValue(v1)][vertexTable.getValue(v2)] != 0;
    }

    public void BFSFromTo(String v1, String v2) {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        int v1int = vertexTable.getValue(v1), v2int = vertexTable.getValue(v2);
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[v1int] = true;

        queue.add(v1int);

        while (!queue.isEmpty()) {
            int cV = queue.poll(); //currentVertex is polled from the queue to be printed
            System.out.print(getName(cV) + " ");
            if (cV == v2int){ //currentVertex is the final destination vertex of "v2"
                System.out.println();
                return;
            }
            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[cV][i]!=0 && !visited[i]){ //if there is a weighted path of non-zero from currentVertex to ith and its not visited
                    visited[i]=true;
                    queue.add(i); //add the next vertex to the queue to check
                }
            }
        }
    }

    public void DFSFromTo(String v1, String v2) {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        int v1int = vertexTable.getValue(v1), v2int = vertexTable.getValue(v1);
        if (v1int == -1 || v2int == -1){

            return; //if there is no corresponding value for the v1 or v2 inputs, there is no such given vertex exists
        }

        if(DFSHelper(v1int,v2int,visited)) {
            //calling the util recursive function for DFS, a return of true meaning the path-finding concluded an end to the final dest. vertex v2
        }

        else
            System.out.printf("No available path from [%s] to [%s]",v1,v2); //if the util function returns false, no path-finding was possible between the last currentVertex to v2


    }
    private boolean DFSHelper(int cVint, int v2int, boolean [] visited){
        System.out.print(getName(cVint) + " ");
        visited[cVint] = true;
        if (cVint == v2int) //if the current vertex is the final destination vertex of v2 the helper method returns true to end the main non-recursive DFS method.
            return true;
        for (int i = 0; i < numVertices; i++) {
            if(adjacencyMatrix[cVint][i] != 0 && !visited[i]) //
                if (DFSHelper(i,v2int,visited))
                    return true;
        }
        return false;
    }
    public int WhatIsShortestPathLength(String v1, String v2) {
        int v1int = vertexTable.getValue(v1);
        int v2int = vertexTable.getValue(v2);

        if (v1int == -1 || v2int == -1) {
            System.out.println("Start or end vertex not found.");
            return -1;
        }

        if (v1int == v2int) {
            return 0;  // Same vertex, no distance to itself
        }

        boolean[] visited = new boolean[numVertices];
        int[] distance = new int[numVertices];

        LinkedList<Integer> queue = new LinkedList<>();
        visited[v1int] = true;
        queue.add(v1int);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentVertex][i] != 0 && !visited[i]) {
                    visited[i] = true;
                    distance[i] = distance[currentVertex] + 1;
                    queue.add(i);

                    if (i == v2int) {
                        return distance[i];  // Found the shortest path
                    }
                }
            }
        }

        System.out.println("No path from " + v1 + " to " + v2);
        return -1;  // No path found
    }

    public int NumberOfSimplePaths(String v1, String v2) {

        int index1 = vertexTable.getValue(v1);
        int index2 = vertexTable.getValue(v2);

        if (index1 == -1 || index2 == -1) {
            System.out.println("Vertex not found.");
            return -1;
        }

        int weight1 = adjacencyMatrix[index1][index2];
        int weight2 = adjacencyMatrix[index2][index1];

        if (weight1 != 0 && weight2 != 0) {
            return weight1 + weight2;
        } else {
            System.out.println("No edge between " + v1 + " and " + v2);
            return -1;
        }
    }





    /**public String Neighbors(String v1) {
        int vertexIndex = vertexTable.getValue(v1);
        String nbrs = "";
        System.out.println(vertexIndex);
        for (int i = 0; i < numVertices; i++) {
            if(vertexTable.table[i] != null)
                System.out.println(vertexTable.table[i].getKey());
        }
        if (vertexIndex != -1) {
            for (int i = 0; i < numVertices; i++) {
                int weight = adjacencyMatrix[vertexIndex][i];
                if (weight != 0 && vertexTable.table[i] != null) {
                    nbrs += vertexTable.table[i].getKey()+ " ";
                }
            }
        } else {
            System.out.println("Vertex not found: " + v1);
        }
        return nbrs;
    }**/

    public String Neighbors(String v1) {
        String nbrs = "";
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[vertexTable.getValue(v1)][i] > 0 && vertexTable.table[i]!=null){
                nbrs += vertexTable.table[i].getKey() + " ";
            }
        }
        return nbrs;
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


        return getName(v1) + "and" + getName(v2) + " has highest degree of " + high;
    }




    public boolean IsDirected() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                int weight1 = adjacencyMatrix[i][j];
                int weight2 = adjacencyMatrix[j][i];

                if (weight1 != weight2) {
                    return true; // Directed
                }
            }
        }

        return false; // Undirected
    }




    public boolean AreTheyAdjacent(String v1, String v2) {
        int index1 = vertexTable.getValue(v1);
        int index2 = vertexTable.getValue(v2);

        if (index1 == -1 || index2 == -1) {
            System.out.println("Vertex not found.");
            return false;
        }

        int weight = adjacencyMatrix[index1][index2];

        return weight != 0;
    }


    public boolean Istherecycle(String v1) {
        boolean[] visited = new boolean[numVertices];
        Arrays.fill(visited, false);

        int vertexIndex = vertexTable.getValue(v1);
        return dfsHasCycle(vertexIndex, visited);
    }

    private boolean dfsHasCycle(int vertexIndex, boolean[] visited) {
        // If the current vertex has already been visited, there is a cycle.
        if (visited[vertexIndex]) {
            return true;
        }

        // Mark the current vertex as visited.
        visited[vertexIndex] = true;

        // Recursively visit all neighbors of the current vertex.
        for (int i = 0; i < numVertices; i++) {
            int weight = adjacencyMatrix[vertexIndex][i];
            if (weight != 0 && vertexTable.table[i] != null) {
                if (dfsHasCycle(i, visited)) {
                    return true;
                }
            }
        }

        // Reset the visited status for backtracking.
        visited[vertexIndex] = false;

        return false;
    }

    public int NumberOfVerticesInComponent(String v1) {
        int startVertexIndex = vertexTable.getValue(v1);

        if (startVertexIndex == -1) {
            System.out.println("Vertex not found.");
            return 0;
        }

        boolean[] visitedVertices = new boolean[numVertices];
        return dfsCountComponentVertices(startVertexIndex, visitedVertices);
    }

    private int dfsCountComponentVertices(int currentVertex, boolean[] visitedVertices) {
        visitedVertices[currentVertex] = true;
        int componentSize = 1;

        for (int i = 0; i < numVertices; i++) {
            int weight = adjacencyMatrix[currentVertex][i];

            if (weight != 0 && !visitedVertices[i]) {
                componentSize += dfsCountComponentVertices(i, visitedVertices);
            }
        }

        return componentSize;
    }




}
