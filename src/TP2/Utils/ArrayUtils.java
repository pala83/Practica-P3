package TP2.Utils;

import java.util.Arrays;

public class ArrayUtils {
    private int[] arr;

    public ArrayUtils(int[] arr) {
        this.arr = Arrays.copyOf(arr, arr.length-1);
    }

    @Override
    public String toString() {
        String retorno = "[";
        for(int i=0; i<this.arr.length; i++){
            retorno+=this.arr[i];
            if(i!=this.arr.length-1)
                retorno+=", ";
        }
        return retorno+="]";
    }
// Ejercicio 1
    public boolean isOrdered(){
        return this._isOrdered(this.arr, 0);
    }

    private boolean _isOrdered(int[] arr, int pointer){
        if(pointer==arr.length-1)
            return true;
        if(arr[pointer]>arr[pointer+1])
            return false;
        return _isOrdered(arr, pointer+1);
    }
// Ejercicio 2
    public int exist(int value){
        return this._exist(this.arr, 0, this.arr.length-1, value);
    }

    private int _exist(int[] arr, int ini, int fin, int value){
        if(ini>fin) 
            return -1;
        int mid = (ini+fin)/2;
        if(arr[mid]==value) 
            return mid;
        if(arr[mid]>value)
            return _exist(arr, ini, mid-1, value);
        else
            return _exist(arr, mid+1, fin, value);
    }
// Ejercicio 5
    public int valueInPosition(){
        return this._valueInPosition(this.arr, 0, this.arr.length-1);
    }

    private int _valueInPosition(int[] arr, int ini, int fin) {
        if (ini > fin)
            return -1;
        int mid = (ini+fin) / 2;
        if (arr[mid] == mid)
            return mid;
        if (arr[mid] > mid)
            return _valueInPosition(arr, ini, mid - 1);
        return _valueInPosition(arr, mid + 1, fin);
    }
// Ejercicio 6
    public int[] selectionSort(){
        int[] retorno = Arrays.copyOf(this.arr, this.arr.length);
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

    public int[] bubbleSort(){
        int[] retorno = Arrays.copyOf(this.arr, this.arr.length);
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
