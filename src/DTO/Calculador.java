package DTO;

import java.util.List;

public class Calculador {
    private final List<Maquina> maquinas;
    private final int totalPiezas;
    private final int estado;
    public Calculador(List<Maquina> maquinas, int totalPiezas, int estado) {
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
        return "Calculador [maquinas=" + maquinas + ", totalPiezas=" + totalPiezas + ", estado=" + estado + "]";
    }
}
