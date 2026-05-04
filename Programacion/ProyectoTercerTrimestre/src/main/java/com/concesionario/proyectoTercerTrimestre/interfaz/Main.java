package com.concesionario.proyectoTercerTrimestre.interfaz;

import com.concesionario.proyectoTercerTrimestre.controller.CocheController;

public class Main {

    public static void main(String[] args) {

        CocheController cocheController =  new CocheController();

        System.out.println(cocheController.findById(2));

        System.out.println(cocheController.findAll());

    }

}