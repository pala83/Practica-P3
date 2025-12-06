# Resumen de hasing
El Hashing, o estructuras de dispersión es un mecanismo de acceso asociativo diseñado para recuperar registros con un costo computacional mínimo, idealmente $O(1)$. A diferencia de estructuras ordenadas como arrays ($Log_2 N$) o árboles binarios ($Log_2 N$), el hashing no mantiene un orden físico de los datos, sino que los dispersa en un espacio de almacenamiento.

## Conceptos fundamentales
### Función de Hashing ($h(x)$): 
Transforma una clave de búsqueda $x$ (del dominio estructurante) en una dirección de almacenamiento (balde) dentro de la tabla. La función más común es la aritmética modular:

$$ h(x)=x mod M $$

Donde $M$ es la cantidad de baldes (generalmente un número primo) y $x$ es la clave.

### Factor de Carga ($\rho$): 
Medida de la densidad de ocupación de la estructura, definida como la relación entre la cantidad de datos almacenados y la capacidad total de los baldes primarios.

$$\rho = \frac{\text{cantidad de datos}}{M \times r}$$

Donde $r$ es el numero de ranuras por balde.

## Tipos de Hashing
### Hashing Perfecto: 
Garantiza que cada clave $x$ tenga una dirección única ($\forall X_1 \neq X_2 \Rightarrow h(X_1) \neq h(X_2)$). Es útil en dominios con regularidades matemáticas conocidas (ej. arreglos multidimensionales), permitiendo acceso en 1 solo paso.

### Hashing Puro:
Es el caso común donde diferentes claves pueden asignarse a la misma dirección ($h(X_1) = h(X_2)$), produciendo colisiones y overflow (desborde) cuando un balde no tiene espacio.

## Manejo de Colisiones: Hashing Separado
Para manejar el overflow, se utilizan técnicas abiertas o dinámicas como el Hashing Separado (o encadenamiento).
- Se asocia una **lista vinculada** (balde lógico) a cada balde de la estructura primaria para almacenar los elementos que colisionan.
- **Hashing Separado con Crecimiento**: La estructura primaria (array de baldes) es dinámica. Se expande cuando la cantidad de elementos supera un límite $L$ basado en un factor de carga de diseño $\rho_d$.
- $r_p$ es la cantidad de ranuras que tiene 

    $$L = \lfloor M \times r_p \times \rho_d \rfloor$$

    Al crecer, se debe realizar una operación costosa de reorganización de todos los elementos (**rehashing**) para reducir la longitud de las listas de rebalse.

## Aplicaciones y Estructuras Auxiliares
- **Listas de Factoreo**: Utilizadas cuando la clave de búsqueda no es única (ej. "Ciudad de nacimiento"). La tabla de hash apunta a una lista que agrupa todos los registros que comparten esa misma clave.
- **Índices**: Se sugiere usar estructuras de hash para búsquedas exactas (ej. DNI) y estructuras ordenadas (como Árboles Binarios de Búsqueda - ABB) para búsquedas por rango (ej. fechas), manteniendo los datos en una estructura principal y referenciándolos desde estos índices.
