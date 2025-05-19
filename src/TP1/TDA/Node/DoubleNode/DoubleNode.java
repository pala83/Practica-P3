package TP1.TDA.Node.DoubleNode;

public class DoubleNode<T> {
    private T value;
    private DoubleNode<T> right;
    private DoubleNode<T> left;

    public DoubleNode(T value, DoubleNode<T> left, DoubleNode<T> right) {
        this.setValue(value);
        this.setRight(right);
        this.setLeft(left);
    }

    public T getValue() {
        return this.value;
    }
    public DoubleNode<T> getRight() {
        return this.right;
    }
    public DoubleNode<T> getLeft() {
        return this.left;
    }
    public void setValue(T value) {
        this.value = value;
    }
    public void setRight(DoubleNode<T> right) {
        this.right = right;
    }
    public void setLeft(DoubleNode<T> left) {
        this.left = left;
    }
}