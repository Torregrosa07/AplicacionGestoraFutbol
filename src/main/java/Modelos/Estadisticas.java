/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author thomas
 */
public class Estadisticas {
    private int IDEstadistica;
    private Jugador jugador;
    private Partido partido;
    private int goles;
    private int asistencias;
    private int tarjetasAmarillas;
    private int tarjetasRojas;

    public Estadisticas() {
    }

    public Estadisticas(int IDEstadistica, Jugador jugador, Partido partido) {
        this.IDEstadistica = IDEstadistica;
        this.jugador = jugador;
        this.partido = partido;
    }

   
    public Estadisticas(int IDEstadistica, Jugador jugador, Partido partido, int goles, int asistencias, int tarjetasAmarillas, int tarjetasRojas) {
        this.IDEstadistica = IDEstadistica;
        this.jugador = jugador;
        this.partido = partido;
        this.goles = goles;
        this.asistencias = asistencias;
        this.tarjetasAmarillas = tarjetasAmarillas;
        this.tarjetasRojas = tarjetasRojas;
    }

    public int getTarjetasRojas() {
        return tarjetasRojas;
    }

    public void setTarjetasRojas(int tarjetasRojas) {
        this.tarjetasRojas = tarjetasRojas;
    }

    public int getIDEstadistica() {
        return IDEstadistica;
    }

    public void setIDEstadistica(int IDEstadistica) {
        this.IDEstadistica = IDEstadistica;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public int getTarjetasAmarillas() {
        return tarjetasAmarillas;
    }

    public void setTarjetasAmarillas(int tarjetasAmarillas) {
        this.tarjetasAmarillas = tarjetasAmarillas;
    }

    @Override
    public String toString() {
        return "Estadisticas{" + "IDEstadistica=" + IDEstadistica + ", jugador=" + jugador + ", partido=" + partido + ", goles=" + goles + ", asistencias=" + asistencias + ", tarjetasAmarillas=" + tarjetasAmarillas + ", tarjetasRojas=" + tarjetasRojas + '}';
    }
    
    
    
}
