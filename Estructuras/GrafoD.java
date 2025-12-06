package Trabajos_Pracicos.Estructuras;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Trabajos_Pracicos.Estructuras.Iterable.ArcoIterator;


public class GrafoD implements Grafo {
	private Map<Integer, List<Arco>> adyacentes;

	public GrafoD() {
		this.adyacentes = new HashMap<>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		if(!this.contieneVertice(verticeId))
			this.adyacentes.put(verticeId, new LinkedList<Arco>());
	}

	@Override
	public void borrarVertice(int verticeId) {
		this.adyacentes.remove(verticeId);
		for(Integer vertice : this.adyacentes.keySet()) {
			List<Arco> arcos = this.adyacentes.get(vertice);
			for(Arco arco : arcos) {
				if(arco.getVerticeDestino() == verticeId)
					arcos.remove(arco);
			}
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, int etiqueta) {
		if(this.contieneVertice(verticeId1))
			this.adyacentes.get(verticeId1).add(new Arco(verticeId1, verticeId2, etiqueta));
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(this.contieneVertice(verticeId1)){
			List<Arco> arcos = this.adyacentes.get(verticeId1);
			arcos.remove(this.obtenerArco(verticeId1, verticeId2));
		}
	}

	@Override
	public boolean contieneVertice(int verticeId) {
		return this.adyacentes.containsKey(verticeId);
	}

	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if(this.contieneVertice(verticeId1))
			for(Arco arco : this.adyacentes.get(verticeId1))
				if (arco.getVerticeDestino() == verticeId2)
					return true;
		return false;
	}

	@Override
	public Arco obtenerArco(int verticeId1, int verticeId2) {
		if(this.contieneVertice(verticeId1))
			for(Arco arco : this.adyacentes.get(verticeId1))
				if(arco.getVerticeDestino() == verticeId2)
					return arco;
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.adyacentes.size();
	}

	@Override
	public int cantidadArcos() {
		int size = 0;
		for(List<Arco> arcos : this.adyacentes.values()) {
			size += arcos.size();
		}
		return size;
	}

	@Override
	public Iterator<Integer> obtenerVertices() {
		Set<Integer> vertices = this.adyacentes.keySet();
		return vertices.iterator();
	}

	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		List<Arco> retorno = adyacentes.get(verticeId);
		if(retorno != null)
			return new ArcoIterator(retorno.iterator());
		return null;
	}
	
	@Override
	public Iterator<Arco> obtenerArcos() {
		List<Arco> arcos = new LinkedList<>();
		for(List<Arco> listaArcos : this.adyacentes.values()) {
			arcos.addAll(listaArcos);
		}
		return arcos.iterator();
	}

	@Override
	public Iterator<Arco> obtenerArcos(int verticeId) {
		if(this.contieneVertice(verticeId))
			return this.adyacentes.get(verticeId).iterator();
		return null;
	}

}