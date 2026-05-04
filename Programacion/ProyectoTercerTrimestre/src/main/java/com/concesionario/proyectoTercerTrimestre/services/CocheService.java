package com.concesionario.proyectoTercerTrimestre.services;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;
import com.concesionario.proyectoTercerTrimestre.repositories.CocheRepository;
import com.concesionario.proyectoTercerTrimestre.repositories.impl.CocheRepositoryImpl;

import java.util.List;

public class CocheService {

   CocheRepository cocheRepository = new CocheRepositoryImpl();

   public Coche findById(int id){

       Coche coche =  cocheRepository.findById(id);

       return coche;

   }

   public List<Coche> findAll(){

       List<Coche> coches = cocheRepository.findAll();

       if (coches.isEmpty()) {
           throw new RuntimeException("No hay clientes");
       }

       return coches;

   }


}
