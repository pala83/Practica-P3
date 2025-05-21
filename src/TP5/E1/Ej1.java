package TP5.E1;

import java.util.ArrayList;
import java.util.List;

public class Ej1 {
    private int[][] salas;
    int n;

    public Ej1(int n) {
        this.n = n;
        this.salas = new int[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                salas[i][j] = 0;
            }
        }
    }

    public int getN() {
        return n;
    }
    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < salas.length; i++) {
            sb.append("[");
            for (int j = 0; j < salas[i].length; j++) {
                sb.append(salas[i][j]);
                if(j < salas[i].length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }

    public void randomizar() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                salas[i][j] = i==j ? 0 : (int) (Math.random() * (n+1));
            }
        }
    }

    public List<Integer> getSalasAdyacentes(int sala){
        List<Integer> ady = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if(salas[sala][i] != 0) {
                ady.add(i);
            }
        }
        return ady;
    }   

    // Se tiene un conjunto de salas comunicadas entre sí a través de puertas que se abren solamente en  un sentido.
    // Una de las salas se denomina entrada y la otra salida.
    // Construir un algoritmo que  permita ir desde la entrada a la salida atravesando la máxima cantidad de salas.

    public List<Integer> solucion(int i, int j){
        List<Integer> sol = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        boolean[] visitados = new boolean[n];
        
        solucionDFS(i, j, 0, tmp, sol, visitados);

        return sol;
    }

    private int solucionDFS(int i, int j, int tmp, List<Integer> caminoActual, List<Integer> sol, boolean[] visitados){
        visitados[i] = true;
        caminoActual.add(i);
        if(i == j) {
            if(caminoActual.size() > sol.size()) {
                sol.clear();
                sol.addAll(caminoActual);
            }
        } 
        else {
            for(Integer ady : this.getSalasAdyacentes(i))
                if(!visitados[ady]) {
                    solucionDFS(ady, j, tmp+1, caminoActual, sol, visitados);
                }
        }
        visitados[i] = false;
        caminoActual.remove(caminoActual.size()-1);
        return tmp;
    }

    public static void main(String[] args) {
        Ej1 e1 = new Ej1(4);
        e1.randomizar();
        System.out.println(e1);
        System.out.println("Solucion: " + e1.solucion(0, 3));
    }
}