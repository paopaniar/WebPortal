/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.SNMPExceptions;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.text.DateFormatter;
import static org.primefaces.component.api.UICalendar.PropertyKeys.timeZone;

/**
 *
 * @author sa
 */
public class Planilla {
String pattern = "yyyy/MM/dd";
SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
ZoneId zoneId = ZoneId.systemDefault();
 LocalDateTime currentLocalDateTime = LocalDateTime.of(LocalDate.MAX, LocalTime.MIN);
    private int numero;
    private int identificacion_usuario;
String fecha1;
String fecha2;
//Date fecha =  (Date) GregorianCalendar.getInstance().getTime();

    private Date fecha_creacion;
    private int estado_pagado;
    private Date fecha_pago ;
    private int identificacion_empleado;
    private BigDecimal monto_bruto_a_pagar;




//Constructor
    public Planilla() {
       


/*LocalDateTime myDateObj = LocalDateTime.now();
    System.out.println("Before formatting: " + myDateObj);
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    String formattedDate = myDateObj.format(myFormatObj);
    System.out.println("After formatting: " + formattedDate);*/

    }
//CONSTRUCTOR PARA CREAR PLANILLA AL EMPLEADO
    public Planilla(int identificacion_empleado) {
     //   this.formato = new LocalDateTime("dd/mm/yyyy");
        this.identificacion_empleado = identificacion_empleado;
    }

    public int getIdentificacion_empleado() {
        return identificacion_empleado;
    }

    public void setIdentificacion_empleado(int identificacion_empleado) {
        this.identificacion_empleado = identificacion_empleado;
    }

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }


//
 /*public String getFecha1() {
   this.fecha1 = fecha_creacion.toLocalDate();
return fecha1;
   }*/

    public String getFecha2() {
        this.fecha2 = fecha_pago.toString();
return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public void setFecha1(String fecha1) {
       this.fecha1 = fecha1;
   }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }



 
    public Planilla(int numero, int identificacion_usuario, int estado_pagado, Date fecha_pago) {
       // this.formato = new LocalDateTime("dd/mm/yyyy");
        this.numero = numero;
        this.identificacion_usuario = identificacion_usuario;
        this.estado_pagado = estado_pagado;
        this.fecha_pago = fecha_pago;
    }

    public String getEstado() {
         if (this.getEstado_pagado() == 0) {
            return "No pagado";
        } else if (this.getEstado_pagado()== 1) {
            return "Pagado";
        }
        
        return "";
    }

    public void setEstado(String estado) {
         if (estado.equals("No pagado")) {
            this.setEstado_pagado(0);
        } else if (estado.equals("Pagado")) {
            this.setEstado_pagado(1);
        }
    }

//GET

    public int getNumero() {
        return numero;
    }

    public int getIdentificacion_usuario() {
        return identificacion_usuario;
    }


  

    public int getEstado_pagado() {
return estado_pagado;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public BigDecimal getMonto_bruto_a_pagar() {
        return monto_bruto_a_pagar;
    }



//SET

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setIdentificacion_usuario(int identificacion_usuario) {
        this.identificacion_usuario = identificacion_usuario;
    }



    public void setEstado_pagado(int estado_pagado) {
      this.estado_pagado = estado_pagado;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setMonto_bruto_a_pagar(BigDecimal monto_bruto_a_pagar) {
        this.monto_bruto_a_pagar = monto_bruto_a_pagar;
    }

   public void insertar_Plantilla(int identificacion_usuario) throws SNMPExceptions {
        PlanillaDB planillaDB = new PlanillaDB();
        planillaDB.insertar_Plantilla(identificacion_usuario);
    }
public void obtener_Plantilla() throws SNMPExceptions {
      PlanillaDB planillaDB = new PlanillaDB();
      planillaDB.obtener_Plantilla();
    }
public void obtener_Empleados_Por_Planilla(int numeroDEplanilla) throws SNMPExceptions {
      PlanillaDB planillaDB = new PlanillaDB();
      planillaDB.getObtener_Empleados_Por_Planilla(numeroDEplanilla);
    }

public void actualizarPagoPlanilla(int numero) throws SNMPExceptions {
        PlanillaDB plnDB = new PlanillaDB();
        plnDB.actualizarPagoPlanilla(numero);
    }

}
