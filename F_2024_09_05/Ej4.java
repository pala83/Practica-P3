package Trabajos_Pracicos.F_2024_09_05;

/**
    Dado un arreglo ordenado de numeros enteros se desea obtener, de existir, un par de numeros X e Y cuya suma sea igual a 5.
    Plantee un algoritmo que resuelva de forma eficiente el problema propuesto en una complejidad computacional como maximo de O(n logn). Donde n es la cantidad de elementos del arreglo.
*/

public class Ej4 {

    // Clase auxiliar para retornar el par
    public static class Par {
        int x, y;
        public Par(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    public Par encontrarParSuma(int[] arreglo, int objetivo) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;

        while (izquierda < derecha) {
            int suma = arreglo[izquierda] + arreglo[derecha];

            if (suma == objetivo) {
                return new Par(arreglo[izquierda], arreglo[derecha]);
            } else if (suma < objetivo) {
                // Si la suma es menor, necesitamos números más grandes -> movemos izquierda
                izquierda++;
            } else {
                // Si la suma es mayor, necesitamos números más chicos -> movemos derecha
                derecha--;
            }
        }

        return null; // No se encontró
    }

    public static void main(String[] args) {
        Ej4 ejercicio = new Ej4();
        
        // Caso 1: Existe
        int[] arr1 = {-3, 0, 1, 2, 4, 5, 8}; // -3 + 8 = 5, 1 + 4 = 5
        System.out.println("Arr1: " + ejercicio.encontrarParSuma(arr1, 5));

        // Caso 2: No existe
        int[] arr2 = {1, 2, 6, 7};
        System.out.println("Arr2: " + ejercicio.encontrarParSuma(arr2, 5));
        
        // Caso 3: Justo
        int[] arr3 = {2, 3};
        System.out.println("Arr3: " + ejercicio.encontrarParSuma(arr3, 5));
    }
}
