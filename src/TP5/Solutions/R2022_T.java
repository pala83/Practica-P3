package TP5.Solutions;
// Sudoku: Dado un tablero de 9x9 que internamente esta dividido en 9 regiones de 3x3, donde ya se han colocado algunos numeros, se desea determinar que numeros del 1 al 9 hay que ubicar en cada casillero vacio de forma de completar el tablero pero sin repetir numeros por filas, columnas, ni dentro de cada region de 3x3.
// Se solicita plantear un algoritmo mediante estrategia Backtracking que resuelva el problema propuesto.
public class R2022_T {
    private static final int TAMANO = 9;
    private static final int SUBCUADRO = 3;
    private static final int VACIO = 0;

    private int[][] tablero;
    private boolean solucionEncontrada;

    public R2022_T(int[][] tableroInicial) {
        this.tablero = new int[TAMANO][TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            System.arraycopy(tableroInicial[i], 0, this.tablero[i], 0, TAMANO);
        }
        this.solucionEncontrada = false;
    }

    public boolean getSolucionEncontrada() {
        return solucionEncontrada;
    }

    public boolean resolver() {
        return resolver(0, 0);
    }

    private boolean resolver(int fila, int col) {
        if (fila == TAMANO) {
            solucionEncontrada = true;
            return true;
        }
        if (tablero[fila][col] != VACIO) {
            return siguienteCelda(fila, col);
        }
        for (int num = 1; num <= TAMANO; num++) {
            if (esValido(fila, col, num)) {
                tablero[fila][col] = num;
                if (siguienteCelda(fila, col)) {
                    return true;
                }
                tablero[fila][col] = VACIO;
            }
        }
        return false;
    }

    private boolean siguienteCelda(int fila, int col) {
        if (col == TAMANO - 1) {
            return resolver(fila + 1, 0);
        } else {
            return resolver(fila, col + 1);
        }
    }

    private boolean esValido(int fila, int col, int num) {
        for (int x = 0; x < TAMANO; x++) {
            if (tablero[fila][x] == num) {
                return false;
            }
        }
        for (int x = 0; x < TAMANO; x++) {
            if (tablero[x][col] == num) {
                return false;
            }
        }
        int subFilaInicio = fila - fila % SUBCUADRO;
        int subColInicio = col - col % SUBCUADRO;
        
        for (int i = 0; i < SUBCUADRO; i++) {
            for (int j = 0; j < SUBCUADRO; j++) {
                if (tablero[subFilaInicio + i][subColInicio + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public void imprimirTablero() {
        for (int i = 0; i < TAMANO; i++) {
            if (i % SUBCUADRO == 0 && i != 0) {
                System.out.println("----------------------");
            }
            for (int j = 0; j < TAMANO; j++) {
                if (j % SUBCUADRO == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] tableroInicial = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        R2022_T sudoku = new R2022_T(tableroInicial);
        
        System.out.println("Tablero inicial:");
        sudoku.imprimirTablero();
        
        System.out.println("\nResolviendo...\n");
        
        if (sudoku.resolver()) {
            System.out.println("Solución encontrada:");
            sudoku.imprimirTablero();
        } else {
            System.out.println("No existe solución para el tablero dado.");
        }
    }
}
