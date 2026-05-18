package com.concesionario.proyectoTercerTrimestre.interfaz;


import com.concesionario.proyectoTercerTrimestre.controller.InteraccionClienteController;
import com.concesionario.proyectoTercerTrimestre.entities.*;

import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;


public class InteraccionClienteMenu {

    private final InteraccionClienteController interaccionClienteController;
    private final DateTimeFormatter formatter;

    public InteraccionClienteMenu() {
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
            System.out.println("6. Buscar interaccion");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();

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
                case 6:
                    buscarInteraccionCliente();
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

        int clienteId = ComprobacionOpcion.leerIdExistente(
                "Introduce el ID del cliente: ",
                interaccionClienteController::existeCliente,
                "No existe ningun cliente con ese ID."
        );

        int usuarioId = ComprobacionOpcion.leerIdExistente(
                "Introduce el ID del usuario: ",
                interaccionClienteController::existeUsuario,
                "No existe ningun usuario con ese ID."
        );

        TipoInteraccion tipo = seleccionarTipoInteraccion();

        LocalDateTime fecha = LocalDateTime.now();

        String asunto = ComprobacionOpcion.leerTextoObligatorio("Asunto: ");

        System.out.print("Descripcion: ");
        String descripcion = ComprobacionOpcion.leerTexto();

        System.out.print("Resultado: ");
        String resultado = ComprobacionOpcion.leerTexto();

        System.out.print("Proxima accion: ");
        String proximaAccion = ComprobacionOpcion.leerTexto();

        LocalDateTime fechaProxima = leerFechaHoraOpcional();

        interaccionClienteController.crearInteraccionCliente(
                clienteId,
                usuarioId,
                tipo,
                fecha,
                asunto,
                descripcion.isBlank() ? null : descripcion,
                resultado.isBlank() ? null : resultado,
                proximaAccion.isBlank() ? null : proximaAccion,
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

        int opcion = ComprobacionOpcion.leerOpcion(1, 6);

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
        System.out.println("\n--- Eliminar interacción cliente ---");

        List<InteraccionCliente> interaccionesCliente = interaccionClienteController.listarInteraccionesCliente();

        if (interaccionesCliente.isEmpty()) {
            System.out.println("No hay interacciones con clientes registradas.");
            return;
        }

        System.out.println("Interacciones disponibles:");

        for (InteraccionCliente interaccionCliente : interaccionesCliente) {
            System.out.println(
                    "ID: " + interaccionCliente.getId()
                            + " | ID Cliente: " + interaccionCliente.getClienteId()
                            + " | ID Usuario: " + interaccionCliente.getUsuarioId()
                            + " | Tipo: " + interaccionCliente.getTipo()
                            + " | Asunto: " + interaccionCliente.getAsunto()
            );
        }

        System.out.print("Introduce el ID de la interacción que quieres eliminar: ");
        int id = ComprobacionOpcion.leerInt();

        System.out.println("¿Seguro que quieres eliminar la interacción con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            interaccionClienteController.eliminarInteraccionCliente(id);
        } else {
            System.out.println("Eliminación cancelada.");
        }
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
        System.out.println("\n--- Modificar interacción cliente ---");

        List<InteraccionCliente> interaccionClientes = interaccionClienteController.listarInteraccionesCliente();

        if (interaccionClientes.isEmpty()) {
            System.out.println("No hay interacciones registradas.");
            return;
        }

        System.out.println("Que interacción quieres modificar: ");

        int iterador = 1;
        for (InteraccionCliente interaccionActual : interaccionClientes) {
            System.out.println(
                    iterador + ". ID: " + interaccionActual.getId()
                            + " | ID Cliente: " + interaccionActual.getClienteId()
                            + " | ID Usuario: " + interaccionActual.getUsuarioId()
                            + " | Tipo: " + interaccionActual.getTipo()
                            + " | Asunto: " + interaccionActual.getAsunto()
            );
            iterador++;
        }

        int opcion = ComprobacionOpcion.leerOpcion(1, interaccionClientes.size());

        InteraccionCliente interaccionActual = interaccionClientes.get(opcion - 1);
        int idInteraccion = interaccionActual.getId();

        InteraccionCliente interaccionModificada = new InteraccionCliente();

        interaccionModificada.setClienteId(interaccionActual.getClienteId());
        interaccionModificada.setUsuarioId(interaccionActual.getUsuarioId());
        interaccionModificada.setTipo(interaccionActual.getTipo());
        interaccionModificada.setFecha(interaccionActual.getFecha());
        interaccionModificada.setAsunto(interaccionActual.getAsunto());
        interaccionModificada.setDescripcion(interaccionActual.getDescripcion());
        interaccionModificada.setResultado(interaccionActual.getResultado());
        interaccionModificada.setProximaAccion(interaccionActual.getProximaAccion());
        interaccionModificada.setFechaProxima(interaccionActual.getFechaProxima());

        System.out.println("\nModificar ID del cliente actual: " + interaccionActual.getClienteId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setClienteId(
                    ComprobacionOpcion.leerIdExistente(
                            "Introduce el ID del cliente: ",
                            interaccionClienteController::existeCliente,
                            "No existe ningun cliente con ese ID."
                    )
            );
        }

        System.out.println("\nModificar ID del usuario actual: " + interaccionActual.getUsuarioId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setUsuarioId(
                    ComprobacionOpcion.leerIdExistente(
                            "Introduce el ID del usuario: ",
                            interaccionClienteController::existeUsuario,
                            "No existe ningun usuario con ese ID."
                    )
            );
        }

        System.out.println("\nModificar tipo actual: " + interaccionActual.getTipo());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setTipo(seleccionarTipoInteraccion());
        }

        System.out.println("\nModificar asunto actual: " + interaccionActual.getAsunto());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setAsunto(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el asunto: ")
            );
        }

        System.out.println("\nModificar descripcion actual: " + interaccionActual.getDescripcion());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setDescripcion(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce la descripcion: ")
            );
        }

        System.out.println("\nModificar resultado actual: " + interaccionActual.getResultado());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setResultado(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el resultado: ")
            );
        }

        System.out.println("\nModificar proxima accion actual: " + interaccionActual.getProximaAccion());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setProximaAccion(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce la proxima accion: ")
            );
        }

        System.out.println("\nModificar fecha proxima actual: " + interaccionActual.getFechaProxima());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            interaccionModificada.setFechaProxima(leerFechaHoraOpcional());
        }

        interaccionClienteController.modificarInteraccionCliente(idInteraccion, interaccionModificada);
    }

    private LocalDateTime leerFechaHoraOpcional() {
        while (true) {
            System.out.print("Fecha proxima (yyyy-MM-dd HH:mm) o vacio si no hay: ");
            String fechaTexto = ComprobacionOpcion.leerTexto();

            if (fechaTexto.isBlank()) {
                return null;
            }

            try {
                return LocalDateTime.parse(fechaTexto, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Formato incorrecto. Introduce yyyy-MM-dd HH:mm.");
            }
        }
    }

    public void buscarInteraccionCliente() {
        System.out.println("\n--- Buscar interacción cliente ---");

        List<InteraccionCliente> interaccionClientes = interaccionClienteController.listarInteraccionesCliente();

        if (interaccionClientes.isEmpty()) {
            System.out.println("No hay interacciones registradas.");
            return;
        }

        System.out.println("Que interacción quieres ver: ");

        for (InteraccionCliente interaccionCliente : interaccionClientes) {
            System.out.println(
                    interaccionCliente.getId()
                            + ". ID Cliente: " + interaccionCliente.getClienteId()
                            + " | ID Usuario: " + interaccionCliente.getUsuarioId()
                            + " | Tipo: " + interaccionCliente.getTipo()
                            + " | Asunto: " + interaccionCliente.getAsunto()
            );
        }

        System.out.print("Introduce el ID de la interacción: ");
        InteraccionCliente interaccionCliente = interaccionClienteController.buscarInteraccionCliente(ComprobacionOpcion.leerInt());

        if (interaccionCliente != null) {
            System.out.println(interaccionCliente);
        }
    }
}