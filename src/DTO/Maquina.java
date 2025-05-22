package DTO;

public class Maquina {
    private final String id;
    private final int produccion;

    public Maquina(String id, int produccion) {
        this.id = id;
        this.produccion = produccion;
    }
    public String getId() {
        return id;
    }
    public int getProduccion() {
        return produccion;
    }
    @Override
    public String toString() {
        return "[" + id + ", " + produccion + "]";
    }
}
