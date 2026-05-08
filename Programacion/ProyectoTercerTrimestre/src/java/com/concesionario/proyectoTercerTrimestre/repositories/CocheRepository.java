package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;

import java.util.List;

public interface CocheRepository {

    void crearCoche(Coche coche);

    void eliminarCoche(int id);

    List<Coche> listarCoches();


    void modificarCoche(int id, Coche coche);

    Coche buscarCoche(int id);


}