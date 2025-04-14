/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import Modelos.Usuario;
import java.sql.*;
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
    String jpql;
    static EntityManagerFactory emf;
    static EntityManager em;

    static {
        emf = Persistence.createEntityManagerFactory(rutaBDO);
    }

    public static Usuario validarUsuario(String nombre, String contraseña) {

        em = emf.createEntityManager();

        try {
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contraseña = :contraseña", Usuario.class);
            query.setParameter("nombre", nombre);
            query.setParameter("contraseña", contraseña);

            return query.getSingleResult(); // devuelve el objeto si encuentra coincidencia
        } catch (NoResultException e) {
            return null; // usuario no encontrado
        } finally {
            em.close();
        }
    }

    public static void insertarUsuario(Usuario usuario) {
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

    public static boolean existeUsuario(String nombre) {
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

    public static boolean actualizarUsuario(Usuario usuario) {
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

}
