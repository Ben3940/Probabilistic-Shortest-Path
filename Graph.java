
public class Graph<T> {
    int[][] adjacency_matrix;
    boolean directed;

    public Graph(int n){
        this(n, false);
    }

    public Graph(int n, boolean directed){
        adjacency_matrix = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                adjacency_matrix[i][j] = 0;
            }
        }
    }

    public void add_edge(Vertex<T> to_vertex, Vertex<T> from_vertex){
        
    }

    public void add_edge_undirected(int from_vertex, int to_vertex){
        adjacency_matrix[from_vertex][to_vertex] = 1;
    }
}
