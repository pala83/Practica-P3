package Trabajos_Pracicos.F_2024_08_08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Roberto tiene un empleo nuevo en un depósito de cajas, y su primera tarea es organizar todas las cajas. Al ser su primer día, quiere hacer un buen trabajo, y decide ubicar las cajas en columnas para ahorrar la mayor cantidad de espacio del depósito posible. De cada caja, Roberto conoce su peso (medida en kilogramos) y su resistencia (también medida en kilogramos). Por ejemplo, si sobre una caja que tiene una resistencia de 100 kilogramos se apilan cajas que juntas suman más de 100 kg, la caja en cuestión no podrá resistir el peso y se romperá.
// Roberto quiere apilar las cajas en la menor cantidad de columnas para ahorrar espacio, pero sin que ninguna caja se rompa.
// Se pide plantear un algoritmo que mediante estrategia Greedy determine en cuántas columnas deberá apilar las cajas Roberto para completar su tarea. Responda:
//  a) Describir cuál sería la estrategia Greedy que seguirá.
//  b) Plantear el algoritmo en Java que lo resuelva mediante dicha estrategia Greedy.

/*
 * ============================================================================
 * a) ESTRATEGIA GREEDY:
 * ============================================================================
 * 
 * 1. ORDENAR las cajas por resistencia de MAYOR a MENOR.
 *    - Las cajas más resistentes van abajo (soportan más peso encima).
 * 
 * 2. Para cada caja (en orden de mayor a menor resistencia):
 *    - Intentar colocarla en una columna existente donde la caja de arriba
 *      pueda soportar el peso de esta nueva caja.
 *    - Si no cabe en ninguna columna existente, crear una nueva columna.
 * 
 * 3. La decisión greedy: siempre colocamos la caja en la primera columna
 *    donde quepa, priorizando reutilizar columnas existentes.
 * 
 * ¿Por qué funciona?
 * - Al ordenar por resistencia descendente, garantizamos que las cajas
 *   más fuertes estén abajo y las más débiles arriba.
 * - Cada caja solo necesita soportar el peso de las cajas que están ENCIMA.
 * 
 * ============================================================================
 */

public class Ej3 {

    // Clase para representar una caja
    public static class Caja implements Comparable<Caja> {
        private int peso;
        private int resistencia;

        public Caja(int peso, int resistencia) {
            this.peso = peso;
            this.resistencia = resistencia;
        }

        public int getPeso() { return peso; }
        public int getResistencia() { return resistencia; }

        // Ordenar por resistencia de MAYOR a MENOR
        @Override
        public int compareTo(Caja otra) {
            return Integer.compare(otra.resistencia, this.resistencia);
        }

        @Override
        public String toString() {
            return "(P:" + peso + ", R:" + resistencia + ")";
        }
    }

    // Clase para representar una columna de cajas
    public static class Columna {
        private List<Caja> cajas;
        private int pesoTotal; // Peso acumulado de todas las cajas en la columna

        public Columna() {
            this.cajas = new ArrayList<>();
            this.pesoTotal = 0;
        }

        // Verifica si se puede agregar una caja encima de la columna
        public boolean puedeAgregar(Caja caja) {
            if (cajas.isEmpty()) {
                return true; // Columna vacía, siempre se puede
            }
            // La caja del tope debe soportar el peso de la nueva caja
            Caja cajaDelTope = cajas.get(cajas.size() - 1);
            return cajaDelTope.getResistencia() >= caja.getPeso();
        }

        // Agrega una caja a la columna
        public void agregar(Caja caja) {
            cajas.add(caja);
            pesoTotal += caja.getPeso();
        }

        public List<Caja> getCajas() { return cajas; }
        public int getPesoTotal() { return pesoTotal; }
    }

    /**
     * b) ALGORITMO GREEDY
     * Determina el mínimo número de columnas necesarias para apilar las cajas.
     */
    public int minimoCantidadColumnas(List<Caja> cajas) {
        if (cajas == null || cajas.isEmpty()) {
            return 0;
        }

        // 1. Ordenar cajas por resistencia de MAYOR a MENOR
        List<Caja> cajasOrdenadas = new ArrayList<>(cajas);
        Collections.sort(cajasOrdenadas);

        // 2. Lista de columnas
        List<Columna> columnas = new ArrayList<>();

        // 3. Para cada caja, intentar ubicarla en una columna existente
        for (Caja caja : cajasOrdenadas) {
            boolean ubicada = false;

            // Buscar una columna donde quepa la caja
            for (Columna columna : columnas) {
                if (columna.puedeAgregar(caja)) {
                    columna.agregar(caja);
                    ubicada = true;
                    break; // Decisión greedy: primera columna disponible
                }
            }

            // Si no cabe en ninguna columna, crear una nueva
            if (!ubicada) {
                Columna nuevaColumna = new Columna();
                nuevaColumna.agregar(caja);
                columnas.add(nuevaColumna);
            }
        }

        return columnas.size();
    }

    /**
     * Versión que también retorna cómo quedaron organizadas las columnas.
     */
    public List<Columna> organizarCajas(List<Caja> cajas) {
        List<Caja> cajasOrdenadas = new ArrayList<>(cajas);
        Collections.sort(cajasOrdenadas);
        List<Columna> columnas = new ArrayList<>();

        for (Caja caja : cajasOrdenadas) {
            boolean ubicada = false;

            for (Columna columna : columnas) {
                if (columna.puedeAgregar(caja)) {
                    columna.agregar(caja);
                    ubicada = true;
                    break;
                }
            }

            if (!ubicada) {
                Columna nuevaColumna = new Columna();
                nuevaColumna.agregar(caja);
                columnas.add(nuevaColumna);
            }
        }

        return columnas;
    }

    public static void main(String[] args) {

        List<Caja> cajas = new ArrayList<>();
        cajas.add(new Caja(50, 100));
        cajas.add(new Caja(30, 80));
        cajas.add(new Caja(40, 60));
        cajas.add(new Caja(20, 30));
        cajas.add(new Caja(10, 20));

        Ej3 ejercicio = new Ej3();
        
        System.out.println("=== Ejemplo 1 ===");
        System.out.println("Cajas: " + cajas);
        List<Columna> resultado = ejercicio.organizarCajas(cajas);
        System.out.println("Cantidad de columnas: " + resultado.size());
        for (int i = 0; i < resultado.size(); i++) {
            System.out.println("Columna " + (i + 1) + ": " + resultado.get(i).getCajas());
        }

        // Ejemplo 2: Cajas que necesitan más columnas
        System.out.println("\n=== Ejemplo 2 ===");
        List<Caja> cajas2 = new ArrayList<>();
        cajas2.add(new Caja(100, 50));  // Muy pesada, poca resistencia
        cajas2.add(new Caja(80, 40));   
        cajas2.add(new Caja(60, 30));   
        cajas2.add(new Caja(10, 200));  // Liviana, muy resistente
        
        System.out.println("Cajas: " + cajas2);
        List<Columna> resultado2 = ejercicio.organizarCajas(cajas2);
        System.out.println("Cantidad de columnas: " + resultado2.size());
        for (int i = 0; i < resultado2.size(); i++) {
            System.out.println("Columna " + (i + 1) + ": " + resultado2.get(i).getCajas());
        }
    }
}
