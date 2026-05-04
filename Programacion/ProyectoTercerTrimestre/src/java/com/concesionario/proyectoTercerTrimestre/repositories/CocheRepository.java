package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;

import java.util.List;

public interface CocheRepository {

    void crearCoche(Coche coche);

    void eliminarCoche(int id);

    List<Coche> listarCoches();

    Coche  buscarCoche(int id);

}