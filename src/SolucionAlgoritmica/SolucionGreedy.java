package SolucionAlgoritmica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import DTO.Calculador;
import DTO.Maquina;

public class SolucionGreedy implements Solucion {
    private int cantActual;

    @Override
    public Calculador calcular(List<Maquina> maquinas, int totalPiezas) {
        List<Maquina> maquinasOrdenadas = this.maquinasOrdenadas(maquinas);
        this.cantActual = 0;
        List<Maquina> secuencia = new LinkedList<>();
        int acumulador = 0;
        int i = 0;
        while (acumulador < totalPiezas && i<maquinasOrdenadas.size()) {
            if((acumulador + maquinasOrdenadas.get(i).getProduccion()) <= totalPiezas){
                int totalMaquinas = (totalPiezas-acumulador)/maquinasOrdenadas.get(i).getProduccion();
                for(int j = 0; j < totalMaquinas; j++){
                    Maquina m = maquinasOrdenadas.get(i);
                    secuencia.add(m);
                    acumulador += m.getProduccion();
                }
            }else{
                i++;
            }
            cantActual++;
        }
        return new Calculador(secuencia, acumulador, secuencia.size());
    }

    public int getCantActual() {
        return cantActual;
    }

    private List<Maquina> maquinasOrdenadas(List<Maquina> maquinas) {
        List<Maquina> maquinasOrdenadas = new ArrayList<>(maquinas);
        Collections.sort(maquinasOrdenadas);
        return maquinasOrdenadas;
    }
}
