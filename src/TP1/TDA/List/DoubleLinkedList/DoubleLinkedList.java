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

    public void insertBack(T info) {
        DoubleNode<T> tmp = new DoubleNode<T>(info, this.last, null);
        if (this.isEmpty()) {
            this.first = tmp;
        } else {
            this.last.setNext(tmp);
        }
        this.last = tmp;
        this.size++;
    }

    public void insertORder(T info){
        if(this.isEmpty() || info.compareTo(this.first.getInfo()) <= 0){
            this.insertFront(info);
        }
        else if(info.compareTo(this.last.getInfo()) >= 0){
            this.insertBack(info);
        }
        else{
            DoubleNode<T> tmp = new DoubleNode<T>(info, null, null);
            DoubleNode<T> pointer = this.first;
            while (pointer.getNext() != null && info.compareTo(pointer.getNext().getInfo()) > 0) {
                pointer = pointer.getNext();
            }
            tmp.setNext(pointer.getNext());
            tmp.setPrev(pointer);
            pointer.setNext(tmp);
            pointer.getNext().setPrev(tmp);
            this.size++;
        }
    }

    public T extractFront() {
        if (this.isEmpty()) {
            return null;
        }
        T info = this.first.getInfo();
        this.first = this.first.getNext();
        if (this.first == null) {
            this.last = null;
        } else {
            this.first.setPrev(null);
        }
        this.size--;
        return info;
    }

    public T extractBack() {
        if (this.isEmpty()) {
            return null;
        }
        T info = this.last.getInfo();
        this.last = this.last.getPrev();
        if (this.last == null) {
            this.first = null;
        } else {
            this.last.setNext(null);
        }
        this.size--;
        return info;

    }

    public void remove(int index){
        if(index < 0 || index >= this.size){
            return;
        }
        DoubleNode<T> current = this.first;
        int i = 0;
        while (current != null && i < index) {
            current = current.getNext();
            i++;
        }
        if(current == null){
            return;
        }
        if(current.getPrev() == null){
            this.extractFront();
        }
        else if(current.getNext() == null){
            this.extractBack();
        }
        else{
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
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
            current = current.getNext();
            i++;
        }
        return current==null ? null : current.getInfo();
    }

    public int size() {
        return this.size;
    }

    public int indexOf(T info) {
        if(this.isEmpty()){
            return -1;
        }
        DoubleNode<T> current = this.first;
        int i = 0;
        while (current != null && !current.getInfo().equals(info)) {
            current = current.getNext();
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
            sb.append(current.getInfo());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleNodeIterator<T>(this.first);
    }
}
