/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;

import Modelos.Partido;
import Prototipos_Ventanas.GestionPartidos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class controladorPartido {

    private TreeSet<Partido> listadoPartidos = new TreeSet<>();
    private int contadorID = 1;

    public boolean añadir(Partido par) {
        return listadoPartidos.add(par);
    }

    public boolean anadirPartido(Date fechaPartido, int idEquipoLocal, String nombreEquipoLocal,
            int idEquipoVisitante, String nombreEquipoVisitante,
            int golesLocal, int golesVisitante) {
        Equipo equipoLocal = new Equipo(idEquipoLocal, nombreEquipoLocal);
        Equipo equipoVisitante = new Equipo(idEquipoVisitante, nombreEquipoVisitante);

        Partido nuevo = new Partido(contadorID++, fechaPartido, equipoLocal, equipoVisitante, golesLocal, golesVisitante);
        return añadir(nuevo);
    }

    public TreeSet<Partido> getListadoPartidos() {
        return listadoPartidos;
    }

}
