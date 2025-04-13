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

    public void anadirJugador(String nombre, String apellidos, String dorsal, String posicion, String sexo) {
        ConexionBDR objetoConexion = new ConexionBDR();
        Connection conn = null;
        Statement sentencia = null;

        try {
            conn = objetoConexion.conectar();
            sentencia = conn.createStatement();
            // se incluyen comillas simples en el valor de dorsal ya que es un string.
            String sql = "INSERT INTO jugador (nombre, apellidos, dorsal, posicion, sexo) VALUES ('"
                    + nombre + "', '" + apellidos + "', '" + dorsal + "', '" + posicion + "', '" + sexo + "')";
            int filasAfectadas = sentencia.executeUpdate(sql);
            if (filasAfectadas > 0) {
                System.out.println("Jugador añadido correctamente.");
            } else {
                System.out.println("No se pudo añadir el jugador.");
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

    private TreeSet<Jugador> listadoJugadores = new TreeSet<>();

    public boolean añadir(Jugador jug) {
        return listadoJugadores.add(jug); // si se añadió devuelve true, si ya existía false
    }

    public Object[][] convertirAMatrizObject() {
        Object[][] matrizObj = new Object[listadoJugadores.size()][5];
        int id = 0;

        for (Jugador jug : this.listadoJugadores) {
            matrizObj[id][0] = jug.getNombre();
            matrizObj[id][1] = jug.getApellidos();
            matrizObj[id][2] = jug.getEquipo() != null ? jug.getEquipo().getNombre() : "Sin equipo";
            matrizObj[id][3] = jug.getDorsal();
            matrizObj[id][4] = jug.getPosicion().toString();
            id++;
        }
        return matrizObj;
    }

}
