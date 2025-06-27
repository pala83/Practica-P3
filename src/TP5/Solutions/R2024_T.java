package TP5.Solutions;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

// Dado un grafo no dirigido G(V,A) escribir un algoritmo en JAVA que permita encontrar el ciclo Hamiltoniano(ciclo que pasa una vez por todos los vertices de V) de mayor peso (o sea que la suma de los arcos que componen el ciclo sea la mayor posible).
// Devuelva como resultado la lista de vertices que se deben seguir en secuencia para formar el ciclo.
// Si hay mas de un ciclo con el mismo valor maximo, dar como resultado cualqueira de ellos.
// Por ejemplo para el grafo de la derecha que tiene varios ciclos hamiltonianos, debe encontrarse y devolver el de mayor peso (en este caso A-B-C-F-D-E-A con peso 180).
// Ciclos ej: A-B-C-F-E-D-A=140, A-B-C-F-D-E-A=180, A-C-B-F-E-D-A=130, etc...
// Grafo d eejemplo:
// Vertices: A, B, C, D, E, F
// Aristas: 
//  A-B=40, A-C=10, A-D=10, A-E=40,
//  B-C=20, B-F=30,
//  C-F=10,
//  D-E=40, D-F=30,
//  E-F=20


public class R2024_T {

    public static List<Integer> cicloHamiltonianoMayorPeso(Grafo.GrafoND<Integer> grafo) {
        int n = grafo.cantidadVertices();
        if (n == 0) return new ArrayList<>();

        List<Integer> vertices = new ArrayList<>();
        Iterator<Integer> it = grafo.obtenerVertices();
        while (it.hasNext()) {
            vertices.add(it.next());
        }

        List<Integer> mejorCiclo = new ArrayList<>();
        int[] maxPeso = {Integer.MIN_VALUE};

        for (int i = 0; i < vertices.size(); i++) {
            boolean[] visitados = new boolean[n + 100];
            List<Integer> caminoActual = new ArrayList<>();
            int inicio = vertices.get(i);
            caminoActual.add(inicio);
            visitados[inicio] = true;
            backtrack(grafo, inicio, inicio, visitados, caminoActual, 0, maxPeso, mejorCiclo, n);
        }
        return mejorCiclo;
    }

    private static void backtrack(Grafo.GrafoND<Integer> grafo, int actual, int inicio, boolean[] visitados, List<Integer> caminoActual, int pesoActual, int[] maxPeso, List<Integer> mejorCiclo, int n) {
        if (caminoActual.size() == n) {
            if (grafo.existeArco(actual, inicio)) {
                Grafo.Arco.Arco<Integer> arco = grafo.obtenerArco(actual, inicio);
                int pesoTotal = pesoActual + arco.getEtiqueta();
                if (pesoTotal > maxPeso[0]) {
                    maxPeso[0] = pesoTotal;
                    mejorCiclo.clear();
                    mejorCiclo.addAll(caminoActual);
                    mejorCiclo.add(inicio); // Para cerrar el ciclo
                }
            }
            return;
        }
        Iterator<Integer> it = grafo.obtenerAdyacentes(actual);
        while (it.hasNext()) {
            int sig = it.next();
            if (!visitados[sig]) {
                Grafo.Arco.Arco<Integer> arco = grafo.obtenerArco(actual, sig);
                visitados[sig] = true;
                caminoActual.add(sig);
                backtrack(grafo, sig, inicio, visitados, caminoActual, pesoActual + arco.getEtiqueta(), maxPeso, mejorCiclo, n);
                caminoActual.remove(caminoActual.size() - 1);
                visitados[sig] = false;
            }
        }
    }

    public static void main(String[] args) {
        String[] nombres = {"A", "B", "C", "D", "E", "F"};
        int n = nombres.length;
        java.util.Map<String, Integer> nombreAId = new java.util.HashMap<>();
        java.util.Map<Integer, String> idANombre = new java.util.HashMap<>();
        for (int i = 0; i < n; i++) {
            nombreAId.put(nombres[i], i);
            idANombre.put(i, nombres[i]);
        }
        Grafo.GrafoND<Integer> grafo = new Grafo.GrafoND<>();
        for (int i = 0; i < n; i++) grafo.agregarVertice(i);
        grafo.agregarArco(nombreAId.get("A"), nombreAId.get("B"), 40);
        grafo.agregarArco(nombreAId.get("A"), nombreAId.get("C"), 10);
        grafo.agregarArco(nombreAId.get("A"), nombreAId.get("D"), 10);
        grafo.agregarArco(nombreAId.get("A"), nombreAId.get("E"), 40);
        grafo.agregarArco(nombreAId.get("B"), nombreAId.get("C"), 20);
        grafo.agregarArco(nombreAId.get("B"), nombreAId.get("F"), 30);
        grafo.agregarArco(nombreAId.get("C"), nombreAId.get("F"), 10);
        grafo.agregarArco(nombreAId.get("D"), nombreAId.get("E"), 40);
        grafo.agregarArco(nombreAId.get("D"), nombreAId.get("F"), 30);
        grafo.agregarArco(nombreAId.get("E"), nombreAId.get("F"), 20);

        java.util.List<Integer> ciclo = cicloHamiltonianoMayorPeso(grafo);
        System.out.print("Ciclo Hamiltoniano de mayor peso: ");
        int peso = 0;
        for (int i = 0; i < ciclo.size(); i++) {
            System.out.print(idANombre.get(ciclo.get(i)));
            if (i < ciclo.size() - 1) System.out.print("-");
            if (i < ciclo.size() - 1) {
                int u = ciclo.get(i), v = ciclo.get(i+1);
                if (grafo.existeArco(u, v)) peso += grafo.obtenerArco(u, v).getEtiqueta();
            }
        }
        System.out.println("\nPeso total: " + peso);
    }

}

