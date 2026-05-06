package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.ClienteController;
import com.concesionario.proyectoTercerTrimestre.controller.InteraccionClienteController;
import com.concesionario.proyectoTercerTrimestre.controller.UsuarioController;
import com.concesionario.proyectoTercerTrimestre.entities.*;
import com.concesionario.proyectoTercerTrimestre.repositories.InteraccionClienteRepository;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            System.out.println("5. Modificar interaccion");
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
                case 5:
                    modificarInteraccion();
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

    private void modificarInteraccion() {

        InteraccionCliente interaccionCliente  = new InteraccionCliente();

        System.out.println("Que detalle venta quieres modificar: ");
        List<InteraccionCliente> interaccionClientes =  interaccionClienteController.listarInteraccionesCliente();
        int iterador = 1;
        for (InteraccionCliente interaccion : interaccionClientes) {
            System.out.println(iterador++ + " - " + interaccion.getId());
        }

        int opcion;
        int opcion2;

        opcion = ComprobacionOpcion.leerOpcion(1, interaccionClientes.size());

        System.out.println("Modificar id del cliente");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 ->{
                System.out.println("Cual de los cliente desea seleccionar: ");
                ClienteController clienteController = new ClienteController();
                List <Cliente> clientes = clienteController.listarClientes();
                iterador = 1;
                for (Cliente cliente : clientes) {
                    System.out.println( iterador++ + ". " + cliente.getNombre());
                }
                int opc = 0;
                while (opc < 1 || opc > clientes.size()) {
                    opc = Integer.parseInt(scanner.nextLine());
                }

                interaccionCliente.setClienteId(opc);

            }case 2 ->{
                interaccionCliente.setClienteId(-1);
            }

        }

        System.out.println("Modificar id del usuario");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 ->{
                System.out.println("Cual de los usuarios desea seleccionar");
                UsuarioController usuarioController = new UsuarioController();
                List <Usuario> usuarios = usuarioController.listarUsuarios();
                iterador = 1;
                for (Usuario usuario : usuarios) {
                    System.out.println( iterador++ + ". " + usuario.getNombre());
                }
                int opc = 0;
                while (opc < 1 || opc > usuarios.size()) {
                    opc = Integer.parseInt(scanner.next());
                    scanner.nextLine();
                }

                interaccionCliente.setUsuarioId(opc);

            }case 2 ->{
                interaccionCliente.setUsuarioId(-1);
            }

        }

        System.out.println("Modificar tipo de interaccion");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {
            case 1 ->{
                interaccionCliente.setTipo(seleccionarTipoInteraccion());
            }case 2 ->{
                interaccionCliente.setTipo(null);
            }
        }

        System.out.println("Modificar el asunto de la interacción");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2){

            case 1->{
                System.out.println("Introduce el asunto: ");
                interaccionCliente.setAsunto(scanner.nextLine());
            }case 2 ->{
                interaccionCliente.setAsunto(null);
            }

        }

        System.out.println("Modificar la descripcion de la interacción");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2){

            case 1->{
                System.out.println("Introduce la descripcion: ");
                interaccionCliente.setDescripcion(scanner.nextLine());
                scanner.nextLine();
            }case 2 ->{
                interaccionCliente.setDescripcion(null);
            }

        }

        System.out.println("Modificar el resultado de interaccion");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {
            case 1 ->{
                System.out.println("Introduce el resultado:: ");
                interaccionCliente.setResultado(scanner.nextLine());
            }case 2 ->{
                interaccionCliente.setResultado(null);
            }
        }

        System.out.println("Modificar la proxima accion de la interaccion");
        System.out.println("1. Si");
        System.out.println("2. No");

        do {
            opcion2 = Integer.parseInt(scanner.nextLine());
        }while (opcion2 <= 0 || opcion2 > 2);

        switch (opcion2) {
            case 1 ->{
                System.out.println("Introduce la proxima accion: ");
                interaccionCliente.setProximaAccion(scanner.nextLine());
            }case 2 ->{
                interaccionCliente.setProximaAccion(null);
            }
        }

        System.out.println("Modificar fecha de la proxima accion de la interaccion");
        System.out.println("1. Si");
        System.out.println("2. No");

        opcion2 = ComprobacionOpcion.leerOpcion(1, 2);

        switch (opcion2) {

            case 1 -> {
                System.out.print("Fecha próxima (yyyy-MM-dd HH:mm), vacío para null: ");

                LocalDateTime fecha = null;

                while (true) {
                    String input = scanner.nextLine();

                    if (input.isBlank()) {
                        fecha = null;
                        break;
                    }

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        fecha = LocalDateTime.parse(input, formatter);
                        break;
                    } catch (DateTimeParseException e) {
                        System.out.print("Formato incorrecto. Usa yyyy-MM-dd HH:mm: ");
                    }
                }

                interaccionCliente.setFechaProxima(fecha);

            }case 2 ->{
                interaccionCliente.setFechaProxima(null);
            }

        }

        interaccionClienteController.modificarInteraccionCliente(opcion,  interaccionCliente);

    }

}