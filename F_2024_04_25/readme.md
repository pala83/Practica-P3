# Ejercicio 1
- Suponga que se tiene una estructura de hashing donde se almacenan los alumnos de la universidad utilizando el número de documento de los estudiantes como clave. Se desea obtener la lista de alumnos cuyo número de documento se encuentra dentro de cierto rango. ¿Cómo resolvería el servicio planteado sobre la estructura presentada? ¿Es una resolución eficiente? ¿Qué podría hacerse para resolver el servicio de manera más eficiente?

## Solución:
En una estructura de hashing estándar, los datos se encuentran dispersos sin orden físico. Para obtener alumnos con DNI en un rango (ej. entre 10.000 y 20.000), sería necesario recorrer toda la tabla de hashing (todos los baldes y sus listas de colisión) y verificar si cada DNI cae dentro del rango solicitado, ya que la función de hash no preserva la vecindad de las claves.

La solucion tendria una complejidad lineal $O(n)$ siendo $n$ la cantidad de alumnos, se pierde totalmente la ventaja de de acceso directo de la tabla de hash para busquedas exactas con complejidad $O(1)$.

La solucion mas eficiente seria un **Arbol binario de busqueda** que si bien, para busquedas exactas no es tan eficiente como un Hashing, para busquedas por rango puede reducir la complejidad a $O(log_2(n)+k)$ donde $n$ es la totalidad de alumnos y $k$ es la cantidad de alumnos que existen dentro del rango.

---
- ¿Puede haber baldes de rebase en una estructura de hashing separado con crecimiento, si el valor de $\rho$ de diseño es menor a 1 (por ejemplo 0,5)?

## Solucion:
> Baldes de rebase con $\rho < 1$
**Si, puede haber baldes de rebase**, Si un balde específico recibe más claves que su capacidad de ranuras ($r_p$), se generará una lista de rebase (overflow) localmente, independientemente de que el resto de la tabla esté vacía.. El factor de carga $\rho$ es un promedio global y no garantiza que cada balde individual cumpla con la condición de no desbordamiento. Por lo tanto, aunque $\rho < 1$ indica que, en promedio, hay menos claves que ranuras en la tabla, no impide que algunos baldes específicos puedan desbordarse debido a una distribución desigual de las claves.

---
- ¿Bajo qué circunstancias una búsqueda en una tabla de hashing abierto con rebase separado puede tener una eficiencia similar a buscar en una lista vinculada con los elementos?

## Solucion:
> Eficiencia similar a una lista vinculada
Una búsqueda en una tabla de hashing abierto con rebase separado tendrá una eficiencia similar a buscar en una lista vinculada ($O(N)$) en el peor caso de colisiones. Esto ocurre si la función de hashing está mal diseñada o la distribución de datos es tal que todas las claves se asignan al mismo balde. En este escenario, la búsqueda implicaría recorrer la lista de rebase del balde, similar a cómo se recorrería una lista vinculada para encontrar un elemento.

---
- Describa qué es una lista de factoreo y para qué se utiliza.

## Solucion:
Una lista de factoreo es una estructura utilizada para manejar la repetición de claves de búsqueda (claves no únicas). Se utiliza cuando una clave de búsqueda (por ejemplo, "Ciudad de Nacimiento") corresponde a múltiples registros de datos (varias personas nacidas en la misma ciudad).

En lugar de guardar los datos directamente en la ranura (lo que pisaría la información anterior o requeriría duplicar claves), la tabla de hash almacena un puntero a una lista externa (la lista de factoreo) que contiene todos los registros o referencias asociados a esa clave específica.

# Ejercicio 5 (V o F)
a. Si se hace un DFS sobre el grafo G, siempre que se encuentre un arco que me lleve a un vértice por el cuál ya pasó el algoritmo habremos detectado un ciclo.

> **Falso**. Solo se detecta un ciclo si se encuentra un arco back, es decir, un arco que lleva a un vértice que está en el estado "amarillo" (en proceso de exploración). Si el vértice ya está en estado "negro" (completamente explorado), no indica un ciclo.

b. El algoritmo de ordenamiento Quicksort tiene una complejidad computacional de O(n).

> **Falso**. La complejidad promedio de Quicksort es O(n log n), aunque en el peor caso puede ser O(n^2).

c. Se podría adaptar el algoritmo de Dijkstra para encontrar el camino más largo en vez del más corto, entre un vértice y el resto de los vértices del grafo.

> **Falso**. Dijkstra no funciona para encontrar caminos más largos, especialmente en grafos con ciclos. Para encontrar el camino más largo, se utilizaria otro algoritmo basado en la tecnica de Backtracking o programación dinámica.

d. Un hilo de ejecución (thread) puede estar compuesto por varios programas en ejecución (procesos).

> **Falso**. Un hilo (thread) es una unidad de ejecución que pertenece a un proceso. Un hilo no puede estar “compuesto por varios procesos”. Un proceso puede contener varios hilos que comparten el mismo espacio de direcciones y recursos del proceso.

``` mermaid
graph LR
    A((A))
    B((B))
    E((C))
    C((D))
    D((E))
    F((F))
    G((G))
    H((H))
    I((I))
    J((J))
    A <--> B
    A <--> F
    A <--> G
    B <--> F
    B <--> G
    F <--> G
    G <--> C
    G <--> H
    G <--> I
    C <--> H
    C <--> I
    I <--> H
    H <--> D
    H <--> E
    H <--> J
    D <--> E
```