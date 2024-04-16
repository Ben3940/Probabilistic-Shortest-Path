
public class Main{
    public static void main(String[] args){
        Graph graph = new Graph();


        Traverse trav = new Traverse(graph);

        trav.bfs_traverse();
        trav.reset_state();
        trav.dfs_traverse();
        // trav.print_parent_map();
        // System.out.println("Path: " + trav.get_path());

    }
}