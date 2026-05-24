package com.concesionario.proyectoTercerTrimestre.interfaz;


import com.concesionario.proyectoTercerTrimestre.controllers.*;
import com.concesionario.proyectoTercerTrimestre.entities.*;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.util.ArrayList;
import java.util.List;


public class DetalleVentaMenu {


    private final DetalleVentaController detalleVentaController;
    private final VentaController ventaController;
    private final CocheController cocheController;
    private final ClienteController clienteController;
    private final UsuarioController usuarioController;

    public DetalleVentaMenu() {
        this.detalleVentaController = new DetalleVentaController();
        this.ventaController = new VentaController();
        this.cocheController = new CocheController();
        this.clienteController = new ClienteController();
        this.usuarioController = new UsuarioController();
    }

    public void mostrarMenuDetalleVenta() {
        int opcion;

        do {
            System.out.println("\n===== MENU DETALLE VENTA =====");
            System.out.println("1. Crear detalle de venta");
            System.out.println("2. Eliminar detalle de venta");
            System.out.println("3. Listar detalles de venta");
            System.out.println("4. Exportar detalles de venta a TXT");
            System.out.println("5. Modificar detalle de venta");
            System.out.println("6. Buscar detalle de venta");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();

            switch (opcion) {
                case 1:
                    crearDetalleVenta();
                    break;
                case 2:
                    eliminarDetalleVenta();
                    break;
                case 3:
                    listarDetallesVenta();
                    break;
                case 4:
                    exportarDetallesVentaTxt();
                    break;
                case 5:
                    modificarDetalleVenta();
                    break;
                case 6:
                    buscarDetalleVenta();
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

    private void crearDetalleVenta() {
        System.out.println("\n--- Crear detalle de venta ---");

        List<Venta> ventas = ventaController.listarVentas();

        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        List<Cliente> clientes = clienteController.listarClientes();
        List<Usuario> usuarios = usuarioController.listarUsuarios();

        System.out.println("\nVentas disponibles:");

        for (Venta ventaActual : ventas) {

            String nombreCliente = "Cliente no encontrado";
            for (Cliente clienteActual : clientes) {
                if (clienteActual.getId() == ventaActual.getClienteId()) {
                    nombreCliente = clienteActual.getNombre();
                    break;
                }
            }

            String nombreUsuario = "Usuario no encontrado";
            for (Usuario usuarioActual : usuarios) {
                if (usuarioActual.getId() == ventaActual.getUsuarioId()) {
                    nombreUsuario = usuarioActual.getNombre();
                    break;
                }
            }

            System.out.println(
                    "ID Venta: " + ventaActual.getId()
                            + " | Cliente: " + nombreCliente
                            + " | Usuario: " + nombreUsuario
                            + " | Fecha: " + ventaActual.getFecha()
                            + " | Estado: " + ventaActual.getEstado()
                            + " | Total: " + ventaActual.getTotal()
            );
        }

        int ventaId = pedirVentaExistente();

        List<Coche> coches = cocheController.listarCoches();
        List<Coche> cochesDisponibles = new ArrayList<>();

        for (Coche cocheActual : coches) {
            boolean cocheYaUsado = detalleVentaController.existeDetalleVentaConCoche(cocheActual.getId());

            if (!cocheYaUsado) {
                cochesDisponibles.add(cocheActual);
            }
        }

        if (cochesDisponibles.isEmpty()) {
            System.out.println("No hay coches disponibles para asignar.");
            return;
        }

        System.out.println("\nCoches disponibles para asignar:");

        for (Coche cocheActual : cochesDisponibles) {
            System.out.println(
                    "ID: " + cocheActual.getId()
                            + " | Matricula: " + cocheActual.getMatricula()
                            + " | Marca: " + cocheActual.getMarca()
                            + " | Modelo: " + cocheActual.getModelo()
                            + " | Precio: " + cocheActual.getPrecio()
            );
        }

        int cocheId = pedirCocheDisponibleParaCrear();

        double precioFinal = ComprobacionOpcion.leerDoubleMinimo("Introduce el precio final: ", 0);

        double descuento = ComprobacionOpcion.leerDoubleMinimo("Introduce el descuento: ", 0);

        detalleVentaController.crearDetalleVenta(ventaId, cocheId, precioFinal, descuento);
    }

    private int pedirCocheDisponibleParaCrear() {
        int cocheId;
        boolean cocheExiste;
        boolean cocheYaUsado = false;

        do {
            cocheId = ComprobacionOpcion.leerIntMinimo("Introduce el ID del coche: ", 1);

            cocheExiste = detalleVentaController.existeCoche(cocheId);

            if (!cocheExiste) {
                System.out.println("No existe ningun coche con ese ID.");
                cocheYaUsado = false;
                continue;
            }

            cocheYaUsado = detalleVentaController.existeDetalleVentaConCoche(cocheId);

            if (cocheYaUsado) {
                System.out.println("Ese coche ya esta asignado a otro detalle de venta.");
            }

        } while (!cocheExiste || cocheYaUsado);

        return cocheId;
    }

    private void eliminarDetalleVenta() {
        System.out.println("\n--- Eliminar detalle de venta ---");

        List<DetalleVenta> detallesVenta = detalleVentaController.listarDetallesVenta();

        if (detallesVenta.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        System.out.println("Detalles de venta disponibles:");

        for (DetalleVenta detalleVenta : detallesVenta) {
            System.out.println(
                    "ID: " + detalleVenta.getId()
                            + " | ID Venta: " + detalleVenta.getVentaId()
                            + " | ID Coche: " + detalleVenta.getCocheId()
                            + " | Precio final: " + detalleVenta.getPrecioFinal()
                            + " | Descuento: " + detalleVenta.getDescuento()
            );
        }

        System.out.print("Introduce el ID del detalle de venta que quieres eliminar: ");
        int id = ComprobacionOpcion.leerInt();

        System.out.println("¿Seguro que quieres eliminar el detalle de venta con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            detalleVentaController.eliminarDetalleVenta(id);
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private void listarDetallesVenta() {
        System.out.println("\n--- Lista de detalles de venta ---");

        List<DetalleVenta> detallesVenta = detalleVentaController.listarDetallesVenta();

        if (detallesVenta.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        for (DetalleVenta detalleVenta : detallesVenta) {
            System.out.println(detalleVenta);
        }
    }

    private void exportarDetallesVentaTxt() {
        detalleVentaController.exportarDetallesVentaTxt();
    }

    private void modificarDetalleVenta() {
        System.out.println("\n--- Modificar detalle de venta ---");

        List<DetalleVenta> detalleVentas = detalleVentaController.listarDetallesVenta();

        if (detalleVentas.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        System.out.println("Introduce el numero del detalle de venta que quieres modificar: ");

        System.out.println("Detalles de venta disponibles:");

        int iterador = 1;
        for (DetalleVenta detalleVentaActual : detalleVentas) {
            System.out.println(
                    iterador + ". ID Venta: " + detalleVentaActual.getVentaId()
                            + " | ID Coche: " + detalleVentaActual.getCocheId()
                            + " | Precio final: " + detalleVentaActual.getPrecioFinal()
                            + " | Descuento: " + detalleVentaActual.getDescuento()
                            + " | ID real: " + detalleVentaActual.getId()
            );
            iterador++;
        }

        int opcion = ComprobacionOpcion.leerOpcion(1, detalleVentas.size());

        DetalleVenta detalleVentaActual = detalleVentas.get(opcion - 1);
        int idDetalleVenta = detalleVentaActual.getId();

        DetalleVenta detalleVentaModificado = new DetalleVenta();

        detalleVentaModificado.setVentaId(detalleVentaActual.getVentaId());
        detalleVentaModificado.setCocheId(detalleVentaActual.getCocheId());
        detalleVentaModificado.setPrecioFinal(detalleVentaActual.getPrecioFinal());
        detalleVentaModificado.setDescuento(detalleVentaActual.getDescuento());

        System.out.println("\nModificar ID de la venta actual: " + detalleVentaActual.getVentaId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setVentaId(pedirVentaExistente());
        }

        System.out.println("\nModificar ID del coche actual: " + detalleVentaActual.getCocheId());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setCocheId(pedirCocheDisponibleParaModificar(idDetalleVenta));
        }

        System.out.println("\nModificar precio final actual: " + detalleVentaActual.getPrecioFinal());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setPrecioFinal(
                    ComprobacionOpcion.leerDoubleMinimo("Introduce el precio final: ", 0)
            );
        }

        System.out.println("\nModificar precio final actual: " + detalleVentaActual.getPrecioFinal());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setPrecioFinal(
                    ComprobacionOpcion.leerDoubleMinimo("Introduce el precio final: ", 0)
            );
        }

        System.out.println("\nModificar descuento actual: " + detalleVentaActual.getDescuento());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            detalleVentaModificado.setDescuento(
                    ComprobacionOpcion.leerDoubleMinimo("Introduce el descuento: ", 0)
            );
        }

        detalleVentaController.modificarDetalleVenta(idDetalleVenta, detalleVentaModificado);
    }

    private int pedirVentaExistente() {
        int ventaId;
        boolean existe;

        do {
            ventaId = ComprobacionOpcion.leerIntMinimo("Introduce el ID de la venta: ", 1);
            existe = detalleVentaController.existeVenta(ventaId);

            if (!existe) {
                System.out.println("No existe ninguna venta con ese ID.");
            }

        } while (!existe);

        return ventaId;
    }

    private int pedirCocheDisponibleParaModificar(int idDetalleVentaActual) {
        int cocheId;
        boolean cocheExiste;
        boolean cocheYaUsado = false;

        do {
            cocheId = ComprobacionOpcion.leerIntMinimo("Introduce el ID del coche: ", 1);

            cocheExiste = detalleVentaController.existeCoche(cocheId);

            if (!cocheExiste) {
                System.out.println("No existe ningun coche con ese ID.");
                cocheYaUsado = false;
                continue;
            }

            cocheYaUsado = detalleVentaController.existeDetalleVentaConCocheExcluyendoId(cocheId, idDetalleVentaActual);

            if (cocheYaUsado) {
                System.out.println("Ese coche ya esta asignado a otro detalle de venta.");
            }

        } while (!cocheExiste || cocheYaUsado);

        return cocheId;
    }

    public void buscarDetalleVenta() {
        System.out.println("\n--- Buscar detalle de venta ---");

        List<DetalleVenta> detalleVentas = detalleVentaController.listarDetallesVenta();

        if (detalleVentas.isEmpty()) {
            System.out.println("No hay detalles de venta registrados.");
            return;
        }

        System.out.println("Que detalle de venta quieres ver: ");

        for (DetalleVenta detalleVenta : detalleVentas) {
            System.out.println(
                    detalleVenta.getId()
                            + ". ID Venta: " + detalleVenta.getVentaId()
                            + " | ID Coche: " + detalleVenta.getCocheId()
                            + " | Precio final: " + detalleVenta.getPrecioFinal()
                            + " | Descuento: " + detalleVenta.getDescuento()
            );
        }

        System.out.print("Introduce el ID del detalle de venta: ");
        DetalleVenta detalleVenta = detalleVentaController.buscarDetalleVenta(ComprobacionOpcion.leerInt());

        if (detalleVenta != null) {
            System.out.println(detalleVenta);
        }
    }





}