/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;

import Modelos.Partido;
import Prototipos_Ventanas.GestionPartidos;
import com.toedter.calendar.JDateChooser;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Keiny
 */
public class controladorPartido {

    private TreeSet<Partido> listadoPartidos = new TreeSet<>();
    private ConexionBDR conexion;
    private int contadorID;

    public controladorPartido() {
        conexion = new ConexionBDR();
        contadorID = obtenerUltimoID() + 1;
    }

    private int obtenerUltimoID() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT MAX(id_partido) AS max_id FROM partido");
            if (rs.next()) {
                return rs.getInt("max_id");
            }
            return 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }
    }

    public boolean añadir(Partido par) {
        return listadoPartidos.add(par);
    }

    public TreeSet<Partido> getListadoPartidos() {
        return listadoPartidos;
    }

    public boolean anadirPartido(Date fechaPartido, int idEquipoLocal, String nombreEquipoLocal,
            int idEquipoVisitante, String nombreEquipoVisitante,
            int golesLocal, int golesVisitante) {
        Equipo equipoLocal = new Equipo(idEquipoLocal, nombreEquipoLocal);
        Equipo equipoVisitante = new Equipo(idEquipoVisitante, nombreEquipoVisitante);

        Partido nuevo = new Partido(contadorID++, fechaPartido, equipoLocal, equipoVisitante, golesLocal, golesVisitante);
        return añadir(nuevo);
    }

    public boolean validarFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean validarHora(String hora) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setLenient(false);
        try {
            sdf.parse(hora);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public int obtenerIdEquipo(String nombreEquipo) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar();
            stmt = conn.createStatement();
            String nombreEscapado = nombreEquipo.replace("'", "''");
            String sql = "SELECT id_equipo FROM equipo WHERE nombre = '" + nombreEscapado + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return rs.getInt("id_equipo");
            }
            return 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }
    }

    public void cargarEquiposEnCombos(JComboBox<String> comboLocal, JComboBox<String> comboVisitante) {
        comboLocal.removeAllItems();
        comboVisitante.removeAllItems();

        comboLocal.addItem("Seleccione un equipo");
        comboVisitante.addItem("Seleccione un equipo");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT nombre FROM equipo");

            while (rs.next()) {
                String nombreEquipo = rs.getString("nombre");
                comboLocal.addItem(nombreEquipo);
                comboVisitante.addItem(nombreEquipo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar equipos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            cerrarRecursos(rs, stmt, conn);
        }
    }

    public void limpiarCampos(JDateChooser dateChooser, TextField hora2, JComboBox<String> comboLocal, JComboBox<String> comboVisitante) { //limpiar campos, para que luego de una inserción queden los campos vacíos ylistos para recibir un nuevo partido
        dateChooser.setDate(null);
        hora2.setText("");
        comboLocal.setSelectedIndex(0);
        comboVisitante.setSelectedIndex(0);
    }

    public boolean guardarPartido(JDateChooser dateChooserFecha, TextField hora2,
            JComboBox<String> comboEquipoLocal, JComboBox<String> comboEquipoVisitante,
            DefaultTableModel modeloPartidos) {

        Date fecha = dateChooserFecha.getDate();
        if (fecha == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String fechaSeleccionada = sdf.format(fecha);

        if (comboEquipoLocal.getSelectedIndex() == 0 || comboEquipoVisitante.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar ambos equipos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String equipoLocal = comboEquipoLocal.getSelectedItem().toString();
        String equipoVisitante = comboEquipoVisitante.getSelectedItem().toString();

        if (equipoLocal.equals(equipoVisitante)) {
            JOptionPane.showMessageDialog(null, "No puedes seleccionar el mismo equipo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String horaStr = hora2.getText().trim();
        if (!validarHora(horaStr)) {
            JOptionPane.showMessageDialog(null, "Hora inválida. Use formato HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Connection conn = null;
        Statement stmt = null;
        try {
            conn = conexion.conectar();
            int idLocal = obtenerIdEquipo(equipoLocal);
            int idVisitante = obtenerIdEquipo(equipoVisitante);

            if (idLocal == 0 || idVisitante == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron los equipos en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            stmt = conn.createStatement();
            String sqlInsert = "INSERT INTO partido (fecha, hora, id_equipo_local, id_equipo_visitante) "
                    + "VALUES ('" + fechaSeleccionada + "', '" + horaStr + "', " + idLocal + ", " + idVisitante + ")";
            stmt.executeUpdate(sqlInsert);

            modeloPartidos.addRow(new Object[]{
                contadorID++,
                fechaSeleccionada,
                horaStr,
                equipoLocal,
                equipoVisitante
            });

            return true;

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } finally {
            cerrarRecursos(null, stmt, conn);
        }
    }

    private void cerrarRecursos(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conexion.desconectar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 public DefaultTableModel cargarPartidos() { //metodoo par que se muestren los partidos establecidos en la ventana de consultasd
    DefaultTableModel modelo = new DefaultTableModel(
        new String[]{"ID", "Fecha", "Hora", "Equipo Local", "Equipo Visitante"}, 0
    );
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        conn = conexion.conectar();
        System.out.println("Conexión a la base de datos exitosa.");
        stmt = conn.createStatement();
        String sql = "SELECT p.id_partido, p.fecha, p.hora, e1.nombre AS local, e2.nombre AS visitante " +
                     "FROM partido p " +
                     "JOIN equipo e1 ON p.id_equipo_local = e1.id_equipo " +
                     "JOIN equipo e2 ON p.id_equipo_visitante = e2.id_equipo";
        System.out.println("Ejecutando consulta: " + sql);
        rs = stmt.executeQuery(sql);

        int rowCount = 0;
        while (rs.next()) {
            modelo.addRow(new Object[]{
                rs.getInt("id_partido"),
                rs.getString("fecha"),
                rs.getString("hora"),
                rs.getString("local"),
                rs.getString("visitante")
            });
            rowCount++;
        }
        System.out.println("Se cargaron " + rowCount + " partidos programados.");
        if (rowCount == 0) {
            System.out.println("Advertencia: No se encontraron partidos en la base de datos.");
        }
    } catch (SQLException | ClassNotFoundException e) {
        System.err.println("Error al cargar partidos: " + e.getMessage());
        JOptionPane.showMessageDialog(null, "Error al cargar partidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        cerrarRecursos(rs, stmt, conn);
    }
    return modelo;
}
}
