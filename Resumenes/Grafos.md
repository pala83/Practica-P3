# Tipos de grafos
### Grafo Etiquetado
``` mermaid
graph LR
    A((Persona 1))
    B((Ciudad 2))
    E((Persona 2))
    C((Persona 3))
    D((Ciudad 1))
    A -- RESIDE --> B
    E -- CONOCE --> C
    A -- CONOCE --> C
    C -- RESIDE --> D
    E -- RESIDE --> D

```

### Grafo Ponderado
Sus aristas son numeros reales, tambien conocido como **Grafo rotulado o Pesado**

``` mermaid
graph LR
    A((Tandil))
    B((Rauch))
    C((Azul))
    D((Ayacucho))
    E((Las Flores))
    F((Olavarria))
    G((Balcarce))
    A -- 70 <--> B
    A -- 100 <--> C
    B -- 77 <--> C
    B -- 70 <--> D
    B -- 91 <--> E
    C -- 116 <--> E
    C -- 56 <--> F
    D -- 77 <--> A
    G -- 96 <--> D
    G -- 108 <--> A

```

### Grafo Conectado o Conexo
Grafo donde **existe un camino** entre cada par de vertices
> GD Conectado (o conexo)
``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    A --> B
    A --> C
    B --> A
    B --> D
    C --> B
    D --> C
```
> GD no Conectado (o no conexo)
``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    A --> B
    A --> C
    B --> A
    B --> D
    C --> B
```
> GND Conectado (o conexo)
``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    E((E))
    A --> A
    A <--> B
    A <--> C
    A <--> D
    C <--> D
    C <--> E
```
> GND no Conectado (o no conexo)
``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    E((E))
    A --> A
    A <--> B
    C <--> D
    C <--> E
```
# Adyacencia y Caminos
Sea un grafo $G = (V, A)$ donde $V$ es el conjunto de vértices y $A$ el conjunto de aristas.
- Un **camino** es una secuencia de vértices $v_1, v_2, ... , v_k \in V$ tal que existe una arista entre cada par de vértices consecutivos $v_i$ y $v_{i+1}$.
- La **longitud** de un camino:
    - **Sin rotulos**: Cantidad de aristas que contiene $k-1$ donde $k$ es la cantidad de vértices en el camino.
    - **Con rotulos**: Suma de los pesos de las aristas que contiene el camino.
- Dos vértices son **adyacentes** si están conectados por una arista, si $(v_n,v_m)\in A$ entonces $v_n$ y $v_m$ son adyacentes.
- Un vértice $v_n$ es **alcanzable** desde un vértice $v_m$ si existe un camino desde $v_m$ hasta $v_n$.

# Grado de un vértice
El **grado** de un vértice es la cantidad de aristas que inciden en él.
- En un **grafo dirigido (GD)** se distingue entre:
    - **Grado de entrada**: Cantidad de aristas que llegan al vértice.
    - **Grado de salida**: Cantidad de aristas que salen del vértice.
- En un **grafo no dirigido (GND)** el grado es la cantidad de aristas que inciden en el vértice.

# Ciclos
Un **ciclo** es un **camino cerrado sin aristas repetidas** (el origen es igual a su destino). O sea, es un camino sin aristas repetidas $v1, v2, ... , vk$ tal que $v1=vk$.
- **Bucle**: Un bucle es una arista que conecta a un vértice consigo mismo. Es un ciclo de longitud 1.
- Un grafo es **acíclico** si no contiene ciclos, de lo contrario es **cíclico**.
- Un **Arbol** es un GND **conexo y acíclico**.

> GD Cíclico
> Con ciclo A → B → D → C → B
``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    A --> B
    A --> C
    B --> A
    B --> D
    C --> B
    D --> C
```
> GD Acíclico
``` mermaid
graph LR
    A((A))
    B((B))
    C((C))
    D((D))
    E((E))
    A --> B
    A --> D
    C --> A
    C --> E
```