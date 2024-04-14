import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
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
    int backtrack_vertex;
    boolean cont = true;


    // Constructor: BFS adds root vertex of graph to queue
    public Traverse(Graph graph){
        this.graph = graph;
        this.queue.add(graph.get_start());
        this.paths_map.put(graph.get_start(), -1);
        this.visited.add(graph.get_start());
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
            System.out.println("Parent: " + parent_idx + " Adjacent: " + adjacent);
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
    }

    public void dfs_traverse(){
        this.backtrack_vertex = 0;
        this.dfs_traverse_recurse(0, 9);
    }

    public void dfs_traverse_recurse(int current_idx, int end){
        this.visited.add(current_idx);

        if (current_idx == end){
            this.cont = false;
            return;
        }

        ArrayList<Integer> adjacent =  this.graph.get_adjacent_vertices(current_idx);
        Collections.reverse(adjacent);

        for (int neighbor : adjacent){
            if(!this.visited.contains(neighbor)){
                
                double r = Math.floor(Math.random() * 100);
                double chance = this.graph.get_edge(current_idx, neighbor) * 100;
                System.out.print(current_idx + " to " + neighbor + " edge visiting chance: " + chance + "\t r: " + r + "  ");
                if (chance == 100 || r <= chance){
                    System.out.println("Success");
                    this.paths_map.put(neighbor, current_idx);
                    this.dfs_traverse_recurse(neighbor, end);
                    if (!this.cont){
                        return;
                    }
                } else {
                    System.out.println("Failed");
                }
            }
        }





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
        path.add(key);
        Collections.reverse(path);
        return path;
    }
}
