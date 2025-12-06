package Trabajos_Pracicos.F_2024_04_25;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Finales_resueltos.Estructuras.Grafo;
import Finales_resueltos.Estructuras.GrafoD;

// Escriba un algoritmo de JAVA que dado un Grafo G, devuelva en una lista, si existe, 
// un camino de longitud mayor a d que vaya desde v hasta un vertice w. 
// Los valores de d, v y w seran dados por parametro.

public class Ej4 {
    
    private Grafo grafo;
    private Set<Integer> visitados;

    public Ej4(Grafo grafo) {
        this.grafo = grafo;
        this.visitados = new HashSet<>();
    }

    // Devuelve un camino de longitud > d desde v hasta w, o null si no existe
    public List<Integer> caminoMayorAD(int v, int w, int d) {
        visitados.clear();
        List<Integer> camino = new ArrayList<>();
        if (backtrack(v, w, d, 0, camino)) {
            return camino;
        }
        return null; // No existe camino con longitud > d
    }

    private boolean backtrack(int actual, int destino, int d, int longitud, List<Integer> camino) {
        // Agregamos el vértice actual al camino
        visitados.add(actual);
        camino.add(actual);

        // Caso base: llegamos al destino con longitud > d
        if (actual == destino && longitud > d) {
            return true;
        }

        // Exploramos los adyacentes
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(actual);
        while (adyacentes != null && adyacentes.hasNext()) {
            int adyacente = adyacentes.next();
            if (!visitados.contains(adyacente)) {
                if (backtrack(adyacente, destino, d, longitud + 1, camino)) {
                    return true; // Encontramos un camino válido
                }
            }
        }

        // Backtrack: no encontramos camino por aquí
        visitados.remove(actual);
        camino.remove(camino.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        /*
         * Grafo de ejemplo:
         * 
         *     1 ──→ 2 ──→ 3
         *     │     │     │
         *     ↓     ↓     ↓
         *     4 ──→ 5 ──→ 6
         *           │
         *           ↓
         *           7
         * 
         * Algunos caminos de 1 a 6:
         * - 1 → 2 → 3 → 6  (longitud 3)
         * - 1 → 2 → 5 → 6  (longitud 3)
         * - 1 → 4 → 5 → 6  (longitud 3)
         * - 1 → 2 → 5 → 7 → ? (no llega a 6)
         */
        
        GrafoD grafo = new GrafoD();
        
        // Agregamos vértices
        for (int i = 1; i <= 7; i++) {
            grafo.agregarVertice(i);
        }
        
        // Agregamos arcos (el tercer parámetro es la etiqueta/peso)
        grafo.agregarArco(1, 2, 1);
        grafo.agregarArco(1, 4, 1);
        grafo.agregarArco(2, 3, 1);
        grafo.agregarArco(2, 5, 1);
        grafo.agregarArco(3, 6, 1);
        grafo.agregarArco(4, 5, 1);
        grafo.agregarArco(5, 6, 1);
        grafo.agregarArco(5, 7, 1);
        
        Ej4 ejercicio = new Ej4(grafo);
        
        // Prueba 1: Camino de 1 a 6 con longitud > 2
        int v = 1, w = 6, d = 2;
        List<Integer> resultado = ejercicio.caminoMayorAD(v, w, d);
        System.out.println("Camino de " + v + " a " + w + " con longitud > " + d + ":");
        System.out.println(resultado != null ? resultado : "No existe");
        // Esperado: [1, 2, 3, 6] o [1, 2, 5, 6] o [1, 4, 5, 6] (longitud 3 > 2)
        
        // Prueba 2: Camino de 1 a 6 con longitud > 3 (no debería existir)
        d = 3;
        resultado = ejercicio.caminoMayorAD(v, w, d);
        System.out.println("\nCamino de " + v + " a " + w + " con longitud > " + d + ":");
        System.out.println(resultado != null ? resultado : "No existe");
        // Esperado: No existe (todos los caminos tienen longitud exacta 3)
        
        // Prueba 3: Camino de 1 a 7 con longitud > 2
        w = 7;
        d = 2;
        resultado = ejercicio.caminoMayorAD(v, w, d);
        System.out.println("\nCamino de " + v + " a " + w + " con longitud > " + d + ":");
        System.out.println(resultado != null ? resultado : "No existe");
        // Esperado: [1, 2, 5, 7] o [1, 4, 5, 7] (longitud 3 > 2)
    }
}
