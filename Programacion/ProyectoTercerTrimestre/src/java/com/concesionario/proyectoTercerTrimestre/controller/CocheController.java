package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.services.CocheService;

import java.util.List;

public class CocheController {

    private final CocheService cocheService;

    public CocheController() {
        this.cocheService = new CocheService();
    }

    public void crearCoche(String marca, String modelo, String version, String matricula,
                           String bastidor, int anio, int kilometros, String combustible,
                           String cambio, String color, double precio, String estado) {

        Coche coche = new Coche(
                marca,
                modelo,
                version,
                matricula,
                bastidor,
                anio,
                kilometros,
                combustible,
                cambio,
                color,
                precio,
                estado
        );

        cocheService.crearCoche(coche);
    }

    public void eliminarCoche(int id) {
        cocheService.eliminarCoche(id);
    }

    public List<Coche> listarCoches() {
        return cocheService.listarCoches();
    }

    public void exportarCochesTxt() {
        cocheService.exportarCochesTxt();
    }
}