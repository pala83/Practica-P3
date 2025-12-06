package Trabajos_Pracicos.Estructuras.Printer;

import Trabajos_Pracicos.Estructuras.Nodo;

public class PreOrder implements Printer {

    @Override
    public void print(Nodo root) {
        preOrder(root);
        System.out.println();        
    }

    private void preOrder(Nodo node) {
        if (node != null) {
            System.out.print(node.getValor() + " ");
            preOrder(node.getIzquierdo());
            preOrder(node.getDerecho());
        }
    }
}
