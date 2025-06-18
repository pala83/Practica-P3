package DTO;

import java.util.List;

public class Solucion {
    private final List<Maquina> maquinas;
    private final int totalPiezas;
    private final int estado;
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
        return "Solucion obtenida: " + maquinas + 
               "\n|- Piezas producidas: " + totalPiezas + 
               "\n|- Estado: " + estado;
    }
}
