package Trabajos_Pracicos.R_2025_06_28;

import Trabajos_Pracicos.Estructuras.ABB;
import Trabajos_Pracicos.Estructuras.Nodo;

/*
Dado un arbol binario de busquedam se desea calcular la suma total de los valores almacenados en lso nodos del arbol que stan dentro de un cierto rango [L,R].
Es decir, dados dos enteros L y R, hay que sumar todos los valores v del arbol tales que L <= v <= R.
    a) Implemente un algoritmo en JAVA que recorra solo las partes necesarias del arbo lpara calcular la suma de los valores dentro del rango dado.
    b) Justifique porque su algoritmo evita visitar nodos innecesarios.
*/

public class Ej1 {

    public int sumarRango(ABB arbol, int L, int R) {
        if (arbol == null) {
            return 0;
        }
        return sumarRangoRec(arbol.getRaiz(), L, R);
    }

    private int sumarRangoRec(Nodo nodo, int L, int R) {
        if (nodo == null) {
            return 0;
        }

        int suma = 0;
        int valor = nodo.getValor();

        // Si el valor del nodo está dentro del rango, lo sumamos
        if (valor >= L && valor <= R) {
            suma += valor;
        }

        // Poda: Solo vamos a la izquierda si el valor actual es mayor que L.
        // Si valor <= L, todos los nodos a la izquierda serán menores que L, por lo tanto fuera de rango.
        if (valor > L) {
            suma += sumarRangoRec(nodo.getIzquierdo(), L, R);
        }

        // Poda: Solo vamos a la derecha si el valor actual es menor que R.
        // Si valor >= R, todos los nodos a la derecha serán mayores que R, por lo tanto fuera de rango.
        if (valor < R) {
            suma += sumarRangoRec(nodo.getDerecho(), L, R);
        }

        return suma;
    }

    public static void main(String[] args) {
        ABB arbol = new ABB();
        // Construir un árbol de ejemplo
        //       10
        //      /  \
        //     5    15
        //    / \   / \
        //   3   7 12  18
        
        arbol.insertar(10);
        arbol.insertar(5);
        arbol.insertar(15);
        arbol.insertar(3);
        arbol.insertar(7);
        arbol.insertar(12);
        arbol.insertar(18);

        Ej1 ejercicio = new Ej1();
        
        // Caso 1: Rango [6, 13] -> Debería sumar 7 + 10 + 12 = 29
        int L = 6;
        int R = 13;
        System.out.println("Suma rango [" + L + ", " + R + "]: " + ejercicio.sumarRango(arbol, L, R));
        
        // Caso 2: Rango [16, 20] -> Debería sumar 18
        L = 16;
        R = 20;
        System.out.println("Suma rango [" + L + ", " + R + "]: " + ejercicio.sumarRango(arbol, L, R));

        // Caso 3: Rango [1, 4] -> Debería sumar 3
        L = 1;
        R = 4;
        System.out.println("Suma rango [" + L + ", " + R + "]: " + ejercicio.sumarRango(arbol, L, R));
    }
}
