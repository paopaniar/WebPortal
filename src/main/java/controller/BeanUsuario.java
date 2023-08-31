/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.SNMPExceptions;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Empleado;
import model.EmpleadoDB;
import model.Usuario;
import model.UsuarioDB;

/**
 *
 * @author sa
 */
public class BeanUsuario implements Serializable {
    
    LinkedList<Usuario> listaUsuarios = new LinkedList<Usuario>();
    LinkedList<Usuario> listaUsuariosDesactivados = new LinkedList<Usuario>();
    String mensaje = "";
    String mensajeError = "";
//MODELO
    private Usuario modelo = new Usuario();
    private int identificacion;
    private String nombre;
    private String apellido;
    private String apellido2;
    private int estado;

//Atenticacion
    private String correo_electronico;
    private String contrasena;

    //rol
    private int rolAdministrador;
    private int rolPlanillero;
    private int rolRecursosHumanos;

    //PRUEBA DE KEVIN
    Usuario usuario_a_modificar;

//GET
    public Usuario getUsuario_a_modificar() {
        return usuario_a_modificar;
    }
    
    public LinkedList<Usuario> getListaUsuarios() throws SNMPExceptions {
        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        UsuarioDB pDB = new UsuarioDB();
        
        lista = pDB.obtener_Todos_Los_Usuarios();
        
        LinkedList resultLista = new LinkedList();
        
        resultLista = lista;
        return resultLista;
    }
    
    public Usuario getModelo() {
        return modelo;
    }
    
    public void setModelo(Usuario modelo) {
        this.modelo = modelo;
    }
    
    public LinkedList<Usuario> getListaUsuariosDesactivados() throws SNMPExceptions {
        LinkedList<Usuario> lista = new LinkedList<Usuario>();
        UsuarioDB eDB = new UsuarioDB();
        
        lista = eDB.obtener_Todos_Los_Usuarios_Desactivados();
        
        LinkedList resultLista = new LinkedList();
        
        resultLista = lista;
        return resultLista;
    }
    
    public void setListaUsuariosDesactivados(LinkedList<Usuario> listaUsuariosDesactivados) {
        this.listaUsuariosDesactivados = listaUsuariosDesactivados;
    }
    
    public int getRolAdministrador() {
        return modelo.getRolAdministrador();
    }
    
    public int getRolPlanillero() {
        return modelo.getRolPlanillero();
    }
    
    public int getRolRecursosHumanos() {
        return modelo.getRolRecursosHumanos();
    }
    
    public String getCorreo_electronico() {
        return modelo.getCorreo_electronico();
    }
    
    public String getContrasena() {
        return modelo.getContrasena();
    }
    
    public String getMensajeError() {
        return mensajeError;
    }
    
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
    
    public int getIdentificacion() {
        return modelo.getIdentificacion();
    }
    
    public String getNombre() {
        return modelo.getNombre();
    }
    
    public String getApellido() {
        return modelo.getApellido();
    }
    
    public String getApellido2() {
        return modelo.getApellido2();
    }
    
    public String getMensaje() {
        return mensaje;
    }
//SET

    public void setUsuario_a_modificar(Usuario usuario_a_modificar) {
        this.usuario_a_modificar = usuario_a_modificar;
    }
    
    public int getEstado() {
        return modelo.getEstado();
    }
    
    public void setEstado(int estado) {
        this.modelo.setEstado(estado);
    }
    
    public void setRolAdministrador(int rolAdministrador) {
        this.modelo.setRolAdministrador(rolAdministrador);;
    }
    
    public void setRolPlanillero(int rolPlanillero) {
        this.modelo.setRolPlanillero(rolPlanillero);
    }
    
    public void setRolRecursosHumanos(int rolRecursosHumanos) {
        this.modelo.setRolRecursosHumanos(rolRecursosHumanos);
    }
    
    public void setCorreo_electronico(String correo_electronico) {
        this.modelo.setCorreo_electronico(correo_electronico);
    }
    
    public void setContrasena(String contrasena) {
        this.modelo.setContrasena(contrasena);
    }
    
    public void setIdentificacion(int identificacion) {
        this.modelo.setIdentificacion(identificacion);
    }
    
    public void setNombre(String nombre) {
        this.modelo.setNombre(nombre);
    }
    
    public void setApellido(String apellido) {
        this.modelo.setApellido(apellido);
    }
    
    public void setApellido2(String apellido2) {
        this.modelo.setApellido2(apellido2);
    }
    
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

//METODO DE AGREGAR ADMINISTRADOR
    public void insertar_Administrador(int identificacion,
            String nombre,
            String apellido,
            String apellido2,
            String correo_electronico,
            String contrasena) throws SNMPExceptions {
        
        try {
            this.modelo.insertar_Administrador(new Usuario(identificacion, nombre, apellido, apellido2, correo_electronico, contrasena));
            this.setMensaje("Los datos se han guardado exitosamente como administrador");
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }
        
    }

//METODO DE AGREGAR PLANILLERO
    public void insertar_Planillero(int identificacion,
            String nombre,
            String apellido,
            String apellido2,
            String correo_electronico,
            String contrasena) throws SNMPExceptions {
        
        try {
           // if (this.modelo.validarDatos() == true) {
             //   this.setMensaje("Debe completar los datos");
           // } else {
                   // if (Validacion()) {
                
         // this.mensajeError="Error";
if (this.modelo.getApellido().equals(" ")) {
                this.setMensajeError("Faltan datos");
        }    
                this.modelo.insertar_Planillero(new Usuario(identificacion, nombre, apellido, apellido2, correo_electronico, contrasena));
                this.setMensaje("Los datos se han guardado exitosamente como planillero");
           // }

 // }
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");            
        }
        
    }

//METODO DE AGREGAR PLANILLERO
    public void insertar_RecursosHumanos(int identificacion,
            String nombre,
            String apellido,
            String apellido2,
            String correo_electronico,
            String contrasena) throws SNMPExceptions {
        
        try {
            this.modelo.insertar_RecursosHumanos(new Usuario(identificacion, nombre, apellido, apellido2, correo_electronico, contrasena));
            this.setMensaje("Los datos se han guardado exitosamente como reclutador");
            
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }
        
    }
    
    public void limpiar() {
       this.setMensaje("");
        this.modelo.setIdentificacion(0);
        this.modelo.setNombre("");
        this.modelo.setApellido("");
        this.modelo.setApellido2("");
        this.modelo.setCorreo_electronico("");
        this.modelo.setContrasena("");
    }
    public void limpiarUsuario() {
       this.setMensaje("");
        this.usuario_a_modificar.setIdentificacion(0);
        this.usuario_a_modificar.setNombre("");
        this.usuario_a_modificar.setApellido("");
        this.usuario_a_modificar.setApellido2("");
        this.usuario_a_modificar.setCorreo_electronico("");
        this.usuario_a_modificar.setContrasena("");
    }
    
    public void desactivar_Usuario_Por_Identificacion(int identificacion) throws SNMPExceptions {
        try {
            this.modelo.desactivar_Usuario_Por_Identificacion(identificacion);
            this.setMensaje("Se ha desactivado correctamente");
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }
    }

    public void activar_Usuario_Por_Identificacion(int identificacion) throws SNMPExceptions {
        try {
            this.modelo.activar_Usuario_Por_Identificacion(identificacion);
            this.setMensaje("Se ha activado correctamente");
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }
    }
 public String validarDatos1() {
        if (this.modelo.getIdentificacion() == 0 || this.modelo.getNombre().equals("") ||  this.modelo.getApellido().equals("")) {
            return mensajeError = "Llenar datos";
        } else {
            return "";
        }
    }
    
    public void validarDatos() {
       /* if (this.modelo.validarDatos() == false) {
            return this.mensajeError = "Urgencia correctamente registrada ;)";
        } else {
            return this.mensajeError = "Debes llenar todos los campos ;)";
        }*/
this.validarDatos1();
    }

    public Usuario obtenerUsuarioActualizar(int ID) throws SNMPExceptions, SQLException {
        
        UsuarioDB logica = new UsuarioDB();
        return logica.retornarUsuario(ID);
    }

    public boolean Validacion() {
        UsuarioDB pdb = new UsuarioDB();
        boolean resp = true;
        this.mensajeError = "Error de validación \n";
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(this.getCorreo_electronico());
        if (String.valueOf(this.getIdentificacion()).equals(0)) {
            this.mensajeError += "Debe llenar el campo Identificación.";
            resp = false;
            return resp;
            
        } else if (this.getNombre().equals("")) {
            this.mensajeError += "Debe llenar el campo Nombre.";
            resp = false;
            return resp;
        } else if (this.getApellido().equals("")) {
            this.mensajeError += "Debe llenar el campo Primer Apellido.";
            resp = false;
            return resp;
        } else if (this.getApellido2().equals("")) {
            this.mensajeError += "Debe llenar el campo Segundo Apellido.";
            resp = false;
            return resp;
        } else if (this.getCorreo_electronico().equals("")) {
            this.mensajeError += "Debe agregar su correo en el campo definido.";
            resp = false;
            return resp;
        } else if (!mather.find()) {
            this.mensajeError += "Formato de correo inválido.";
            resp = false;
            return resp;
        }        
        mensajeError = "";
        return resp;
        
    }
    
    public void cargarDatos(Usuario Em) {
        this.setMensaje("");
        
        this.setIdentificacion(Em.getIdentificacion());
        this.setNombre(Em.getNombre());
        this.setApellido(Em.getApellido());
        this.setApellido2(Em.getApellido2());
        this.setEstado(Em.getEstado());
        this.setCorreo_electronico(Em.getCorreo_electronico());
        this.setContrasena(Em.getContrasena());
        this.setRolAdministrador(Em.getRolAdministrador());
        this.setRolPlanillero(Em.getRolPlanillero());
        this.setRolRecursosHumanos(Em.getRolRecursosHumanos());
        
    }

    public void obtenerDatos(Usuario per) throws SNMPExceptions, SQLException {
        this.setMensaje("");
        
        this.setIdentificacion(per.getIdentificacion());
        this.setNombre(per.getNombre());
        this.setApellido(per.getApellido());
        this.setApellido2(per.getApellido2());
        this.setEstado(per.getEstado());
        this.setCorreo_electronico(per.getCorreo_electronico());
        this.setContrasena(per.getContrasena());
        this.setRolAdministrador(per.getRolAdministrador());
        this.setRolPlanillero(per.getRolPlanillero());
        this.setRolRecursosHumanos(per.getRolRecursosHumanos());
//
        usuario_a_modificar = obtenerUsuarioActualizar(this.getIdentificacion());
    }
    
    public void actualizar_Usuario(Usuario usuario_a_modificar_frente) throws SNMPExceptions {
        
        try {
            UsuarioDB usuario = new UsuarioDB();
            int cedula = usuario_a_modificar_frente.getIdentificacion();
            String nombre = usuario_a_modificar_frente.getNombre();
            usuario.actualizar_Usuario(usuario_a_modificar_frente);
            this.setMensaje("Los datos se han actualizado exitosamente");
            
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema en actualizar!");
        }
    }
}
