/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mariogarciaprogramacionp5;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 08-feb-2021 19:14:53
 */
public class Main {

    public static void main(String[] args) {

    //empleados fijos
    Fijo MGR=new Fijo("MARIO", "GARCIA REYES", 15, 3);
    Fijo RPL=new Fijo("RAUL", "PEREZ LOPEZ", 22, 1);
    Fijo RUN=new Fijo("RUBEN", "UBER NAVARRO", 24, 2);
    
    //empleados temporales
    Contratado CHS=new Contratado("CORA", "HONRUBIA SAEZ", 22, 4, 30);
    Contratado TGC=new Contratado("TONI", "GAZQUEZ CHACON", 24, 5, 4);
    Contratado ERR=new Contratado("ESPERANZA", "REYES RUIZ", 15, 6, 8);
    
    //creamos un objeto de tipo Plantilla
    Plantilla empleados = new Plantilla();
    
    //añadimos los empleados a la plantilla
    empleados.addEmpleado(MGR);
    empleados.addEmpleado(RPL);
    empleados.addEmpleado(RUN);
    empleados.addEmpleado(CHS);
    empleados.addEmpleado(TGC);
    empleados.addEmpleado(ERR);
    
    //mostramos los datos del empleado
    empleados.mostrarDatos();
    
    //mostramos el importe total de las nominas de la plantilla
    empleados.importeTotalSalarioPlantilla();
    }
}
