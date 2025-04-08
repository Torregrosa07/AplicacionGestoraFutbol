/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
/*
*
* @author thomas
*/
@NamedQuery(name="queryEstática1", query="SELECT u FROM Usuario u")

@Entity
public class Usuario {
    @Id
    private Long id;
    private String Usuario;
    private String Contraseña;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(Long id, String Usuario, String Contraseña) {
        this.id = id;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", Usuario=" + Usuario + ", Contrase\u00f1a=" + Contraseña + '}';
    }

    

   
    
}
