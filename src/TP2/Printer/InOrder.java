package TP2.Printer;

import TP1.TDA.Node.DoubleNode.DoubleNode;

public class InOrder<T> implements Printer<T> {

    @Override
    public void print(DoubleNode<T> root) {
        inOrder(root);
        System.out.println();        
    }

    public void inOrder(DoubleNode<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrder(node.getRight());
        }
    }
}

