package TP5.Solutions;

import java.util.ArrayList;
import java.util.List;

public class LaberintoBacktracking {
    
    /**
     * Clase que representa una celda en el laberinto
     */
    static class Celda {
        private int valor;
        private boolean puedeIrNorte;
        private boolean puedeIrEste;
        private boolean puedeIrSur;
        private boolean puedeIrOeste;
        
        public Celda(int valor, boolean puedeIrNorte, boolean puedeIrEste, boolean puedeIrSur, boolean puedeIrOeste) {
            this.valor = valor;
            this.puedeIrNorte = puedeIrNorte;
            this.puedeIrEste = puedeIrEste;
            this.puedeIrSur = puedeIrSur;
            this.puedeIrOeste = puedeIrOeste;
        }

        public int getValor() {
            return valor;
        }

        public boolean puedoIrNorte() {
            return puedeIrNorte;
        }

        public boolean puedoIrEste() {
            return puedeIrEste;
        }

        public boolean puedoIrSur() {
            return puedeIrSur;
        }

        public boolean puedoIrOeste() {
            return puedeIrOeste;
        }
    }
    
    /**
     * Clase que representa una posición en el laberinto (fila, columna)
     */
    static class Posicion {
        private int fila;
        private int columna;
        
        public Posicion(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
        }
        
        public int getFila() {
            return fila;
        }
        
        public int getColumna() {
            return columna;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Posicion posicion = (Posicion) obj;
            return fila == posicion.fila && columna == posicion.columna;
        }
        
        @Override
        public String toString() {
            return "(" + fila + ", " + columna + ")";
        }
    }
    
    private Celda[][] laberinto;
    private int tamano;
    private int longitudMinima;
    private List<Posicion> caminoMinimo;
    
    /**
     * Crea un nuevo laberinto de tamaño n x n
     */
    public LaberintoBacktracking(int n) {
        this.tamano = n;
        this.laberinto = new Celda[n][n];
        this.longitudMinima = Integer.MAX_VALUE;
        this.caminoMinimo = new ArrayList<>();
    }
    
    /**
     * Establece una celda en el laberinto en la posición (fila, columna)
     */
    public void setCelda(int fila, int columna, int valor, boolean norte, boolean este, boolean sur, boolean oeste) {
        laberinto[fila][columna] = new Celda(valor, norte, este, sur, oeste);
    }
    
    /**
     * Encuentra el camino de longitud mínima entre la posición inicio y destino
     */
    public List<Posicion> encontrarCaminoMinimo(Posicion inicio, Posicion destino) {
        // Reiniciar los valores
        longitudMinima = Integer.MAX_VALUE;
        caminoMinimo = new ArrayList<>();
        
        // Matriz para marcar las celdas visitadas
        boolean[][] visitado = new boolean[tamano][tamano];
        
        // Lista para mantener el camino actual
        List<Posicion> caminoActual = new ArrayList<>();
        caminoActual.add(inicio);
        
        // Marcar la posición inicial como visitada
        visitado[inicio.getFila()][inicio.getColumna()] = true;
        
        // Iniciar el backtracking
        backtracking(inicio, destino, visitado, caminoActual, laberinto[inicio.getFila()][inicio.getColumna()].getValor());
        
        return caminoMinimo;
    }
    
    /**
     * Método recursivo de backtracking para encontrar el camino mínimo
     */
    private void backtracking(Posicion actual, Posicion destino, boolean[][] visitado, 
                            List<Posicion> caminoActual, int longitudActual) {
        
        // Si hemos llegado al destino, comprobamos si el camino es más corto que el mínimo actual
        if (actual.equals(destino)) {
            if (longitudActual < longitudMinima) {
                longitudMinima = longitudActual;
                caminoMinimo = new ArrayList<>(caminoActual);
            }
            return;
        }
        
        // Si la longitud actual ya supera la mínima encontrada, podamos la búsqueda
        if (longitudActual >= longitudMinima) {
            return;
        }
        
        // Probamos mover al Norte
        probarDireccion(actual, destino, visitado, caminoActual, longitudActual, -1, 0);
        
        // Probamos mover al Este
        probarDireccion(actual, destino, visitado, caminoActual, longitudActual, 0, 1);
        
        // Probamos mover al Sur
        probarDireccion(actual, destino, visitado, caminoActual, longitudActual, 1, 0);
        
        // Probamos mover al Oeste
        probarDireccion(actual, destino, visitado, caminoActual, longitudActual, 0, -1);
    }
    
    /**
     * Intenta moverse en una dirección si es posible
     * @param direccion 0=Norte, 1=Este, 2=Sur, 3=Oeste
     */
    private void probarDireccion(Posicion actual, Posicion destino, boolean[][] visitado, 
                               List<Posicion> caminoActual, int longitudActual, 
                               int dFila, int dColumna) {
        
        int filaActual = actual.getFila();
        int columnaActual = actual.getColumna();
        int nuevaFila = filaActual + dFila;
        int nuevaColumna = columnaActual + dColumna;
        
        // Verificar si la nueva posición está dentro de los límites del laberinto
        if (nuevaFila < 0 || nuevaFila >= tamano || nuevaColumna < 0 || nuevaColumna >= tamano) {
            return;
        }
        
        // Verificar si ya hemos visitado esta posición
        if (visitado[nuevaFila][nuevaColumna]) {
            return;
        }
        
        // Verificar si podemos movernos en la dirección deseada desde la celda actual
        Celda celdaActual = laberinto[filaActual][columnaActual];
        boolean puedeMoverse = false;
        
        if (dFila == -1 && dColumna == 0) { // Norte
            puedeMoverse = celdaActual.puedoIrNorte();
        } else if (dFila == 0 && dColumna == 1) { // Este
            puedeMoverse = celdaActual.puedoIrEste();
        } else if (dFila == 1 && dColumna == 0) { // Sur
            puedeMoverse = celdaActual.puedoIrSur();
        } else if (dFila == 0 && dColumna == -1) { // Oeste
            puedeMoverse = celdaActual.puedoIrOeste();
        }
        
        if (puedeMoverse) {
            // Debugging
            // System.out.println("Moviendo desde (" + filaActual + "," + columnaActual + ") a (" + nuevaFila + "," + nuevaColumna + ")");
            
            // Crear la nueva posición
            Posicion nuevaPosicion = new Posicion(nuevaFila, nuevaColumna);
            
            // Añadir la nueva posición al camino actual
            caminoActual.add(nuevaPosicion);
            
            // Marcar la nueva posición como visitada
            visitado[nuevaFila][nuevaColumna] = true;
            
            // Calcular la nueva longitud sumando el valor de la nueva celda
            int nuevaLongitud = longitudActual + laberinto[nuevaFila][nuevaColumna].getValor();
            
            // Continuar el backtracking desde la nueva posición
            backtracking(nuevaPosicion, destino, visitado, caminoActual, nuevaLongitud);
            
            // Retroceder: eliminar la última posición del camino actual y marcarla como no visitada
            caminoActual.remove(caminoActual.size() - 1);
            visitado[nuevaFila][nuevaColumna] = false;
        }
    }
    
    /**
     * Método principal para probar la implementación
     */
    /**
     * Imprime el laberinto mostrando los valores y paredes
     */
    public void imprimirLaberinto() {
        for (int i = 0; i < tamano; i++) {
            // Imprimir paredes horizontales superiores
            for (int j = 0; j < tamano; j++) {
                System.out.print("+");
                System.out.print(laberinto[i][j].puedoIrNorte() ? "   " : "---");
            }
            System.out.println("+");
            
            // Imprimir paredes verticales y valores
            for (int j = 0; j < tamano; j++) {
                System.out.print(laberinto[i][j].puedoIrOeste() ? " " : "|");
                System.out.print(" " + laberinto[i][j].getValor() + " ");
            }
            System.out.println("|"); // Última pared vertical
        }
        
        // Imprimir última fila de paredes horizontales
        for (int j = 0; j < tamano; j++) {
            System.out.print("+");
            System.out.print("---");
        }
        System.out.println("+");
    }
    
    /**
     * Imprime el laberinto con el camino mínimo marcado
     */
    public void imprimirLaberintoConCamino(List<Posicion> camino) {
        // Crear una matriz para representar el laberinto
        char[][] representacion = new char[tamano * 2 + 1][tamano * 2 + 1];
        
        // Inicializar la matriz con espacios en blanco
        for (int i = 0; i < representacion.length; i++) {
            for (int j = 0; j < representacion[i].length; j++) {
                representacion[i][j] = ' ';
            }
        }
        
        // Dibujar las paredes exteriores
        for (int i = 0; i < representacion.length; i++) {
            representacion[0][i] = '-';
            representacion[representacion.length - 1][i] = '-';
        }
        
        for (int i = 0; i < representacion.length; i++) {
            representacion[i][0] = '|';
            representacion[i][representacion.length - 1] = '|';
        }
        
        // Dibujar las celdas y sus paredes
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                // Posición del centro de la celda en la matriz de representación
                int filaRep = i * 2 + 1;
                int colRep = j * 2 + 1;
                
                // Valor de la celda o 'X' si está en el camino
                boolean enCamino = false;
                for (Posicion p : camino) {
                    if (p.getFila() == i && p.getColumna() == j) {
                        enCamino = true;
                        break;
                    }
                }
                
                if (enCamino) {
                    representacion[filaRep][colRep] = 'X';
                } else {
                    representacion[filaRep][colRep] = (char) ('0' + laberinto[i][j].getValor());
                }
                
                // Dibujar paredes según las direcciones permitidas
                if (!laberinto[i][j].puedoIrNorte()) {
                    representacion[filaRep - 1][colRep] = '-';
                }
                if (!laberinto[i][j].puedoIrEste()) {
                    representacion[filaRep][colRep + 1] = '|';
                }
                if (!laberinto[i][j].puedoIrSur()) {
                    representacion[filaRep + 1][colRep] = '-';
                }
                if (!laberinto[i][j].puedoIrOeste()) {
                    representacion[filaRep][colRep - 1] = '|';
                }
            }
        }
        
        // Imprimir la representación
        for (int i = 0; i < representacion.length; i++) {
            for (int j = 0; j < representacion[i].length; j++) {
                System.out.print(representacion[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * Método principal para probar la implementación
     */
    public static void main(String[] args) {
        // Crear un laberinto de ejemplo 4x4
        LaberintoBacktracking laberinto = new LaberintoBacktracking(4);
        
        // Configurar las celdas del laberinto (valor, norte, este, sur, oeste)
        // Se ha modificado para asegurar que existe al menos un camino válido desde (0,0) hasta (3,3)
        // Fila 0
        laberinto.setCelda(0, 0, 1, false, true, true, false);
        laberinto.setCelda(0, 1, 2, false, true, true, true);
        laberinto.setCelda(0, 2, 1, false, true, true, true);
        laberinto.setCelda(0, 3, 3, false, false, true, true);
        
        // Fila 1
        laberinto.setCelda(1, 0, 2, true, true, true, false);
        laberinto.setCelda(1, 1, 8, true, true, false, true);
        laberinto.setCelda(1, 2, 4, true, true, true, true);
        laberinto.setCelda(1, 3, 2, true, false, true, true);
        
        // Fila 2
        laberinto.setCelda(2, 0, 3, true, false, true, false);
        laberinto.setCelda(2, 1, 5, false, true, true, false);
        laberinto.setCelda(2, 2, 2, true, true, true, true);
        laberinto.setCelda(2, 3, 3, true, false, true, true);
        
        // Fila 3
        laberinto.setCelda(3, 0, 4, true, true, false, false);
        laberinto.setCelda(3, 1, 2, true, true, false, true);
        laberinto.setCelda(3, 2, 1, true, true, false, true);
        laberinto.setCelda(3, 3, 2, true, false, false, true);
        
        // Definir posiciones de inicio y destino
        Posicion inicio = new Posicion(0, 0);
        Posicion destino = new Posicion(3, 3);
        
        // Encontrar el camino mínimo
        List<Posicion> caminoMinimo = laberinto.encontrarCaminoMinimo(inicio, destino);
        
        // Mostrar el resultado
        // Imprimir el laberinto inicial
        System.out.println("Laberinto inicial:");
        laberinto.imprimirLaberinto();
        
        System.out.println("\nBuscando camino mínimo de (0,0) a (3,3)...");
        
        if (caminoMinimo.isEmpty()) {
            System.out.println("\nNo se encontró un camino entre las posiciones dadas.");
        } else {
            System.out.println("\nCamino mínimo encontrado (longitud " + laberinto.longitudMinima + "):");
            for (Posicion p : caminoMinimo) {
                System.out.println(p);
            }
            
            System.out.println("\nLaberinto con el camino marcado (X):");
            laberinto.imprimirLaberintoConCamino(caminoMinimo);
        }
    }
}
