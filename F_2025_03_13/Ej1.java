package Trabajos_Pracicos.F_2025_03_13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
    A partir de un conjunto N de personas con diferentes valores de fuerza se esea armar una secuencia de 4 subconjuntos disjuntos de manera tal que cada conjunto tenga una fuerza acumulada mayor que su predecesor pero intentando minimizar la diferencia total de fuerza entre cada par de conjuntos.
    Por ejemplo, con las personas y su valor de fuerza siguiente N = {P1(2), P2(7), P3(4), P4(1), P5(3)} se podria armar la secuencia S= <C1={P2(7)}, C2={P3(4), P4(1)}, C3={P5(3)}, C4={P1(2)}> resultando en una diferencia de fuerza total de (7-5)+(5-3)+(3-2)=5.
    Se pide diseñar un algoritmo que resuelva el problema mediante una estrategia Greedy retornando los conjuntos armados y la diferencia de fuerza total.
    Responda:
        a) describa con sus palabras cual seria la estrategia Greedy que seguiría.
        b) plantee un codigo JAVA que lo resuelva mediante dicha estrategia Greedy.
*/

public class Ej1 {

    public static class Persona implements Comparable<Persona> {
        String nombre;
        int fuerza;

        public Persona(String nombre, int fuerza) {
            this.nombre = nombre;
            this.fuerza = fuerza;
        }

        @Override
        public int compareTo(Persona o) {
            // Orden descendente por fuerza
            return Integer.compare(o.fuerza, this.fuerza);
        }

        @Override
        public String toString() {
            return nombre + "(" + fuerza + ")";
        }
    }

    public static class Subconjunto {
        List<Persona> personas = new ArrayList<>();
        int fuerzaTotal = 0;
        int id;

        public Subconjunto(int id) {
            this.id = id;
        }

        public void agregar(Persona p) {
            personas.add(p);
            fuerzaTotal += p.fuerza;
        }

        @Override
        public String toString() {
            return "C" + id + "=" + personas;
        }
    }

    public List<Subconjunto> resolver(List<Persona> personas) {
        // 1. Ordenar personas por fuerza descendente
        Collections.sort(personas);

        List<Subconjunto> conjuntos = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            conjuntos.add(new Subconjunto(i + 1));
        }

        // 2. Asignar las 4 personas más fuertes a cada conjunto para inicializar
        // Esto establece una base escalonada (C1 tendrá la más fuerte, C4 la 4ta más fuerte)
        for (int i = 0; i < 4; i++) {
            if (i < personas.size()) {
                conjuntos.get(i).agregar(personas.get(i));
            }
        }

        // 3. Distribuir el resto de las personas
        for (int i = 4; i < personas.size(); i++) {
            Persona p = personas.get(i);
            boolean asignado = false;

            // Intentamos agregar a los conjuntos de abajo hacia arriba (C4 -> C3 -> C2)
            // Usamos un bucle para evitar la concatenación de IFs y hacerlo escalable
            for (int j = conjuntos.size() - 1; j > 0; j--) {
                Subconjunto actual = conjuntos.get(j);
                Subconjunto superior = conjuntos.get(j - 1);

                // Solo agregamos si se mantiene la restricción de orden estricto con el conjunto superior
                if (actual.fuerzaTotal + p.fuerza < superior.fuerzaTotal) {
                    actual.agregar(p);
                    asignado = true;
                    break;
                }
            }

            // Si no cabe en ninguno de los anteriores sin romper la escala, va a C1
            if (!asignado) {
                conjuntos.get(0).agregar(p);
            }
        }

        return conjuntos;
    }

    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("P1", 2));
        personas.add(new Persona("P2", 7));
        personas.add(new Persona("P3", 4));
        personas.add(new Persona("P4", 1));
        personas.add(new Persona("P5", 3));

        Ej1 ejercicio = new Ej1();
        List<Subconjunto> resultado = ejercicio.resolver(personas);

        System.out.print("S= <");
        int diferenciaTotal = 0;
        for (int i = 0; i < resultado.size(); i++) {
            System.out.print(resultado.get(i));
            if (i < resultado.size() - 1) {
                System.out.print(", ");
                diferenciaTotal += (resultado.get(i).fuerzaTotal - resultado.get(i+1).fuerzaTotal);
            }
        }
        System.out.println(">");
        System.out.println("Diferencia de fuerza total: " + diferenciaTotal);
        
        // Verificación de fuerzas
        for(Subconjunto s : resultado) {
            System.out.println("Fuerza " + s.id + ": " + s.fuerzaTotal);
        }
    }
}
