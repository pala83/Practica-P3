package TP1.TDA.Node.DoubleNode;

public class DoubleNode<T> {
    private T info;
    private DoubleNode<T> next;
    private DoubleNode<T> prev;

    public DoubleNode(T info, DoubleNode<T> prev, DoubleNode<T> next) {
        this.setInfo(info);
        this.setNext(next);
        this.setPrev(prev);
    }

    public T getInfo() {
        return info;
    }
    public DoubleNode<T> getNext() {
        return next;
    }
    public DoubleNode<T> getPrev() {
        return prev;
    }
    public void setInfo(T info) {
        this.info = info;
    }
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }
    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }
}