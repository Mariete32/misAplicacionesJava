/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogarciaprogramacionp5;

import java.util.ArrayList;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-feb-2021 19:14:45
 */
public class Plantilla{

    private ArrayList<Persona> empleados = new ArrayList();

    //funcion que añade un empleado a la plantilla
    public void addEmpleado(Persona a){
        empleados.add(a);
    }
    
    public void mostrarDatos(){
        for (int i = 0; i < empleados.size(); i++) {
            Persona a=(Persona)empleados.get(i);
            a.mostrarDatos();
            String tipo=temporalOfijo(a);
            System.out.println("Tipo de empleado: "+tipo);
        }
    }
    
    //funcion que suma las nominas de todos los empleados
    public void importeTotalSalarioPlantilla(){
        double salario=0;
        for (int i = 0; i < empleados.size(); i++) {
            Empleado a=(Empleado)empleados.get(i);
            salario+=a.getSalario();
            
        }
        System.out.println("El salario total de los empleados es: "+salario+"€");
    }
    
    //funcion que devuelve de que tipo es el empleado, fijo o temporal
    public String temporalOfijo(Persona a){
        if (a instanceof Fijo) {
            return "Fijo";
        } else {
            return "Temporal";
        }
    }
}
