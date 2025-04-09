/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import Modelos.Usuario;
import java.sql.*;
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
}
