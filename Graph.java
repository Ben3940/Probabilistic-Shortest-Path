/*
 * Student Name: Benjamin Yanick
 * File Name: Graph.java
 * Assignment Number: 2
 * 
 * Description:
 *   The Graph class contains data about the graph data structure, such as the adjacency_matrix, start, and end vertices.
 *    It also handles all logic associated with getting a specific edge from the graph (get_edge()), start and end vertices,
 *    the entire adjacency matrix, and adjacent vertices to a specified vertex.  The weights are stored in the adjacency matrix
 *    and are the probabilities of a given edge being open during the DFS traversal approach.
 */


import java.util.ArrayList;

public class Graph {
    
    // Adjacency matrix with probability weights of edge being open
    double[][] adjacency_matrix = {
        {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0.7, 0, 1, 0, 0, 0},
        {1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0.7, 0, 0, 0.5, 0},
        {0, 0.7, 1, 0, 0, 0, 0, 0.3, 0, 0},
        {0, 0, 1, 0.7, 0, 0, 0, 0.1, 1, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0.3, 0.1, 0, 0, 0.2, 0.8},
        {0, 0, 0, 0.5, 0, 1, 0, 0.2, 0, 0.1},
        {0, 0, 0, 0, 0, 0, 1, 0.8, 0.1, 0},
    };
    int start, end;

    public Graph(){
        
        this.start = 0;
        this.end = 9;
    }

    
    // Getters for start vertex, end vertex, and adjacency_matrix
    public int get_start(){
        return this.start;
    }

    public int get_end(){
        return this.end;
    }

    public double[][] get_adjacency_matrix(){
        return this.adjacency_matrix;
    }



    // Returns list of adjacent vertices for vertex_idx
    public ArrayList<Integer> get_adjacent_vertices(int vertex_idx){
        ArrayList<Integer> adjacent = new ArrayList<>();

        // Loop through vertices 0-9; if weight > 0, vertex is adjacent
        for(int i =0; i <= 9; i++){
            if(this.adjacency_matrix[vertex_idx][i] > 0){
                adjacent.add(i);
            }
        }
        return adjacent;
    }

    // Returns weight value of edge between specified vertices
    public double get_edge(int from_vertex, int to_vertex){
        return this.adjacency_matrix[from_vertex][to_vertex];
    }

    // Prints adjacency matrix of Graph class
    public void print_adjacency_matrix(){
        System.out.print("    ");
        for (int i = 0; i < this.adjacency_matrix.length; i++){
            System.out.print(i + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < this.adjacency_matrix.length; i++){
            System.out.print(i + "   ");
            for (int j = 0; j < this.adjacency_matrix[0].length; j++){
                System.out.print(this.adjacency_matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
