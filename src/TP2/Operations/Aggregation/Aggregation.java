package TP2.Operations.Aggregation;

import TP1.TDA.Node.DoubleNode.DoubleNode;

public interface Aggregation<T> {
    T aggregate(DoubleNode<T> node);
}
