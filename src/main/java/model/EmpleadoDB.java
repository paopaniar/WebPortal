/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author sa
 */
public class EmpleadoDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public EmpleadoDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void insertar_Empleado_Semanal(Empleado empleado) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Insertar_Empleado_Semanal " + empleado.getIdentificacion() + ", '"
                    + empleado.getNombre() + "', '"
                    + empleado.getApellido() + "', '"
                    + empleado.getApellido2() + "', '"
                    + empleado.getTipo_jornada() + "', '"
                    + empleado.getDescripcion_puesto() + "', "
                    + empleado.getSalarioXhora() + ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void insertar_Empleado_Quincenal(Empleado empleado) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Insertar_Empleado_Quincenal " + empleado.getIdentificacion() + ", '"
                    + empleado.getNombre() + "', '"
                    + empleado.getApellido() + "', '"
                    + empleado.getApellido2() + "', '"
                    + empleado.getTipo_jornada() + "', '"
                    + empleado.getDescripcion_puesto() + "', "
                    + empleado.getSalarioXhora() + ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void Desactivar_Empleado_Por_Identificacion(int identificacion) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Desactivar_Empleado_Por_Identificacion " + identificacion + ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public void activar_Empleado_Por_Identificacion(int identificacion) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Activar_Empleado_Por_Identificacion " + identificacion + ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public LinkedList<Empleado> obtener_Todos_Los_Empleados() throws SNMPExceptions {
        String strSQL = "";
        LinkedList<Empleado> listaPro = new LinkedList<Empleado>();

        try {

            //Se instancia la clase de acceso a datos
            strSQL
                    = "EXEC sp_Obtener_Todos_Los_Empleados;";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                String nombre = rsPA.getString("nombre");
                String apellido = rsPA.getString("apellido");
                String apellido2 = rsPA.getString("apellido2");
                String descripcion_puesto = rsPA.getString("descripcion_puesto");
                String tipo_jornada = rsPA.getString("tipo_jornada");
                float salarioXhora = rsPA.getFloat("salarioXhora");
                BigDecimal salario_bruto_actual = rsPA.getBigDecimal("salario_bruto_actual");
                int semanal = rsPA.getInt("semanal");
                int quincenal = rsPA.getInt("quincenal");
                int estado = rsPA.getInt("estado");
                Empleado oEmpleado = new Empleado();
                oEmpleado.setIdentificacion(identificacion);
                oEmpleado.setNombre(nombre);
                oEmpleado.setApellido(apellido);
                oEmpleado.setApellido2(apellido2);
                oEmpleado.setDescripcion_puesto(descripcion_puesto);
                oEmpleado.setTipo_jornada(tipo_jornada);
                oEmpleado.setSalarioXhora(salarioXhora);
                oEmpleado.setSalario_bruto_actual(salario_bruto_actual);
                oEmpleado.setSemanal(semanal);
                oEmpleado.setQuincenal(quincenal);
                oEmpleado.setEstado(estado);
                listaPro.add(oEmpleado);
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

    public LinkedList<Empleado> obtener_Todos_Los_Empleados_Desactivados() throws SNMPExceptions {
        String strSQL = "";
        LinkedList<Empleado> listaPro = new LinkedList<Empleado>();

        try {

            //Se instancia la clase de acceso a datos
            strSQL
                    = "EXEC sp_Obtener_Todos_Los_Empleados_Desactivados;";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                String nombre = rsPA.getString("nombre");
                String apellido = rsPA.getString("apellido");
                String apellido2 = rsPA.getString("apellido2");
                String tipo_jornada = rsPA.getString("tipo_jornada");
                String descripcion_puesto = rsPA.getString("descripcion_puesto");
                float salarioXhora = rsPA.getFloat("salarioXhora");
                int semanal = rsPA.getInt("semanal");
                int quincenal = rsPA.getInt("quincenal");
                int estado = rsPA.getInt("estado");
                Empleado oEmpleado = new Empleado();
                oEmpleado.setIdentificacion(identificacion);
                oEmpleado.setNombre(nombre);
                oEmpleado.setApellido(apellido);
                oEmpleado.setApellido2(apellido2);
                oEmpleado.setDescripcion_puesto(descripcion_puesto);
                oEmpleado.setTipo_jornada(tipo_jornada);
                oEmpleado.setSalarioXhora(salarioXhora);
                oEmpleado.setSemanal(semanal);
                oEmpleado.setQuincenal(quincenal);
                oEmpleado.setEstado(estado);
                listaPro.add(oEmpleado);
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

    public LinkedList<Integer> obtener_Identificacion_De_Empleados() throws SNMPExceptions {
        String strSQL = "";
        LinkedList<Integer> listaIdentificaciones = new LinkedList<Integer>();

        try {

            //Se instancia la clase de acceso a datos
            strSQL
                    = "EXEC sp_Obtener_Identificacion_De_Empleados;";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {
                listaIdentificaciones.add(rsPA.getInt("identificacion"));
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
        return listaIdentificaciones;
    }

    public void actualizar_Empleado(Empleado empleado_a_modificar_frente) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC  sp_Actualizar_Empleado " + empleado_a_modificar_frente.getIdentificacion() + ", '"
                    + empleado_a_modificar_frente.getNombre()+ "', '"
                    + empleado_a_modificar_frente.getApellido()+ "', '"
                    + empleado_a_modificar_frente.getApellido2()+ "', '"
                    + empleado_a_modificar_frente.getTipo_jornada() + "' , '"
                    + empleado_a_modificar_frente.getDescripcion_puesto()+ "', "
                    + empleado_a_modificar_frente.getSemanal()+ " , "
                    + empleado_a_modificar_frente.getQuincenal()+ ", "
                    + empleado_a_modificar_frente.getSalarioXhora()+ " ,"
                    + empleado_a_modificar_frente.getEstado()+ ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public Empleado retornarEmpleado(int ID) throws SNMPExceptions, SQLException {
        Empleado oEmpleado = new Empleado();

        String strSQL = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            strSQL = "EXEC sp_Obtener_Empleado_Por_Identificacion " + ID + ";";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            if (rsPA.next()) {

                int identificacion = rsPA.getInt("identificacion");
                String nombre = rsPA.getString("nombre");
                String apellido = rsPA.getString("apellido");
                String apellido2 = rsPA.getString("apellido2");
                String tipo_jornada = rsPA.getString("tipo_jornada");
                String descripcion_puesto = rsPA.getString("descripcion_puesto");
                int semanal = rsPA.getInt("semanal");
                int quincenal = rsPA.getInt("quincenal");
                float salarioXhora = rsPA.getFloat("salarioXhora");
                BigDecimal salario_bruto_actual = rsPA.getBigDecimal("salario_bruto_actual");
                int estado = rsPA.getInt("estado");

                oEmpleado.setIdentificacion(identificacion);
                oEmpleado.setNombre(nombre);
                oEmpleado.setApellido(apellido);
                oEmpleado.setApellido2(apellido2);
                oEmpleado.setDescripcion_puesto(descripcion_puesto);
                oEmpleado.setTipo_jornada(tipo_jornada);
                oEmpleado.setSalarioXhora(salarioXhora);
                oEmpleado.setSalario_bruto_actual(salario_bruto_actual);
                oEmpleado.setSemanal(semanal);
                oEmpleado.setQuincenal(quincenal);
                oEmpleado.setEstado(estado);

            }

            rsPA.close();

            return oEmpleado;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

}
