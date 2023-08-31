/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.SNMPExceptions;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author sa
 */
public class Usuario {

    private int identificacion;
    private String nombre;
    private String apellido;
    private String apellido2;
   private int estado;
String mensaje = "";

//Atenticacion
   private String correo_electronico;
    private String contrasena;

    //rol
    private int rolAdministrador;
    private int rolPlanillero;
    private int rolRecursosHumanos;
//Constructores

    public Usuario() {
    }

//COMPLETO PARA OBTENER TODO EL OBJETO
    public Usuario(int identificacion,
            String nombre,
            String apellido,
            String apellido2,
            String correo_electronico,
            String contrasena,
            int rolAdministrador,
            int rolPlanillero,
            int rolRecursosHumanos) {

        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.correo_electronico = correo_electronico;
        this.contrasena = contrasena;
        this.rolAdministrador = rolAdministrador;
        this.rolPlanillero = rolPlanillero;
        this.rolRecursosHumanos = rolRecursosHumanos;
    }

//SIN ROLES PARA INSERT DE ROLES
    public Usuario(int identificacion, String nombre, String apellido, String apellido2, String correo_electronico, String contrasena) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.correo_electronico = correo_electronico;
        this.contrasena = contrasena;
    }

//GET

    public int getRolAdministrador() {
        return rolAdministrador;
    }

    public int getRolPlanillero() {
        return rolPlanillero;
    }

    public int getRolRecursosHumanos() {
        return rolRecursosHumanos;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

//SET

    public void setRolAdministrador(int rolAdministrador) {
        this.rolAdministrador = rolAdministrador;
    }

    public void setRolPlanillero(int rolPlanillero) {
        this.rolPlanillero = rolPlanillero;
    }

    public void setRolRecursosHumanos(int rolRecursosHumanos) {
        this.rolRecursosHumanos = rolRecursosHumanos;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

//METODOS DE ADMINISTRADOR
    public void insertar_Administrador(Usuario usuario) throws SNMPExceptions {
       
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.insertar_Administrador(usuario);
  
    }

    public void insertar_Planillero(Usuario usuario) throws SNMPExceptions {

          
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.insertar_Planillero(usuario);
    }

    public void insertar_RecursosHumanos(Usuario usuario) throws SNMPExceptions {
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.insertar_RecursosHumanos(usuario);
    }
public void desactivar_Usuario_Por_Identificacion(int identificacion) throws SNMPExceptions {
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.Desactivar_Usuario_Por_Identificacion(identificacion);
    }

public void activar_Usuario_Por_Identificacion(int identificacion) throws SNMPExceptions {
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.Activar_Usuario_Por_Identificacion(identificacion);
    }
public boolean Validacion() {
 UsuarioDB pdb = new UsuarioDB();
        boolean resp = true;
 this.mensaje = "Error de validación \n";
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(this.getCorreo_electronico());
        if (String.valueOf(this.getIdentificacion()).equals(0)) {
            this.mensaje  += "Debe llenar el campo Identificación.";
            resp = false;
            return resp;

        } else if (this.getNombre().equals("")) {
           this.mensaje  +=  "Debe llenar el campo Nombre.";
            resp = false;
            return resp;
        } else if (this.getApellido().equals("")) {
          this.mensaje  += "Debe llenar el campo Primer Apellido.";
            resp = false;
            return resp;
        } else if (this.getApellido2().equals("")) {
           this.mensaje  +="Debe llenar el campo Segundo Apellido.";
            resp = false;
            return resp;
        } else if (this.getCorreo_electronico().equals("")) {
           this.mensaje  += "Debe agregar su correo en el campo definido.";
            resp = false;
            return resp;
        } else if (!mather.find()) {
          this.mensaje  += "Formato de correo inválido.";
            resp = false;
            return resp;
        } 
        mensaje = "";
        return resp;

    }
/* public void actualizar_Usuario(Usuario usuario) throws SNMPExceptions {
        UsuarioDB usuarioDB = new UsuarioDB();
        usuarioDB.actualizar_Usuario(usuario);
    }*/

    public String validarDatos() {
        if (this.identificacion == 0 || nombre.equals("") ||  apellido.equals("")) {
            return mensaje = "Llenar datos";
        } else {
            return "";
        }
    }
}
