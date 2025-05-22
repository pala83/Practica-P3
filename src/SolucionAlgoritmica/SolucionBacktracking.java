package SolucionAlgoritmica;

import java.util.ArrayList;
import java.util.List;

import DTO.Calculador;
import DTO.Maquina;

public class SolucionBacktracking implements Solucion {
    private List<Maquina> mejorSecuencia = new ArrayList<>();
    private int cantActual;

    @Override
    public Calculador calcular(List<Maquina> maquinas, int totalPiezas) {
        this.cantActual = 0;
        for (Maquina m : maquinas) 
            this.backtracking(maquinas, m, totalPiezas, 0, new ArrayList<>());
        return new Calculador(mejorSecuencia, totalPiezas, mejorSecuencia.size());
    }

    private void backtracking(List<Maquina> maquinas, 
                              Maquina m, 
                              int totalPiezas, 
                              int acumulador, 
                              List<Maquina> arrTMP) {
        cantActual++;
        arrTMP.add(m);
        acumulador += m.getProduccion();
        if(acumulador == totalPiezas && arrTMP.size() <= (mejorSecuencia.isEmpty() ? Integer.MAX_VALUE : mejorSecuencia.size())){
            mejorSecuencia.clear();
            mejorSecuencia.addAll(arrTMP);
        }
        if(acumulador > totalPiezas)
            return;
        for(Maquina subM : maquinas){
            backtracking(maquinas, subM, totalPiezas, acumulador, arrTMP);
            arrTMP.remove(arrTMP.size()-1);
        }
    }

    public int getCantActual() {
        return cantActual;
    }

}
