package Trabajos_Pracicos.Estructuras;

public class Nodo {
    private int valor;
    private Nodo izquierdo;
    private Nodo derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }

    public int getValor() {
        return valor;
    }
    
    public Nodo getIzquierdo() {
        return izquierdo;
    }
    
    public Nodo getDerecho() {
        return derecho;
    }
    
    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
}
