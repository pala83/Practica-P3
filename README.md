# Respuestas tp3

## Ejercicio 1
> Conjunto de elementos: X= {68, 42, 47, 5, 76, 95, 23, 88, 90, 85, 31, 71, 60, 10, 46, 61, 50, 92, 74, 6, 97, 66, 1, 56, 27, 7, 14, 92}

### a.- Hashing separado (con M=7 y rp=1, rs=1).

| Balde | Índice h(x)=x mod 7 | Elementos             |
|-------|---------------------|-----------------------|
| 0     | 0                   | 42, 56, 7, 14         |
| 1     | 1                   | 85, 71, 50, 92, 1, 92 |
| 2     | 2                   | 23                    |
| 3     | 3                   | 31, 10, 66            |
| 4     | 4                   | 95, 88, 60, 46, 74    |
| 5     | 5                   | 68, 47, 5, 61         |
| 6     | 6                   | 76, 90, 6, 97, 27     |

### b.- Hashing separado con crecimiento (con el comportamiento de HashTable de JAVA) (con M=7, ρd=0,9).
> Umbral inicial: $\lfloor7\cdot1\cdot0,9\rfloor=6$  
> Al insertar el elemento 7, redimensionamos la tabla a $M'= 2\cdot7+1=15$ y el umbral $L'=\lfloor15\cdot1\cdot0,9\rfloor=13$

| Paso | M & L      | Rehash        |
|------|------------|---------------|
| 1–6  | (M=7)      | no rehash     |
| 7    | (n=7= L)   | rehash a M=15 |
| 8–13 | (7<n<13)   | sin rehash    |
| 14   | (n=13= L') | rehash a M=31 |
| 15–26| (13<n<31)  | sin rehash    |
| 27   | (n=31= L") | rehash a M=63 |

## Ejercicio 2

1. No, no es posible listar en orden todas las claves almacenadas en una estructura de hashing, ya que no se mantiene ningún orden entre los elementos, para almacenar las claves en hashing se utiliza la técnica de la dispersión (un conjunto de claves). Por lo tanto, la estructura más adecuada para listar en orden todas las claves almacenadas en una estructura de hashing es un árbol balanceado (por ejemplo, TreeMap) para mantener el orden con complejidad $O(log(n))$ por inserción y recorrido en orden $O(n)$.
2. Los servicios que resuelve hashing son: 
    - Búsqueda, inserción y eliminación de claves $O(1)$.
    - Consultas por rangos (por ejemplo, todos los alumnos con notas > x) no son adecuadas para hashing ya que requieren recorrer todos los elementos $O(n)$.

## Ejercicio 3
El ejercicio 3 fue implementado en el archivo ComedorService.java
### a.- Dado un DNI de cliente, responder el saldo de su cuenta.
```java
public double obtenerSaldo(Integer dni) {
    Cliente cliente = this.clientes.get(dni);
    return cliente!=null?cliente.getSaldo():0;
}
```

### b.- Imprimir un listado de Nombre y Apellido de todos los clientes que tienen en su saldo de cuenta menos de un valor X dado
```java
public List<Cliente> listarClientesSaldoMenor(double saldo) {
    List<Cliente> clientes = new ArrayList<>();
    for(Cliente cliente : this.clientes.getAllValues()){
        if(cliente.getSaldo() < saldo){
            clientes.add(cliente);
        }
    }
    return clientes;
}
```

### c.- Dado un código postal, listar todos los clientes que provengan de esa ciudad.
```java
public List<Cliente> listarClientesConCP(String codigoPostal) {
    List<Cliente> clientes = new ArrayList<>();
    for(Cliente cliente : this.clientes.getAllValues()){
        if(cliente.getCodigoPostal().equals(codigoPostal)){
            clientes.add(cliente);
        }
    }
    return clientes;
}
```
