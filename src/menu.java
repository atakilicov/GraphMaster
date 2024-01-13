import java.sql.SQLOutput;
import java.util.Scanner;


public class menu {
    static java.util.Scanner input = new Scanner(System.in);
    static Graph Graph = new Graph();

    public static void menu() {


        System.out.println("enter the operation you want to do\n"+"1-Read Graph From File" +
                "2-Find Is There A Path?" +
                "3- BFS vertices" +
                "4-DFS vertices" +
                "5-Find The Shortest Path lenght" +
                "6-Find The Number Of Paths" +
                "7-Neighbour Names" +
                "8-Find Highest Degrees Name" +
                "9-Find if is Directed" +
                "10-Find if they Adjacent" +
                "11-Is there a cycle?" +
                "12-Number of Vertices In component");


        switch (input.nextInt()) {
            case 1:

               Graph.readGraphFromFile(askForWord("file path"));
               print("done!");
                break;

            case 2:

                if(Graph.isThereAPath(askForWord("vertice 1"),askForWord("vertice 2"))){
                   print("There Is A Path!");}
                else{
                    print("no path");
                }
                break;

            case 3:
                Graph.BFSfromTo(askForWord("vertice 1"),askForWord("vertice2"));
                break;

            case 4:
                Graph.DFSfromTo(askForWord("vertice 1"),askForWord("vertice2"));
                break;

            case 5:
                print(Graph.WhatIsShortestPathLength(askForWord("vertice 1"),askForWord("vertice2")));
                break;
            case 6:
               print(Graph.NumberOfSimplePaths(askForWord("vertice 1"),askForWord("vertice2")));
                break;
            case 7:
                Graph.Neighbors(askForWord("vertice"));
                break;
            case 8:
                print(Graph.HighestDegree());
            case 9:
                if (Graph.IsDirected()){
                    print("graph is directed");
                }
                else
                    print("graph is not directed");

                break;
            case 10:
                if (Graph.AreTheyAdjacent(askForWord("vertice 1"),askForWord("vertice2"))){
                    print("they are adjacent");
                }
                else
                    print("they are not adjacent");

                break;
            case 11:
                if (Graph.Istherecycle(askForWord("vertice 1"))){
                    print("There is a path!");
                }
                else
                    print("there is no path");
                break;
            case 12:
                print(Graph.NumberOfVerticesInComponent(askForWord("vertice")));
                break;

            default:
            print("enter a valid input");






        }

    }

    public static String askForWord(String S) {
        print("Enter the string " + S);
        return input.next();
    }

    public static void print(Object o){
        System.out.println(o); // S.O.U.T kalabalığını azaltmak için ufak bir dokunuş : )
    }
}
