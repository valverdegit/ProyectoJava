
package com.avance1;


public class Factura {

    private double costo;
    private int[] factura;
    
    public Factura(){
        factura = new int[100];
        costo = 0;
        
    }


    public double getCosto() {
        return costo;
    }


    public void setCosto(double costo) {
        this.costo = costo;
    }

    
    
    
    public double calcularCosto(){
        switch (vehiculo.getPaquete()) {
            case "Básico":
                costo += 5;
                break;
            case "Regular":
                costo += 8;
                break;
            case "Premium":
                costo += 12;
                break;
        }
        return costo;
    }
}
    

