package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.InteraccionClienteController;
import com.concesionario.proyectoTercerTrimestre.entities.InteraccionCliente;
import com.concesionario.proyectoTercerTrimestre.entities.TipoInteraccion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class InteraccionClienteMenu {

    private final Scanner scanner;
    private final InteraccionClienteController interaccionClienteController;
    private final DateTimeFormatter formatter;

    public InteraccionClienteMenu() {
        this.scanner = new Scanner(System.in);
        this.interaccionClienteController = new InteraccionClienteController();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public void mostrarMenuInteraccionesCliente() {
        int opcion;

        do {
            System.out.println("\n===== MENU INTERACCIONES CLIENTE =====");
            System.out.println("1. Crear interaccion");
            System.out.println("2. Eliminar interaccion");
            System.out.println("3. Listar interacciones");
            System.out.println("4. Exportar interacciones a TXT");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearInteraccionCliente();
                    break;
                case 2:
                    eliminarInteraccionCliente();
                    break;
                case 3:
                    listarInteraccionesCliente();
                    break;
                case 4:
                    exportarInteraccionesClienteTxt();
                    break;
                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);
    }

    private void crearInteraccionCliente() {
        System.out.println("\n--- Crear interaccion con cliente ---");

        System.out.print("ID Cliente: ");
        int clienteId = Integer.parseInt(scanner.nextLine());

        System.out.print("ID Usuario: ");
        int usuarioId = Integer.parseInt(scanner.nextLine());

        TipoInteraccion tipo = seleccionarTipoInteraccion();

        LocalDateTime fecha = LocalDateTime.now();

        System.out.print("Asunto: ");
        String asunto = scanner.nextLine();

        System.out.print("Descripcion: ");
        String descripcion = scanner.nextLine();

        System.out.print("Resultado: ");
        String resultado = scanner.nextLine();

        System.out.print("Proxima accion: ");
        String proximaAccion = scanner.nextLine();

        System.out.print("Fecha proxima (yyyy-MM-dd HH:mm) o vacio si no hay: ");
        String fechaProximaTexto = scanner.nextLine();

        LocalDateTime fechaProxima = null;

        if (!fechaProximaTexto.isBlank()) {
            fechaProxima = LocalDateTime.parse(fechaProximaTexto, formatter);
        }

        interaccionClienteController.crearInteraccionCliente(
                clienteId,
                usuarioId,
                tipo,
                fecha,
                asunto,
                descripcion,
                resultado,
                proximaAccion,
                fechaProxima
        );
    }

    private TipoInteraccion seleccionarTipoInteraccion() {
        System.out.println("Tipo de interaccion:");
        System.out.println("1. Llamada");
        System.out.println("2. Email");
        System.out.println("3. WhatsApp");
        System.out.println("4. Reunion");
        System.out.println("5. Visita");
        System.out.println("6. Otro");
        System.out.print("Elige una opcion: ");

        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                return TipoInteraccion.LLAMADA;
            case 2:
                return TipoInteraccion.EMAIL;
            case 3:
                return TipoInteraccion.WHATSAPP;
            case 4:
                return TipoInteraccion.REUNION;
            case 5:
                return TipoInteraccion.VISITA;
            case 6:
                return TipoInteraccion.OTRO;
            default:
                System.out.println("Opcion no valida. Se asigna Llamada por defecto.");
                return TipoInteraccion.LLAMADA;
        }
    }

    private void eliminarInteraccionCliente() {
        System.out.println("\n--- Eliminar interaccion ---");

        System.out.print("Introduce el ID de la interaccion: ");
        int id = Integer.parseInt(scanner.nextLine());

        interaccionClienteController.eliminarInteraccionCliente(id);
    }

    private void listarInteraccionesCliente() {
        System.out.println("\n--- Lista de interacciones ---");

        List<InteraccionCliente> interacciones = interaccionClienteController.listarInteraccionesCliente();

        if (interacciones.isEmpty()) {
            System.out.println("No hay interacciones registradas.");
            return;
        }

        for (InteraccionCliente interaccion : interacciones) {
            System.out.println(interaccion);
        }
    }

    private void exportarInteraccionesClienteTxt() {
        interaccionClienteController.exportarInteraccionesClienteTxt();
    }
}