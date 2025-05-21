package TP5.Solutions;

import java.util.LinkedList;
import java.util.List;
import TP5.Clases.Celda;
import TP5.Clases.Posicion;

public class Ej2 {
    private Celda[][] laberinto;
    private List<Posicion> mejorCamino;
    private int tam;

    public Ej2(int tam) {
        this.laberinto = new Celda[tam][tam];
        this.mejorCamino = new LinkedList<>();
        this.tam = tam;
        this.generarLaberinto();
    }

    private void generarLaberinto() {
        for(int i = 0; i < tam; i++) {
            for(int j = 0; j < tam; j++) {
                laberinto[i][j] = generarCelda(i, j);
            }
        }
    }

    public List<Posicion> solucion(Posicion origen, Posicion destino){
        List<Posicion> camino = new LinkedList<>();
        int valorAcumulado = 0;
        this.backtracking(origen, destino, camino, valorAcumulado);
        return this.mejorCamino;
    }

    private void backtracking(Posicion origen, Posicion destino, List<Posicion> caminoActual, int valorAcumulado){
        caminoActual.add(origen);
        if(origen.equals(destino)){
            if(valorAcumulado < this.mejorCamino.size()){
                this.mejorCamino.clear();
                this.mejorCamino.addAll(caminoActual);
            }
        }
        
    }

    private Celda generarCelda(int fila, int columna) {
        int valor = (int)(Math.random()*100);
        boolean norte = (Math.random() < 0.5 ? false : true);
        boolean este = (Math.random() < 0.5 ? false : true);
        boolean sur = (Math.random() < 0.5 ? false : true);
        boolean oeste = (Math.random() < 0.5 ? false : true);
        if(fila == 0) norte = false;
        if(columna == 0) este = false;
        if(fila == tam-1) sur = false;
        if(columna == tam-1) oeste = false;
        return new Celda(valor, norte, este, sur, oeste);
    }

    public void imprimirLaberinto() {
        for(int i = 0; i < tam; i++) {
            System.out.print("|");
            for(int j = 0; j < tam; j++) {
                System.out.print(laberinto[i][j].getValor() + " " + "("+ 
                                                                    cambiarbooleano(laberinto[i][j].getPuedeIrNorte()) + ", " + 
                                                                    cambiarbooleano(laberinto[i][j].getPuedeIrEste()) + ", " + 
                                                                    cambiarbooleano(laberinto[i][j].getPuedeIrSur()) + ", " + 
                                                                    cambiarbooleano(laberinto[i][j].getPuedeIrOeste()) + ")\t");
            }
            System.out.println("|");
        }
    }

    private int cambiarbooleano(boolean valor) {
        return valor ? 1 : 0;
    }
}
