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
        int[] genotipo = new int[numBits];
        Random aleatorio = new Random();
        for(int i = 0; i < genotipo.length;i++){
            genotipo[i] = aleatorio.nextInt(max);
        }
        return genotipo;
    }
    public int obtenerIndividuoAleatorio(int maximo);
    public static boolean probabilidadMuta(double probabilidad){
        return Math.random() < probabilidad;
    }
}
