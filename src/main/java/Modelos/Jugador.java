/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author keiny
 */
public class Jugador {
    private int IDjugador;
    private String nombre;
    private String apellidos;
    private String posicion;
    enum Sexo{FEMENINO, MASCULINO, NO_DEFINIDO};
    private int edad;
    private String nacionalidad;
    enum TipoTarjeta{AMARILLO, ROJO};

    public Jugador() {
    }

    public Jugador(int IDjugador, String nombre) {
        this.IDjugador = IDjugador;
        this.nombre = nombre;
    }

    public Jugador(int IDjugador, String nombre, String apellidos, String posicion, int edad, String nacionalidad) {
        this.IDjugador = IDjugador;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.posicion = posicion;
        this.edad = edad;
        this.nacionalidad = nacionalidad;
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
