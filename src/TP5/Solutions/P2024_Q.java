package TP5.Solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Se tiene un conjunto de N elementos y se quieren obtener todas las formas distintas de colocar esos elementos en una secuencia S.
// Por ejemplo, si el conjunto es {1, 2, 3}, las formas son: {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}.
// Escriba un algoritmo java que resuelva el problema mediante backtracking.
public class P2024_Q {
    private List<List<Integer>> permutaciones;
    private Integer[] elementos;
    private boolean[] usado;
    private Integer[] permutacionActual;

    public P2024_Q(Integer[] elementos) {
        this.elementos = elementos;
        this.permutaciones = new ArrayList<>();
        this.usado = new boolean[elementos.length];
        this.permutacionActual = new Integer[elementos.length];
    }

    public List<List<Integer>> generarPermutaciones() {
        permutaciones.clear();
        backtracking(0);
        return new ArrayList<>(permutaciones);
    }

    private void backtracking(int posicion) {
        if (posicion == elementos.length) {
            permutaciones.add(new ArrayList<>(Arrays.asList(permutacionActual)));
            return;
        }
        for (int i = 0; i < elementos.length; i++) {
            if (!usado[i]) {
                usado[i] = true;
                permutacionActual[posicion] = elementos[i];
                backtracking(posicion + 1);
                usado[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] conjunto = {8, 2, 4};
        P2024_Q generador = new P2024_Q(conjunto);
        System.out.println("Conjunto original: " + Arrays.toString(conjunto));
        System.out.println("\nTodas las permutaciones posibles:");
        List<List<Integer>> permutaciones = generador.generarPermutaciones();
        for (List<Integer> permutacion : permutaciones) {
            System.out.println(permutacion);
        }
        System.out.println("\nTotal de permutaciones: " + permutaciones.size());
    }
}
