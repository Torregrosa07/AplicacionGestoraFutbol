/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionesBD;

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
    String url = "jdbc:mysql://localhost:3306/";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection con;
    static Statement sentencia;

    public ConexionBDR() {
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
            if (con!= null && !con.isClosed()) {
            con.close();
                System.out.println("SE DESCONECTÓ DE " + bd + "CORRECTAMENTE");
//            JOptionPane.showMessageDialog(null, "Conexión desconectada");
                
            }
        } catch (SQLException ex) {
            System.out.println("NO SE DESCONECTÓ DE " + bd + "CORRECTAMENTE");
            Logger.getLogger(ConexionBDR.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "No se pudo desconectar");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConexionBDR conexion = new ConexionBDR();
        conexion.conectar();
      
    }
    
     public Statement getStatement() throws SQLException, ClassNotFoundException {
        if (con == null || con.isClosed()) {
            conectar();
        }
        return con.createStatement();
    }
}
