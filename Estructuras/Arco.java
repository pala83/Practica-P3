package Trabajos_Pracicos.Estructuras;

public class Arco {
    private int verticeOrigen;
	private int verticeDestino;
	private int etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, int etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

	public int getEtiqueta() {
		return etiqueta;
	}

	@Override
	public String toString() {
		return "[" + verticeOrigen + " --> " + verticeDestino + " (" + etiqueta + ")]";
	}
}
