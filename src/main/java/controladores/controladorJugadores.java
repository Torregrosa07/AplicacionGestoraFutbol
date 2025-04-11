/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import ConexionesBD.ConexionBDR;
import Modelos.Jugador;
import Modelos.Equipo;
import Prototipos_Ventanas.GestionJugadores;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Santiago
 */
public class controladorJugadores {

    private TreeSet<Jugador> listadoJugadores = new TreeSet<>();
    
    
    public boolean añadir(Jugador jug) {
        return listadoJugadores.add(jug); // si se añadió devuelve true, si ya existía false
    }
    
    public boolean anadirJugador(String nombre, String apellidos, String equipoNombre, int dorsal, String posicion) {
    // Creamos un equipo temporal (deberías tener una lista de equipos disponibles)
    Equipo equipo = new Equipo(0, equipoNombre); // ID 0 temporal
    Jugador nuevo = new Jugador(nombre, apellidos, equipo, Jugador.Posicion.PORTERO, nombre);
    return añadir(nuevo);
}




    public Object[][] convertirAMatrizObject() {
    Object[][] matrizObj = new Object[listadoJugadores.size()][5];
    int id = 0;

    for (Jugador jug : this.listadoJugadores) {
        matrizObj[id][0] = jug.getNombre();
        matrizObj[id][1] = jug.getApellidos();
        matrizObj[id][2] = jug.getEquipo() != null ? jug.getEquipo().getNombre() : "Sin equipo";
        matrizObj[id][3] = jug.getDorsal();
        matrizObj[id][4] = jug.getPosicion().toString();
        id++;
    }
    return matrizObj;
}
    
    public void MostrarSexoCombo (JComboBox jComboSexo){
        ConexionBDR objetoConexion = new ConexionBDR();
        
        try {
            objetoConexion.conectar();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GestionJugadores.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    String sql = "SHOW COLUMNS FROM jugador LIKE 'sexo'";
    
    try { 
        Statement sentencia = objetoConexion.conectar().createStatement();
        ResultSet rs = sentencia.executeQuery(sql);
        
        if(rs.next()){
            // Obtenemos el string del tipo, por ejemplo: "enum('Masculino','Femenino')"
            String type = rs.getString("Type");
            System.out.println("Tipo obtenido de la BD: " + type);
            
            // Extraer la parte entre paréntesis
            String enumValues = type.substring(type.indexOf("(") + 1, type.indexOf(")"));
            System.out.println("Valores enumerados: " + enumValues);
            
            // Dividir los valores por coma
            String[] valores = enumValues.split(",");
            
            // Limpiar el JComboBox antes de agregar nuevos elementos
            jComboSexo.removeAllItems();
            
            // Agregar cada valor, eliminando las comillas simples y espacios extra
            for(String valor : valores){
                String valorLimpio = valor.replace("'", "").trim();
                System.out.println("Agregando valor: " + valorLimpio);
                jComboSexo.addItem(valorLimpio);
            }
        } else {
            System.out.println("No se encontró la columna 'sexo' en la tabla jugadores.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}    

}
