package TP5.Solutions;

import java.util.ArrayList;
import java.util.List;

// El robot de limpieza necesita volver desde su posicion actual hasta su base de carga. El robot dispone de un mapa de la casa representado como una matriz, donde cada celda es una posicion de la casa. La matriz posee un 0 si la celda esta vacia, o un 1 si la celda presenta algun obstaculo (por ejemplo, un mueble). Dado que el robot le queda poca bateria, desea encontrar el camino que menos energia le requiere para llegar a la base de carga. Se sabe que el robot NO consume energia cuando avanza, sino cuando tiene que girar para cambiar de direccion. Se desea encontrar entonces el camino desde la ubicacion inicial del robot hasta la base, tal que el robot tenga que hacer la menor cantidad de cambios de direcciones posibles (para consumir la menor energia posibloe), considerando que:
// - Desde una celda solo te puedes mover a las celdas contiguas (izquierda, derecha, arriba, abajo).
// - El robot solo puede moverse por celdas libres (no por celdas con obstaculos).
// - El primer movimiento realizado por el robot no consume energia (no importa a cual de las celdas vecinas se mueva desde su celda inicial).

class Posicion {
    private int x;
    private int y;

    public Posicion(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Robot {
    private Posicion posicionActual;
    private int energia;
    public static final int ENERGIA_GIRO = 1;
    public static final int ENERGIA_AVANCE = 0;

    public Robot(Posicion inicial, int energia) {
        this.posicionActual = inicial;
        this.energia = energia;
    }

    public Posicion getPosicionActual() { return posicionActual; }
    public int getEnergia() { return energia; }
    public void setEnergia(int energia) { this.energia = energia; }
    public void setPosicionActual(Posicion p) { this.posicionActual = p; }
    public boolean puedeMoverse() { return energia > 0; }
}

public class R2023_T {
    private List<Posicion> mejorCamino;
    private int minGiros;
    private int[][] mapa;
    private Posicion destino;
    public static final Posicion[] DIRECCIONES = {
        new Posicion(0, 1), // derecha
        new Posicion(0, -1), // izquierda
        new Posicion(1, 0), // abajo
        new Posicion(-1, 0) // arriba
    };

    public List<Posicion> solucion(int[][] mapa, Posicion inicio, Posicion destino, int energiaInicial) {
        this.mapa = mapa;
        this.destino = destino;
        this.mejorCamino = new ArrayList<>();
        this.minGiros = Integer.MAX_VALUE;
        int filas = mapa.length;
        int cols = mapa[0].length;
        boolean[][] visitado = new boolean[filas][cols];
        List<Posicion> camino = new ArrayList<>();
        camino.add(inicio);
        visitado[inicio.getX()][inicio.getY()] = true;
        for (int d = 0; d < DIRECCIONES.length; d++) {
            int nx = inicio.getX() + DIRECCIONES[d].getX();
            int ny = inicio.getY() + DIRECCIONES[d].getY();
            if (esPosicionValida(nx, ny, visitado)) {
                camino.add(new Posicion(nx, ny));
                visitado[nx][ny] = true;
                backtracking(nx, ny, destino, energiaInicial, 0, d, camino, visitado);
                visitado[nx][ny] = false;
                camino.remove(camino.size()-1);
            }
        }
        return new ArrayList<>(mejorCamino);
    }

    private void backtracking(int x, int y, Posicion destino, int energiaRestante, int giros, int dirAnterior, List<Posicion> camino, boolean[][] visitado) {
        if (x == destino.getX() && y == destino.getY()) {
            if (giros < minGiros) {
                minGiros = giros;
                mejorCamino = new ArrayList<>(camino);
            }
            return;
        }
        for (int d = 0; d < DIRECCIONES.length; d++) {
            int nx = x + DIRECCIONES[d].getX();
            int ny = y + DIRECCIONES[d].getY();
            if (esPosicionValida(nx, ny, visitado)) {
                int nuevoGiro = giros;
                if (dirAnterior != d) nuevoGiro = giros + 1;
                if (energiaRestante - (dirAnterior != d ? 1 : 0) < 0) {
                } else {
                    camino.add(new Posicion(nx, ny));
                    visitado[nx][ny] = true;
                    backtracking(nx, ny, destino, energiaRestante - (dirAnterior != d ? 1 : 0), nuevoGiro, d, camino, visitado);
                    visitado[nx][ny] = false;
                    camino.remove(camino.size()-1);
                }
            }
        }
    }

    private boolean esPosicionValida(int x, int y, boolean[][] visitado) {
        return x >= 0 && x < mapa.length && y >= 0 && y < mapa[0].length && mapa[x][y] == 0 && !visitado[x][y];
    }

    public static void main(String[] args) {
        int[][] mapa = {
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0}
        };
        Posicion inicio = new Posicion(0, 0);
        Posicion destino = new Posicion(4, 4);
        int energiaInicial = 10;
        R2023_T solver = new R2023_T();
        List<Posicion> camino = solver.solucion(mapa, inicio, destino, energiaInicial);
        System.out.println("Camino con menos giros:");
        for (Posicion p : camino) {
            System.out.print(p + " ");
        }
        System.out.println("\nCantidad de giros: " + solver.minGiros);
    }
}
