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

/**
 *
 * @author Santiago
 */
public class ConexionBDR {

    String bd = "ejemplo";
    String sql;
    String usuario = "root";
    String clave = "";
    String url = "jdbc:mysql://localhost:3306/";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection con;
    Statement sentencia;

    public ConexionBDR() {
    }

    public Connection conectar() throws ClassNotFoundException {
        Class.forName(driver);
        System.out.println("SE CONECTO A BASE DE DATOS " + bd);
        try {
            con = DriverManager.getConnection(url + bd, usuario, clave);
        } catch (SQLException ex) {
            System.out.println("NO SE CONECTO A BASE DE DATOS " + bd);
            Logger.getLogger(ConexionBDR.class.getName()).log(Level.SEVERE, null, ex);

        }
        return con;
    }

    public Connection getConnection() {
        return con;
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionBDR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ConexionBDR conexion = new ConexionBDR();
        conexion.conectar();
    }
}
