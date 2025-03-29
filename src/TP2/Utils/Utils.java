package TP2.Utils;

public class Utils {
    private int number;

    public Utils(int number) {
        this.number = number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    
    // Ejercicio 3
    public String toBinary(){
        return this._toBinary(this.number);
    }

    private String _toBinary(int num){
        if(num==0)
            return "0";
        if(num==1)
            return "1";
        return _toBinary(num/2) + _toBinary(num%2);
    }
    // Ejercicio 4
    public String fribonacci(){
        String retorno = "";
        for(int i=0; i<this.number; i++){
            retorno+=_fribonacci(i)+" ";
        }
        return retorno;
    }

    private int _fribonacci(int num){
        if(num<=1){
            return num;
        }
        return _fribonacci(num-1) + _fribonacci(num-2);
    }
}
