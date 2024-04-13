import java.util.ArrayList;

public class Graph {
    // int[][] adjacency_matrix = {
    //     {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
    //     {1, 0, 0, 0, 1, 0, 1, 0, 0, 0},
    //     {1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
    //     {1, 0, 0, 0, 0, 1, 0, 0, 1, 0},
    //     {0, 1, 1, 0, 0, 0, 0, 1, 0, 0},
    //     {0, 0, 1, 1, 0, 0, 0, 1, 1, 0},
    //     {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
    //     {0, 0, 0, 0, 1, 1, 0, 0, 1, 1},
    //     {0, 0, 0, 1, 0, 1, 0, 1, 0, 1},
    //     {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
    // };
    double[][] adjacency_matrix = {
        {0, 1, 1, 1, 0, 0, 0, 0, 0, 0},
        {1, 0, 0, 0, 0.7, 0, 1, 0, 0, 0},
        {1, 0, 0, 0, 1, 1, 0, 0, 0, 0},
        {1, 0, 0, 0, 0, 0.7, 0, 0, 0.5, 0},
        {0, 0.7, 1, 0, 0, 0, 0, 0.3, 0, 0},
        {0, 0, 1, 0.7, 0, 0, 0, 0.1, 1, 0},
        {0, 1, 0, 0, 0, 0, 0, 0, 0, 1},
        {0, 0, 0, 0, 0.3, 0.1, 0, 0, 0.2, 0.8},
        {0, 0, 0, 0.5, 0, 1, 0, 0.2, 0, 0.1},
        {0, 0, 0, 0, 0, 0, 1, 0.8, 0.1, 0},
    };
    boolean directed;
    int start, end;

    public Graph(boolean directed){
        this.directed = directed;
        this.start = 0;
        this.end = 9;
    }

    public int get_start(){
        return this.start;
    }

    public int get_end(){
        return this.end;
    }

    public double[][] get_adjacency_matrix(){
        return this.adjacency_matrix;
    }

    public ArrayList<Integer> get_adjacent_vertices(int vertex_idx){
        ArrayList<Integer> adjacent = new ArrayList<>();

        for(int i =0; i <= 9; i++){
            if(this.adjacency_matrix[vertex_idx][i] > 0){
                adjacent.add(i);
            }
        }
        return adjacent;
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
