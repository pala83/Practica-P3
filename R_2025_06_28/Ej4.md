# Resolución Ejercicio 4 (Hashing)

## Inciso a) Validez de la estructura

Para determinar si la estructura es válida, debemos verificar si se ha excedido el límite de elementos permitido por el factor de carga de diseño ($\rho_d$) antes de realizar una redimensión (rehashing).

**Datos:**
*   $M = 4$ (Cantidad de baldes iniciales)
*   $r_p = 1$ (Ranuras por balde)
*   $\rho_d = 1.25$ (Factor de carga de diseño)
*   **Elementos insertados:** 4, 24, 8, 12, 16, 20 (Total $N = 6$)

**Cálculo del Límite ($L$):**
Según la fórmula del resumen:
$$L = \lfloor M \times r_p \times \rho_d \rfloor$$
$$L = \lfloor 4 \times 1 \times 1.25 \rfloor = \lfloor 5 \rfloor = 5$$

**Análisis:**
La estructura tiene actualmente **6 elementos**.
Como $N > L$ ($6 > 5$), la estructura ha superado su capacidad de diseño y debería haber crecido (redimensionado y rehasheado).

**Conclusión:**
La estructura **NO está correctamente formada (es inválida)** porque excede el límite de carga ($L=5$) sin haber realizado la expansión y reorganización (rehashing) necesaria.

---

## Inciso b) Estructura Válida

Al superar el límite, la estructura debe crecer. Basándonos en el resumen que menciona "rehashing de todos los elementos", asumiremos una estrategia estándar de duplicación del tamaño de la tabla ($M_{nuevo} = 2 \times M_{actual}$).

**Nuevos Parámetros:**
*   $M_{nuevo} = 8$
*   Función de Hash: $h(x) = x \mod 8$
*   Nuevo Límite: $L' = \lfloor 8 \times 1 \times 1.25 \rfloor = 10$ (Ahora $6 \le 10$, es válido).

**Rehashing de elementos:**
Calculamos la nueva posición para cada clave:
*   $4 \mod 8 = 4$
*   $24 \mod 8 = 0$
*   $8 \mod 8 = 0$
*   $12 \mod 8 = 4$
*   $16 \mod 8 = 0$
*   $20 \mod 8 = 4$

**Distribución Resultante:**
*   **Balde 0:** Contendrá las claves {24, 8, 16}
*   **Balde 4:** Contendrá las claves {4, 12, 20}
*   **Resto de baldes:** Vacíos.

**Representación Gráfica:**

```text
      +---+    +----+    +---+    +----+
[0] --|24|---> | 8  |--->|16 | -> NULL
      +---+    +----+    +---+
[1] -- NULL
[2] -- NULL
[3] -- NULL
      +---+    +----+    +----+
[4] --| 4 |--> | 12 |--->| 20 | -> NULL
      +---+    +----+    +----+
[5] -- NULL
[6] -- NULL
[7] -- NULL
```

---

## Inciso c) Lista de Factoreo

**Definición:**
Una **Lista de Factoreo** es una estructura auxiliar utilizada en Hashing cuando las claves de búsqueda **no son únicas** (hay repeticiones).

**Funcionamiento y Uso:**
En lugar de almacenar múltiples registros con la misma clave dispersos en la lista de colisiones (lo cual degradaría el rendimiento de búsqueda al tener que recorrer nodos con claves distintas), la tabla de hash almacena una única entrada para esa clave. Esta entrada apunta a una **lista secundaria** (la lista de factoreo) que agrupa exclusivamente a todos los registros que comparten esa misma clave.

**Ejemplo:**
Si agrupamos alumnos por "Ciudad de Nacimiento":
*   Hash("Tandil") $\rightarrow$ Apunta a una lista que contiene solo a los alumnos de Tandil.
