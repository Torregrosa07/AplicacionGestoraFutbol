/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Jugador;
import Modelos.Equipo;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class controladorJugadores {

    /**
     * MÉTODO PARA AÑADIR A LOS JUGADORES A LA BD
     *
     * @param nombre
     * @param apellidos
     * @param dorsal
     * @param posicion
     * @param sexo
     * @param edad
     * @param idEquipo
     */
    public void anadirJugador(String nombre, String apellidos, String dorsal,
            String posicion, String sexo, int edad, Integer idEquipo) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection conn = null;
        Statement sentencia = null;

        try {
            conn = objetoConexion.conectar();
            sentencia = conn.createStatement();

            String sql;
            if (idEquipo != null) {
                sql = "INSERT INTO jugador (nombre, apellidos, dorsal, posicion, sexo, edad, id_equipo) "
                        + "VALUES ('" + nombre + "', '" + apellidos + "', '" + dorsal + "', '"
                        + posicion + "', '" + sexo + "', " + edad + ", " + idEquipo + ")";
            } else {
                sql = "INSERT INTO jugador (nombre, apellidos, dorsal, posicion, sexo, edad) "
                        + "VALUES ('" + nombre + "', '" + apellidos + "', '" + dorsal + "', '"
                        + posicion + "', '" + sexo + "', " + edad + ")";
            }

            sentencia.executeUpdate(sql);

        } catch (SQLIntegrityConstraintViolationException e) {
            // Error de duplicado (si existe la restricción UNIQUE)
            JOptionPane.showMessageDialog(null, "Error: El jugador ya existe en la base de datos.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
                objetoConexion.desconectar();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * MÉTODO PARA ELIMINAR JUGADORES DE LA BD
     *
     * @param idJugador
     */
    public void eliminarJugador(int idJugador) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection conn = null;
        Statement sentencia = null;

        try {
            conn = objetoConexion.conectar();
            sentencia = conn.createStatement();

            String sql = "DELETE FROM jugador WHERE id_jugador = " + idJugador;
            int filasAfectadas = sentencia.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("Jugador eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún jugador con el ID: " + idJugador);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            objetoConexion.desconectar();
        }
    }

    /**
     * MÉTODO PARA MODIFICAR A LOS JUGADORES DE LA BD
     *
     * @param idJugador
     * @param nombre
     * @param apellidos
     * @param dorsal
     * @param posicion
     * @param sexo
     * @param edad
     * @param idEquipo
     */
    public void modificarJugador(int idJugador, String nombre, String apellidos, String dorsal,
            String posicion, String sexo, int edad, Integer idEquipo) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection conn = null;
        Statement sentencia = null;

        try {
            conn = objetoConexion.conectar();
            sentencia = conn.createStatement();

            String sql;
            if (idEquipo != null) {
                sql = "UPDATE jugador SET "
                        + "nombre = '" + nombre + "', "
                        + "apellidos = '" + apellidos + "', "
                        + "dorsal = '" + dorsal + "', "
                        + "posicion = '" + posicion + "', "
                        + "sexo = '" + sexo + "', "
                        + "edad = " + edad + ", "
                        + "id_equipo = " + idEquipo + " "
                        + "WHERE id_jugador = " + idJugador;
            } else {
                sql = "UPDATE jugador SET "
                        + "nombre = '" + nombre + "', "
                        + "apellidos = '" + apellidos + "', "
                        + "dorsal = '" + dorsal + "', "
                        + "posicion = '" + posicion + "', "
                        + "sexo = '" + sexo + "', "
                        + "edad = " + edad + ", "
                        + "id_equipo = NULL "
                        + "WHERE id_jugador = " + idJugador;
            }

            int filasAfectadas = sentencia.executeUpdate(sql);
            if (filasAfectadas > 0) {
                System.out.println("Jugador modificado correctamente.");
            } else {
                System.out.println("No se encontró ningún jugador con el ID: " + idJugador);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            objetoConexion.desconectar();
        }
    }

    /**
     * MÉTODO PARA BUSCAR A JUGADOR POR NOMBRE Y APELLIDOS
     *
     * @param nombre
     * @param apellidos
     * @return
     */
    public Jugador buscarJugadorPorNombreApellidos(String nombre, String apellidos) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        Jugador jugadorEncontrado = null;

        try {
            conn = objetoConexion.conectar();
            sentencia = conn.createStatement();

            String sql = "SELECT j.*, e.nombre as nombre_equipo, e.anio_fundacion, e.localidad, e.entrenador "
                    + "FROM jugador j LEFT JOIN equipo e ON j.id_equipo = e.id_equipo "
                    + "WHERE LOWER(j.nombre) = LOWER('" + nombre + "') "
                    + "AND LOWER(j.apellidos) = LOWER('" + apellidos + "')";
            rs = sentencia.executeQuery(sql);

            if (rs.next()) {
                jugadorEncontrado = new Jugador();
                jugadorEncontrado.setIDjugador(rs.getInt("id_jugador"));
                jugadorEncontrado.setNombre(rs.getString("nombre"));
                jugadorEncontrado.setApellidos(rs.getString("apellidos"));

                // Convertir String a Enum para posición
                String posicionStr = rs.getString("posicion");
                if (posicionStr != null) {
                    jugadorEncontrado.setPosicion(Jugador.Posicion.valueOf(posicionStr));
                }

                jugadorEncontrado.setDorsal(rs.getString("dorsal"));
                jugadorEncontrado.setEdad(rs.getInt("edad"));
                jugadorEncontrado.setNacionalidad(rs.getString("nacionalidad"));

                // Convertir String a Enum para sexo
                String sexoStr = rs.getString("sexo");
                if (sexoStr != null) {
                    jugadorEncontrado.setSexo(Jugador.Sexo.valueOf(sexoStr));
                }

                // Manejar equipo si existe
                Integer idEquipo = rs.getInt("id_equipo");
                if (!rs.wasNull()) {
                    Equipo equipo = new Equipo();
                    equipo.setIDEquipo(idEquipo);
                    equipo.setNombre(rs.getString("nombre_equipo"));
                    equipo.setAñoFundacion(rs.getInt("anio_fundacion"));
                    equipo.setLocalidad(rs.getString("localidad"));
                    equipo.setEntrenador(rs.getString("entrenador"));
                    jugadorEncontrado.setEquipo(equipo);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            objetoConexion.desconectar();
        }

        return jugadorEncontrado;
    }

    /**
     * MÉTODO PARA MOSTRAR A LOS EQUIPOS EN EL COMBO BOX, MEDIANTE SENTENCIA SQL
     *
     * @param jComboEquipo
     */
    public void mostrarEquiposCombo(JComboBox jComboEquipo) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            conn = objetoConexion.conectar();
            sentencia = conn.createStatement();
            // Consulta para obtener el nombre de todos los equipos.

            String sql = "SELECT nombre FROM equipo";
            rs = sentencia.executeQuery(sql);
            // Limpiar el JComboBox antes de agregar nuevos elementos.
            jComboEquipo.removeAllItems();
            jComboEquipo.addItem("Sin equipo");

            while (rs.next()) {
                String nombreEquipo = rs.getString("nombre");
                jComboEquipo.addItem(nombreEquipo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            objetoConexion.desconectar();
        }
    }

    /**
     * MÉTODO PARA MOSTRAR SEXO EN EL COMBO BOX MEDIANTE SENTENCIA SQL
     *
     * @param jComboSexo
     */
    public void MostrarSexoCombo(JComboBox jComboSexo) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            con = objetoConexion.conectar();
            sentencia = con.createStatement();
            String sql = "SHOW COLUMNS FROM jugador LIKE 'sexo'";
            rs = sentencia.executeQuery(sql);
            if (rs.next()) {
                String type = rs.getString("Type");
                String enumValues = type.substring(type.indexOf("(") + 1, type.indexOf(")"));
                String[] valores = enumValues.split(",");
                jComboSexo.removeAllItems();
                for (String valor : valores) {
                    String valorLimpio = valor.replace("'", "").trim();
                    jComboSexo.addItem(valorLimpio);
                }
            } else {
                System.out.println("No se encontró la columna 'sexo'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // Importante: cerrar la conexión
            objetoConexion.desconectar();
        }
    }

    /**
     * MÉTODO PARA MOSTRAR POSICIONES DE JUGADORES, MEDIANTE SENTENCIA SQL
     *
     * @param jComboPosicion
     */
    public void MostrarPosicionCombo(JComboBox jComboPosicion) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection con = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            con = objetoConexion.conectar();
            sentencia = con.createStatement();
            String sql = "SHOW COLUMNS FROM jugador LIKE 'posicion'";
            rs = sentencia.executeQuery(sql);
            if (rs.next()) {
                String type = rs.getString("Type");
                String enumValues = type.substring(type.indexOf("(") + 1, type.indexOf(")"));
                String[] valores = enumValues.split(",");
                jComboPosicion.removeAllItems();
                for (String valor : valores) {
                    String valorLimpio = valor.replace("'", "").trim();
                    jComboPosicion.addItem(valorLimpio);
                }
            } else {
                System.out.println("No se encontró la columna 'posicion'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            // Importante: cerrar la conexión
            objetoConexion.desconectar();
        }
    }

    /**
     * MÉTODO PARA CONVERTIR A MATRIZ DE OBJETOS
     *
     * @return
     */
    public Object[][] convertirAMatrizObject() {
        ConexionBDR conexionBDR = new ConexionBDR();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            con = conexionBDR.conectar();
            st = con.createStatement();

            rs = st.executeQuery("SELECT COUNT(*) FROM jugador");
            rs.next();
            int cantidad = rs.getInt(1);

            Object[][] matrizObj = new Object[cantidad][8]; // Ajusta el tamaño según las columnas

            rs = st.executeQuery("SELECT j.id_jugador, j.nombre, j.apellidos, j.posicion, j.dorsal, e.nombre as equipo, j.edad, j.sexo "
                    + "FROM jugador j LEFT JOIN equipo e ON j.id_equipo = e.id_equipo");

            int id = 0;
            while (rs.next()) {
                matrizObj[id][0] = rs.getString("id_jugador");
                matrizObj[id][1] = rs.getString("nombre");
                matrizObj[id][2] = rs.getString("apellidos");
                matrizObj[id][3] = rs.getString("posicion");
                matrizObj[id][4] = rs.getString("dorsal");
                matrizObj[id][5] = rs.getString("equipo");
                matrizObj[id][6] = rs.getString("edad");
                matrizObj[id][7] = rs.getString("sexo");
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
