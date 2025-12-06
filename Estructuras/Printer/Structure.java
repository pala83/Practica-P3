package Trabajos_Pracicos.Estructuras.Printer;

import Trabajos_Pracicos.Estructuras.Nodo;

public class Structure implements Printer{
    @Override
    public void print(Nodo root) {
        if (root == null) {
            System.out.println("<Árbol vacío>");
            return;
        }
        System.out.println(root.getValor());
        printStructure(root.getDerecho(), "", false);
        printStructure(root.getIzquierdo(), "", true);
    }
    
    private void printStructure(Nodo node, String prefix, boolean isTail) {
        if (node == null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + "null");
            return;
        }
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getValor());
        printStructure(node.getDerecho(), prefix + (isTail ? "    " : "│   "), false);
        printStructure(node.getIzquierdo(), prefix + (isTail ? "    " : "│   "), true);
    }
}
