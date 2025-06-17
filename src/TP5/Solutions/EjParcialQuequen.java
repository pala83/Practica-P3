package TP5.Solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Grafo.Grafo;

public class EjParcialQuequen {
    // Dado un grafo no dirigido G que contiene varios ciclos, escriba un algoritmo en JAVA que devuelva (en una lista de nodos) el ciclo de mayor tamaño (o sea, el ciclo compuesto por la mayor cantidad de nodos).

    private Grafo<Integer> grafo;
    private HashMap<Integer, String> colores;
    private List<Integer> mejorSolucion;

    
    public EjParcialQuequen(Grafo<Integer> grafo) {
        this.grafo = grafo;
        this.colores = new HashMap<>();
        Iterator<Integer> itv = grafo.obtenerVertices();
        while (itv.hasNext()) {
            Integer vertex = itv.next();
            colores.put(vertex, "Blanco");
        }
        this.mejorSolucion = new ArrayList<>();
    }
  
    public void solucionador_de_la_solucion_a_solucionar(Grafo<Integer> G){
        Iterator<Integer> itv = G.obtenerVertices();
        while (itv.hasNext()) {
            Integer vertice = itv.next();
            if(colores.get(vertice).equals("Blanco")) {
                buscarCiclosMasLargo(vertice, new ArrayList<>());
            }
        }
    }

    public void buscarCiclosMasLargo(Integer vertice, List<Integer> cicloActual) {
        cicloActual.add(vertice);
        colores.put(vertice, "Amarillo");
        if(cicloActual.size() > mejorSolucion.size()) {
            mejorSolucion.clear();
            mejorSolucion.addAll(cicloActual);
        }
        Iterator<Integer> itv = grafo.obtenerAdyacentes(vertice);
        while (itv.hasNext()) {
            Integer adyacente = itv.next();
            if(colores.get(adyacente).equals("Blanco")) {
                colores.put(adyacente, "Amarillo");
                buscarCiclosMasLargo(adyacente, cicloActual);
            }
            else if(colores.get(adyacente).equals("Amarillo") && cicloActual.contains(adyacente)) {
                int index = cicloActual.indexOf(adyacente);
                List<Integer> ciclo = cicloActual.subList(index, cicloActual.size());
                if(ciclo.size() > mejorSolucion.size()) {
                    mejorSolucion.clear();
                    mejorSolucion.addAll(ciclo);
                }
            }        
        }

    }


// Dado un conjunto N de numeros enteros distintos, se desea encontrar, si existen, 4 subconjuntos no vacios y disjuntos que sean una particion
// de N (o sea la union de los 4 subconjuntos es igual al conjunto N), tal que la suma de los elementos de cada subconjunto sea igual a un
// numero T dado como parametro.

// Por ej: N=[5,19,3,7,9,2,1,-10] T=9. Una solucion seria [{5,3,1},{7,2},{9},{19,-10}]

    public List<Set<Integer>> solucion(Set<Integer> numeros, int T) {
        List<Set<Integer>> subconjuntos = new ArrayList<>();
        HashMap<Integer, String> colores = new HashMap<>();
        for(Integer numero : numeros) {
            colores.put(numero, "blanco"); // Inicialmente todos los numeros son blancos
        }
        Set<Integer> subconjuntoActual = new HashSet<>();
        Iterator<Integer> it = numeros.iterator();
        while (it.hasNext() && subconjuntos.size() < 4) {
            Integer numero = it.next();
            if(colores.get(numero).equals("blanco")) {
                this.backtracking(numero, numeros, subconjuntos, colores, subconjuntoActual, T);
            }
        }
        return subconjuntos;
    }

    private int backtracking(Integer numero,
                              Set<Integer> numeros, 
                              List<Set<Integer>> subconjuntos, 
                              HashMap<Integer, String> colores, 
                              Set<Integer> subconjuntoActual, 
                              int sumaActual) {
        subconjuntoActual.add(numero);
        colores.put(numero, "amarillo");
        sumaActual -= numero;
        if(sumaActual == 0) {
            subconjuntos.add(new HashSet<>(subconjuntoActual));
            if(subconjuntos.size() == 4) {
                return sumaActual;
            }
        }
        for(Integer adyacente : numeros) {
            if(colores.get(adyacente).equals("blanco") && sumaActual - adyacente >= 0) {
                sumaActual = this.backtracking(adyacente, numeros, subconjuntos, colores, subconjuntoActual, sumaActual);
                colores.put(adyacente, "blanco");
                subconjuntoActual.remove(adyacente);
                sumaActual += adyacente;
            }
        }
        return sumaActual;
    }


    public static void main(String[] args) {
        EjParcialQuequen solucionador = new EjParcialQuequen();
        Set<Integer> numeros = new HashSet<>();
        numeros.add(5);
        numeros.add(19);
        numeros.add(3);
        numeros.add(7);
        numeros.add(9);
        numeros.add(2);
        numeros.add(1);
        numeros.add(-10);
        
        int T = 9;
        
        List<Set<Integer>> resultado = solucionador.solucion(numeros, T);
        
        System.out.println("Subconjuntos encontrados:");
        for(Set<Integer> subconjunto : resultado) {
            System.out.println(subconjunto);
        }
    }
}
