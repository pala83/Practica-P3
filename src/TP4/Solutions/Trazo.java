package TP4.Solutions;

public class Trazo {
    private int tiempoI;
    private int tiempoF;
    private String estado;

    public Trazo(String estado) {
        this.tiempoI = 0;
        this.tiempoF = 0;
        this.estado = estado;
    }

    public int getTiempoI() { return this.tiempoI; }
    public int getTiempoF() { return this.tiempoF; }
    public String getEstado() { return this.estado; }

    public void setTiempoI(int tiempoI) { this.tiempoI = tiempoI; }
    public void setTiempoF(int tiempoF) { this.tiempoF = tiempoF; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "[TI=" + this.tiempoI + ", TF=" + this.tiempoF + ", estado=" + this.estado + "]";
    }
}
