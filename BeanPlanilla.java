/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.SNMPExceptions;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.LinkedList;
import model.Empleado;
import model.EmpleadoDB;
import model.Empleado_Planilla;
import model.Empleado_PlanillaDB;
import model.Planilla;
import model.PlanillaDB;
import model.Usuario;
import model.UsuarioDB;

/**
 *
 * @author sa
 */
public class BeanPlanilla {
    //MODELO

    private Planilla modelo = new Planilla();

    String mensaje;
 LinkedList<Planilla> listaPlanillas = new LinkedList<Planilla>();
LinkedList<Empleado> obtener_Empleados_Por_Planilla = new LinkedList<Empleado>();
     Empleado empleadoXplanilla;
    Empleado_Planilla empleado_planilla;
 SimpleDateFormat formato = new SimpleDateFormat("01/mm/yyyy");
String inicio;
long suma = 0;
Date periodoInicial ;
Date periodoFinal;
int Semanal = 7;
int Quincenal = 15;
//GET
    public int getNumero() {
        return modelo.getNumero(); 
}

    public LinkedList<Planilla> getListaPlanillas() throws SNMPExceptions {
       LinkedList<Planilla> lista = new LinkedList<Planilla>();
        PlanillaDB pDB = new PlanillaDB();

        lista = pDB.obtener_Plantilla();

    /*  LinkedList resultLista = new LinkedList();

        resultLista = lista;*/
        return lista;
    }

    public void setListaPlanillas(LinkedList<Planilla> listaPlanillas) {
        this.listaPlanillas = listaPlanillas;
    }

    public Empleado getEmpleadoXplanilla() {
        return empleadoXplanilla;
    }

    public void setEmpleadoXplanilla(Empleado empleadoXplanilla) {
        this.empleadoXplanilla = empleadoXplanilla;
    }

    public Planilla getModelo() {
        return modelo;
    }

    public void setModelo(Planilla modelo) {
        this.modelo = modelo;
    }

    public Empleado_Planilla getEmpleado_planilla() {
        return empleado_planilla;
    }

    public void setEmpleado_planilla(Empleado_Planilla empleado_planilla) {
        this.empleado_planilla = empleado_planilla;
    }

    public Date getPeriodoInicial() {

        return (Date) Calendar.getInstance().getTime();
    }

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public void setPeriodoInicial(Date periodoInicial) {
        this.periodoInicial = periodoInicial;
    }

    public Date getPeriodoFinal() {
        return periodoFinal;
    }

    public void setPeriodoFinal(Date periodoFinal) {
        this.periodoFinal = periodoFinal;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public long getSuma() {
        return suma;
    }

    public void setSuma(long suma) {
        this.suma = suma;
    }

    public java.util.Date getSemanal() {

Calendar calendar = Calendar.getInstance(); 
        if (empleadoXplanilla.getSemanal()==1) {
          //  periodoInicial= this.formato(inicio);
            //suma = periodoInicial.getTime() +;
            calendar.set(01, 12, 2022);
            calendar.add(Calendar.DAY_OF_YEAR, Quincenal);
calendar.getTime();
        } else {  //if (empleadoXplanilla.getQuincenal()==1)
            calendar.add(Calendar.DAY_OF_YEAR, Semanal);
 calendar.getTime();
        }
return calendar.getTime();
}

    public void setSemanal(int Semanal) {
        this.Semanal = Semanal;
    }

    public int getQuincenal() {
        return Quincenal;
    }

    public void setQuincenal(int Quincenal) {
        this.Quincenal = Quincenal;
    }


//IDENTIFICACION DEL USUARIO QUE CREO LA PLANILLA

    public int getIdentificacion_usuario() {
        return modelo.getIdentificacion_usuario();
    }

    public String getEstado_pagado() {
        if (modelo.getEstado_pagado() == 0) {
            return "No pagado";
        } else {
            return "Pagado";
        }
    }

    public LinkedList<Empleado> getObtener_Empleados_Por_Planilla(int numeroDEplanilla)  throws SNMPExceptions{
    LinkedList<Empleado> lista = new LinkedList<Empleado>();
        PlanillaDB eDB = new PlanillaDB();

        lista = eDB.getObtener_Empleados_Por_Planilla(numeroDEplanilla);

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;


    }

    public void setObtener_Empleados_Por_Planilla(LinkedList<Empleado> obtener_Empleados_Por_Planilla) {
        this.obtener_Empleados_Por_Planilla = obtener_Empleados_Por_Planilla;
    }


   public String getEstado() {
        return modelo.getEstado();
    }

    public void setEstado(String estado) {
        this.modelo.setEstado(estado); 
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha_pago() {
        return modelo.getFecha_pago();
    }

public Date getFecha_creacion() {
        return modelo.getFecha_creacion();
    }

    public BigDecimal getMonto_bruto_a_pagar() {
        return modelo.getMonto_bruto_a_pagar();
    }


//SET
    public void setNumero(int numero) {
        this.modelo.setNumero(numero);
    }

    public void setIdentificacion_usuario(int identificacion_usuario) {
        this.modelo.setIdentificacion_usuario(identificacion_usuario);
    }

    public void setEstado_pagado(String estado_pagado) {
        if (estado_pagado.equals("No pagado")) {
            this.modelo.setEstado_pagado(0);
        } else if (estado_pagado.equals("Pagado")) {
            this.modelo.setEstado_pagado(1);
        }
    }

    public void setFecha_pago(Date fecha_pago) {
        this.modelo.setFecha_pago(fecha_pago);
    }

public void setFecha_creacion(Date fecha_creacion) {
        this.modelo.setFecha_creacion(fecha_creacion) ;
    }

    public void setMonto_bruto_a_pagar(BigDecimal monto_bruto_a_pagar) {
        this.modelo.setMonto_bruto_a_pagar(monto_bruto_a_pagar);
    }



//BEAN PARA AGREGAR LA PLANILLA SOLO CON EL EMPLEADO ID
    public void insertar_Plantilla(int identificacion) throws SNMPExceptions {

        try {
            this.modelo.insertar_Plantilla(identificacion);
            this.setMensaje("La planilla ha sido creada");
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }

    }

    public void obtener_Plantilla() throws SNMPExceptions {
        try {
            this.modelo.obtener_Plantilla();
            this.setMensaje("Se ha desactivado correctamente");
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }
}

public Empleado_Planilla obtenerDatos(int identificacion, int numeroPlanilla) throws SNMPExceptions, SQLException {
        empleado_planilla =new Empleado_Planilla(identificacion,numeroPlanilla);
        EmpleadoDB eDB = new EmpleadoDB();
        empleadoXplanilla=eDB.retornarEmpleado(identificacion);
        return  empleado_planilla;
 }




    public void cargarDatos(Planilla Em) {
        this.setMensaje("");

//this.setEstado_pagado(Em.getEstado_pagado());
this.setNumero(Em.getNumero());
//this.setFecha_pago(LocalDateTime.MAX);
this.setIdentificacion_usuario(Em.getIdentificacion_usuario());
}
public void actualizarPagoPlanilla(int numero) throws SNMPExceptions{
try {
this.modelo.actualizarPagoPlanilla(numero);
  this.setMensaje("Se ha pagado la planilla correctamente");
}catch (Exception e){
this.setMensaje("¡Ha ocurrido un problema en el pago de planilla!");
}
}

    private Date formato(String inicio) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 }
