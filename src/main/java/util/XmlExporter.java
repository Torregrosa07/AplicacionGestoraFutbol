/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import ConexionesBD.ConexionBDR;
import Modelos.Equipo;
import Modelos.Jugador;
import Modelos.Jugador.Posicion;
import Modelos.Jugador.Sexo;
import Modelos.Jugador.TipoTarjeta;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author keiny
 */
public class XmlExporter {

      public void exportarEquiposYJugadoresAXml(String rutaArchivo) {
        ConexionBDR conexion = new ConexionBDR();

        try {
            conexion.conectar();
            Connection conn = conexion.getConnection();

            if (conn != null && !conn.isClosed()) {
                Statement stmtEquipos = conn.createStatement();
                String sqlEquipos = "SELECT * FROM equipo";
                ResultSet rsEquipos = stmtEquipos.executeQuery(sqlEquipos);

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();

                Element rootElement = doc.createElement("equipos");
                doc.appendChild(rootElement);

                while (rsEquipos.next()) {
                    int idEquipo = rsEquipos.getInt("id_equipo");
                    String nombre = rsEquipos.getString("nombre");
                    int anioFundacion = rsEquipos.getInt("anio_fundacion");
                    String localidad = rsEquipos.getString("localidad");
                    String entrenador = rsEquipos.getString("entrenador");

                    Equipo equipo = new Equipo(idEquipo, nombre, anioFundacion, localidad, entrenador);

                    Element equipoElement = doc.createElement("equipo");
                    equipoElement.setAttribute("id", String.valueOf(equipo.getIDEquipo()));
                    equipoElement.setAttribute("nombre", nombre != null ? nombre : "");
                    equipoElement.setAttribute("anio_fundacion", String.valueOf(anioFundacion));
                    equipoElement.setAttribute("localidad", localidad != null ? localidad : "");
                    equipoElement.setAttribute("entrenador", entrenador != null ? entrenador : "");

                    rootElement.appendChild(equipoElement);

                    // consultar jugadores del equipo
                    Statement stmtJugadores = conn.createStatement();
                    String sqlJugadores = "SELECT * FROM jugador WHERE id_equipo = " + idEquipo;
                    ResultSet rsJugadores = stmtJugadores.executeQuery(sqlJugadores);

                    while (rsJugadores.next()) {
                        int idJugador = rsJugadores.getInt("id_jugador");
                        String nombreJugador = rsJugadores.getString("nombre");
                        String apellidos = rsJugadores.getString("apellidos");
                        String posicionStr = rsJugadores.getString("posicion");
                        String dorsal = rsJugadores.getString("dorsal");
                        String sexoStr = rsJugadores.getString("sexo");
                        int edad = rsJugadores.getInt("edad");
                        String nacionalidad = rsJugadores.getString("nacionalidad");

                        // manejo de enums con valores por defecto con try catchs
                        Posicion posicion = Posicion.DEFENSA; // valor por defecto
                        try {
                            if (posicionStr != null && !posicionStr.trim().isEmpty()) {
                                posicion = Posicion.valueOf(posicionStr.toUpperCase());
                            } else {
                                System.out.println("Posición nula o vacía para jugador ID: " + idJugador + ", usando DEFENSA por defecto.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Posición inválida '" + posicionStr + "' para jugador ID: " + idJugador + ", usando DEFENSA por defecto.");
                        }

                        Sexo sexo = Sexo.NO_DEFINIDO; // valor por defecto
                        try {
                            if (sexoStr != null && !sexoStr.trim().isEmpty()) {
                                sexo = Sexo.valueOf(sexoStr.toUpperCase());
                            } else {
                                System.out.println("Sexo nulo o vacío para jugador ID: " + idJugador + ", usando NO_DEFINIDO por defecto.");
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("Sexo inválido '" + sexoStr + "' para jugador ID: " + idJugador + ", usando NO_DEFINIDO por defecto.");
                        }

                        // tipoTarjeta no se incluye porque no está en la base de datos
                        Jugador jugador = new Jugador(
                            idJugador,
                            nombreJugador != null ? nombreJugador : "",
                            apellidos != null ? apellidos : "",
                            posicion,
                            equipo,
                            dorsal != null ? dorsal : "",
                            sexo,
                            edad,
                            nacionalidad != null ? nacionalidad : ""
                        );

                        Element jugadorElement = doc.createElement("jugador");
                        jugadorElement.setAttribute("id", String.valueOf(jugador.getIDjugador()));
                        jugadorElement.setAttribute("nombre", jugador.getNombre());
                        jugadorElement.setAttribute("apellidos", jugador.getApellidos());
                        jugadorElement.setAttribute("posicion", jugador.getPosicion() != null ? jugador.getPosicion().name() : "DESCONOCIDO");
                        jugadorElement.setAttribute("numero", jugador.getDorsal());
                        jugadorElement.setAttribute("edad", String.valueOf(jugador.getEdad()));
                        jugadorElement.setAttribute("nacionalidad", jugador.getNacionalidad());
                        jugadorElement.setAttribute("genero", jugador.getSexo() != null ? jugador.getSexo().name() : "NO_DEFINIDO");

                        equipoElement.appendChild(jugadorElement);
                    }

                    rsJugadores.close();
                    stmtJugadores.close();
                }

                rsEquipos.close();
                stmtEquipos.close();

                // de guarda el XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File(rutaArchivo));
                transformer.transform(source, result);

                System.out.println("Archivo XML exportado correctamente a: " + rutaArchivo);
            } else {
                System.out.println(" No se pudo conectar a la base de datos.");
            }

        } catch (Exception e) {
            System.err.println("Error al exportar XML: " + e.getMessage());
            e.printStackTrace();
        } finally {
            conexion.desconectar();
        }
    }

    public static void main(String[] args) {
        XmlExporter exporter = new XmlExporter();
        exporter.exportarEquiposYJugadoresAXml("equipos_y_jugadores.xml");
    }
}
