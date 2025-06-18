package CalculoAlgoritmico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import DTO.Solucion;
import DTO.Maquina;

/**
 * Implementación de {@link Calculador} que utiliza backtracking con
 * estrategias de poda para minimizar el número de arranques:
 * <ul>
 *   <li>Ordenación de máquinas por producción descendente.</li>
 *   <li>Poda por cantidad de máquinas usadas.</li>
 *   <li>Poda por total de piezas.</li>
 * </ul>
 */
public class Backtracking implements Calculador {
    private List<Maquina> mejorSecuencia = new ArrayList<>();
    private int estados;

    /**
     * {@inheritDoc}
     * Implementa un algoritmo de backtracking para encontrar la secuencia óptima de máquinas que
     * minimiza la cantidad de arranques necesarios para producir exactamente el número de piezas objetivo.
     *
     * <p><strong>Estrategia de resolución:</strong></p>
     * <ul>
     *   <li><strong>Árbol de exploración:</strong> Se construye un árbol donde cada nivel representa la elección
     *   de una máquina. En cada nodo se explora la posibilidad de seleccionar cualquiera de las máquinas
     *   disponibles, generando así un árbol n-ario donde n es la cantidad de máquinas.</li>
     *   
     *   <li><strong>Estados finales:</strong> Un estado es final cuando la suma de las producciones de las
     *   máquinas seleccionadas es igual al total de piezas requerido (solución) o cuando la suma excede
     *   este total (solución inválida).</li>
     *   
     *   <li><strong>Estados solución:</strong> Son aquellos estados finales donde la suma de producciones
     *   es exactamente igual al total de piezas requerido. Entre estos, se busca el que tenga la menor
     *   cantidad de máquinas.</li>
     *   
     *   <li><strong>Estrategias de poda:</strong>
     *     <ul>
     *       <li><strong>Poda por optimalidad:</strong> Se mantiene un registro de la mejor solución encontrada
     *       y se abandona cualquier rama donde la cantidad de máquinas usadas hasta el momento sea mayor o
     *       igual que la mejor solución encontrada.</li>
     *       <li><strong>Poda por factibilidad:</strong> Se abandona la rama actual si la suma de producciones
     *       excede el total de piezas requerido.</li>
     *       <li><strong>Ordenamiento:</strong> Las máquinas se ordenan por producción descendente para favorecer
     *       la exploración de soluciones con menos máquinas al principio de la búsqueda.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param maquinas   Lista de máquinas disponibles (no es necesaria
     *                   ordenación previa, se ordena internamente).
     * @param totalPiezas Número total de piezas objetivo.
     * @return Una {@link Solucion} con la secuencia óptima de arranques y
     *         la longitud de dicha secuencia.
     */
    @Override
    public Solucion calcular(List<Maquina> maquinas, int totalPiezas) {
        this.estados = 0;
        /*
         * <strong>PODA:</strong> Se podria considerar una poda, ya que al tener las maquinas ordenadas de mayor a menor produccion,
         * se podria obtener la mejor secuencia posible en relacion a cantidad de maquinas en menor cantidad de iteraciones,
         * por lo que la PODA1 sera mucho mas eficiente.
         */
        List<Maquina> maquinasOrdenadas = new ArrayList<>(maquinas);
        Collections.sort(maquinasOrdenadas);
        for (Maquina m : maquinasOrdenadas) 
            this.backtracking(maquinasOrdenadas, m, totalPiezas, 0, new ArrayList<>());
        return new Solucion(mejorSecuencia, totalPiezas, mejorSecuencia.size());
    }

    private void backtracking(List<Maquina> maquinas, 
                              Maquina m, 
                              int totalPiezas, 
                              int acumulador, 
                              List<Maquina> arrTMP) {
        estados++;
        arrTMP.add(m);
        acumulador += m.getProduccion();
        // PODA1: Si la secuencia actual supera la mejor secuencia, no es necesario continuar
        if(!mejorSecuencia.isEmpty() && arrTMP.size() >= mejorSecuencia.size())
            return;
        // PODA2: Si el acumulador supera el total de piezas, no es necesario continuar
        if(acumulador > totalPiezas)
            return;
        if(acumulador == totalPiezas && arrTMP.size() <= (mejorSecuencia.isEmpty() ? Integer.MAX_VALUE : mejorSecuencia.size())){
            mejorSecuencia.clear();
            mejorSecuencia.addAll(arrTMP);
        }
        
        for(Maquina subM : maquinas){
            backtracking(maquinas, subM, totalPiezas, acumulador, arrTMP);
            arrTMP.remove(arrTMP.size()-1);
        }
    }

    public int cantidadEstados() {
        return estados;
    }

}
