/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogarciaprogramacionp5;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-feb-2021 19:13:26
 */
public class Persona {
    private String nombre;
    private String apellidos;
    private int edad;
    
    //constructor padre
    Persona (String nombre,String apellidos,int edad){
    this.apellidos=apellidos;
    this.edad=edad;
    this.nombre=nombre;
    }

    public void mostrarDatos(){
        System.out.println("Nombre: "+this.nombre);
        System.out.println("Apellidos: "+this.apellidos);
        System.out.println("Edad: "+this.edad);
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return the edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * @param edad the edad to set
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}
