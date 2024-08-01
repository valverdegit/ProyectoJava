package com.programas.prueba;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vehiculos {

    private String nombreCliente;
    private String placa;
    private String marca;
    private String color;
    private String paquete;
    private LocalDateTime fechaRecepcion;
    

    public Vehiculos(String nombreCliente, String placa, String marca, String color, String paquete, String zona, LocalDateTime fechaRecepcion) {
        this.nombreCliente = nombreCliente;
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.paquete = paquete;
        //nuevo
        this.zona= zona;
        //
        this.fechaRecepcion = fechaRecepcion;
    }
    //nuevo

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getPaquete() {
        return paquete;
    }
    public void setPaquete(String paquete) {
        this.paquete = paquete;
    }
    public String getFechaIngreso() {
        return fechaIngreso;
    }
    //

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaAspirado(LocalDateTime fechaAspirado) {
      
    }

    public void setFechaLavado(LocalDateTime fechaLavado) {
    }

    public void setFechaEncerado(LocalDateTime fechaEncerado) {
        
    }

    public void setFechaChasis(LocalDateTime fechaChasis) {
        
    }

    public void setFechaRetiro(LocalDateTime fechaRetiro) {
        
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaRecepcionFormato = fechaRecepcion.format(formato);
        return "Nombre del cliente: " + nombreCliente + "\n"
                + "Placa: " + placa + "\n"
                + "Marca: " + marca + "\n"
                + "Color: " + color + "\n"
                + "Paquete: " + paquete + "\n"
                + "Fecha de recepción: " + fechaRecepcionFormato;
    }
}
