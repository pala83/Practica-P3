# Ejercicio 2
Considerando la implementación de la lista vinculada realizada en el ejercicio anterior, comparar la complejidad computacional contra un array en las siguientes operaciones:
1. Insertar al principio.
``` java
public void insertFront(T info) {
    SimpleNode<T> tmp = new SimpleNode<T>(info, this.first); // O(1)
    this.first = tmp; // O(1)
    this.size++; // O(1)
}
```
> Complejidad: O(1) + O(1) + O(1) = O(1)

2. Buscar un elemento en una posicion.
``` java
public T get(int index){
    if(index < 0 || index >= this.size){ // O(1)
        return null;
    }
    SimpleNode<T> current = this.first; // O(1)
    int i = 0;
    while (current != null && i < index) { // T(n) = n-1+1
        current = current.getNext();
        i++;
    }
    return current==null ? null : current.getInfo(); // O(1)
}
```
> Supongo n como la cantidad de nodos de la lista: O(1) + O(1) + $\sum_{n=1}^{n} c1$ + O(1) = n + c1 = O(n)

3. Determinar la cantidad de elementos
``` java
public int size() {
    return this.size; // O(1)
}
```
> Complejidad: O(1)

4. Borrar un elemento de una posicion determinada.
``` java
public void remove(int index){
    if(index < 0 || index >= this.size){ // O(1)
        return;
    }
    if(index == 0){ // O(1)
        this.first = this.first.getNext();
        this.size--;
    }
    else{
        SimpleNode<T> current = this.first;
        int i = 0;
        while (current.getNext() != null && i < index-1) { // O(n)
            current = current.getNext();
            i++;
        }
        if(current.getNext() != null){ // O(1)
            current.setNext(current.getNext().getNext());
            this.size--;
        }
    }
}
```

# Ejercicio 4
A partir de la clase Lista implementada en el ejercicio 1, implemente el patrón **iterator-iterable**, para que la lista sea iterable. ¿Existe alguna ventaja computacional a la hora de recorrer la lista de principio a fin si se cuenta con un iterador?
- Recorrido de la lista sin **iterator-iterable**
``` java
for(int i=0; i<list.size(); i++){ // O(n)
    System.out.println(list.get(i)); // O(n)
}
```
> Complejidad en el peor de los casos $O(n²)$
- Recorrido de la lista luego de la implementacion de **iterator-iterable**
``` java
for(Integer elem: lista){ // O(n)
    System.out.println(elem); // O(1)
}
```
> Complejidad en el peor de los casos O(1)