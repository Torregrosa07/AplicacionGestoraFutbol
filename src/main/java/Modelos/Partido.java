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
public class Partido implements Comparable<Partido> {

    private int idPartido;
    private Date fechaPartido;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private int golesLocal;
    private int golesVisitante;

    public Partido(int idPartido, Date fechaPartido, Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante) {
        this.idPartido = idPartido;
        this.fechaPartido = fechaPartido;
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public Date getFechaPartido() {
        return fechaPartido;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public void setFechaPartido(Date fechaPartido) {
        this.fechaPartido = fechaPartido;
    }

    //Metodos 
    @Override
    public String toString() {
        return "Partido{" + "idPartido=" + idPartido + ", fechaPartido=" + fechaPartido + ", equipoLocal=" + equipoLocal + ", equipoVisitante=" + equipoVisitante + ", golesLocal=" + golesLocal + ", golesVisitante=" + golesVisitante + '}';
    }

    /*Este método devuelve true si el partido ha finalizado,
        lo cual se determina porque los goles de ambos equipos son números no negativos.
        Es decir, si los goles son mayores o iguales a cero, se considera que el partido ha finalizado.*/
    public boolean haFinalizado() {
        return golesLocal >= 0 && golesVisitante >= 0;
    }

    public void registrarResultado(int golesLocal, int golesVisitante) {
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
    }

    @Override
    public int compareTo(Partido o) {
       return Integer.compare(this.idPartido, o.idPartido);
    }

}
