/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import datos.TokenizadorCiudades;
import interfaces.GeneticoBases;
import java.io.IOException;

/**
 *
 * @author depot
 */
public class GeneticoTSP implements GeneticoBases,Cloneable{
    
    private int genotipo[];
    private long fenotipo;
    private long fitness;
    private int matrizDistancias[][];
   

    public GeneticoTSP(int genotipo[]) throws IOException {
        this.genotipo = genotipo;
        this.fenotipo = 0;
        this.fitness = 0;
        TokenizadorCiudades token = new TokenizadorCiudades(genotipo.length);
        this.matrizDistancias = token.leerDatos();
    }
    
    @Override
    public void obtenerFitness() {
        //Calculamos el fitness de acuerdo a la distancia que se da de acuerdo al genetico que se tiene
        long sumatoria = 0;
        for(int posicionGenetico = 0; posicionGenetico < this.genotipo.length - 1; posicionGenetico++){
            int valor = this.matrizDistancias[this.genotipo[posicionGenetico]][this.genotipo[posicionGenetico + 1]];
            sumatoria += valor;
        }
        //Aqui calculamos la distancia entre la ultima ciudad y la ciudad de inicio
        sumatoria += this.matrizDistancias[this.genotipo[this.genotipo.length - 1]][this.genotipo[0]];
        this.fitness = sumatoria;
    }
    
    @Override
    public void obtenerFenotipo(){
        
    }

    @Override
    public int[] getGenotipo(){
        return this.genotipo;
    }

    @Override
    public void setGenotipo(int[] genotipo){
        this.genotipo = genotipo;
    }

    @Override
    public long getFenotipo(){
        return this.fenotipo;
    }

    @Override
    public void setFenotipo(long fenotipo){
        this.fenotipo = fenotipo;
    }

    @Override
    public long getFitness(){
        return this.fitness;
    }

    @Override
    public void setFitness(long fitness){
        this.fitness = fitness;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
    public void setMatrizDistancias(int[][] matrizDistancias){
        this.matrizDistancias = matrizDistancias;
    }
    
}
