package TP4.Solutions;

import java.util.Iterator;
import java.util.Map;

import TP4.TDA.Grafo.Grafo;

public class DFS extends Recorrido {

    public DFS(Grafo<?> grafo){
        super(grafo, "blanco");
    }

    @Override
    public void forest(){
        int tiempo = 0;
        for(Map.Entry<Integer, Trazo> entry : this.trazo.entrySet()) {
            if(entry.getValue().getEstado().equals("blanco")) {
                tiempo = this.DFSVisit(entry.getKey(), tiempo);
            }
        }
    }

    @Override
    public void simple(Integer vertice){
        int tiempo = 0;
        if(this.trazo.get(vertice).getEstado().equals("blanco"))
            tiempo = DFSVisit(vertice, tiempo);
    }

    private int DFSVisit(Integer vertice, int tiempo){
        Trazo trazoVertice = this.trazo.get(vertice);
        trazoVertice.setEstado("amarillo");
        tiempo++;
        trazoVertice.setTiempoI(tiempo);
        Iterator<Integer> ady = this.grafo.obtenerAdyacentes(vertice);
        while(ady.hasNext()) {
            Integer adyacente = ady.next();
            Trazo trazoAdyacente = this.trazo.get(adyacente);
            if(trazoAdyacente.getEstado().equals("blanco")) {
                tiempo = this.DFSVisit(adyacente, tiempo);
            }
        }
        trazoVertice.setEstado("negro");
        tiempo++;
        trazoVertice.setTiempoF(tiempo);
        return tiempo;
    }
}
