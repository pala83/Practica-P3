package Trabajos_Pracicos.F_2024_08_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Trabajos_Pracicos.Estructuras.GrafoND;

// Dado un grafo conectado, no dirigido y no rotulado G(V,A) que tiene mas de 2 vertices.
// Escriba un algoritmo en JAVA que devuelva en una lista, si existen, todos los puntos de articulacion del grafo G(V,A).
// Un punto de articulacion es un vertice que si se lo borra el grafo deja de ser un grafo conectado.
// Por ejemplo en el grafo de la derecha los vertices G y H son puntos de articulacion, ya que cualquiera de ellos que se borre hace que el grafo deje de ser conectado o conexo.

public class Ej1 {
    private GrafoND grafo;
    private int time = 0;

    public Ej1(GrafoND grafo) {
        this.grafo = grafo;
    }

    public List<Integer> encontrarPuntosArticulacion() {
        boolean[] visitado = new boolean[grafo.cantidadVertices()];
        int[] disc = new int[grafo.cantidadVertices()];
        int[] low = new int[grafo.cantidadVertices()];
        int[] parent = new int[grafo.cantidadVertices()];
        boolean[] esPuntoArticulacion = new boolean[grafo.cantidadVertices()];

        Arrays.fill(parent, -1);
        Arrays.fill(visitado, false);
        Arrays.fill(esPuntoArticulacion, false);

        List<Integer> retorno = new ArrayList<>();

        // Hago el forest del DFS para poder recorrer todos los vertices del grafo
        for (int i = 0; i < grafo.cantidadVertices(); i++) {
            if (!visitado[i]) {
                DFS(i, visitado, disc, low, parent, esPuntoArticulacion);
            }
        }

        // Recolecto los puntos de articulacion encontrados
        for (int i = 0; i < esPuntoArticulacion.length; i++) {
            if (esPuntoArticulacion[i]) {
                retorno.add(i);
            }
        }

        return retorno;
    }

    public void DFS(int v, boolean[] visitado, int[] disc, int[] low, int[] parent, boolean[] esPuntoArticulacion) {
        int hijo = 0;
        visitado[v] = true;
        disc[v] = low[v] = ++time;
        
        for (int adj : grafo.obtenerAdyacentes(v)) {
            // caso 1: adj es padre de v (no hago nada)
            if (parent[v] == adj) continue;

            // caso 2: adj ya fue visitado -> Arco back
            if(visitado[adj]) {
                low[v] = Math.min(low[v], disc[adj]);
            }

            // casi 3: v no ha sido visitado -> Arco tree
            else {
                hijo++;
                parent[adj] = v;
                DFS(adj, visitado, disc, low, parent, esPuntoArticulacion);
                // Al regresar en la recursion actualizo low[v] base a low[adj]
                low[v] = Math.min(low[v], low[adj]);

                // 1. U es raíz del DFS y tiene más de 1 hijo
                if (parent[v] == -1 && hijo > 1)
                    esPuntoArticulacion[v] = true;

                // 2. U no es raíz y low[v] >= disc[u]
                // Esto significa que no hay back-edge desde el subárbol de v hacia un ancestro de u
                if (parent[v] != -1 && low[adj] >= disc[v])
                    esPuntoArticulacion[v] = true;
            }
        }
    }

    public static void main(String[] args) {
        GrafoND grafo = new GrafoND();
        for (int i = 0; i <= 9; i++) {
            grafo.agregarVertice(i);
        }

        // A -> 0 
        // B -> 1
        // G -> 2
        // F -> 3
        // C -> 4
        // H -> 5
        // I -> 6
        // D -> 7
        // E -> 8
        // J -> 9

        grafo.agregarArco(0, 1, 0);
        grafo.agregarArco(0, 2, 0);
        grafo.agregarArco(0, 3, 0);

        grafo.agregarArco(1, 2, 0);
        grafo.agregarArco(1, 3, 0);

        grafo.agregarArco(2, 3, 0);
        grafo.agregarArco(2, 4, 0);
        grafo.agregarArco(2, 5, 0);
        grafo.agregarArco(2, 6, 0);

        grafo.agregarArco(4, 5, 0);
        grafo.agregarArco(4, 6, 0);

        grafo.agregarArco(5, 6, 0);
        grafo.agregarArco(5, 7, 0);
        grafo.agregarArco(5, 8, 0);
        grafo.agregarArco(5, 9, 0);

        grafo.agregarArco(7, 8, 0);

        Ej1 ej1 = new Ej1(grafo);
        List<Integer> puntosArticulacion = ej1.encontrarPuntosArticulacion();
        System.out.println("Puntos de articulación encontrados: " + puntosArticulacion);
    }

}
