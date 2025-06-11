package TP5.Solutions;

// Dado un grafo no dirigido G(V,A) se desea colorear cada uno de sus vertices utilizando la menor cantidad posible de colores totales, sabiendo que dos vertices adyacentes no podran utilizar el mismo color.

import Grafo.Grafo;
import Grafo.GrafoD;
import java.util.*;

public class PF2024_T {
    private Grafo<?> grafo;
    private int[] colores;
    private int cantidadColores;
    private int[] mejorSolucion;
    private int mejorCantidadColores;
    private int cantidadNodos;
    private List<Integer> nodos;

    public PF2024_T(Grafo<?> grafo) {
        this.grafo = grafo;
        this.cantidadNodos = grafo.cantidadVertices();
        this.colores = new int[cantidadNodos];
        this.mejorSolucion = new int[cantidadNodos];
        this.mejorCantidadColores = Integer.MAX_VALUE;
        this.nodos = new ArrayList<>();
        
        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
            nodos.add(it.next());
        }
    }

    public int[] colorear() {
        for (cantidadColores = 1; cantidadColores <= cantidadNodos; cantidadColores++) {
            if (backtracking(0)) {
                return Arrays.copyOf(mejorSolucion, mejorSolucion.length);
            }
        }
        return new int[0];
    }

    private boolean backtracking(int nodoActual) {
        if (nodoActual == cantidadNodos) {
            mejorCantidadColores = cantidadColores;
            System.arraycopy(colores, 0, mejorSolucion, 0, cantidadNodos);
            return true;
        }
        int nodo = nodos.get(nodoActual);
        for (int color = 1; color <= cantidadColores; color++) {
            if (esColorValido(nodo, color)) {
                colores[nodoActual] = color;
                if (backtracking(nodoActual + 1)) {
                    return true;
                }
                colores[nodoActual] = 0;
            }
        }
        return false;
    }

    private boolean esColorValido(int nodo, int color) {
        Iterator<Integer> vecinos = grafo.obtenerAdyacentes(nodo);
        while (vecinos != null && vecinos.hasNext()) {
            int vecino = vecinos.next();
            int indiceVecino = nodos.indexOf(vecino);
            if (indiceVecino != -1 && indiceVecino < colores.length && colores[indiceVecino] == color) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Grafo<String> grafo = new GrafoD<>();
            
        for (int i = 0; i < 4; i++) {
            grafo.agregarVertice(i);
        }

        grafo.agregarVertice(0);
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
            
        grafo.agregarArco(0, 1, "");
        grafo.agregarArco(0, 2, "");
        grafo.agregarArco(0, 3, "");

        
        PF2024_T coloracion = new PF2024_T(grafo);
        int[] colores = coloracion.colorear();
            
        System.out.println("Número cromático: " + coloracion.mejorCantidadColores);
        System.out.println("Asignación de colores por vértice:");
        for (int i = 0; i < colores.length; i++) {
            System.out.println("Vértice " + i + ": Color " + colores[i]);
        }
    }
}
