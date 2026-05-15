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

        if (!detalleVentaRepository.existeVenta(detalleVenta.getVentaId())) {
            System.out.println("No existe una venta con ese ID.");
            return;
        }

        if (!detalleVentaRepository.existeCoche(detalleVenta.getCocheId())) {
            System.out.println("No existe un coche con ese ID.");
            return;
        }

        if (detalleVentaRepository.existeDetalleVentaConVentaYCoche(
                detalleVenta.getVentaId(),
                detalleVenta.getCocheId())) {
            System.out.println("Ya existe un detalle de venta con esa venta y ese coche.");
            return;
        }

        if (detalleVentaRepository.existeDetalleVentaConCoche(detalleVenta.getCocheId())) {
            System.out.println("Ese coche ya esta asociado a una venta.");
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

        boolean eliminado = detalleVentaRepository.eliminarDetalleVenta(id);

        if (eliminado) {
            System.out.println("Detalle de venta eliminado correctamente.");
        } else {
            System.out.println("No existe ningún detalle de venta con ese ID.");
        }
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
        if (id <= 0) {
            System.out.println("El ID del detalle de venta no es valido.");
            return;
        }

        if (detalleVenta == null) {
            System.out.println("El detalle de venta no puede ser nulo.");
            return;
        }

        DetalleVenta detalleVentaExistente = detalleVentaRepository.buscarDetalleVenta(id);

        if (detalleVentaExistente == null) {
            System.out.println("No existe ningun detalle de venta con ese ID.");
            return;
        }

        if (detalleVenta.getVentaId() <= 0) {
            System.out.println("El ID de la venta no es valido.");
            return;
        }

        if (!detalleVentaRepository.existeVenta(detalleVenta.getVentaId())) {
            System.out.println("No existe ninguna venta con ese ID.");
            return;
        }

        if (detalleVenta.getCocheId() <= 0) {
            System.out.println("El ID del coche no es valido.");
            return;
        }

        if (!detalleVentaRepository.existeCoche(detalleVenta.getCocheId())) {
            System.out.println("No existe ningun coche con ese ID.");
            return;
        }

        if (detalleVenta.getCantidad() <= 0) {
            System.out.println("La cantidad debe ser mayor que 0.");
            return;
        }

        if (detalleVentaRepository.existeDetalleVentaConVentaYCocheExcluyendoId(
                detalleVenta.getVentaId(),
                detalleVenta.getCocheId(),
                id
        )) {
            System.out.println("Ya existe un detalle de venta con esa venta y ese coche.");
            return;
        }

        if (detalleVentaRepository.existeDetalleVentaConCocheExcluyendoId(
                detalleVenta.getCocheId(),
                id
        )) {
            System.out.println("Ese coche ya esta asignado a otro detalle de venta.");
            return;
        }

        detalleVentaRepository.modificarDetalleVenta(id, detalleVenta);
        System.out.println("Detalle de venta modificado correctamente.");
    }

    public boolean existeVenta(int ventaId) {
        if (ventaId <= 0) {
            return false;
        }

        return detalleVentaRepository.existeVenta(ventaId);
    }

    public boolean existeCoche(int cocheId) {
        if (cocheId <= 0) {
            return false;
        }

        return detalleVentaRepository.existeCoche(cocheId);
    }

    public DetalleVenta buscarDetalleVenta(int id) {
        if (id <= 0) {
            System.out.println("El ID del detalle de venta no es valido.");
            return null;
        }

        DetalleVenta detalleVenta = detalleVentaRepository.buscarDetalleVenta(id);

        if (detalleVenta == null) {
            System.out.println("Detalle de venta no encontrado.");
            return null;
        }

        return detalleVenta;
    }

    public boolean existeDetalleVentaConCocheExcluyendoId(int cocheId, int idDetalleVenta) {
        if (cocheId <= 0 || idDetalleVenta <= 0) {
            return false;
        }

        return detalleVentaRepository.existeDetalleVentaConCocheExcluyendoId(cocheId, idDetalleVenta);
    }

    public boolean existeDetalleVentaConCoche(int cocheId) {
        if (cocheId <= 0) {
            return false;
        }

        return detalleVentaRepository.existeDetalleVentaConCoche(cocheId);
    }



}