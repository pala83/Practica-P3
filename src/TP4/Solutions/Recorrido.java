package TP4.Solutions;

import TP4.TDA.Grafo.Grafo;
import java.util.HashMap;
import java.util.Iterator;


public abstract class Recorrido {
    protected Grafo<?> grafo;
    protected HashMap<Integer, Trazo> trazo;

    public Recorrido(Grafo<?> grafo, String estadoInicial){
        this.grafo = grafo;
        this.trazo = new HashMap<>();
        Iterator<Integer> it = this.grafo.obtenerVertices();
		while(it.hasNext()) {
			Integer vertice = it.next();
			this.trazo.put(vertice, new Trazo(estadoInicial));
		}
    }

    public HashMap<Integer, Trazo> getTrazo(){
        HashMap<Integer, Trazo> copia = new HashMap<>(this.trazo);
        return copia;
    }

    public void cleanTrazo(){
        this.trazo.clear();
    }

    public abstract void forest();
    public abstract void simple(Integer vertice);
}
