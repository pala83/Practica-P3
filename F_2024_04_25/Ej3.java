package Trabajos_Pracicos.F_2024_04_25;

// Se tiene un mapa de una mina de oro representada como una matriz de MxN. Cada celda posee un numero entero positivo representando la cantidad de oro en esa celda, o 0 si la celda esta vacia. Se desea encontrar el camino que mas oro recolecta considerando que:
// - Cada vez que visitas una celda recolectas el oro de esa celda.
// - Desde una celda solo te puedes mover a las celdas contiguas (izq, der, arriba, abajo).
// - No se puede visitar la misma celda mas de una vez ni se pueden visitar celdas con 0 de oro.
// - Puedes iniciar y terminar tu recorrido en cualquier celda del mapa que tenga algo de oro.
// Resolve el problema utilizando Backtracking

public class Ej3 {

    private int[][] mapa;
    private boolean[][] visitado;
    private int filas, columnas;
    private int maxOro;
    
    // Direcciones: arriba, abajo, izquierda, derecha
    private int[] dirFila = {-1, 1, 0, 0};
    private int[] dirCol = {0, 0, -1, 1};

    public Ej3(int[][] mapa) {
        this.mapa = mapa;
        this.filas = mapa.length;
        this.columnas = mapa[0].length;
        this.visitado = new boolean[filas][columnas];
        this.maxOro = 0;
    }

    // El oro actual regresa a su estado anterior por la recursividad, no hace falta restarlo
    private void backtrack(int fila, int col, int oroActual) {
        // Condicion de corte: celda fuera de límites, ya visitada o sin oro
        if (!esValida(fila, col)) return;

        // Recolectamos el oro de la celda actual
        oroActual += mapa[fila][col];
        visitado[fila][col] = true;

        // Actualizamos el máximo si encontramos uno mejor
        if (oroActual > maxOro) {
            maxOro = oroActual;
        }

        // Exploramos las 4 direcciones (arriba, abajo, izq, der)
        for (int d = 0; d < 4; d++) {
            int nuevaFila = fila + dirFila[d];
            int nuevaCol = col + dirCol[d];
            backtrack(nuevaFila, nuevaCol, oroActual);
        }

        // Backtrack: desmarcamos la celda para probar otros caminos
        visitado[fila][col] = false;
    }

    public int encontrarMaxOro() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (mapa[i][j] > 0) {
                    backtrack(i, j, 0);
                }
            }
        }
        return maxOro;
    }

    private boolean esValida(int fila, int col) {
        return fila >= 0 && fila < filas && 
               col >= 0 && col < columnas && 
               !visitado[fila][col] && 
               mapa[fila][col] > 0;
    }

    public static void main(String[] args) {
        int[][] mapa = {
            {0, 6, 0},
            {5, 8, 7},
            {0, 9, 0}
        };
        
        Ej3 minero = new Ej3(mapa);
        int resultado = minero.encontrarMaxOro();
        System.out.println("Máximo oro recolectado: " + resultado); // Esperado: 35 (6+8+7+5+9 o alguna combinación)
        
        // Otro ejemplo
        int[][] mapa2 = {
            {1, 0, 7},
            {2, 0, 6},
            {3, 4, 5},
            {0, 3, 0},
            {9, 0, 20}
        };
        
        Ej3 minero2 = new Ej3(mapa2);
        int resultado2 = minero2.encontrarMaxOro();
        System.out.println("Máximo oro recolectado: " + resultado2);
    }
}
