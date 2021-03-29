/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import interfaces.GeneticoBases;


/**
 *
 * @author depot
 */
public class Genetico implements Cloneable,GeneticoBases{
    
    private int genotipo[];
    private long fenotipo;
    private long fitness;

    public Genetico(int genotipo[]) {
        this.genotipo = genotipo;
        this.fenotipo = 0;
        this.fitness = 0;
    }

    @Override
    public int[] getGenotipo() {
        return genotipo;
    }

    @Override
    public void setGenotipo(int genotipo[]) {
        this.genotipo = genotipo;
    }

    @Override
    public long getFenotipo() {
        return fenotipo;
    }

    @Override
    public void setFenotipo(long fenotipo) {
        this.fenotipo = fenotipo;
    }

    @Override
    public long getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(long fitness) {
        this.fitness = fitness;
    }
    
    @Override
    public void obtenerFenotipo(){
        //Convertimos el binario a decimal
        int potencia = this.genotipo.length - 1;
        for(Integer binario : this.genotipo){
            this.fenotipo += (binario == 1) ? Math.pow(2, potencia) : 0;
            potencia--;
        }
    }
    
    @Override
    public void obtenerFitness(){
       this.fitness = this.fenotipo * 2;//f(x) = 2x                
    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
    
    
}
