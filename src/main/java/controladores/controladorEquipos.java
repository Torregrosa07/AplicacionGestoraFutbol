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
    
    public boolean anadirEquipo(String nombre, Date añoFundacion, String localidad, String entrenador ) {
    
   
    Equipo nuevo = new Equipo(nombre, añoFundacion, localidad, entrenador);
    return añadir(nuevo);
}




    public Object[][] convertirAMatrizObject() {

       
        Object[][] matrizObj = new Object[listadoEquipos.size()][5];

        int id = 0;

        for (Equipo equi : this.listadoEquipos) {

            matrizObj[id][0] = equi.getIDEquipo();
            matrizObj[id][1] = equi.getNombre();
            matrizObj[id][2] = equi.getAñoFundacion()+ ""; 
            matrizObj[id][3] = equi.getLocalidad()+ ""; 
            matrizObj[id][3] = equi.getEntrenador()+ ""; 


//            Equipo equipo = equi.getEquipo();
//            if (equipo != null) {
//                matrizObj[id][4] = equipo.getNombre();
//            } else {
//                matrizObj[id][4] = "Sin equipo";
//            }

            id++;

        }

        return matrizObj;

    }
}
