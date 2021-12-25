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
public class DawBank {

    public static void main(String[] args) {
        
        //pedimos el numero de cuenta
        String numeroCuenta=insertaCuenta();
        //pedimos el titular de la cuenta
        String titular=insertaNombre();

        //mostramos el menu opciones y realizamos las acciones
        menuOpciones(numeroCuenta, titular);   
    }
    
    //funcion que comprueba que el numero de cuenta sea correcto
    public static boolean compruebaCuenta(String numeroCuenta){
        boolean valido=false;
        String ES=numeroCuenta.substring(0, 2);
        
        //comprobamos si tiene 24 digitos y empieza por ES
        if (numeroCuenta.length()==24 && ES.equals("ES")) {
            String numero=numeroCuenta.substring(2, 24);
            if (numero.matches("\\d*")) {
                  valido=true;
            }else{
                System.out.println("numero incorrecto");
                ;
            }
        }else{
            System.out.println("faltan digitos o introduce ES en mayusculas");
        }
        return valido;
    }
    
    //funcion que pide el numero y lo comprueba
    public static String insertaCuenta(){
        Scanner teclado=new Scanner(System.in);
        boolean numeroCorrecto=false;
        String numeroCuenta="";
        while(numeroCorrecto==false){
        System.out.println("inserta tu numero de cuenta");
        numeroCuenta=teclado.nextLine();
        numeroCorrecto=compruebaCuenta(numeroCuenta);
        }
        return numeroCuenta;
    }
    
    //funcion que comprueba el nombre
    public static boolean compruebaNombre(String Nombre){
        boolean valido=false;
        String[] nombreCompleto=Nombre.split(" ");
        System.out.println(nombreCompleto.length);
        if (nombreCompleto.length > 2) {
            valido=true;
        }else{
            System.out.println("nombre no valido");
        }
        return valido;
    }
    
    //funcion que pide el nombre
    public static String insertaNombre(){
        Scanner teclado=new Scanner(System.in);
        String titular="";
        boolean nombreCorrecto=false;
        while(nombreCorrecto==false){
        System.out.println("inserta el titular de la cuenta");
        titular=teclado.nextLine();
        nombreCorrecto=compruebaNombre(titular);
        }
        return titular;
    }
    
    //funcion que mueestra el menu de opciones y realiza las acciones
    public static void menuOpciones(String numeroCuenta, String titular){
        Scanner teclado=new Scanner(System.in);
        CuentaBancaria nuevaCuenta=new CuentaBancaria(numeroCuenta, titular);
        int opcion=0;
        while (opcion!=6) {
            
            //mostramos por panatalla las opciones disponibles
            System.out.println("Que desea realizar:\n1- hacer un ingreso\n2- retirar saldo"
                + "\n3- mostrar movimientos de ingreso\n4- mostrar movimientos de retirada"
                + "\n5- mostrar saldo disponible\n6- Salir");
            opcion=teclado.nextInt();
            
            //Realizamos las acciones segun el valor de entrada  
            switch (opcion) {
                case 1:
                    System.out.println("¿cuanto desea ingresar?");
                    double ingreso=teclado.nextInt();
                    nuevaCuenta.ingresarSaldo(ingreso);
                    break;
                case 2:
                    System.out.println("¿cuanto desea retirar?");
                    double retirar=teclado.nextInt();
                    nuevaCuenta.retirarSaldo(retirar);
                    break;
                case 3:
                    nuevaCuenta.getIngresos();
                    break;
                case 4:
                    nuevaCuenta.getRetiradas();
                    break;
                case 5:
                    System.out.println(nuevaCuenta.getSaldo());
                    break;
                case 6:
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
}
