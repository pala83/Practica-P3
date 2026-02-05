# Ejercicio 3
### Estrategia Greedy para organizar cajas en columnas
La verdad no tengo ni idea y no tengo ganas de ver cual es mejor, pero existen las siguientes 3 opciones:
- Ordenar las cajas por $RESISTENCIA + PESO$ <-- Esta es la que implemente
- Ordenar las cajas por $RESISTENCIA / PESO$
- Ordenar las cajas por $PESO / RESISTENCIA$
Los resultados dan mas o menos simialr, pero no tengo tiempo ni ganas de analizar todas las combinaciones de cajas para ver cual es mejor.

Lo que controla que no se rompan las cajas es una variable que va tomando siempre el minimo entre la resistencia de la nueva caja y la capacidad restante de la columna, comenzando con un valor infinito.
``` java
if (cajas.size() == 1) {
    capacidadRestante = caja.getResistencia();
} else {
    capacidadRestante = Math.min(capacidadRestante - caja.getPeso(), caja.getResistencia());
}
```

# Ejercicio 4
En las estructuras de hashing abierto separado:

$i)$ ¿Qué significado tiene el número $\rho_d$ (rho de diseño)?
- El $\rho_d$ (factor de carga) es un parametro que define el limite de densidad promedio aceptable para la tabla.
- Representa la relacion entre la cantidad de elementos ($n$) y la capacidad fisica de los baldes primarios ($M \times r$)
$$ \rho_d = \frac{n}{M \times r} $$
- Tambien sirve para determinar cuando se debe redimensionar la tabla hash (rehashing) calculando el limite $L=\lfloor M \times r \times \rho_d \rfloor$.

$ii)$ Explique qué impactos sobre la estructura y sobre su desempeño tendrán valores de pd chicos (ej. pd = 0,1) y grandes (ej. pd = 3).
- **Para $\rho_d$ chicos ej: $\rho_d = 0.1$**: 
    - **Impacto en la estructura**: La tabla crecera rapidamente (rehashing frecuente) y habra muchos baldes primarios vacios o con pocos elementos.
    - **Impacto en el desempeño**: Habra pocas colisiones lo que mejora el tiempo de busqueda.
    - **Impacto en memoria**: Ineficiente se desperdiciara mucha memoria con baldes vacios.
- **Para $\rho_d$ grandes ej: $\rho_d = 3$**:
    - **Impacto en la estructura**: La tabla crecera lentamente y habra muchos baldes primarios con muchos elementos.
    - **Impacto en el desempeño**: Habra muchas colisiones lo que degrada el tiempo de busqueda.
    - **Impacto en memoria**: Eficiente se utilizara bien la memoria pero a costa del tiempo de busqueda.

Si se le pide diseñar una estructura de datos que usará el sistema de alumnos para guardar los datos de los alumnos de la facultad, donde habrá altas y bajas de alumnos y donde se sabe que la consulta más frecuente del sistema es emitir un reporte con todos los alumnos listados en orden de número de libreta universitaria de menor a mayor. ¿Qué estructura de datos elegiría? ¿Cómo estaría organizada la información? Justifique.

# Ejercicio 5
¿Cuál es la cantidad máxima de puntos de articulación que puede tener un grafo no dirigido conectado? Justifique su respuesta.

> si nos imaginamos la mayor simplificacion de un grafo (un grafo lineal) se observa claramente que para un grafo $G=(V,A)$ la cantidad de vertices $V$ debe ser al menos 3 y que de solo dejar los vertices $V_0$ y $V_n$ la camtidad de puntos de articulacion maxima siempre sera $V-2$.

Si se tuviera una función de hashing perfecto sobre una estructura de hashing con $M$ baldes y $r_p=2$, ¿se estaría desperdiciando espacio en dicha estructura? Justifique su respuesta.

> TODO

# Nota de color
Este final esta calificado con 11 de dificultad en una escala de 5 estrellas y cumple con la categoria **LCDTM** (`Larguisimo`, `Complejo`, `Desaprobado`, `Técnicamente imposible` y `Maldad pura`). Se recomienda adherirse a una religion y aparecer en la mesa con mas fe que estudios.