package TP1.Iterator.SimpleNodeterator;

import TP1.TDA.Node.SimpleNode.SimpleNode;
import java.util.Iterator;

public class SimpleNodeIterator<T> implements Iterator<T> {
    private SimpleNode<T> current;

    public SimpleNodeIterator(SimpleNode<T> current) {
        this.current = current;
    }

    public SimpleNode<T> getCurrent() {
        return this.current;
    }

    @Override
    public boolean hasNext() {
        return this.current != null;   
    }

    @Override
    public T next() {
        T info = this.current.getInfo();
        this.current = this.current.getNext();
        return info;
    }

}
