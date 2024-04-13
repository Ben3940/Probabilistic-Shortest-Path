
public class Main{
    public static void main(String[] args){
        Graph graph = new Graph(false);

        graph.print_adjacency_matrix();

        Traverse trav = new Traverse(graph);

        trav.bfs_traverse();
        trav.print_parent_map();
        System.out.println("Path: " + trav.get_path());

    }
}