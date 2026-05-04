package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;
import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.entities.Combustible;
import com.concesionario.proyectoTercerTrimestre.entities.EstadoCoche;
import com.concesionario.proyectoTercerTrimestre.entities.TipoCambio;
import com.concesionario.proyectoTercerTrimestre.services.CocheService;

import java.util.List;
import java.util.Optional;

public class CocheController {

    private final CocheService cocheService;

    public CocheController() {
        this.cocheService = new CocheService();
    }

    public void crearCoche(String marca, String modelo, String version, String matricula,
                           String bastidor, int anio, int kilometros,
                           Combustible combustible, TipoCambio cambio,
                           String color, double precio, EstadoCoche estado) {

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

    public Optional<Coche> buscarCoche(int id) {
        return cocheService.buscarCoche(id);
    }
}