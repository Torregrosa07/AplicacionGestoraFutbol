/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Prototipos_Ventanas;

import controladores.controladorPartido;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keiny
 */
public class ConsultaPartidos extends javax.swing.JPanel {

    private controladorPartido controlador;
    private DefaultTableModel modeloPartidos;
    private DefaultTableModel modeloEstadisticas;

    public ConsultaPartidos() {
        controlador = new controladorPartido();
        initComponents();

        try {
            System.out.println("Configurando jTable2...");
            DefaultTableModel modelo = controlador.cargarPartidos();
            tablaPartidos.setModel(modelo);

            tablaPartidos.setEnabled(false);
            tablaPartidos.setRowSelectionAllowed(false);

            tablaPartidos.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
            tablaPartidos.getColumnModel().getColumn(1).setPreferredWidth(100); // Fecha
            tablaPartidos.getColumnModel().getColumn(2).setPreferredWidth(80);  // Hora
            tablaPartidos.getColumnModel().getColumn(3).setPreferredWidth(150); // Equipo Local
            tablaPartidos.getColumnModel().getColumn(4).setPreferredWidth(150); // Equipo Visitante

            System.out.println("jTable2 configurada correctamente. Filas: " + tablaPartidos.getRowCount());
        } catch (Exception e) {
            System.err.println("Error al configurar jTable2: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            System.out.println("Configurando jTable1...");
            DefaultTableModel estadisticasModel = controlador.cargarEstadisticas();
            if (estadisticasModel.getRowCount() == 0) {
                System.out.println("Advertencia: No se cargaron estadísticas. El modelo está vacío.");
            } else {
                System.out.println("Estadísticas cargadas exitosamente. Filas: " + estadisticasModel.getRowCount());
                estadisticasModel = ordenarEstadisticasPorPuntos(estadisticasModel);
                System.out.println("Estadísticas ordenadas por puntos. Filas: " + estadisticasModel.getRowCount());

            }

            tablaEstadisticas.setModel(estadisticasModel);

            tablaEstadisticas.setEnabled(false);
            tablaEstadisticas.setRowSelectionAllowed(false);

            tablaEstadisticas.getColumnModel().getColumn(0).setPreferredWidth(150); // Equipo
            tablaEstadisticas.getColumnModel().getColumn(1).setPreferredWidth(50);  // GF
            tablaEstadisticas.getColumnModel().getColumn(2).setPreferredWidth(50);  // GC
            tablaEstadisticas.getColumnModel().getColumn(3).setPreferredWidth(50);  // PG
            tablaEstadisticas.getColumnModel().getColumn(4).setPreferredWidth(50);  // PP
            tablaEstadisticas.getColumnModel().getColumn(5).setPreferredWidth(50);  // PE
            tablaEstadisticas.getColumnModel().getColumn(6).setPreferredWidth(80);  // Puntos

            System.out.println("jTable1 configurada correctamente. Filas: " + tablaEstadisticas.getRowCount());
        } catch (Exception e) {
            System.err.println("Error al configurar jTable1: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private DefaultTableModel ordenarEstadisticasPorPuntos(DefaultTableModel modelo) { //metodo de aditamento no realmente necesario, solo para mayor orden visual al momento de ver los puntos de manera "jerarquica"
        java.util.List<Object[]> filas = new java.util.ArrayList<>();
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object[] fila = new Object[modelo.getColumnCount()];
            for (int j = 0; j < modelo.getColumnCount(); j++) {
                fila[j] = modelo.getValueAt(i, j);
            }
            filas.add(fila);
        }

        filas.sort((fila1, fila2) -> {
            Integer puntos1 = (Integer) fila1[6]; // Columna "Puntos"
            Integer puntos2 = (Integer) fila2[6];
            return puntos2.compareTo(puntos1); // Orden descendente
        });

        DefaultTableModel modeloOrdenado = new DefaultTableModel(
                new String[]{"Equipo", "GF", "GC", "PG", "PP", "PE", "Puntos"}, 0
        );
        for (Object[] fila : filas) {
            modeloOrdenado.addRow(fila);
        }

        return modeloOrdenado;
    }

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
        tablaPartidos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEstadisticas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

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

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel1.setText("Partidos Establecidos");

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 20)); // NOI18N
        jLabel2.setText("Estadistícas ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(339, 339, 339)
                        .addComponent(jLabel1)))
                .addContainerGap(66, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(398, 398, 398))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaEstadisticas;
    private javax.swing.JTable tablaPartidos;
    // End of variables declaration//GEN-END:variables
}
