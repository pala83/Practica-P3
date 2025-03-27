public class App {
    public static void main(String[] args) throws Exception {
        int[] arreglo1 = {1,2,3,4,5,6};
        int[] arreglo2 = {1,2,3,4,3,6};
        int[] arreglo3 = {1,2,0,4,5};
        System.out.println(printArr(arreglo1) + " Esta " + resultOrder(arreglo1));
        System.out.println(printArr(arreglo2) + " Esta " + resultOrder(arreglo2));
        System.out.println(printArr(arreglo3) + " Esta " + resultOrder(arreglo3));
    }

    public static String printArr(int[] arr){
        String retorno = "La lista es la siguiente: [";
        for(int i: arr){
            retorno+=i + " ";
        }
        return retorno+="]";
    }

    public static String resultOrder(int[] arr){
        return checkOrder(arr, 0) ? "ordenado" : "desordenado";
    }

    public static boolean checkOrder(int[] arr, int pointer){
        if(pointer==arr.length-1) return true;
        if(arr[pointer]>arr[pointer+1]) return false;
        return checkOrder(arr, pointer+1);
    }
}
