package com.programas.prueba;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prueba {

    public static Vehiculos[] lobby = new Vehiculos[8];
    public static Vehiculos[] aspirado = new Vehiculos[3];
    public static Vehiculos[] lavado = new Vehiculos[3];
    public static Vehiculos[] encerado = new Vehiculos[3];
    public static Vehiculos[] chasis = new Vehiculos[3];
    public static Vehiculos[] retiro = new Vehiculos[8];

    public static int cuentaVehiculos = 0;

    public static void main(String[] args) {

        int contador = 0;
        String menu = "Bienvenido a Luxury Cars\n Por favor seleccione una opción del menú:\n 1. Recepción de vehículos\n 2. Atención de vehículos\n 3. Avanzar etapa del vehículo\n 4. Retiro de vehículo\n 5. Reportes\n 6. Salir\n";

        while (contador == 0) {
            String opcion = JOptionPane.showInputDialog(null, menu);

            switch (opcion) {
                case "1":
                    while (true) {
                        String[] opciones = {"Agregar vehículo", "Mostrar vehículos", "Salir"};
                        int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Recepción de Vehículos",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

                        if (eleccion == 0) {
                            agregarVehiculo();
                        } else if (eleccion == 1) {
                            mostrarVehiculos();
                        } else {
                            break;
                        }
                    }
                    break;
                case "2":
                    iniciarAtencion();
                    break;
                case "3":
                    avanzarEtapaVehiculo();
                    break;
                case "4":
                    retirarVehiculo();
                    break;
                case "5":
                    // Implementar función para reportes si es necesario
                    break;
                case "6":
                    contador += 1;
                    JOptionPane.showMessageDialog(null, "Gracias por su visita.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese una opción válida.");
            }
        }
    }

    public static void agregarVehiculo() {
        if (cuentaVehiculos >= 8) {
            JOptionPane.showMessageDialog(null, "El lobby está lleno, no entran más vehículos.");
            return;
        }

        String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String placa = JOptionPane.showInputDialog("Ingrese la placa del vehículo:");

        if (placaRegistrada(placa)) {
            JOptionPane.showMessageDialog(null, "La placa ya está registrada. Intente con otra placa.");
            return;
        }

        String marca = JOptionPane.showInputDialog("Ingrese la marca del vehículo:");
        String color = JOptionPane.showInputDialog("Ingrese el color del vehículo:");
        String[] paquetes = {"Básico", "Regular", "Premium"};
        String paquete = (String) JOptionPane.showInputDialog(null, "Seleccione el paquete deseado:",
                "Paquete", JOptionPane.QUESTION_MESSAGE, null, paquetes, paquetes[0]);

        LocalDateTime fechaRecepcion = LocalDateTime.now();

        Vehiculos vehiculo = new Vehiculos(nombreCliente, placa, marca, color, paquete, fechaRecepcion);
        lobby[cuentaVehiculos] = vehiculo;
        cuentaVehiculos++;

        JOptionPane.showMessageDialog(null, "Vehículo agregado exitosamente.");
    }

    public static boolean placaRegistrada(String placa) {
        for (int i = 0; i < cuentaVehiculos; i++) {
            if (lobby[i].getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    public static void mostrarVehiculos() {
        if (cuentaVehiculos == 0) {
            JOptionPane.showMessageDialog(null, "No hay vehículos en el lobby.");
            return;
        }

        String vehiculos = "";
        for (int i = 0; i < cuentaVehiculos; i++) {
            vehiculos += lobby[i].toString() + "\n\n";
        }

        JOptionPane.showMessageDialog(null, vehiculos);
    }

    public static void iniciarAtencion() {
        if (cuentaVehiculos == 0) {
            JOptionPane.showMessageDialog(null, "No hay vehículos en el lobby para iniciar atención.");
            return;
        }

        if (aspirado[2] != null) {
            JOptionPane.showMessageDialog(null, "No hay espacio en la sección de Aspirado.");
            return;
        }

        Vehiculos vehiculo = lobby[0];
        LocalDateTime fechaAspirado = LocalDateTime.now();
        vehiculo.setFechaAspirado(fechaAspirado);
        moverVacio(aspirado, vehiculo);
        reorganizarLobby();

        JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " movido a la sección de Aspirado. Fecha de Aspirado: " + fechaAspirado.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    public static void avanzarEtapaVehiculo() {
        String[] opciones = {"Aspirado", "Lavado", "Encerado", "Chasis"};
        String origenVehiculo = (String) JOptionPane.showInputDialog(null, "Seleccione la zona de donde quiere mover el vehículo:",
                "Avanzar Etapa", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        Vehiculos vehiculo = obtenerVehiculoPorZona(origenVehiculo);
        if (vehiculo == null) {
            JOptionPane.showMessageDialog(null, "No hay vehículos en la zona seleccionada.");
            return;
        }

        switch (origenVehiculo) {
            case "Aspirado":
                if (moverVacio(lavado, vehiculo)) {
                    LocalDateTime fechaLavado = LocalDateTime.now();
                    vehiculo.setFechaLavado(fechaLavado);
                    reorganizarFila(aspirado);
                    JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " movido a Lavado. Fecha de Lavado: " + fechaLavado.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                }
                break;
            case "Lavado":
                if ("Regular".equals(vehiculo.getPaquete()) || "Premium".equals(vehiculo.getPaquete())) {
                    if (moverVacio(encerado, vehiculo)) {
                        LocalDateTime fechaEncerado = LocalDateTime.now();
                        vehiculo.setFechaEncerado(fechaEncerado);
                        reorganizarFila(lavado);
                        JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " movido a Encerado. Fecha de Encerado: " + fechaEncerado.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    }
                } else {
                    if (moverVacio(retiro, vehiculo)) {
                        LocalDateTime fechaRetiro = LocalDateTime.now();
                        vehiculo.setFechaRetiro(fechaRetiro);
                        reorganizarFila(lavado);
                        JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " movido a Retiro. Fecha de Retiro: " + fechaRetiro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    }
                }
                break;
            case "Encerado":
                if ("Premium".equals(vehiculo.getPaquete())) {
                    if (moverVacio(chasis, vehiculo)) {
                        LocalDateTime fechaChasis = LocalDateTime.now();
                        vehiculo.setFechaChasis(fechaChasis);
                        reorganizarFila(encerado);
                        JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " movido a Chasis. Fecha de Chasis: " + fechaChasis.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    }
                } else {
                    if (moverVacio(retiro, vehiculo)) {
                        LocalDateTime fechaRetiro = LocalDateTime.now();
                        vehiculo.setFechaRetiro(fechaRetiro);
                        reorganizarFila(encerado);
                        JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " movido a Retiro. Fecha de Retiro: " + fechaRetiro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                    }
                }
                break;
            case "Chasis":
                if (moverVacio(retiro, vehiculo)) {
                    LocalDateTime fechaRetiro = LocalDateTime.now();
                    vehiculo.setFechaRetiro(fechaRetiro);
                    reorganizarFila(chasis);
                    JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " movido a Retiro. Fecha de Retiro: " + fechaRetiro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                break;
        }
    }

    public static Vehiculos obtenerVehiculoPorZona(String zona) {
        Vehiculos[] fila = null;
        switch (zona) {
            case "Aspirado":
                fila = aspirado;
                break;
            case "Lavado":
                fila = lavado;
                break;
            case "Encerado":
                fila = encerado;
                break;
            case "Chasis":
                fila = chasis;
                break;
            default:
                return null;
        }

        for (int i = 0; i < fila.length; i++) {
            if (fila[i] != null) {
                return fila[i];
            }
        }
        return null;
    }

    public static boolean moverVacio(Vehiculos[] destino, Vehiculos vehiculo) {
        for (int i = 0; i < destino.length; i++) {
            if (destino[i] == null) {
                destino[i] = vehiculo;
                return true;
            }
        }
        return false;
    }

    public static void reorganizarLobby() {
        for (int i = 0; i < cuentaVehiculos - 1; i++) {
            lobby[i] = lobby[i + 1];
        }
        lobby[cuentaVehiculos - 1] = null;
        cuentaVehiculos--;
    }

    public static void reorganizarFila(Vehiculos[] fila) {
        for (int i = 0; i < fila.length - 1; i++) {
            fila[i] = fila[i + 1];
        }
        fila[fila.length - 1] = null;
    }

    public static void retirarVehiculo() {
        if (retiro[0] == null) {
            JOptionPane.showMessageDialog(null, "No hay vehículos listos para retirar.");
            return;
        }

        Vehiculos vehiculo = retiro[0];
        reorganizarFila(retiro);
        JOptionPane.showMessageDialog(null, "Vehículo " + vehiculo.getPlaca() + " retirado exitosamente.");
    }
}
