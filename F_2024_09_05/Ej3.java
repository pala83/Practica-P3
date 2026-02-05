package Trabajos_Pracicos.F_2024_09_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    Dado un conjunto de numeros reales X se desea encontrar el conjunto minimo de intervalos de distancia 2 de manera tal que todos los numeros del conjunto esten contenidos en dichos intevalos. Por ejemplo, dado el conjunto X={2.0, 1.5, 10.2, 5.7, 8.8, 9.1, 2.1, 9.5} una solucion valida pordia ser {[1.5, 3.5], [5.7, 7.7], [8.8, 10.8]} ya que (3.5 - 1.5) = (7.7 - 5.7) = (10.8 - 8.8) = 2 (distancia)
    Aclaracion: Como se puede ver en el ejemplo, no necesariamente los numeros elegidos para los intervalos deben existir en el conjunto dado.
        a) Describir cual seria la estrategia Greedy que seguirá.
        b) Plantear el algoritmo en JAVA que lo resuelva mediante dicha estrategia Greedy.
*/

public class Ej3 {

    public static class Intervalo {
        double inicio;
        double fin;

        public Intervalo(double inicio, double fin) {
            this.inicio = inicio;
            this.fin = fin;
        }

        @Override
        public String toString() {
            return String.format("[%.1f, %.1f]", inicio, fin);
        }
    }

    /**
     * Estrategia Greedy:
     * 1. Ordenar el conjunto de números de menor a mayor.
     * 2. Tomar el primer número no cubierto (el menor disponible) y establecerlo como el inicio del intervalo.
     *    El intervalo será [x, x + 2].
     *    Esta decisión es greedy porque al empezar el intervalo lo más a la izquierda posible (pero cubriendo el número actual),
     *    maximizamos el alcance del intervalo hacia la derecha para cubrir la mayor cantidad de números futuros posibles.
     * 3. Descartar/Saltar todos los números que caen dentro de este nuevo intervalo.
     * 4. Repetir hasta que no queden números.
     */
    public List<Intervalo> encontrarIntervalos(List<Double> numeros) {
        List<Intervalo> solucion = new ArrayList<>();
        if (numeros == null || numeros.isEmpty()) {
            return solucion;
        }

        // 1. Ordenar los números (O(N log N))
        Collections.sort(numeros);

        // 2. Recorrer y generar intervalos (O(N))
        int i = 0;
        while (i < numeros.size()) {
            // El inicio del intervalo es el menor número no cubierto actualmente
            double inicio = numeros.get(i);
            double fin = inicio + 2.0;
            
            solucion.add(new Intervalo(inicio, fin));

            // Avanzamos el índice saltando todos los números cubiertos por este intervalo
            while (i < numeros.size() && numeros.get(i) <= fin) {
                i++;
            }
        }

        return solucion;
    }

    public static void main(String[] args) {
        List<Double> x = new ArrayList<>();
        // X={2.0, 1.5, 10.2, 5.7, 8.8, 9.1, 2.1, 9.5}
        x.add(2.0);
        x.add(1.5);
        x.add(10.2);
        x.add(5.7);
        x.add(8.8);
        x.add(9.1);
        x.add(2.1);
        x.add(9.5);

        Ej3 ejercicio = new Ej3();
        System.out.println("Conjunto original: " + x);
        
        List<Intervalo> resultado = ejercicio.encontrarIntervalos(x);

        System.out.println("Conjunto ordenado: " + x);
        System.out.println("Intervalos mínimos (" + resultado.size() + "): " + resultado);
    }
}
