package Ej2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MochilaFraccionaria {
    private Elemento[] elementos;
    private int capacidad;

    public MochilaFraccionaria(Elemento[] elementos, int capacidad) {
        Arrays.sort(elementos);
        this.elementos = elementos;
        this.capacidad = capacidad;
    }

    public List<Elemento> asignarElementos() {
        double pesoAcumulado = 0;
        int elem = 0;
        List<Elemento> retorno = new LinkedList<Elemento>();
        while(elem < this.elementos.length && pesoAcumulado < this.capacidad) {
            if(pesoAcumulado + this.elementos[elem].getPeso() <= this.capacidad) {
                pesoAcumulado += this.elementos[elem].getPeso();
                retorno.add(this.elementos[elem]);
            }
            else {
                double pesoRestante = this.capacidad - pesoAcumulado;
                double fraccion = pesoRestante / this.elementos[elem].getPeso();
                double valorFraccion = this.elementos[elem].getValor() * fraccion;
                double porcentaje = fraccion * 100;
                Elemento elemRestante = new Elemento(
                    String.format("%.1f%% de %s", porcentaje, this.elementos[elem].getNombre()),
                    (int)valorFraccion,
                    pesoRestante
                );
                retorno.add(elemRestante);
                pesoAcumulado = this.capacidad; // Llenamos la mochila
            }
            elem++;
        }
        return retorno;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public static void main(String[] args) {
        Elemento[] elementos = {
            new Elemento("Pan", 2500, 1),
            new Elemento("Plomo", 15000, 2),
            new Elemento("Plata", 500000, 0.5),
            new Elemento("Chocolate", 11600, 1)
        };
        MochilaFraccionaria mochila = new MochilaFraccionaria(elementos, 2);
        List<Elemento> asignacion = mochila.asignarElementos();
        System.out.println("Asignación:");
        for (Elemento elem : asignacion) {
            System.out.println(elem.getNombre() + " (" + elem.getPeso() + " kg, $" + elem.getValor() + ")");
        }
    }
}
