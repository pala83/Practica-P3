package Trabajos_Pracicos.F_2025_08_14;

import java.util.ArrayList;
import java.util.List;

/*
Implemente en JAVA un arbol binario de busqueda cuya clave de busqeda es un int key y que implemente la tecnica de lista de factoreo en los nodos.
Muestre en JAVA la declaracaion de la clase Nodo y de la clase ABB, en particular los atributos de instancia y la implementacion eficiente de solo los siguientes metodos de ABB. Para cada uno indique la complejidad computacional resultante en notacion Big-O

    a) public boolean agregar(int key, T elemento); // agrega un nuevo elemento al arbol y retorna true si la clave es nueva o false si ya existia en el arbol
    b) public List<T> elementosPorNuvel(int nuvel); // retorna una lisa con todos los elementos de un nuvel dado del arbol
    c) public boolean esBalanceado(); // retorna true si el arbol es balanceado o no
*/

public class Ej1<T> {
    private class Nodo {
        private int key;
        private List<T> elemento = new ArrayList<>();
        private Nodo izquierdo;
        private Nodo derecho;

        public Nodo(int key, T elemento) {
            this.key = key;
            this.elemento.add(elemento);
            this.izquierdo = null;
            this.derecho = null;
        }
    }
    private Nodo raiz;

    public Ej1() {
        this.raiz = null;
    }

    public boolean agregar(int key, T elem){
        return agregarRec(raiz, key, elem);
    }

    private boolean agregarRec(Nodo nodo, int key, T elem){
        if(raiz == null){
            raiz = new Nodo(key, elem);
            return true;
        };
        if(nodo.key == key){
            nodo.elemento.add(elem);
            return false;
        }
        if(key < nodo.key)
            return agregarRec(nodo.izquierdo, key, elem);
        if(key > nodo.key)
            return agregarRec(nodo.derecho, key, elem);
        return false; // No deberia llegar aca
    }

    public List<T> elementosPorNivel(int nivel){
        List<T> retorno = new ArrayList<>();
        elementosPorNivelRec(raiz, 0, nivel, retorno);
        return retorno;
    }

    private void elementosPorNivelRec(Nodo nodo, int nivelActual, int nivelBuscado, List<T> retorno){
        if(nivelActual <= nivelBuscado && nodo != null){
            if(nivelActual == nivelBuscado){
                retorno.addAll(nodo.elemento);
            } else {
                elementosPorNivelRec(nodo.izquierdo, nivelActual + 1, nivelBuscado, retorno);
                elementosPorNivelRec(nodo.derecho, nivelActual + 1, nivelBuscado, retorno);
            }
        }
    }

    public boolean esBalanceado(){
        return esBalanceadoRec(raiz) != -1;
    }

    private int esBalanceadoRec(Nodo nodo){
        if(nodo == null){
            return 0;
        }
        int alturaIzq = esBalanceadoRec(nodo.izquierdo);
        if(alturaIzq == -1) return -1;
        int alturaDer = esBalanceadoRec(nodo.derecho);
        if(alturaDer == -1) return -1;

        if(Math.abs(alturaIzq - alturaDer) > 1){
            return -1;
        }
        return Math.max(alturaIzq, alturaDer) + 1;
    }
}
