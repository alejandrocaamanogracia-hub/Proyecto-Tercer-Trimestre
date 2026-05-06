package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.DetalleVenta;
import com.concesionario.proyectoTercerTrimestre.repositories.DetalleVentaRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.DetalleVentaRepositoryImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService() {
        this.detalleVentaRepository = new DetalleVentaRepositoryImpl();
    }

    public void crearDetalleVenta(DetalleVenta detalleVenta) {
        if (detalleVenta == null) {
            System.out.println("El detalle de venta no puede ser nulo.");
            return;
        }

        if (detalleVenta.getVentaId() <= 0) {
            System.out.println("El ID de la venta no es valido.");
            return;
        }

        if (detalleVenta.getCocheId() <= 0) {
            System.out.println("El ID del coche no es valido.");
            return;
        }

        if (detalleVenta.getCantidad() <= 0) {
            System.out.println("La cantidad debe ser mayor que 0.");
            return;
        }

        if (detalleVenta.getPrecioUnitario() < 0) {
            System.out.println("El precio unitario no puede ser negativo.");
            return;
        }

        detalleVentaRepository.crearDetalleVenta(detalleVenta);
        System.out.println("Detalle de venta creado correctamente.");
    }

    public void eliminarDetalleVenta(int id) {
        if (id <= 0) {
            System.out.println("El ID del detalle de venta no es valido.");
            return;
        }

        detalleVentaRepository.eliminarDetalleVenta(id);
        System.out.println("Detalle de venta eliminado correctamente.");
    }

    public List<DetalleVenta> listarDetallesVenta() {
        return detalleVentaRepository.listarDetallesVenta();
    }

    public void exportarDetallesVentaTxt() {
        List<DetalleVenta> detallesVenta = detalleVentaRepository.listarDetallesVenta();

        if (detallesVenta.isEmpty()) {
            System.out.println("No hay detalles de venta para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("detalle_venta.txt"))) {

            writer.write("===== LISTADO DE DETALLES DE VENTA =====");
            writer.newLine();
            writer.newLine();

            for (DetalleVenta detalleVenta : detallesVenta) {
                writer.write("ID: " + detalleVenta.getId());
                writer.newLine();
                writer.write("ID Venta: " + detalleVenta.getVentaId());
                writer.newLine();
                writer.write("ID Coche: " + detalleVenta.getCocheId());
                writer.newLine();
                writer.write("Cantidad: " + detalleVenta.getCantidad());
                writer.newLine();
                writer.write("Precio unitario: " + detalleVenta.getPrecioUnitario());
                writer.newLine();
                writer.write("----------------------------------");
                writer.newLine();
            }

            System.out.println("Detalles de venta exportados correctamente a detalle_venta.txt");

        } catch (IOException e) {
            System.out.println("Error al exportar los detalles de venta.");
            e.printStackTrace();
        }
    }

    public void modificarDetalleVenta(int id, DetalleVenta detalleVenta) {

        if (id <= 0 || id > detalleVentaRepository.listarDetallesVenta().size()) {
            System.out.println("El ID del detalle de venta no es valido.");
            return;
        }

        if (detalleVenta.getCantidad() < 0) {
            System.out.println("La cantidad debe ser mayor que 0.");
            return;
        }

        if (detalleVenta.getPrecioUnitario() < 0) {
            System.out.println("El precio unitario no puede ser negativo.");
            return;
        }

        detalleVentaRepository.modificarDetalleVenta(id, detalleVenta);
    }

}