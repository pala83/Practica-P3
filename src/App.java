
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import TP4.Solutions.BFS;
import TP4.Solutions.CaminoMayorLongitud;
import TP4.Solutions.Caminos;
import TP4.Solutions.DFS;
import TP4.Solutions.ExisteCiclo;
import TP4.Solutions.Trazo;
import TP4.TDA.Arco.Arco;
import TP4.TDA.Grafo.Grafo;
import TP4.TDA.Grafo.GrafoD;

public class App {

    public static void main(String[] args) {
        Grafo<Double> grafo = new GrafoD<>();
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
        // Ejercicio 2
        DFS dfs = new DFS(grafo);
        dfs.forest();
        System.out.println("DFS:");
        for(Entry<Integer, Trazo> entry : dfs.getTrazo().entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        dfs.cleanTrazo();

        BFS bfs = new BFS(grafo);
        bfs.forest();
        System.out.println("BFS:");
        for(Entry<Integer, Trazo> entry : bfs.getTrazo().entrySet()){
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        bfs.cleanTrazo();
        //Ejercicio 3
        ExisteCiclo existeCiclo = new ExisteCiclo(grafo);
        existeCiclo.forest();
        System.out.println("Ciclo: " + existeCiclo.getCiclo());
        existeCiclo.clearCiclo();

        //Ejercicio 4
        CaminoMayorLongitud caminoMayorLongitud = new CaminoMayorLongitud(grafo);
        caminoMayorLongitud.buscarCamino(2, 5);
        System.out.println("Camino mayor longitud: " + caminoMayorLongitud.getMejorCamino());
        caminoMayorLongitud.cleanMejorCamino();

        //Ejercicio 5
        Caminos caminos = new Caminos(grafo);
        List<List<Integer>> result = caminos.obtenerCaminos(2, 5);
        System.out.println("Caminos: " + result);
    }

    public static void cargarGrafo(Grafo<Double> grafo, int vertices) {
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