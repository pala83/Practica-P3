# Trabajo práctico 2
## Ejercicio 1
Algoritmo:

``` java
private boolean _isOrdered(int[] arr, int pointer){
    if(pointer==arr.length-1)
        return true;
    if(arr[pointer]>arr[pointer+1])
        return false;
    return _isOrdered(arr, pointer+1);
}
```
1. ¿Qué complejidad O tiene? (La complejidad en el peor caso)
Siendo $n$ el tamaño del arreglo

> $$
> T(n) =
> \begin{cases}
>     c_1 & \text{si } n \leq 0 \\
>     T(n-1) + c_2 & \text{si } n > 0
> \end{cases}
> $$

> 
> $T(n)=T(n-1)+c_2$  
> $T(n-1)=T(n-2)+c_2+c_2$  
> $T(n-2)=T(n-3)+c_2+c_2+c_2$  
> $T(n-i)=T(n-i-1)+ic_2+c_2$  
> $\text{si } i=n$  
> $T(0)=T(-1)+nc_2+c_2 = max(c_1+nc_2+c_2) = n$  
> $T(n)=O(n)$

2. ¿Trae algún problema hacerlo recursivo? ¿Cuál?
> Si, requiere mas parámetros en la llamada a la función que deberían ser innecesarios
3. ¿Qué cambiaría si la estructura fuera una lista en lugar de un arreglo?
> No cambia nada, en un arreglo y en una lista la complejidad algorítmica seria la misma

## Ejercicio 6
¿Qué complejidad big-O tienen estos algoritmos?

**Selection Sort:**
``` java
public int[] selectionSort(){
    int[] retorno = Arrays.copyOf(this.arr, this.arr.length);
    for(int i=0; i<retorno.length; i++){
        for(int j=i+1; j<retorno.length; j++){
            if(retorno[j]<retorno[i]){
                int aux = retorno[j];
                retorno[j] = retorno[i];
                retorno[i] = aux;
            }
        }
    }
    return retorno;
}
```
### Calculo de complejidad
Siendo $n$ el tamaño del arreglo

> $T(n) = \sum_{i=0}^{n-1}(c_1+\sum_{j=i+1}^{n-1}c_2+c_3)+c_4 $  
> $T(n) = \sum_{i=0}^{n-1}(c_1+(n-1-(i+1)+1)c_2+c_3)+c_4 $  
> $T(n) = \sum_{i=0}^{n-1}(c_1+nc_2-ic_2-c_2+c_3)+c_4 $  
> $T(n) = \sum_{i=0}^{n-1}(c_1+nc_2-c_2+c_3)-c_2\sum_{i=0}^{n-1}i+c_4 $  
> $\sum_{i=1}^{n}i= \frac{n(n+1)}{2} $  
> $T(n) = n(c_1+nc_2-c_2+c_3)-c_2\frac{(n-1)n}{2}+c_4 $  
> $T(n) = max(nc_1+n^2c_2-nc_2+nc_3-c_2\frac{(n-1)n}{2}+c_4) = n^2 $  
> $T(n) \in O(n^2)$  

**Bubble Sort:**
``` java
public int[] bubbleSort(){
    int[] retorno = Arrays.copyOf(this.arr, this.arr.length);
    for(int i=0; i<retorno.length-1; i++){
        for(int j=0; j<retorno.length-1-i; j++){
            if(retorno[j]>retorno[j+1]){
                int aux = retorno[j];
                retorno[j] = retorno[j+1];
                retorno[j+1] = aux;
            }
        }
    }
    return retorno;
}
```

### Calculo de complejidad
Siendo $n$ el tamaño del arreglo

> $T(n) = \sum_{i=0}^{n-2}(c_1+\sum_{j=0}^{n-i-1}c_2+c_3)+c_4$  
> $T(n) = \sum_{i=0}^{n-2}(c_1+(n-i)c_2+c_3)+c_4$  
> $T(n) = \sum_{i=0}^{n-2}(c_1+nc_2-ic_2+c_3)+c_4$  
> $T(n) = \sum_{i=0}^{n-2}(c_1+nc_2+c_3)-c_2\sum_{i=0}^{n-2}i+c_4$  
> $T(n) = (n-1)(c_1+nc_2-c_2+c_3)-c_2\frac{(n-2)(n-1)}{2}+c_4$  
> $T(n) = max((n-1)(c_1+nc_2-c_2+c_3)-c_2\frac{(n-2)(n-1)}{2}+c_4) = n^2$  
> $T(n) \in O(n^2)$  

## Ejercicio 7
### MergeSort
``` java
private void mergesort(int low, int high){
    if(low<high){
        int middle = (low+high)/2;
        mergesort(low, middle);
        mergesort(middle+1, high);
        merge(low, middle, high);
    }
}

private void merge(int low, int middle, int high){
    for(int i=low; i<=high; i++)
        helper[i] = numbers[i];
    int i = low;
    int j = middle+1;
    int k = low;
    while (i<=middle && j<=high) {
        if(helper[i]<=helper[j]){
            numbers[k] = helper[i];
            i++;
        }
        else{
            numbers[k] = helper[j];
            j++;
        }
        k++;
    }
    while (i<=middle) {
        numbers[k] = helper[i];
        k++;
        i++;
    }
    while (j<=high) {
        numbers[k] = helper[j];
        k++;
        j++;
    }
}
```

#### Calculo de complejidad
> Siendo $n$ el tamaño del arreglo
> 
> $$
> T(n) =
>   \begin{cases} 
>       c_1 & \text{si } n = 1 \\ 
>       2T(n/2) + nc_2+c_3 & \text{si } n > 0
>   \end{cases}
> $$
> 
> $$
> \begin{aligned} \\
> T(n) &=2T(n/2)+nc_2+c_3 \\
> T(n/2) &=2[2T(n/4)+c_2\frac{n}{2}+c_3]+nc_2+c_3 \\
> &=4T(n/4)+2nc_2+3c_3 \\
> T(n/4)&=4[2T(n/8)+c_2\frac{n}{4}+c_3]+2nc_2+3c_3 \\
> &=8T(n/8)+3nc_2+7c_3 \\
> T(n/2^{k})&=2^kT(n/2^k)+knc_2+(2^{k-1})c_3 \\
> \end{aligned}
> $$
> 
> Caso base $T(n)=c_1$ cuando $n=1$  
> Por lo tanto, necesito que $\frac{n}{2^k}=1$
> 
> $$\frac{n}{2^k}=1 \implies n=2^k$$  
> $$\log_2n = \log_22^k=k\log_22$$  
> $$k=\log_2n$$  
> 
> $$
> \begin{aligned} \\
> T(n/2^{\log_2n})&=2^{\log_2n}T(n/2^{\log_2n})+n\log_2nc_2+(2^{\log_2n}-1)c_3 \\
> T(1)&=\max(nc_1+n\log_2nc_2+(n-1)c_3) = O(n\log_2n) \\
> T(n)&\in O(n\log_2n) \\
> \end{aligned}
> $$
