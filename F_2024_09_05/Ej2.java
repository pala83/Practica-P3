package Trabajos_Pracicos.F_2024_09_05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Trabajos_Pracicos.Estructuras.Arco;
import Trabajos_Pracicos.Estructuras.GrafoND;

/*
    Dado un grafo no dirigido G(V,A), donde cada vértice está conectado con el resto mediante un arco, y cada arco tiene asociado un valor de ponderación.
    Definición: Un grafo se dice cúbico si todos sus vértices tienen grado tres.
    Se quiere encontrar, si existe, un grafo cúbico G’(V,E), donde E es un subconjunto de A, cuya suma de las ponderaciones de los arcos sea mínima.
    Aclaración: el ejemplo de la derecha es sólo esquemático, no se han dibujado las ponderaciones.
    Diseñe un algoritmo mediante la técnica Backtracking que devuelva, si existe, dicho grafo.
        a) Dibuje el árbol de exploración del algoritmo, indicando qué decisiones se toman en cada paso y qué información se lleva en los estados.
        b) Escriba el algoritmo en JAVA solicitado.
        c) Explique la estrategia de poda utilizada.
*/

public class Ej2 {
    private GrafoND grafoCubicoMinimo;
    private GrafoND mejorSolucion;
    private int mejorPeso;

    public Ej2(GrafoND grafo) {
        this.grafoCubicoMinimo = new GrafoND();
        for(int v : grafo.obtenerVertices()){
            this.grafoCubicoMinimo.agregarVertice(v);
        }
        this.mejorPeso = Integer.MAX_VALUE;
    }

    public GrafoND encontrarGrafoCubico(int n, GrafoND grafo) {
        this.mejorSolucion = new GrafoND();
        this.mejorPeso = Integer.MAX_VALUE;

        // Validación básica: Un grafo cúbico debe tener N par y N >= 4
        if (n < 4 || n % 2 != 0) {
            return null;
        }

        // Ordenamos los arcos por peso para intentar encontrar soluciones buenas rápido (heurística para poda)
        List<Arco> arcos = new ArrayList<>();
        for(Arco a : grafo.obtenerArcos()){
            // Filtramos para tener cada arista una sola vez (GrafoND tiene duplicados u->v y v->u)
            if (a.getVerticeOrigen() < a.getVerticeDestino()) {
                arcos.add(a);
            }
        }
        arcos.sort(Comparator.comparingInt(a -> a.getEtiqueta()));

        // Estado inicial: índice 0, grados en 0, peso 0, solución vacía (pero con vértices)
        GrafoND solucionInicial = new GrafoND();
        for (int v : grafo.obtenerVertices()) {
            solucionInicial.agregarVertice(v);
        }
        
        backtracking(arcos, 0, new int[n], 0, solucionInicial);
        System.out.println(mejorSolucion);
        
        return mejorSolucion.cantidadArcos() == 0 ? null : mejorSolucion;
    }

    private void backtracking(List<Arco> arcos, int indice, int[] grados, int pesoActual, GrafoND solucionActual) {
        // 1. Poda por Optimalidad: Si ya superamos el mejor peso encontrado, no seguimos
        if (pesoActual >= mejorPeso) {
            return;
        }

        // 2. Caso Base: Hemos recorrido todos los arcos disponibles
        if (indice == arcos.size()) {
            // Verificar si es una solución válida (todos los vértices con grado 3)
            if (esCubico(grados)) {
                // Como ya validamos pesoActual < mejorPeso arriba, esta es la nueva mejor solución
                mejorPeso = pesoActual;
                // CLONAR la solución actual para no perderla al hacer backtracking
                mejorSolucion = new GrafoND();
                for (int v : solucionActual.obtenerVertices()) {
                    mejorSolucion.agregarVertice(v);
                }
                for (Arco a : solucionActual.obtenerArcos()) {
                    // Solo agregamos una dirección para evitar duplicados (GrafoND agrega la inversa automáticamente)
                    if (a.getVerticeOrigen() < a.getVerticeDestino()) {
                        mejorSolucion.agregarArco(a.getVerticeOrigen(), a.getVerticeDestino(), a.getEtiqueta());
                    }
                }
            }
            return;
        }

        Arco arco = arcos.get(indice);

        // --- Rama 1: Intentar AGREGAR el arco ---
        // Poda por Factibilidad: Solo agregamos si no excede el grado 3 en ninguno de los extremos
        if (grados[arco.getVerticeOrigen()] < 3 && grados[arco.getVerticeDestino()] < 3) {
            // Aplicar cambios
            grados[arco.getVerticeOrigen()]++;
            grados[arco.getVerticeDestino()]++;
            solucionActual.agregarArco(arco.getVerticeOrigen(), arco.getVerticeDestino(), arco.getEtiqueta());

            // Llamada recursiva
            backtracking(arcos, indice + 1, grados, pesoActual + arco.getEtiqueta(), solucionActual);
            // Deshacer cambios (Backtracking)
            solucionActual.borrarArco(arco.getVerticeOrigen(), arco.getVerticeDestino());
            grados[arco.getVerticeOrigen()]--;
            grados[arco.getVerticeDestino()]--;
        }

        // --- Rama 2: NO agregar el arco ---
        // Poda de factibilidad futura (Opcional pero recomendada):
        // Si al descartar este arco, algún vértice ya no puede alcanzar grado 3 con los arcos restantes, podaríamos.
        // (Para simplificar el código en un examen, a veces se omite, pero es la poda más fuerte).
        
        backtracking(arcos, indice + 1, grados, pesoActual, solucionActual);
    }

    private boolean esCubico(int[] grados) {
        for (int g : grados) {
            if (g != 3) return false;
        }
        return true;
    }

    // --- Main para pruebas ---
    public static void main(String[] args) {
        int n = 6;
        GrafoND grafo = new GrafoND();
        for(int i = 0; i < n; i++){
            grafo.agregarVertice(i);
        }
        grafo.agregarArco(0, 1, 10);
        grafo.agregarArco(0, 2, 20);
        grafo.agregarArco(0, 3, 30);
        grafo.agregarArco(0, 4, 35);
        grafo.agregarArco(0, 5, 55);
        grafo.agregarArco(1, 2, 40);
        grafo.agregarArco(1, 3, 50);
        grafo.agregarArco(1, 4, 55);
        grafo.agregarArco(1, 5, 60);
        grafo.agregarArco(2, 3, 60);
        grafo.agregarArco(2, 4, 65);
        grafo.agregarArco(2, 5, 70);
        grafo.agregarArco(3, 4, 70);
        grafo.agregarArco(3, 5, 75);
        grafo.agregarArco(4, 5, 80);
        System.out.println(grafo);

        Ej2 ejercicio = new Ej2(grafo);
        List<Arco> arcos = new ArrayList<>();
        for(Arco a : grafo.obtenerArcos()){
            arcos.add(a);
        }
        
        System.out.println("Buscando grafo cúbico mínimo para K6...");
        GrafoND resultado = ejercicio.encontrarGrafoCubico(n, grafo);
        
        if (resultado != null) {
            System.out.println("Solución encontrada con peso total: " + ejercicio.mejorPeso);
            System.out.println(resultado);
        } else {
            System.out.println("No se encontró solución.");
        }
    }
}
