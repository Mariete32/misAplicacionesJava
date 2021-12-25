/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.JOptionPane;

/**
 * @author Mario Garcia Reyes <mgkm10@gmail.com>
 * 20-abr-2021 17:54:13
 */
public class Excepciones extends Exception {

    private String excepcion;

    public Excepciones(String excepcion) {
        this.excepcion = excepcion;
        if (excepcion.equals("email")) {
            JOptionPane.showMessageDialog(null, "El email es incorrecto");
        }
    }
}
