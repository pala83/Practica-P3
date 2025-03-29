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
        if(current.getValue().compareTo(value) > 0){
            if(current.getLeft() == null){
                DoubleNode<T> temp = new DoubleNode<T>(value, null, null);
                current.setLeft(temp);
            } 
            else{
                add(current.getLeft(), value);
            }
        }
        else if(current.getValue().compareTo(value) < 0){
            if(current.getRight() == null){
                DoubleNode<T> temp = new DoubleNode<T>(value, null, null);
                current.setRight(temp);
            }
            else{
                add(current.getRight(), value);
            }
        }
    }

    public T getRoot(){
        return this.root.getValue();
    }

    public void printPreOrder(){
        this.preOrder(root);
        System.out.println();
    }

    private void preOrder(DoubleNode<T> root){
        if(root!=null){
            System.out.println(root.getValue() + " ");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

}
