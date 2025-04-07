/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Santiago
 */
public class controladorEstadisticas {
     public static String obtenerPartidosPorFecha(Date fecha) {
        java.sql.Date fecha_sql = new java.sql.Date(fecha.getTime());
        StringBuilder resultado = new StringBuilder();

        try (Connection con = ConexionBD.conectar()) {
            String sql = "SELECT equipo_local, equipo_visitante, hora FROM partidos WHERE fecha = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, fecha_sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String local = rs.getString("equipo_local");
                String visitante = rs.getString("equipo_visitante");
                String hora = rs.getString("hora");
                resultado.append(local).append(" vs ").append(visitante)
                         .append(" a las ").append(hora).append("\n");
            }

        } catch (SQLException e) {
            return "Error al conectar con la base de datos: " + e.getMessage();
        }

        if (resultado.length() == 0) {
            return "No hay partidos programados para esa fecha.";
        }

        return resultado.toString();
    }
    
    
}
