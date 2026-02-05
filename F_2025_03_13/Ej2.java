package Trabajos_Pracicos.F_2025_03_13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Trabajos_Pracicos.Estructuras.GrafoD;

/**
  Dado un grafo dirigido G(V,A), implementar un algoritmo en java que determina si el mismo contiene, por lo menos, 2 ciclos donde o se compartan arcos entre ambos ciclos (si se pueden compartir vertices).
 */

public class Ej2 {

    private static final int BLANCO = 0;
    private static final int AMARILLO = 1;
    private static final int NEGRO = 2;

    private GrafoD grafo;
    private Map<Integer, Trazo> trazos;
    private Set<String> arcosUsadosEnCiclos;
    private int cantidadCiclosEncontrados;
    private int tiempo;

    // Clase auxiliar Trazo para llevar el estado de los nodos
    private class Trazo {
        int color = BLANCO;
        int tiempoInicial;
        int tiempoFinal;
        Integer padre = null;
    }

    public Ej2(GrafoD grafo) {
        this.grafo = grafo;
    }

    public boolean tieneAlMenosDosCiclosDisjuntosEnArcos() {
        this.trazos = new HashMap<>();
        this.arcosUsadosEnCiclos = new HashSet<>();
        this.cantidadCiclosEncontrados = 0;
        this.tiempo = 0;

        // Inicializar trazos para todos los vértices
        for (Integer v : grafo.obtenerVertices()) {
            trazos.put(v, new Trazo());
        }

        for (Integer v : grafo.obtenerVertices()) {
            if (cantidadCiclosEncontrados >= 2) return true;
            
            if (trazos.get(v).color == BLANCO) {
                dfs(v);
            }
        }

        return cantidadCiclosEncontrados >= 2;
    }

    private void dfs(Integer u) {
        if (cantidadCiclosEncontrados >= 2) return;

        Trazo trazoU = trazos.get(u);
        trazoU.color = AMARILLO;
        trazoU.tiempoInicial = ++tiempo;

        for (Integer v : grafo.obtenerAdyacentes(u)) {
            if (cantidadCiclosEncontrados >= 2) return;

            // Verificar si el arco ya fue usado
            String idArco = u + "-" + v;
            if (arcosUsadosEnCiclos.contains(idArco)) {
                continue;
            }

            Trazo trazoV = trazos.get(v);
            
            if (trazoV.color == AMARILLO) {
                // Back-edge detectado: Ciclo encontrado
                registrarCiclo(u, v);
            } else if (trazoV.color == BLANCO) {
                trazoV.padre = u;
                dfs(v);
            }
        }

        trazoU.color = NEGRO;
        trazoU.tiempoFinal = ++tiempo;
    }

    private void registrarCiclo(Integer u, Integer v) {
        // El ciclo se cierra con el arco u -> v
        // Y retrocedemos por los padres desde u hasta v
        
        // Verificar si podemos formar el ciclo sin usar arcos ya marcados (doble check por seguridad)
        // El arco de cierre:
        String arcoCierre = u + "-" + v;
        if (arcosUsadosEnCiclos.contains(arcoCierre)) return;

        // Recolectamos los arcos del ciclo para marcarlos
        Set<String> arcosCicloActual = new HashSet<>();
        arcosCicloActual.add(arcoCierre);

        Integer actual = u;
        boolean cicloValido = true;

        // Retrocedemos desde u hasta v usando los padres
        while (!actual.equals(v)) {
            Integer padre = trazos.get(actual).padre;
            if (padre == null) {
                // Esto no debería pasar si la lógica de colores es correcta y v es ancestro (Amarillo)
                cicloValido = false;
                break;
            }
            
            String arco = padre + "-" + actual;
            if (arcosUsadosEnCiclos.contains(arco)) {
                cicloValido = false;
                break;
            }
            arcosCicloActual.add(arco);
            actual = padre;
        }

        if (cicloValido) {
            arcosUsadosEnCiclos.addAll(arcosCicloActual);
            cantidadCiclosEncontrados++;
        }
    }

    public static void main(String[] args) {
        GrafoD g = new GrafoD();
        g.agregarVertice(1);
        g.agregarVertice(2);
        g.agregarVertice(3);
        g.agregarVertice(4);
        g.agregarVertice(5);

        // Ciclo 1: 1->2->3->1
        g.agregarArco(1, 2, 0);
        g.agregarArco(2, 3, 0);
        g.agregarArco(3, 1, 0);

        // Ciclo 2: 3->4->5->3 (Comparte vértice 3, pero no arcos)
        g.agregarArco(3, 4, 0);
        g.agregarArco(4, 5, 0);
        g.agregarArco(5, 3, 0);

        Ej2 ej2 = new Ej2(g);
        System.out.println("Tiene 2 ciclos disjuntos en arcos: " + ej2.tieneAlMenosDosCiclosDisjuntosEnArcos());
        
        GrafoD g2 = new GrafoD();
        g2.agregarVertice(1);
        g2.agregarVertice(2);
        // Solo 1 ciclo
        g2.agregarArco(1, 2, 0);
        g2.agregarArco(2, 1, 0);
        
        Ej2 ej2b = new Ej2(g2);
        System.out.println("Tiene 2 ciclos disjuntos en arcos (caso 1 ciclo): " + ej2b.tieneAlMenosDosCiclosDisjuntosEnArcos());
    }
}
