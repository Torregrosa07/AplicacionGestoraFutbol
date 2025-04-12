/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Prototipos_Ventanas;

import Modelos.Equipo;
import controladores.controladorEquipos;
import controladores.controladorUsuarios;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thomas
 */
public class GestionEquipos extends javax.swing.JPanel {

    private controladores.controladorEquipos controladorEquipos = new controladores.controladorEquipos();
    private Object[][] matrizDatos;
    private DefaultTableModel dtm;
    private String[] columnas = {"NOMBRE", "AÑO FUNDACIÓN", "LOCALIDAD", "ENTRENADOR"};

    /**
     * Creates new form GestionEquipos
     */
    public GestionEquipos() {
        initComponents();

        controladorEquipos = new controladores.controladorEquipos();

        actualizaTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    private void actualizaTabla() {
        //miniAgenda.añadir(new Deportista("Ana","Futbol",2011, 1.76f)); // registro de ejemplo directo
        matrizDatos = controladorEquipos.convertirAMatrizObject();
        dtm = new DefaultTableModel(matrizDatos, columnas) {
            //para impedir edición de las celdas
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        TDatos.setModel(dtm);

    }

    private void limpiarCampos() {
        txtNombreEquipo.setText("");
        txtAñoDeFundacion.setText("");
        txtLocalidad.setText("");
        txtEntrenador.setText("");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEliminar1 = new javax.swing.JButton();
        btnBuscar1 = new javax.swing.JButton();
        btnAnadir1 = new javax.swing.JButton();
        btnActualizar1 = new javax.swing.JButton();
        btnModificar1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreEquipo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAñoDeFundacion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtLocalidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEntrenador = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TDatos = new javax.swing.JTable();
        btnAnadir = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        btnEliminar1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnEliminar1.setText("Eliminar");
        btnEliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminar1ActionPerformed(evt);
            }
        });

        btnBuscar1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnBuscar1.setText("Buscar");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnAnadir1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnAnadir1.setText("Añadir");
        btnAnadir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadir1ActionPerformed(evt);
            }
        });

        btnActualizar1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnActualizar1.setText("Actualizar");

        btnModificar1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnModificar1.setText("Modificar");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1155, 581));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("Nombre Del Equipo:");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setText("Año De Fundación:");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setText("Localidad:");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setText("Entrenador:");

        TDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TDatos);

        btnAnadir.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnAnadir.setText("Añadir");
        btnAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnadirActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombreEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAñoDeFundacion, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAñoDeFundacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnadir, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1026, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminar1ActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnAnadir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadir1ActionPerformed

        // PRUEBA CON TREESET Y OBJETOS
        /*String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String dorsal = txtDorsal.getText().trim();
        String posicionStr = txtPosicion.getText().trim();

        if (nombre.isEmpty() || apellidos.isEmpty() || posicionStr.isEmpty() || dorsal.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "No añadido", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Obtener el equipo seleccionado
            String nombreEquipo = (String) jComboEquipo.getSelectedItem();
            Equipo equipo = controladorEquipos.getEquipoPorNombre(nombreEquipo);

            if (equipo == null) {
                JOptionPane.showMessageDialog(this, "Equipo no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convertir posición de String a enum
            Jugador.Posicion posicion;
            try {
                posicion = Jugador.Posicion.valueOf(posicionStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Posición no válida. Use: PORTERO, DEFENSA, MEDIOCENTRO o DELANTERO", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Jugador nuevo = new Jugador(nombre, apellidos, equipo, posicion, dorsal);
            boolean añadido = controladorJug.añadir(nuevo);

            if (añadido) {
                actualizaTabla(); // Asegúrate de usar el método correcto
                JOptionPane.showMessageDialog(this, "Jugador añadido correctamente.", "Añadido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "El jugador ya existe.", "No añadido", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al añadir jugador: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
         */
    }//GEN-LAST:event_btnAnadir1ActionPerformed

    private void btnAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnadirActionPerformed

        String nombreEquipo = txtNombreEquipo.getText().trim();
        String añoFundacionStr = txtAñoDeFundacion.getText().trim();
        String localidad = txtLocalidad.getText().trim();
        String entrenador = txtEntrenador.getText().trim();

        if (nombreEquipo.isEmpty() || añoFundacionStr.isEmpty() || localidad.isEmpty() || entrenador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int añoFundacion;
        try {
            añoFundacion = Integer.parseInt(añoFundacionStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año de fundación debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            txtAñoDeFundacion.setText("");
            return;
        }

        Equipo nuevoEquipo = new Equipo(nombreEquipo, añoFundacion, localidad, entrenador);
        boolean guardadoBD = controladorEquipos.guardarEnBD(nuevoEquipo);

        if (guardadoBD) {
            JOptionPane.showMessageDialog(this, "Equipo registrado correctamente en la base de datos.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizaTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el equipo en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAnadirActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int filaSeleccionada = TDatos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un equipo para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreEquipo = TDatos.getValueAt(filaSeleccionada, 0).toString();

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar el equipo '" + nombreEquipo + "'?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            boolean exito = controladorEquipos.eliminarEquipo(nombreEquipo);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Equipo eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizaTabla(); // vuelve a cargar la tabla desde la BD
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String nombreBuscar = txtNombreEquipo.getText().trim();

        if (nombreBuscar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa el nombre del equipo que deseas buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Equipo equipoEncontrado = controladorEquipos.buscarEquipoPorNombre(nombreBuscar);

        if (equipoEncontrado != null) {
   
            txtNombreEquipo.setText(equipoEncontrado.getNombre());
            txtAñoDeFundacion.setText(String.valueOf(equipoEncontrado.getAñoFundacion()));
            txtLocalidad.setText(equipoEncontrado.getLocalidad());
            txtEntrenador.setText(equipoEncontrado.getEntrenador());

            for (int i = 0; i < TDatos.getRowCount(); i++) {
                String nombreEnTabla = TDatos.getValueAt(i, 0).toString();
                if (nombreEnTabla.equalsIgnoreCase(nombreBuscar)) {
                    TDatos.setRowSelectionInterval(i, i); 
                    TDatos.scrollRectToVisible(TDatos.getCellRect(i, 0, true)); 
                    break;
                }
            }

            JOptionPane.showMessageDialog(this, "Equipo encontrado y seleccionado en la tabla.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún equipo con ese nombre.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        int filaSeleccionada = TDatos.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un equipo para modificar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombreOriginal = TDatos.getValueAt(filaSeleccionada, 0).toString();

        // Obtener nuevos valores desde los JTextFields
        String nuevoNombre = txtNombreEquipo.getText().trim();
        String anioStr = txtAñoDeFundacion.getText().trim();
        String nuevaLocalidad = txtLocalidad.getText().trim();
        String nuevoEntrenador = txtEntrenador.getText().trim();

        if (nuevoNombre.isEmpty() || anioStr.isEmpty() || nuevoEntrenador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int nuevoAnio;
        try {
            nuevoAnio = Integer.parseInt(anioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El año debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean exito = controladorEquipos.modificarEquipo(nombreOriginal, nuevoNombre, nuevoAnio, nuevaLocalidad, nuevoEntrenador);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Equipo modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizaTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo modificar el equipo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TDatos;
    private javax.swing.JButton btnActualizar1;
    private javax.swing.JButton btnAnadir;
    private javax.swing.JButton btnAnadir1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEliminar1;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnModificar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAñoDeFundacion;
    private javax.swing.JTextField txtEntrenador;
    private javax.swing.JTextField txtLocalidad;
    private javax.swing.JTextField txtNombreEquipo;
    // End of variables declaration//GEN-END:variables
}
