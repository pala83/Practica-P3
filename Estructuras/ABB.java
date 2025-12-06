package Trabajos_Pracicos.Estructuras;

import Trabajos_Pracicos.Estructuras.Printer.Printer;

public class ABB {

    private Nodo raiz;

    public ABB() {
        this.raiz = null;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            return raiz;
        }
        if (valor < raiz.getValor()) {
            raiz.setIzquierdo(insertarRec(raiz.getIzquierdo(), valor));
        } else if (valor > raiz.getValor()) {
            raiz.setDerecho(insertarRec(raiz.getDerecho(), valor));
        }
        return raiz;
    }

    public void print(Nodo root, Printer printer) {
        printer.print(root);
    }
}