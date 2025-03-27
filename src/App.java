import java.util.ArrayList;

import TP1.TDA.List.LinkedList.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList<Integer> list = new LinkedList<Integer>();
        list.insertFront(1);
        list.insertFront(2);
        list.insertFront(3);
        list.insertFront(4);
        list.insertFront(5);
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
        for(Integer elem: list){
            System.out.println(elem);
        }
    }

    public boolean checkOrder(ArrayList<Integer> arr, int left, int right){
        if(arr.get(left)>arr.get(right)){
            return false;
        }
        if(left>=right)
            return true;
        return checkOrder(arr, left+1, right-1);
    }
}
