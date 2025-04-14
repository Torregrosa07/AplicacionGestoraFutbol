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

            String sql = "INSERT INTO equipo (nombre, año_fundacion, localidad, entrenador) VALUES ("
                    + "'" + equipo.getNombre().replace("'", "''") + "', "
                    + equipo.getAñoFundacion() + ", "
                    + "'" + equipo.getLocalidad().replace("'", "''") + "', "
                    + "'" + equipo.getEntrenador().replace("'", "''") + "');";

            int resultado = st.executeUpdate(sql);
            return resultado > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar();
        }
    }

    public boolean modificarEquipo(String nombreOriginal, String nuevoNombre, int nuevoAño, String nuevaLocalidad, String nuevoEntrenador) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;

        try {
            con = conexionBDR.conectar();
            st = con.createStatement();

            String sql = "UPDATE equipo SET "
                    + "nombre = '" + nuevoNombre.replace("'", "''") + "', "
                    + "año_fundacion = " + nuevoAño + ", "
                    + "localidad = '" + nuevaLocalidad.replace("'", "''") + "', "
                    + "entrenador = '" + nuevoEntrenador.replace("'", "''") + "' "
                    + "WHERE nombre = '" + nombreOriginal.replace("'", "''") + "'";

            int filas = st.executeUpdate(sql);
            return filas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar();
        }
    }

    public boolean eliminarEquipo(String nombre) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;

        try {
            con = conexionBDR.conectar();
            st = con.createStatement();

            String sql = "DELETE FROM equipo WHERE nombre = '" + nombre.replace("'", "''") + "'";

            int filasAfectadas = st.executeUpdate(sql);
            return filasAfectadas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar();
        }
    }

    public Equipo buscarEquipoPorNombre(String nombre) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar();
            st = con.createStatement();

            String sql = "SELECT id_equipo, nombre, año_fundacion, localidad, entrenador FROM equipo WHERE nombre = '" + nombre.replace("'", "''") + "'";
        rs = st.executeQuery(sql);

        if (rs.next()) {
            int idEquipo = rs.getInt("id_equipo"); // Obtener el ID
            String nombreEquipo = rs.getString("nombre");
            int anioFundacion = rs.getInt("año_fundacion");
            String localidad = rs.getString("localidad");
            String entrenador = rs.getString("entrenador");

            return new Equipo(idEquipo, nombreEquipo, anioFundacion, localidad, entrenador);
            } else {
                return null; 
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar(); 
        }
    }

    

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
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar();
        }
    }

}
