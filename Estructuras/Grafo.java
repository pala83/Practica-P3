package Trabajos_Pracicos.Estructuras;

public interface Grafo {
	public void agregarVertice(int verticeId); 							// Testeado
	public void borrarVertice(int verticeId);
	public void agregarArco(int verticeId1, int verticeId2, int etiqueta);// Testeado
	public void borrarArco(int verticeId1, int verticeId2);
	public boolean contieneVertice(int verticeId);						// Testeado
	public boolean existeArco(int verticeId1, int verticeId2);
	public Arco obtenerArco(int verticeId1, int verticeId2);
	public int cantidadVertices(); 										// Testeado
	public int cantidadArcos();											// Testeado
	public Iterable<Integer> obtenerVertices(); 						// Testeado
	public Iterable<Integer> obtenerAdyacentes(int verticeId); 			// Testeado
	public Iterable<Arco> obtenerArcos(); 								// Testeado
	public Iterable<Arco> obtenerArcos(int verticeId); 					// Testeado
}
