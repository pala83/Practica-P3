package SolucionAlgoritmica;

import java.util.ArrayList;
import java.util.List;

import DTO.Calculador;
import DTO.Maquina;

public class SolucionGreedy implements Solucion {
    private int cantActual;

    @Override
    public Calculador calcular(List<Maquina> maquinas, int totalPiezas) {
        this.cantActual = 0;
        List<Maquina> secuencia = new ArrayList<>();
        int acumulador = 0;
        while (acumulador < totalPiezas) {
            cantActual += maquinas.size();
            Maquina mejor = maquinas.get(0);
            secuencia.add(mejor);
            acumulador += mejor.getProduccion();
        }
        return new Calculador(secuencia, acumulador, secuencia.size());
    }

    public int getCantActual() {
        return cantActual;
    }
}
