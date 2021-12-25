/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import BBDD.BaseDeDatos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-abr-2021 15:44:03
 */
public class ModeloCita {

    private String fecha;
    private int idCita;
    private int idAlumno;
    private String hora;
/**
 * contructor de la clase
 * @param fecha
 * @param idCita
 * @param idAlumno
 * @param hora 
 */
    public void ModeloCita(String fecha, int idCita, int idAlumno, String hora) {
        this.setFecha(fecha);
        this.setIdCita(idCita);
        this.setIdAlumno(idAlumno);
        this.setHora(hora);
    }
/**
 * funcion que muestra los datos de la cita
 * @param idAlumno
 * @return 
 */
    public ModeloCita datosCita(int idAlumno) {
        ModeloCita cita = new ModeloCita();
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexion();
            String consulta = "SELECT * FROM citas WHERE idAlumno='" + idAlumno + "'";
            ResultSet datos = sesion.executeQuery(consulta);

            while (datos.next()) {
                String fecha = datos.getString("Fecha");
                String hora = datos.getString("Hora");
                int id = datos.getInt("idCita");
                cita.setIdAlumno(idAlumno);
                cita.setFecha(fecha);
                cita.setHora(hora);
                cita.setIdCita(id);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cita;
    }

    /**
     * funcion que inserta una cita
     * @param alumno
     * @param cita 
     */
    public void insertarCita(ModeloAlumno alumno, ModeloCita cita) {
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM citas WHERE idalumno='" + alumno.getIdAlumno() + "'";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.moveToInsertRow();
            int idAlumno = alumno.getIdAlumno();
            String fechaCita = cita.getFecha();
            String hora = cita.getHora();

            datos.updateString("Fecha", fechaCita);
            datos.updateString("Hora", hora);
            datos.updateInt("idAlumno", idAlumno);
            datos.insertRow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * funcion que actualiza los datos del alumno seleccionado
     * @param citaActualizado 
     */
    public void actualizarCita(ModeloCita citaActualizado) {

        try {
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM citas WHERE idCita='" + citaActualizado.getIdCita() + "'";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.last();
            datos.updateString("Fecha", citaActualizado.getFecha());
            datos.updateString("Hora", citaActualizado.getHora());
            datos.updateInt("idAlumno", citaActualizado.getIdAlumno());
            datos.updateRow();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * funcion que borra una cita en la base de datos
 * @param idalumno 
 */
    public void borrarCita(int idalumno) {
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM citas WHERE idalumno='" + idalumno + "'";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.next();
            datos.deleteRow();
        } catch (Exception e) {
        }

    }

    /**
     * funcion que exporta los alumnos de la base de datos
     */
    public static void exportarCitas() {
        try {
            Statement sesion;
            sesion = BaseDeDatos.conexion();
            String consulta = "SELECT * FROM citas";
            ResultSet datos = sesion.executeQuery(consulta);
            File exportadoCitas = new File("src/ficheros/citasExportados.txt");
            FileWriter writer = new FileWriter(exportadoCitas);
            while (datos.next()) {
                int idAlumno = datos.getInt("idAlumno");
                int idCita = datos.getInt("idCita");
                String fecha = datos.getString("fecha");
                String hora = datos.getString("hora");
                writer.write(idAlumno + " ");
                writer.write(idCita + " ");
                writer.write(fecha + " ");
                writer.write(hora + "\n");
            }
            writer.close();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * funcion que importa las citas desde un fichero
 * @param fichero 
 */
    public static void importarCitas(File fichero) {
        try {
            File importadocitas = fichero;
            Statement sesion;
            sesion = BaseDeDatos.conexionInsertar();
            String consulta = "SELECT * FROM citas";
            ResultSet datos = sesion.executeQuery(consulta);
            datos.moveToInsertRow();
            Scanner lector = new Scanner(importadocitas);
            while (lector.hasNext()) {
                int idCita = lector.nextInt();
                int idAlumno = lector.nextInt();
                String fecha = lector.next();
                String hora = lector.next();
                datos.updateInt("idcita", idCita);
                datos.updateInt("idalumno", idAlumno);
                datos.updateString("fecha", fecha);
                datos.updateString("hora", hora);
                datos.insertRow();
            }
            lector.close();
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the idCita
     */
    public int getIdCita() {
        return idCita;
    }

    /**
     * @param idCita the idCita to set
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
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
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
}
