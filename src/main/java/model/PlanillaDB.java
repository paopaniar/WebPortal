/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import com.sun.javafx.binding.StringFormatter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

/**
 *
 * @author sa
 */
public class PlanillaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public PlanillaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

    public void insertar_Plantilla(int identificacion_usuario) throws SNMPExceptions {
        String strSQL = "";
        int numero_planilla = 0;
        try {

            strSQL
                    = "EXEC sp_Insertar_Plantilla " + identificacion_usuario + ";";

//Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            while (rsPA.next()) {
                if ((rsPA.getObject("numero_planilla") != null && !rsPA.wasNull())) {
                    numero_planilla = rsPA.getInt("numero_planilla");
                }
            }
            insertar_Empleados_En_Plantilla(numero_planilla);
            rsPA.close();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
 public LinkedList<Empleado> getObtener_Empleados_Por_Planilla(int numeroDEplanilla) throws SNMPExceptions {
 String  strSQL = "";
        LinkedList<Empleado> listaPro = new LinkedList<Empleado>();

        try {

            //Se instancia la clase de acceso a datos
           strSQL

           
                    = "EXEC sp_Obtener_Empleados_Por_Planilla "+numeroDEplanilla+";"; 
                    
                    
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
                BigDecimal salario_bruto_actual =rsPA.getBigDecimal("salario_bruto_actual");
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
    public void insertar_Empleados_En_Plantilla(int numero_planilla) throws SNMPExceptions {
        try {
            String strSQL = "";
            EmpleadoDB eDB = new EmpleadoDB();
            LinkedList<Integer> lista_de_cedulas = eDB.obtener_Identificacion_De_Empleados();

           for(int cedula:lista_de_cedulas){

            strSQL= "insert into empleado_planilla (empleado,planilla) values ("+cedula+","+numero_planilla+");";
            accesoDatos.ejecutaSQL(strSQL);
            }


        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

    public LinkedList<Planilla> obtener_Plantilla() throws SNMPExceptions {

        String strSQL = "";
        LinkedList<Planilla> listaPro = new LinkedList<Planilla>();

        try {

            //Se instancia la clase de acceso a datos
            strSQL
                    = "EXEC sp_Obtener_Todas_Las_Planillas;";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {

                int numero = rsPA.getInt("numero");
                int identificacion_usuario = rsPA.getInt("usuario");
                int estado_pagado = rsPA.getInt("estado_pagado");
              Date fechaCreacion = rsPA.getDate("fecha_creacion");
               Date fechaPago = rsPA.getDate("fecha_pago");
             //LocalDateTIme fechaCreacion = rsPA.getDate("fecha_creacion");

BigDecimal monto_bruto_a_pagar = rsPA.getBigDecimal("monto_bruto_a_pagar");


                /*Date x = rsPA.getDate("fecha_creacion");
LocalDateTime fecha_pago = LocalDateTime.ofInstant(x.toInstant(), ZoneId.systemDefault());*/
                Planilla oPlanilla = new Planilla();
                oPlanilla.setNumero(numero);
                oPlanilla.setIdentificacion_usuario(identificacion_usuario);
                oPlanilla.setEstado_pagado(estado_pagado);
                oPlanilla.setMonto_bruto_a_pagar(monto_bruto_a_pagar);
//oPlanilla.setFecha_pago(fecha_pago);
 //               oPlanilla.setFecha1(fechaCreacion);
                 oPlanilla.setFecha_creacion(fechaCreacion);
oPlanilla.setFecha_pago(fechaPago);
                listaPro.add(oPlanilla);

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
public void actualizarPagoPlanilla(int numero) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL = "Update pago_planilla set estado_pagado = 1 where planilla = "+numero+" ;";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

}
