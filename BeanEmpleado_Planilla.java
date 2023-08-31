/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DAO.SNMPExceptions;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import model.Empleado;
import model.EmpleadoDB;
import model.Empleado_Planilla;
import model.Empleado_PlanillaDB;
import model.Usuario;
import model.UsuarioDB;

/**
 *
 * @author sa
 */
public class BeanEmpleado_Planilla {

    LinkedList<Empleado_Planilla> listaBeneficios = new LinkedList<Empleado_Planilla>();
    LinkedList<Empleado_Planilla> listaCategoria = new LinkedList<Empleado_Planilla>();
    LinkedList<Empleado_Planilla> listaRenta = new LinkedList<Empleado_Planilla>();
    Empleado empleadoXplanilla;
    Empleado_Planilla empleado_planilla;
    Empleado_Planilla beneficio;
    Empleado_Planilla categoria;
    Empleado_Planilla renta;
    private String encargadoPago;
    private String activado;
    private String aplicado;
    String mensaje;
//MODELO
    Empleado_Planilla modelo = new Empleado_Planilla();

    public LinkedList<Empleado_Planilla> getListaBeneficios(int empleadoId, int planillaId) throws SNMPExceptions {
        LinkedList<Empleado_Planilla> lista = new LinkedList<Empleado_Planilla>();
        Empleado_PlanillaDB eDB = new Empleado_PlanillaDB();

        lista = eDB.obtener_Beneficios_Empleado_por_Planilla(empleadoId, planillaId);

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;
    }

    public void setListaBeneficios(LinkedList<Empleado_Planilla> listaBeneficios) {
        this.listaBeneficios = listaBeneficios;
    }

    public LinkedList<Empleado_Planilla> getListaCategoria(int empleadoId, int planillaId) throws SNMPExceptions {
        LinkedList<Empleado_Planilla> lista = new LinkedList<Empleado_Planilla>();
        Empleado_PlanillaDB eDB = new Empleado_PlanillaDB();

        lista = eDB.obtener_Categoria_Pago_Empleado_por_Planilla(empleadoId, planillaId);

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;
    }

    public void setListaCategoria(LinkedList<Empleado_Planilla> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public LinkedList<Empleado_Planilla> getListaRenta(int empleadoId, int planillaId) throws SNMPExceptions {
        LinkedList<Empleado_Planilla> lista = new LinkedList<Empleado_Planilla>();
        Empleado_PlanillaDB eDB = new Empleado_PlanillaDB();

        lista = eDB.obtener_renta_Empleado_por_Planilla(empleadoId, planillaId);

        LinkedList resultLista = new LinkedList();

        resultLista = lista;
        return resultLista;
    }

    public void setListaRenta(LinkedList<Empleado_Planilla> listaRenta) {
        this.listaRenta = listaRenta;
    }

    public int getEmpleado() {
        return modelo.getEmpleado();
    }

    public int getPlanilla() {
        return modelo.getPlanilla();
    }

    public String getAcronimo() {
        return modelo.getAcronimo();
    }

    public String getDescripcion_beneficios() {
        return modelo.getDescripcion_beneficios();
    }

    public float getPorcentaje_beneficios() {
        return modelo.getPorcentaje_beneficios();
    }

    public int getPagaEmpleado() {
        return modelo.getPagaEmpleado();
    }

    public int getProceso() {
        return modelo.getProceso();
    }

    public String getDescripcion_categoria() {
        return modelo.getDescripcion_categoria();
    }

    public float getPorcentaje_categoria() {
        return modelo.getPorcentaje_categoria();
    }

    public int getCantidadhoras() {
        return modelo.getCantidadhoras();
    }

    public BigDecimal getSalario_bruto() throws SNMPExceptions, SQLException {
        return modelo.getSalario_bruto();
    }

    public BigDecimal getSalario_neto() throws SNMPExceptions, SQLException {
        return modelo.getSalario_neto();
    }

    public int getQuincenal() {
        return modelo.getQuincenal();
    }

    public int getSemanal() {
        return modelo.getSemanal();
    }

    public LocalDateTime getPeriodo_inicio() {
        return modelo.getPeriodo_inicio();
    }

    public LocalDateTime getPeriodo_final() {
        return modelo.getPeriodo_final();
    }

    public int getDiurno() {
        return modelo.getDiurno();
    }

    public int getNocturno() {
        return modelo.getNocturno();
    }

    public int getMixto() {
        return modelo.getMixto();
    }

    public BigDecimal getMonto_a_reducir_Beneficios() {
        return modelo.getMonto_a_reducir_Beneficios();
    }

    public String getDescripcion_renta() {
        return modelo.getDescripcion_renta();
    }

    public float getPorcentaje_deduccion() {
        return modelo.getPorcentaje_deduccion();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getAplica() {
        return modelo.getAplica();
    }

    public BigDecimal getMonto_a_reducir_Renta() {
        return modelo.getMonto_a_reducir_Renta();
    }

    public BigDecimal getMonto_a_pagar_Categoria() {
        return modelo.getMonto_a_pagar_Categoria();
    }

    public BigDecimal getSalarioxHora() throws SNMPExceptions, SQLException {
        return modelo.getSalarioxHora();
    }

    public BigDecimal getMonto_renta() throws SNMPExceptions, SQLException {
        return modelo.getMonto_renta();
    }

    public BigDecimal getMonto_beneficios() throws SNMPExceptions, SQLException {
        return modelo.getMonto_beneficios();
    }

    public BigDecimal getMonto_categoria_pago() throws SNMPExceptions, SQLException {
        return modelo.getMonto_categoria_pago();
    }

//SET
    public void setEmpleado(int empleado) {
        this.modelo.setEmpleado(empleado);
    }

    public void setPlanilla(int planilla) {
        this.modelo.setPlanilla(planilla);
    }

    public void setAcronimo(String acronimo) {
        this.modelo.setAcronimo(acronimo);
    }

    public void setDescripcion_beneficios(String descripcion_beneficios) {
        this.modelo.setDescripcion_beneficios(descripcion_beneficios);
    }

    public void setPorcentaje_beneficios(float porcentaje_beneficios) {
        this.modelo.setPorcentaje_beneficios(porcentaje_beneficios);
    }

    public void setPagaEmpleado(int pagaEmpleado) {
        this.modelo.setPagaEmpleado(pagaEmpleado);
    }

    public void setProceso(int proceso) {
        this.modelo.setProceso(proceso);
    }

    public void setDescripcion_categoria(String descripcion_categoria) {
        this.modelo.setDescripcion_categoria(descripcion_categoria);
    }

    public void setPorcentaje_categoria(float porcentaje_categoria) {
        this.modelo.setPorcentaje_categoria(porcentaje_categoria);
    }

    public void setCantidadhoras(int cantidadhoras) {
        this.modelo.setCantidadhoras(cantidadhoras);
    }

    public void setSalario_bruto(BigDecimal salario_bruto) {
        this.modelo.setSalario_bruto(salario_bruto);
    }

    public void setSalario_neto(BigDecimal salario_neto) {
        this.modelo.setSalario_neto(salario_neto);
    }

    public void setQuincenal(int quincenal) {
        this.modelo.setQuincenal(quincenal);
    }

    public void setSemanal(int semanal) {
        this.modelo.setSemanal(semanal);
    }

    public void setPeriodo_inicio(LocalDateTime periodo_inicio) {
        this.modelo.setPeriodo_inicio(periodo_inicio);
    }

    public void setPeriodo_final(LocalDateTime periodo_final) {
        this.modelo.setPeriodo_final(periodo_final);
    }

    public void setDiurno(int diurno) {
        this.modelo.setDiurno(diurno);
    }

    public void setNocturno(int nocturno) {
        this.modelo.setNocturno(nocturno);
    }

    public void setMixto(int mixto) {
        this.modelo.setMixto(mixto);
    }

    public void setMonto_a_reducir_Beneficios(BigDecimal monto_a_reducir_Beneficios) {
        this.modelo.setMonto_a_reducir_Beneficios(monto_a_reducir_Beneficios);
    }

    public void setDescripcion_renta(String descripcion_renta) {
        this.modelo.setDescripcion_renta(descripcion_renta);
    }

    public void setPorcentaje_deduccion(float porcentaje_deduccion) {
        this.modelo.setPorcentaje_deduccion(porcentaje_deduccion);
    }

    public void setAplica(int aplica) {
        this.modelo.setAplica(aplica);
    }

    public void setMonto_a_reducir_Renta(BigDecimal monto_a_reducir_Renta) {
        this.modelo.setMonto_a_reducir_Renta(monto_a_reducir_Renta);
    }

    public void setMonto_a_pagar_Categoria(BigDecimal monto_a_pagar_Categoria) {
        this.modelo.setMonto_a_pagar_Categoria(monto_a_pagar_Categoria);
    }

    public void setSalarioxHora(BigDecimal SalarioxHora) {
        this.modelo.setSalarioxHora(SalarioxHora);
    }

    public void setMonto_renta(BigDecimal monto_renta) {
        this.modelo.setMonto_renta(monto_renta);
    }

    public void setMonto_beneficios(BigDecimal monto_beneficios) {
        this.modelo.setMonto_beneficios(monto_beneficios);
    }

    public Empleado_Planilla getCategoria() {
        return beneficio;
    }

    public void setCategoria(Empleado_Planilla categoria) {
        this.beneficio = categoria;
    }

    public Empleado getEmpleadoXplanilla() {
        return empleadoXplanilla;
    }

    public String getActivado() {
        if (this.modelo.getProceso() == 1) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public void setActivado(String activado) {
        if (activado.equals("Sí")) {
            this.modelo.setProceso(1);
        } else if (activado.equals("No")) {
            this.modelo.setProceso(0);
        }
    }

    public String getAplicado() {
        if (this.modelo.getAplica() == 1) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public void setAplicado(String aplicado) {
        if (aplicado.equals("Sí")) {
            this.modelo.setAplica(1);
        } else if (aplicado.equals("No")) {
            this.modelo.setAplica(0);
        }
    }

    public String getEncargadoPago() {
        if (modelo.getPagaEmpleado() == 1) {
            return "Patrono";
        } else {
            return "Empleado";
        }
    }

    public void setEncargadoPago(String encargadoPago) {
        if (encargadoPago.equals("Patrono")) {
            this.modelo.setPagaEmpleado(1);
        } else if (encargadoPago.equals("Empleado")) {
            this.modelo.setPagaEmpleado(0);
        }
    }

    public void setEmpleadoXplanilla(Empleado empleadoXplanilla) {
        this.empleadoXplanilla = empleadoXplanilla;
    }

    public Empleado_Planilla getEmpleado_planilla() {
        return empleado_planilla;
    }

    public void setEmpleado_planilla(Empleado_Planilla empleado_planilla) {
        this.empleado_planilla = empleado_planilla;
    }

    public void setMonto_categoria_pago(BigDecimal monto_categoria_pago) {
        this.modelo.setMonto_categoria_pago(monto_categoria_pago);
    }

    public Empleado_Planilla getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(Empleado_Planilla beneficio) {
        this.beneficio = beneficio;
    }

    public Empleado_Planilla getRenta() {
        return renta;
    }

    public void setRenta(Empleado_Planilla renta) {
        this.renta = renta;
    }

    public Empleado_Planilla obtenerDatos(int identificacion, int numeroPlanilla) throws SNMPExceptions, SQLException {
        empleado_planilla = new Empleado_Planilla(identificacion, numeroPlanilla);
        EmpleadoDB eDB = new EmpleadoDB();
        empleadoXplanilla = eDB.retornarEmpleado(identificacion);
        return empleado_planilla;
    }

    public Empleado_Planilla obtenerDatosBeneficos(int id, int pln, String acronimo) throws SNMPExceptions, SQLException {

        this.setMensaje("");

        empleado_planilla = new Empleado_Planilla(id, pln);
        Empleado_PlanillaDB eDB = new Empleado_PlanillaDB();
        beneficio = eDB.retornarBeneficios(id, pln,acronimo);
        return empleado_planilla;

    }

    public Empleado_Planilla obtenerDatosCategoria(int id, int pln, String descrip) throws SNMPExceptions, SQLException {

        this.setMensaje("");

        empleado_planilla = new Empleado_Planilla(id, pln);
        Empleado_PlanillaDB eDB = new Empleado_PlanillaDB();
        beneficio = eDB.retornarCategorias(id, pln, descrip);
        return empleado_planilla;

    }

    public Empleado_Planilla obtenerDatosRenta(int id, int pln) throws SNMPExceptions, SQLException {

        empleado_planilla = new Empleado_Planilla(id, pln);
        Empleado_PlanillaDB eDB = new Empleado_PlanillaDB();
        beneficio = eDB.retornarRentas(id, pln);
        return empleado_planilla;

    }

public void actualizarBene(int pln, int emp) throws SNMPExceptions{
try {
this.modelo.actualizarBene(pln, emp);
  this.setMensaje("correctamente");
}catch (Exception e){
this.setMensaje("¡Ha ocurrido un problem!");
}
}

    public void actualizar_beneficios(Empleado_Planilla beneficio_a_modificar_frente) throws SNMPExceptions {

        try {
            Empleado_PlanillaDB beneficio = new Empleado_PlanillaDB();

            beneficio.actualizar_Beneficios(beneficio_a_modificar_frente);
            this.setMensaje("Los beneficios se han actualizado exitosamente");

        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema en actualizar el beneficio!");
        }
    }

    public void actualizar_categorias(Empleado_Planilla categoria_a_modificar_frente) throws SNMPExceptions {

        try {
            Empleado_PlanillaDB categoria = new Empleado_PlanillaDB();

            categoria.actualizar_Categorias(categoria_a_modificar_frente);
            this.setMensaje("La categoria se ha actualizado exitosamente");

        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema en actualizar la categoria!");
        }
    }

    public void actualizar_rentas(Empleado_Planilla renta_a_modificar_frente) throws SNMPExceptions {

        try {
            Empleado_PlanillaDB renta = new Empleado_PlanillaDB();

            renta.actualizar_Categorias(renta_a_modificar_frente);
            this.setMensaje("La renta se ha actualizado exitosamente");

        } catch (Exception e) {
            this.setMensaje("¡Ha ocurrido un problema en actualizar el renta!");
        }
    }
}
