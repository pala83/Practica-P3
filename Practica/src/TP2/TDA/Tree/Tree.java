package TP2.TDA.Tree;

import java.util.ArrayList;
import java.util.List;
import TP1.TDA.Node.DoubleNode.DoubleNode;
import TP2.Operations.Aggregation.Aggregation;
import TP2.Printer.Printer;

public class Tree<T extends Comparable<T>> {
    private DoubleNode<T> root;

    public Tree(){
        this.root = null;
    }

    public void add(T value){ // Complejidad: O(log n)
        if(this.root == null)
            this.root = new DoubleNode<T>(value, null, null);
        else
            this.add(this.root, value);
    }

    private void add(DoubleNode<T> current, T value){
        if(current.getValue().compareTo(value) > 0){
            if(current.getLeft() == null){
                DoubleNode<T> temp = new DoubleNode<T>(value, null, null);
                current.setLeft(temp);
            } 
            else{
                add(current.getLeft(), value);
            }
        }
        else if(current.getValue().compareTo(value) < 0){
            if(current.getRight() == null){
                DoubleNode<T> temp = new DoubleNode<T>(value, null, null);
                current.setRight(temp);
            }
            else{
                add(current.getRight(), value);
            }
        }
    }
    
    public boolean hasElem(T elem){ // Complejidad: O(log n)
        return hasElem(elem, this.root);
    }
    
    private boolean hasElem(T elem, DoubleNode<T> node){
        if(node == null)
            return false;
        if(node.getValue().compareTo(elem) == 0)
            return true;
        else if(node.getValue().compareTo(elem) > 0)
            return hasElem(elem, node.getLeft());
        else
            return hasElem(elem, node.getRight());
    }

    public boolean isEmpty(){ // Complejidad: O(1)
        return this.root == null;
    }
    
    public boolean delete(T elem){ // Complejidad: O(log n)
        return delete(elem, this.root);
    }
    
    private boolean delete(T elem, DoubleNode<T> node){
        if(node == null)
            return false;
        if(node.getValue().compareTo(elem) == 0){
            if(node.getLeft() == null && node.getRight() == null){ // Caso 1: Nodo hoja
                node = null;                                       // Simplemente borro el nodo
            }
            else if(node.getLeft() != null && node.getRight() == null){ // Caso 2: Solo tiene hijo izquierdo
                node = node.getLeft();
            }
            else if(node.getLeft() == null && node.getRight() != null){ // Caso 2: Solo tiene hijo derecho
                node = node.getRight();
            }
            else{                                               // Caso 3: Tiene ambos hijos
                DoubleNode<T> temp = getMax(node.getLeft());    // Encuentro el maximo del subarbol izquierdo (El mayor de los menores, NMDSI)
                node.setValue(temp.getValue());                 // Reemplazo el valor del nodo a borrar por el maximo del subarbol izquierdo
                delete(temp.getValue(), node.getLeft());        // Borro el maximo del subarbol izquierdo
            }
            return true;
        }
        else if(node.getValue().compareTo(elem) > 0)
            return delete(elem, node.getLeft());
        else
            return delete(elem, node.getRight());
    }

    private DoubleNode<T> getMax(DoubleNode<T> node){
        if(node.getRight() == null)
            return node;
        return getMax(node.getRight());
    }
    
    public int getHeight(){ // Complejidad: O(n)
        return getHeight(this.root);
    }
    
    private int getHeight(DoubleNode<T> node){
        if(node == null)
            return 0;
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public List<T> getLongestBranch(){ // Complejidad: O(n)
        return getLongestBranch(this.root, new ArrayList<T>());
    }

    private List<T> getLongestBranch(DoubleNode<T> node, List<T> branch){
        if(node == null)
            return branch;
        branch.add(node.getValue());
        List<T> leftBranch = getLongestBranch(node.getLeft(), new ArrayList<T>(branch));
        List<T> rightBranch = getLongestBranch(node.getRight(), new ArrayList<T>(branch));
        return leftBranch.size() > rightBranch.size() ? leftBranch : rightBranch;
    }
    
    public List<T> getFrontera(){ // Complejidad: O(n)
        return getFrontera(this.root, new ArrayList<T>());
    }
    
    private List<T> getFrontera(DoubleNode<T> node, List<T> frontera){
        if(node == null)
            return frontera;
        if(node.getLeft() == null && node.getRight() == null)
            frontera.add(node.getValue());
        getFrontera(node.getLeft(), frontera);
        getFrontera(node.getRight(), frontera);
        return frontera;
    }

    public T getMaxElem(){ // Complejidad: O(n)
        return getMaxElem(this.root);
    }
    
    private T getMaxElem(DoubleNode<T> node){
        if(node == null)
            return null;
        if(node.getRight() == null)
            return node.getValue();
        return getMaxElem(node.getRight());
    }
    
    public List<T> getElemAtLevel(int level){ // Complejidad: O(n)
        return getElemAtLevel(level, this.root, 0, new ArrayList<T>());
    }

    private List<T> getElemAtLevel(int level, DoubleNode<T> node, int currentLevel, List<T> elements){
        if(node == null)
            return elements;
        if(currentLevel == level)
            elements.add(node.getValue());
        getElemAtLevel(level, node.getLeft(), currentLevel + 1, elements);
        getElemAtLevel(level, node.getRight(), currentLevel + 1, elements);
        return elements;
    }

    public List<T> getHeigherLeaves(T value){ // Complejidad: O(n)
        return getHeigherLeaves(this.root, new ArrayList<T>(), value);
    }

    private List<T> getHeigherLeaves(DoubleNode<T> node, List<T> leaves, T value){
        if(node.getLeft() == null && node.getRight() == null){
            if(node.getValue().compareTo(value) > 0)
                leaves.add(node.getValue());
        }
        else{
            if(node.getLeft() != null)
                getHeigherLeaves(node.getLeft(), leaves, value);
            if(node.getRight() != null)
                getHeigherLeaves(node.getRight(), leaves, value);
        }
        return leaves;
    }

    public void print(Printer<T> printer){
        printer.print(this.root);
    }

    // Ejercicio 2
    public T aggregation(Aggregation<T> aggregator){
        return aggregator.aggregate(this.root);
    }
}
