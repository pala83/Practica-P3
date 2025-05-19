package TP2.Operations.Aggregation;

import TP2.TDA.Node.DoubleNode.DoubleNode;

public class Summ implements Aggregation<Integer> {

    @Override
    public Integer aggregate(DoubleNode<Integer> node) {
        if (node == null) {
            return 0;
        }
        return node.getValue() + aggregate(node.getLeft()) + aggregate(node.getRight());
    }
}
