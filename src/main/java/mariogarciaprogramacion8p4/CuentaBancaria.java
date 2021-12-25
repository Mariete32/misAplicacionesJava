/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mariogarciaprogramacion8p4;

import java.util.Scanner;

/**
 *
 * @author Mario
 */
public class CuentaBancaria {
    
    private final String IBAN, titular;
    private Double Saldo=0.0;
    double[] ingresos = new double[100];
    private int contadorIngresos=0;
    private double[] retiradas = new double[100];
    private int contadorRetiradas=0;
    
    //creamos el constructor con el numero IBAN y el titular
    public CuentaBancaria(String IBAN,String titular){
        this.IBAN = IBAN;
        this.titular = titular; 
    }
    //funcion que ingresa Saldo
    public void ingresarSaldo(double ingreso){
        if (ingreso>3000) {
            System.out.println("---AVISO: Notificar a hacienda---"); 
        }
        this.Saldo += ingreso;
        ingresos[this.contadorIngresos]=ingreso;
        contadorIngresos++;
    }
    //funcion que retira saldo
    public void retirarSaldo(double retirada){
        if (this.Saldo-retirada < -50) {
            System.out.println("---Supera el maximo permitido---");
        } else if(this.Saldo-retirada > -50 && this.Saldo-retirada < 0){
            System.out.println("---AVISO: Saldo negativo---");
            this.Saldo -= retirada;
            this.retiradas[this.contadorRetiradas]=retirada;
            contadorRetiradas++;
        }else{
            this.Saldo -= retirada;
            this.retiradas[this.contadorRetiradas]=retirada;
            contadorRetiradas++;
        }
    }
    /**
     * @return the Saldo
     */
    public Double getSaldo() {
        return this.Saldo;
    }

//funcion que nos devuelve el historial de ingresos
    public void getIngresos() {
        for (int i = 0; i < this.contadorIngresos; i++) {
            System.out.println(ingresos[i]);
        }
    }

//funcion que nos devuelve el historial de retiradas
    public void getRetiradas() {
        for (int i = 0; i < this.contadorRetiradas; i++) {
            System.out.println(retiradas[i]);
        }
    }
    
    
    
}
