package DTO;

import java.util.List;

public class SolucionAproximada extends Solucion {
    private static final String ANSI_YELLOW = "\u001B[33m";
    public SolucionAproximada(List<Maquina> maquinas, int totalPiezas, int estado) {
        super(maquinas, totalPiezas, estado);
    }

    @Override
    public String toString() {
        return ANSI_YELLOW + "Solucion aproximada: " + ANSI_RESET + super.getMaquinas() + 
               "\n|- Piezas producidas: " + super.getTotalPiezas() + 
               "\n|- Estado: " + super.getEstado() + ANSI_RESET;
    }
}
