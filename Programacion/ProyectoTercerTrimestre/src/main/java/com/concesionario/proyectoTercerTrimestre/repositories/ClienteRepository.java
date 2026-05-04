package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Cliente;

import java.util.List;

public interface ClienteRepository {

    Cliente findById(int id);

    List<Cliente> findAll();

}
