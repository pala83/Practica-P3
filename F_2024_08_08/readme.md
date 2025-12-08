 # Ejercicio 3
 ### Estrategia Greedy para organizar cajas en columnas
 
 1. ORDENAR las cajas por resistencia de MAYOR a MENOR.
    - Las cajas más resistentes van abajo (soportan más peso encima).
 
 2. Para cada caja (en orden de mayor a menor resistencia):
    - Intentar colocarla en una columna existente donde la caja de arriba
      pueda soportar el peso de esta nueva caja.
    - Si no cabe en ninguna columna existente, crear una nueva columna.
 
 3. La decisión greedy: siempre colocamos la caja en la primera columna
    donde quepa, priorizando reutilizar columnas existentes.
 
 ¿Por qué funciona?
 - Al ordenar por resistencia descendente, garantizamos que las cajas
   más fuertes estén abajo y las más débiles arriba.
 - Cada caja solo necesita soportar el peso de las cajas que están ENCIMA.
 
 ============================================================================