package TP1.Operator;

import TP1.TDA.List.LinkedList.LinkedList;

public class Intersection<T extends Comparable<T>> extends Operator<T> {

    public Intersection(LinkedList<T> l1, LinkedList<T> l2) {
        super(l1, l2);
    }

    @Override
    public boolean condition(boolean exist) {
        return exist;
    }
}
