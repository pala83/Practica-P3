package TP1.TDA.List.LinkedList;

import java.util.Iterator;
import TP1.Iterator.SimpleNodeterator.SimpleNodeIterator;
import TP1.TDA.Node.SimpleNode.SimpleNode;

public class LinkedList<T> implements Iterable<T> {
    private SimpleNode<T> first;
    private int size;

    public LinkedList() {
        this.first = null;
        this.size = 0;
    }

    public void insertFront(T info) {
        SimpleNode<T> tmp = new SimpleNode<T>(info, this.first);
        this.first = tmp;
        this.size++;
    }

    public void insertOrder(T info) {
        if (this.isEmpty() || ((Comparable<T>) info).compareTo(this.first.getInfo())<=0){
            this.insertFront(info);
        }
        else{
            SimpleNode<T> tmp = new SimpleNode<T>(info, null);
            SimpleNode<T> pointer = this.first;
            while (pointer.getNext() != null && ((Comparable<T>) info).compareTo(this.first.getNext().getInfo()) > 0) {
                pointer = pointer.getNext();
            }
            tmp.setNext(pointer.getNext());
            pointer.setNext(tmp);
            this.size++;
        }
    }

    public T extractFront(){
        if (this.isEmpty()) {
            return null;
        }
        T info = this.first.getInfo();
        this.first = this.first.getNext();
        this.size--;
        return info;
    }

    public void remove(T info){
        if(this.isEmpty()){
            return;
        }
        if(this.first.getInfo().equals(info)){
            this.first = this.first.getNext();
            this.size--;
        }
        else{
            SimpleNode<T> current = this.first;
            while (current.getNext() != null && !current.getNext().getInfo().equals(info)) {
                current = current.getNext();
            }
            if(current.getNext() != null){
                current.setNext(current.getNext().getNext());
                this.size--;
            }
        }
    }

    public boolean isEmpty() {
        return this.first == null;
    }

    public T get(int index){
        if(index < 0 || index >= this.size){
            return null;
        }
        SimpleNode<T> current = this.first;
        int i = 0;
        while (current != null && i < index) {
            current = current.getNext();
            i++;
        }
        return current==null ? null : current.getInfo();
    }

    public int size() {
        return this.size;
    }

    public int indexOf(T info){
        if(this.isEmpty()){
            return -1;
        }
        SimpleNode<T> current = this.first;
        int i = 0;
        while (current != null && !current.getInfo().equals(info)) {
            current = current.getNext();
            i++;
        }
        return current==null ? -1 : i;
    }

    @Override
    public String toString() {
        if(this.isEmpty()){
            return "Lista vacia";
        }
        StringBuilder sb = new StringBuilder();
        SimpleNode<T> current = this.first;
        while (current != null) {
            sb.append(current.getInfo());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleNodeIterator<T>(this.first);
    }
}
