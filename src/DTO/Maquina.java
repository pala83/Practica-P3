package DTO;

public class Maquina implements Comparable<Maquina> {
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
    @Override
    public int compareTo(Maquina o) {
        return Integer.compare(o.produccion, this.produccion);
    }
}
