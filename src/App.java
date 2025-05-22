import java.util.ArrayList;
import java.util.List;

import DTO.Calculador;
import DTO.Maquina;
import SolucionAlgoritmica.SolucionBacktracking;

public class App {
    public static void main(String[] args) {
        List<Maquina> maquinas = new ArrayList<>();
        maquinas.add(new Maquina("M1",7));
        maquinas.add(new Maquina("M2",3));
        maquinas.add(new Maquina("M3",4));
        maquinas.add(new Maquina("M4",1));

        SolucionBacktracking solucionBacktracking = new SolucionBacktracking();
        Calculador calculador = solucionBacktracking.calcular(maquinas, 13);
        System.out.println(calculador);
        System.out.println("Cantidad de estados: " + solucionBacktracking.getCantActual());
    }
}