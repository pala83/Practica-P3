package TP1.TDA.List.DoubleLinkedList;

import java.util.Iterator;

import TP1.Iterator.DoubleNodeIterator.DoubleNodeIterator;
import TP1.TDA.Node.DoubleNode.DoubleNode;

public class DoubleLinkedList<T> implements Iterable<T> {

    private DoubleNode<T> first;
    private DoubleNode<T> last;
    private int size;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void insertFront(T info) {
        DoubleNode<T> tmp = new DoubleNode<T>(info, null, this.first);
        if (this.isEmpty()) {
            this.last = tmp;
        } else {
            this.first.setPrev(tmp);
        }
        this.first = tmp;
        this.size++;
    }

    public void extractFront() {
        if (this.isEmpty()) {
            return;
        }
        this.first = this.first.getNext();
        if (this.first == null) {
            this.last = null;
        } else {
            this.first.setPrev(null);
        }
        this.size--;
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        DoubleNode<T> current = this.first;
        int i = 0;
        while (current != null && i < index) {
            current = current.getNext();
            i++;
        }
        if (current == null) {
            return null;
        }
        return current.getInfo();
    }

    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleNodeIterator<T>(this.first);
    }
}
