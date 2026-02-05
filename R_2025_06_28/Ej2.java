package Trabajos_Pracicos.R_2025_06_28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Debido a un mal funcionamiento de una de las maquinas que generan piezas, la empresa se encentra con la situacion de tener que enviar a un deposito la cantidad de piezas sobrantes. Los empleados han ubicado dichas piezas en un numero X de cajas, cada una con su propio peso.
Se sabe que el transporte contratado puede llevar un peso total P en cada viaje. Se desea encontrar el orden en que deben ser cargadas las cajas en el transporte de manera de minimizar el total de viajes a realizar
    a) Describa con sus palabras cual seria la estrategia Greedy que seguiria.
    b) Plantee un codigo JAVA que lo resuelva mediante dicha estrategia Greedy.
*/

public class Ej2 {

    /*
     * a) Estrategia Greedy (First Fit Decreasing):
     * 1. Ordenar las cajas de mayor a menor peso.
     * 2. Mantener una lista de viajes activos.
     * 3. Para cada caja, intentar ubicarla en el primer viaje existente donde quepa.
     * 4. Si no cabe en ningún viaje existente, crear uno nuevo y cargarla allí.
     * 
     * Esta estrategia busca minimizar los viajes aprovechando los espacios libres de los viajes
     * ya abiertos antes de abrir uno nuevo.
     */

    private class Viaje {
        private int capacidadDisponible;
        private List<Integer> cajas;

        public Viaje(int capacidadMaxima) {
            this.capacidadDisponible = capacidadMaxima;
            this.cajas = new ArrayList<>();
        }

        public boolean puedeCargar(int pesoCaja) {
            return pesoCaja <= capacidadDisponible;
        }

        public void cargar(int pesoCaja) {
            cajas.add(pesoCaja);
            capacidadDisponible -= pesoCaja;
        }

        @Override
        public String toString() {
            return cajas.toString() + " - Espacio libre: " + capacidadDisponible;
        }
    }

    public void planificarViajes(Integer[] cajas, int capacidadP) {
        // 1. Ordenar de mayor a menor
        Arrays.sort(cajas, Collections.reverseOrder());
        
        List<Viaje> viajes = new ArrayList<>();

        // 2. Iterar cada caja
        for (Integer pesoCaja : cajas) {
            boolean cargada = false;
            
            // 3. Buscar un viaje donde entre (First Fit)
            for (Viaje viaje : viajes) {
                if (viaje.puedeCargar(pesoCaja)) {
                    viaje.cargar(pesoCaja);
                    cargada = true;
                    break; // Ya la cargamos, pasamos a la siguiente caja
                }
            }
            
            // 4. Si no entra en ninguno, crear nuevo viaje
            if (!cargada) {
                Viaje nuevoViaje = new Viaje(capacidadP);
                nuevoViaje.cargar(pesoCaja);
                viajes.add(nuevoViaje);
            }
        }

        // Imprimir resultados
        System.out.println("Planificación de viajes (Capacidad: " + capacidadP + "):");
        for (int i = 0; i < viajes.size(); i++) {
            System.out.println("Viaje " + (i + 1) + ": " + viajes.get(i));
        }
        System.out.println("Total de viajes necesarios: " + viajes.size());
    }

    public static void main(String[] args) {
        Ej2 ejercicio = new Ej2();
        
        // Ejemplo de prueba
        Integer[] cajas = {10, 20, 5, 7, 8, 2, 15, 30, 5, 12};
        int capacidadTransporte = 30;
        
        ejercicio.planificarViajes(cajas, capacidadTransporte);
    }
}
