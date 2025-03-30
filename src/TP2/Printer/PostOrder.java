package TP2.Printer;

import TP1.TDA.Node.DoubleNode.DoubleNode;

public class PostOrder<T> implements Printer<T> {

    @Override
    public void print(DoubleNode<T> root) {
        postOrder(root);
        System.out.println();        
    }

    private void postOrder(DoubleNode<T> node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }
}

