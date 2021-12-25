/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BBDD.BaseDeDatos;
import java.io.File;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-abr-2021 15:40:04
 */
public class ModeloAlumno {

    private int idAlumno;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private int telefono;

    /**
     * funcion que recoge los datos del alumno seleccionado
     *
     * @param nombre
     * @return// devuelve el objeto alumno con los datos
     */
    public ModeloAlumno datosAlumno(String nombre) {
        ModeloAlumno persona = new ModeloAlumno();
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexion();
            String consulta = "SELECT * FROM alumnos WHERE Nombre='" + nombre + "'";
            ResultSet datos = sesion.executeQuery(consulta);

            while (datos.next()) {
                String email = datos.getString("email");
                int telefono = datos.getInt("telefono");
                int idAlumno = datos.getInt("idalumno");
                String apellido1=datos.getString("apellido1");
                String apellido2=datos.getString("apellido2");
                persona.setApellido1(apellido1);
                persona.setApellido2(apellido2);
                persona.setIdAlumno(idAlumno);
                persona.setEmail(email);
                persona.setTelefono(telefono);
                persona.setNombre(nombre);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return persona;
    }

    /**
     * funcion que inserta un alumno
     *
     * @param alumno
     */
    public void insertarAlumno(ModeloAlumno alumno) {
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM alumnos";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.moveToInsertRow();
            String nombre = alumno.getNombre();
            String ape1 = alumno.getApellido1();
            String ape2 = alumno.getApellido2();
            String email = alumno.getEmail();
            int telefono = alumno.getTelefono();
            String telefonotexto = String.valueOf(telefono);
            datos.updateString("nombre", nombre);
            datos.updateString("apellido1", ape1);
            datos.updateString("apellido2", ape2);
            datos.updateString("email", email);
            datos.updateString("telefono", telefonotexto);
            datos.insertRow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * funcion que actualiza los datos del alumno seleccionado
     *
     * @param alumnoActualizado
     */
    public void actualizarAlumno(ModeloAlumno alumnoActualizado) {

        try {
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM alumnos WHERE idalumno='" + alumnoActualizado.getIdAlumno() + "'";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.last();
            datos.updateString("nombre", alumnoActualizado.getNombre());
            datos.updateString("apellido1", alumnoActualizado.getApellido1());
            datos.updateString("apellido2", alumnoActualizado.getApellido2());
            datos.updateInt("telefono", alumnoActualizado.getTelefono());
            datos.updateString("email", alumnoActualizado.getEmail());
            datos.updateRow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * funcion que borra el alumno en la base de datos
     *
     * @param idAlumnoBorrar
     */
    public void borrarAlumno(int idAlumnoBorrar) {
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM alumnos WHERE idalumno='" + idAlumnoBorrar + "'";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.next();
            datos.deleteRow();
        } catch (Exception e) {
        }
    }

    /**
     * funcion que exporta los alumnos de la base de datos
     */
    public static void exportarAlumnos() {
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexion();
            String consulta = "SELECT * FROM alumnos";
            ResultSet datos = sesion.executeQuery(consulta);
            File exportadoAlumnos = new File("src/ficheros/alumnosExportados.txt");
            FileWriter writer = new FileWriter(exportadoAlumnos);
            while (datos.next()) {
                int idAlumno = datos.getInt("idAlumno");
                String nombre = datos.getString("nombre");
                String ape1 = datos.getString("apellido1");
                String ape2 = datos.getString("apellido2");
                String email = datos.getString("email");
                String telefono = datos.getString("telefono");
                writer.write(idAlumno + " ");
                writer.write(nombre + " ");
                writer.write(ape1 + " ");
                writer.write(ape2 + " ");
                writer.write(email + " ");
                writer.write(telefono + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * se importa el fichero que esta dentro de la carpeta src
     *
     * @param fichero
     */
    public static void importarAlummos(File fichero) {
        try {
            File importadoalumnos = fichero;
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM alumnos";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.moveToInsertRow();
            Scanner lector = new Scanner(importadoalumnos);
            while (lector.hasNext()) {
                int idAlumno = lector.nextInt();
                String nombre = lector.next();
                String ape1 = lector.next();
                String ape2 = lector.next();
                String email = lector.next();
                String telefono = lector.next();
                datos.updateInt("idalumno", idAlumno);
                datos.updateString("nombre", nombre);
                datos.updateString("apellido1", ape1);
                datos.updateString("apellido2", ape2);
                datos.updateString("email", email);
                datos.updateString("telefono", telefono);
                datos.insertRow();
            }
            lector.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    /**
     * @return the idAlumno
     */
    public int getIdAlumno() {
        return idAlumno;
    }

    /**
     * @param idAlumno the idAlumno to set
     */
    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

}
