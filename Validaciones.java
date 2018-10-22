/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;

public class Validaciones
{
      public int validacion (){
        int dato=0;
        boolean error;
        do{
            try{
               error = false;
               Scanner teclado = new Scanner(System.in);
               dato = teclado.nextInt(); 
            }catch(InputMismatchException e){
               System.out.println("Numero ingresado no es valido, intente nuevamente ");  
               error = true;
               
            }
        }while(error);
        return dato;     
    }
}
