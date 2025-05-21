package TP5.Clases;

public class Posicion {
    private int fila;
    private int columna;

    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() { return this.fila; }
    public int getColumna() { return this.columna; }
}
