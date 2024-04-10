
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

    public void add_edge(int to_vertex, int from_vertex){
        this.adjacency_matrix[to_vertex][from_vertex] = 1;
        if (!this.directed){
            this.adjacency_matrix[from_vertex][to_vertex] = 1;
        }
    }

    public void print_adjacency_matrix(){
        System.out.print("    ");
        for (int i = 0; i < this.adjacency_matrix.length; i++){
            System.out.print(i + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < this.adjacency_matrix.length; i++){
            System.out.print(i + "   ");
            for (int j = 0; j < this.adjacency_matrix[0].length; j++){
                System.out.print(this.adjacency_matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }
}
