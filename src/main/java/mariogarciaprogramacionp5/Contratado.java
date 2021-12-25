/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogarciaprogramacionp5;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-feb-2021 20:45:13
 */
public class Contratado extends Empleado{
    private double salario;
    private double diasContrato;
    
    //salario base
    public final double SBASE=1000;
    
    //constructor
    Contratado(String nombre,String apellidos, int edad, int idEmpleado, int diasContrato){
        super(nombre,apellidos,edad,idEmpleado);
        this.diasContrato=diasContrato;
    }
    //funcion que calcula la nomina
    @Override
    public double importeNomina(){
        double salarioDiario=this.SBASE/30;
        this.salario=salarioDiario*this.getDiasContrato();
        this.salario=Math.round(getSalario() * 100) / 100d;
        return this.getSalario();
        
    }
    /**
     * @return the diasContrato
     */
    public double getDiasContrato() {
        return diasContrato;
    }
    
    /**
     * @param diasContrato the diasContrato to set
     */
    public void setDiasContrato(int diasContrato) {
        this.diasContrato = diasContrato;
    }
    
    //funcion que muestra los datos del empleado temporal
    @Override
    public void mostrarDatos(){
        super.mostrarDatos();
        System.out.println("Dias contratados: "+this.getDiasContrato());
        System.out.println("Salario: "+importeNomina());
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }
}
