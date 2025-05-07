
package TP4.TDA.Grafo;

import java.util.Iterator;
import java.util.Map;

import TP4.TDA.Arco.Arco;

public interface Grafo<T> {
	public void agregarVertice(int verticeId); 							// Testeado
	public void borrarVertice(int verticeId);
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta);// Testeado
	public void borrarArco(int verticeId1, int verticeId2);
	public boolean contieneVertice(int verticeId);						// Testeado
	public boolean existeArco(int verticeId1, int verticeId2);
	public Arco<T> obtenerArco(int verticeId1, int verticeId2);
	public int cantidadVertices(); 										// Testeado
	public int cantidadArcos();											// Testeado
	public Iterator<Integer> obtenerVertices(); 						// Testeado
	public Iterator<Integer> obtenerAdyacentes(int verticeId); 			// Testeado
	public Iterator<Arco<T>> obtenerArcos(); 							// Testeado
	public Iterator<Arco<T>> obtenerArcos(int verticeId); 				// Testeado
	public Map<Integer, Tmp> DFS();
}
