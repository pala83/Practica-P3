import java.util.Random;

import TP1.Operator.Difference;
import TP1.Operator.Intersection;
import TP1.TDA.List.LinkedList.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList<Integer> l1 = listaRandom();
        LinkedList<Integer> l2 = listaRandom();
        LinkedList<Integer> o1 = listaRandomOrdenada();
        LinkedList<Integer> o2 = listaRandomOrdenada();
        System.out.println("|----------------- Test listas desordenadas -----------------|");
        System.out.println(testDiferencia(l1, l2, false));
        System.out.println("|----------------- Test listas ordenadas --------------------|");
        System.out.println(testDiferencia(o1, o2, true));

        System.out.println("|----------------- Test listas desordenadas -----------------|");
        System.out.println(testInterseccion(l1, l2, false));
        System.out.println("|----------------- Test listas ordenadas --------------------|");
        System.out.println(testInterseccion(o1, o2, true));
    }

    public static LinkedList<Integer> listaRandom(){
        Random rand = new Random();
        LinkedList<Integer> lista = new LinkedList<>();
        for(int i=0; i<rand.nextInt(50); i++){
            lista.insertFront(rand.nextInt(100));
        }
        return lista;
    }

    public static LinkedList<Integer> listaRandomOrdenada(){
        Random rand = new Random();
        LinkedList<Integer> lista = new LinkedList<>();
        int value = 100;
        for(int i=0; i<rand.nextInt(50); i++){
            value-= rand.nextInt(25);
            lista.insertFront(value);
        }
        return lista;
    }

    public static String testDiferencia(LinkedList<Integer> l1, LinkedList<Integer> l2, boolean conOrden){
        Difference<Integer> diff = new Difference<>(l1, l2);
        StringBuilder sb = new StringBuilder();
        sb.append("Lista 1: " + l1+"\n");
        sb.append("Lista 2: " + l2+"\n");
        if(conOrden)
            sb.append("Diferencia ordenada: " + diff.resultWithOrder().imprimirInverso());
        else
            sb.append("Diferencia: " + diff.result().imprimirInverso());
        return sb.toString();
    }

    public static String testInterseccion(LinkedList<Integer> l1, LinkedList<Integer> l2, boolean conOrden){
        Intersection<Integer> intersect = new Intersection<>(l1, l2);
        StringBuilder sb = new StringBuilder();
        sb.append("Lista 1: " + l1+"\n");
        sb.append("Lista 2: " + l2+"\n");
        if(conOrden)
            sb.append("Interseccion ordenada: " + intersect.resultWithOrder().imprimirInverso());
        else
            sb.append("Interseccion: " + intersect.result().imprimirInverso());
        return sb.toString();
    }
}

