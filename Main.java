
public class Main{
    public static void main(String[] args){
        Graph graph = new Graph(false);

        graph.print_adjacency_matrix();

        BFS bfs = new BFS(graph);

        bfs.traverse();
        bfs.print_parent_map();
        System.out.println("Path: " + bfs.get_path());

    }
}