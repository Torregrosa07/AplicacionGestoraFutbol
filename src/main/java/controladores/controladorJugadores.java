/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Jugador;
import Modelos.Equipo;
import Prototipos_Ventanas.GestionJugadores;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Santiago
 */
public class controladorJugadores {

    public void anadirJugador(String nombre, String apellidos, String dorsal, String posicion, String sexo, Integer idEquipo) {
    ConexionBDR objetoConexion = new ConexionBDR();
    Connection conn = null;
    Statement sentencia = null;

    try {
        conn = objetoConexion.conectar(); // <-- Asegurar conexión
        sentencia = conn.createStatement(); // <-- Inicializar aquí

        String sql;
        if (idEquipo != null) {
            sql = "INSERT INTO jugador (nombre, apellidos, dorsal, posicion, sexo, id_equipo) VALUES ('"
                    + nombre + "', '" + apellidos + "', '" + dorsal + "', '" + posicion + "', '" + sexo + "', " + idEquipo + ")";
        } else {
            sql = "INSERT INTO jugador (nombre, apellidos, dorsal, posicion, sexo) VALUES ('"
                    + nombre + "', '" + apellidos + "', '" + dorsal + "', '" + posicion + "', '" + sexo + "')";
        }

        int filasAfectadas = sentencia.executeUpdate(sql); // Ahora sentencia no es null
        if (filasAfectadas > 0) {
            System.out.println("Jugador añadido correctamente.");
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

    /*private int obtenerIdEquipo(Connection conn, String nombreEquipo) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String nombreEscapado = nombreEquipo.replace("'", "''");
            String sql = "SELECT id_equipo FROM equipo WHERE nombre = '" + nombreEscapado + "'";

            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("id_equipo");
            }
            return 0;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }*/
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

//    private TreeSet<Jugador> listadoJugadores = new TreeSet<>();
//
//    public boolean añadir(Jugador jug) {
//        return listadoJugadores.add(jug); // si se añadió devuelve true, si ya existía false
//    }
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
