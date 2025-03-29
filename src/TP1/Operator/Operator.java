package TP1.Operator;

import java.util.Iterator;

import TP1.TDA.List.LinkedList.LinkedList;

public abstract class Operator<T extends Comparable<T>> {
    protected LinkedList<T> l1;
    protected LinkedList<T> l2;

    public Operator(LinkedList<T> l1, LinkedList<T> l2) {
        this.l1 = l1;
        this.l2 = l2;
    }
    public LinkedList<T> getL1() {
        return l1.clone();
    }

    public LinkedList<T> getL2() {
        return l2.clone();
    }

    public LinkedList<T> result() {
        LinkedList<T> result = new LinkedList<T>();
        if(this.l1.isEmpty() || this.l2.isEmpty()){
            return result;
        }
        for(T value1: this.l1){ // O(n*m)         // Para cada elemento de l1
            Iterator<T> it2 = this.l2.iterator(); // reseteo el iterador de la l2
            boolean exist = false;                
            while(it2.hasNext() && !exist){
                T value2 = it2.next();
                exist = (value1.compareTo(value2) == 0);
            }
            if(condition(exist))
                result.insertFront(value1);
        }   
        return result;
    }
    
    public LinkedList<T> resultWithOrder() {
        LinkedList<T> result = new LinkedList<T>();
        if(this.l1.isEmpty() || this.l2.isEmpty()) return result;
        if(!this.l1.isOrdered()){
            System.out.println("La lista: "+this.l1.toString()+" no esta ordenada");
            return result;
        }
        if(!this.l2.isOrdered()){
            System.out.println("La lista: "+this.l2.toString()+" no esta ordenada");
            return result;
        }
        Iterator<T> it2 = this.l2.iterator();   // No reseteo el iterador de la l2
        T value2 = it2.next();                  // Se recorre una unica vez
        for(T value1: this.l1){ // O(n+m)
            boolean exist = value1.compareTo(value2) == 0;
            while(value1.compareTo(value2) > 0 && it2.hasNext()){
                value2 = it2.next();
                exist = value1.compareTo(value2) == 0;
            }
            if(condition(exist))
                result.insertFront(value1);
        }
        return result;
    }

    public abstract boolean condition(boolean exist);
}
