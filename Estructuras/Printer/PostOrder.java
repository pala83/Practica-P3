package Trabajos_Pracicos.Estructuras.Printer;

import Trabajos_Pracicos.Estructuras.Nodo;

public class PostOrder implements Printer {

    @Override
    public void print(Nodo root) {
        postOrder(root);
        System.out.println();        
    }

    private void postOrder(Nodo node) {
        if (node != null) {
            postOrder(node.getIzquierdo());
            postOrder(node.getDerecho());
            System.out.print(node.getValor() + " ");
        }
    }
}

