package Trabajos_Pracicos.F_2024_04_25;

import java.util.ArrayList;
import java.util.List;

import Trabajos_Pracicos.Estructuras.ABB;
import Trabajos_Pracicos.Estructuras.Nodo;
import Trabajos_Pracicos.Estructuras.Printer.Structure;

public class Ej2 {
    private ABB arbol;
    private int[] valores;
    public static void main(String[] args) {
        Ej2 ejercicio = new Ej2();
        ejercicio.valores = new int[]{15,6,3,2,4,7,13,9,18,16,20};
        ejercicio.cargarArbol(ejercicio.valores);
        ejercicio.arbol.print(ejercicio.arbol.getRaiz(), new Structure());
        int M = 6;
        int N = 17;
        List<Integer> resultado = ejercicio.algoritmo(M, N);
        System.out.println("Valores en el rango [" + M + ", " + N + "]: " + resultado);
    }

    public void cargarArbol(int[] valores) {
        arbol = new ABB();
        for (int valor : valores) {
            arbol.insertar(valor);
        }
    }
    // Escriba un algoritmo java que dado un arbol binario de busqueda binario y dos valores de umbral M y N retorne una lista de todos los valores en el rango [M,N] contenidos en el arbol. La lista resultante debe estar ordenada de menor a mayor. Por ejemplo, para el arbol {15,6,3,2,4,7,13,9,18,16,20} y la entrada M=6 y N=17, el algoritmo debe dar como salida la lista [6,7,9,13,15,16].
    public List<Integer> algoritmo(int M, int N){
        List<Integer> resultado = new ArrayList<>();
        rangoRec(arbol.getRaiz(), M, N, resultado);
        return resultado;
    }

    private void rangoRec(Nodo nodo, int M, int N, List<Integer> resultado) {
        if (nodo == null) {
            return;
        }
        if (nodo.getValor() > M) {
            rangoRec(nodo.getIzquierdo(), M, N, resultado);
        }
        if (nodo.getValor() >= M && nodo.getValor() <= N) {
            resultado.add(nodo.getValor());
        }
        if (nodo.getValor() < N) {
            rangoRec(nodo.getDerecho(), M, N, resultado);
        }
    }
}