package TP1.Iterator.DoubleNodeIterator;

import java.util.Iterator;
import TP1.TDA.Node.DoubleNode.DoubleNode;

public class DoubleNodeIterator<T> implements Iterator<T> {
    private DoubleNode<T> current;

    public DoubleNodeIterator(DoubleNode<T> current) {
        this.current = current;
    }

    public DoubleNode<T> getCurrent() {
        return current;
    }

    @Override
    public boolean hasNext() {
        return this.current.getNext() != null;
    }

    @Override
    public T next() {
        T info = this.current.getInfo();
        this.current = this.current.getNext();
        return info;
    }

}
