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
