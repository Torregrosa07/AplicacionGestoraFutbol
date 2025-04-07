/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionesBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Santiago
 */
public class ConexionBDR {
    

public class ConexionBD {
    public static void main(String[] args) {
        Connection con;
        Statement sentencia;
        String sql;
        String usuario ="root";
        String clave ="FNYqz3BMkWdV";
        String url = "jdbc:mysql://192.168.0.139/Instituto";
        
        try {
            // comprobación de disponibilidad del conector jdbc - cargar driver
            // No necesario desde Java SE 6
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver");
//            } catch (ClassNotFoundException ex) {
//                System.out.println("Clase no encontrada para conectar con BD");
//            }
//            
            // conexión con la BD
            con = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión establecida con " + url);
            
            // cierre de la conexión
            con.close(); 
            
        } catch (SQLException e) {
            // Información del Error
            System.err.println("SQL Error mensaje: " + e.getMessage());
            System.err.println("SQL Estado: " + e.getSQLState());
            System.err.println("SQL código específico: " + e.getErrorCode());
        } catch (Exception e){
            e.printStackTrace(System.err);
            }
        }

    }
}
