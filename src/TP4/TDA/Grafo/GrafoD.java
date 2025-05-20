package TP4.TDA.Grafo;

import java.util.LinkedList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import TP4.Iterable.ArcoIterator;
import TP4.TDA.Arco.Arco;

public class GrafoD<T> implements Grafo<T> {
	private Map<Integer, List<Arco<T>>> adyacentes;

	public GrafoD() {
		this.adyacentes = new HashMap<>();
	}

	@Override
	public void agregarVertice(int verticeId) {
		if(!this.contieneVertice(verticeId))
			this.adyacentes.put(verticeId, new LinkedList<Arco<T>>());
	}

	@Override
	public void borrarVertice(int verticeId) {
		this.adyacentes.remove(verticeId);
		for(Integer vertice : this.adyacentes.keySet()) {
			List<Arco<T>> arcos = this.adyacentes.get(vertice);
			for(Arco<T> arco : arcos) {
				if(arco.getVerticeDestino() == verticeId)
					arcos.remove(arco);
			}
		}
	}

	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if(this.contieneVertice(verticeId1))
			this.adyacentes.get(verticeId1).add(new Arco<T>(verticeId1, verticeId2, etiqueta));
	}

	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(this.contieneVertice(verticeId1)){
			List<Arco<T>> arcos = this.adyacentes.get(verticeId1);
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
			for(Arco<T> arco : this.adyacentes.get(verticeId1))
				if (arco.getVerticeDestino() == verticeId2)
					return true;
		return false;
	}

	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if(this.contieneVertice(verticeId1))
			for(Arco<T> arco : this.adyacentes.get(verticeId1))
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
		for(List<Arco<T>> arcos : this.adyacentes.values()) {
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
		List<Arco<T>> retorno = adyacentes.get(verticeId);
		if(retorno != null)
			return new ArcoIterator<>(retorno.iterator());
		return null;
	}
	
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		List<Arco<T>> arcos = new LinkedList<>();
		for(List<Arco<T>> listaArcos : this.adyacentes.values()) {
			arcos.addAll(listaArcos);
		}
		return arcos.iterator();
	}

	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		if(this.contieneVertice(verticeId))
			return this.adyacentes.get(verticeId).iterator();
		return null;
	}

	@Override
	public Map<Integer, Tmp> DFS() {
		Map<Integer, Tmp> tmpMap = new HashMap<>();
		Iterator<Integer> it = this.obtenerVertices();
		while(it.hasNext()) {
			Integer vertice = it.next();
			tmpMap.put(vertice, new Tmp());
		}
		int tiempo = 0;
		for(Map.Entry<Integer, Tmp> entry : tmpMap.entrySet()) {
			if(entry.getValue().getColor().equals("blanco")) {
				tiempo = this.DFSVisit(entry.getKey(), tiempo, tmpMap, false);
			}
		}
		return tmpMap;
	}

	private int DFSVisit(Integer vertice, int tiempo, Map<Integer, Tmp> tmpMap, boolean isCiclo) {
		Tmp tmp = tmpMap.get(vertice);
		tmp.setColor("amarillo");
		tiempo++;
		tmp.setTI(tiempo);
		Iterator<Integer> ady = this.obtenerAdyacentes(vertice);
		while(ady.hasNext() && !isCiclo) {
			Integer adyacente = ady.next();
			Tmp tmpAdy = tmpMap.get(adyacente);
			if(tmpAdy.getColor().equals("blanco"))
				tiempo = DFSVisit(adyacente, tiempo, tmpMap, isCiclo);
			else if(tmpAdy.getColor().equals("amarillo")) {
				isCiclo = true;
			}
		}
		tmp.setColor("negro");
		tiempo++;
		tmp.setTF(tiempo);
		return tiempo;
	}

	@Override
	public Map<Integer, Tmp> BFS() {
		Map<Integer, Tmp> tmpMap = new HashMap<>();
		Iterator<Integer> it = this.obtenerVertices();

		// Inicializar todos los nodos como "blanco" en el mapa tmpMap
		while (it.hasNext()) {
			Integer vertice = it.next();
			tmpMap.put(vertice, new Tmp());
		}

		int tiempo = 0;
		Queue<Integer> queue = new LinkedList<>();

		// Recorrer todos los nodos del grafo
		for (Map.Entry<Integer, Tmp> entry : tmpMap.entrySet()) {
			if (entry.getValue().getColor().equals("blanco")) {
				// Si el nodo no ha sido visitado, iniciar BFS desde él
				tiempo = BFSVisit(entry.getKey(), tiempo, tmpMap, queue);
			}
		}

		return tmpMap;
	}

	private int BFSVisit(Integer vertice, int tiempo, Map<Integer, Tmp> tmpMap, Queue<Integer> queue) {
		Tmp tmp = tmpMap.get(vertice);

		// Marcar el nodo como visitado (negro) y registrar el tiempo inicial
		tmp.setColor("negro");
		tiempo++;
		tmp.setTI(tiempo);

		// Agregar el nodo a la cola
		queue.add(vertice);

		// Procesar la cola
		while (!queue.isEmpty()) {
			Integer actual = queue.remove();
			Iterator<Integer> ady = this.obtenerAdyacentes(actual);

			// Recorrer los nodos adyacentes
			while (ady.hasNext()) {
				Integer adyacente = ady.next();
				Tmp tmpAdy = tmpMap.get(adyacente);

				if (tmpAdy.getColor().equals("blanco")) {
					// Marcar el nodo adyacente como visitado (negro)
					tmpAdy.setColor("negro");
					tiempo++;
					tmpAdy.setTI(tiempo);

					// Agregar el nodo adyacente a la cola
					queue.add(adyacente);
				}
			}

			// Registrar el tiempo final del nodo actual
			tmpMap.get(actual).setTF(tiempo);
		}

		return tiempo;
	}

	// Ejercicio 3
	@Override
	public List<Integer> ciclo() {
		List<Integer> ciclo = new LinkedList<>();
		Map<Integer, Tmp> tmpMap = new HashMap<>();
		Iterator<Integer> it = this.obtenerVertices();
		while (it.hasNext()) {
			Integer vertice = it.next();
			tmpMap.put(vertice, new Tmp());
		}
		for (Integer vertice : tmpMap.keySet()) {
			if (tmpMap.get(vertice).getColor().equals("blanco")) {
				if (DFSDetectarCiclo(vertice, tmpMap, ciclo, new LinkedList<>())) {
					return ciclo;
				}
			}
		}
		return ciclo;
	}

	private boolean DFSDetectarCiclo(Integer vertice, Map<Integer, Tmp> tmpMap, List<Integer> ciclo, LinkedList<Integer> stack) {
		Tmp tmp = tmpMap.get(vertice);
		tmp.setColor("amarillo");
		stack.add(vertice);

		Iterator<Integer> ady = this.obtenerAdyacentes(vertice);
		while (ady.hasNext()) {
			Integer adyacente = ady.next();
			Tmp tmpAdy = tmpMap.get(adyacente);

			if (tmpAdy.getColor().equals("blanco")) {
				if (DFSDetectarCiclo(adyacente, tmpMap, ciclo, stack)) {
					return true;
				}
			} else if (tmpAdy.getColor().equals("amarillo")) {
				ciclo.addAll(stack.subList(stack.indexOf(adyacente), stack.size()));
				ciclo.add(adyacente);
				return true;
			}
		}

		tmp.setColor("negro");
		stack.removeLast();
		return false;
	}

	@Override
	public List<Integer> caminoMasLargo(int i, int j) {
		List<Integer> caminoActual = new LinkedList<>();
		List<Integer> caminoMasLargo = new LinkedList<>();
		Map<Integer, Boolean> visitados = new HashMap<>();
		for (Integer vertice : this.adyacentes.keySet()) {
			visitados.put(vertice, false);
		}
		dfsCaminoMasLargo(i, j, visitados, caminoActual, caminoMasLargo);
		return caminoMasLargo;
	}

	private void dfsCaminoMasLargo(int actual, int destino, Map<Integer, Boolean> visitados, List<Integer> caminoActual, List<Integer> caminoMasLargo) {
		visitados.put(actual, true);
		caminoActual.add(actual);
		if (actual == destino) {
			if (caminoActual.size() > caminoMasLargo.size()) {
				caminoMasLargo.clear();
				caminoMasLargo.addAll(caminoActual);
			}
		} else {
			Iterator<Integer> adyacentes = this.obtenerAdyacentes(actual);
			while (adyacentes.hasNext()) {
				Integer adyacente = adyacentes.next();
				if (!visitados.get(adyacente)) {
					dfsCaminoMasLargo(adyacente, destino, visitados, caminoActual, caminoMasLargo);
				}
			}
		}
		visitados.put(actual, false);
		caminoActual.remove(caminoActual.size() - 1);
	}
}

public class DFS {
	private Grafo<?> grafo;
	private HashMap<Integer, String> colores;

	public DFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.colores = new HashMap<>();
	}

	public void dfs(){
		Iterator<Integer> itVert = this.grafo.obtenerVertices();
		while(itVert.hasNext()){
			Integer vertice = itVert.next();
			colores.put(vertice, "blanco");
		}
		itVert = this.grafo.obtenerVertices();
		while(itVert.hasNext()){
			Integer vertice = itVert.next();
			if(colores.get(vertice).equals("blanco"))
				this.dfsVisit(vertice);
		}
	}

	private void dfsVisit(Integer vertice){
		colores.put(vertice, "amarillo");
		Iterator<Integer> adyacentes = this.grafo.obtenerAdyacentes(vertice);
		while(adyacentes.hasNext()){
			Integer adyacente = adyacentes.next();
			if(colores.get(adyacente).equals("blanco"))
				dfsVisit(adyacente);
		}
		colores.put(vertice, "negro");
	}
}