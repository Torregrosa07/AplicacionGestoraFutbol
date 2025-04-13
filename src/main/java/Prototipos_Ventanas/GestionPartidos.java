/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Prototipos_Ventanas;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;
import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keiny
 */
public class GestionPartidos extends javax.swing.JPanel {

    private DefaultTableModel modelo;
    private int contadorID = 1;
    DefaultTableModel modeloPartidos = new DefaultTableModel(new String[]{"ID", "Fecha", "Hora", "Equipo Local", "Equipo Visitante"}, 0);
    DefaultTableModel modeloEstadisticas = new DefaultTableModel(new String[]{"Fecha", "GF", "GC", "PG", "PP", "PE", "Puntos"}, 0);

    public GestionPartidos() {
        initComponents();
        cargarEquiposEnCombos();

        modeloPartidos = new DefaultTableModel();
        modeloPartidos.addColumn("ID");
        modeloPartidos.addColumn("Fecha");
        modeloPartidos.addColumn("HORA");
        modeloPartidos.addColumn("Equipo Local");
        modeloPartidos.addColumn("Equipo Visitante");
        tablaPartidos.setModel(modeloPartidos);

    }

    public boolean validarFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public boolean validarHora(String hora2) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(hora2);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private int obtenerIdEquipo(Connection conn, String nombreEquipo) throws SQLException {
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
    }

    private void cargarEquiposEnCombos() {
        comboEquipoLocal.removeAllItems();
        comboEquipoVisitante.removeAllItems();

        comboEquipoLocal.addItem("Seleccione un equipo");
        comboEquipoVisitante.addItem("Seleccione un equipo");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kickoff", "root", "");
            stmt = conn.createStatement();
            String sql = "SELECT nombre FROM equipo";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String nombreEquipo = rs.getString("nombre");
                comboEquipoLocal.addItem(nombreEquipo);
                comboEquipoVisitante.addItem(nombreEquipo);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar equipos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void limpiarCampos() {
        dateChooserFecha.setDate(null);
        hora2.setText("");
        comboEquipoLocal.setSelectedIndex(0);
        comboEquipoVisitante.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        guargar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPartidos = new javax.swing.JTable();
        hora = new javax.swing.JLabel();
        hora2 = new java.awt.TextField();
        golesFavor = new javax.swing.JLabel();
        gf = new java.awt.TextField();
        golesContra = new javax.swing.JLabel();
        gc = new java.awt.TextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEstadisticas = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        dateChooserFecha = new com.toedter.calendar.JDateChooser();
        comboEquipoLocal = new javax.swing.JComboBox<>();
        comboEquipoVisitante = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel1.setText("FECHA:");

        guargar.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        guargar.setText("AÑADIR");
        guargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guargarActionPerformed(evt);
            }
        });

        tablaPartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaPartidos);

        hora.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        hora.setText("HORA:");

        hora2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hora2ActionPerformed(evt);
            }
        });

        golesFavor.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        golesFavor.setText("GF:");

        gf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gfActionPerformed(evt);
            }
        });

        golesContra.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        golesContra.setText("GC:");

        gc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gcActionPerformed(evt);
            }
        });

        tablaEstadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaEstadisticas);

        jButton1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jButton1.setText("PUBLICAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel2.setText("EQUIPOS:");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel3.setText("PUBLICAR UN PARTIDO");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel4.setText("PUBLICAR ESTADISTICAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(113, 113, 113)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(golesContra, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(golesFavor, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(gf, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(gc, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(hora2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(dateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(comboEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(guargar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dateChooserFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(hora2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(guargar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboEquipoVisitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboEquipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gf, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(golesFavor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(gc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(golesContra, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guargarActionPerformed
        String fechaSeleccionada = "";
        Date fecha = dateChooserFecha.getDate();
        if (fecha == null) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione una fecha.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        fechaSeleccionada = sdf.format(fecha);

        if (comboEquipoLocal.getSelectedIndex() == 0 || comboEquipoVisitante.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar ambos equipos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String equipoLocal = comboEquipoLocal.getSelectedItem().toString();
        String equipoVisitante = comboEquipoVisitante.getSelectedItem().toString();

        if (equipoLocal.equals(equipoVisitante)) {
            JOptionPane.showMessageDialog(this, "No puedes seleccionar el mismo equipo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String horaStr = hora2.getText().trim();
        if (!validarHora(horaStr)) {
            JOptionPane.showMessageDialog(this, "Hora inválida. Use formato HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ConexionBDR conexionBD = new ConexionBDR();
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = conexionBD.conectar(); // Usas tu clase
            int idLocal = obtenerIdEquipo(conn, equipoLocal);
            int idVisitante = obtenerIdEquipo(conn, equipoVisitante);

            if (idLocal == 0 || idVisitante == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron los equipos en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            stmt = conn.createStatement();
            String sqlInsert = "INSERT INTO partido (fecha, hora, id_equipo_local, id_equipo_visitante) "
                    + "VALUES ('" + fechaSeleccionada + "', '" + horaStr + "', " + idLocal + ", " + idVisitante + ")";
            stmt.executeUpdate(sqlInsert);

            modeloPartidos.addRow(new Object[]{
                contadorID,
                fechaSeleccionada,
                horaStr,
                equipoLocal,
                equipoVisitante
            });
            contadorID++;

            limpiarCampos();

        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conexionBD.desconectar(); // Lo cierras con tu método
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_guargarActionPerformed

    private void hora2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hora2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hora2ActionPerformed

    private void gfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gfActionPerformed

    private void gcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gcActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gcActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // calcularEstadisticas();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboEquipoLocal;
    private javax.swing.JComboBox<String> comboEquipoVisitante;
    private com.toedter.calendar.JDateChooser dateChooserFecha;
    private java.awt.TextField gc;
    private java.awt.TextField gf;
    private javax.swing.JLabel golesContra;
    private javax.swing.JLabel golesFavor;
    private javax.swing.JButton guargar;
    private javax.swing.JLabel hora;
    private java.awt.TextField hora2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEstadisticas;
    private javax.swing.JTable tablaPartidos;
    // End of variables declaration//GEN-END:variables

}
