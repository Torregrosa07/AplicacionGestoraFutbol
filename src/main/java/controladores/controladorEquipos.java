/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Santiago
 */
public class controladorEquipos {

    /**
     * Guarda el equipo en la BDR
     * @param equipo
     * @return 
     */
     public boolean guardarEnBD(Equipo equipo) {
        ConexionBDR conexionBDR = new ConexionBDR(); // Conexión a la base de datos
        Connection con = null;
        Statement st = null;

        try {
            con = conexionBDR.conectar(); // Se conecta a la base de datos
            st = con.createStatement();

            // Construye la consulta SQL para insertar un nuevo equipo
            String sql = "INSERT INTO equipo (nombre, anio_fundacion, localidad, entrenador) VALUES ("
                    + "'" + equipo.getNombre().replace("'", "''") + "', "
                    + equipo.getAñoFundacion() + ", "
                    + "'" + equipo.getLocalidad().replace("'", "''") + "', "
                    + "'" + equipo.getEntrenador().replace("'", "''") + "');";

            // Ejecuta la consulta y obtiene el resultado
            int resultado = st.executeUpdate(sql);
            return resultado > 0; // Retorna true si se insertó correctamente

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Imprime el error en caso de excepción
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close(); // Cierra la declaración
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar(); // Desconecta de la base de datos
        }
    }

    /**
     * /Verifica si un equipo con un nombre y año de fundación ya existe
     * @param nombre
     * @param añoFundacion
     * @return 
     */
    public boolean existeEquipo(String nombre, int añoFundacion) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar(); // Conectar a la base de datos
            st = con.createStatement();

            // Consulta SQL para verificar si el equipo ya existe
            String sql = "SELECT COUNT(*) FROM equipo WHERE nombre = '"
                    + nombre.replace("'", "''") + "' AND anio_fundacion = " + añoFundacion;

            rs = st.executeQuery(sql); // Ejecuta la consulta

            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el contador es mayor que 0, el equipo existe
            } else {
                return false; // Si no existe el equipo
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Imprime el error en caso de excepción
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cierra el ResultSet
                }
                if (st != null) {
                    st.close(); // Cierra la declaración
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar(); // Desconecta de la base de datos
        }
    }

    /**
     * mMdifica los datos de un equipo existente
     * @param nombreOriginal
     * @param nuevoNombre
     * @param nuevoAño
     * @param nuevaLocalidad
     * @param nuevoEntrenador
     * @return 
     */
    public boolean modificarEquipo(String nombreOriginal, String nuevoNombre, int nuevoAño, String nuevaLocalidad, String nuevoEntrenador) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;

        try {
            con = conexionBDR.conectar(); 
            st = con.createStatement();

            // Consulta para actualizar los datos del equipo
            String sql = "UPDATE equipo SET "
                    + "nombre = '" + nuevoNombre.replace("'", "''") + "', "
                    + "anio_fundacion = " + nuevoAño + ", "
                    + "localidad = '" + nuevaLocalidad.replace("'", "''") + "', "
                    + "entrenador = '" + nuevoEntrenador.replace("'", "''") + "' "
                    + "WHERE nombre = '" + nombreOriginal.replace("'", "''") + "'";

            // Ejecuta la consulta y obtiene el número de filas afectadas
            int filas = st.executeUpdate(sql);
            return filas > 0; // Retorna true si la actualización fue exitosa

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Imprime el error en caso de excepción
            return false;
        } finally {
            try {
                if (st != null) {
                    st.close(); // Cierra la declaración
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar(); // Desconecta de la base de datos
        }
    }

    /**
     * Método para eliminar un equipo y sus referencias (jugadores y partidos)
     * @param nombre
     * @return 
     */
    public boolean eliminarEquipo(String nombre) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar(); 
            con.setAutoCommit(false); 
            st = con.createStatement();

            // Consulta SQL para obtener el ID del equipo
            String sqlBuscarId = "SELECT id_equipo FROM equipo WHERE nombre = '" + nombre.replace("'", "''") + "'";
            rs = st.executeQuery(sqlBuscarId);

            if (!rs.next()) {
                return false; // Si no se encuentra el equipo, no se puede eliminar
            }

            int idEquipo = rs.getInt("id_equipo"); // Obtener el ID del equipo

            //Actualiza los jugadores para quitar la referencia al equipo
            String sqlActualizarJugadores = "UPDATE jugador SET id_equipo = NULL WHERE id_equipo = " + idEquipo;
            st.executeUpdate(sqlActualizarJugadores);

            //Elimina los partidos relacionados con el equipo
            String sqlEliminarPartidos = "DELETE FROM partido WHERE id_equipo_local = " + idEquipo
                    + " OR id_equipo_visitante = " + idEquipo;
            st.executeUpdate(sqlEliminarPartidos);

            //Elimina el equipo
            String sqlEliminarEquipo = "DELETE FROM equipo WHERE id_equipo = " + idEquipo;
            int filasAfectadas = st.executeUpdate(sqlEliminarEquipo);

            con.commit(); // Confirma la transacción
            return filasAfectadas > 0; 

        } catch (SQLException | ClassNotFoundException e) {
            try {
                if (con != null) {
                    con.rollback(); // Si hay error, revierte la transacción
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace(); // Imprime el error en caso de excepción
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cierra el ResultSet
                }
                if (st != null) {
                    st.close(); // Cierra la declaración
                }
                if (con != null) {
                    con.setAutoCommit(true); // Vuelve a activar el autocommit
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar(); // Desconecta de la base de datos
        }
    }

    /**
     * Busca un equipo por su nombre, muy descriptivo jaja saludos
     * @param nombre
     * @return 
     */
    public Equipo buscarEquipoPorNombre(String nombre) {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar(); // Conectar a la base de datos
            st = con.createStatement();

            // Consulta SQL para obtener los datos del equipo por nombre
            String sql = "SELECT id_equipo, nombre, anio_fundacion, localidad, entrenador FROM equipo WHERE nombre = '" + nombre.replace("'", "''") + "'";
            rs = st.executeQuery(sql);

            if (rs.next()) {
                // Si el equipo existe, crea el objeto Equipo
                int idEquipo = rs.getInt("id_equipo");
                String nombreEquipo = rs.getString("nombre");
                int anioFundacion = rs.getInt("anio_fundacion");
                String localidad = rs.getString("localidad");
                String entrenador = rs.getString("entrenador");

                return new Equipo(idEquipo, nombreEquipo, anioFundacion, localidad, entrenador);
            } else {
                return null; // Si no se encuentra el equipo, retorna null
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Imprime el error en caso de excepción
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cierra el ResultSet
                }
                if (st != null) {
                    st.close(); // Cierra la declaración
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar(); // Desconecta de la base de datos
        }
    }

    /**
     * convierte los datos de los equipos en una matriz de objetos
     * @return 
     */
    public Object[][] convertirAMatrizObject() {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar(); // Conectar a la base de datos
            st = con.createStatement();

            // Obtener la cantidad de equipos en la base de datos
            rs = st.executeQuery("SELECT COUNT(*) FROM equipo");
            rs.next();
            int cantidad = rs.getInt(1);

            // Crear la matriz de objetos con las dimensiones adecuadas
            Object[][] matrizObj = new Object[cantidad][4];

            // Consulta SQL para obtener los detalles de todos los equipos
            rs = st.executeQuery("SELECT nombre, anio_fundacion, localidad, entrenador FROM equipo");

            int id = 0;
            while (rs.next()) {
                // Asigna los valores de la consulta a la matriz
                matrizObj[id][0] = rs.getString("nombre");
                matrizObj[id][1] = rs.getInt("anio_fundacion");
                matrizObj[id][2] = rs.getString("localidad");
                matrizObj[id][3] = rs.getString("entrenador");
                id++;
            }

            return matrizObj; // Retorna la matriz con los datos

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace(); // Imprime el error en caso de excepción
            return new Object[0][0]; // Retorna una matriz vacía si hay error
        } finally {
            try {
                if (rs != null) {
                    rs.close(); // Cierra el ResultSet
                }
                if (st != null) {
                    st.close(); // Cierra la declaración
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar(); // Desconecta de la base de datos
        }
    }

}
