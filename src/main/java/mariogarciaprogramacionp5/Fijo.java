/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogarciaprogramacionp5;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-feb-2021 19:14:21
 */
public class Fijo extends Empleado {
    private double salario;
    //modificacion salario base
    private final double SBASE=1500;
    
    //constructor
    Fijo(String nombre,String apellidos,int edad,int idEmpleado){
        super(nombre,apellidos,edad,idEmpleado);
        
    }
    //funcion que calcula la nomina
    @Override
    public double importeNomina(){
        this.salario=SBASE;
        return this.getSalario();
    }
    
    //funcion que muestra los datos del empleado fijo
    @Override
    public void mostrarDatos(){
        super.mostrarDatos();
        System.out.println("Salario: "+importeNomina());
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

}
