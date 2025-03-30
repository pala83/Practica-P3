package TP2;

import TP2.Operations.Aggregation.Aggregation;
import TP2.Operations.Aggregation.Summ;
import TP2.Printer.Printer;
import TP2.Printer.Structure;
import TP2.TDA.Tree.Tree;

public class Main {
    public static void main(String[] args) {
        Tree<Character> tree1 = new Tree<>();
        Tree<Character> tree2 = new Tree<>();
        Tree<Integer> tree3 = new Tree<>();
        Character[] A = {'J', 'N', 'B', 'D', 'W', 'E', 'T', 'Y', 'H', 'M', 'F'};
        Character[] B = {'B', 'D', 'E', 'F', 'H', 'J', 'N', 'M', 'T', 'W', 'Y'};
        for(Character c : A){
            tree1.add(c);
        }
        for(Character c : B){
            tree2.add(c);
        }
        for(Character c : A){
            Integer i = Integer.valueOf(c);
            tree3.add(i);
        }
        // Ejercicio 1
        System.out.println("Tree 1: Arbol de caracteres");
        Printer<Character> printer1 = new Structure<>();
        tree1.print(printer1);
        System.out.println("Tree 2: Arbol de Integers");
        Printer<Integer> printer2 = new Structure<>();
        tree3.print(printer2);
        // Ejercicio 2
        Aggregation<Integer> add = new Summ();
        System.out.println( "Suma total de los elementos del arbol 2: "+ tree3.aggregation(add));
        // Ejercicio 3
        Tree<Integer> ej3 = new Tree<>();
        Integer[] ej3Data = {6,2,1,4,10,8,7,9,11};
        for(Integer i : ej3Data){
            ej3.add(i);
        }
        System.out.println("Ejercicio 3: Arbol de enteros");
        Printer<Integer> printer3 = new Structure<>();
        ej3.print(printer3);
        int k = 8;
        System.out.println("Elementos superiores que "+k+": "+ej3.getHeigherLeaves(k).toString());
    }
}
