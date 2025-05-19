package TP1.TDA.Node.SimpleNode;

public class SimpleNode<T> {
    private T info;
    private SimpleNode<T> next;

    public SimpleNode(T info, SimpleNode<T> next) {
        this.setInfo(info);
        this.setNext(next);
    }

    public T getInfo() {
        return info;
    }
    public SimpleNode<T> getNext() {
        return next;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public void setNext(SimpleNode<T> next) {
        this.next = next;
    }
}