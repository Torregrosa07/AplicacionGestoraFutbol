/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import Modelos.Usuario;
import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author thomas
 */
public class controladorUsuarios {

    static String nombreBDO = "AD_Ejemplo1.odb";
    static String rutaBase = "db/";
    static String rutaBDO = rutaBase + nombreBDO;
    static EntityManagerFactory emf;
    static EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory(rutaBDO);
    }

    public static Usuario validarUsuario(String nombre, String contraseña) {
        em = emf.createEntityManager(); 
        try {
            em.getTransaction().begin(); 
            em.flush();                  
            em.clear();                  
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contraseña = :contraseña", Usuario.class);
            query.setParameter("nombre", nombre);
            query.setParameter("contraseña", contraseña);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static void Añadir(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(usuario);
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    public static boolean existe(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(u) FROM Usuario u WHERE u.nombre = :nombre", Long.class);
            query.setParameter("nombre", nombre);
            Long count = query.getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public static Usuario buscarUsuarioPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
            query.setParameter("nombre", nombre);

            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public static boolean actualizar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
            query.setParameter("nombre", usuario.getNombre());

            Usuario original = query.getSingleResult();

            if (original != null) {
                original.setContraseña(usuario.getContraseña());
                em.merge(original);
                tx.commit();
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    public static boolean eliminarUsuario(String nombre) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
            query.setParameter("nombre", nombre);
            Usuario usuario = query.getSingleResult();

            if (usuario != null) {
                em.remove(usuario);
                tx.commit();
                return true;
            }
            return false;
        } catch (NoResultException e) {
            return false;
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            em.close();
        }
    }

    public static List<Usuario> obtenerTodosUsuarios() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    public static int importarUsuariosDesdeXML() throws FileNotFoundException, Exception {
        FileInputStream fis = new FileInputStream("usuarios.xml");
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
        ArrayList<Object[]> lista = (ArrayList<Object[]>) decoder.readObject();
        decoder.close();

        if (lista.isEmpty()) {
            throw new Exception("El archivo XML está vacío.");
        }

        int cargados = 0;

        for (Object[] fila : lista) {
            try {
                String nombre = (String) fila[0];
                String contraseña = (String) fila[1];

                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setContraseña(contraseña);

                if (!controladorUsuarios.existe(nombre)) {
                    controladorUsuarios.Añadir(nuevoUsuario);
                    cargados++;
                }

            } catch (Exception e) {
                System.err.println("Error al procesar usuario: " + e.getMessage());
            }
        }

        return cargados;
    }

}
