package SolucionAlgoritmica;

import java.util.List;

import DTO.Calculador;
import DTO.Maquina;

public interface Solucion {
    Calculador calcular(List<Maquina> maquinas, int estado);
}
