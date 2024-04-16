/*
 * Student Name: Benjamin Yanick
 * File Name: Main.java
 * Assignment Number: 2
 * 
 * Description:
 *   The main program which instantiates a Graph object and Traverse object.  
 *    These two classes will handle all logic and data for the program.
 */


public class Main{
    public static void main(String[] args){
        Graph graph = new Graph();
        Traverse trav = new Traverse(graph);

        trav.bfs_traverse();
        trav.reset_state();
        trav.dfs_traverse();
    }
}