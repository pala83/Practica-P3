package TP5.Solutions;
// Partición de conjunto. Dado un conjunto de n enteros se desea encontrar, si existe, una partición en dos subconjuntos disjuntos, tal que la suma de sus elementos sea la misma.
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ej4 {
    private int[] numeros;
    private boolean[] enPrimerConjunto;
    private boolean solucionEncontrada;
    private Set<Integer> primerConjunto;
    private Set<Integer> segundoConjunto;

    public Ej4(int[] numeros) {
        this.numeros = numeros;
        this.enPrimerConjunto = new boolean[numeros.length];
        this.solucionEncontrada = false;
    }

    public boolean existeParticion() {
        int sumaTotal = 0;
        for(Integer num : numeros) {
            sumaTotal += num;
        }
        if (sumaTotal % 2 != 0) {
            return false;
        }
        int sumaObjetivo = sumaTotal / 2;
        return backtracking(0, 0, sumaObjetivo);
    }

        
    private boolean backtracking(int indice, int sumaActual, int sumaObjetivo) {
        if (solucionEncontrada) {
            return true;
        }
        if (sumaActual == sumaObjetivo) {
            guardarSolucion();
            solucionEncontrada = true;
            return true;
        }
        if (sumaActual > sumaObjetivo || indice >= numeros.length) {
            return false;
        }
        enPrimerConjunto[indice] = true;
        if (backtracking(indice + 1, sumaActual + numeros[indice], sumaObjetivo)) {
            return true;
        }
        enPrimerConjunto[indice] = false;
        return backtracking(indice + 1, sumaActual, sumaObjetivo);
    }
    
    private void guardarSolucion() {
        primerConjunto = new HashSet<>();
        segundoConjunto = new HashSet<>();
        for (int i = 0; i < numeros.length; i++) {
            if (enPrimerConjunto[i]) {
                primerConjunto.add(numeros[i]);
            } else {
                segundoConjunto.add(numeros[i]);
            }
        }
    }
    
    public Set<Integer> getPrimerConjunto() {
        if (!solucionEncontrada) {
            return null;
        }
        return new HashSet<>(primerConjunto);
    }
    
    public Set<Integer> getSegundoConjunto() {
        if (!solucionEncontrada) {
            return null;
        }
        return new HashSet<>(segundoConjunto);
    }

    public static void main(String[] args) {
        int[] conjunto = {1, 5, 11, 9, 6};
        Ej4 particion = new Ej4(conjunto);
        
        System.out.println("Conjunto original: " + Arrays.toString(conjunto));
        
        if (particion.existeParticion()) {
            System.out.println("\nSe encontró una partición válida:");
            System.out.println("Primer subconjunto: " + particion.getPrimerConjunto() + " (Suma: " + particion.getPrimerConjunto().stream().mapToInt(Integer::intValue).sum() + ")");
            System.out.println("Segundo subconjunto: " + particion.getSegundoConjunto() + " (Suma: " + particion.getSegundoConjunto().stream().mapToInt(Integer::intValue).sum() + ")");
        } else {
            System.out.println("\nNo es posible dividir el conjunto en dos subconjuntos con sumas iguales.");
        }

    }
}
