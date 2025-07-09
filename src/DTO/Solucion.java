package DTO;

import java.util.List;

public class Solucion {
    private final List<Maquina> maquinas;
    private final int totalPiezas;
    private final int estado;
    private static final String ANSI_GREEN = "\u001B[32m";
    protected static final String ANSI_RESET = "\u001B[0m";
    public Solucion(List<Maquina> maquinas, int totalPiezas, int estado) {
        this.maquinas = maquinas;
        this.totalPiezas = totalPiezas;
        this.estado = estado;
    }
    public List<Maquina> getMaquinas() {
        return List.copyOf(maquinas);
    }
    public int getTotalPiezas() {
        return totalPiezas;
    }
    public int getEstado() {
        return estado;
    }
    @Override
    public String toString() {
        return ANSI_GREEN + "Solucion obtenida: " + ANSI_RESET + maquinas + 
               "\n|- Piezas producidas: " + totalPiezas + 
               "\n|- Estado: " + estado + ANSI_RESET;
    }
}
