public class Zona {
    private String nombre;
    private Vehiculo[] vehiculos;
    private int cantidadVehiculos;

   
    public Zona(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.vehiculos = new Vehiculo[capacidadMaxima];
        this.cantidadVehiculos = 0;
    }

  
    public void agregarVehiculo(Vehiculo vehiculo) {
        if (cantidadVehiculos < vehiculos.length) {
            vehiculos[cantidadVehiculos] = vehiculo;
            cantidadVehiculos++;
        } else {
            System.out.println("No se pueden agregar más vehículos a la zona " + nombre);
        }
    }


    public String generarReporte() {
        StringBuilder reporte = new StringBuilder("Reporte de vehículos en la zona " + nombre + ":\n");
        for (int i = 0; i < cantidadVehiculos; i++) {
            reporte.append(vehiculos[i].toString()).append("\n");
        }
        return reporte.toString();
    }


    public String visualizarDistribucion() {
        StringBuilder visualizacion = new StringBuilder(nombre + ": ");
        for (int i = 0; i < cantidadVehiculos; i++) {
            visualizacion.append(vehiculos[i].getPaquete().charAt(0)).append(" ");
        }
        return visualizacion.toString();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
