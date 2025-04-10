package TP2;

import java.util.ArrayList;
import java.util.List;

import TP1.TDA.Node.DoubleNode.DoubleNode;
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
            System.out.print(c + ", ");  tree1.add(c);
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
        // Ejercicio 4

        System.out.println("Ejercicio 4: Arbol de enteros con nodos nulos");

        Printer<Integer> printer = new Structure<>();
        DoubleNode<Integer> customTree = createTree5();
        System.out.println("Arbol original");
        printer.print(customTree);
        calculateNodeValues(customTree);
        System.out.println("Arbol calculado");
        printer.print(customTree);

        // Ejercicio 5
        Printer<Character> printer5 = new Structure<>();
        DoubleNode<Character> customTree2 = createTree6();
        System.out.println("Arbol original");
        printer5.print(customTree2);
        List<String> palabras = returnLetters(customTree2, 1);
        System.out.println("Arreglo de palabras: " + palabras.toString());
    

    }

    public static List<String> returnLetters(DoubleNode<Character> root, int n){
        List<String> retorno = new ArrayList<>();
        Character[] vocales = {'A', 'E', 'I', 'O', 'U'};
        String temp = "";
        int cont = 0;
        findLetters(root, vocales, retorno, temp, cont, n);
        return retorno;
    }

    public static void findLetters(DoubleNode<Character> root, Character[] vocales, List<String> retorno, String tmp, int cont, int n){
        if(root != null && cont<=n){
            Character aux = root.getValue();
            tmp+=aux;
            for(Character c: vocales){
                if(c.equals(aux)) cont++;
            }
            findLetters(root.getLeft(), vocales, retorno, tmp, cont, n);
            findLetters(root.getRight(), vocales, retorno, tmp, cont, n);
            if(root.getLeft()==null && root.getRight()==null && cont==n){
                retorno.add(tmp);
            }
        }
    }

    public static DoubleNode<Integer> createTree5(){
        DoubleNode<Integer> node14 = new DoubleNode<>(14, null, null);
        DoubleNode<Integer> node7 = new DoubleNode<>(7, null, null);
        DoubleNode<Integer> node_5 = new DoubleNode<>(-5, null, null);
        DoubleNode<Integer> node9 = new DoubleNode<>(9, null, null);
        DoubleNode<Integer> node20 = new DoubleNode<>(20, null, null);
    
        DoubleNode<Integer> node7_5 = new DoubleNode<>(null, node7, node_5);
        DoubleNode<Integer> node14_75 = new DoubleNode<>(null, node14, node7_5);
        DoubleNode<Integer> node_9 = new DoubleNode<>(null, null, node9);
        DoubleNode<Integer> node_9_20 = new DoubleNode<>(null, node_9, node20);
    
        DoubleNode<Integer> root = new DoubleNode<>(null, node14_75, node_9_20);
        
        return root;
    }

    public static DoubleNode<Character> createTree6(){
        DoubleNode<Character> nodeL = new DoubleNode<>('L', null, null);
        DoubleNode<Character> nodeA = new DoubleNode<>('A', null, null);
        DoubleNode<Character> nodeO = new DoubleNode<>('O', null, null);
        DoubleNode<Character> nodeA2 = nodeA;
        DoubleNode<Character> nodeO2 = nodeO;
    
        DoubleNode<Character> nodeN = new DoubleNode<>('N', nodeA, nodeO);
        DoubleNode<Character> nodeA3 = new DoubleNode<>('A', nodeL, nodeN);
        DoubleNode<Character> nodeS = new DoubleNode<>('S', null, nodeA2);
        DoubleNode<Character> nodeI = new DoubleNode<>('I', nodeS, nodeO2);

        DoubleNode<Character> root = new DoubleNode<>('M', nodeA3, nodeI);
        
        return root;
    }

    public static void calculateNodeValues(DoubleNode<Integer> root){
        if(root != null){
            calculateNodeValues(root.getLeft());
            calculateNodeValues(root.getRight());
            if(root.getValue() == null){
                Integer left = root.getLeft() != null ? root.getLeft().getValue() : 0;
                Integer right = root.getRight() != null ? root.getRight().getValue() : 0;
                root.setValue(right-left);
            }
        }
    }

}

