import java.util.ArrayList;
import java.util.List;

import DTO.Calculador;
import DTO.Maquina;
import SolucionAlgoritmica.SolucionBacktracking;
import SolucionAlgoritmica.SolucionGreedy;

public class App {
    public static void main(String[] args) {
        List<Maquina> maquinas = new ArrayList<>();
        maquinas.add(new Maquina("M1",7));
        maquinas.add(new Maquina("M2",3));
        maquinas.add(new Maquina("M3",4));
        maquinas.add(new Maquina("M4",1));

        SolucionBacktracking solucionBacktracking = new SolucionBacktracking();
        SolucionGreedy solucionGreedy = new SolucionGreedy();
        int totalPiezas = 16;
        Calculador calculadorBK = solucionBacktracking.calcular(maquinas, totalPiezas);
        Calculador calculadorGR = solucionGreedy.calcular(maquinas, totalPiezas);
        System.out.println(calculadorBK);
        System.out.println("Cantidad de estados: " + solucionBacktracking.getCantActual());
        System.out.println(calculadorGR);
        System.out.println("Cantidad de estados: " + solucionGreedy.getCantActual());
    }
}