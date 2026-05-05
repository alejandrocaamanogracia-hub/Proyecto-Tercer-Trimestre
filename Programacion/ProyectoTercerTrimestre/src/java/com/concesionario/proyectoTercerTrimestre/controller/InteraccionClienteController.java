package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.InteraccionCliente;
import com.concesionario.proyectoTercerTrimestre.services.InteraccionClienteService;

import java.time.LocalDateTime;
import java.util.List;

public class InteraccionClienteController {

    private final InteraccionClienteService interaccionClienteService;

    public InteraccionClienteController() {
        this.interaccionClienteService = new InteraccionClienteService();
    }

    public void crearInteraccionCliente(int clienteId, int usuarioId, String tipo,
                                        LocalDateTime fecha, String asunto,
                                        String descripcion, String resultado,
                                        String proximaAccion, LocalDateTime fechaProxima) {

        InteraccionCliente interaccionCliente = new InteraccionCliente(
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

        interaccionClienteService.crearInteraccionCliente(interaccionCliente);
    }

    public void eliminarInteraccionCliente(int id) {
        interaccionClienteService.eliminarInteraccionCliente(id);
    }

    public List<InteraccionCliente> listarInteraccionesCliente() {
        return interaccionClienteService.listarInteraccionesCliente();
    }

    public void exportarInteraccionesClienteTxt() {
        interaccionClienteService.exportarInteraccionesClienteTxt();
    }
}