package TP4.Solutions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import TP4.TDA.Grafo.Grafo;

public class CaminoMayorLongitud{

    private Grafo<?> grafo;
    private HashMap<Integer, String> trazo;
    private List<Integer> mejorCamino;

    public CaminoMayorLongitud(Grafo<?> grafo){
        this.grafo = grafo;
        this.trazo = new HashMap<>();
        this.mejorCamino = new LinkedList<>();
        Iterator<Integer> itVertice = this.grafo.obtenerVertices();
        while(itVertice.hasNext()){
            Integer vertice = itVertice.next();
            this.trazo.put(vertice, "blanco");
        }
    }

    public List<Integer> buscarCamino(Integer origen, Integer destino){
        List<Integer> tmpList = new LinkedList<>();
        _buscarCamino(origen, destino, tmpList);
        return List.copyOf(this.mejorCamino);
    }

    private void _buscarCamino(Integer actual, Integer destino, List<Integer> caminoActual){
        this.trazo.put(actual, "amarillo");
        caminoActual.add(actual);
        if(actual==destino){
            if(caminoActual.size()>=this.mejorCamino.size()){
                this.mejorCamino.clear();
                this.mejorCamino.addAll(caminoActual);
            }
        }
        Iterator<Integer> ady = this.grafo.obtenerAdyacentes(actual);
        while (ady.hasNext()) {
            Integer adyacente = ady.next();
            if(this.trazo.get(adyacente).equals("blanco")){
                this.trazo.put(adyacente, "amarillo");
                caminoActual.add(adyacente);
                _buscarCamino(adyacente, destino, caminoActual);
                this.trazo.put(adyacente, "blanco");
                caminoActual.removeLast();
            }
        }
    }

    public List<Integer> getMejorCamino(){
        return List.copyOf(this.mejorCamino);
    }

    public void cleanMejorCamino(){
        this.mejorCamino.clear();
    }
}
