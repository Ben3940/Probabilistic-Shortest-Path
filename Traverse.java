/*
 * Student Name: Benjamin Yanick
 * File Name: Traverse.java
 * Assignment Number: 2
 * 
 * Description:
 *   Handles the main workload of the program.  References a Graph object to perform both BFS and DFS traversals.
 *    For each traversal approach, a path is recorded for how the traversal approach moved throughout the graph.
 *    The BFS approach uses a iterative approach (via a while loop) while the DFS uses a recursive approach.  There
 *    is also a function for printing the final results of the traversal and has logic to handle the DFS and BFS 
 *    approachs differently.  The BFS approach also uses a parent map (Java HashMap) that is used to recreate the
 *    final path taken by the BFS approach.
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Stack;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;

public class Traverse {
    Graph graph;
    Map<Integer, Integer> paths_map = new HashMap<>();
    Set<Integer> visited = new HashSet<Integer>();
    Queue<Integer> queue = new LinkedList<Integer>();
    Stack<Integer> stack = new Stack<Integer>();
    ArrayList<Integer> path = new ArrayList<Integer>();
    boolean cont = true;
    int hops = 0;
    int dfs_misses = 0;


    // Constructor: BFS adds root vertex of graph to queue
    public Traverse(Graph graph){
        this.graph = graph;
        this.queue.add(graph.get_start());
        this.paths_map.put(graph.get_start(), -1);
        this.visited.add(graph.get_start());
    }

    // Reset all shared state between BFS and DFS, so BFS and DFS can be performed back-to-back
    public void reset_state(){
        this.visited = new HashSet<>();
        this.visited.add(this.graph.get_start());
        this.hops = 0;

    }

    // Traversal using BFS (benchmark optimal) approach
    public void bfs_traverse(){
        
        
        
        while (!this.queue.isEmpty()){
            // Get next vertex to visit
            int parent_idx = this.queue.poll();
            // At end, stop traversing
            if (parent_idx == this.graph.get_end()){
                break;
            }

            

            // Get adjacent vertices of current vertex
            ArrayList<Integer> adjacent =  this.graph.get_adjacent_vertices(parent_idx);
            
            // Queue all adjacent verticies of current vertex that are not yet visited
            // Add neighbor,parent (<key><value>) pair to paths_map for final path
            for (int neighbor : adjacent){
                // Neighbor not visited yet, add to queue
                if(!this.visited.contains(neighbor)){
                    this.queue.add(neighbor);
                    this.visited.add(neighbor);
                    this.paths_map.put(neighbor, parent_idx);
                }
            }
        }

        this.print_results(true);
    }

    // Wrapper to recursive function implementing DFS approach
    public void dfs_traverse(){
        // Begin recusion
        this.dfs_traverse_recurse(0, 9);
        this.print_results(false);
        
    }

    // Recursive function for DFS
    public void dfs_traverse_recurse(int current_idx, int end){
        System.out.println("On vertex: " + current_idx);
        
        // Visit current_idx, add it to stack, add to running path of hops, increment hop count
        this.visited.add(current_idx);
        this.stack.push(current_idx);
        this.path.add(current_idx);
        this.hops++;

        // Reached end, return from recursive calls
        if (current_idx == end){
            this.cont = false;
            this.hops--;
            return;
        }

        // Get list of adjacent vertices, reverse ordering so to consider largest to smallest vertex numbers
        ArrayList<Integer> adjacent =  this.graph.get_adjacent_vertices(current_idx);
        Collections.reverse(adjacent);

        // Loop through neighbors and visit any that have not been visited yet
        for (int neighbor : adjacent){
            if(!this.visited.contains(neighbor)){
                
                // Randomly select r value from range (0 to 1) * 100 => (0 to 99)
                double r = Math.floor(Math.random() * 100);
                double chance = this.graph.get_edge(current_idx, neighbor) * 100;

                
                // Success conditions to move to neighbor
                if (chance == 100 || r <= chance){
                    System.out.print("Going on edge (" + current_idx + "," + neighbor + ") edge prob = " + chance + "% " + (chance == 100 ? "\n" : "r = " + r + "\n"));

                    // Recursivly call next DFS hop
                    this.dfs_traverse_recurse(neighbor, end);

                    // Condition to check if DFS approach should continue
                    if (!this.cont){
                        return;
                    }

                // Failed hop, still count as hop and increment misses (closed road)
                } else {
                    this.hops++;
                    this.dfs_misses++;
                    System.out.println("Cannot go on edge (" + current_idx + "," + neighbor + ") edge prob = " + chance + "% " + "r = " + r);
                }
            }
        }

        // Could not hop to any adjacent neighbor, so backtrack to previous vertex.  We pop from stack because that would be the vertex without
        //   adjacent neighbors to hop to.  The second vertex from the top of stack is vertex to backtrack to.
        this.stack.pop();
        int vertex = this.stack.peek();
        this.path.add(vertex);
        System.out.println("Going back to vertex: " + vertex);

        // Backtracking still counts as hop
        this.hops++;
    }

    // Prints either the BFS or DFS results
    public void print_results(boolean bfs_approach){
        // Print final results of BFS approach
        if(bfs_approach){
            System.out.println("BFS - deterministic solution");
            System.out.println("An optimal solution: ");
            System.out.println(this.get_path_bfs());
            System.out.println("Total hops = " + this.hops + "\n");
        
        // Pritn final results of DFS approach
        } else {
            System.out.println("DFS - probabilistic solution");
            System.out.println("Total DFS hops = " + this.hops);
            System.out.println("Total DFS misses = " + this.dfs_misses);
            System.out.println("Path = " + this.path + "\n");
        }
    }

    // Print parent-path map used for BFS approach
    public void print_parent_map(){
        for (int key : this.paths_map.keySet()){
            System.out.println("Key: " + key + " Value: " + this.paths_map.get(key));
        }
    }

    // Construct the final path for the BFS appraoch using the paths_map
    public ArrayList<Integer> get_path_bfs(){
        ArrayList<Integer> path = new ArrayList<>();
        int key = this.graph.get_end();
        while (key != this.graph.get_start()){
            path.add(key);
            key = this.paths_map.get(key);
        }
        this.set_hops(path);
        path.add(key);
        Collections.reverse(path);
        return path;
    }

    // Sets hop count manually
    public void set_hops(ArrayList<Integer> path){
        this.hops = path.size();
    }

    
}
