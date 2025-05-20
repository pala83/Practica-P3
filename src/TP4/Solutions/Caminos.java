package TP4.Solutions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import TP4.TDA.Grafo.Grafo;

public class Caminos {
    private Grafo<?> grafo;
    private HashMap<Integer, String> colores;
    private List<List<Integer>> caminos;

    public Caminos(Grafo<?> grafo) {
        this.grafo = grafo;
        this.caminos = new LinkedList<>();
        this.colores = new HashMap<>();
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while(it.hasNext()) {
            Integer vertice = it.next();
            colores.put(vertice, "blanco");
        }
    }

    public List<List<Integer>> obtenerCaminos(Integer origen, Integer destino) {
        List<Integer> camino = new LinkedList<>();
        if(this.colores.get(origen).equals("blanco")) {
            encontrarCaminosRec(origen, destino, camino);
        }
        return caminos;
    }

    private void encontrarCaminosRec(Integer actual, Integer destino, List<Integer> camino) {
        this.colores.put(actual, "amarillo");
        camino.add(actual);
        if (actual.equals(destino))
            caminos.add(new LinkedList<>(camino));
        else {
            Iterator<Integer> itAdy = this.grafo.obtenerAdyacentes(actual);
            while (itAdy.hasNext()) { // por cada posible decision
                Integer adyacente = itAdy.next();
                if (this.colores.get(adyacente).equals("blanco")) { // Evitar ciclos
                    encontrarCaminosRec(adyacente, destino, camino);
                }
            }
        }
        camino.removeLast(); // Backtrack
    }
}
