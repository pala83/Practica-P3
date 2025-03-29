package TP2.Sort.Mergesort;

public class Mergesort {
    private int[] numbers;
    private int[] helper;
    private int size;

    public void sort(int[] numbers){
        this.numbers = numbers;
        this.size = numbers.length;
        this.helper = new int[this.size];
        mergesort(0, size-1);
    }
    
    private void mergesort(int low, int high){
        if(low<high){
            int middle = (low+high)/2;
            mergesort(low, middle);
            mergesort(middle+1, high);
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high){
        
        for(int i=low; i<=high; i++){
            helper[i] = numbers[i];
        }
        int i = low;
        int j = middle+1;
        int k = low;
        while (i<=middle && j<=high) {
            if(helper[i]<=helper[j]){
                numbers[k] = helper[i];
                i++;
            }
            else{
                numbers[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i<=middle) {
            numbers[k] = helper[i];
            k++;
            i++;
        }
        while (j<=high) {
            numbers[k] = helper[j];
            k++;
            j++;
        }
    }
}
