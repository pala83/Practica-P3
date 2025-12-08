# Grafo de ejemplo:

> DISCLAIMER: Es total y absolutamente imposible que te salga resolver este algoritmo sin conocer su mecanismo previamente.

``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    E((E))
    F((F))
    G((G))
    H((H))
    I((I))
    J((J))
    A --- B
    A --- G
    A --- F
    B --- F
    B --- G
    F --- G
    G --- C
    G --- H
    G --- I
    C --- H
    C --- I
    I --- H
    H --- D
    H --- E
    H --- J
    D --- E
```
Arbol de arcos Tree resultante:
``` mermaid
graph LR
    A(("A (1|1)"))
    B(("B (2|1)"))
    G(("G (3|1)"))
    F(("F (4|1)"))
    C(("C (5|3)"))
    H(("H (6|3)"))
    I(("I (7|3)"))
    D(("D (8|6)"))
    E(("E (9|6)"))
    J(("J (10|10)"))
    A --> B --> G --> C --> H --> I
    G --> F
    H --> D --> E
    H --> J
```

Puntos de articulacion (explicacion abajo):
- **G** : $low[C] (3) \geq disc[G] (3)$
- **H** : $low[D] (6) \geq disc[H] (6)$

## Explicacion del algoritmo
La resolucion del algoritmo se basa en un DFS completo (con visitados y tiempos de descubrimiento) pero modificado e inspirado en el [algoritmo de Tarjan para componentes fuertemente conexas](https://en.wikipedia.org/wiki/Tarjan%27s_strongly_connected_components_algorithm).

Los siguientes conceptos son claves para entender el algoritmo:
1. **Tiempo de descubrimiento (disc)**: Es el tiempo en el que un nodo es descubierto por primera vez durante el DFS.
2. **Low value (low)**: Es el tiempo de descubrimiento del nodo con el menor tiempo de descubrimiento que es alcanzable desde el nodo actual, incluyendo a sí mismo.
    - Si el nodo actual apunta a un **arco back** su **Low value** sera definido como el minimo entre el **Low value** del nodo actual y **Disc value** del nodo al que apunta el arco back `low[nodo_actual] = Math.min(low[nodo_actual], disc[nodo_dstino])`.
3. **Arco back**: Aparece cuando apuntamos a un nodo que ya fue visitado y que no es el padre del nodo actual en el DFS.
4. **Hijo**: Es el contador de hijos directos de un nodo en el **arbol de arcos Tree** del grafo.
5. **Punto de articulacion**: Un nodo es punto de articulacion si al removerlo (junto con sus arcos) del grafo, el grafo se divide en dos o mas componentes conexas.

### Casos para definir un punto de articulacion
**Caso 1**: Si es el nodo es la raiz del DFS y tiene mas de un hijo.
``` java
if (parent[nodo] == -1 && hijo > 1)
    esPuntoArticulacion[nodo] = true;
```

``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    A --- B
    A --- C
    B --- D
```
Se observa que **$A$ es punto de articulacion**, ya que al generar el recorrido DFS, el arbol de arcos generado es el siguiente:
``` mermaid
graph TD
    A(("A (1|1)"))
    B(("B (2|1)"))
    C(("C (3|1)"))
    D(("D (4|1)"))

    A --> B
    B --> C
    A --> D
```
---

**Caso 2**: Si no es la raiz del DFS y existe un hijo tal que $low[hijo] \geq disc[nodo\_actual]$.
``` java
if (parent[nodo] != -1 && low[adyacente] >= disc[nodo])
    esPuntoArticulacion[nodo] = true;
```
``` mermaid
graph LR
    A(("A (1|1)"))
    B(("B (2|1)"))
    C(("C (3|1)"))
    D(("D (4|4)"))
    A --- B
    A --- C
    B --- C
    C --- D
```
Se observa que **$C$ es punto de articulacion** ya que $low[D] (4) \geq disc[C] (3)$ y $A$ solo tiene 1 **hijo**.
``` mermaid
graph TD
    A(("A (1|1)"))
    B(("B (2|1)"))
    C(("C (3|1)"))
    D(("D (4|4)"))
    A --> B
    B --> C
    C -. arco back .-> A
    C --> D
```