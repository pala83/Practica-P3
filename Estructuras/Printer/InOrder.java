package Trabajos_Pracicos.Estructuras.Printer;

import Trabajos_Pracicos.Estructuras.Nodo;

public class InOrder implements Printer {

    @Override
    public void print(Nodo root) {
        inOrder(root);
        System.out.println();        
    }

    private void inOrder(Nodo node) {
        if (node != null) {
            inOrder(node.getIzquierdo());
            System.out.print(node.getValor() + " ");
            inOrder(node.getDerecho());
        }
    }
}

