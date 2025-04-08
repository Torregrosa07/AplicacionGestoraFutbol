/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.Serializable;

/**
 *
 * @author keiny
 */
public class Jugador implements Comparable<Jugador>, Serializable{
    private int IDjugador;
    private String nombre;
    private String apellidos;
    private String posicion;
    private Equipo equipo;
    private String dorsal;
    
    enum Sexo{FEMENINO, MASCULINO, NO_DEFINIDO};
    private int edad;
    private String nacionalidad;
    enum TipoTarjeta{AMARILLO, ROJO};

    @Override
    public int compareTo(Jugador otro) {
        return this.getNombre().toLowerCase().compareTo(otro.getNombre().toLowerCase());
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public int getIDjugador() {
        return IDjugador;
    }

    public void setIDjugador(int IDjugador) {
        this.IDjugador = IDjugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    

    public Jugador() {
    }

    public Jugador(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Jugador(int IDjugador, String nombre, String apellidos, String posicion, Equipo equipo, String dorsal, int edad, String nacionalidad) {
        this.IDjugador = IDjugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.posicion = posicion;
        this.equipo = equipo;
        this.dorsal = dorsal;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
    }
    
    
    //Constructor para el metodo de añdair
    public Jugador(String nombre, String apellidos, String posicion, Equipo equipo, String dorsal) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.posicion = posicion;
        this.equipo = equipo;
        this.dorsal = dorsal;
        
    }
    
    
    
    
  
    @Override
    public String toString() {
        return "Jugador{" + "IDjugador=" + IDjugador + ", nombre=" + nombre + ", apellidos=" + apellidos + ", posicion=" + posicion + ", edad=" + edad + ", nacionalidad=" + nacionalidad + '}';
    }

    private void registrarGol(){
        
    }   
    
    private void registrarTarjeta(){
        
    }
    private float calcularRendimiento(){
        return 0;
    }
    
    
}
