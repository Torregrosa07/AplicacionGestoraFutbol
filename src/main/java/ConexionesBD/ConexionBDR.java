/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionesBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Santiago
 */
public class ConexionBDR {
    

public class ConexionBD {
    private static final String URL = "jdbc:mysql://192.168.0.139/Instituto";
    private static final String USUARIO = "root";
    private static final String CLAVE = "FNYqz3BMkWdV";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}
    
}
