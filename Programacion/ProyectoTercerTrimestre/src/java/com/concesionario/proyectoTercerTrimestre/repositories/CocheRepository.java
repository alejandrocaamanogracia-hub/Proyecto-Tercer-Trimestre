package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.Coche;

import java.util.List;

public interface CocheRepository {

    void crearCoche(Coche coche);

    void eliminarCoche(int id);

    List<Coche> listarCoches();
<<<<<<< HEAD

    void modificarCoche(int id, Coche coche);

    Coche buscarCoche(int id);

=======
>>>>>>> b40c4a9e5a9be5217f0a95a74284f60254651042
}