/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.SNMPExceptions;
import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import org.primefaces.PrimeFaces;

/**
 *
 * @author sa
 */
public class Seguridad {

    public String dirigirMenuPorRol(String rol, Usuario oUsuario) throws SNMPExceptions, SQLException {
        if (rol.equals("Administrador")) {
            if (oUsuario.getRolAdministrador() == 1) {
                return "menus/administrator/menu.xhtml";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "No tienes permisos de administrador");
                PrimeFaces.current().dialog().showMessageDynamic(message);
                return "menus/ingresoFront.xhtml";
            }

        } else if (rol.equals("Recursos Humanos")) {
            if (oUsuario.getRolRecursosHumanos() == 1) {
                return "menus/recursoshumanos/menu.xhtml";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "No tienes permisos de recursos humanos");
                PrimeFaces.current().dialog().showMessageDynamic(message);
                return "ingresoFront.xhtml";
            }

        } else if (rol.equals("Planillero")) {
            if (oUsuario.getRolPlanillero() == 1) {
                return "menus/planillero/menu.xhtml";
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "No tienes permisos de planillero");
                PrimeFaces.current().dialog().showMessageDynamic(message);
                return "ingresoFront.xhtml";
            }

        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", "Datos Invalidos");
            PrimeFaces.current().dialog().showMessageDynamic(message);
            return"ingresoFront.xhtml";
        }

    }

    public Usuario obtenerUsuarioLogeado(int identificacion) throws SNMPExceptions, SQLException {
        UsuarioDB usariodb = new UsuarioDB();
        return usariodb.buscarUsuarioPorIdentificacion(identificacion);
    }

    public int obtenerIdentificacionEnLogin(String correo_electronico, String contrasena) throws SNMPExceptions, SQLException {
        UsuarioDB usariodb = new UsuarioDB();
        return usariodb.obtenerIdentificacionEnLogin(correo_electronico, contrasena);

    }
}
