import java.util.List;
import CalculoAlgoritmico.Backtracking;
import CalculoAlgoritmico.Greedy;
import DTO.Solucion;
import Utils.FileSelector;
import Utils.TXTReader;
import DTO.Maquina;

public class App {
    public static void main(String[] args) {
        FileSelector selector = new FileSelector();
        TXTReader reader = new TXTReader(',');
        try {
            reader.readEngines(selector.chooseDataFile("./src/Data").toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        List<Maquina> maquinas = reader.getMaquinas();
        int totalPiezas = reader.getPiezasTotales();

        Backtracking backtracking = new Backtracking();
        Greedy greedy = new Greedy();
        Solucion solucionBK = backtracking.calcular(maquinas, totalPiezas);
        Solucion solucionGR = greedy.calcular(maquinas, totalPiezas);
        System.out.println("\nBacktracking");
        System.out.println(solucionBK);
        System.out.println("|- Cantidad de estados: " + backtracking.cantidadEstados());
        System.out.println("\nGreedy");
        System.out.println(solucionGR);
        System.out.println("|- Cantidad de estados: " + greedy.cantidadEstados());
    }
}