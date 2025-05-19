package TP2.Sort.Quicksort;

public class Quicksort {
    private int[] numbers;
    private int size;

    public void sort(int[] numbers){
        this.numbers = numbers;
        this.size = numbers.length;
        quicksort(0, size-1);
    }

    private void quicksort(int low, int high){
        if(low<high){
            int pivot = partition(low, high);
            quicksort(low, pivot-1);
            quicksort(pivot+1, high);
        }
    }

    private int partition(int low, int high){
        int pivot = numbers[high];
        int i = low-1;
        for(int j=low; j<high; j++){
            if(numbers[j]<pivot){
                i++;
                swap(i, j);
            }
        }
        swap(i+1, high);
        return i+1;
    }

    private void swap(int i, int j){
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

}
