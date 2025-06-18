package CalculoAlgoritmico;

import java.util.List;

import DTO.Maquina;
import DTO.Solucion;

public interface Calculador {
    /**
     * Genera una solucion para el problema de encontrar la secuencia óptima de máquinas.
     * @param maquinas Lista de máquinas disponibles.
     * @param totalPiezas Cantidad total de piezas que se necesitan producir.
     * @return Solución que contiene la secuencia de máquinas que minimiza la cantidad
     *         de máquinas necesarias para producir al menos el total de piezas requerido.
     */
    Solucion calcular(List<Maquina> maquinas, int totalPiezas);
    /**
     * Retorna la cantidad de estados generados para la solucion.
     * @return Cantidad de estados generados.
     */
    int cantidadEstados();
}
