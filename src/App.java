import java.util.Arrays;

import TP2.Sort.Mergesort.Mergesort;
import TP2.Sort.Quicksort.Quicksort;
import TP2.TDA.Tree.Tree;

public class App {
    public static void main(String[] args) throws Exception {
        int[] arreglo1 = {1,2,3,4,5,6};
        int[] arreglo2 = {1,2,3,4,3,6};
        int[] arreglo3 = {1,2,0,4,5};
        int[] arregloPosicion = {-3, -1, 0, 2, 4, 6, 10};
        System.out.println(printArr(arreglo1) + " Esta " + resultOrder(arreglo1));
        System.out.println(printArr(arreglo2) + " Esta " + resultOrder(arreglo2));
        System.out.println(printArr(arreglo3) + " Esta " + resultOrder(arreglo3));
        int busq = 7;
        System.out.print("El elemento "+busq+" en el arreglo " + printArr(arreglo1));
        System.out.println(existe(arreglo1, 0, arreglo1.length-1, busq) ? " Existe" : " No Existe");
        System.out.println("El numero 12 en binario es: " + toBinary(12));
        int numero = 7;
        System.out.println("Fribonacci de "+numero+": "+ imprimirFribonacci(numero));
        int pos = valorEnPosicion(arregloPosicion, 0, arregloPosicion.length-1);
        System.out.println("El arreglo: "+ printArr(arregloPosicion) +" tiene el valor "+pos+" en la posicion del arreglo["+pos+"]="+arregloPosicion[pos]);
        int[] arrDesordenado = {4,6,4,-3,3,5,17,5,3,2,3,5,7,8};
        System.out.println("El arreglo: "+ printArr(arrDesordenado)+ " ordenado por seleccion queda asi: "+printArr(selectionSort(arrDesordenado)));
        System.out.println("El arreglo: "+ printArr(arrDesordenado)+ " ordenado por bubble queda asi: "+printArr(bubbleSort(arrDesordenado)));
        int[] arrMerge = Arrays.copyOf(arrDesordenado, arrDesordenado.length-1);
        Mergesort merge = new Mergesort();
        merge.sort(arrMerge);
        System.out.println("Arreglo ordenado por Mergesort: "+printArr(arrMerge));
        int[] arrQuick = Arrays.copyOf(arrDesordenado, arrDesordenado.length-1);
        Quicksort quick = new Quicksort();
        quick.sort(arrQuick);
        System.out.println("Arreglo ordenado por Quicksort: "+printArr(arrQuick));

        Tree<Integer> arbolito = new Tree<Integer>();
        arbolito.add(200);
        arbolito.add(102);
        arbolito.add(50);
        arbolito.add(103);
        arbolito.add(203);
        arbolito.add(201);
        arbolito.add(300);
        arbolito.printPreOrder();
    }

    public static String printArr(int[] arr){
        String retorno = "[";
        for(int i: arr){
            retorno+=i + " ";
        }
        return retorno+="]";
    }

    public static String resultOrder(int[] arr){
        return checkOrder(arr, 0) ? "ordenado" : "desordenado";
    }

    public static boolean checkOrder(int[] arr, int pointer){
        if(pointer==arr.length-1) return true;
        if(arr[pointer]>arr[pointer+1]) return false;
        return checkOrder(arr, pointer+1);
    }
// Ejercicio 2
    public static boolean existe(int[] arr, int ini, int fin, int buscado){
        if(ini>fin) 
            return false;
        int mid = (ini+fin)/2;
        if(arr[mid]==buscado) 
            return true;
        if(arr[mid]>buscado)
            return existe(arr, ini, mid-1, buscado);
        else
            return existe(arr, mid+1, fin, buscado);
    }
// Ejercicio 3
    public static String toBinary(int num){
        if(num==0)
            return "0";
        if(num==1)
            return "1";
        return toBinary(num/2) + toBinary(num%2);
    }
// Ejercicio 4
    public static String imprimirFribonacci(int num){
        String retorno = "";
        for(int i=0; i<num; i++){
            retorno+=fribonacci(i)+" ";
        }
        return retorno;
    }

    public static int fribonacci(int num){
        if(num<=1){
            return num;
        }
        return fribonacci(num-1) + fribonacci(num-2);
    }
// Ejercicio 5
    public static int valorEnPosicion(int[] arr, int ini, int fin) {
        if (ini > fin)
            return -1;
        int mid = (ini+fin) / 2;
        if (arr[mid] == mid)
            return mid;
        if (arr[mid] > mid)
            return valorEnPosicion(arr, ini, mid - 1);
        return valorEnPosicion(arr, mid + 1, fin);
    }
// Ejercicio 6
    public static int[] selectionSort(int[] arr){
        int[] retorno = Arrays.copyOf(arr, arr.length);
        for(int i=0; i<retorno.length; i++){
            for(int j=i+1; j<retorno.length; j++){
                if(retorno[j]<retorno[i]){
                    int aux = retorno[j];
                    retorno[j] = retorno[i];
                    retorno[i] = aux;
                }
            }
        }
        return retorno;
    }

    public static int[] bubbleSort(int[] arr){
        int[] retorno = Arrays.copyOf(arr, arr.length);
        for(int i=0; i<retorno.length-1; i++){
            for(int j=0; j<retorno.length-1-i; j++){
                if(retorno[j]>retorno[j+1]){
                    int aux = retorno[j];
                    retorno[j] = retorno[j+1];
                    retorno[j+1] = aux;
                }
            }
        }
        return retorno;
    }
}

