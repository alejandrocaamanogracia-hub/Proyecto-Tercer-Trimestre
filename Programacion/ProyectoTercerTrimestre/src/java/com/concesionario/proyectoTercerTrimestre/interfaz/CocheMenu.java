package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controllers.CocheController;
import com.concesionario.proyectoTercerTrimestre.entities.*;
import com.concesionario.proyectoTercerTrimestre.utils.ComprobacionOpcion;

import java.util.List;


public class CocheMenu {


    private final CocheController cocheController;

    public CocheMenu() {
        this.cocheController = new CocheController();
    }

    public void mostrarMenuCoches() {
        int opcion;

        do {
            System.out.println("\n===== MENU COCHES =====");
            System.out.println("1. Crear coche");
            System.out.println("2. Eliminar coche");
            System.out.println("3. Listar coches");
            System.out.println("4. Exportar coches a TXT");
            System.out.println("5. Modificar un coche");
            System.out.println("6. Buscar un coche");
            System.out.println("0. Volver");
            System.out.print("Elige una opcion: ");

            opcion = ComprobacionOpcion.leerInt();

            switch (opcion) {
                case 1:
                    crearCoche();
                    break;
                case 2:
                    eliminarCoche();
                    break;
                case 3:
                    listarCoches();
                    break;
                case 4:
                    exportarCochesTxt();
                    break;
                case 5:
                    modificarCoches();
                    break;
                case 6:
                    buscarCoche();
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

    private void crearCoche() {
        System.out.println("\n--- Crear coche ---");

        String marca = ComprobacionOpcion.leerTextoObligatorio("Marca: ");
        String modelo = ComprobacionOpcion.leerTextoObligatorio("Modelo: ");

        System.out.print("Version: ");
        String version = ComprobacionOpcion.leerTexto();

        System.out.print("Matricula: ");
        String matricula = ComprobacionOpcion.leerTexto();

        String bastidor = ComprobacionOpcion.leerTextoObligatorio("Bastidor: ");

        int anio = ComprobacionOpcion.leerIntMinimo("Anio: ", 1900);

        int kilometros = ComprobacionOpcion.leerIntMinimo("Kilometros: ", 0);

        Combustible combustible = seleccionarCombustible();

        TipoCambio cambio = seleccionarTipoCambio();

        System.out.print("Color: ");
        String color = ComprobacionOpcion.leerTexto();

        double precio = ComprobacionOpcion.leerDoubleMinimo("Precio: ", 0);

        EstadoCoche estado = seleccionarEstadoCoche();

        cocheController.crearCoche(
                marca,
                modelo,
                version.isBlank() ? null : version,
                matricula.isBlank() ? null : matricula,
                bastidor,
                anio,
                kilometros,
                combustible,
                cambio,
                color.isBlank() ? null : color,
                precio,
                estado
        );
    }

    private Combustible seleccionarCombustible() {
        System.out.println("Combustible:");
        System.out.println("1. Gasolina");
        System.out.println("2. Diesel");
        System.out.println("3. Hibrido");
        System.out.println("4. Electrico");
        System.out.println("5. GLP");
        System.out.print("Elige una opcion: ");

        int opcion = ComprobacionOpcion.leerOpcion(1, 5);

        return switch (opcion) {
            case 1 -> Combustible.GASOLINA;
            case 2 -> Combustible.DIESEL;
            case 3 -> Combustible.HIBRIDO;
            case 4 -> Combustible.ELECTRICO;
            case 5 -> Combustible.GLP;
            default -> Combustible.GASOLINA;
        };
    }

    private TipoCambio seleccionarTipoCambio() {
        System.out.println("Cambio:");
        System.out.println("1. Manual");
        System.out.println("2. Automatico");
        System.out.print("Elige una opcion: ");

        int opcion = ComprobacionOpcion.leerOpcion(1, 2);

        return switch (opcion) {
            case 1 -> TipoCambio.MANUAL;
            case 2 -> TipoCambio.AUTOMATICO;
            default -> TipoCambio.MANUAL;
        };
    }

    private EstadoCoche seleccionarEstadoCoche() {
        System.out.println("Estado:");
        System.out.println("1. Disponible");
        System.out.println("2. Reservado");
        System.out.println("3. Vendido");
        System.out.print("Elige una opcion: ");

        int opcion = ComprobacionOpcion.leerOpcion(1, 3);

        return switch (opcion) {
            case 1 -> EstadoCoche.DISPONIBLE;
            case 2 -> EstadoCoche.RESERVADO;
            case 3 -> EstadoCoche.VENDIDO;
            default -> EstadoCoche.DISPONIBLE;
        };
    }

    private void eliminarCoche() {
        System.out.println("\n--- Eliminar coche ---");

        List<Coche> coches = cocheController.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
            return;
        }

        System.out.println("Coches disponibles:");

        for (Coche coche : coches) {
            System.out.println(
                    "ID: " + coche.getId()
                            + " | Marca: " + coche.getMarca()
                            + " | Modelo: " + coche.getModelo()
                            + " | Matrícula: " + coche.getMatricula()
                            + " | Estado: " + coche.getEstado()
            );
        }

        int id = ComprobacionOpcion.leerIdExistente(
                "Introduce el ID del coche que quieres eliminar: ",
                cocheController::existeCoche,
                "No existe ningún coche con ese ID."
        );

        System.out.println("¿Seguro que quieres eliminar el coche con ID " + id + "?");
        System.out.println("1. Sí");
        System.out.println("2. No");

        int confirmacion = ComprobacionOpcion.leerOpcion(1, 2);

        if (confirmacion == 1) {
            boolean eliminado = cocheController.eliminarCoche(id);

            if (eliminado) {
                System.out.println("Coche eliminado correctamente.");
            }
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    private void listarCoches() {
        System.out.println("\n--- Lista de coches ---");

        List<Coche> coches = cocheController.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
            return;
        }

        for (Coche coche : coches) {
            System.out.println(coche);
        }
    }

    private void exportarCochesTxt() {
        cocheController.exportarCochesTxt();
    }

    private void modificarCoches() {
        System.out.println("\n--- Modificar coche ---");

        List<Coche> coches = cocheController.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
            return;
        }

        System.out.println("Selecciona el numero del coche que quieres modificar: ");

        int iterador = 1;
        for (Coche cocheActual : coches) {
            System.out.println(
                     iterador + ". Matricula: " + cocheActual.getMatricula()
                            + " | Marca: " + cocheActual.getMarca()
                            + " | Modelo: " + cocheActual.getModelo()
                            + " | Precio: " + cocheActual.getPrecio()
                            + " | ID interno: " + cocheActual.getId()
            );
            iterador++;
        }

        int opcion = ComprobacionOpcion.leerOpcion(1, coches.size());

        Coche cocheActual = coches.get(opcion - 1);
        int idCoche = cocheActual.getId();

        Coche cocheModificado = new Coche();

        cocheModificado.setMarca(cocheActual.getMarca());
        cocheModificado.setModelo(cocheActual.getModelo());
        cocheModificado.setVersion(cocheActual.getVersion());
        cocheModificado.setMatricula(cocheActual.getMatricula());
        cocheModificado.setBastidor(cocheActual.getBastidor());
        cocheModificado.setAnio(cocheActual.getAnio());
        cocheModificado.setKilometros(cocheActual.getKilometros());
        cocheModificado.setCombustible(cocheActual.getCombustible());
        cocheModificado.setCambio(cocheActual.getCambio());
        cocheModificado.setColor(cocheActual.getColor());
        cocheModificado.setPrecio(cocheActual.getPrecio());
        cocheModificado.setEstado(cocheActual.getEstado());

        System.out.println("\nModificar marca actual: " + cocheActual.getMarca());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setMarca(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce la marca del coche: ")
            );
        }

        System.out.println("\nModificar modelo actual: " + cocheActual.getModelo());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setModelo(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el modelo del coche: ")
            );
        }

        System.out.println("\nModificar version actual: " + cocheActual.getVersion());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            System.out.print("Introduce la version del coche: ");
            String version = ComprobacionOpcion.leerTexto();
            cocheModificado.setVersion(version.isBlank() ? null : version);
        }

        System.out.println("\nModificar matricula actual: " + cocheActual.getMatricula());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            System.out.print("Introduce la matricula: ");
            String matricula = ComprobacionOpcion.leerTexto();
            cocheModificado.setMatricula(matricula.isBlank() ? null : matricula);
        }

        System.out.println("\nModificar bastidor actual: " + cocheActual.getBastidor());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setBastidor(
                    ComprobacionOpcion.leerTextoObligatorio("Introduce el bastidor del coche: ")
            );
        }

        System.out.println("\nModificar anio actual: " + cocheActual.getAnio());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setAnio(
                    ComprobacionOpcion.leerIntMinimo("Introduce el anio del coche: ", 1900)
            );
        }

        System.out.println("\nModificar kilometros actuales: " + cocheActual.getKilometros());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setKilometros(
                    ComprobacionOpcion.leerIntMinimo("Introduce los kilometros del coche: ", 0)
            );
        }

        System.out.println("\nModificar combustible actual: " + cocheActual.getCombustible());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setCombustible(seleccionarCombustible());
        }

        System.out.println("\nModificar cambio actual: " + cocheActual.getCambio());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setCambio(seleccionarTipoCambio());
        }

        System.out.println("\nModificar color actual: " + cocheActual.getColor());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            System.out.print("Introduce el color del coche: ");
            String color = ComprobacionOpcion.leerTexto();
            cocheModificado.setColor(color.isBlank() ? null : color);
        }

        System.out.println("\nModificar precio actual: " + cocheActual.getPrecio());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setPrecio(
                    ComprobacionOpcion.leerDoubleMinimo("Introduce el precio del coche: ", 0)
            );
        }

        System.out.println("\nModificar estado actual: " + cocheActual.getEstado());
        System.out.println("1. Si");
        System.out.println("2. No");

        if (ComprobacionOpcion.leerOpcion(1, 2) == 1) {
            cocheModificado.setEstado(seleccionarEstadoCoche());
        }

        cocheController.modificarCoche(idCoche, cocheModificado);
    }

    public void buscarCoche() {
        System.out.println("\n--- Buscar coche ---");

        List<Coche> coches = cocheController.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
            return;
        }

        System.out.println("Que coche quieres ver: ");

        for (Coche coche : coches) {
            System.out.println(
                    coche.getId() + ". "
                            + coche.getMatricula()
                            + " | " + coche.getMarca()
                            + " " + coche.getModelo()
            );
        }

        System.out.print("Introduce el ID del coche: ");
        Coche coche = cocheController.buscarCoche(ComprobacionOpcion.leerInt());

        if (coche != null) {
            System.out.println(coche);
        }
    }

}