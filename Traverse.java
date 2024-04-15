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
    int backtrack_vertex;
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

    public void reset_state(){
        this.paths_map = new HashMap<>();
        this.visited = new HashSet<>();
        this.paths_map.put(graph.get_start(), -1);
        this.visited.add(this.graph.get_start());
        this.hops = this.dfs_misses = 0;

    }

    public void bfs_traverse(){
        
        
        
        while (!this.queue.isEmpty()){
            // Get next vertex to visit
            int parent_idx = this.queue.poll();
            // At end
            if (parent_idx == this.graph.get_end()){
                break;
            }

            

            // Get adjacent vertices of current vertex
            ArrayList<Integer> adjacent =  this.graph.get_adjacent_vertices(parent_idx);
            // System.out.println("Parent: " + parent_idx + " Adjacent: " + adjacent);
            // Queue all adjacent verticies of current vertex that are not yet visited
            // Add neighbor,parent (<key><value>) pair to paths_map for backtracking path
            for (int neighbor : adjacent){
                
                if(!this.visited.contains(neighbor)){
                    this.queue.add(neighbor);
                    this.visited.add(neighbor);
                    this.paths_map.put(neighbor, parent_idx);
                }
            }
        }
        System.out.println("BFS - deterministic solution");
        System.out.println("An optimal solution: ");
        System.out.println(this.get_path());
        System.out.println("Total hops = " + this.get_hops() + "\n");

    }

    public void dfs_traverse(){
        this.backtrack_vertex = 0;
        
        System.out.println("DFS - probabilistic solution");
        this.dfs_traverse_recurse(0, 9);
        System.out.println("Total DFS hops = " + this.get_hops());
        System.out.println("Total DFS misses = " + this.dfs_misses);
        System.out.println("Path = " + this.get_path_dfs() + "\n");
    }

    public void dfs_traverse_recurse(int current_idx, int end){
        System.out.println("On vertex: " + current_idx);
        
        this.visited.add(current_idx);
        this.stack.push(current_idx);
        this.path.add(current_idx);
        this.hops++;
        if (current_idx == end){
            this.cont = false;
            this.hops--;
            return;
        }

        ArrayList<Integer> adjacent =  this.graph.get_adjacent_vertices(current_idx);
        Collections.reverse(adjacent);

        
        for (int neighbor : adjacent){
            if(!this.visited.contains(neighbor)){
                
                double r = Math.floor(Math.random() * 100);
                double chance = this.graph.get_edge(current_idx, neighbor) * 100;
                // System.out.print(current_idx + " to " + neighbor + " edge visiting chance: " + chance + "\t r: " + r + "  ");

                

                if (chance == 100 || r <= chance){
                    System.out.print("Going on edge (" + current_idx + "," + neighbor + ") edge prob = " + chance + "% " + (chance == 100 ? "\n" : "r = " + r + "\n"));
                    
                    this.paths_map.put(neighbor, current_idx);
                    this.backtrack_vertex = current_idx;
                    this.dfs_traverse_recurse(neighbor, end);
                    if (!this.cont){
                        return;
                    }
                } else {
                    this.hops++;
                    this.dfs_misses++;
                    System.out.println("Cannot go on edge (" + current_idx + "," + neighbor + ") edge prob = " + chance + "% " + "r = " + r);
                }
            }
        }
        this.stack.pop();
        int vertex = this.stack.peek();
        // this.visited.remove(vertex_leaving);
        this.path.add(vertex);
        System.out.println("Going back to vertex: " + vertex);
        this.hops++;
    }

    public void print_parent_map(){
        for (int key : this.paths_map.keySet()){
            System.out.println("Key: " + key + " Value: " + this.paths_map.get(key));
        }
    }

    public ArrayList<Integer> get_path(){
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

    public ArrayList<Integer> get_path_dfs(){
        // ArrayList<Integer> path = new ArrayList<>();
        // while(!this.stack.empty()){
        //     path.add(this.stack.pop());
        // }
        // Collections.reverse(path);
        // return path;
        return this.path;
    }

    public int get_hops(){
        return this.hops;
    }

    public void set_hops(ArrayList<Integer> path){
        this.hops = path.size();
    }

    
}
