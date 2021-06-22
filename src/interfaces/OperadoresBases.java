/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.Random;

/**
 *
 * @author depot
 */
public interface OperadoresBases{
    public int[] cruza(Object g1, Object g2) throws CloneNotSupportedException;
    public void muta(Object hijo, int max_Value);
    public void evolucion(int poblacion, int generaciones, double probabilidad_cruza, double probabilidad_muta) throws CloneNotSupportedException;
    public Object getMejorIndividuo() throws CloneNotSupportedException;
    public void generarPoblacionAleatoria(int tamPoblacion);
    public static int[] obtenerGenotipoAleatorio(int max,int numBits){
        
        //FOR TSP
        int[] genotipo = new int[numBits];
        for(int posicion = 0; posicion < numBits; posicion++){
            genotipo[posicion] = -1;
        }
        Random aleatorio = new Random();
        for(int i = 0; i < genotipo.length;i++){
            int nuevo = aleatorio.nextInt(max);
            for(int j=0; j<=i;j++){
                if(nuevo == genotipo[j]){
                    nuevo = aleatorio.nextInt(max);
                    j = -1;
                }
            }
            genotipo[i] = nuevo;
        }
        
        //FOR 3SAT
//        int[] genotipo = new int[numBits];
//        Random aleatorio = new Random();
//        for(int i = 0; i < numBits ; i++){
//            int nuevo = aleatorio.nextInt(2);
//            genotipo[i] = nuevo;
//        }
        return genotipo;
    }
    public int obtenerIndividuoAleatorio(int maximo);
    public static boolean probabilidadMuta(double probabilidad){
        return Math.random() < probabilidad;
    }
}
