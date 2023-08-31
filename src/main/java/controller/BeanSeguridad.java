/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.SNMPExceptions;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import model.Seguridad;
import model.Usuario;
import model.UsuarioDB;
import org.primefaces.PrimeFaces;

/**
 *
 * @author sa
 */
public class BeanSeguridad {

    //Inicio de sesion
    private String rol;
    private String correo_electronico;
    private String contrasena;
    String mensaje;
    //Usuario obtenido
    private Usuario usuarioLogueado = null;

    private Seguridad modelo = new Seguridad();

    public String getMensaje() {
        return mensaje;
    }

    //GET
    public String getRol() {
        return rol;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    //SET
    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setUsuarioLogueado(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /*
    public void validarUsuario() {
     
        if (!(this.getCorreo_electronico_en_login().equals("")) && !(this.getContrasena_en_login().equals(""))) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Usuario o contraseña incorrectos");
            PrimeFaces.current().dialog().showMessageDynamic(message);
         }
        
    }
     */
    public String ingresar() throws SNMPExceptions, SQLException {
        try {
            int identificacion = modelo.obtenerIdentificacionEnLogin(correo_electronico, contrasena);
            usuarioLogueado = modelo.obtenerUsuarioLogeado(identificacion);
            return modelo.dirigirMenuPorRol(this.rol, this.usuarioLogueado);
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema con los credenciales!");
        }
        return "";
    }

}
