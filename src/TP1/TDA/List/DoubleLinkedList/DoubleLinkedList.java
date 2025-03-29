package TP1.TDA.List.DoubleLinkedList;

import java.util.Iterator;

import TP1.Iterator.DoubleNodeIterator.DoubleNodeIterator;
import TP1.TDA.Node.DoubleNode.DoubleNode;

public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T> {

    private DoubleNode<T> first;
    private DoubleNode<T> last;
    private int size;

    public DoubleLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void insertFront(T value) {
        DoubleNode<T> tmp = new DoubleNode<T>(value, null, this.first);
        if (this.isEmpty()) {
            this.last = tmp;
        } else {
            this.first.setLeft(tmp);
        }
        this.first = tmp;
        this.size++;
    }

    public void insertBack(T value) {
        DoubleNode<T> tmp = new DoubleNode<T>(value, this.last, null);
        if (this.isEmpty()) {
            this.first = tmp;
        } else {
            this.last.setRight(tmp);
        }
        this.last = tmp;
        this.size++;
    }

    public void insertORder(T value){
        if(this.isEmpty() || value.compareTo(this.first.getValue()) <= 0){
            this.insertFront(value);
        }
        else if(value.compareTo(this.last.getValue()) >= 0){
            this.insertBack(value);
        }
        else{
            DoubleNode<T> tmp = new DoubleNode<T>(value, null, null);
            DoubleNode<T> pointer = this.first;
            while (pointer.getRight() != null && value.compareTo(pointer.getRight().getValue()) > 0) {
                pointer = pointer.getRight();
            }
            tmp.setRight(pointer.getRight());
            tmp.setLeft(pointer);
            pointer.setRight(tmp);
            pointer.getRight().setLeft(tmp);
            this.size++;
        }
    }

    public T extractFront() {
        if (this.isEmpty()) {
            return null;
        }
        T value = this.first.getValue();
        this.first = this.first.getRight();
        if (this.first == null) {
            this.last = null;
        } else {
            this.first.setLeft(null);
        }
        this.size--;
        return value;
    }

    public T extractBack() {
        if (this.isEmpty()) {
            return null;
        }
        T value = this.last.getValue();
        this.last = this.last.getLeft();
        if (this.last == null) {
            this.first = null;
        } else {
            this.last.setRight(null);
        }
        this.size--;
        return value;

    }

    public void remove(int index){
        if(index < 0 || index >= this.size){
            return;
        }
        DoubleNode<T> current = this.first;
        int i = 0;
        while (current != null && i < index) {
            current = current.getRight();
            i++;
        }
        if(current == null){
            return;
        }
        if(current.getLeft() == null){
            this.extractFront();
        }
        else if(current.getRight() == null){
            this.extractBack();
        }
        else{
            current.getLeft().setRight(current.getRight());
            current.getRight().setLeft(current.getLeft());
            this.size--;
        }
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
            current = current.getRight();
            i++;
        }
        return current==null ? null : current.getValue();
    }

    public int size() {
        return this.size;
    }

    public int indexOf(T value) {
        if(this.isEmpty()){
            return -1;
        }
        DoubleNode<T> current = this.first;
        int i = 0;
        while (current != null && !current.getValue().equals(value)) {
            current = current.getRight();
            i++;
        }
        return current == null ? -1 : i;
    }

    @Override
    public String toString() {
        if(this.isEmpty()){
            return "Lista vacia";
        }
        StringBuilder sb = new StringBuilder();
        DoubleNode<T> current = this.first;
        while (current != null) {
            sb.append(current.getValue());
            sb.append(" ");
            current = current.getRight();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleNodeIterator<T>(this.first);
    }
}
