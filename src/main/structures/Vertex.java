package main.structures;

public class Vertex<T> {

    T data;


    public Vertex(){}

    public Vertex(T v) {
        this.data = v;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
