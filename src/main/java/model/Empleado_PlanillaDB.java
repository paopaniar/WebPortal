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
public class Empleado_PlanillaDB {

    private AccesoDatos accesoDatos = new AccesoDatos();
    private Connection conn;

    public Empleado_PlanillaDB() {
        accesoDatos = new AccesoDatos();
        accesoDatos.setDbConn(conn);
    }

 
  public LinkedList<Empleado_Planilla> obtener_Beneficios_Empleado_por_Planilla(int empleado, int planilla) throws SNMPExceptions {
        String strSQL = "";
        LinkedList<Empleado_Planilla> listaPro = new LinkedList<Empleado_Planilla>();

        try {

            //Se instancia la clase de acceso a datos
            strSQL
                    = "EXEC sp_Obtener_Beneficios_Empleado_por_Planilla " + empleado + " ," + planilla + ";";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {
                String acronimo = rsPA.getString("acronimo");
                String descripcion_beneficios = rsPA.getString("descripcion_beneficios");
                float porcentaje_beneficios = rsPA.getFloat("porcentaje_beneficios");
                int pagaEmpleado = rsPA.getInt("pagaEmpleado");
                int proceso = rsPA.getInt("proceso");
                BigDecimal monto_a_reducir_Beneficios = rsPA.getBigDecimal("monto_a_reducir");

                Empleado_Planilla oEmpleadoPlanilla = new Empleado_Planilla();
                oEmpleadoPlanilla.setEmpleado(empleado);
                oEmpleadoPlanilla.setPlanilla(planilla);
                oEmpleadoPlanilla.setAcronimo(acronimo);
                oEmpleadoPlanilla.setDescripcion_beneficios(descripcion_beneficios);
                oEmpleadoPlanilla.setPorcentaje_beneficios(porcentaje_beneficios);
                oEmpleadoPlanilla.setPagaEmpleado(pagaEmpleado);
                oEmpleadoPlanilla.setProceso(proceso);
                oEmpleadoPlanilla.setMonto_a_reducir_Beneficios(monto_a_reducir_Beneficios);

                listaPro.add(oEmpleadoPlanilla);
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


  public LinkedList<Empleado_Planilla> obtener_renta_Empleado_por_Planilla(int empleado, int planilla) throws SNMPExceptions {
        String strSQL = "";
        LinkedList<Empleado_Planilla> listaPro = new LinkedList<Empleado_Planilla>();

        try {

            //Se instancia la clase de acceso a datos
            strSQL
                    = "EXEC sp_Obtener_renta_Empleado_por_Planilla " + empleado + " ," + planilla + ";";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {
                String descripcion_renta = rsPA.getString("descripcion_renta");
                float porcentaje_deduccion = rsPA.getFloat("porcentaje_deduccion");
                int aplica = rsPA.getInt("aplica");
                BigDecimal monto_a_reducir = rsPA.getBigDecimal("monto_a_reducir");

                Empleado_Planilla oEmpleadoPlanilla = new Empleado_Planilla();
                oEmpleadoPlanilla.setEmpleado(empleado);
                oEmpleadoPlanilla.setPlanilla(planilla);
                oEmpleadoPlanilla.setDescripcion_renta(descripcion_renta);
                oEmpleadoPlanilla.setPorcentaje_deduccion(porcentaje_deduccion);
                oEmpleadoPlanilla.setAplica(aplica);
                oEmpleadoPlanilla.setMonto_a_reducir_Renta(monto_a_reducir);
               

                listaPro.add(oEmpleadoPlanilla);
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




    public LinkedList<Empleado_Planilla> obtener_Categoria_Pago_Empleado_por_Planilla(int empleado, int planilla) throws SNMPExceptions {
        String strSQL = "";
        LinkedList<Empleado_Planilla> listaPro = new LinkedList<Empleado_Planilla>();

        try {

            //Se instancia la clase de acceso a datos
            strSQL
                    = "EXEC sp_Obtener_Categoria_Pago_Empleado_por_Planilla " + empleado + " ," + planilla + ";";

            //Se ejecuta la sentencia SQL
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //Se llena el arryaList con los proyectos
            while (rsPA.next()) {
                String descripcion_categoria = rsPA.getString("descripcion_categoria");
                float porcentaje_categoria = rsPA.getFloat("porcentaje_categoria");
                int cantidadhoras = rsPA.getInt("cantidadhoras");
                BigDecimal monto_a_pagar = rsPA.getBigDecimal("monto_a_pagar");

                Empleado_Planilla oEmpleadoPlanilla = new Empleado_Planilla();
                oEmpleadoPlanilla.setEmpleado(empleado);
                oEmpleadoPlanilla.setPlanilla(planilla);
             
                oEmpleadoPlanilla.setDescripcion_categoria(descripcion_categoria);
                oEmpleadoPlanilla.setPorcentaje_categoria(porcentaje_categoria);
                oEmpleadoPlanilla.setCantidadhoras(cantidadhoras);
              
                oEmpleadoPlanilla.setMonto_a_pagar_Categoria(monto_a_pagar);

                listaPro.add(oEmpleadoPlanilla);
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

    public BigDecimal montoTotalBeneficios(int empleado, int numeroDEplanilla) throws SNMPExceptions,
            SQLException {
        String select = "";
        BigDecimal monto_beneficios = null;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "select monto_beneficios from monto_beneficios where empleado =" + empleado + "and planilla=" + numeroDEplanilla + " ;";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {
                    monto_beneficios = rsPA.getBigDecimal("monto_beneficios");

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
        return monto_beneficios;
    }

public BigDecimal montoTotalCategoria(int empleado, int numeroDEplanilla) throws SNMPExceptions,
            SQLException {
        String select = "";
        BigDecimal monto_categoria = null;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "select monto_categoria_pago from monto_categoria_pago where empleado =" + empleado + " and planilla=" + numeroDEplanilla + " ;";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {
                    monto_categoria = rsPA.getBigDecimal("monto_categoria_pago");

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
        return monto_categoria;
    }


    public Empleado_Planilla retornar(int empleado, int numeroDEplanilla) throws SNMPExceptions,
            SQLException {
        String select = "";

        Empleado oEmpla = new Empleado();
        Planilla oPlanilla = new Planilla();
        Empleado_Planilla oEmpleadoPlanill = new Empleado_Planilla();
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "EXEC sp_Obtener_Empleados_Por_Planilla " + empleado + "," + numeroDEplanilla + ";";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {

                    oEmpla.setIdentificacion(empleado);
                    oPlanilla.setNumero(numeroDEplanilla);
                    oEmpleadoPlanill.setEmpleado(oEmpla.getIdentificacion());
                    oEmpleadoPlanill.setPlanilla(oPlanilla.getNumero());

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
        return oEmpleadoPlanill;
    }

public void actualizar_Beneficios2(int planilla, int empleado) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL = "EXEC sp_Actualizar_Beneficios " + planilla +" , " + empleado + ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
 public void actualizar_Beneficios(Empleado_Planilla empleadoPln) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Actualizar_Beneficios " + empleadoPln.getEmpleado()+ ", "
                    + empleadoPln.getPlanilla()+ ", '"
                    + empleadoPln.getAcronimo()+ "', '"
                    + empleadoPln.getDescripcion_beneficios()+ "', "
                    + empleadoPln.getPorcentaje_beneficios()+ " , "
                    + empleadoPln.getPagaEmpleado()+ ", "
                    + empleadoPln.getProceso()+ ";";

//Se ejecuta la sentencia SQL
            accesoDatos.ejecutaSQL(strSQL);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }

public BigDecimal montoSalario_bruto(int empleado, int numeroDEplanilla) throws SNMPExceptions,
            SQLException {
        String select = "";
        BigDecimal salario_bruto = null;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT salario_bruto FROM salario_bruto WHERE empleado =" + empleado + "and planilla=" + numeroDEplanilla + " ;";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {
                    salario_bruto = rsPA.getBigDecimal("salario_bruto");

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
        return salario_bruto;
    }


public BigDecimal montoSalario_neto(int empleado, int numeroDEplanilla) throws SNMPExceptions,
            SQLException {
        String select = "";
        BigDecimal salario_neto = null;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT salario_neto FROM salario_neto WHERE empleado =" + empleado + "and planilla=" + numeroDEplanilla + " ;";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {
                    salario_neto = rsPA.getBigDecimal("salario_neto");

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
        return salario_neto;
    }








public BigDecimal montoSalarioxHora(int empleado, int numeroDEplanilla) throws SNMPExceptions,
            SQLException {
        String select = "";
        BigDecimal salarioxHora = null;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT SalarioxHora FROM salarioxHora_Empleado_Planilla WHERE empleado =" + empleado + "and planilla=" + numeroDEplanilla + " ;";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {
                    salarioxHora = rsPA.getBigDecimal("SalarioxHora");

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
        return salarioxHora;
    }


public BigDecimal montoTotalRenta(int empleado, int numeroDEplanilla) throws SNMPExceptions,
            SQLException {
        String select = "";
        BigDecimal monto_renta = null;
        try {

            //Se crea la sentencia de búsqueda
            select
                    = "SELECT monto_renta FROM monto_renta WHERE  empleado =" + empleado + "and planilla=" + numeroDEplanilla + " ;";
            //Se llena el arryaList con los proyectos
            try ( //Se ejecuta la sentencia SQL
                    ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(select)) {
                //Se llena el arryaList con los proyectos
                while (rsPA.next()) {
                    monto_renta = rsPA.getBigDecimal("monto_renta");

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
        return monto_renta;
    }

  public Empleado_Planilla retornarBeneficios(int empleado, int planilla, String acronimo1) throws SNMPExceptions, SQLException {
      
  Empleado_Planilla oEmpleadoPlanilla = new Empleado_Planilla();
        String strSQL = "";
        try {
            //Se intancia la clase de acceso a datos
           

            //Se crea la sentencia de Busqueda
            strSQL = "select * from beneficios where empleado ="+empleado+" and planilla = "+planilla+" and acronimo='"+acronimo1+"';";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            if (rsPA.next()) {

                 String acronimo = rsPA.getString("acronimo");
                String descripcion_beneficios = rsPA.getString("descripcion_beneficios");
                float porcentaje_beneficios = rsPA.getFloat("porcentaje_beneficios");
                int pagaEmpleado = rsPA.getInt("pagaEmpleado");
                int proceso = rsPA.getInt("proceso");
                BigDecimal monto_a_reducir_Beneficios = rsPA.getBigDecimal("monto_a_reducir");

              
                oEmpleadoPlanilla.setEmpleado(empleado);
                oEmpleadoPlanilla.setPlanilla(planilla);
                oEmpleadoPlanilla.setAcronimo(acronimo);
                oEmpleadoPlanilla.setDescripcion_beneficios(descripcion_beneficios);
                oEmpleadoPlanilla.setPorcentaje_beneficios(porcentaje_beneficios);
                oEmpleadoPlanilla.setPagaEmpleado(pagaEmpleado);
                oEmpleadoPlanilla.setProceso(proceso);
                oEmpleadoPlanilla.setMonto_a_reducir_Beneficios(monto_a_reducir_Beneficios);

            }

            rsPA.close();

            return oEmpleadoPlanilla;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
  public Empleado_Planilla retornarCategorias(int empleado, int planilla, String descripcion) throws SNMPExceptions, SQLException {
      
  Empleado_Planilla oEmpleadoPlanilla = new Empleado_Planilla();
        String strSQL = "";
        try {
           
            //Se crea la sentencia de Busqueda
            strSQL = "select * from categoria_pago where empleado ="+empleado+" and planilla = "+planilla+" and descripcion_categoria= '"+descripcion+"';";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            if (rsPA.next()) {

               String descripcion_categoria = rsPA.getString("descripcion_categoria");
                float porcentaje_categoria = rsPA.getFloat("porcentaje_categoria");
                int cantidadhoras = rsPA.getInt("cantidadhoras");
                BigDecimal monto_a_pagar = rsPA.getBigDecimal("monto_a_pagar");
                
              
                oEmpleadoPlanilla.setEmpleado(empleado);
                oEmpleadoPlanilla.setPlanilla(planilla);
                oEmpleadoPlanilla.setDescripcion_categoria(descripcion_categoria);
                oEmpleadoPlanilla.setPorcentaje_categoria(porcentaje_categoria);
                oEmpleadoPlanilla.setCantidadhoras(cantidadhoras);
                oEmpleadoPlanilla.setMonto_a_pagar_Categoria(monto_a_pagar);

            }

            rsPA.close();

            return oEmpleadoPlanilla;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
}
  public Empleado_Planilla retornarRentas(int planilla, int empleado) throws SNMPExceptions, SQLException {
      
  Empleado_Planilla oEmpleadoPlanilla = new Empleado_Planilla();
        String strSQL = "";
        try {
            //Se intancia la clase de acceso a datos
            AccesoDatos accesoDatos = new AccesoDatos();

            //Se crea la sentencia de Busqueda
            strSQL = "EXEC sp_Obtener_renta_Empleado_por_Planilla " + planilla + " , "+ empleado + ";";

            //se ejecuta la sentencia sql
            ResultSet rsPA = accesoDatos.ejecutaSQLRetornaRS(strSQL);
            //se llama el array con los proyectos
            if (rsPA.next()) {

               String descripcion_renta = rsPA.getString("descripcion_renta");
                float porcentaje_deduccion = rsPA.getFloat("porcentaje_deduccion");
                int aplica = rsPA.getInt("aplica");
                BigDecimal monto_a_reducir = rsPA.getBigDecimal("monto_a_reducir");
                
              
                oEmpleadoPlanilla.setEmpleado(empleado);
                oEmpleadoPlanilla.setPlanilla(planilla);
                oEmpleadoPlanilla.setDescripcion_renta(descripcion_renta);
                oEmpleadoPlanilla.setPorcentaje_beneficios(porcentaje_deduccion);
                oEmpleadoPlanilla.setAplica(aplica);
                oEmpleadoPlanilla.setMonto_a_reducir_Beneficios(monto_a_reducir);

            }

            rsPA.close();

            return oEmpleadoPlanilla;

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        } catch (Exception e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        } finally {

        }
    }
 public void actualizar_Categorias(Empleado_Planilla empleadoPln) throws SNMPExceptions {
        String strSQL = "";
        try {

            strSQL
                    = "EXEC sp_Actualizar_Cantidad_De_Horas_En_Categoria_De_Pago " + empleadoPln.getEmpleado()+ ", "
                    + empleadoPln.getPlanilla()+ ", '"
                    + empleadoPln.getDescripcion_categoria()+ "',  "
                    + empleadoPln.getCantidadhoras()+ ";";

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
