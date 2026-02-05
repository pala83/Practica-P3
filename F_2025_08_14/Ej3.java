package Trabajos_Pracicos.F_2025_08_14;

import java.util.ArrayList;
import java.util.List;
/*
Se quiere organizar la distribución de invitados a las mesas en una fiesta de casamiento.
Hay N invitados, y M mesas de k lugares cada mesa. (siendo M·k ≥ N).

Se dispone de una función de afinidad que dada una lista de entre 1 y k invitados que se sentarán a una mesa, devuelve un valor entero positivo de afinidad entre esas personas, a mayor valor devuelva, mayor afinidad entre ellas:
public int calcularAfinidad(List<Invitado> grupo)

Escriba un algoritmo en JAVA mediante la técnica Backtracking que tenga la siguiente especificación:

public Asignacion asignarMesas(List<Invitado> invitados, int M, int k)

Debe retornar la asignación de invitados a las mesas que maximice el valor total de afinidad.
Ud. decida y explique la especificación de la clase Asignacion.

Pueden quedar mesas vacías (tendrán afinidad cero) o incompletas, pero todos los invitados deben tener una mesa asignada.
El algoritmo debe contar con función de poda.

a) Dibuje el árbol de exploración del algoritmo, indicando qué decisiones se toman en cada paso y qué información se lleva en los estados.
b) Escriba en JAVA un algoritmo de backtracking que lo resuelva.
c) Explique la poda implementada.
*/
public class Ej3 {

    /*
     * a) Árbol de exploración:
     * - Raíz: Estado inicial sin invitados asignados.
     * - Nodos intermedios: Representan decisiones parciales donde se ha asignado un subconjunto de invitados.
     * - Ramas: Cada rama desde un nodo representa la decisión de asignar el invitado actual a una de las M mesas disponibles.
     * - Hojas: Estados donde los N invitados han sido asignados.
     * 
     * Información del Estado:
     * - Índice del invitado que se está considerando (nivel del árbol).
     * - Configuración actual de las mesas (qué invitados están en qué mesa).
     * 
     * c) Poda implementada:
     * 1. Poda por Capacidad: Si una mesa ha alcanzado su capacidad máxima 'k', se poda la rama que intenta agregar otro invitado a esa mesa.
     * 2. Poda por Simetría (Mesas Vacías): Dado que las mesas son inicialmente idénticas, asignar un invitado a la mesa vacía 1 es equivalente
     *    a asignarlo a la mesa vacía 2. Para evitar explorar estados redundantes (permutaciones de mesas), si el algoritmo intenta asignar
     *    un invitado a una mesa vacía, no intentará asignarlo a otras mesas vacías posteriores en el mismo paso recursivo.
     */

    // Clase dummy para representar un Invitado
    public static class Invitado {
        String nombre;
        public Invitado(String nombre) { this.nombre = nombre; }
        @Override public String toString() { return nombre; }
    }

    // Clase Asignacion: Encapsula la distribución de invitados en mesas y la afinidad total.
    public static class Asignacion {
        List<List<Invitado>> mesas;
        int afinidadTotal;

        public Asignacion(int M) {
            mesas = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                mesas.add(new ArrayList<>());
            }
            afinidadTotal = 0;
        }

        // Constructor de copia profunda
        public Asignacion(Asignacion otra) {
            this.mesas = new ArrayList<>();
            for (List<Invitado> mesa : otra.mesas) {
                this.mesas.add(new ArrayList<>(mesa));
            }
            this.afinidadTotal = otra.afinidadTotal;
        }
        
        @Override
        public String toString() {
            return "Afinidad: " + afinidadTotal + ", Mesas: " + mesas;
        }
    }

    private int maxAfinidad = -1;
    private Asignacion mejorAsignacion = null;

    // Método simulado de afinidad (Black box)
    public int calcularAfinidad(List<Invitado> grupo) {
        if (grupo.isEmpty()) return 0;
        // Ejemplo simple: afinidad basada en tamaño para probar
        return grupo.size() * grupo.size(); 
    }

    public Asignacion asignarMesas(List<Invitado> invitados, int M, int k) {
        Asignacion estadoInicial = new Asignacion(M);
        maxAfinidad = -1;
        mejorAsignacion = null;

        backtrack(invitados, 0, estadoInicial, M, k);

        return mejorAsignacion;
    }

    private void backtrack(List<Invitado> invitados, int index, Asignacion estadoActual, int M, int k) {
        // Caso Base: Todos los invitados asignados
        if (index == invitados.size()) {
            int afinidadActual = calcularAfinidadTotal(estadoActual);
            if (afinidadActual > maxAfinidad) {
                maxAfinidad = afinidadActual;
                estadoActual.afinidadTotal = maxAfinidad;
                mejorAsignacion = new Asignacion(estadoActual);
            }
            return;
        }

        Invitado invitado = invitados.get(index);

        // Iterar sobre las mesas disponibles
        for (int i = 0; i < M; i++) {
            List<Invitado> mesa = estadoActual.mesas.get(i);

            // Poda 1: Capacidad
            if (mesa.size() < k) {
                boolean estabaVacia = mesa.isEmpty();

                // Do: Agregar invitado a la mesa
                mesa.add(invitado);

                // Recurse
                backtrack(invitados, index + 1, estadoActual, M, k);

                // Undo: Quitar invitado (Backtracking)
                mesa.remove(mesa.size() - 1);

                // Poda 2: Simetría de mesas vacías
                // Si la mesa estaba vacía, acabamos de explorar la opción de "inaugurar una mesa nueva".
                // No tiene sentido intentar inaugurar la mesa i+1, i+2, etc., porque son equivalentes.
                // Por lo tanto, si probamos una mesa vacía, cortamos el bucle de mesas.
                if (estabaVacia) {
                    break;
                }
            }
        }
    }

    private int calcularAfinidadTotal(Asignacion asignacion) {
        int total = 0;
        for (List<Invitado> mesa : asignacion.mesas) {
            total += calcularAfinidad(mesa);
        }
        return total;
    }

    public static void main(String[] args) {
        Ej3 ejercicio = new Ej3();
        List<Invitado> invitados = new ArrayList<>();
        for (int i = 1; i <= 5; i++) invitados.add(new Invitado("Inv" + i));
        
        int M = 3; // 3 mesas
        int k = 2; // 2 personas por mesa
        
        Asignacion resultado = ejercicio.asignarMesas(invitados, M, k);
        System.out.println("Mejor asignación encontrada: " + resultado);
    }
}
