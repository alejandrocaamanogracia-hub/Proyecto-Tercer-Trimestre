package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.repositories.CocheRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.CocheRepositoryImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CocheService {

    private final CocheRepository cocheRepository;

    public CocheService() {
        this.cocheRepository = new CocheRepositoryImpl();
    }

    public void crearCoche(Coche coche) {
        if (coche == null) {
            System.out.println("El coche no puede ser nulo.");
            return;
        }

        if (coche.getMarca() == null || coche.getMarca().isBlank()) {
            System.out.println("La marca del coche es obligatoria.");
            return;
        }

        if (coche.getModelo() == null || coche.getModelo().isBlank()) {
            System.out.println("El modelo del coche es obligatorio.");
            return;
        }

        if (coche.getBastidor() == null || coche.getBastidor().isBlank()) {
            System.out.println("El bastidor del coche es obligatorio.");
            return;
        }

        if (coche.getAnio() < 1900) {
            System.out.println("El anio del coche no es valido.");
            return;
        }

        if (coche.getKilometros() < 0) {
            System.out.println("Los kilometros no pueden ser negativos.");
            return;
        }

        if (coche.getPrecio() < 0) {
            System.out.println("El precio no puede ser negativo.");
            return;
        }

        if (coche.getEstado() == null) {
            System.out.println("El estado del coche es obligatorio.");
            return;
        }

        cocheRepository.crearCoche(coche);
        System.out.println("Coche creado correctamente.");
    }

    public boolean eliminarCoche(int id) {
        if (id <= 0) {
            System.out.println("El ID del coche no es valido.");
            return false;
        }

        return cocheRepository.eliminarCoche(id);
    }

    public List<Coche> listarCoches() {
        return cocheRepository.listarCoches();
    }

    public void exportarCochesTxt() {
        List<Coche> coches = cocheRepository.listarCoches();

        if (coches.isEmpty()) {
            System.out.println("No hay coches para exportar.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("coches.txt"))) {

            writer.write("===== LISTADO DE COCHES =====");
            writer.newLine();
            writer.newLine();

            for (Coche coche : coches) {
                writer.write("ID: " + coche.getId());
                writer.newLine();
                writer.write("Marca: " + coche.getMarca());
                writer.newLine();
                writer.write("Modelo: " + coche.getModelo());
                writer.newLine();
                writer.write("Version: " + coche.getVersion());
                writer.newLine();
                writer.write("Matricula: " + coche.getMatricula());
                writer.newLine();
                writer.write("Bastidor: " + coche.getBastidor());
                writer.newLine();
                writer.write("Año: " + coche.getAnio());
                writer.newLine();
                writer.write("Kilometros: " + coche.getKilometros());
                writer.newLine();
                writer.write("Combustible: " + coche.getCombustible().getValorDb());
                writer.newLine();
                writer.write("Cambio: " + coche.getCambio().getValorDb());
                writer.newLine();
                writer.write("Color: " + coche.getColor());
                writer.newLine();
                writer.write("Precio: " + coche.getPrecio());
                writer.newLine();
                writer.write("Estado: " + coche.getEstado().getValorDb());
                writer.newLine();
                writer.write("----------------------------------");
                writer.newLine();
            }

            System.out.println("Coches exportados correctamente a coches.txt");

        } catch (IOException e) {
            System.out.println("Error al exportar los coches.");
            e.printStackTrace();
        }
    }

    public void modificarCoche(int id, Coche coche) {
        if (id <= 0) {
            System.out.println("El ID del coche no es valido.");
            return;
        }

        Coche cocheExistente = cocheRepository.buscarCoche(id);

        if (cocheExistente == null) {
            System.out.println("No existe ningun coche con ese ID.");
            return;
        }

        if (coche.getMarca() == null || coche.getMarca().isBlank()) {
            System.out.println("La marca no puede estar vacia.");
            return;
        }

        if (coche.getModelo() == null || coche.getModelo().isBlank()) {
            System.out.println("El modelo no puede estar vacio.");
            return;
        }

        if (coche.getBastidor() == null || coche.getBastidor().isBlank()) {
            System.out.println("El bastidor no puede estar vacio.");
            return;
        }

        if (coche.getAnio() < 1900) {
            System.out.println("El anio del coche debe ser mayor o igual a 1900.");
            return;
        }

        if (coche.getKilometros() < 0) {
            System.out.println("Los kilometros no pueden ser negativos.");
            return;
        }

        if (coche.getPrecio() < 0) {
            System.out.println("El precio no puede ser negativo.");
            return;
        }

        if (coche.getEstado() == null) {
            System.out.println("El estado no puede estar vacio.");
            return;
        }

        cocheRepository.modificarCoche(id, coche);
        System.out.println("Coche modificado correctamente.");
    }

    public Coche buscarCoche(int id) {
        if (id <= 0) {
            System.out.println("El ID del coche no es valido.");
            return null;
        }

        Coche coche = cocheRepository.buscarCoche(id);

        if (coche == null) {
            System.out.println("Coche no encontrado.");
            return null;
        }

        return coche;
    }

    public boolean existeCoche(int id) {
        return cocheRepository.existeCoche(id);
    }

}