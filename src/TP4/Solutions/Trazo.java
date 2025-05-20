package TP4.Solutions;

public class Trazo {
    private int tiempoI;
    private int tiempoF;
    private String color;

    public Trazo() {
        this.tiempoI = 0;
        this.tiempoF = 0;
        this.color = "blanco";
    }

    public int getTiempoI() { return this.tiempoI; }
    public int getTiempoF() { return this.tiempoF; }
    public String getColor() { return this.color; }

    public void setTiempoI(int tiempoI) { this.tiempoI = tiempoI; }
    public void setTiempoF(int tiempoF) { this.tiempoF = tiempoF; }
    public void setColor(String color) { this.color = color; }

    @Override
    public String toString() {
        return "[TI=" + this.tiempoI + ", TF=" + this.tiempoF + ", color=" + this.color + "]";
    }
}
