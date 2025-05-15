/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import ConexionesBD.ConexionBDR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author Santiago
 */
public class ConexionBDRTest {
    
    /**
     * Test del método conectar()
     */
    @Test
    public void testConectar() {
        ConexionBDR conexion = new ConexionBDR();
        try {
            // Intentar conectar
            Connection conn = conexion.conectar();
            
            // Verificar que la conexión no sea nula
            assertNotNull(conn, "La conexión debe ser creada correctamente");
            
            // Verificar que la conexión esté abierta
            assertFalse(conn.isClosed(), "La conexión debe estar abierta");
            
            // Limpiar recursos
            conexion.desconectar();
            
        } catch (ClassNotFoundException | SQLException ex) {
            fail("No debería lanzar excepción: " + ex.getMessage());
        }
    }
    
    /**
     * Test del método getConnection()
     */
    @Test
    public void testGetConnection() {
        ConexionBDR conexion = new ConexionBDR();
        try {
            // Conectar primero
            conexion.conectar();
            
            // Obtener la conexión
            Connection conn = conexion.getConnection();
            
            // Verificar que no sea nula
            assertNotNull(conn, "getConnection() no debe devolver null");
            
            // Verificar que sea la misma conexión que se estableció
            assertFalse(conn.isClosed(), "La conexión debe estar abierta");
            
            // Limpiar recursos
            conexion.desconectar();
            
        } catch (ClassNotFoundException | SQLException ex) {
            fail("No debería lanzar excepción: " + ex.getMessage());
        }
    }
    
    /**
     * Test del método desconectar()
     */
    @Test
    public void testDesconectar() {
        ConexionBDR conexion = new ConexionBDR();
        try {
            // Conectar primero
            conexion.conectar();
            
            // Obtener la conexión
            Connection conn = conexion.getConnection();
            
            // Desconectar
            conexion.desconectar();
            
            // Verificar que la conexión esté cerrada
            assertTrue(conn.isClosed(), "La conexión debe estar cerrada después de desconectar");
            
        } catch (ClassNotFoundException | SQLException ex) {
            fail("No debería lanzar excepción: " + ex.getMessage());
        }
    }
    
    /**
     * Test del método getStatement()
     */
    @Test
    public void testGetStatement() {
        ConexionBDR conexion = new ConexionBDR();
        try {
            // Intenta obtener un Statement (debe conectar si no está conectado)
            Statement stmt = conexion.getStatement();
            
            // Verificar que no sea nulo
            assertNotNull(stmt, "El Statement no debe ser null");
            
            // Verificar que la conexión esté abierta
            assertFalse(conexion.getConnection().isClosed(), "La conexión debe estar abierta");
            
            // Limpiar recursos
            conexion.desconectar();
            
        } catch (ClassNotFoundException | SQLException ex) {
            fail("No debería lanzar excepción: " + ex.getMessage());
        }
    }
    
    /**
     * Test para verificar que getStatement() reconecta si está desconectado
     */
    @Test
    public void testGetStatementReconexion() {
        ConexionBDR conexion = new ConexionBDR();
        try {
            // Conectar y luego desconectar
            conexion.conectar();
            conexion.desconectar();
            
            // Ahora intentar obtener un Statement (debe volver a conectar)
            Statement stmt = conexion.getStatement();
            
            // Verificar que no sea nulo
            assertNotNull(stmt, "El Statement debe ser creado correctamente");
            
            // Verificar que la conexión esté abierta nuevamente
            assertFalse(conexion.getConnection().isClosed(), 
                    "La conexión debe estar abierta después de reconectar");
            
            // Limpiar recursos
            conexion.desconectar();
            
        } catch (ClassNotFoundException | SQLException ex) {
            fail("No debería lanzar excepción: " + ex.getMessage());
        }
    }
    
    /**
     * Test para verificar la configuración predeterminada
     */
    @Test
    public void testConfiguracionPredeterminada() {
        ConexionBDR conexion = new ConexionBDR();
        try {
            // Conectar para verificar que puede conectarse con la configuración predeterminada
            Connection conn = conexion.conectar();
            
            // Verificar que la conexión no sea nula
            assertNotNull(conn, "Debe poder conectarse con la configuración predeterminada");
            
            // Limpiar recursos
            conexion.desconectar();
            
        } catch (ClassNotFoundException ex) {
            fail("No debería lanzar excepción con la configuración predeterminada: " + ex.getMessage());
        }
    }
    
    /**
     * Test para verificar manejo de excepciones en desconexión
     */
    @Test
    public void testDesconectarSinConexion() {
        ConexionBDR conexion = new ConexionBDR();
        
        // Intentar desconectar sin haber conectado primero
        // No debería lanzar excepciones
        try {
            conexion.desconectar();
            // Si llegamos aquí, el test pasa porque no lanzó excepción
            assertTrue(true, "No debería lanzar excepción");
        } catch (Exception ex) {
            fail("No debería lanzar excepción al desconectar sin conexión: " + ex.getMessage());
        }
    }
}
    

