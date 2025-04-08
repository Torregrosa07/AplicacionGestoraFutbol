/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Prototipos_Ventanas;

import Modelos.Usuario;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

/**
 *
 * @author Santiago
 */
public class Login extends javax.swing.JFrame {

    boolean esAdministrador = false;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setResizable(false);


        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });

        txtContraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusGained(evt);
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusLost(evt);
            }
        });
        SwingUtilities.invokeLater(() -> {
            jPanel1.requestFocusInWindow();
        });

        InicioSesion.addActionListener(e -> validarAcceso());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        InicioSesion = new javax.swing.JButton();
        EntrarDeInvitado = new javax.swing.JButton();
        Registrarse = new javax.swing.JButton();
        RestaurarContraseña = new javax.swing.JButton();
        txtContraseña = new javax.swing.JPasswordField();
        logo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsuario.setText("Usuario");
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });

        InicioSesion.setText("Iniciar Sesión");

        EntrarDeInvitado.setText("Entrar como invitado");
        EntrarDeInvitado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EntrarDeInvitadoActionPerformed(evt);
            }
        });

        Registrarse.setText("Registrarse");

        RestaurarContraseña.setText("No recuerdas tu contraseña?");

        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContraseña.setText("Contraseña");
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(logo)
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(InicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuario)
                                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(EntrarDeInvitado, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RestaurarContraseña)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(InicioSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RestaurarContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Registrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(EntrarDeInvitado, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed


    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void EntrarDeInvitadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EntrarDeInvitadoActionPerformed
        JOptionPane.showMessageDialog(this, "Acceso concedido. Bienvenido Invitado", "KickOff Tournaments", JOptionPane.INFORMATION_MESSAGE);

        // Abre la nueva ventana
        Consultar nuevaVentana = new Consultar();
        nuevaVentana.setVisible(true);
        nuevaVentana.setLocationRelativeTo(null); // Centra la nueva ventana
        esAdministrador = false;

        // Cierra la ventana de login
        this.dispose();
    }//GEN-LAST:event_EntrarDeInvitadoActionPerformed
    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {
        if (txtUsuario.getText().equals("Usuario")) {
            txtUsuario.setText("");
            txtUsuario.setForeground(Color.BLACK);
        }
    }

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {
        if (txtUsuario.getText().isEmpty()) {
            txtUsuario.setText("Usuario");
            txtUsuario.setForeground(Color.GRAY);
        }
    }

    private void txtContraseñaFocusGained(java.awt.event.FocusEvent evt) {
        String password = new String(txtContraseña.getPassword());
        if (password.equals("Contraseña")) {
            txtContraseña.setText("");
            txtContraseña.setForeground(Color.BLACK);
            txtContraseña.setEchoChar('•');
        }
    }

    private void txtContraseñaFocusLost(java.awt.event.FocusEvent evt) {
        String password = new String(txtContraseña.getPassword());
        if (password.isEmpty()) {
            txtContraseña.setText("Contraseña");
            txtContraseña.setForeground(Color.GRAY);
            txtContraseña.setEchoChar((char) 0);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        // Creación de usuarios
        Usuario u1 = new Usuario(1L, "admin", "1234");
        Usuario u2 = new Usuario(2L, "usuario1", "abcd");
        Usuario u3 = new Usuario(3L, "profesor", "clave");
//        em.persist(u1);
//        em.persist(u2);
//        em.persist(u3);


        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login ventana = new Login();
                ventana.setLocationRelativeTo(null); // Centra la ventana en la pantalla
                ventana.setVisible(true);
            }
        });
    }

    private void validarAcceso() {
        String[][] usuarios = {
            {"profesorx", "macarena"},
            {"Lobezno", "Laura123"},
            {"Tormenta", "elrayodestructor"},
            {"ciclope", "quemeves"},
            {"bestia", "noseescribirjajasaludos"},
            {"nightcrawler", "vivacristorey"},
            {"quicksilver", "hijodeamagnetonojodaporquemicontraseñaestanlarganomameslol"},
            {"mystique", "Xavier"},
            {"marksuckerberg", "facebook325"},
            {"homelander", "olasoyjomlander"}
        };

        String usuarioIngresado = txtUsuario.getText();
        String contraseñaIngresada = new String(txtContraseña.getPassword());

        if (usuarioIngresado.equals("Usuario")) {
            usuarioIngresado = "";
        }
        if (contraseñaIngresada.equals("Contraseña")) {
            contraseñaIngresada = "";
        }

        boolean accesoValido = false;
        esAdministrador = true;
        int i = 0;
        while (i < usuarios.length && !accesoValido) {
            if (usuarios[i][0].equalsIgnoreCase(usuarioIngresado)
                    && usuarios[i][1].equals(contraseñaIngresada)) {
                accesoValido = true;
            }
            i++;
        }

        if (accesoValido) {
            JOptionPane.showMessageDialog(this, "Acceso concedido. Bienvenido, " + usuarioIngresado + ".", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);

            // Abre la nueva ventana
            Gestion nuevaVentana = new Gestion();
            nuevaVentana.setVisible(true);
            nuevaVentana.setLocationRelativeTo(null); // Centra la nueva ventana

            // Cierra la ventana de login
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Acceso denegado", JOptionPane.ERROR_MESSAGE);
            txtContraseña.setText("");
            txtContraseñaFocusLost(null);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton EntrarDeInvitado;
    private javax.swing.JButton InicioSesion;
    private javax.swing.JButton Registrarse;
    private javax.swing.JButton RestaurarContraseña;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel logo;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
