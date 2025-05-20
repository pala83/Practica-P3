package TP4.Solutions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import TP4.TDA.Grafo.Grafo;

public class ExisteCiclo extends Recorrido {

    private List<Integer> ciclo;
    public ExisteCiclo(Grafo<?> grafo){
        super(grafo, "blanco");
        this.ciclo = new LinkedList<>();
    }

    @Override
    public void forest() {
        for(Map.Entry<Integer, Trazo> entry : this.trazo.entrySet()) {
            if(entry.getValue().getEstado().equals("blanco")) {
                this.buscaCiclo(entry.getKey());
            }
        }
    }

    @Override
    public void simple(Integer vertice) {
        if(this.trazo.get(vertice).getEstado().equals("blanco"))
            this.buscaCiclo(vertice);
    }

    private void buscaCiclo(Integer vertice){
        Trazo trazoVertice = this.trazo.get(vertice);
        trazoVertice.setEstado("amarillo");
        this.ciclo.add(vertice);
        Iterator<Integer> ady = this.grafo.obtenerAdyacentes(vertice);
        while (ady.hasNext()) {
            Integer adyacente = ady.next();
            Trazo trazoAdyacente = this.trazo.get(adyacente);
            if(trazoAdyacente.getEstado().equals("blanco"))
                buscaCiclo(adyacente);
            else
                return;
            this.ciclo.removeLast();
        }
        trazoVertice.setEstado("negro");
    }

    public List<Integer> getCiclo() {
        return List.copyOf(this.ciclo);
    }

    public void clearCiclo(){
        this.ciclo.clear();
    }
}
