package TP5.Solutions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import Grafo.Grafo;

// Problema a resolver:
// Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se abren solamente en un sentido. Una de las salas se denomina entrada y la otra salida. Construir un algoritmo que permita ir desde la entrada a la salida atravesando la máxima cantidad de salas. Idea: podría representar el problema mediante un grafo dirigido, donde cada nodo es una habitación, y cada puerta es un arco dirigido hacia otra habitación.
public class Ej1 {
    private Grafo<?> grafo;
    private List<Integer> mejorCamino;

    public Ej1(Grafo<?> grafo) {
        this.grafo = grafo;
        this.mejorCamino = new LinkedList<>();
    }

    public List<Integer> solucion(int origen, int destino){
        HashMap<Integer, Boolean> visitados = new HashMap<>();
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while(it.hasNext()) {
            Integer vertice = it.next();
            visitados.put(vertice, false);
        }
        this.backtracking(origen, destino, new LinkedList<>(), visitados);
        return List.copyOf(this.mejorCamino);
    }

    private void backtracking(int origen, int destino, List<Integer> caminoActual, HashMap<Integer, Boolean> visitado){
        visitado.put(origen, true);
        caminoActual.add(origen);
        if(origen == destino) {
            if(caminoActual.size() > this.mejorCamino.size()) {
                this.mejorCamino.clear();
                this.mejorCamino.addAll(caminoActual);
            }
        } 
        else {
            Iterator<Integer> it = this.grafo.obtenerAdyacentes(origen);
            while(it.hasNext()) {
                Integer adyacente = it.next();
                if(!visitado.get(adyacente)) {
                    backtracking(adyacente, destino, caminoActual, visitado);
                }
            }
        }
        visitado.put(origen, false);
        caminoActual.remove(caminoActual.size()-1);
    }
}