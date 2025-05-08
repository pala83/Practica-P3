package TP4;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import TP4.TDA.Arco.Arco;
import TP4.TDA.Grafo.GrafoD;
import TP4.TDA.Grafo.Tmp;

public class Main {

    public static void main(String[] args) {
        GrafoD<Double> grafo = new GrafoD<>();
        cargarGrafo(grafo, 7);  
        System.out.println("Grafo random de "+grafo.cantidadVertices()+" vertices :");
        Iterator<Integer> vertices = grafo.obtenerVertices();
        while(vertices.hasNext()){
            Integer vertice = vertices.next();
            Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
            System.out.println("V: " + vertice + " -> " + imprimirVertices(adyacentes));
        }
        System.out.println("Arcos: "+grafo.cantidadArcos()+ " en total");
        Iterator<Arco<Double>> arcos = grafo.obtenerArcos();
        while(arcos.hasNext()){
            Arco<Double> arco = arcos.next();
            System.out.println("A: " + arco.getVerticeOrigen() + " -> " + arco.getVerticeDestino() + " : " + arco.getEtiqueta());
        }

        System.out.println("DFS: ");
        for(Map.Entry<Integer, Tmp> entry : grafo.DFS().entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public static void cargarGrafo(GrafoD<Double> grafo, int vertices) {
        List<Integer> tmp = new LinkedList<>();
        for (int i = 1; i <= vertices; i++) {
            tmp.add(i);
            grafo.agregarVertice(i);
        }
        List<Integer> tmpVertices = new LinkedList<>(tmp);
        while(!tmpVertices.isEmpty()){
            Integer vOrigen = tmpVertices.remove((int)(Math.random()*tmpVertices.size()));
            int cantAdy = (int)(Math.random()*tmp.size());
            for(int j = 0; j < cantAdy; j++){
                Integer vDestino = tmp.get(j);
                if(!vOrigen.equals(vDestino))
                    grafo.agregarArco(vOrigen, vDestino, Math.random()*100);
            }
        }
    }

    public static String imprimirVertices(Iterator<Integer> it) {
        Iterator<Integer> tmp = it;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while(tmp.hasNext()){
            sb.append(tmp.next());
            if(tmp.hasNext()){
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
