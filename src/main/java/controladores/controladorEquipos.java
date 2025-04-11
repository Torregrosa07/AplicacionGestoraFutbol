/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import Modelos.Equipo;
import java.util.Date;
import java.util.TreeSet;

/**
 *
 * @author Santiago
 */
public class controladorEquipos {

    private TreeSet<Equipo> listadoEquipos = new TreeSet<>();

    public boolean añadir(Equipo equi) {
        return listadoEquipos.add(equi); // si se añadió devuelve true, si ya existía false
    }

    public boolean anadirEquipo(String nombre, int añoFundacion, String localidad, String entrenador) {

        Equipo nuevo = new Equipo(nombre, añoFundacion, localidad, entrenador);
        return añadir(nuevo);
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        for (Equipo e : listadoEquipos) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                return e;
            }
        }
        return null; // no encontrado
    }

    public TreeSet<Equipo> getListadoEquipos() {
        return listadoEquipos;
    }

    public Equipo getEquipoPorNombre(String nombre) {
        for (Equipo equipo : listadoEquipos) {
            if (equipo.getNombre().equalsIgnoreCase(nombre)) {
                return equipo;
            }
        }
        return null;
    }

    public Object[][] convertirAMatrizObject() {
        Object[][] matrizObj = new Object[listadoEquipos.size()][4];

        int id = 0;

        for (Equipo equi : this.listadoEquipos) {
            matrizObj[id][0] = equi.getNombre();
            matrizObj[id][1] = equi.getAñoFundacion();
            matrizObj[id][2] = equi.getLocalidad();
            matrizObj[id][3] = equi.getEntrenador();
            id++;
        }

        return matrizObj;
    }

}
