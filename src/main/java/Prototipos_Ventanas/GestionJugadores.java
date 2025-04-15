/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Prototipos_Ventanas;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;
import Modelos.Jugador;
import controladores.controladorJugadores;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Santiago
 */
public class GestionJugadores extends javax.swing.JPanel {

    private controladores.controladorJugadores controladorJugadores = new controladores.controladorJugadores();
    private Object[][] matrizDatos;
    private DefaultTableModel dtm;
    private String[] columnas = {"ID", "NOMBRE", "APELLIDOS", "POSICION", "DORSAL", "EQUIPO", "EDAD", "SEXO"};

    /**
     * Creates new form GestionJugadores
     *
     * @throws java.lang.ClassNotFoundException
     */
    public GestionJugadores() {
        try {
            initComponents();

            // Usa la variable de clase controladorJugadores
            controladorJugadores.mostrarEquiposCombo(jComboEquipo);
            controladorJugadores.MostrarSexoCombo(jComboSexo);
            controladorJugadores.MostrarPosicionCombo(jComboPosicion);

            actualizarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(GestionJugadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarTabla() throws SQLException {
        matrizDatos = controladorJugadores.convertirAMatrizObject();
        dtm = new DefaultTableModel(matrizDatos, columnas) {
            //para impedir edición de las celdas
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        TDatos.setModel(dtm);

    }
//        // Inicializar controladores
//        controladorJug = new controladores.controladorJugadores();
//        controladorEquipos = new controladores.controladorEquipos();
//
//        // Cargar equipos (si no hay, crea equipos de prueba)
//        if (controladorEquipos.getListadoEquipos().isEmpty()) {
//            // Crear equipos de prueba si no hay ninguno
//            crearEquiposDePrueba();
//        }
//
//        cargarEquiposEnCombo();
//        actualizaTabla(); // Usa el método que funciona correctamente

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TDatos = new javax.swing.JTable();
        labelNombre = new javax.swing.JLabel();
        labelApellidos = new javax.swing.JLabel();
        labelDorsal = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new java.awt.TextField();
        txtApellidos = new java.awt.TextField();
        txtDorsal = new java.awt.TextField();
        btnAnadir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        labelEquipo = new javax.swing.JLabel();
        jComboEquipo = new javax.swing.JComboBox<>();
        labelSexo = new javax.swing.JLabel();
        jComboSexo = new javax.swing.JComboBox<>();
        jComboPosicion = new javax.swing.JComboBox<>();
        txtEdad = new javax.swing.JTextField();
        labelEdad = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        TDatos.setBackground(new java.awt.Color(204, 204, 204));
        TDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "APELLIDOS", "POSICIÓN", "DORSAL", "EQUIPO", "EDAD", "SEXO"
            }
        ));
        jScrollPane1.setViewportView(TDatos);
        if (TDatos.getColumnModel().getColumnCount() > 0) {
            TDatos.getColumnModel().getColumn(7).setResizable(false);
        }

        labelNombre.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelNombre.setText("Nombre:");

        labelApellidos.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelApellidos.setText("Apellidos:");

        labelDorsal.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelDorsal.setText("Dorsal:");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Posición:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        txtDorsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDorsalActionPerformed(evt);
            }
        });

        btnAnadir.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnAnadir.setText("Añadir");
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnModificar.setText("Modificar");

        btnEliminar.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        labelEquipo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelEquipo.setText("Equipo:");

        jComboEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin Equipo" }));
        jComboEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboEquipoActionPerformed(evt);
            }
        });

        labelSexo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelSexo.setText("Sexo:");

        jComboSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboSexoActionPerformed(evt);
            }
        });

        labelEdad.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        labelEdad.setText("Edad:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelEquipo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(labelApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelDorsal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelEdad)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDorsal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtEdad)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboSexo, 0, 101, Short.MAX_VALUE)
                                    .addComponent(jComboPosicion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDorsal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDorsal, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboPosicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEdad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtDorsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDorsalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDorsalActionPerformed

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed

        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String dorsal = txtDorsal.getText().trim();
        String posicion = (String) jComboPosicion.getSelectedItem();
        String sexo = (String) jComboSexo.getSelectedItem();
        String edadTexto = txtEdad.getText().trim();

        String equipoSeleccionado = (String) jComboEquipo.getSelectedItem();
        Integer idEquipo = null;
        if (!"Sin equipo".equals(equipoSeleccionado)) {
            controladores.controladorEquipos ctrlEquipos = new controladores.controladorEquipos();
            Equipo equipo = ctrlEquipos.buscarEquipoPorNombre(equipoSeleccionado);
            if (equipo != null) {
                idEquipo = equipo.getIDEquipo();
            }
        }

        // Validar que ningún campo esté vacío (incluyendo edad)
        if (nombre.isEmpty() || apellidos.isEmpty() || dorsal.isEmpty() || posicion.isEmpty() || sexo == null || sexo.isEmpty() || edadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Convertir edad a entero
        int edad;
        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido.", "Error en el campo edad", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al método del controlador para añadir el jugador
        controladores.controladorJugadores controlador = new controladores.controladorJugadores();
        controlador.anadirJugador(nombre, apellidos, dorsal, posicion, sexo, edad, idEquipo);
        JOptionPane.showMessageDialog(this, "Jugador añadido correctamente.");

        try {
            actualizarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(GestionJugadores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAnadirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        int filaSeleccionada = TDatos.getSelectedRow();

        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un jugador para eliminar.", "No se seleccionó nada", JOptionPane.WARNING_MESSAGE);
            return;
        }

       //Obtener el id del Jugador
        int idJugador;
        try {
            idJugador = Integer.parseInt(TDatos.getValueAt(filaSeleccionada, 0).toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al obtener el ID del jugador.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el nombre del jugador 
        String nombreJugador = TDatos.getValueAt(filaSeleccionada, 1).toString();

        // Confirmación antes de eliminar
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar al jugador \"" + nombreJugador + "\" con ID " + idJugador + "?","Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            controladores.controladorJugadores controlador = new controladores.controladorJugadores();
            controlador.eliminarJugador(idJugador);

            // Actualizar la tabla despues de eliminar
            try {
                actualizarTabla();
            } catch (SQLException ex) {
                Logger.getLogger(GestionJugadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jComboEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboEquipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboEquipoActionPerformed

    private void jComboSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboSexoActionPerformed

//    private void crearEquiposDePrueba() {
//        try {
//            // Ejemplo: Crear 3 equipos básicos
//            controladorEquipos.añadir(new Equipo(1, "Barcelona"));
//            controladorEquipos.añadir(new Equipo(2, "Real Madrid"));
//            controladorEquipos.añadir(new Equipo(3, "Atlético de Madrid"));
//
//            JOptionPane.showMessageDialog(
//                    this,
//                    "Se crearon equipos de prueba automáticamente.",
//                    "Aviso",
//                    JOptionPane.INFORMATION_MESSAGE
//            );
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(
//                    this,
//                    "Error al crear equipos de prueba: " + e.getMessage(),
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE
//            );
//        }
//    }
//
//    private void actualizaTabla() {
//        //miniAgenda.añadir(new Deportista("Ana","Futbol",2011, 1.76f)); // registro de ejemplo directo
//        matrizDatos = controladorJug.convertirAMatrizObject();
//         dtm = new DefaultTableModel(matrizDatos, columnas) {
//            //para impedir edición de las celdas
//            @Override
//            public boolean isCellEditable(int fila, int columna) {
//                return false;
//            }
//        };
//        TDatos.setModel(dtm);
//
//    }
//
//    private void cargarEquiposEnCombo() {
//        jComboEquipo.removeAllItems(); // Limpiar antes de cargar
//
//        for (Equipo equipo : controladorEquipos.getListadoEquipos()) {
//            jComboEquipo.addItem(equipo.getNombre());
//        }
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TDatos;
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> jComboEquipo;
    private javax.swing.JComboBox<String> jComboPosicion;
    private javax.swing.JComboBox<String> jComboSexo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelApellidos;
    private javax.swing.JLabel labelDorsal;
    private javax.swing.JLabel labelEdad;
    private javax.swing.JLabel labelEquipo;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelSexo;
    private java.awt.TextField txtApellidos;
    private java.awt.TextField txtDorsal;
    private javax.swing.JTextField txtEdad;
    private java.awt.TextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
