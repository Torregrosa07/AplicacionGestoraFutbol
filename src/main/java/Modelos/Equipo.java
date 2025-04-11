/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Date;

/**
 *
 * @author thomas
 */
public class Equipo implements Comparable<Equipo>{
    private int IDEquipo;
    private String nombre;
    private int añoFundacion;
    private String localidad;
    private String entrenador;

    public Equipo() {
    }

    public Equipo(int IDEquipo, String nombre) {
        this.IDEquipo = IDEquipo;
        this.nombre = nombre;
    }

    public Equipo(String nombre, int añoFundacion, String localidad, String entrenador) {
        this.IDEquipo = IDEquipo;
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.localidad = localidad;
        this.entrenador = entrenador;
    }
    public Equipo(int IDEquipo, String nombre, int sañoFundacion, String localidad, String entrenador) {
        this.IDEquipo = IDEquipo;
        this.nombre = nombre;
        this.añoFundacion = añoFundacion;
        this.localidad = localidad;
        this.entrenador = entrenador;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public int getIDEquipo() {
        return IDEquipo;
    }

    public void setIDEquipo(int IDEquipo) {
        this.IDEquipo = IDEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(int añoFundacion) {
        this.añoFundacion = añoFundacion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    @Override
    public String toString() {
        return "Equipo{" + "IDEquipo=" + IDEquipo + ", nombre=" + nombre + ", a\u00f1oFundacion=" + añoFundacion + ", localidad=" + localidad + ", entrenador=" + entrenador + '}';
    }
    
    
    @Override
    public int compareTo(Equipo otro) {
        return this.nombre.compareToIgnoreCase(otro.getNombre());
    }
    
    private void agregarJugador (Jugador jugador) {
        
    }
    private int calcularPuntos () {
        
        return 0;
        
    }
    private void obtenerEstadisticas () {
        
    }
}
