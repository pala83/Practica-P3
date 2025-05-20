package TP4.Solutions;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import TP4.TDA.Grafo.Grafo;

public class BFS extends Recorrido{
    public BFS(Grafo<?> grafo){
        super(grafo, "No visitado");
    }

    @Override
    public void forest(){
        int tiempo = 0;
        for(Map.Entry<Integer, Trazo> entry : this.trazo.entrySet()) {
            if(entry.getValue().getEstado().equals("No visitado")) {
                tiempo = this.BFSVisit(entry.getKey(), tiempo, new LinkedList<>());
            }
        }
    }

    @Override
    public void simple(Integer vertice){
        int tiempo = 0;
        if(this.trazo.get(vertice).getEstado().equals("No visitado"))
            tiempo = this.BFSVisit(vertice, tiempo, new LinkedList<>());
    }


    private int BFSVisit(Integer vertice, int tiempo, Queue<Integer> queue){
        Trazo trazoVertice = this.trazo.get(vertice);
		trazoVertice.setEstado("Visitado");
		tiempo++;
		trazoVertice.setTiempoI(tiempo);
		queue.add(vertice);
		while (!queue.isEmpty()) {
			Integer actual = queue.remove();
			Iterator<Integer> ady = this.grafo.obtenerAdyacentes(actual);
			while (ady.hasNext()) {
				Integer adyacente = ady.next();
				Trazo tmpAdy = this.trazo.get(adyacente);
				if (tmpAdy.getEstado().equals("No visitado")) {
					tmpAdy.setEstado("Visitado");
					tiempo++;
					tmpAdy.setTiempoI(tiempo);
					queue.add(adyacente);
				}
			}
			this.trazo.get(actual).setTiempoF(tiempo);
		}
		return tiempo;
    }
}
