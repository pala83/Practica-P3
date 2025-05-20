
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import TP4.TDA.Arco.Arco;
import TP4.TDA.Grafo.GrafoD;
import TP4.TDA.Grafo.Tmp;

public class App {

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

        //System.out.println("DFS: ");
        //for(Map.Entry<Integer, Tmp> entry : grafo.DFS().entrySet()){
        //    System.out.println(entry.getKey() + " -> " + entry.getValue());
        //}
        System.out.println("BFS: ");
        for(Map.Entry<Integer, Tmp> entry : grafo.BFS().entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("Ciclo: " + grafo.ciclo());

        GrafoD<Double> grafoAciclico = new GrafoD<>();
        cargarGrafoAciclico(grafoAciclico, 7);
        System.out.println("Grafo aciclico de "+grafoAciclico.cantidadVertices()+" vertices :");
        Iterator<Integer> verticesAciclico = grafoAciclico.obtenerVertices();
        while(verticesAciclico.hasNext()){
            Integer vertice = verticesAciclico.next();
            Iterator<Integer> adyacentes = grafoAciclico.obtenerAdyacentes(vertice);
            System.out.println("V: " + vertice + " -> " + imprimirVertices(adyacentes));
        }
        System.out.println("Arcos: "+grafoAciclico.cantidadArcos()+ " en total");
        Iterator<Arco<Double>> arcosAciclico = grafoAciclico.obtenerArcos();
        while(arcosAciclico.hasNext()){
            Arco<Double> arco = arcosAciclico.next();
            System.out.println("A: " + arco.getVerticeOrigen() + " -> " + arco.getVerticeDestino() + " : " + arco.getEtiqueta());
        }

        System.out.println("Camino mas largo: " + grafoAciclico.caminoMasLargo(1, 7));
        System.out.println("Camino mas largo: " + grafoAciclico.caminoMasLargo(5, 1));
        System.out.println("Camino mas largo: " + grafoAciclico.caminoMasLargo(1, 3));
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

    public static void cargarGrafoAciclico(GrafoD<Double> grafo, int vertices) {
        List<Integer> listaVertices = new LinkedList<>();
        for (int i = 1; i <= vertices; i++) {
            listaVertices.add(i);
            grafo.agregarVertice(i);
        }
    
        for (int i = 0; i < listaVertices.size(); i++) {
            Integer origen = listaVertices.get(i);
            for (int j = i + 1; j < listaVertices.size(); j++) {
                Integer destino = listaVertices.get(j);
                grafo.agregarArco(origen, destino, Math.random() * 100);
            }
        }
    }
}
