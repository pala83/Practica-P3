package DTO;

public class SinSolucion extends Solucion {
    private static final String ANSI_RED = "\u001B[31m";
    public SinSolucion() {
        super(null, 0, 0);
    }

    @Override
    public String toString() {
        return ANSI_RED + "Sin solucion" + ANSI_RESET;
    }
}
