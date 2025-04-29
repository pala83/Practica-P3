package TP4.TDA.Grafo;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GrafoD<Double> grafo = new GrafoD<>();
        cargarVertices(grafo, 5);
        System.out.println("Grafo Vertices: " + grafo.obtenerVertices().toString());
    }

    public static void cargarVertices(GrafoD<Double> grafo, int vertices) {
        List<Integer> tmp = new LinkedList<>();
        for (int i = 1; i <= vertices; i++) {
            tmp.add(i);
            grafo.agregarVertice(i);
        }
        List<Integer> tmpVertices = new LinkedList<>(tmp);
        while(!tmpVertices.isEmpty()){
            Integer vOrigen = tmpVertices.remove((int)(Math.random()*tmpVertices.size()));
            int cantAdy = (int)(Math.random()*tmp.size());
            for(int j = 0; j < cantAdy; j++){
                Integer vDestino = tmp.get(j);
                if(!vOrigen.equals(vDestino))
                    grafo.agregarArco(vOrigen, vDestino, Math.random());
            }
        }
    }
}
