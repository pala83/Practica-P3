package Trabajos_Pracicos.Estructuras;

public class GrafoND extends GrafoD {

	@Override
	public void agregarArco(int verticeId1, int verticeId2, int etiqueta) {
		super.agregarArco(verticeId1, verticeId2, etiqueta);
		super.agregarArco(verticeId2, verticeId1, etiqueta);
	}
	
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		super.borrarArco(verticeId1, verticeId2);
		super.borrarArco(verticeId2, verticeId1);
	}

	@Override
	public int cantidadArcos() {
		return super.cantidadArcos() / 2;
	}
}
