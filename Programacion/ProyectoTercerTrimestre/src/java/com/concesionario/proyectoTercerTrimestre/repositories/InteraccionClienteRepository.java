package com.concesionario.proyectoTercerTrimestre.repositories;

import com.concesionario.proyectoTercerTrimestre.entities.InteraccionCliente;

import java.util.List;

public interface InteraccionClienteRepository {

    void crearInteraccionCliente(InteraccionCliente interaccionCliente);

    void eliminarInteraccionCliente(int id);

    List<InteraccionCliente> listarInteraccionesCliente();
<<<<<<< HEAD

    void modificarInteraccionCliente(int id, InteraccionCliente interaccionCliente);

    InteraccionCliente bucarInteraccionCliente(int id);

=======
>>>>>>> b40c4a9e5a9be5217f0a95a74284f60254651042
}