/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.SNMPExceptions;
import java.io.IOException;
import java.sql.SQLException;
import javax.faces.context.FacesContext;
import model.Empleado;
import model.Empleado_Planilla;
import model.Planilla;
import model.Usuario;

/**
 *
 * @author sa
 */
public class BeanNavegacion {
//Seccion de menu navegacion de MENU ADMINISTRADOR

    public void admnFront() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/admnFront.xhtml");
    }
    public void admnNuevoEmpleado() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/AgregarNuevo.xhtml");
    }
    public void admnActualizarEmpleado(Empleado em) throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/actualizarEmpleado.xhtml");
    }
    public void admnNuevaPlanilla() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/CrearNuevaPlanilla.xhtml");
    }
    
 
//Seccion de menu navegacion de MENU PLANILLERO

    public void plnFront() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/plnFront.xhtml");
    }
//Seccion de menu navegacion de MENU RECURSOS HUMANOS

    public void hrFront() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../recursoshumanos/AgregarNuevo.xhtml");
    }
//Seccion de menu navegacioln de INGRESO LOG IN
public void ingresoFront() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../.../ingresoFront.xhtml");
    }
public void actualizarEmpleado(Empleado em) throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../recursoshumanos/actualizarEmpleado.xhtml");
    }
public void crearNuevaPlanilla() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/CrearNuevaPlanilla.xhtml");
    }
public void reporteUsuarios() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/ReporteUsuarios.xhtml");
    }
public void reporteEmpleados() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/reportesRecursos.xhtml");
    }
public void reportePlanillas() throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/Reportes.xhtml");
    }

public void verEmpleadoXplanilla(Usuario user) throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/verEmpleadoXplanilla.xhtml");
    }
public void admVerEmpleadoXplanilla(Usuario user) throws SNMPExceptions, SQLException, IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/verEmpleadoXplanilla.xhtml");
    }
public void actualizarUsuario(Usuario usuario) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/actualizarUsuario.xhtml");
    }

public void empleadoPlanillero(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/empleadoPlanillero.xhtml");
    }
public void admImprimirEmpleadoPlanilla(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/imprimirEmpleadoPlanilla.xhtml");
    }
public void imprimirEmpleadoPlanilla(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/imprimirEmpleadoPlanilla.xhtml");
    }

public void admEmpleadoPlanillero(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/empleadoPlanillero.xhtml");
    }
public void admBeneficio(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/actualizarBeneficio.xhtml");
    }
public void plnBeneficio(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/actualizarBeneficio.xhtml");
    }
public void admCategoria(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/actualizarCategoria.xhtml");
    }
public void plnCategoria(Empleado_Planilla empleadoPlanilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/actualizarCategoria.xhtml");
    }
public void pagoPlanilla(Planilla planilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../planillero/pagoPlanilla.xhtml");
    }
public void admPagoPlanilla(Planilla planilla) throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/pagoPlanilla.xhtml");
    }
public void admReporteUsuariosActivos() throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/ReporteUsuariosActivos.xhtml");
    }
public void admReporteUsuariosDesactivos() throws SNMPExceptions, SQLException, IOException {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../administrator/ReporteUsuariosDesactivos.xhtml");
    }
}
