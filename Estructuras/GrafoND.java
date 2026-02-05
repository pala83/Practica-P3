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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("GrafoND:\n");
		for (Integer vertice : this.obtenerVertices()) {
			sb.append("Vertice ").append(vertice).append(": ");
			for (Arco arco : this.obtenerArcos(vertice)) {
				sb.append(arco);
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
