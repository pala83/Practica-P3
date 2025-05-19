package TP2.Printer;

import TP2.TDA.Node.DoubleNode.DoubleNode;

public class Structure<T> implements Printer<T>{
    @Override
    public void print(DoubleNode<T> root) {
        if (root == null) {
            System.out.println("<Árbol vacío>");
            return;
        }
        System.out.println(root.getValue());
        printStructure(root.getRight(), "", false);
        printStructure(root.getLeft(), "", true);
    }
    
    private void printStructure(DoubleNode<T> node, String prefix, boolean isTail) {
        if (node == null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + "null");
            return;
        }
        System.out.println(prefix + (isTail ? "└── " : "├── ") + node.getValue());
        printStructure(node.getRight(), prefix + (isTail ? "    " : "│   "), false);
        printStructure(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
    }
}
