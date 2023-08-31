/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.SNMPExceptions;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.LinkedList;
import model.Empleado;
import model.EmpleadoDB;
import model.Usuario;
import model.UsuarioDB;

/**
 *
 * @author sa
 */
public class BeanEmpleado {

    private Empleado modelo = new Empleado();
    LinkedList<Empleado> listaPersonas = new LinkedList<Empleado>();
 LinkedList<Empleado> listaEmpleadosDesactivados = new LinkedList<Empleado>();
 LinkedList<Empleado> listaEmpleadosActivos = new LinkedList<Empleado>();
    String mensaje;
//Strings para el front
private String estados;
    //PRUEBA DE KEVIN
     Empleado empleado_a_modificar;

//GET

    public Empleado getEmpleado_a_modificar() {
        return empleado_a_modificar;
    }
    public int getIdentificacion() {
        return modelo.getIdentificacion();
    }

    public String getNombre() {
        return modelo.getNombre();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getApellido() {
        return modelo.getApellido();
    }

    public String getApellido2() {
        return modelo.getApellido2();
    }

    public String getTipo_jornada() {
        return modelo.getTipo_jornada();
    }

  public BigDecimal getSalario_bruto_actual() {
        return modelo.getSalario_bruto_actual();
    }

    public String getEstados() {
      if (this.modelo.getEstado()== 1) {
            return "Activo";
        } else {
            return "Desactivo";
        }
    }

    public void setEstados(String estados) {
         if (estados.equals("Activo")) {
            this.modelo.setEstado(1);
        } else if (estados.equals("Desactivo")) {
            this.modelo.setEstado(0);
        }
    }


public void setSalario_bruto_actual(BigDecimal salario_bruto_actual) {
        this.modelo.setSalario_bruto_actual(salario_bruto_actual);
    }

 public void actualizar_Empleado(Empleado empleado_a_modificar_frente) throws SNMPExceptions {

        try {
            EmpleadoDB empleado = new EmpleadoDB();
            int cedula = empleado_a_modificar_frente.getIdentificacion();
            String nombre = empleado_a_modificar_frente.getNombre();
            empleado.actualizar_Empleado(empleado_a_modificar_frente);
            this.setMensaje("Los datos se han actualizado exitosamente");

        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema en actualizar!");
        }
    }

  public LinkedList<Empleado> getListaEmpleadosDesactivados() throws SNMPExceptions {
        LinkedList<Empleado> lista = new LinkedList<Empleado>();
        EmpleadoDB eDB = new EmpleadoDB();

        lista = eDB.obtener_Todos_Los_Empleados_Desactivados();

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;
    }

    public LinkedList<Empleado> getListaPersonas() throws SNMPExceptions {
      LinkedList<Empleado> lista = new LinkedList<Empleado>();
        EmpleadoDB eDB = new EmpleadoDB();

        lista = eDB.obtener_Todos_Los_Empleados();

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;
    }


    public LinkedList<Empleado> getListaEmpleadosActivos() throws SNMPExceptions {
        LinkedList<Empleado> lista = new LinkedList<Empleado>();
        EmpleadoDB eDB = new EmpleadoDB();

        lista = eDB.obtener_Todos_Los_Empleados();

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;
    }

    public String getDescripcion_puesto() {
        return modelo.getDescripcion_puesto();
    }

    public float getSalarioXhora() {
        return modelo.getSalarioXhora();

    }

    public int getSemanal() {
        return modelo.getSemanal();
    }

    public int getQuincenal() {
        return modelo.getQuincenal();
    }

    public int getEstado() {
        return modelo.getEstado();
    }

    public String getTipoPago() {
        if (modelo.getQuincenal() == 1) {
            return "Quincenal";
        } else if (modelo.getSemanal() == 1) {
            return "Semanal";
        }

        return "";

    }

    public void setSemanal(int semanal) {
        this.modelo.setSemanal(semanal);
    }

    public void setQuincenal(int quincenal) {
        this.modelo.setQuincenal(quincenal);
    }

    public void setListaPersonas(LinkedList<Empleado> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public void setEstado(int estado) {
        this.modelo.setEstado(estado);
    }

    public void setTipoPago(String tipoPago) {
        if (tipoPago.equals("Semanal")) {
            this.modelo.setSemanal(1);
        } else if (tipoPago.equals("Quincenal")) {
            this.modelo.setQuincenal(1);
        }
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

    public void setTipo_jornada(String tipo_jornada) {
        this.modelo.setTipo_jornada(tipo_jornada);
    }

    public void setSalarioXhora(float salarioXhora) {
        this.modelo.setSalarioXhora(salarioXhora);
    }

    public void setDescripcion_puesto(String descripcion_puesto) {
        this.modelo.setDescripcion_puesto(descripcion_puesto);
    }

    public void insertar_Empleado_Semanal(
            int identificacion,
            String nombre,
            String apellido,
            String apellido2,
            String tipo_jornada,
            String descripcion_puesto,
            float salarioXhora) throws SNMPExceptions {

        try {
            this.modelo.insertar_Empleado_Semanal(new Empleado(identificacion, nombre, apellido, apellido2, tipo_jornada, descripcion_puesto, salarioXhora));
            this.setMensaje("Los datos se han guardado exitosamente como empleado semanal");
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }

    }
public void desactivar_Empleado_Por_Identificacion(int identificacion) throws SNMPExceptions{
try {
this.modelo.desactivar_Empleado_Por_Identificacion(identificacion);
  this.setMensaje("Se ha desactivado correctamente");
}catch (Exception e){
this.setMensaje("¡Ha ocurrido un problema!");
}
}
public void obtener_Todos_Los_Empleados_Desactivados(){
try {
this.modelo.obtener_Todos_Los_Empleados_Desactivados();
  this.setMensaje("Se ha desactivado correctamente");
}catch (Exception e){
this.setMensaje("¡Ha ocurrido un problema!");
}
}
public void activar_Empleado_Por_Identificacion(int identificacion) throws SNMPExceptions {
        try {
this.modelo.activar_Empleado_Por_Identificacion(identificacion);
  this.setMensaje("Se ha activado correctamente");
}catch (Exception e){
this.setMensaje("¡Ha ocurrido un problema!");
}
    }
    public void insertar_Empleado_Quincenal(
            int identificacion,
            String nombre,
            String apellido,
            String apellido2,
            String tipo_jornada,
            String descripcion_puesto,
            float salarioXhora) throws SNMPExceptions {

        try {
            this.modelo.insertar_Empleado_Semanal(new Empleado(identificacion, nombre, apellido, apellido2, tipo_jornada, descripcion_puesto, salarioXhora));
            this.setMensaje("Los datos se han guardado exitosamente como quincenal");
        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema!");
        }

    }

    public void cargarDatos(Empleado Em) {
        this.setMensaje("");

        this.setIdentificacion(Em.getIdentificacion());
        this.setNombre(Em.getNombre());
        this.setApellido(Em.getApellido());
        this.setApellido2(Em.getApellido2());
        this.setTipo_jornada(Em.getTipo_jornada());
        this.setDescripcion_puesto(Em.getDescripcion_puesto());
        this.setSalarioXhora(Em.getSalarioXhora());
        this.setTipoPago(Em.getTipoPago());
        this.setSemanal(Em.getSemanal());
        this.setQuincenal(Em.getQuincenal());
        this.setEstado(Em.getEstado());


    }
    public void limpiar() {
        this.setMensaje("");

        this.modelo.setIdentificacion(0);
        this.modelo.setNombre("");
        this.modelo.setApellido("");
        this.modelo.setApellido2("");
        this.modelo.setTipo_jornada("");
        this.modelo.setDescripcion_puesto("");
        this.modelo.setSalarioXhora(0);
     
    }
    public void obtenerDatos(Empleado per) throws SNMPExceptions, SQLException {
        this.setMensaje("");

        this.setIdentificacion(per.getIdentificacion());
        this.setNombre(per.getNombre());
        this.setApellido(per.getApellido());
        this.setApellido2(per.getApellido2());
        this.setTipo_jornada(per.getTipo_jornada());
        this.setDescripcion_puesto(per.getDescripcion_puesto());
        this.setSemanal(per.getSemanal());
        this.setQuincenal(per.getQuincenal());
        this.setSalarioXhora(per.getSalarioXhora());
       this.setEstado(per.getEstado());

 empleado_a_modificar = obtenerEmpleadoActualizar(this.getIdentificacion());

    }
public Empleado obtenerEmpleadoActualizar(int ID) throws SNMPExceptions, SQLException {

      EmpleadoDB logica = new EmpleadoDB ();
        return logica.retornarEmpleado(ID);
    }
}
