package Trabajos_Pracicos.F_2025_03_13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    Dado un conjunto de M numeros naturales positivo se esea responder en cuantas secuancias distintas se pueden ubicar dichos numeros sabiendo que as secuencias no deben estar vacias ni contener 2 nuemeros pares seguidos.
    Por ejemplo, con el conjunto M = {1,2,3,4,} se podrian formar 12 secuencias < {1,2,3,4}, {1,4,3,2}, {2,1,3,4}, {2,1,4,3}, {2,3,1,4}, {2,3,4,1}, {3,2,1,4}, {3,4,1,2}, {4,1,2,3}, {4,1,3,2}, {4,3,2,1}, {4,3,1,2} > (notese que el 2 y el 4 nunca van de manera consecutiva ya que ambos son pares).
*/

public class Ej3 {

    private int contadorSecuencias;

    public int contarSecuenciasValidas(int[] numeros) {
        this.contadorSecuencias = 0;
        // Ordenamos para facilitar el manejo de permutaciones si fuera necesario, 
        // aunque para backtracking puro con boolean[] usados no es estrictamente necesario.
        // Pero ayuda a visualizar.
        Arrays.sort(numeros);
        
        boolean[] usados = new boolean[numeros.length];
        List<Integer> secuenciaActual = new ArrayList<>();
        
        backtracking(numeros, usados, secuenciaActual);
        
        return contadorSecuencias;
    }

    private void backtracking(int[] numeros, boolean[] usados, List<Integer> secuenciaActual) {
        // Caso base: La secuencia tiene la longitud del conjunto original (es una permutación completa)
        if (secuenciaActual.size() == numeros.length) {
            contadorSecuencias++;
            // Opcional: Imprimir la secuencia para verificar
            // System.out.println(secuenciaActual);
            return;
        }

        for (int i = 0; i < numeros.length; i++) {
            if (!usados[i]) {
                int numeroCandidato = numeros[i];
                
                // Poda: Verificar restricción de pares consecutivos
                if (esValidoAgregar(secuenciaActual, numeroCandidato)) {
                    
                    // Marcar
                    usados[i] = true;
                    secuenciaActual.add(numeroCandidato);
                    
                    // Recursión
                    backtracking(numeros, usados, secuenciaActual);
                    
                    // Desmarcar (Backtracking)
                    secuenciaActual.remove(secuenciaActual.size() - 1);
                    usados[i] = false;
                }
            }
        }
    }

    private boolean esValidoAgregar(List<Integer> secuencia, int numero) {
        // Si la secuencia está vacía, siempre es válido agregar el primer número
        if (secuencia.isEmpty()) {
            return true;
        }

        // Obtenemos el último número agregado
        int ultimoNumero = secuencia.get(secuencia.size() - 1);

        // Verificamos si ambos son pares
        boolean ultimoEsPar = (ultimoNumero % 2 == 0);
        boolean nuevoEsPar = (numero % 2 == 0);

        // No es válido si ambos son pares
        if (ultimoEsPar && nuevoEsPar) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Ej3 ejercicio = new Ej3();
        
        // Ejemplo del enunciado: {1, 2, 3, 4}
        int[] m = {1, 2, 3, 4};
        
        System.out.println("Conjunto M: " + Arrays.toString(m));
        int cantidad = ejercicio.contarSecuenciasValidas(m);
        System.out.println("Cantidad de secuencias válidas: " + cantidad);
    }
}
