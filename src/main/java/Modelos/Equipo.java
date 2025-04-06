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
public class Equipo {
    private int IDEquipo;
    private String nombre;
    private Date añoFundacion;
    private String localidad;
    private String entrenador;

    public Equipo() {
    }

    public Equipo(int IDEquipo, String nombre) {
        this.IDEquipo = IDEquipo;
        this.nombre = nombre;
    }

    public Equipo(int IDEquipo, String nombre, Date añoFundacion, String localidad, String entrenador) {
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

    public Date getAñoFundacion() {
        return añoFundacion;
    }

    public void setAñoFundacion(Date añoFundacion) {
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
    
    private void agregarJugador (Jugador jugador) {
        
    }
    private int calcularPuntos () {
        
        return 0;
        
    }
    private void obtenerEstadisticas () {
        
    }
}
