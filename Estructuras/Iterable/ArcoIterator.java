package Trabajos_Pracicos.Estructuras.Iterable;

import java.util.Iterator;

import Trabajos_Pracicos.Estructuras.Arco;

public class ArcoIterator implements Iterator<Integer> {
    private Iterator<Arco> itArco;

    public ArcoIterator(Iterator<Arco> itArco) {
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
