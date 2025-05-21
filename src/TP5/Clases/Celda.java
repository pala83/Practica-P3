package TP5.Clases;

public class Celda {
    private int valor;
    private boolean norte;
    private boolean este;
    private boolean sur;
    private boolean oeste;

    public Celda(int valor, boolean norte, boolean este, boolean sur, boolean oeste) {
        this.valor = valor;
        this.norte = norte;
        this.este = este;
        this.sur = sur;
        this.oeste = oeste;
    }
    
    public int getValor() { return this.valor; }
    public boolean getPuedeIrNorte() { return this.norte; }
    public boolean getPuedeIrEste() { return this.este; }
    public boolean getPuedeIrSur() { return this.sur; }
    public boolean getPuedeIrOeste() { return this.oeste; }

    public void setValor(int valor) { this.valor = valor; }
    public void setNorte(boolean norte) { this.norte = norte; }
    public void setEste(boolean este) { this.este = este; }
    public void setSur(boolean sur) { this.sur = sur; }
    public void setOeste(boolean oeste) { this.oeste = oeste; }
}
