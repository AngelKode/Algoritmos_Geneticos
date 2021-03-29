/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author depot
 */
public interface GeneticoBases { 
    public void obtenerFitness();
    public void obtenerFenotipo();
    public int[] getGenotipo();
    public void setGenotipo(int genotipo[]);
    public long getFenotipo();
    public void setFenotipo(long fenotipo);
    public long getFitness();
    public void setFitness(long fitness);
}
