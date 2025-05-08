package TP4.TDA.Grafo;

public class Tmp {
	private String color;
	private int TI;
	private int TF;
	public Tmp() {
		this.color = "blanco";
		this.TI = 0;
		this.TF = 0;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getTI() {
		return TI;
	}
	public void setTI(int tI) {
		TI = tI;
	}
	public int getTF() {
		return TF;
	}
	public void setTF(int tF) {
		TF = tF;
	}
    @Override
    public String toString() {
        return "[color=" + color + ", TI=" + TI + ", TF=" + TF + "]";
    }
}
