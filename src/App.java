import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import Grafo.Grafo;
import Grafo.GrafoD;
import TP5.Solutions.Ej1;
import TP5.Solutions.Ej2;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int vertices = 7;
        Grafo<Integer> grafo = grafoRandom(vertices);
        System.out.println("Grafo random con " + vertices + " vertices: ");
        Iterator<Integer> itV = grafo.obtenerVertices();
        while(itV.hasNext()) {
            Integer vertice = itV.next();
            Iterator<Integer> itA = grafo.obtenerAdyacentes(vertice);
            System.out.print("[" + vertice + "]: ");
            while(itA.hasNext()) {
                System.out.print(itA.next());
                if(itA.hasNext())
                    System.out.print(" -> ");
            }
            System.out.println();
        }
        // Ejercicio 1
        System.out.println("|------------------------------------ Ejercicio 1 ----------------------------------------------------------|");
        //Ej1 ej1 = new Ej1(grafo);
        //System.out.println("Elija 2 vertices para determianr el camino mas largo entre ellos:");
        //System.out.print("Origen: ");
        //int origen = inputValue();
        //System.out.print("Destino: ");
        //int destino = inputValue();
        //System.out.println("Mejor camino: "+ej1.solucion(origen, destino));
        System.out.println("|------------------------------------ Ejercicio 2 ----------------------------------------------------------|");
        Ej2 ej2 = new Ej2(5);
        ej2.imprimirLaberinto();
        System.out.println("|------------------------------------ Ejercicio 3 ----------------------------------------------------------|");
        //System.out.print("Ingrese un numero decimal: ");
        //int num = inputValue();
        //System.out.println("El numero "+num+" en binario es: " + Integer.toBinaryString(num));
        //// Ejercicio 4
        //System.out.println("|------------------------------------ Ejercicio 4 ----------------------------------------------------------|");
        //System.out.print("Ingrese la cantidad de numeros de la secuencia de fribonacci que quiere ver: ");
        //int numFibo = inputValue();
        //util.setNumber(numFibo);
        //System.out.println("Fribonacci de "+numFibo+": "+ util.fribonacci());
        //// Ejercicio 5
        //System.out.println("|------------------------------------ Ejercicio 5 ----------------------------------------------------------|");
        //int[] arregloPosicion = {-3, -1, 0, 2, 4, 6, 10};
        //ArrayUtils arrPos = new ArrayUtils(arregloPosicion);
        //System.out.println("Arreglo ejemplo: A1" + arrPos);
        //System.out.println("Arreglo random: A2" + arr1);
        //System.out.println("Existe el elemento A1["+arrPos.valueInPosition()+"] = "+arrPos.valueInPosition());
        //System.out.println(
        //    arr1.valueInPosition()<0 ? "No existe el elemento A2[x] = x" :
        //                               "Existe el elemento A2["+arr1.valueInPosition()+"] = "+arr1.valueInPosition());
        //// Ejercicio 6
        //System.out.println("|------------------------------------ Ejercicio 6 ----------------------------------------------------------|");
        //System.out.println("Arreglo desordenado 1: A1"+arr2);
        //System.out.println("Arreglo desordenado 2: A2"+arr3);
        //System.out.println("Ordenamiento burbujeo: A1"+toString(arr2.bubbleSort()));
        //System.out.println("Ordenamiento seleccion: A2"+toString(arr3.selectionSort()));
        //// Ejercicio 7
        //System.out.println("|------------------------------------ Ejercicio 7 ----------------------------------------------------------|");
        //int[] arrMerg = randomArr(20);
        //int[] arrQuick = randomArr(20);
        //System.out.println("Arreglo desordenado 1: A1"+toString(arrMerg));
        //System.out.println("Arreglo desordenado 2: A2"+toString(arrQuick));
        //Mergesort merge = new Mergesort();
        //merge.sort(arrMerg);
        //System.out.println("Ordenamiento MergeSort: A1"+toString(arrMerg));
        //Quicksort quick = new Quicksort();
        //quick.sort(arrQuick);
        //System.out.println("Ordenamiento QuickSort: A2"+toString(arrQuick));
    }

    public static GrafoD<Integer> grafoRandom(Integer vertices){
        GrafoD<Integer> grafo = new GrafoD<>();
        List<Integer> tmp = new LinkedList<>();
        for (int i = 1; i <= vertices; i++) {
            tmp.add(i);
            grafo.agregarVertice(i);
        }
        List<Integer> tmpVertices = new LinkedList<>(tmp);
        while(!tmpVertices.isEmpty()){
            Integer vOrigen = tmpVertices.remove((int)(Math.random()*tmpVertices.size()));
            int cantAdy = (int)(Math.random()*tmp.size());
            List<Integer> tmpAdy = new LinkedList<>(tmp);
            tmpAdy.remove(vOrigen);
            for(int j = 0; j < cantAdy && !tmpAdy.isEmpty(); j++){
                Integer randomIndex = (int)(Math.random()*tmpAdy.size());
                Integer vDestino = tmpAdy.remove((int)randomIndex);
                grafo.agregarArco(vOrigen, vDestino, (int)(Math.random()*100));
            }
        }
        return grafo;
    }

    
    
    private static int inputValue() {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Por favor ingrese un número válido:");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}