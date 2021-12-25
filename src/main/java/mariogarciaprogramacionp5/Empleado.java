/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogarciaprogramacionp5;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-feb-2021 19:13:35
 */
public abstract class Empleado extends Persona{
    private int idEmpleado;
    protected double salario;
    
    //constructor
    Empleado(String nombre,String apellidos,int edad, int idEmpleado){
    super(nombre, apellidos, edad);
    this.idEmpleado=idEmpleado;
    }
    
    public abstract double importeNomina();
    
    @Override
    public void mostrarDatos(){
        super.mostrarDatos();
        System.out.println("Empleado: "+this.idEmpleado);
    }
    /**
     * @return the idEmpleado
     */
    public int getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * @param idEmpleado the idEmpleado to set
     */
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salarioBase
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }
}
