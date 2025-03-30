package TP2;

import TP2.Printer.Printer;
import TP2.Printer.Structure;
import TP2.TDA.Tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Character> tree1 = new Tree<>();
        Tree<Character> tree2 = new Tree<>();
        Character[] A = {'J', 'N', 'B', 'D', 'W', 'E', 'T', 'Y', 'H', 'M', 'F'};
        Character[] B = {'B', 'D', 'E', 'F', 'H', 'J', 'N', 'M', 'T', 'W', 'Y'};
        for(Character c : A){
            tree1.add(c);
        }
        for(Character c : B){
            tree2.add(c);
        }
        System.out.println("Tree 1:");
        Printer<Character> printer1 = new Structure<>();
        tree1.print(printer1);
        System.out.println("Tree 2:");
        //tree2.printTreeStructure();
    }
}
