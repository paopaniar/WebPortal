/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DAO.SNMPExceptions;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 *
 * @author sa
 */
public class Empleado_Planilla {

//Repositorio
    Empleado_PlanillaDB repositorio = new Empleado_PlanillaDB();

//Numero de identificacion del empleado
    private int empleado;
//Numero de planilla
    private int planilla;

//Beneficios de un empleado durante la planilla actual
    private String acronimo;
    private String descripcion_beneficios;
    private float porcentaje_beneficios;
    private int pagaEmpleado;
    private int proceso;
    private BigDecimal monto_a_reducir_Beneficios;

//Renta de un empleado durante la planilla actual
    private String descripcion_renta;
    private float porcentaje_deduccion;
    private int aplica;
    private BigDecimal monto_a_reducir_Renta;

//Categoria del pago de un empleado durante la planilla actual 
    private String descripcion_categoria;
    private float porcentaje_categoria;
    private int cantidadhoras;
    private BigDecimal monto_a_pagar_Categoria;

//Salario por hora de un empleado durante la planilla actual 
    private BigDecimal SalarioxHora;

//Salario Bruto de un empleado especifico durante la plantilla actual
    private BigDecimal salario_bruto;

//Salario Neto de un empleado especifico durante la plantilla actual
    private BigDecimal salario_neto;

//Monto Total a reducir de la tabla Renta
    private BigDecimal monto_renta;

//Monto Total a reducir de la tabla Beneficios
    private BigDecimal monto_beneficios;

//Monto Total a pagar de la tabla Categoria de pago
    private BigDecimal monto_categoria_pago;

//Tipo de planilla para un empleado especifico
    private int quincenal;
    private int semanal;
    private LocalDateTime periodo_inicio;
    private LocalDateTime periodo_final;

//Turno de un empleado especifico durante la planilla actual
    private int diurno;
    private int nocturno;
    private int mixto;
//Strings para mostrar en el frente 
    private String encargadoPago;
    private String activado;
    private String aplicado;
//CONSTRUCTOR

    public Empleado_Planilla() {
    }

    public Empleado_Planilla(int empleado, int planilla, String acronimo, String descripcion_beneficios, float porcentaje_beneficios, int pagaEmpleado, int proceso, String descripcion_categoria, float porcentaje_categoria, int cantidadhoras, BigDecimal salario_bruto, BigDecimal salario_neto, int quincenal, int semanal, LocalDateTime periodo_inicio, LocalDateTime periodo_final, int diurno, int nocturno, int mixto) {
        this.empleado = empleado;
        this.planilla = planilla;
        this.acronimo = acronimo;
        this.descripcion_beneficios = descripcion_beneficios;
        this.porcentaje_beneficios = porcentaje_beneficios;
        this.pagaEmpleado = pagaEmpleado;
        this.proceso = proceso;
        this.descripcion_categoria = descripcion_categoria;
        this.porcentaje_categoria = porcentaje_categoria;
        this.cantidadhoras = cantidadhoras;
        this.salario_bruto = salario_bruto;
        this.salario_neto = salario_neto;
        this.quincenal = quincenal;
        this.semanal = semanal;
        this.periodo_inicio = periodo_inicio;
        this.periodo_final = periodo_final;
        this.diurno = diurno;
        this.nocturno = nocturno;
        this.mixto = mixto;
    }
//Constructor especial para ver empleado x planilla

    public Empleado_Planilla(int empleado, int planilla) {
        this.empleado = empleado;
        this.planilla = planilla;
    }

//GET
    public int getEmpleado() {
        return empleado;
    }

    public int getPlanilla() {
        return planilla;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public String getDescripcion_beneficios() {
        return descripcion_beneficios;
    }

    public float getPorcentaje_beneficios() {
        return porcentaje_beneficios;
    }

    public int getPagaEmpleado() {
        return pagaEmpleado;
    }

    public int getProceso() {
        return proceso;
    }

    public String getDescripcion_categoria() {
        return descripcion_categoria;
    }

    public float getPorcentaje_categoria() {
        return porcentaje_categoria;
    }

    public int getCantidadhoras() {
        return cantidadhoras;
    }

    public BigDecimal getSalario_bruto() throws SNMPExceptions, SQLException {
        salario_bruto = repositorio.montoSalario_bruto(this.empleado, this.planilla);
        return salario_bruto;
    }

    public BigDecimal getSalario_neto() throws SNMPExceptions, SQLException {
        salario_bruto = repositorio.montoSalario_neto(this.empleado, this.planilla);
        return salario_bruto;
    }

    public int getQuincenal() {
        return quincenal;
    }

    public int getSemanal() {
        return semanal;
    }

    public LocalDateTime getPeriodo_inicio() {
        return periodo_inicio;
    }

    public LocalDateTime getPeriodo_final() {
        return periodo_final;
    }

    public int getDiurno() {
        return diurno;
    }

    public int getNocturno() {
        return nocturno;
    }

    public int getMixto() {
        return mixto;
    }

    public BigDecimal getMonto_a_reducir_Beneficios() {
        return monto_a_reducir_Beneficios;
    }

    public String getDescripcion_renta() {
        return descripcion_renta;
    }

    public float getPorcentaje_deduccion() {
        return porcentaje_deduccion;
    }

    public int getAplica() {
        return aplica;
    }

    public BigDecimal getMonto_a_reducir_Renta() {
        return monto_a_reducir_Renta;
    }

    public BigDecimal getMonto_a_pagar_Categoria() {

        return monto_a_pagar_Categoria;
    }

    public BigDecimal getSalarioxHora() throws SNMPExceptions, SQLException {
        SalarioxHora = repositorio.montoSalarioxHora(this.empleado, this.planilla);
        return SalarioxHora;
    }

    public BigDecimal getMonto_renta() throws SNMPExceptions, SQLException {
        monto_renta = repositorio.montoTotalRenta(this.empleado, this.planilla);
        return monto_renta;
    }

    public BigDecimal getMonto_beneficios() throws SNMPExceptions, SQLException {
        monto_beneficios = repositorio.montoTotalBeneficios(this.empleado, this.planilla);
        return monto_beneficios;
    }

    public BigDecimal getMonto_categoria_pago() throws SNMPExceptions, SQLException {
        monto_categoria_pago = repositorio.montoTotalCategoria(empleado, planilla);
        return monto_categoria_pago;
    }

//SET
    public String getEncargadoPago() {
        if (this.getPagaEmpleado() == 1) {
            return "Patrono";
        } else {
            return "Empleado";
        }
    }

    public void setEncargadoPago(String encargadoPago) {
        if (encargadoPago.equals("Patrono")) {
            this.setPagaEmpleado(1);
        } else if (encargadoPago.equals("Empleado")) {
            this.setPagaEmpleado(0);
        }
    }

    public String getActivado() {
        if (this.proceso == 1) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public String getAplicado() {
        if (this.getAplica() == 1) {
            return "Sí";
        } else {
            return "No";
        }
    }

    public void setAplicado(String aplicado) {
        if (aplicado.equals("Sí")) {
            this.setAplica(1);
        } else if (aplicado.equals("No")) {
            this.setAplica(0);
        }
    }

    public void setActivado(String activado) {
        if (activado.equals("Sí")) {
            this.setProceso(1);
        } else if (activado.equals("No")) {
            this.setProceso(0);
        }
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public void setPlanilla(int planilla) {
        this.planilla = planilla;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public void setDescripcion_beneficios(String descripcion_beneficios) {
        this.descripcion_beneficios = descripcion_beneficios;
    }

    public void setPorcentaje_beneficios(float porcentaje_beneficios) {
        this.porcentaje_beneficios = porcentaje_beneficios;
    }

    public void setPagaEmpleado(int pagaEmpleado) {
        this.pagaEmpleado = pagaEmpleado;
    }

    public void setProceso(int proceso) {
        this.proceso = proceso;
    }

    public void setDescripcion_categoria(String descripcion_categoria) {
        this.descripcion_categoria = descripcion_categoria;
    }

    public void setPorcentaje_categoria(float porcentaje_categoria) {
        this.porcentaje_categoria = porcentaje_categoria;
    }

    public void setCantidadhoras(int cantidadhoras) {
        this.cantidadhoras = cantidadhoras;
    }

    public void setSalario_bruto(BigDecimal salario_bruto) {
        this.salario_bruto = salario_bruto;
    }

    public void setSalario_neto(BigDecimal salario_neto) {
        this.salario_neto = salario_neto;
    }

    public void setQuincenal(int quincenal) {
        this.quincenal = quincenal;
    }

    public void setSemanal(int semanal) {
        this.semanal = semanal;
    }

    public void setPeriodo_inicio(LocalDateTime periodo_inicio) {
        this.periodo_inicio = periodo_inicio;
    }

    public void setPeriodo_final(LocalDateTime periodo_final) {
        this.periodo_final = periodo_final;
    }

    public void setDiurno(int diurno) {
        this.diurno = diurno;
    }

    public void setNocturno(int nocturno) {
        this.nocturno = nocturno;
    }

    public void setMixto(int mixto) {
        this.mixto = mixto;
    }

    public void setMonto_a_reducir_Beneficios(BigDecimal monto_a_reducir_Beneficios) {
        this.monto_a_reducir_Beneficios = monto_a_reducir_Beneficios;
    }

    public void setDescripcion_renta(String descripcion_renta) {
        this.descripcion_renta = descripcion_renta;
    }

    public void setPorcentaje_deduccion(float porcentaje_deduccion) {
        this.porcentaje_deduccion = porcentaje_deduccion;
    }

    public void setAplica(int aplica) {
        this.aplica = aplica;
    }

    public void setMonto_a_reducir_Renta(BigDecimal monto_a_reducir_Renta) {
        this.monto_a_reducir_Renta = monto_a_reducir_Renta;
    }

    public void setMonto_a_pagar_Categoria(BigDecimal monto_a_pagar_Categoria) {
        this.monto_a_pagar_Categoria = monto_a_pagar_Categoria;
    }

    public void setSalarioxHora(BigDecimal SalarioxHora) {
        this.SalarioxHora = SalarioxHora;
    }

    public void setMonto_renta(BigDecimal monto_renta) {
        this.monto_renta = monto_renta;
    }

    public void setMonto_beneficios(BigDecimal monto_beneficios) {
        this.monto_beneficios = monto_beneficios;
    }

    public void setMonto_categoria_pago(BigDecimal monto_categoria_pago) {
        this.monto_categoria_pago = monto_categoria_pago;
    }
public void actualizarBene(int pln, int emp) throws SNMPExceptions {
        Empleado_PlanillaDB plnDB = new Empleado_PlanillaDB();
        plnDB.actualizar_Beneficios2(pln, emp);
    }
}
