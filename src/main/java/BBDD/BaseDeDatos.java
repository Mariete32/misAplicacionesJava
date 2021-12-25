/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-abr-2021 16:30:01
 */

public class BaseDeDatos {
/**
 * funcion que conecta con la base de datos
 * @return 
 */
    public static Statement conexion(){
        Statement sesion=null;
        try {
            String url="jdbc:mysql://localhost:3306/bd?serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conexion= DriverManager.getConnection(url, "alumno", "alumno");
            sesion=conexion.createStatement();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return sesion;
    }
/**
 * çfuncion que conecta con la base de datos para insertar datos
 * @return 
 */
    public static Statement conexionInsertar() {
        Statement sesion=null;
        try {
            String url="jdbc:mysql://localhost:3306/bd?serverTimezone=UTC";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conexion= DriverManager.getConnection(url, "alumno", "alumno");
            sesion=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return sesion;}
}
