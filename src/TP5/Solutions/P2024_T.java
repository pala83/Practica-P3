package TP5.Solutions;

import java.util.HashSet;
import java.util.Set;

// Dado un conjunto C de letras (que tiene mas de 4 elementos), se desean generar todas las palabras validas que contengan exactamente 4 letras sin repetir y no empiecen con una letra vocal. Se supone que contamos con una clase Diccionario que nos permite verificar si una secuencia de letras es una palabra valida <Diccionario.esPalabraValida(string)>.
// Por ejemplo, con C={E,R,N,O,M,A,T} se deberia generar una solucion compuesta por {"MANO", "MONA", "REMO", "MORA", "RAMO", "ROEN", "TOMA", "ROTA", etc...}
// Escriba un algoritmo java que resuelva el problema mediante backtracking.
// Considerar como implementada y funcional la clase Diccionario y la funcion esPalabraValida(string).
public class P2024_T {
    private char[] letras;
    private boolean[] usada;
    private StringBuilder palabraActual;
    private Set<String> palabrasValidas;
    private static final Set<Character> VOCALES = Set.of('A', 'E', 'I', 'O', 'U');

    public P2024_T(char[] letras) {
        this.letras = new char[letras.length];
        for (int i = 0; i < letras.length; i++) {
            this.letras[i] = Character.toUpperCase(letras[i]);
        }
        this.usada = new boolean[letras.length];
        this.palabraActual = new StringBuilder(4);
        this.palabrasValidas = new HashSet<>();
    }

    public Set<String> generarPalabrasValidas() {
        palabrasValidas.clear();
        backtracking(0);
        return new HashSet<>(palabrasValidas);
    }

    private void backtracking(int posicion) {
        if (posicion == 4) {
            String palabra = palabraActual.toString();
            if (Diccionario.esPalabraValida(palabra)) {
                palabrasValidas.add(palabra);
            }
            return;
        }

        for (int i = 0; i < letras.length; i++) {
            if (!usada[i]) {
                if (!(posicion == 0 && esVocal(letras[i]))) {
                    usada[i] = true;
                    palabraActual.append(letras[i]);
                    backtracking(posicion + 1);
                    palabraActual.deleteCharAt(palabraActual.length() - 1);
                    usada[i] = false;
                }
            }
        }
    }

    private boolean esVocal(char c) {
        return VOCALES.contains(Character.toUpperCase(c));
    }

    public static void main(String[] args) {
        char[] letras = {'E', 'R', 'N', 'O', 'M', 'A', 'T'};
        P2024_T generador = new P2024_T(letras);
        
        System.out.println("Letras disponibles: " + new String(letras));
        System.out.println("\nGenerando palabras válidas de 4 letras sin repetición que no empiecen con vocal...");
        
        Set<String> palabras = generador.generarPalabrasValidas();
        
        System.out.println("\nPalabras encontradas (" + palabras.size() + "):");
        int contador = 0;
        for (String palabra : palabras) {
            System.out.print(palabra + " ");
            contador++;
            if (contador % 5 == 0) {
                System.out.println();
            }
        }
    }
}

// Clase simulada del diccionario para propósitos de ejemplo
class Diccionario {
    private static final Set<String> PALABRAS_VALIDAS = Set.of(
        "MANO", "MONA", "REMO", "MORA", "RAMO", "ROEN", "TOMA", "ROTA",
        "MATE", "META", "TENOR", "NOTA", "TONO", "MORE", "ROME", "MOTE",
        "TEMA", "RATO", "TARO", "TORO", "MONT", "NORM", "TRON", "MORO"
    );

    public static boolean esPalabraValida(String palabra) {
        return PALABRAS_VALIDAS.contains(palabra);
    }
}
