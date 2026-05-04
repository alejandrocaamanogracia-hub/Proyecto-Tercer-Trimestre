package com.concesionario.proyectoTercerTrimestre.controller;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.services.CocheService;

import java.util.List;

public class CocheController {

    CocheService cocheService = new CocheService();

    public Coche findById(Integer id) {
        return cocheService.findById(id);
    }

    public List<Coche> findAll() {
        return cocheService.findAll();
    }

}
