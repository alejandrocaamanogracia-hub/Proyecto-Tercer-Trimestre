package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;

import java.util.List;

public interface CocheRepository {

    Coche findById(int id);

    List<Coche> findAll();

}
