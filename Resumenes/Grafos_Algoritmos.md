# Clasificacion de Arcos
### Arcos Tree (arcos de arbol)
- Son los arcos que encuentran vertices nuevos (Blancos)
- Forman el arbol de DFS
### Arcos Back (arcos de retroceso)
- Son los arcos que encuentran vertices amarillos (de amarillo a amarillo)
- Encontrar un **Arco back** determina que el **grafo es cíclico**.
- *Un grafo es acíclico si y solo si el recorrido DFS no logra encontrar arcos back*.

# DFS (Depth-First Search)
> Es una generalizacion del recorrido PREORDER en un arbol.

Necesitamos:
- Un **Grafo** representado con listas de adyacencia y cada vertice tendra su estado en **"blanco"**.
- Un **HashMap<Vertice, Trazo>** que llevara el seguimiento del trazo del DFS.
- Un **Trazo** que contiene:
  - **Estado**: blanco, amarillo, negro.
  - Tiempo de descubrimiento **(TI)** y tiempo de finalización **(TF)**.
- Complejidad: $O(|V| + |A|)$

``` java
public void DFS(){
    int tiempo = 0;
    for(Map.Entry<Integer, Trazo> entry : trazo.entrySet()) {
        if(entry.getValue().getEstado().equals("blanco")) {
            tiempo = this.DFSVisit(entry.getKey(), tiempo);
        }
    }
}

private int DFSVisit(Integer vertice, int tiempo){
    Trazo trazoVertice = this.trazo.get(vertice);
    trazoVertice.setEstado("amarillo");
    tiempo++;
    trazoVertice.setTiempoI(tiempo);
    Iterator<Integer> ady = this.grafo.obtenerAdyacentes(vertice);
    while(ady.hasNext()) {
        Integer adyacente = ady.next();
        Trazo trazoAdyacente = this.trazo.get(adyacente);
        if(trazoAdyacente.getEstado().equals("blanco")) {
            tiempo = this.DFSVisit(adyacente, tiempo);
        }
        // else
        // if(trazoAdyacente.getEstado().equals("amarillo"))
        // print("Hay un ciclo!")
    }
    trazoVertice.setEstado("negro");
    tiempo++;
    trazoVertice.setTiempoF(tiempo);
    return tiempo;
}
```

# BFS (Breadth-First Search)
> Es una generalizacion del recorrido POR NIVEL en un arbol.
Necesitamos:
- Un **Grafo** representado con listas de adyacencia y cada vertice tendra su estado en **"blanco"**.
- Un **HashMap<Vertice, String>** que llevara el seguimiento del estado del vertice en el BFS.
- Una **Cola** para manejar los vertices a visitar.
- Complejidad: $O(|V| + |A|)$

### Casos de uso
- **Componente conexa**: recorrer todos los vertices alcanzables desde un vertice inicial.
- **Camino de menor longitud**: encontrar el camino mas corto entre dos vertices en un grafo no ponderado.


``` java
public void forest(){
    int tiempo = 0;
    for(Map.Entry<Integer, Trazo> entry : this.trazo.entrySet()) {
        if(entry.getValue().getEstado().equals("No visitado")) {
            tiempo = this.BFSVisit(entry.getKey(), tiempo, new LinkedList<>());
        }
    }
}

private int BFSVisit(Integer vertice, int tiempo, Queue<Integer> queue){
    Trazo trazoVertice = this.trazo.get(vertice);
    trazoVertice.setEstado("Visitado");
    tiempo++;
    trazoVertice.setTiempoI(tiempo);
    queue.add(vertice);
    while (!queue.isEmpty()) {
        Integer actual = queue.remove();
        Iterator<Integer> ady = this.grafo.obtenerAdyacentes(actual);
        while (ady.hasNext()) {
            Integer adyacente = ady.next();
            Trazo tmpAdy = this.trazo.get(adyacente);
            if (tmpAdy.getEstado().equals("No visitado")) {
                tmpAdy.setEstado("Visitado");
                tiempo++;
                tmpAdy.setTiempoI(tiempo);
                queue.add(adyacente);
            }
        }
        this.trazo.get(actual).setTiempoF(tiempo);
    }
    return tiempo;
}
```