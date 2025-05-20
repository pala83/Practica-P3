package TP3.DTO;

import java.time.LocalDate;

public class Cliente {
    private final Integer dni;
    private String nombre, apellido;
    private LocalDate fechaNacimiento;
    private String domicilio, codigoPostal;
    private Double saldo;
    private String carrera;

    public Cliente(Integer dni, String nombre, String apellido, LocalDate fechaNacimiento, String domicilio, String codigoPostal, Double saldo, String carrera) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.domicilio = domicilio;
        this.codigoPostal = codigoPostal;
        this.saldo = saldo;
        this.carrera = carrera;
    }

    public Integer getDni() { return this.dni; }
    public String getNombre() { return this.nombre; }
    public String getApellido() { return this.apellido; }
    public LocalDate getFechaNacimiento() { return this.fechaNacimiento; }
    public String getDomicilio() { return this.domicilio; }
    public String getCodigoPostal() { return this.codigoPostal; }
    public Double getSaldo() { return this.saldo; }
    public String getCarrera() { return this.carrera; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    @Override
    public String toString() {
        return "Cliente [dni=" + this.dni + 
                    ", nombre=" + this.nombre + 
                    ", apellido=" + this.apellido + 
                    ", fechaNacimiento=" + this.fechaNacimiento + 
                    ", domicilio=" + this.domicilio + 
                    ", codigoPostal=" + this.codigoPostal + 
                    ", saldo=" + this.saldo + 
                    ", carrera=" + this.carrera + 
                    "]";
    }
}
