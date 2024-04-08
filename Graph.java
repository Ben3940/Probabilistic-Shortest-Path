public class Graph {
    int[][] adjacency_matrix;
    public Graph(int n){
        adjacency_matrix = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                adjacency_matrix[i][j] = 0;
            }
        }
    }

    public void add_edge(int from_vertex, int to_vertex){
        adjacency_matrix[from_vertex][to_vertex] = 1;
    }
}
