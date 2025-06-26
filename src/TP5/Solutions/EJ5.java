package TP5.Solutions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Grafo.Grafo;
import Grafo.GrafoND;
import Grafo.Arco.Arco;

public class EJ5 {
    private Grafo<Integer> grafo;
    private int origen;
    private int destino;
    private int maxVertices;
    private List<Integer> mejorCamino;
    private int mejorCosto;
    private boolean[] visitados;
    private List<Integer> caminoActual;
    private int costoActual;

    public List<Integer> caminoConMenorCosto(Grafo<Integer> g, int origen, int destino, int maxVertices) {
        this.grafo = g;
        this.origen = origen;
        this.destino = destino;
        this.maxVertices = maxVertices;
        this.mejorCosto = Integer.MAX_VALUE;
        this.mejorCamino = new ArrayList<>();
        this.caminoActual = new ArrayList<>();
        // Encontrar el ID máximo de vértice
        int maxId = 0;
        Iterator<Integer> vertices = g.obtenerVertices();
        while (vertices.hasNext()) {
            int v = vertices.next();
            if (v > maxId) maxId = v;
        }
        this.visitados = new boolean[maxId + 1];

        // Inicializar arreglo de visitados
        for (int i = 0; i < visitados.length; i++) {
            visitados[i] = false;
        }

        // Comenzar la búsqueda desde el origen
        caminoActual.add(origen);
        visitados[origen] = true;
        backtrack(origen, 0);

        return mejorCamino;
    }

    private void backtrack(int verticeActual, int verticesVisitados) {
        // Si llegamos al destino y usamos como máximo maxVertices
        if (verticeActual == destino && verticesVisitados <= maxVertices) {
            if (costoActual < mejorCosto) {
                mejorCosto = costoActual;
                mejorCamino = new ArrayList<>(caminoActual);
            }
            return;
        }

        // Si ya visitamos el máximo de vértices, terminamos
        if (verticesVisitados >= maxVertices) {
            return;
        }

        // Explorar todos los vértices adyacentes
        Iterator<Integer> it = grafo.obtenerAdyacentes(verticeActual);
        while (it.hasNext()) {
            int vecino = it.next();
            if (!visitados[vecino]) {
                // Obtener el costo del arco
                Arco<Integer> arco = grafo.obtenerArco(verticeActual, vecino);
                int costoArco = arco.getEtiqueta();

                // Realizar el movimiento
                visitados[vecino] = true;
                caminoActual.add(vecino);
                int costoAnterior = costoActual;
                costoActual += costoArco;

                // Llamada recursiva
                backtrack(vecino, verticesVisitados + 1);

                // Deshacer el movimiento (backtrack)
                visitados[vecino] = false;
                caminoActual.remove(caminoActual.size() - 1);
                costoActual = costoAnterior;
            }
        }
    }

    // Método auxiliar para agregar una arista en ambos sentidos (grafo no dirigido)
    private static void agregarArista(GrafoND<Integer> grafo, int v1, int v2, int costo) {
        grafo.agregarArco(v1, v2, costo);
        grafo.agregarArco(v2, v1, costo);
    }
    
    public static void main(String[] args) {
        // Crear un grafo no dirigido que represente el laberinto de la imagen
        GrafoND<Integer> grafo = new GrafoND<>();
        
        // Agregar vértices (cada celda del laberinto)
        // Usaremos coordenadas (fila, columna) como identificadores: (0,0), (0,1), etc.
        // Convertimos las coordenadas a un único número: fila * 10 + columna
        // Esto nos da: 0, 1, 2, 3, 10, 11, 12, 13, 20, 21, 22, 23, 30, 31, 32, 33
        for (int fila = 0; fila < 4; fila++) {
            for (int col = 0; col < 4; col++) {
                grafo.agregarVertice(fila * 10 + col);
            }
        }
        
        // Agregar aristas (conexiones entre celdas adyacentes)
        // Valores de ejemplo para los costos (podrían ser los valores de las celdas)
        
        // Conexiones horizontales
        agregarArista(grafo, 0, 1, 1);  // (0,0) - (0,1)
        agregarArista(grafo, 0, 10, 1); // (0,0) - (1,0)
        agregarArista(grafo, 1, 2, 1);  // (0,1) - (0,2)
        agregarArista(grafo, 1, 11, 1); // (0,1) - (1,1)
        agregarArista(grafo, 2, 3, 1);  // (0,2) - (0,3)
        agregarArista(grafo, 2, 12, 1); // (0,2) - (1,2)
        agregarArista(grafo, 3, 13, 1); // (0,3) - (1,3)
        
        agregarArista(grafo, 10, 20, 1); // (1,0) - (2,0)
        agregarArista(grafo, 11, 21, 1); // (1,1) - (2,1)
        agregarArista(grafo, 12, 13, 1); // (1,2) - (1,3)
        agregarArista(grafo, 12, 22, 1); // (1,2) - (2,2)
        agregarArista(grafo, 13, 23, 1); // (1,3) - (2,3)
        
        agregarArista(grafo, 20, 21, 1); // (2,0) - (2,1)
        agregarArista(grafo, 20, 30, 1); // (2,0) - (3,0)
        agregarArista(grafo, 21, 22, 1); // (2,1) - (2,2)
        agregarArista(grafo, 21, 31, 1); // (2,1) - (3,1)
        agregarArista(grafo, 22, 23, 1); // (2,2) - (2,3)
        agregarArista(grafo, 22, 32, 1); // (2,2) - (3,2)
        agregarArista(grafo, 23, 33, 1); // (2,3) - (3,3)
        
        agregarArista(grafo, 30, 31, 1); // (3,0) - (3,1)
        agregarArista(grafo, 31, 32, 1); // (3,1) - (3,2)
        agregarArista(grafo, 32, 33, 1); // (3,2) - (3,3)
        
        // Crear instancia de EJ5
        EJ5 ej5 = new EJ5();
        
        // Definir origen (0,0) y destino (3,3)
        int origen = 0;   // (0,0)
        int destino = 33;  // (3,3)
        int maxVertices = 10; // Un máximo razonable para este laberinto
        
        // Encontrar el camino
        List<Integer> camino = ej5.caminoConMenorCosto(grafo, origen, destino, maxVertices);
        
        // Mostrar el laberinto
        System.out.println("Laberinto de prueba (4x4):");
        System.out.println("+---+---+---+---+");
        System.out.println("| S |   |   |   |");
        System.out.println("+   +   +   +   +");
        System.out.println("|   |   |   |   |");
        System.out.println("+   +   +   +   +");
        System.out.println("|   |   |   |   |");
        System.out.println("+   +   +   +   +");
        System.out.println("|   |   |   | E |");
        System.out.println("+---+---+---+---+");
        System.out.println("S = Inicio (0,0), E = Destino (3,3)");
        
        System.out.println("\nBuscando camino de (0,0) a (3,3) con máximo " + maxVertices + " vértices");
        
        if (camino.isEmpty()) {
            System.out.println("No se encontró un camino válido con las restricciones dadas.");
        } else {
            System.out.println("Camino encontrado: " + formatCamino(camino));
            System.out.println("Costo total: " + ej5.mejorCosto);
        }
    }
    
    // Método auxiliar para formatear el camino como coordenadas (fila,col)
    private static String formatCamino(List<Integer> camino) {
        return camino.stream()
                   .map(n -> String.format("(%d,%d)", n/10, n%10))
                   .collect(java.util.stream.Collectors.joining(" -> "));
    }
}

