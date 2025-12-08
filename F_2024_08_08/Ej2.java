package Trabajos_Pracicos.F_2024_08_08;

import java.util.ArrayList;
import java.util.List;

// El patrón de desbloqueo de un celular es un método de autenticación que consta de una cuadrícula de 3x3 puntos como la de la derecha. El usuario debe conectar un mínimo de 4 puntos para crear un patrón. Los puntos se conectan trazando una línea continua, sin levantar el dedo de la pantalla. No se puede pasar más de una vez por una conexión entre puntos, y las conexiones desde un punto se pueden hacer a los puntos contiguos. El patrón puede iniciar desde cualquiera de los puntos de la cuadrícula y finalizar en cualquier punto.
// Diseñe un algoritmo mediante la técnica Backtracking que descubra el patrón de desbloqueo del teléfono celular, contando con el siguiente método:
// public boolean esPatronValido(List<Punto> patron)
// Al cual se le pasa una lista de puntos en el orden en que se conectan en el patrón, y el método devuelve true si el patrón logra desbloquear el teléfono o false en caso contrario.

public class Ej2 {

    private static final int MIN_PUNTOS = 4;
    private static final int MAX_PUNTOS = 9;
    
    // Direcciones contiguas: arriba, abajo, izq, der, y las 4 diagonales
    private static final int[] dirF = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static final int[] dirC = {0, 0, -1, 1, -1, 1, -1, 1};
    
    private boolean[][] visitado;
    private List<Punto> patron;
    private List<Punto> patronCorrecto;

    public Ej2(List<Punto> patronCorrecto) {
        this.visitado = new boolean[3][3];
        this.patron = new ArrayList<>();
        this.patronCorrecto = patronCorrecto;
    }

    public List<Punto> descubrirPatron() {
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                List<Punto> resultado = backtrack(fila, col);
                if (resultado != null) {
                    return resultado;
                }
            }
        }
        return null; // No se encontró patrón válido
    }

    private List<Punto> backtrack(int fila, int col) {
        // Agregamos el punto actual al patrón
        Punto puntoActual = new Punto(fila, col);
        patron.add(puntoActual);
        visitado[fila][col] = true;

        // Condicion de corte: Si tenemos al menos MIN_PUNTOS, verificamos si es válido
        if (patron.size() >= MIN_PUNTOS) {
            if (esPatronValido(patron)) {
                return new ArrayList<>(patron); // Retornamos copia del patrón encontrado
            }
        }

        // Si no llegamos al máximo, seguimos explorando
        if (patron.size() < MAX_PUNTOS) {
            // Exploramos los puntos contiguos (8 direcciones)
            for (int d = 0; d < 8; d++) {
                int nuevaFila = fila + dirF[d];
                int nuevaCol = col + dirC[d];

                if (esMovimientoValido(nuevaFila, nuevaCol)) {
                    List<Punto> resultado = backtrack(nuevaFila, nuevaCol);
                    if (resultado != null) {
                        return resultado; // Encontramos un patrón válido
                    }
                }
            }
        }

        // Backtrack: deshacemos el movimiento
        patron.remove(patron.size() - 1);
        visitado[fila][col] = false;
        
        return null;
    }

    private boolean esMovimientoValido(int fila, int col) {
        return fila >= 0 && fila < 3 && 
               col >= 0 && col < 3 && 
               !visitado[fila][col];
    }

    public boolean esPatronValido(List<Punto> patron1) {
        if (patron1 == null || patronCorrecto == null) return patron1 == patronCorrecto;
        if (patron1.size() != patronCorrecto.size()) return false;
        
        for (int i = 0; i < patron1.size(); i++) {
            if (!patron1.get(i).equals(patronCorrecto.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Clase para representar un punto
    public static class Punto {
        private int fila;
        private int col;

        public Punto(int fila, int col) {
            this.fila = fila;
            this.col = col;
        }

        public int getFila() { return fila; }
        public int getCol() { return col; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Punto punto = (Punto) obj;
            return fila == punto.fila && col == punto.col;
        }

        @Override
        public String toString() {
            return "(" + fila + "," + col + ")";
        }
    }

    public static void main(String[] args) {
        // Definimos el patrón correcto a descubrir
        List<Punto> patronCorrecto = List.of(
            new Punto(0, 0),
            new Punto(1, 0),
            new Punto(2, 0),
            new Punto(2, 1)
        );
        
        Ej2 ejercicio = new Ej2(patronCorrecto);
        List<Punto> patronEncontrado = ejercicio.descubrirPatron();
        
        if (patronEncontrado != null) {
            System.out.println("¡Patrón encontrado!");
            System.out.print("Secuencia: ");
            for (Punto p : patronEncontrado) {
                System.out.print(p + " -> ");
            }
            System.out.println("FIN");
        } else {
            System.out.println("No se encontró un patrón válido.");
        }
    }
}