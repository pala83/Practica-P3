package Trabajos_Pracicos.R_2025_06_28;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Trabajos_Pracicos.Estructuras.Grafo;
import Trabajos_Pracicos.Estructuras.GrafoND;

/*
Un mapa muestra un continente dividido en vairas regiones, el mapa representa como un grafo no dirigido, donde cada nodo representa una region y cada arista indica qie don regiones son adyacentes (comparten frotnera).
El objetivo es asignar colores a las regiones de todo el mapa de forma tal que ninguna region adyacente tenga el mismo color y se utilice la minimca cantidad posible de colores.
Implementar en java el metodo colorear especificando a contiuacion, que mediante backtracking determine cual es el minimo numero de colores necesario y devuelva una asignacion valida de colores para todas las regiones.

public HashMap<int, int> colorear(Grafo g)

Describa cual seria la estrategia de poda que utilizaria para optimizar la busqueda.
*/

public class Ej3 {

    /*
     * Estrategia de Poda:
     * La poda principal consiste en verificar la validez de un color antes de explorar recursivamente.
     * Al intentar asignar un color 'c' a una región (nodo) 'u', verificamos inmediatamente si alguno de sus
     * vecinos ya tiene asignado el color 'c'.
     * 
     * - Si algún vecino ya tiene el color 'c', la asignación es inválida (restricción del problema).
     *   En este caso, "podamos" la rama: no continuamos asignando colores al resto del grafo con esta configuración,
     *   ya que sabemos que no llevará a una solución válida.
     * 
     * Esta verificación temprana evita explorar un subárbol exponencial de asignaciones que serían inválidas
     * debido a la decisión actual.
     */

    public HashMap<Integer, Integer> colorear(Grafo g) {
        List<Integer> vertices = new ArrayList<>();
        for (Integer v : g.obtenerVertices()) {
            vertices.add(v);
        }

        // Intentamos encontrar una solución incrementando el número de colores disponibles (m)
        // Empezamos con 1 color, luego 2, etc., hasta encontrar la mínima cantidad necesaria.
        // El peor caso es N colores (cada nodo con un color distinto).
        for (int m = 1; m <= vertices.size(); m++) {
            HashMap<Integer, Integer> asignacionColores = new HashMap<>();
            if (backtracking(g, vertices, 0, m, asignacionColores)) {
                System.out.println("Solución encontrada con " + m + " colores.");
                return asignacionColores;
            }
        }
        return null; // No debería ocurrir en un grafo válido
    }

    private boolean backtracking(Grafo g, List<Integer> vertices, int indice, int m, HashMap<Integer, Integer> colores) {
        // Caso Base: Si hemos asignado color a todos los vértices, encontramos una solución
        if (indice == vertices.size()) {
            return true;
        }

        int verticeActual = vertices.get(indice);

        // Probar cada color desde 1 hasta m
        for (int color = 1; color <= m; color++) {
            // Poda: Verificar si es seguro asignar este color
            if (esSeguro(g, verticeActual, color, colores)) {
                // Asignar color
                colores.put(verticeActual, color);

                // Recursión: Intentar colorear el siguiente vértice
                if (backtracking(g, vertices, indice + 1, m, colores)) {
                    return true;
                }

                // Backtracking: Desasignar color para probar el siguiente
                colores.remove(verticeActual);
            }
        }

        // Si ningún color del 1 al m sirve para este vértice con la configuración actual, retornamos false
        return false;
    }

    private boolean esSeguro(Grafo g, int vertice, int color, HashMap<Integer, Integer> colores) {
        // Verificar todos los adyacentes del vértice actual
        for (Integer adyacente : g.obtenerAdyacentes(vertice)) {
            // Si el adyacente ya tiene un color asignado y es el mismo que queremos usar, no es seguro
            if (colores.containsKey(adyacente) && colores.get(adyacente) == color) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Ej3 ejercicio = new Ej3();
        GrafoND grafo = new GrafoND();
        
        // Crear un grafo de ejemplo (Triángulo + 1 nodo conectado a uno de ellos)
        // 1 -- 2
        // | \  |
        // |  \ |
        // 4 -- 3
        
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);

        grafo.agregarArco(1, 2, 0);
        grafo.agregarArco(2, 3, 0);
        grafo.agregarArco(3, 1, 0); // Ciclo de 3 (requiere 3 colores)
        grafo.agregarArco(3, 4, 0);
        grafo.agregarArco(1, 4, 0);

        System.out.println("Coloreando grafo...");
        HashMap<Integer, Integer> resultado = ejercicio.colorear(grafo);
        
        System.out.println("Asignación de colores resultante:");
        System.out.println(resultado);
    }
}
