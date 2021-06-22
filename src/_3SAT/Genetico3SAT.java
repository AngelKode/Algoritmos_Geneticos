/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3SAT;

import datos.Tokenizador3SAP;
import interfaces.GeneticoBases;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author depot
 */
public class Genetico3SAT implements GeneticoBases,Cloneable{
    
    private int genotipo[];
    private long fenotipo;
    private long fitness;
    private final ArrayList<int[]> elementosPrueba;
    
    public Genetico3SAT(int genotipo[]) throws IOException{
        this.genotipo = genotipo;
        this.fenotipo = 0;
        this.fitness = 0;
        this.elementosPrueba = new Tokenizador3SAP(550).leerDatos();
    }
    
    @Override
    public void obtenerFitness() {
        //Vamos a calcular el fitness de cada invididuo de acuerdo a la cantidad de satisfacibilidad booleana
        //El número total es el máximo, y si un elemento no cumple, se le va restando
        this.fitness = 550;
       
        for(int[] prueba : this.elementosPrueba){
            
            //Obtenemos los valores que vamos a evaluar del genotipo
            int numeros[] = new int[3];
            //Si el valor de prueba es negativo, obtenemos el NOT de ese bit
            //Si no, obtenemos el valor en esa posicion nadamas, y asi para las 3 posiciones
            if(prueba[0] < 0){
                numeros[0] = (this.genotipo[(prueba[0] * -1) - 1] == 0) ? 1 : 0;
            }else{
                numeros[0] = this.genotipo[prueba[0] - 1];
            }
            
            if(prueba[1] < 0){
                numeros[1] = (this.genotipo[(prueba[1] * -1) - 1] == 0) ? 1 : 0;
            }else{
                numeros[1] = this.genotipo[prueba[1] - 1];
            }
            
            if(prueba[2] < 0){
                numeros[2] = (this.genotipo[(prueba[2] * -1) - 1] == 0) ? 1 : 0;
            }else{
                numeros[2] = this.genotipo[prueba[2] - 1];
            }
            
            boolean valores[] = new boolean[3];
            boolean valores2[] = new boolean[3];
            valores[0] = (numeros[0] == 1);
            valores[1] = (numeros[1] == 1);
            valores[2] = (numeros[2] == 1);
            //Si no cumple, restamos 1
            if(!(valores[0] || valores[1] || valores[2])){
                this.fitness--;
            }
        }
    }

    @Override
    public void obtenerFenotipo() {
        
    }

    @Override
    public int[] getGenotipo() {
        return this.genotipo;
    }

    @Override
    public void setGenotipo(int[] genotipo) {
        this.genotipo = genotipo;
    }

    @Override
    public long getFenotipo() {
        return this.fenotipo;
    }

    @Override
    public void setFenotipo(long fenotipo) {
        this.fenotipo = fenotipo;
    }

    @Override
    public long getFitness() {
        return this.fitness;
    }

    @Override
    public void setFitness(long fitness) {
        this.fitness = fitness;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
}
