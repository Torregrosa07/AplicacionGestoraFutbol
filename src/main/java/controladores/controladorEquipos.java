/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago
 */
public class controladorEquipos {

    public boolean guardarEnBD(Equipo equipo) {
    ConexionBDR conexionBDR = new ConexionBDR();
    Connection con = null;
    Statement st = null;

    try {
        con = conexionBDR.conectar();
        st = con.createStatement();

        String sql = "INSERT INTO equipo (nombre, año_fundacion, localidad, entrenador) VALUES (" +
                "'" + equipo.getNombre().replace("'", "''") + "', " +
                equipo.getAñoFundacion() + ", " +
                "'" + equipo.getLocalidad().replace("'", "''") + "', " +
                "'" + equipo.getEntrenador().replace("'", "''") + "');";

        int resultado = st.executeUpdate(sql);
        return resultado > 0;

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        return false;
    } finally {
        try {
            if (st != null) st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conexionBDR.desconectar(); 
    }
}


   

    public Equipo buscarEquipoPorNombre(String nombre) {
//        for (Equipo e : listadoEquipos) {
//            if (e.getNombre().equalsIgnoreCase(nombre)) {
//                return e;
//            }
//        }
        return null; // no encontrado
    }

//    public TreeSet<Equipo> getListadoEquipos() {
//        return listadoEquipos;
//    }

//    public Equipo getEquipoPorNombre(String nombre) {
//        for (Equipo equipo : listadoEquipos) {
//            if (equipo.getNombre().equalsIgnoreCase(nombre)) {
//                return equipo;
//            }
//        }
//        return null;
//    }

    public Object[][] convertirAMatrizObject() {
    ConexionBDR conexionBDR = new ConexionBDR();
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    try {
        con = conexionBDR.conectar();
        st = con.createStatement();

        rs = st.executeQuery("SELECT COUNT(*) FROM equipo");
        rs.next();
        int cantidad = rs.getInt(1);

        Object[][] matrizObj = new Object[cantidad][4];

        rs = st.executeQuery("SELECT nombre, año_fundacion, localidad, entrenador FROM equipo");

        int id = 0;
        while (rs.next()) {
            matrizObj[id][0] = rs.getString("nombre");
            matrizObj[id][1] = rs.getInt("año_fundacion");
            matrizObj[id][2] = rs.getString("localidad");
            matrizObj[id][3] = rs.getString("entrenador");
            id++;
        }

        return matrizObj;

    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        return new Object[0][0];
    } finally {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conexionBDR.desconectar(); // ✅ cerramos solo la conexión
    }
}


}
