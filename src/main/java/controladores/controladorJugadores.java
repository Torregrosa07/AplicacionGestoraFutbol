/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import Modelos.Jugador;
import Modelos.Equipo;
import java.util.TreeSet;

/**
 *
 * @author Santiago
 */
public class controladorJugadores {

    private TreeSet<Jugador> listadoJugadores = new TreeSet<>();
    
    
    public boolean añadir(Jugador jug) {
        return listadoJugadores.add(jug); // si se añadió devuelve true, si ya existía false
    }
    
    public boolean anadirJugador(String nombre, String apellidos, String equipoNombre, int dorsal, String posicion) {
    // Creamos un equipo temporal (deberías tener una lista de equipos disponibles)
    Equipo equipo = new Equipo(0, equipoNombre); // ID 0 temporal
    Jugador nuevo = new Jugador(nombre, apellidos, posicion, equipo, String.valueOf(dorsal));
    return añadir(nuevo);
}




    public Object[][] convertirAMatrizObject() {

        // creo matriz con tantas filas como jugadores hay en el treeSet y 
        // columnas 4, pues son 4 datos a almacenar.
        Object[][] matrizObj = new Object[listadoJugadores.size()][5];

        int id = 0;

        for (Jugador jug : this.listadoJugadores) {

            matrizObj[id][0] = jug.getNombre();
            matrizObj[id][1] = jug.getApellidos();
            matrizObj[id][2] = jug.getEdad() + ""; // como cadena
            matrizObj[id][3] = jug.getPosicion() + ""; // como cadena


            Equipo equipo = jug.getEquipo();
            if (equipo != null) {
                matrizObj[id][4] = equipo.getNombre();
            } else {
                matrizObj[id][4] = "Sin equipo";
            }

            id++;

        }

        return matrizObj;

    }

}
