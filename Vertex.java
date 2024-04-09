import java.util.ArrayList;

public class Vertex<T> {
    T value;
    ArrayList<Vertex<T>> edges;

    public Vertex(T val){
        this.value = val;
        this.edges = new ArrayList<Vertex<T>>();
    }

    public T get_value(){
        return this.value;
    }

    public void add_edge(Vertex<T> vertex){
        this.edges.add(vertex);
    }
}
