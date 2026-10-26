package com.proyectou3.models;

/**
 *
 * @author erika
 */
public class Personaje {

    public int id;
    public String nombre;
    public int danio;
    public String habilidad;
    public int costo;

    public Personaje(int id, String nombre, int danio, String habilidad, int costo) {
        this.id = id;
        this.nombre = nombre;
        this.danio = danio;
        this.habilidad = habilidad;
        this.costo = costo;
    }

    public int getInt() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getDanio() {
        return danio;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public int getCosto() {
        return costo;
    }
}
