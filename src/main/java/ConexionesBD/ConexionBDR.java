/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionesBD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Santiago
 */
public class ConexionBDR {

    String bd = "kickoff";
    static String sql;
    String usuario = "root";
    String clave = "";
    String url = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection con;
    static Statement sentencia;
    
    // Ruta al archivo de configuración
    private static final String CONFIG_FILE = "config/database.txt";

    public ConexionBDR() {
        // Cargar configuración al crear la instancia
        cargarConfiguracion();
    }
    
    /**
     * Carga la configuración de la base de datos desde un archivo
     */
    private void cargarConfiguracion() {
        try (BufferedReader fichBuf = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String linea;
            while ((linea = fichBuf.readLine()) != null) {
                String[] configuracion = linea.split("=");
                if (configuracion.length == 2) {
                    String clave = configuracion[0].trim();
                    String valor = configuracion[1].trim();
                    
                    switch (clave.toLowerCase()) {
                        case "bd":
                        case "database":
                            this.bd = valor;
                            break;
                        case "usuario":
                        case "user":
                            this.usuario = valor;
                            break;
                        case "clave":
                        case "password":
                            this.clave = valor;
                            break;
                        case "url":
                        case "servidor":
                        case "host":
                            // Si el valor incluye el puerto, usamos tal cual
                            if (valor.contains(":")) {
                                this.url = "jdbc:mysql://" + valor + "/";
                            } else {
                                this.url = "jdbc:mysql://" + valor + ":3306/";
                            }
                            break;
                        case "driver":
                            this.driver = valor;
                            break;
                    }
                }
            }
            System.out.println("Configuración cargada correctamente");
        } catch (IOException e) {
            System.out.println("No se pudo cargar la configuración: " + e.getMessage());
            System.out.println("Se usarán los valores por defecto");
            // Los valores por defecto ya están establecidos en las declaraciones
        }
    }

    public Connection conectar() throws ClassNotFoundException {
        Class.forName(driver);
        System.out.println("SE CONECTÓ A BASE DE DATOS " + bd);
        try {
            con = DriverManager.getConnection(url + bd, usuario, clave);
//            JOptionPane.showMessageDialog(null, "Se conectó a la BD correctamente" ); 
        } catch (SQLException ex) {
            System.out.println("NO SE CONECTÓ A BASE DE DATOS " + bd);
//            JOptionPane.showMessageDialog(null, "No se pudo conectar a la BD correctamente" ); 
            Logger.getLogger(ConexionBDR.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }

    public Connection getConnection() {
        return con;
    }

    public void desconectar() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("SE DESCONECTÓ DE " + bd + " CORRECTAMENTE");
//            JOptionPane.showMessageDialog(null, "Conexión desconectada");
                
            }
        } catch (SQLException ex) {
            System.out.println("NO SE DESCONECTÓ DE " + bd + " CORRECTAMENTE");
            Logger.getLogger(ConexionBDR.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "No se pudo desconectar");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexionBDR conexion = new ConexionBDR();
        conexion.conectar();
        conexion.desconectar();
    }
    
    public Statement getStatement() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            conectar();
        }
        return con.createStatement();
    }
}