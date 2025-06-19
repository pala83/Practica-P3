# Trabajo Practico Especial:
### Integrantes:
- Ulises Palazzo
- Sebastian Cisneros

# Optimización de Producción con Múltiples Máquinas

## Descripción del Problema

Dado un número determinado de piezas a producir, se desea encontrar la secuencia óptima de máquinas que minimice la cantidad total de arranques necesarios para completar la producción. Cada máquina tiene una capacidad de producción fija de piezas por ciclo.

**Ejemplo:**
- Máquinas disponibles: (M1, 7), (M2, 3), (M3, 4), (M4, 1)
- Piezas a producir: 12
- Soluciones posibles:
  - [M1 (7) + M3 (4) + M4 (1)] = 12 piezas (3 arranques)
  - [M3 (4) + M3 (4) + M3 (4)] = 12 piezas (3 arranques)
  - [M1 (7) + M2 (3) + M2 (3)] = 13 piezas (3 arranques, no óptima por exceso)

## Soluciones Implementadas

### 1. Backtracking

**Estrategia de Resolución:**
- **Árbol de exploración:** Se construye un árbol donde cada nivel representa la elección de una máquina. En cada nodo se explora la posibilidad de seleccionar cualquiera de las máquinas disponibles.
- **Estados finales:** Un estado es final cuando la suma de piezas producidas es igual o supera el total requerido.
- **Estados solución:** Son aquellos estados finales donde la suma de piezas es exactamente igual al total requerido.
- **Poda:**
  - Se ordenan las máquinas por producción descendente para encontrar soluciones óptimas más rápido.
  - Se abandona una rama si la cantidad de máquinas usadas iguala o supera la mejor solución encontrada.
  - Se poda cuando la suma parcial excede el total de piezas requerido.

### 2. Algoritmo Greedy

**Estrategia de Resolución:**
- **Candidatos:** Las máquinas disponibles, ordenadas por producción descendente.
- **Selección de candidatos:** En cada paso, selecciona la máquina con mayor producción que no exceda las piezas restantes.
- **Consideraciones:**
  - No siempre encuentra la solución óptima, pero es muy eficiente en tiempo.
  - Es determinista: siempre produce el mismo resultado para la misma entrada.
  - En caso de no encontrar una solución exacta, devuelve la mejor aproximación posible.

## Estructura del Proyecto

```
src/
├── CalculoAlgoritmico/
│   ├── Backtracking.java    # Implementación del algoritmo de backtracking
│   ├── Calculador.java      # Interfaz común para los algoritmos
│   └── Greedy.java          # Implementación del algoritmo greedy
├── DTO/
│   ├── Maquina.java        # Clase que representa una máquina
│   └── Solucion.java        # Clase que representa una solución
├── Utils/
│   ├── FileSelector.java   # Utilidad para seleccionar archivos
│   └── TXTReader.java       # Utilidad para leer archivos de entrada
└── App.java                # Punto de entrada de la aplicación
```

## Cómo Ejecutar

1. Coloque los archivos de datos en la carpeta `src/Data/` con el siguiente formato:
   ```
   <total_piezas>
   <nombre_maquina1>,<produccion1>
   <nombre_maquina2>,<produccion2>
   ...
   ```

2. Ejecute la clase `App`:
   ```bash
   javac -d bin src/*.java src/CalculoAlgoritmico/*.java src/DTO/*.java src/Utils/*.java
   java -cp bin App
   ```

3. Seleccione el archivo de entrada cuando se le solicite.

## Ejemplo de Entrada

```
15
M1,7
M2,3
M3,4
M4,1
```

## Ejemplo de Salida

```
Backtracking
Solucion obtenida: [[M1, 7], [M3, 4], [M3, 4]]
|- Piezas producidas: 15
|- Estado: 3
|- Cantidad de estados: 15

Greedy
Solucion obtenida: [[M1, 7], [M3, 4], [M3, 4]]
|- Piezas producidas: 15
|- Estado: 3
|- Cantidad de estados: 6
```

## Análisis de Complejidad

### Backtracking
- **Tiempo en el peor caso:** O(m^n), donde m es el número de máquinas y n es el número de piezas.
- **Espacio:** O(n) para el stack de llamadas recursivas.

### Greedy
- **Tiempo:** O(m log m) para ordenar las máquinas + O(m) para la selección, donde m es el número de máquinas.
- **Espacio:** O(m) para almacenar las máquinas ordenadas.

## Consideraciones

- El algoritmo de backtracking encuentra siempre la solución óptima pero puede ser ineficiente para instancias grandes.
- El algoritmo greedy es mucho más rápido pero no garantiza la solución óptima en todos los casos.
- Ambos algoritmos devuelven soluciones válidas, pero pueden diferir en la cantidad de arranques necesarios.