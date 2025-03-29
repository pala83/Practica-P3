package TP2.TDA.Tree;

import TP1.TDA.Node.DoubleNode.DoubleNode;

public class Tree<T extends Comparable<T>> {
    private DoubleNode<T> root;

    public Tree(){
        this.root = null;
    }

    public void add(T value){
        if(this.root == null)
            this.root = new DoubleNode<T>(value, null, null);
        else
            this.add(this.root, value);
    }

    private void add(DoubleNode<T> current, T value){
        if(current.getInfo().compareTo(value) > 0){
            if(current.getPrev() == null){
                DoubleNode<T> temp = new DoubleNode<T>(value, null, null);
                current.setPrev(temp);
            } 
            else{
                add(current.getPrev(), value);
            }
        }
        else if(current.getInfo().compareTo(value) < 0){
            if(current.getNext() == null){
                DoubleNode<T> temp = new DoubleNode<T>(value, null, null);
                current.setNext(temp);
            }
            else{
                add(current.getNext(), value);
            }
        }
    }

    public T getRoot(){
        return this.root.getInfo();
    }

    public void printPreOrder(){
        this.preOrder(root);
    }

    private void preOrder(DoubleNode<T> root){
        if(root!=null){
            System.out.println(root.getInfo());
            preOrder(root.getPrev());
            preOrder(root.getNext());
        }
    }

}
