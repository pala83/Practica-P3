package TP5.Solutions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Se tiene una palabra P dada como parámetro, en la que algunas letras ya han sido descubiertas y otras estan ocultas con guiones bajos por ej. P = "E_A__N".
// Tambien se da como parametro una lista L de letras disponibles que pueden usarse para adivinar las letras ocultas.
// Suponga que se cuenta con una clase Diccionario de lengua española con el metodo:
// public boolean existe(String palabra)
// que retorna true si la palabra pasada como parametro existe en el diccionario. Se desea encontrar TODAS las palabras validas que puedan formarse usando las letras disponibles y la configuracion inicial de la palabra P.
// a) Escriba un algoritmo en java que resuelva el problema mediante backtracking.

public class P2025_T {
    
    private Diccionario diccionario;
    private Set<String> soluciones;
    private char[] palabraArray;
    private List<Character> letrasDisponibles;
    
    public P2025_T() {
        this.diccionario = new Diccionario();
    }
    
    public List<String> encontrarPalabras(String patron, List<Character> letras) {
        soluciones = new HashSet<>();
        this.palabraArray = patron.toCharArray();
        this.letrasDisponibles = new ArrayList<>();
        
        for (char c : letras) {
            this.letrasDisponibles.add(Character.toUpperCase(c));
        }
        
        backtrack(0);
        
        return new ArrayList<>(soluciones);
    }
    
    private void backtrack(int pos) {
        if (pos == palabraArray.length) {
            String palabra = new String(palabraArray);
            if (diccionario.existe(palabra)) {
                soluciones.add(palabra);
            }
            return;
        }
        if (palabraArray[pos] != '_') {
            backtrack(pos + 1);
            return;
        }
        
        for (int i = 0; i < letrasDisponibles.size(); i++) {
            char letra = letrasDisponibles.get(i);
            if (letra != ' ') {
                palabraArray[pos] = letra;
                letrasDisponibles.set(i, ' ');
                backtrack(pos + 1);
                palabraArray[pos] = '_';
                letrasDisponibles.set(i, letra);
            }
        }
    }
    

    private static class Diccionario {

        public boolean existe(String palabra) {
            Set<String> palabrasValidas = new HashSet<>();
            palabrasValidas.add("EXAMEN");
            palabrasValidas.add("ECATON");
            palabrasValidas.add("ENLACE");
            palabrasValidas.add("ENCUENTRO");
            palabrasValidas.add("EJEMPLO");
            palabrasValidas.add("ELEFANTE");
            palabrasValidas.add("CANCIÓN");
            palabrasValidas.add("MANZANA");
            palabrasValidas.add("PROGRAMAR");
            
            return palabrasValidas.contains(palabra);
        }
    }
    
    public static void main(String[] args) {
        P2025_T solucion = new P2025_T();
        
        System.out.println("Ejemplo 1: Patrón E_A___");
        String patron = "E_A___";
        List<Character> letras = new ArrayList<>();
        letras.add('X');
        letras.add('C');
        letras.add('T');
        letras.add('O');
        letras.add('M');
        letras.add('A');
        letras.add('N');
        letras.add('E');
        
        List<String> resultados = solucion.encontrarPalabras(patron, letras);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron palabras válidas.");
        } else {
            System.out.println("Palabras encontradas (" + resultados.size() + "):");
            for (String palabra : resultados) {
                System.out.println("- " + palabra);
            }
        }
        
        System.out.println("\nEjemplo 2: Patrón _A_____A");
        patron = "_A_____A";
        letras.clear();
        letras.add('M');
        letras.add('N');
        letras.add('Z');
        letras.add('A');
        letras.add('P');
        letras.add('L');
        letras.add('E');
        
        resultados = solucion.encontrarPalabras(patron, letras);
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron palabras válidas.");
        } else {
            System.out.println("Palabras encontradas (" + resultados.size() + "):");
            for (String palabra : resultados) {
                System.out.println("- " + palabra);
            }
        }
    }
}
