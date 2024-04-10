
public class Graph<T> {
    int[][] adjacency_matrix = {
        {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
        {1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 1, 0, 0, 1, 0},
        {0, 1, 1, 0, 0, 0, 0, 1, 0, 0},
        {0, 0, 1, 1, 0, 0, 0, 1, 1, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 1, 1, 0, 0, 1, 1},
        {0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
        {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
    };
    boolean directed;

    public Graph(boolean directed){
        this.directed = directed;
    }

    public void add_edge(int to_vertex, int from_vertex){
        this.adjacency_matrix[to_vertex][from_vertex] = 1;
        if (!this.directed){
            this.adjacency_matrix[from_vertex][to_vertex] = 1;
        }
    }

    // Prints adjacency matrix of Graph class
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
