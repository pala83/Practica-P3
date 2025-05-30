import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import EJ1.Ej1;

public class App {
    public static void main(String[] args) throws Exception {
        List<Integer> monedas = new ArrayList<Integer>();
        monedas.add(100);
        monedas.add(25);
        monedas.add(10);
        monedas.add(5);
        monedas.add(1);
        
        Ej1 ej1 = new Ej1(monedas);
        
        System.out.println("Datos tratados (ordenados por valor/cantidad de mayor a menor):");
        for (Integer dato : ej1.getDatosTratados()) {
            System.out.println("Valor: " + dato);
        }
        
        int valorMonetario = 289;
        Map<Integer, Integer> resultado = ej1.greedyChange(valorMonetario);
        
        System.out.println("\nCambio óptimo para $" + valorMonetario + ":");
        int totalMonedas = 0;
        for (Map.Entry<Integer, Integer> entry : resultado.entrySet()) {
            System.out.println(entry.getValue() + " monedas de $" + entry.getKey());
            totalMonedas += entry.getValue();
        }
        System.out.println("Total de monedas: " + totalMonedas);
    }
}
