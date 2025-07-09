package CalculoAlgoritmico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import DTO.Solucion;
import DTO.SolucionAproximada;
import DTO.Maquina;
import DTO.SinSolucion;
/**
 * Implementación de {@link Calculador} que utiliza un algoritmo de greedy para encontrar la secuencia óptima de máquinas que
 * minimiza la cantidad de arranques necesarios para producir exactamente el número de piezas objetivo.
 * <ul>
 *  <li>Ordenamiento de máquinas por producción descendente.</li>
 *  <li>Selección de candidatos:</li>
 *  <li>Consideraciones de solución:</li>
 * </ul>
 */
public class Greedy implements Calculador {
    private int estados;

    /**
     * {@inheritDoc}
     * Implementa un algoritmo greedy para encontrar una secuencia de máquinas que minimiza
     * la cantidad de arranques necesarios para producir exactamente el número de piezas objetivo.
     *
     * <p><strong>Estrategia de resolución:</strong></p>
     * <ul>
     *   <li><strong>Candidatos:</strong> Las máquinas disponibles, ordenadas por producción
     *   descendente. Esto permite priorizar las máquinas que producen más piezas por arranque.</li>
     *   
     *   <li><strong>Selección de candidatos:</strong> En cada paso, se selecciona la máquina con
     *   mayor producción que no exceda la cantidad de piezas restantes. Se usa la máxima cantidad
     *   posible de esa máquina antes de pasar a la siguiente.</li>
     *   
     *   <li><strong>Consideraciones de solución:</strong>
     *     <ul>
     *       <li>El algoritmo puede no encontrar una solución exacta si no existe una combinación
     *       de máquinas que sumen exactamente el total de piezas requerido.</li>
     *       <li>No garantiza encontrar la solución óptima en todos los casos, pero es eficiente
     *       en términos de tiempo de ejecución.</li>
     *       <li>En caso de no encontrar una solución exacta, se devolverá la mejor aproximación posible.</li>
     *       <li>Es determinista: siempre producirá el mismo resultado para la misma entrada.</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param maquinas Lista de máquinas disponibles (no necesita estar ordenada).
     * @param totalPiezas Número total de piezas objetivo a producir.
     * @return Una {@link Solucion} con la secuencia de máquinas encontrada y el total de piezas producidas.
     *         Si no se puede alcanzar exactamente el total, se devolverá la mejor aproximación posible.
     */
    @Override
    public Solucion calcular(List<Maquina> maquinas, int totalPiezas) {
        List<Maquina> maquinasOrdenadas = this.maquinasOrdenadas(maquinas);
        this.estados = 0;
        List<Maquina> secuencia = new LinkedList<>();
        int acumulador = 0;
        int i = 0;
        while (acumulador < totalPiezas && i<maquinasOrdenadas.size()) {
            if((acumulador + maquinasOrdenadas.get(i).getProduccion()) <= totalPiezas){
                int totalMaquinas = (totalPiezas-acumulador)/maquinasOrdenadas.get(i).getProduccion();
                secuencia.addAll(Collections.nCopies(totalMaquinas, maquinasOrdenadas.get(i)));
                acumulador += totalMaquinas * maquinasOrdenadas.get(i).getProduccion();
            }else{
                i++;
            }
            estados++;
        }
        if(secuencia.isEmpty())
            return new SinSolucion();
        if(acumulador < totalPiezas)
            return new SolucionAproximada(secuencia, acumulador, secuencia.size());
        return new Solucion(secuencia, acumulador, secuencia.size());
    }

    public int cantidadEstados() {
        return estados;
    }

    private List<Maquina> maquinasOrdenadas(List<Maquina> maquinas) {
        List<Maquina> maquinasOrdenadas = new ArrayList<>(maquinas);
        Collections.sort(maquinasOrdenadas);
        return maquinasOrdenadas;
    }
}
