    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author sa
 */
public class UsuarioDB {
    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public UsuarioDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

 public Usuario buscarUsuarioPorIdentificacion(int identificacion) throws SNMPExceptions,
            SQLException {
        String select = "";
        Usuario oUsuario = null;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "EXEC sp_Usuario_Por_Identificacion "+identificacion+";";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {

                    oUsuario = new Usuario(
                            rsPA.getInt("identificacion"),
                            
                            rsPA.getString("nombre"),
                            rsPA.getString("apellido"),
                            rsPA.getString("apellido2"),

                            rsPA.getString("correo_electronico"),
                            rsPA.getString("contrasena"),
                        
                            rsPA.getInt("rolAdministrador"),
                            rsPA.getInt("rolPlanillero"),
                            rsPA.getInt("rolRecursosHumanos"));

                }
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return oUsuario;
    }


public int obtenerIdentificacionEnLogin(String correo_electronico,String contrasena) throws SNMPExceptions,
            SQLException {
        String select = "";
        int identificacion = 0;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "EXEC sp_Validar '"+correo_electronico+"' ,'"+contrasena+"';";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {

                    identificacion =
                            rsPA.getInt("usuario");
                            
                            
                }
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return identificacion;
    }


//METODOS DE ADMINISTRADOR
public void insertar_Administrador(Usuario usuario) throws SNMPExceptions{
String strSQL = "";
        try {
            
            strSQL
                    = "EXEC sp_Insertar_Administrador "+usuario.getIdentificacion()+", '"+
                                                        usuario.getNombre()+"', '"+
                                                        usuario.getApellido()+"', '"+
                                                        usuario.getApellido2()+"', '"+
                                                        usuario.getCorreo_electronico()+"', '"+
                                                        usuario.getContrasena()+"';";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
}
public void insertar_Planillero(Usuario usuario) throws SNMPExceptions{
String strSQL = "";
        try {
            
            strSQL
                    = "EXEC sp_Insertar_Planillero "+usuario.getIdentificacion()+", '"+
                                                        usuario.getNombre()+"', '"+
                                                        usuario.getApellido()+"', '"+
                                                        usuario.getApellido2()+"', '"+
                                                        usuario.getCorreo_electronico()+"', '"+
                                                        usuario.getContrasena()+"';";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
}
public void insertar_RecursosHumanos(Usuario usuario) throws SNMPExceptions{
String strSQL = "";
        try {
            
            strSQL
                    = "EXEC sp_Insertar_Recursos_Humanos "+usuario.getIdentificacion()+", '"+
                                                        usuario.getNombre()+"', '"+
                                                        usuario.getApellido()+"', '"+
                                                        usuario.getApellido2()+"', '"+
                                                        usuario.getCorreo_electronico()+"', '"+
                                                        usuario.getContrasena()+"';";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
                throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
            } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
}
public void Desactivar_Usuario_Por_Identificacion(int identificacion) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Desactivar_Usuario_Por_Identificacion " + identificacion +";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }


public LinkedList<Usuario> obtener_Todos_Los_Usuarios() throws SNMPExceptions {
        String  strSQL = "";
        LinkedList<Usuario> listaPro = new LinkedList<Usuario>();

        try {

            //Se instancia la clase de acceso a datos
           strSQL

           
                    = "EXEC sp_Obtener_Todos_Los_Usuarios;"; 
                    
                    
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {

int identificacion = rsPA.getInt("identificacion");
String nombre= rsPA.getString("nombre");
String apellido=  rsPA.getString("apellido");
String apellido2 =  rsPA.getString("apellido2");
String correo_electronico = rsPA.getString("correo_electronico");
int estado= rsPA.getInt("estado");
String contrasena = rsPA.getString("contrasena");
int rolAdministrador = rsPA.getInt("rolAdministrador");
int rolPlanillero = rsPA.getInt("rolPlanillero");
int rolRecursosHumanos = rsPA.getInt("rolRecursosHumanos");
Usuario oUsuario = new Usuario();
oUsuario.setIdentificacion(identificacion);
oUsuario.setNombre(nombre);
oUsuario.setApellido(apellido);
oUsuario.setApellido2(apellido2);
oUsuario.setCorreo_electronico(correo_electronico);
oUsuario.setEstado(estado);
oUsuario.setContrasena(contrasena);
oUsuario.setRolAdministrador(rolAdministrador);
oUsuario.setRolPlanillero(rolPlanillero);
oUsuario.setRolRecursosHumanos(rolRecursosHumanos);
      listaPro.add(oUsuario);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaPro;
    }
public void actualizar_Usuario(Usuario usuario_a_modificar_frente) throws SNMPExceptions{
String strSQL = "";
        try {

            
            strSQL
                    = "EXEC  sp_Actualizar_Usuario "+usuario_a_modificar_frente.getIdentificacion()
                                                    +",'"+ usuario_a_modificar_frente.getNombre()+"',"
                                                    + "'"+  usuario_a_modificar_frente.getApellido()+ "',"
                                                    + "'"+ usuario_a_modificar_frente.getApellido2()
                                                    +"','"+ usuario_a_modificar_frente.getCorreo_electronico()+"',"
                                                    + "'"+ usuario_a_modificar_frente.getContrasena()+ "',"
                                                    + usuario_a_modificar_frente.getRolAdministrador()
                                                    + ","+ usuario_a_modificar_frente.getRolPlanillero()+ ","
                                                    + usuario_a_modificar_frente.getRolRecursosHumanos()+ ","
                                                    + usuario_a_modificar_frente.getEstado()+ ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
                throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
            } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
}
 public boolean consultarUsuario(int ID)
            throws SNMPExceptions, SQLException {

        boolean existe = false;
        String strSQL = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
             strSQL = "EXEC sp_Usuario_Por_Identificacion "+ ID+";";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            if (rsPA.next()) {

                existe = true;
            }

            rsPA.close();

            return existe;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }

    }
   public Usuario retornarUsuario(int ID) throws SNMPExceptions, SQLException {
        Usuario oUsuario = new Usuario();

        String strSQL = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
           strSQL = "EXEC sp_Usuario_Por_Identificacion "+ ID +";";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            if (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
String nombre= rsPA.getString("nombre");
String apellido=  rsPA.getString("apellido");
String apellido2 =  rsPA.getString("apellido2");
String correo_electronico = rsPA.getString("correo_electronico");
int estado= rsPA.getInt("estado");
String contrasena = rsPA.getString("contrasena");
int rolAdministrador = rsPA.getInt("rolAdministrador");
int rolPlanillero = rsPA.getInt("rolPlanillero");
int rolRecursosHumanos = rsPA.getInt("rolRecursosHumanos");


oUsuario.setIdentificacion(identificacion);
oUsuario.setNombre(nombre);
oUsuario.setApellido(apellido);
oUsuario.setApellido2(apellido2);
oUsuario.setCorreo_electronico(correo_electronico);
oUsuario.setEstado(estado);
oUsuario.setContrasena(contrasena);
oUsuario.setRolAdministrador(rolAdministrador);
oUsuario.setRolPlanillero(rolPlanillero);
oUsuario.setRolRecursosHumanos(rolRecursosHumanos);

            }

            rsPA.close();

            return oUsuario;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
public void Activar_Usuario_Por_Identificacion(int identificacion) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Activar_Usuario_Por_Identificacion " + identificacion +";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }


public LinkedList<Usuario> obtener_Todos_Los_Usuarios_Desactivados() throws SNMPExceptions {
        String  strSQL = "";
        LinkedList<Usuario> listaPro = new LinkedList<Usuario>();
  Usuario oUsuario = new Usuario();

        try {

            //Se instancia la clase de acceso a datos
           strSQL

           
                    = "EXEC sp_Obtener_Todos_Los_Usuarios_Desactivados;"; 
                    
                    
            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {


                int identificacion = rsPA.getInt("identificacion");
String nombre= rsPA.getString("nombre");
String apellido=  rsPA.getString("apellido");
String apellido2 =  rsPA.getString("apellido2");
String correo_electronico = rsPA.getString("correo_electronico");
int estado= rsPA.getInt("estado");
String contrasena = rsPA.getString("contrasena");
int rolAdministrador = rsPA.getInt("rolAdministrador");
int rolPlanillero = rsPA.getInt("rolPlanillero");
int rolRecursosHumanos = rsPA.getInt("rolRecursosHumanos");


oUsuario.setIdentificacion(identificacion);
oUsuario.setNombre(nombre);
oUsuario.setApellido(apellido);
oUsuario.setApellido2(apellido2);
oUsuario.setCorreo_electronico(correo_electronico);
oUsuario.setEstado(estado);
oUsuario.setContrasena(contrasena);
oUsuario.setRolAdministrador(rolAdministrador);
oUsuario.setRolPlanillero(rolPlanillero);
oUsuario.setRolRecursosHumanos(rolRecursosHumanos);
      listaPro.add(oUsuario);
            }
            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        } finally {

        }
        return listaPro;
    }

  public boolean ExisteUsuario(int id) throws SNMPExceptions, SQLException {
        boolean existe = false;
        String select = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            select = "select * from Usuario where identificacion = " + id;

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select);
            //se llama el array con los proyectos
            if (rsPA.next()) {

                existe = true;
            }

            rsPA.close();

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
        return existe;
    }


}
