/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.ModeloAlumno;
import Vista.VistaCita;
import java.sql.SQLException;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 09-abr-2021 10:39:47
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ModeloAlumno ma= new ModeloAlumno();
        VistaCita vc=new VistaCita();
        Controlador c=new Controlador(ma, vc);
    }

}
