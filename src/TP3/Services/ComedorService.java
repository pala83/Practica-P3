package TP3.Services;

import TP3.TDA.DynamicHashTable;

import java.util.ArrayList;
import java.util.List;

import TP3.DTO.Cliente;

public class ComedorService implements Service {
    private DynamicHashTable<Integer, Cliente> clientes;
    private final static int INITIAL_CAPACITY = 20;

    public ComedorService() {
        this.clientes = new DynamicHashTable<Integer, Cliente>(INITIAL_CAPACITY);
    }

    @Override
    public void agregarCliente(Cliente cliente) {
        this.clientes.put(cliente.getDni(), cliente);
    }

    @Override
    public Cliente obtenerCliente(Integer dni) {
        return this.clientes.get(dni);
    }

    @Override
    public void imprimirCliente(Integer dni) {
        System.out.println(this.clientes.showValue(dni));
    }

    @Override
    public double obtenerSaldo(Integer dni) {
        Cliente cliente = this.clientes.get(dni);
        return cliente!=null?cliente.getSaldo():0;
    }

    @Override
    public List<Cliente> listarClientesSaldoMenor(double saldo) {
        List<Cliente> clientes = new ArrayList<>();
        for(Cliente cliente : this.clientes.getAllValues()){
            if(cliente.getSaldo() < saldo){
                clientes.add(cliente);
            }
        }
        return clientes;
    }

    @Override
    public List<Cliente> listarClientesConCP(String codigoPostal) {
        List<Cliente> clientes = new ArrayList<>();
        for(Cliente cliente : this.clientes.getAllValues()){
            if(cliente.getCodigoPostal().equals(codigoPostal)){
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}
