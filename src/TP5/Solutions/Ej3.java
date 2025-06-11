package TP5.Solutions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Suma de subconjuntos. Dados n números positivos distintos, se desea encontrar todas las combinaciones de esos números tal que la suma sea igual a M.

public class Ej3 {
    private int[] numbers;
    private int targetSum;

    public Ej3(int[] numbers, int targetSum) {
        this.numbers = numbers;
        this.targetSum = targetSum;
    }

    public List<List<Integer>> buscarTodasLasCombinaciones() {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(this.numbers);
        backtracking(this.numbers, this.targetSum, 0, new ArrayList<>(), result, 0);
        return result;
    }
    
    private void backtracking(int[] numbers, int targetSum, int acumulador, List<Integer> combinacionTmp, List<List<Integer>> result, int inicio) {
        if (acumulador == targetSum) {
            result.add(new ArrayList<>(combinacionTmp));
            return;
        }
        for (int i = inicio; i < numbers.length; i++) {
            if (acumulador + numbers[i] <= targetSum) {
                combinacionTmp.add(numbers[i]);
                backtracking(numbers, targetSum, acumulador + numbers[i], combinacionTmp, result, i + 1);
                combinacionTmp.remove(combinacionTmp.size() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
        int[] numbers = {10, 7, 5, 18, 12, 20, 15};
        int targetSum = 35;
        System.out.println("Numbers: " + Arrays.toString(numbers));
        System.out.println("Target sum: " + targetSum);
        System.out.println("Possible combinations:");
        Ej3 ej3 = new Ej3(numbers, targetSum);
        List<List<Integer>> combinations = ej3.buscarTodasLasCombinaciones();
        if (combinations.isEmpty()) {
            System.out.println("No combinations found");
        } else {
            for (List<Integer> combination : combinations) {
                System.out.println(combination + " = " + targetSum);
            }
        }
    }
}
