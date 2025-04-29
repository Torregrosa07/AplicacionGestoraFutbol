/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

            String sql = "INSERT INTO equipo (nombre, anio_fundacion, localidad, entrenador) VALUES ("
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

    public boolean existeEquipo(String nombre, int añoFundacion) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar();
            st = con.createStatement();

            String sql = "SELECT COUNT(*) FROM equipo WHERE nombre = '"
                    + nombre.replace("'", "''") + "' AND anio_fundacion = " + añoFundacion;

            rs = st.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt(1) > 0;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
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

    public boolean modificarEquipo(String nombreOriginal, String nuevoNombre, int nuevoAño, String nuevaLocalidad, String nuevoEntrenador) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;

        try {
            con = conexionBDR.conectar();
            st = con.createStatement();

            String sql = "UPDATE equipo SET "
                    + "nombre = '" + nuevoNombre.replace("'", "''") + "', "
                    + "anio_fundacion = " + nuevoAño + ", "
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
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar();
            con.setAutoCommit(false); 
            st = con.createStatement();

            // Primero obtenemos el ID del equipo
            String sqlBuscarId = "SELECT id_equipo FROM equipo WHERE nombre = '" + nombre.replace("'", "''") + "'";
            rs = st.executeQuery(sqlBuscarId);

            if (!rs.next()) {
                return false; 
            }

            int idEquipo = rs.getInt("id_equipo");

            // 1. Actualizar los jugadores del equipo para quitar la referencia
            String sqlActualizarJugadores = "UPDATE jugador SET id_equipo = NULL WHERE id_equipo = " + idEquipo;
            st.executeUpdate(sqlActualizarJugadores);

            // 2. Eliminar partidos relacionados con el equipo o actualizar referencias
            // Opción A: Eliminar los partidos
            String sqlEliminarPartidos = "DELETE FROM partido WHERE id_equipo_local = " + idEquipo
                    + " OR id_equipo_visitante = " + idEquipo;
            st.executeUpdate(sqlEliminarPartidos);

            // 3. Finalmente, eliminar el equipo
            String sqlEliminarEquipo = "DELETE FROM equipo WHERE id_equipo = " + idEquipo;
            int filasAfectadas = st.executeUpdate(sqlEliminarEquipo);

            con.commit(); // Confirmar la transacción
            return filasAfectadas > 0;

        } catch (SQLException | ClassNotFoundException e) {
            try {
                if (con != null) {
                    con.rollback(); // Si hay error, revertir todos los cambios
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.setAutoCommit(true);
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

            String sql = "SELECT id_equipo, nombre, anio_fundacion, localidad, entrenador FROM equipo WHERE nombre = '" + nombre.replace("'", "''") + "'";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                int idEquipo = rs.getInt("id_equipo"); // Obtener el ID
                String nombreEquipo = rs.getString("nombre");
                int anioFundacion = rs.getInt("anio_fundacion");
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

            rs = st.executeQuery("SELECT nombre, anio_fundacion, localidad, entrenador FROM equipo");

            int id = 0;
            while (rs.next()) {
                matrizObj[id][0] = rs.getString("nombre");
                matrizObj[id][1] = rs.getInt("anio_fundacion");
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
