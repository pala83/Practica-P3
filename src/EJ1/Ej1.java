package EJ1;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Cambio de monedas: Dado un conjunto C de N tipos de monedas con un número ilimitado de ejemplares de cada tipo, se requiere formar, si se puede, una cantidad M empleando el mínimo número de ellas.
// Por ejemplo, un cajero automático dispone de billetes de distintos valores: 100$ 100 existencias, 25$ 100 existencias, 10$ 100 existencias, 5$ 100 existencias y 1$ 100 existencias, si se tiene que pagar 289$, la mejor solución consiste en dar 10 billetes: 2 de 100$, 3 de 25$, 1 de 10$ y 4 de 1$.

public class Ej1 {
    private List<Integer> monedas;
    
    public Ej1(List<Integer> monedas) {
        // debe estar ordenado de mayor a menor
        Collections.sort(monedas, Collections.reverseOrder()); 
        this.monedas = monedas;
    }
    
    public List<Integer> getDatosTratados() {
        return monedas;
    }
    
    public Map<Integer, Integer> greedyChange(int valorMonetario) {
        Map<Integer, Integer> result = new HashMap<>();
        int resto = valorMonetario;
        for (int moneda : monedas) {
            int necesarias = resto / moneda;
            if (necesarias > 0) {
                result.put(moneda, necesarias);
                resto -= moneda * necesarias;
            }
        }        
        return result;
    }
}
