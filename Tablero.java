/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infinitygame;

import java.util.*;
public class Tablero {
   private int tablero[];
   private int saludJugadores[];
   private int posicionJugadores[];
   private int listaJugadores[];
   private int meditacionJugador[];
   Validaciones val = new Validaciones();
   private Random azar = new Random();
   public Tablero(){
       seleccionMenu();
   }
   public void seleccionMenu(){
        int opcion = 0;
        while(opcion!=3){
            menu();
            System.out.println("Ingrese una opcion");
            opcion = val.validacion();
            switch(opcion){
                
                case 1 : 
                        jugadorIndividual();
                        generarTablero();
                        do{ 
                            if(tablero.length<=posicionJugadores[0]|| posicionJugadores[0]<0 ){
                                System.out.println("Game over");
                                seleccionMenu();
                            }   
                            if(saludJugadores[0] == 0){
                                System.out.println("Game over");
                                seleccionMenu();
                            }
                            do{
                                    System.out.println("Ingrese la opcion : 1.- Lanzar dados 2.- Meditar");
                                    opcion = val.validacion();
                                }while(opcion <=0 || opcion >2);
                            switch(opcion){
                                case 1:
                                    lanzarDados(opcion);
                                    posicionJugadores[0]= posicionJugadores[0]+ opcion;
                                        switch(tablero[posicionJugadores[0]]){
                                            case 1:
                                                System.out.println("La posicion actual es "+ posicionJugadores[0]);
                                            case 2:
                                                System.out.println("Cayo en desafio");
                                                casillaDesafio();
                                            break;
                                            case 3:
                                                System.out.println("Cayo en portal");
                                                casillaPortal();
                                            break;
                                            case 4:
                                                System.out.println("Cayo en casilla salud");
                                                casillaSalud();
                                            break;
                                        }
                                    break;
                                case 2 :
                                    meditacion();
                                    break;
                            }
                        }while(saludJugadores[0]>0 || posicionJugadores[0] <= tablero.length);
                        System.out.println("Fin del juego");
                        break;
                case 2 :
                        multijugadores(opcion);
                        listaJugadores = new int[opcion];
                        generarTablero();
                        break;
                case 3 :
                        System.out.println("¡Hasta luego!");
                        System.exit(0);
                        break;
       
                    default:
                        System.out.println("Opcion no valida");
                        break;
                
            }
        }
    }
   public static void menu(){
        System.out.println("Bienvenido a Infinity Game");
        System.out.println("");
        System.out.println("Modo de juego");
        System.out.println("1: Modo Individual");
        System.out.println("2: Multijugador(En mejora...)");
        System.out.println("3: Salir del juego");
    } 
   private int lanzarDados(int dado){
        Random azar = new Random();
        for (int i = 0; i < 2; i++) {
            int lanzamiento = azar.nextInt(6)+1;
            dado = dado + lanzamiento;
        }
        System.out.println("Su lanzamiento es "+ dado);
        return dado;    
   }
   private void generarTablero(){
        int largo= 0;
        do{
            System.out.println("Ingrese el largo del tablero(mayor a 20)");
            largo= val.validacion();
        }while(largo<20);
        tablero = new int[largo];
        for (int i = 0; i < tablero.length; i++) {
           tablero[i]= azar.nextInt(4)+1;// 0 inicio,1 blanco, 2 desafio , 3 portal , 4 salud , 5 final.
        }
        tablero[0]= 0;
        tablero[(tablero.length-1)]= 5;
   }
   private void casillaDesafio(){
        int camino= azar.nextInt(2);
        if(0==camino){
            camino = azar.nextInt(6)-5;
            posicionJugadores[0] = posicionJugadores[0] + camino;
            System.out.println("Usted ha caigo en desafio avanza o retrocede" + camino + " pasos");
        }else{
            camino = azar.nextInt(5)-4;
            posicionJugadores[0] = posicionJugadores[0] + camino;
            if(posicionJugadores[0]<0){
                System.out.println("Game Over");
                saludJugadores[0]= 0;
            }else if(posicionJugadores[0]>tablero.length){
                System.out.println("Game Over");
                saludJugadores[0]= 0;
            }
            System.out.println("Usted ha caigo en desafio gana o pierde" + camino + " puntos de vida");
        }
    }
   private void casillaSalud(){
        int salud = azar.nextInt(3)+1;
        if(saludJugadores[0]== 15){
            System.out.println("Salud actual del jugador es 15 ");
        }else{
                saludJugadores[0]= saludJugadores[0]+ salud;
                if(saludJugadores[0]>= 15){
                     System.out.println("La salud actual es 15");
                     saludJugadores[0]= 15;
                }else{
                     System.out.println("La salud actual es "+ saludJugadores[0]);
                }
           
        }
   }
   private void casillaPortal(){
       int valor = 0;
       do{
          valor = azar.nextInt(tablero.length);
          if(posicionJugadores[0]==tablero[valor]){
              
          } else if(tablero[valor] == 3){
              posicionJugadores[0] = tablero[valor];
          }  
        }while(tablero[valor]!= 3);
       System.out.println("posicion actual "+ posicionJugadores[0]);
   }
   private int multijugadores(int largo){
       do{
          System.out.println("Ingrese la cantidad de jugadores. (mayor a 1)");
          largo = val.validacion();
        }while(largo<=1);
       listaJugadores = new int[largo];
       saludJugadores = new int[largo];
       posicionJugadores = new int[largo];
       meditacionJugador= new int[largo];
       for(int i = 0; i<largo;i++){
            saludJugadores[i] = 15 ;
            posicionJugadores[i]= 0;
            meditacionJugador[i] = 5;
       }
       return largo;
   }
   private void jugadorIndividual(){
       listaJugadores = new int[1];
       saludJugadores = new int[1];
       posicionJugadores = new int[1];
       meditacionJugador = new int[1];
       meditacionJugador[0]= 5;
       saludJugadores[0] = 15 ;
       posicionJugadores[0]= 0;
   }
   private void meditacion(){
       if(meditacionJugador[0]> 0){
           meditacionJugador[0]= meditacionJugador[0] -1;
           System.out.println("La salud del jugador actual es "+ saludJugadores[0]);
           System.out.println("Quedan estas meditaciones "+ meditacionJugador[0]);
       }else{
          saludJugadores[0]= saludJugadores[0] - 1;
          System.out.println("La salud es "+ saludJugadores[0]);
          System.out.println("Ya no quedan meditaciones, empezo a restar 1 de vida");
       }
   }
}
