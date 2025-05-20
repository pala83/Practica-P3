package TP3.Services;

import TP3.DTO.Cliente;
import java.util.List;

public interface Service {
    public void agregarCliente(Cliente cliente);
    public Cliente obtenerCliente(Integer dni);
    public void imprimirCliente(Integer dni);
    public double obtenerSaldo(Integer dni);
    public List<Cliente> listarClientesSaldoMenor(double saldo);
    public List<Cliente> listarClientesConCP(String codigoPostal);
}
