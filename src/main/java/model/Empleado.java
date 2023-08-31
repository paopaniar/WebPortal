/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.SNMPExceptions;
import java.math.BigDecimal;


/**
 *
 * @author sa
 */
public class Empleado {
private int identificacion;
private String nombre;
private String apellido;
private String apellido2;
private String tipo_jornada;
private String descripcion_puesto;
//Tipo de empleado
private float salarioXhora;
private int semanal;
private int quincenal;
private int estado;
private BigDecimal salario_bruto_actual;
//Strings para el front
private String estados;
//Constructores
    public Empleado() {
    }

    public Empleado(int identificacion, String nombre, String apellido, String apellido2, String tipo_jornada, String descripcion_puesto, float salarioXhora) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.apellido2 = apellido2;
        this.tipo_jornada = tipo_jornada;
        this.descripcion_puesto = descripcion_puesto;
        this.salarioXhora = salarioXhora;

    }



 //GET

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

    public String getTipo_jornada() {
        return tipo_jornada;
    }


    public float getSalarioXhora() {
        return salarioXhora;
    }

    public int getEstado() {
        return estado;
    }

    public String getTipoPago() {
       if (this.getQuincenal() == 1) {
            return "Quincenal";
        } else if (this.getSemanal() == 1) {
            return "Semanal";
        }
        
        return "";
        
    }

    public BigDecimal getSalario_bruto_actual() {
        return salario_bruto_actual;
    }

    public String getEstados() {
     if (this.getEstado()== 1) {
            return "Activo";
        } else {
            return "Desactivo";
        }
    }

    public void setEstados(String estados) {
               if (estados.equals("Activo")) {
            this.setEstado(1);
        } else if (estados.equals("Desactivo")) {
            this.setEstado(0);
        }
    }
    
    


//SET

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

    public void setTipo_jornada(String tipo_jornada) {
        this.tipo_jornada = tipo_jornada;
    }

    public String getDescripcion_puesto() {
        return descripcion_puesto;
    }

    public int getSemanal() {
        return semanal;
    }

    public int getQuincenal() {
        return quincenal;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setTipoPago(String tipoPago) {
       if (tipoPago.equals("Semanal")) {
            this.setSemanal(1);
        } else if (tipoPago.equals("Quincenal")) {
            this.setQuincenal(1);
        }
    }

 

    public void setSalarioXhora(float salarioXhora) {
        this.salarioXhora = salarioXhora;
    }

    public void setSemanal(int semanal) {
        this.semanal = semanal;
    }

    public void setQuincenal(int quincenal) {
        this.quincenal = quincenal;
    }

    public void setSalario_bruto_actual(BigDecimal salario_bruto_actual) {
        this.salario_bruto_actual = salario_bruto_actual;
    }

    
    public void setDescripcion_puesto(String descripcion_puesto) {
        this.descripcion_puesto = descripcion_puesto;
    }
public void insertar_Empleado_Semanal(Empleado empleado) throws SNMPExceptions {
        EmpleadoDB empleadoDB = new EmpleadoDB();
        empleadoDB.insertar_Empleado_Semanal(empleado);
    }
public void insertar_Empleado_Quincenal(Empleado empleado) throws SNMPExceptions {
        EmpleadoDB empleadoDB = new EmpleadoDB();
        empleadoDB.insertar_Empleado_Quincenal(empleado);
    }
public void desactivar_Empleado_Por_Identificacion(int identificacion) throws SNMPExceptions {
        EmpleadoDB empleadoDB = new EmpleadoDB();
        empleadoDB.Desactivar_Empleado_Por_Identificacion(identificacion);
    }
public void obtener_Todos_Los_Empleados_Desactivados() throws SNMPExceptions {
        EmpleadoDB empleadoDB = new EmpleadoDB();
        empleadoDB.obtener_Todos_Los_Empleados();
    }
public void activar_Empleado_Por_Identificacion(int identificacion) throws SNMPExceptions {
        EmpleadoDB empleadoDB = new EmpleadoDB();
        empleadoDB.activar_Empleado_Por_Identificacion(identificacion);
    }

}
