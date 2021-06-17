/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP_Hibrido;

import datos.TokenizadorHibrido;
import interfaces.GeneticoBases;
import java.io.IOException;

/**
 *
 * @author 200an
 */
public class GeneticoTSPHibrido implements GeneticoBases,Cloneable{
    
    private int genotipo[];
    private long fenotipo;
    private long fitness;
    private int matrizDistancias[][];
    private int matrizDisenio[][];

    public GeneticoTSPHibrido(int genotipo[]) throws IOException {
        this.genotipo = genotipo;
        this.fenotipo = 0;
        this.fitness = 0;
        TokenizadorHibrido token = new TokenizadorHibrido(genotipo.length);
        this.matrizDistancias = token.leerDatos();//Obtengo las distancias
        this.matrizDisenio = token.obtenerDisenioPiso();//Obtengo el tipo de diseño, si el camino es de subida, bajada o normal
    }
    
    
    @Override
    public void obtenerFitness() {
        //Calculamos el fitness de acuerdo a la distancia que se da de acuerdo al genetico que se tiene
        //y de acuerdo al tipo de diseño
        long sumatoria = 0;
        for(int posicionGenetico = 0; posicionGenetico < this.genotipo.length - 1; posicionGenetico++){
            int valor = this.matrizDistancias[this.genotipo[posicionGenetico]][this.genotipo[posicionGenetico + 1]];
            int disenio = this.matrizDisenio[this.genotipo[posicionGenetico]][this.genotipo[posicionGenetico + 1]];
            
            valor += disenio;
            
            sumatoria += valor;
        }
        //Aqui calculamos la distancia entre la ultima ciudad y la ciudad de inicio y le sumamos el valor del diseño
        int disenio = this.matrizDisenio[this.genotipo[this.genotipo.length - 1]][this.genotipo[0]];
        sumatoria += this.matrizDistancias[this.genotipo[this.genotipo.length - 1]][this.genotipo[0]] + disenio;
        this.fitness = sumatoria;
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
    
    public void setMatrizDistancias(int[][] matrizDistancias){
        this.matrizDistancias = matrizDistancias;
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
     
}
