/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import ConexionesBD.ConexionBDR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

/**
 *
 * @author Santiago
 */
public class Test_BDR {
    
    @Test
    void testCargarConfiguracion_ArchivoInexistente() {
        ConexionBDR conexion = new ConexionBDR();
        assertDoesNotThrow(() -> conexion.conectar());
    }

    @Test
    void testDesconexion_Exitosa() throws SQLException, ClassNotFoundException {
        ConexionBDR conexion = new ConexionBDR();
        conexion.conectar();
        assertDoesNotThrow(() -> conexion.desconectar());
    }
}
    

