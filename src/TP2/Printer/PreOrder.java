package TP2.Printer;

import TP1.TDA.Node.DoubleNode.DoubleNode;

public class PreOrder<T> implements Printer<T> {

    @Override
    public void print(DoubleNode<T> root) {
        preOrder(root);
        System.out.println();        
    }

    public void preOrder(DoubleNode<T> node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
}
