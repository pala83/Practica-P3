package Ej2;

public class Elemento implements Comparable<Elemento> {
    private String nombre;
    private int valor;
    private double peso;

    public Elemento(String nombre, int valor, double peso) {
        this.nombre = nombre;
        this.valor = valor;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public int getValor() {
        return valor;
    }

    public double getPeso() {
        return peso;
    }

    public double getValorPorPeso() {
        return (double) valor / peso;
    }

    @Override
    public int compareTo(Elemento o) {
        return Double.compare(o.getValorPorPeso(), this.getValorPorPeso());
    }
}
