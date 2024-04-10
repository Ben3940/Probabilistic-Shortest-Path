
public class Main{
    public static void main(String[] args){
        Graph<Integer> graph = new Graph<Integer>(5);

        graph.add_edge(0, 0);
        graph.add_edge(2, 3);
        graph.add_edge(4, 1);
        graph.add_edge(3, 4);

        graph.print_adjacency_matrix();

    }
}