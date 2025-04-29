/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import Modelos.Usuario;
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

    // Nombre del archivo de base de datos ODB (OpenOffice Base)
    static String nombreBDO = "usuarios.odb";
    // Ruta base donde se encuentra la base de datos
    static String rutaBase = "db/";
    // Ruta completa hacia la base de datos, combinando la ruta base y el nombre del archivo
    static String rutaBDO = rutaBase + nombreBDO;
    // Fábrica para crear EntityManagers, se inicializa una sola vez para toda la aplicación
    static EntityManagerFactory emf;
    // EntityManager utilizado para realizar operaciones sobre la base de datos (consultas, inserciones, etc.)
    static EntityManager em;

    // Bloque de inicialización estático: se ejecuta una vez al cargar la clase y configura la conexión a la base de datos
    static {
        // Se crea una única instancia de EntityManagerFactory con la ruta especificada, lo que permite administrar conexiones
        emf = Persistence.createEntityManagerFactory(rutaBDO);
    }

    /**
     * Valida si el usuario y contraseña coinciden dentro de la BDO
     *
     * @param nombre
     * @param contraseña
     * @return
     */
    public Usuario validarUsuario(String nombre, String contraseña) {
        // Se crea un nuevo EntityManager desde la fábrica.
        em = emf.createEntityManager();
        try {
            // Inicia una nueva transacción para asegurar la consistencia durante la consulta
            em.getTransaction().begin();

            // Limpia y sincroniza el contexto de persistencia:
            // flush(): escribe los cambios pendientes en la base de datos (aunque aquí no haya cambios, se asegura sincronización)
            em.flush();
            // clear(): limpia el contexto de persistencia, descartando entidades en caché
            em.clear();
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre AND u.contraseña = :contraseña", Usuario.class);
            query.setParameter("nombre", nombre);
            query.setParameter("contraseña", contraseña);

            // Retorna el usuario encontrado, si existe
            return query.getSingleResult();
        } catch (NoResultException e) {
            // Si no se encuentra ningún usuario que coincida, se devuelve null
            return null;
        }
    }

    /**
     * Añade un usuario a la BDO
     *
     * @param usuario
     */
    public void añadir(Usuario usuario) {
        // Se crea un nuevo EntityManager desde la fábrica 
        EntityManager em = emf.createEntityManager();
        // Se obtiene la transacción asociada al EntityManager
        EntityTransaction tx = em.getTransaction();
        try {
            // Inicia la transacción para permitir operaciones de escritura en la base de datos
            tx.begin();
            // Se guarda el objeto usuario en la base de datos
            em.persist(usuario);
            // Si no hubo errores, se confirma (commit) la transacción y se guarda definitivamente el cambio
            tx.commit();
        } finally {
            // En caso de que ocurra un error y la transacción siga activa, se revierte (rollback) para evitar datos corruptos
            if (tx.isActive()) {
                tx.rollback();
            }

            // Se cierra el EntityManager para liberar recursos
            em.close();
        }
    }

    /**
     * Revisa si el usuario cuyo nombre recibe, existe dentro de la BDO
     *
     * @param nombre
     * @return
     */
    public boolean existe(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            // Se crea una consulta JPQL que cuenta cuántos usuarios existen con el nombre dado
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(u) FROM Usuario u WHERE u.nombre = :nombre", Long.class);
            query.setParameter("nombre", nombre);
            // Se ejecuta la consulta y se obtiene el número de coincidencias
            Long count = query.getSingleResult();
            // Retorna true si hay al menos una coincidencia, es decir, el usuario ya existes
            return count > 0;
        } finally {
            // Se cierra el EntityManager para liberar recursos
            em.close();
        }
    }

    /**
     * Similar al anterior, busca si el usuario está dentro de la BDO y devuelve
     * su nombre
     *
     * @param nombre
     * @return
     */
    public static Usuario buscar(String nombre) {
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

    /**
     * Busca al usuario y
     *
     * @param usuario
     * @return
     */
    public boolean editar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
            query.setParameter("nombre", usuario.getNombre());

            // Se ejecuta la consulta para obtener el usuario original
            Usuario original = query.getSingleResult();

            // Si el usuario original existe, se procede a actualizar su contraseña
            if (original != null) {
                original.setContraseña(usuario.getContraseña());
                // Se fusiona el usuario modificado en el contexto de persistencia
                em.merge(original);
                // Se confirma la transacción para guardar los cambios
                tx.commit();
                return true; // Returnea true si la edición fue exitosa
            }
            return false; // Returnea false si no se encuentra el usuario original
        } catch (NoResultException e) {
            return false;
        } finally {
            // Si la transacción sigue activa por cualquier motivo, se hace rollback
            if (tx.isActive()) {
                tx.rollback();
            }
            // Se cierra el EntityManager para liberar recursos
            em.close();
        }
    }

    /**
     * Recibe el nombre del usuario y lo borra
     *
     * @param nombre
     * @return
     */
    public boolean eliminar(String nombre) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            TypedQuery<Usuario> query = em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
            query.setParameter("nombre", nombre);
            Usuario usuario = query.getSingleResult();

            // Si el usuario existe, se elimina
            if (usuario != null) {
                em.remove(usuario);
                // Se confirma la transacción (commit) para aplicar la eliminación
                tx.commit();
                return true; // Retorna true si el usuario fue eliminado correctamente
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
            // Se crea y ejecuta una consulta JPQL para obtener todos los usuairos
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

}
