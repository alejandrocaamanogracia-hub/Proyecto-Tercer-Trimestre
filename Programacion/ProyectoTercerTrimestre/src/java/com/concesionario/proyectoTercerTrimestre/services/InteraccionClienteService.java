package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.InteraccionCliente;
import com.concesionario.proyectoTercerTrimestre.repositories.InteraccionClienteRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.InteraccionClienteRepositoryImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InteraccionClienteService {

    private final InteraccionClienteRepository interaccionClienteRepository;

    public InteraccionClienteService() {
        this.interaccionClienteRepository = new InteraccionClienteRepositoryImpl();
    }

    public void crearInteraccionCliente(InteraccionCliente interaccionCliente) {
        if (interaccionCliente == null) {
            System.out.println("La interaccion no puede ser nula.");
            return;
        }

        if (interaccionCliente.getClienteId() <= 0) {
            System.out.println("El ID del cliente no es valido.");
            return;
        }

        if (!interaccionClienteRepository.existeCliente(interaccionCliente.getClienteId())) {
            System.out.println("No existe ningun cliente con ese ID.");
            return;
        }

        if (interaccionCliente.getUsuarioId() <= 0) {
            System.out.println("El ID del usuario no es valido.");
            return;
        }

        if (!interaccionClienteRepository.existeUsuario(interaccionCliente.getUsuarioId())) {
            System.out.println("No existe ningun usuario con ese ID.");
            return;
        }

        if (interaccionCliente.getTipo() == null) {
            System.out.println("El tipo de interaccion es obligatorio.");
            return;
        }

        if (interaccionCliente.getFecha() == null) {
            System.out.println("La fecha de la interaccion es obligatoria.");
            return;
        }

        if (interaccionCliente.getAsunto() == null || interaccionCliente.getAsunto().isBlank()) {
            System.out.println("El asunto de la interaccion es obligatorio.");
            return;
        }

        interaccionClienteRepository.crearInteraccionCliente(interaccionCliente);
        System.out.println("Interaccion creada correctamente.");
    }

    public void eliminarInteraccionCliente(int id) {
        if (id <= 0) {
            System.out.println("El ID de la interaccion con cliente no es valido.");
            return;
        }

        boolean eliminado = interaccionClienteRepository.eliminarInteraccionCliente(id);

        if (eliminado) {
            System.out.println("Interaccion con cliente eliminada correctamente.");
        } else {
            System.out.println("No existe ninguna interaccion con cliente con ese ID.");
        }
    }

    public List<InteraccionCliente> listarInteraccionesCliente() {
        return interaccionClienteRepository.listarInteraccionesCliente();
    }

    public void exportarInteraccionesClienteTxt() {
        List<InteraccionCliente> interacciones = interaccionClienteRepository.listarInteraccionesCliente();

        if (interacciones.isEmpty()) {
            System.out.println("No hay interacciones para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("interacciones_cliente.txt"))) {

            writer.write("===== LISTADO DE INTERACCIONES CON CLIENTES =====");
            writer.newLine();
            writer.newLine();

            for (InteraccionCliente interaccion : interacciones) {
                writer.write("ID: " + interaccion.getId());
                writer.newLine();
                writer.write("ID Cliente: " + interaccion.getClienteId());
                writer.newLine();
                writer.write("ID Usuario: " + interaccion.getUsuarioId());
                writer.newLine();
                writer.write("Tipo: " + interaccion.getTipo().getValorDb());
                writer.newLine();
                writer.write("Fecha: " + interaccion.getFecha());
                writer.newLine();
                writer.write("Asunto: " + interaccion.getAsunto());
                writer.newLine();
                writer.write("Descripcion: " + interaccion.getDescripcion());
                writer.newLine();
                writer.write("Resultado: " + interaccion.getResultado());
                writer.newLine();
                writer.write("Proxima accion: " + interaccion.getProximaAccion());
                writer.newLine();
                writer.write("Fecha proxima: " + interaccion.getFechaProxima());
                writer.newLine();
                writer.write("----------------------------------");
                writer.newLine();
            }

            System.out.println("Interacciones exportadas correctamente a interacciones_cliente.txt");

        } catch (IOException e) {
            System.out.println("Error al exportar las interacciones.");
            e.printStackTrace();
        }
    }

    public void modificarInteraccionCliente(int id, InteraccionCliente interaccionCliente) {
        if (id <= 0) {
            System.out.println("El ID de la interaccion no es valido.");
            return;
        }

        InteraccionCliente interaccionExistente = interaccionClienteRepository.buscarInteraccionCliente(id);

        if (interaccionExistente == null) {
            System.out.println("No existe ninguna interaccion con ese ID.");
            return;
        }

        if (interaccionCliente.getClienteId() <= 0) {
            System.out.println("El ID del cliente no es valido.");
            return;
        }

        if (!interaccionClienteRepository.existeCliente(interaccionCliente.getClienteId())) {
            System.out.println("No existe ningun cliente con ese ID.");
            return;
        }

        if (interaccionCliente.getUsuarioId() <= 0) {
            System.out.println("El ID del usuario no es valido.");
            return;
        }

        if (!interaccionClienteRepository.existeUsuario(interaccionCliente.getUsuarioId())) {
            System.out.println("No existe ningun usuario con ese ID.");
            return;
        }

        if (interaccionCliente.getTipo() == null) {
            System.out.println("El tipo de interaccion no puede estar vacio.");
            return;
        }

        if (interaccionCliente.getFecha() == null) {
            System.out.println("La fecha de la interaccion no puede estar vacia.");
            return;
        }

        if (interaccionCliente.getAsunto() != null && interaccionCliente.getAsunto().isBlank()) {
            System.out.println("El asunto de la interaccion no es valido.");
            return;
        }

        if (interaccionCliente.getDescripcion() != null && interaccionCliente.getDescripcion().isBlank()) {
            System.out.println("La descripcion de la interaccion no es valida.");
            return;
        }

        if (interaccionCliente.getResultado() != null && interaccionCliente.getResultado().isBlank()) {
            System.out.println("El resultado de la interaccion no es valido.");
            return;
        }

        if (interaccionCliente.getProximaAccion() != null && interaccionCliente.getProximaAccion().isBlank()) {
            System.out.println("La proxima accion no es valida.");
            return;
        }

        interaccionClienteRepository.modificarInteraccionCliente(id, interaccionCliente);
        System.out.println("Interaccion modificada correctamente.");
    }

    public InteraccionCliente buscarInteraccionCliente(int id) {
        if (id <= 0) {
            System.out.println("El ID de la interaccion no es valido.");
            return null;
        }

        InteraccionCliente interaccionCliente = interaccionClienteRepository.buscarInteraccionCliente(id);

        if (interaccionCliente == null) {
            System.out.println("No existe ninguna interaccion con ese ID.");
            return null;
        }

        return interaccionCliente;
    }

    public boolean existeCliente(int clienteId) {
        if (clienteId <= 0) {
            return false;
        }

        return interaccionClienteRepository.existeCliente(clienteId);
    }

    public boolean existeUsuario(int usuarioId) {
        if (usuarioId <= 0) {
            return false;
        }

        return interaccionClienteRepository.existeUsuario(usuarioId);
    }

}