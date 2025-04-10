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
    public enum Posicion{PORTERO, DEFENSA, MEDIOCENTRO, DELANTERO};
    private Posicion posicion;
    private Equipo equipo;
    private String dorsal;
    
    public enum Sexo{FEMENINO, MASCULINO, NO_DEFINIDO};
    private Sexo sexo;
    private int edad;
    private String nacionalidad;
    public enum TipoTarjeta{AMARILLO, ROJO};
    private TipoTarjeta tipoTarjeta;


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

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
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

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(TipoTarjeta tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }
    
    @Override
    public int compareTo(Jugador otro) {
        return this.getNombre().toLowerCase().compareTo(otro.getNombre().toLowerCase());
    }

    public Jugador() {
    }

    public Jugador(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Jugador(int IDjugador, String nombre, String apellidos, Posicion posicion, Equipo equipo, String dorsal, Sexo sexo, int edad, String nacionalidad, TipoTarjeta tipoTarjeta) {
        this.IDjugador = IDjugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.posicion = posicion;
        this.equipo = equipo;
        this.dorsal = dorsal;
        this.sexo = sexo;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
        this.tipoTarjeta = tipoTarjeta;
    }
    
    //Nuevo contructor
      public Jugador(String nombre, String apellidos,Equipo equipo, Posicion posicion, String dorsal){
      this.nombre = nombre;
      this.apellidos = apellidos;
      this.equipo = equipo;
      this.posicion = posicion;
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
