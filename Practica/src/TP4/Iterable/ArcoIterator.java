package TP4.Iterable;

import java.util.Iterator;
import TP4.TDA.Arco.Arco;

public class ArcoIterator<T> implements Iterator<Integer> {
    private Iterator<Arco<T>> itArco;

    public ArcoIterator(Iterator<Arco<T>> itArco) {
        this.itArco = itArco;
    }

    @Override
    public boolean hasNext() {
        return itArco.hasNext();
    }

    @Override
    public Integer next() {
        return this.itArco.next().getVerticeDestino();
    }

}
