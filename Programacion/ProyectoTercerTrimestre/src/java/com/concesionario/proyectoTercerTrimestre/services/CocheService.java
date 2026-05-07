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

        if (coche.getMatricula() == null || coche.getMatricula().isBlank()) {
            System.out.println("La matricula del coche es obligatoria.");
            return;
        }

        if (coche.getBastidor() == null || coche.getBastidor().isBlank()) {
            System.out.println("El bastidor del coche es obligatorio.");
            return;
        }

        if (coche.getAnio() < 1900) {
            System.out.println("El año del coche no es valido.");
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

        if (coche.getCombustible() == null) {
            System.out.println("El combustible del coche es obligatorio.");
            return;
        }

        if (coche.getCambio() == null) {
            System.out.println("El cambio del coche es obligatorio.");
            return;
        }

        if (coche.getEstado() == null) {
            System.out.println("El estado del coche es obligatorio.");
            return;
        }

        cocheRepository.crearCoche(coche);
        System.out.println("Coche creado correctamente.");
    }

    public void eliminarCoche(int id) {
        if (id <= 0) {
            System.out.println("El ID del coche no es valido.");
            return;
        }

        cocheRepository.eliminarCoche(id);
        System.out.println("Coche eliminado correctamente.");
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

    public void modificarCoche(int id, Coche coche){

        if (id <= 0 || id > cocheRepository.listarCoches().size()) {
            System.out.println("El ID del coche no es valido.");
            return;
        }

        if (coche.getAnio() < -1){
            System.out.println("El anio del coche no puede ser negativo.");
            return;
        }

        if (coche.getPrecio() < -1) {
            System.out.println("El precio no puede ser negativo");
            return;
        }

        if (coche.getKilometros() < -1){
            System.out.println("Los kilometros no pueden ser negativos");
        }

        cocheRepository.modificarCoche(id, coche);

    }

    public Coche buscarCoche(int id){

        if (id <= 0) {
            System.out.println("El ID del coche no es valido.");
            return null;
        }

        Coche coche = cocheRepository.buscarCoche(id);

        if (coche == null){
            System.out.println("Coche no encontrado.");
            return null;
        }

        return coche;

    }

}