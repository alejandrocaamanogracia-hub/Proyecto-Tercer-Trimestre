package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.Venta;
import com.concesionario.proyectoTercerTrimestre.repositories.VentaRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.VentaRepositoryImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService() {
        this.ventaRepository = new VentaRepositoryImpl();
    }

    public void crearVenta(Venta venta) {
        if (venta == null) {
            System.out.println("La venta no puede ser nula.");
            return;
        }

        if (venta.getClienteId() <= 0) {
            System.out.println("El ID del cliente no es valido.");
            return;
        }

        if (venta.getUsuarioId() <= 0) {
            System.out.println("El ID del usuario no es valido.");
            return;
        }

        if (venta.getFecha() == null) {
            System.out.println("La fecha de la venta es obligatoria.");
            return;
        }

        if (venta.getEstado() == null) {
            System.out.println("El estado de la venta es obligatorio.");
            return;
        }

        if (venta.getTotal() < 0) {
            System.out.println("El total de la venta no puede ser negativo.");
            return;
        }

        ventaRepository.crearVenta(venta);
        System.out.println("Venta creada correctamente.");
    }

    public void eliminarVenta(int id) {
        if (id <= 0) {
            System.out.println("El ID de la venta no es valido.");
            return;
        }

        ventaRepository.eliminarVenta(id);
        System.out.println("Venta eliminada correctamente.");
    }

    public List<Venta> listarVentas() {
        return ventaRepository.listarVentas();
    }

    public void exportarVentasTxt() {
        List<Venta> ventas = ventaRepository.listarVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ventas.txt"))) {

            writer.write("===== LISTADO DE VENTAS =====");
            writer.newLine();
            writer.newLine();

            for (Venta venta : ventas) {
                writer.write("ID: " + venta.getId());
                writer.newLine();
                writer.write("ID Cliente: " + venta.getClienteId());
                writer.newLine();
                writer.write("ID Usuario: " + venta.getUsuarioId());
                writer.newLine();
                writer.write("Fecha: " + venta.getFecha());
                writer.newLine();
                writer.write("Estado: " + venta.getEstado().getValorDb());
                writer.newLine();
                writer.write("Total: " + venta.getTotal());
                writer.newLine();
                writer.write("----------------------------------");
                writer.newLine();
            }

            System.out.println("Ventas exportadas correctamente a ventas.txt");

        } catch (IOException e) {
            System.out.println("Error al exportar las ventas.");
            e.printStackTrace();
        }
    }
}