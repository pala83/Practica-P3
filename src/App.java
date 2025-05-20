import java.time.LocalDate;

import TP3.DTO.Cliente;
import TP3.Services.ComedorService;
import TP3.TDA.DynamicHashTable;
import TP3.TDA.StaticHashTable;

public class App {
    public static void main(String[] args) throws Exception {
        ComedorService comedor = new ComedorService();
        //public Cliente(Integer dni, String nombre, String apellido, LocalDate fechaNacimiento, String domicilio, String codigoPostal, Double saldo, String carrera)
        Cliente c1 = new Cliente(12345678,"Juan", "Pérez",  LocalDate.of(2000, 5, 12), "Calle Falsa 123", "7600", 1500.0, "Ingeniería en Sistemas");
        Cliente c2 = new Cliente(23456789,"Lucía", "Gómez", LocalDate.of(1999, 3, 22), "Av. Siempreviva 742", "1900", 500.0, "Licenciatura en Matemática");
        Cliente c3 = new Cliente(34567890,"Martín", "Lopez", LocalDate.of(2001, 11, 30), "San Martín 2000", "5000", 0.0, "Arquitectura");
        Cliente c4 = new Cliente(45678901,"Ana", "Fernández", LocalDate.of(2002, 8, 5), "Mitre 450", "7600", 300.0, "Abogacía");
        Cliente c5 = new Cliente(56789012,"Diego", "Ramírez", LocalDate.of(1998, 1, 15), "Belgrano 999", "1400", 50.0, "Medicina");

        comedor.agregarCliente(c1);
        comedor.agregarCliente(c2);
        comedor.agregarCliente(c3);
        comedor.agregarCliente(c4);
        comedor.agregarCliente(c5);

        System.out.println(comedor.listarClientesSaldoMenor(500));

        StaticHashTable<Integer, Integer> hashTable = new StaticHashTable<>(7);
        int[] x = {68, 42, 47, 5, 76, 95, 23, 88, 90, 85, 31, 71, 60, 10, 46, 61, 50, 92, 74, 6, 97, 66, 1, 56, 27, 7, 14, 92};
        for(int i : x){
            hashTable.put(i, i);
        }
        System.out.println(hashTable);

        DynamicHashTable<Integer, Integer> dynamicHashTable = new DynamicHashTable<>(7);
        for(int i : x){
            dynamicHashTable.put(i, i);
        }
        System.out.println(dynamicHashTable);
    }
}