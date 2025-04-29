/*
         * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
         * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;

import Modelos.Partido;
import com.toedter.calendar.JDateChooser;
import java.awt.TextField;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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
        //contadorID = obtenerUltimoID() + 1;
    }

    public boolean añadir(Partido par) {
        return listadoPartidos.add(par);
    }

    public TreeSet<Partido> getListadoPartidos() {
        return listadoPartidos;
    }

//    public boolean anadirPartido(Date fechaPartido, int idEquipoLocal, String nombreEquipoLocal,
//            int idEquipoVisitante, String nombreEquipoVisitante,
//            int golesLocal, int golesVisitante) {
//        Equipo equipoLocal = new Equipo(idEquipoLocal, nombreEquipoLocal);
//        Equipo equipoVisitante = new Equipo(idEquipoVisitante, nombreEquipoVisitante);
//
//        Partido nuevo = new Partido(contadorID++, fechaPartido, equipoLocal, equipoVisitante, golesLocal, golesVisitante);
//        return añadir(nuevo);
//    }
    /**
     * MÉTODO PARA CONVERTIR MATRIZ A OBJETO (implementación: actualizar
     * automaticamente)
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

            // contar los registros
            rs = st.executeQuery("SELECT COUNT(*) FROM partido");
            rs.next();
            int cantidad = rs.getInt(1);
            rs.close();

            // se crea matriz con el tamaño adecuado
            Object[][] matrizObj = new Object[cantidad][7]; //tamaño de la matriz

            //y se ejecuta la sentencia
            rs = st.executeQuery("SELECT p.id_partido, p.fecha, p.hora, el.nombre AS equipo_local, ev.nombre AS equipo_visitante, p.goles_local, p.goles_visitante "
                    + "FROM partido p " 
                    + "LEFT JOIN equipo el ON p.id_equipo_local = el.id_equipo "
                    + "LEFT JOIN equipo ev ON p.id_equipo_visitante = ev.id_equipo");

            int id = 0;
            //se especifica que va en cada esa+pacio de la matriz
            while (rs.next()) {
                matrizObj[id][0] = rs.getInt("id_partido");
                matrizObj[id][1] = rs.getDate("fecha");
                matrizObj[id][2] = rs.getTime("hora");
                matrizObj[id][3] = rs.getString("equipo_local");
                matrizObj[id][4] = rs.getString("equipo_visitante");
                matrizObj[id][5] = rs.getInt("goles_local");
                matrizObj[id][6] = rs.getInt("goles_visitante");
                id++;
            }

            return matrizObj;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return new Object[0][7]; //devuelve el objeto de la matriz con el tamaño que se le asignó
        } finally { //y finalmente procede a cerrar la conexión
            try { 
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexionBDR.desconectar();
        }
    }

    /**
     * MÉTODO PARA VALIDAR LA FECHA DEL JDATECHOOSER
     *
     * @param fecha
     * @return
     */
    public boolean validarFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");//nuevo objeto de SimpleDateFormat estableciendo el formato de fecha que será aceptado
        sdf.setLenient(false);//se establece en false
        try {
            sdf.parse(fecha);//si se introduce la fecha en el formato correcto
            return true;//devolverá true
        } catch (ParseException e) {//en caso de excepción (haber introducido un formato invalido)
            return false;//devuelve false
        }
    }

    /**
     * MÉTODO PARA VALIDAR LA HORA
     *
     * @param hora
     * @return
     */
    public boolean validarHora(String hora) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");//nuevo objeto de SimpleDateFormat estableciendo el formato de hora que será aceptado
        sdf.setLenient(false);//se establece en false
        try {
            sdf.parse(hora);//si se introduce la hora en el formato correcto
            return true;//devolverá true
        } catch (ParseException e) { //en caso de excepción (haber introducido un formato invalido)
            return false;//devuelve false
        }
    }

    /**
     * MÉTODO PARA OBTENER EL ID DE LOS EQUIPOS Y PODER DISTINGUIRLOS PARA
     * CUADNO SE VAYAN A ELIMINAR O SUBIR ESTADISTICAS DE ESTOS
     *
     * @param nombreEquipo
     * @return
     */
    public int obtenerIdEquipo(String nombreEquipo) {
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar();
            sentencia = conn.createStatement();
            String nombreEscapado = nombreEquipo.replace("'", "''"); //para obtener el id del equipo escapamos el nombre con comillas
            String sql = "SELECT id_equipo FROM equipo WHERE nombre = '" + nombreEscapado + "'";  //y se hace la sentencia
            rs = sentencia.executeQuery(sql); //para luego ejecutarse

            if (rs.next()) {
                return rs.getInt("id_equipo");//y de vuelve el id del equipo
            }
            return 0;
        } catch (SQLException | ClassNotFoundException e) { //catch en caso de excepxión 
            e.printStackTrace();
            return 0;
        } finally { //se cierra todo
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
            conexion.desconectar(); //y se desconecta
        }
    }

    /**
     * MÉTODO PARA QUE SE CARGUENN LOS EQUIPOS EN COMBOS Y QUE AYUDEN A QUE
     * LUZCA UN DISEÑO MUCHO MAS INTUITIVO
     *
     * @param comboLocal
     * @param comboVisitante
     */
    public void cargarEquiposEnCombos(JComboBox<String> comboLocal, JComboBox<String> comboVisitante) {
        comboLocal.removeAllItems(); //quita los items
        comboVisitante.removeAllItems();

        comboLocal.addItem("Seleccione un equipo"); //y en el combo carga un mensaje predeterminado
        comboVisitante.addItem("Seleccione un equipo");

        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar(); //se conecta a la base de datos
            sentencia = conn.createStatement();
            rs = sentencia.executeQuery("SELECT nombre FROM equipo"); //y selecciona el nombre de los equipos

            while (rs.next()) {
                String nombreEquipo = rs.getString("nombre"); //se crea una variable de nombreEquipo
                comboLocal.addItem(nombreEquipo); //y los añade
                comboVisitante.addItem(nombreEquipo);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar equipos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally { //cierra todo
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
            conexion.desconectar();//y se desconecta
        }
    }

    /**
     * MÉTODO PARA LIMPIAR CAMPOS LUEGO DE HACER UNA INSERCIÓN
     *
     * @param dateChooser
     * @param hora2
     * @param comboLocal
     * @param comboVisitante
     */
    public void limpiarCampos(JDateChooser dateChooser, TextField hora2, JComboBox<String> comboLocal, JComboBox<String> comboVisitante) {
        dateChooser.setDate(null); 
        hora2.setText("");
        comboLocal.setSelectedIndex(0);
        comboVisitante.setSelectedIndex(0);
    }

    /**
     * MÉTODO PARA ELIMINAR PARTIDOS
     *
     * @param idPartido
     */
    public void eliminarPartido(int idPartido) {
        Connection conn = null;
        Statement sentencia = null;
        try {
            conn = conexion.conectar(); //se conecta a la base de datos
            sentencia = conn.createStatement(); //se crea la sentencia
            String sql = "DELETE FROM partido WHERE id_partido = " + idPartido;
            int filasAfectadas = sentencia.executeUpdate(sql); //
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Partido eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el partido con ID: " + idPartido, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el partido: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            conexion.desconectar();
        }
    }

    /**
     * MÉTODO PARA CARGAR PARTIDOS EN LA TABLA DE LA VENTANA DE CONSULTAS
     *
     * @return
     */
    public DefaultTableModel cargarPartidos() {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Fecha", "Hora", "Equipo Local", "Equipo Visitante"}, 0
        ); //instancia de la tabla partidos
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar();
            System.out.println("Conexión a la base de datos exitosa en cargarPartidos.");//mensaje para la consola
            sentencia = conn.createStatement();
            String sql = "SELECT p.id_partido, p.fecha, p.hora, e1.nombre AS local, e2.nombre AS visitante " //sentencia
                    + "FROM partido p "
                    + "JOIN equipo e1 ON p.id_equipo_local = e1.id_equipo "
                    + "JOIN equipo e2 ON p.id_equipo_visitante = e2.id_equipo";
            System.out.println("Ejecutando consulta en cargarPartidos: " + sql); //mas comentarios
            rs = sentencia.executeQuery(sql);

            int contadorFilas = 0; //se crea un contador de filas
            while (rs.next()) {
                modelo.addRow(new Object[]{ //y se añade al modelo la fila
                    rs.getInt("id_partido"),
                    rs.getString("fecha"),
                    rs.getString("hora"),
                    rs.getString("local"),
                    rs.getString("visitante")
                });
                contadorFilas++; //para que el contador incremente
            }
            System.out.println("Se cargaron " + contadorFilas + " partidos programados en cargarPartidos."); //y muestre en la consola cuantos partidos se cargaron con el contador de filas
            if (contadorFilas == 0) {
                System.out.println("Advertencia: No se encontraron partidos en la base de datos en cargarPartidos.");
            }
        } catch (SQLException | ClassNotFoundException e) { //catch para excepciones 
            System.err.println("Error al cargar partidos en cargarPartidos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar partidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally { //se cierrra todo
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
            conexion.desconectar(); //y se desconecta
        }
        return modelo;
    }

    /**
     * MÉTODOS PARA CARGAR ESTADISTICAS DE LOS PARTIDOS EN LA VENTANA DE
     *
     *
     * @return
     */
    public DefaultTableModel cargarEstadisticas() {
        DefaultTableModel modelo = new DefaultTableModel( //instacia de la tabal de estadisticas
                new String[]{"Equipo", "GF", "GC", "PG", "PP", "PE", "Puntos"}, 0
        );
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        Statement sentenciaLocal = null;//sentencia para obtener al equipo local
        ResultSet rsLocal = null;
        Statement sentenciaVisitante = null; //sentencia para obtener al equipo visitante
        ResultSet rsVisitante = null;
        try {
            conn = conexion.conectar();
            sentencia = conn.createStatement();

            rs = sentencia.executeQuery("SELECT id_equipo, nombre FROM equipo"); //se selecciona el id del equipo en labase de datos
            while (rs.next()) {
                int idEquipo = rs.getInt("id_equipo"); //variable del id
                String nombreEquipo = rs.getString("nombre"); //variable para el nombre 

                int gf = 0;
                int gc = 0;
                int pg = 0;
                int pp = 0;
                int pe = 0;

                String sqlLocal = "SELECT goles_local, goles_visitante " //sentencia a ejecutar
                        + "FROM partido "
                        + "WHERE id_equipo_local = " + idEquipo
                        + " AND goles_local IS NOT NULL AND goles_visitante IS NOT NULL";
                sentenciaLocal = conn.createStatement();
                rsLocal = sentenciaLocal.executeQuery(sqlLocal);
                while (rsLocal.next()) { //lógica para estadisticas del equipo local
                    int golesLocal = rsLocal.getInt("goles_local");
                    int golesVisitante = rsLocal.getInt("goles_visitante");
                    gf += golesLocal;
                    gc += golesVisitante;
                    if (golesLocal > golesVisitante) {
                        pg++;
                    } else if (golesLocal < golesVisitante) {
                        pp++;
                    } else {
                        pe++;
                    }
                }

                String sqlVisitante = "SELECT goles_local, goles_visitante "
                        + "FROM partido "
                        + "WHERE id_equipo_visitante = " + idEquipo
                        + " AND goles_local IS NOT NULL AND goles_visitante IS NOT NULL";
                sentenciaVisitante = conn.createStatement();
                rsVisitante = sentenciaVisitante.executeQuery(sqlVisitante);
                while (rsVisitante.next()) { //lógica para calcular las estadisticas del equipo visitante
                    int golesLocal = rsVisitante.getInt("goles_local");
                    int golesVisitante = rsVisitante.getInt("goles_visitante");
                    gf += golesVisitante;
                    gc += golesLocal;
                    if (golesVisitante > golesLocal) {
                        pg++;
                    } else if (golesVisitante < golesLocal) {
                        pp++;
                    } else {
                        pe++;
                    }
                }

                int puntos = (pg * 3) + (pe * 1); //calcular los puntos

                modelo.addRow(new Object[]{ //y se añade al modelo la fila
                    nombreEquipo, gf, gc, pg, pp, pe, puntos
                });
            }
        } catch (SQLException | ClassNotFoundException e) { //manejo de excepciones
            JOptionPane.showMessageDialog(null, "Error al cargar estadísticas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try { //se cierra todo
                if (rsLocal != null) {
                    rsLocal.close();
                }
                if (sentenciaLocal != null) {
                    sentenciaLocal.close();
                }
                if (rsVisitante != null) {
                    rsVisitante.close();
                }
                if (sentenciaVisitante != null) {
                    sentenciaVisitante.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            conexion.desconectar(); //y se desconecta
        }
        return modelo; //devuelve el modelo
    }

    /**
     * MÉTODO PARA CALCULAR ESTADISTÍCAS
     *
     * @param idPartido
     * @param golesLocalStr
     * @param golesVisitanteStr
     * @return
     */
    public boolean registrarResultado(int idPartido, String golesLocalStr, String golesVisitanteStr) {
        int golesLocal, golesVisitante; //variables para los goles (teniendo en cuenta que se calcula solo con los goles ya que la tabla estadisticas de la BDR no está implementada)
        try {
            golesLocal = Integer.parseInt(golesLocalStr);//se parsean los goles
            golesVisitante = Integer.parseInt(golesVisitanteStr);
            if (golesLocal < 0 || golesVisitante < 0) { //y se maneja que los goles no puedan ser negativos, solo enteros (ceros si)
                JOptionPane.showMessageDialog(null, "Los goles no pueden ser negativos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese valores numéricos válidos para los goles.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Connection conn = null;
        Statement sentencia = null;
        try {
            conn = conexion.conectar();
            sentencia = conn.createStatement();
            String sql = "UPDATE partido SET goles_local = " + golesLocal + ", goles_visitante = " + golesVisitante //sentencia para actualizar en la tabla partido el registro de los goles
                    + " WHERE id_partido = " + idPartido;
            int filasAfectadas = sentencia.executeUpdate(sql); //se ejecuta
            if (filasAfectadas == 0) { 
                JOptionPane.showMessageDialog(null, "No se encontró el partido con ID: " + idPartido, "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar resultado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } finally { //cierra todo
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            conexion.desconectar(); // se desconecta
        }
    }

    /**
     * MÉTODO PARA BUSCAR UN PARTIDO POR SU ID (teniendo en cuenta que la
     * intención del contador con id y que se muestre en la tabla es un
     * identificador mas no un contador de partidos)
     *
     * @param id
     * @return
     */
    public Partido buscarPartidoPorId(int id) {
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        Statement stmtEquipos = null;
        ResultSet rsLocal = null;
        ResultSet rsVisitante = null;
        try {
            conn = conexion.conectar(); //se conecta
            if (conn != null && !conn.isClosed()) { //si la conexion es exitosa
                sentencia = conn.createStatement();//se podrá crear la sentencia
                String sql = "SELECT * FROM partido WHERE id_partido = " + id; //y busca a los partido spor su id en la tabla partido de la BDR
                rs = sentencia.executeQuery(sql);

                if (rs.next()) {
                    int idEquipoLocal = rs.getInt("id_equipo_local"); //variables para goles
                    int idEquipoVisitante = rs.getInt("id_equipo_visitante");

                    String nombreLocal = ""; //variables para nombres
                    String nombreVisitante = "";

                    stmtEquipos = conn.createStatement();
                    rsLocal = stmtEquipos.executeQuery("SELECT nombre FROM equipo WHERE id_equipo = " + idEquipoLocal);
                    if (rsLocal.next()) {
                        nombreLocal = rsLocal.getString("nombre");
                    }

                    rsVisitante = stmtEquipos.executeQuery("SELECT nombre FROM equipo WHERE id_equipo = " + idEquipoVisitante);
                    if (rsVisitante.next()) {
                        nombreVisitante = rsVisitante.getString("nombre");
                    }

                    Equipo equipoLocal = new Equipo(idEquipoLocal, nombreLocal);
                    Equipo equipoVisitante = new Equipo(idEquipoVisitante, nombreVisitante);

                    String fechaStr = rs.getString("fecha") + " " + rs.getString("hora");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date fechaPartido = sdf.parse(fechaStr);

                    int golesLocal = rs.getInt("goles_local");
                    if (rs.wasNull()) {
                        golesLocal = -1;
                    }
                    int golesVisitante = rs.getInt("goles_visitante");
                    if (rs.wasNull()) {
                        golesVisitante = -1;
                    }

                    Partido partido = new Partido(
                            rs.getInt("id_partido"),
                            fechaPartido,
                            equipoLocal,
                            equipoVisitante,
                            golesLocal,
                            golesVisitante
                    );
                    return partido;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al buscar partido por ID: " + e.getMessage());
            e.printStackTrace();
        } catch (ParseException e) {
            System.err.println("Error al parsear la fecha: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (rsLocal != null) {
                    rsLocal.close();
                }
                if (rsVisitante != null) {
                    rsVisitante.close();
                }
                if (stmtEquipos != null) {
                    stmtEquipos.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            conexion.desconectar();
        }
        return null;
    }

    /**
     * MÉTODO PARA BUSCAR PARTIDOS POR FECHA (se consultan en la base de datos)
     *
     * @param fecha La fecha en formato "yyyy-MM-dd" para buscar los partidos.
     * @return Un DefaultTableModel con los partidos encontrados.
     */
    public DefaultTableModel buscarPartidosPorFecha(String fecha) {
        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"ID", "Fecha", "Hora", "Equipo Local", "Equipo Visitante"}, 0
        );
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar();
            System.out.println("Conexión a la base de datos exitosa en buscarPartidosPorFecha.");
            sentencia = conn.createStatement();
            String sql = "SELECT p.id_partido, p.fecha, p.hora, e1.nombre AS local, e2.nombre AS visitante "
                    + "FROM partido p "
                    + "JOIN equipo e1 ON p.id_equipo_local = e1.id_equipo "
                    + "JOIN equipo e2 ON p.id_equipo_visitante = e2.id_equipo "
                    + "WHERE p.fecha = '" + fecha + "'";
            System.out.println("Ejecutando consulta en buscarPartidosPorFecha: " + sql);
            rs = sentencia.executeQuery(sql);

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
            System.out.println("Se encontraron " + rowCount + " partidos para la fecha " + fecha + " en buscarPartidosPorFecha.");
            if (rowCount == 0) {
                System.out.println("Advertencia: No se encontraron partidos para la fecha " + fecha + " en buscarPartidosPorFecha.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error al buscar partidos por fecha: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al buscar partidos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            conexion.desconectar();
        }
        return modelo;
    }

    /**
     * MÉTODO PARA VERIFICAR QUE EXISTTE UN EQUIPO
     *
     * @param nombre
     * @return
     */
    public boolean existeEquipo(String nombre) {
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            conn = conexion.conectar();
            sentencia = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM equipo WHERE nombre = '" + nombre.replace("'", "''") + "'";
            rs = sentencia.executeQuery(sql);
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException | ClassNotFoundException e) {
            return false;
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
            conexion.desconectar();
        }
    }

    /**
     * MÉTODO PARA VERIFICAR SI EXISTE UN PARTIDO
     *
     * @param fecha
     * @param hora
     * @param equipoLocal
     * @param equipoVisitante
     * @return
     */
    public boolean existePartido(String fecha, String hora, String equipoLocal, String equipoVisitante) {
        Connection conn = null;
        Statement sentencia = null;
        ResultSet rs = null;

        try {
            conn = conexion.conectar();
            sentencia = conn.createStatement();

            // normalizar los datos
            fecha = fecha.trim();
            hora = hora.trim();
            if (hora.length() == 5) {
                hora += ":00"; // asegurar el formato HH:mm:ss
            }
            equipoLocal = equipoLocal.trim().toLowerCase();
            equipoVisitante = equipoVisitante.trim().toLowerCase();

            // se escapan comillas simples para obtener el nombre del equiop
            String equipoLocalEscapado = equipoLocal.replace("'", "''");
            String equipoVisitanteEscapado = equipoVisitante.replace("'", "''");

            System.out.println("Verificando partido - Fecha: " + fecha + ", Hora: " + hora
                    + ", Local: " + equipoLocalEscapado + ", Visitante: " + equipoVisitanteEscapado);

            String sql = "SELECT COUNT(*) FROM partido p "
                    + "JOIN equipo el ON p.id_equipo_local = el.id_equipo "
                    + "JOIN equipo ev ON p.id_equipo_visitante = ev.id_equipo "
                    + "WHERE p.fecha = '" + fecha + "' AND p.hora = '" + hora + "' "
                    + "AND LOWER(el.nombre) = '" + equipoLocalEscapado + "' "
                    + "AND LOWER(ev.nombre) = '" + equipoVisitanteEscapado + "'";

            rs = sentencia.executeQuery(sql);

            int count;
            if (rs.next()) {
                count = rs.getInt(1);
            } else {
                count = 0;
            }
            System.out.println("Resultado de existePartido: " + count + " coincidencias encontradas");
            return count > 0;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conexion.desconectar();
        }
    }

    /**
     *
     * MÉTODO GUARDAR PARTIDO (segundo metodo)
     *
     * @param dateChooserFecha Componente de fecha.
     * @param hora2 Campo de texto para la hora.
     * @param equipoLocal Nombre del equipo local.
     * @param equipoVisitante Nombre del equipo visitante.
     * @param modeloPartidos Modelo de la tabla de partidos.
     * @return true si se guardó correctamente, false si hubo un error.
     */
    public boolean guardarPartido(JDateChooser dateChooserFecha, TextField hora2, String equipoLocal, String equipoVisitante, DefaultTableModel modeloPartidos) {
        Date fecha = dateChooserFecha.getDate();
        if (fecha == null) {
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSeleccionada = sdf.format(fecha);

        if (equipoLocal == null || equipoLocal.equals("Sin equipo") || equipoVisitante == null || equipoVisitante.equals("Sin equipo")) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar ambos equipos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (equipoLocal.equals(equipoVisitante)) {
            JOptionPane.showMessageDialog(null, "No puedes seleccionar el mismo equipo", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String horaStr = hora2.getText().trim();
        if (horaStr.length() == 5) {
            horaStr += ":00"; // asegurar el formato HH:mm:ss
        }

        // Verificar si el partido ya existe
        if (existePartido(fechaSeleccionada, horaStr, equipoLocal, equipoVisitante)) {
            JOptionPane.showMessageDialog(null, "El partido ya existe en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Connection conn = null;
        Statement sentencia = null;
        try {
            conn = conexion.conectar();
            int idLocal = obtenerIdEquipo(equipoLocal);
            int idVisitante = obtenerIdEquipo(equipoVisitante);

            if (idLocal == 0 || idVisitante == 0) {
                JOptionPane.showMessageDialog(null, "No se encontraron los equipos en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            sentencia = conn.createStatement();
            String sqlInsert = "INSERT INTO partido (fecha, hora, id_equipo_local, id_equipo_visitante) "
                    + "VALUES ('" + fechaSeleccionada + "', '" + horaStr + "', " + idLocal + ", " + idVisitante + ")";
            sentencia.executeUpdate(sqlInsert);

            return true;
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (sentencia != null) {
                    sentencia.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            conexion.desconectar();
        }
    }

    /**
     * Importa partidos desde un archivo XML y los guarda en la base de datos.
     *
     * @param modeloPartidos Modelo de la tabla de partidos para actualizar.
     * @return Número de partidos cargados con éxito.
     * @throws FileNotFoundException Si el archivo XML no se encuentra.
     * @throws Exception Si ocurre un error al leer o procesar el XML.
     */
//    public int importarPartidosDesdeXML(DefaultTableModel modeloPartidos) throws FileNotFoundException, Exception {
//        FileInputStream fis = new FileInputStream("partidos.xml");
//        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
//        ArrayList<Object[]> lista = (ArrayList<Object[]>) decoder.readObject();
//        decoder.close();
//
//        if (lista.isEmpty()) {
//            throw new Exception("XML vacío.");
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        int cargados = 0;
//
//        for (Object[] fila : lista) {
//            try {
//                Integer id = (Integer) fila[0];
//                String fechaStr = (String) fila[1];
//                String hora = (String) fila[2];
//                String equipoLocal = (String) fila[3];
//                String equipoVisitante = (String) fila[4];
//
//                Date fecha = dateFormat.parse(fechaStr);
//                if (!existeEquipo(equipoLocal) || !existeEquipo(equipoVisitante)) {
//                    continue;
//                }
//
//                if (hora.length() == 5) {
//                    hora += ":00"; // asegurar el formato HH:mm:ss
//                }
//
//                JDateChooser dateTemporal = new JDateChooser(fecha);
//                TextField horaTemporal = new TextField(hora);
//
//                if (!existePartido(fechaStr, hora, equipoLocal, equipoVisitante)) {
//                    if (guardarPartido(dateTemporal, horaTemporal, equipoLocal, equipoVisitante, modeloPartidos)) {
//                        cargados++;
//                    }
//                }
//
//            } catch (Exception e) {
//                System.err.println("Error al procesar partido: " + e.getMessage());
//            }
//        }
//
//        modeloPartidos.setRowCount(0);
//        DefaultTableModel nuevoModelo = cargarPartidos();
//        for (int i = 0; i < nuevoModelo.getRowCount(); i++) {
//            modeloPartidos.addRow(new Object[]{
//                nuevoModelo.getValueAt(i, 0),
//                nuevoModelo.getValueAt(i, 1),
//                nuevoModelo.getValueAt(i, 2),
//                nuevoModelo.getValueAt(i, 3),
//                nuevoModelo.getValueAt(i, 4)
//            });
//        }
//        return cargados;
//    }
}
