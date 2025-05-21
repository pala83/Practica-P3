<<<<<<< HEAD
import java.util.Scanner;

import TP2.Sort.Mergesort.Mergesort;
import TP2.Sort.Quicksort.Quicksort;
import TP2.Utils.ArrayUtils;
import TP2.Utils.Utils;

public class App {
    public static void main(String[] args) throws Exception {
        // Ejercicio 1
        System.out.println("|------------------------------------ Ejercicio 1 ----------------------------------------------------------|");
        ArrayUtils arr1 = new ArrayUtils(randomOrderArr(20, -10));
        ArrayUtils arr2 = new ArrayUtils(randomArr(15));
        ArrayUtils arr3 = new ArrayUtils(randomArr(20));
        System.out.println("El arreglo: "+arr1+ " Esta " + isOrder(arr1.isOrdered()));
        System.out.println("El arreglo: "+arr2+ " Esta " + isOrder(arr2.isOrdered()));
        System.out.println("El arreglo: "+arr3+ " Esta " + isOrder(arr3.isOrdered()));
        System.out.println("|------------------------------------ Ejercicio 2 ----------------------------------------------------------|");
        // Ejercicio 2
        System.out.println("Arreglo: "+arr1);
        System.out.print("Buscar la posicion del elemento: ");
        int busq = inputValue();
        System.out.println("El elemento "+busq+ (arr1.exist(busq) != -1 ? " Existe en la posicion: " + arr1.exist(busq) : " No Existe"));
        // Ejercicio 3
        System.out.println("|------------------------------------ Ejercicio 3 ----------------------------------------------------------|");
        System.out.print("Ingrese un numero decimal: ");
        int num = inputValue();
        Utils util = new Utils(num);
        System.out.println("El numero "+num+" en binario es: " + util.toBinary());
        // Ejercicio 4
        System.out.println("|------------------------------------ Ejercicio 4 ----------------------------------------------------------|");
        System.out.print("Ingrese la cantidad de numeros de la secuencia de fribonacci que quiere ver: ");
        int numFibo = inputValue();
        util.setNumber(numFibo);
        System.out.println("Fribonacci de "+numFibo+": "+ util.fribonacci());
        // Ejercicio 5
        System.out.println("|------------------------------------ Ejercicio 5 ----------------------------------------------------------|");
        int[] arregloPosicion = {-3, -1, 0, 2, 4, 6, 10};
        ArrayUtils arrPos = new ArrayUtils(arregloPosicion);
        System.out.println("Arreglo ejemplo: A1" + arrPos);
        System.out.println("Arreglo random: A2" + arr1);
        System.out.println("Existe el elemento A1["+arrPos.valueInPosition()+"] = "+arrPos.valueInPosition());
        System.out.println(
            arr1.valueInPosition()<0 ? "No existe el elemento A2[x] = x" :
                                       "Existe el elemento A2["+arr1.valueInPosition()+"] = "+arr1.valueInPosition());
        // Ejercicio 6
        System.out.println("|------------------------------------ Ejercicio 6 ----------------------------------------------------------|");
        System.out.println("Arreglo desordenado 1: A1"+arr2);
        System.out.println("Arreglo desordenado 2: A2"+arr3);
        System.out.println("Ordenamiento burbujeo: A1"+toString(arr2.bubbleSort()));
        System.out.println("Ordenamiento seleccion: A2"+toString(arr3.selectionSort()));
        // Ejercicio 7
        System.out.println("|------------------------------------ Ejercicio 7 ----------------------------------------------------------|");
        int[] arrMerg = randomArr(20);
        int[] arrQuick = randomArr(20);
        System.out.println("Arreglo desordenado 1: A1"+toString(arrMerg));
        System.out.println("Arreglo desordenado 2: A2"+toString(arrQuick));
        Mergesort merge = new Mergesort();
        merge.sort(arrMerg);
        System.out.println("Ordenamiento MergeSort: A1"+toString(arrMerg));
        Quicksort quick = new Quicksort();
        quick.sort(arrQuick);
        System.out.println("Ordenamiento QuickSort: A2"+toString(arrQuick));
    }

    public static String printArr(int[] arr){
        String retorno = "[";
        for(int i: arr){
            retorno+=i + " ";
        }
        return retorno+="]";
    }

    public static int inputValue(){
        try {
            Scanner scanner = new Scanner(System.in);
            int value = scanner.nextInt();
            return value;
        } catch (Exception e) {
            return -1;
        }
    }

    public static String isOrder(boolean ordenado){
        return ordenado ? "ordenado" : "desordenado";
    }

    public static int[] randomArr(int n){
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = (int)(Math.random()*100);
        }
        return arr;
    }

    public static int[] randomOrderArr(int n, int inic){
        int[] arr = new int[n];
        int value = inic;
        for(int i=0; i<n; i++){
            arr[i] = value+= (int)(Math.random()*5);
        }
        return arr;
    }

    public static String toString(int[] arr) {
        String retorno = "[";
        for(int i=0; i<arr.length; i++){
            retorno+=arr[i];
            if(i!=arr.length-1)
                retorno+=", ";
        }
        return retorno+="]";
    }

}

=======

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import TP4.Solutions.BFS;
import TP4.Solutions.CaminoMayorLongitud;
import TP4.Solutions.Caminos;
import TP4.Solutions.DFS;
import TP4.Solutions.ExisteCiclo;
import TP4.Solutions.Trazo;
import TP4.TDA.Arco.Arco;
import TP4.TDA.Grafo.Grafo;
import TP4.TDA.Grafo.GrafoD;

public class App {

    public static void main(String[] args) {
        Grafo<Double> grafo = new GrafoD<>();
        cargarGrafo(grafo, 7);  
        System.out.println("Grafo random de "+grafo.cantidadVertices()+" vertices :");
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while(vertices.hasNext()){
            Integer vertice = vertices.next();
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
            System.out.println("V: " + vertice + " -> " + imprimirVertices(adyacentes));
        }
        System.out.println("Arcos: "+grafo.cantidadArcos()+ " en total");
        Iterator<Arco<Double>> arcos = grafo.obtenerArcos();
        while(arcos.hasNext()){
            Arco<Double> arco = arcos.next();
            System.out.println("A: " + arco.getVerticeOrigen() + " -> " + arco.getVerticeDestino() + " : " + arco.getEtiqueta());
        }
        // Ejercicio 2
        DFS dfs = new DFS(grafo);
        dfs.forest();
        System.out.println("DFS:");
        for(Entry<Integer, Trazo> entry : dfs.getTrazo().entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        dfs.cleanTrazo();

        BFS bfs = new BFS(grafo);
        bfs.forest();
        System.out.println("BFS:");
        for(Entry<Integer, Trazo> entry : bfs.getTrazo().entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        bfs.cleanTrazo();
        //Ejercicio 3
        ExisteCiclo existeCiclo = new ExisteCiclo(grafo);
        existeCiclo.forest();
        System.out.println("Ciclo: " + existeCiclo.getCiclo());
        existeCiclo.clearCiclo();

        //Ejercicio 4
        CaminoMayorLongitud caminoMayorLongitud = new CaminoMayorLongitud(grafo);
        caminoMayorLongitud.buscarCamino(2, 5);
        System.out.println("Camino mayor longitud: " + caminoMayorLongitud.getMejorCamino());
        caminoMayorLongitud.cleanMejorCamino();

        //Ejercicio 5
        Caminos caminos = new Caminos(grafo);
        List<List<Integer>> result = caminos.obtenerCaminos(2, 5);
        System.out.println("Caminos: " + result);
    }

    public static void cargarGrafo(Grafo<Double> grafo, int vertices) {
        List<Integer> tmp = new LinkedList<>();
        for (int i = 1; i <= vertices; i++) {
            tmp.add(i);
            grafo.agregarVertice(i);
        }
        List<Integer> tmpVertices = new LinkedList<>(tmp);
        while(!tmpVertices.isEmpty()){
            Integer vOrigen = tmpVertices.remove((int)(Math.random()*tmpVertices.size()));
            int cantAdy = (int)(Math.random()*tmp.size());
            for(int j = 0; j < cantAdy; j++){
                Integer vDestino = tmp.get(j);
                if(!vOrigen.equals(vDestino))
                    grafo.agregarArco(vOrigen, vDestino, Math.random()*100);
            }
        }
    }

    public static String imprimirVertices(Iterator<Integer> it) {
        Iterator<Integer> tmp = it;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(tmp.hasNext()){
            sb.append(tmp.next());
            if(tmp.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void cargarGrafoAciclico(GrafoD<Double> grafo, int vertices) {
        List<Integer> listaVertices = new LinkedList<>();
        for (int i = 1; i <= vertices; i++) {
            listaVertices.add(i);
            grafo.agregarVertice(i);
        }
        for (int i = 0; i < listaVertices.size(); i++) {
            Integer origen = listaVertices.get(i);
            for (int j = i + 1; j < listaVertices.size(); j++) {
                Integer destino = listaVertices.get(j);
                grafo.agregarArco(origen, destino, Math.random() * 100);
            }
        }
    }
}
>>>>>>> TP4
