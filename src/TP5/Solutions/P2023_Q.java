package TP5.Solutions;

import java.util.ArrayList;
import java.util.List;

// Dado un conjunto de productos N donde cada producto tiene un peso Pi y un valor economico Vi, y una mochila con capacidad maxima de K kilos. Se desea encontrar el subconjunto de productos a ubicar en la mochila que maximice el valor economico total, sin superar los K kilos disponibles. Se sabe que los productos no pueden ser fraccionados, es decir, si se elige poner un producto X en la mochila debera hacerse en su totalidad.

public class P2023_Q {
    private int[] pesos;
    private int[] valores;
    private int capacidad;
    private int mejorValor;
    private List<Integer> solucion;
    private List<Integer> solucionTMP;

    public P2023_Q(int[] pesos, int[] valores, int capacidad) {
        if (pesos == null || valores == null || pesos.length != valores.length) {
            throw new IllegalArgumentException("Los arreglos de pesos y valores deben tener la misma longitud");
        }
        this.pesos = pesos.clone();
        this.valores = valores.clone();
        this.capacidad = capacidad;
        this.mejorValor = 0;
        this.solucionTMP = new ArrayList<>();
        this.solucion = new ArrayList<>();
    }

    public List<Integer> resolver() {
        backtracking(0, 0, 0);
        return solucion;
    }

    private void backtracking(int indice, int pesoActual, int valorActual) {
        if (indice == pesos.length) {
            if (valorActual > mejorValor) {
                mejorValor = valorActual;
                solucion = new ArrayList<>(solucionTMP);
            }
            return;
        }

        if (pesoActual + pesos[indice] <= capacidad) {
            solucionTMP.add(indice);
            backtracking(indice + 1, pesoActual + pesos[indice], valorActual + valores[indice]);
            solucionTMP.remove(solucionTMP.size() - 1);
        }

        backtracking(indice + 1, pesoActual, valorActual);
    }


    public int getMejorValor() {
        return mejorValor;
    }

    public static void main(String[] args) {
        int[] pesos = {2, 3, 4, 5};  
        int[] valores = {3, 4, 5, 6}; 
        int capacidad = 8;            

        P2023_Q mochila = new P2023_Q(pesos, valores, capacidad);
        List<Integer> solucion = mochila.resolver();

        System.out.println("Productos seleccionados (índices): " + solucion);
        System.out.println("Valor total: " + mochila.getMejorValor());
        
        System.out.println("\nDetalle de productos seleccionados:");
        int pesoTotal = 0;
        int valorTotal = 0;
        for (int i : solucion) {
            System.out.printf("Producto %d - Peso: %d, Valor: %d%n", 
                            i, pesos[i], valores[i]);
            pesoTotal += pesos[i];
            valorTotal += valores[i];
        }
        System.out.printf("\nTotal - Peso: %d, Valor: %d%n", pesoTotal, valorTotal);
    }
}
