package TP2.Printer;

import TP2.TDA.Node.DoubleNode.DoubleNode;

public class InOrder<T> implements Printer<T> {

    @Override
    public void print(DoubleNode<T> root) {
        inOrder(root);
        System.out.println();        
    }

    private void inOrder(DoubleNode<T> node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            inOrder(node.getRight());
        }
    }
}

