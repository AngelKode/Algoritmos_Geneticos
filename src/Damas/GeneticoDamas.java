/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Damas;

import interfaces.GeneticoBases;

/**
 *
 * @author depot
 */
public class GeneticoDamas implements GeneticoBases,Cloneable{
    
    private int genotipo[];
    private long fenotipo;
    private long fitness;
    
    public GeneticoDamas(int genotipo[]) {
        this.genotipo = genotipo;
        this.fenotipo = 0;
        this.fitness = 0;
    }
    
    @Override
    public void obtenerFitness() {
        //Multiplicamos la cantidad de reinas * 6, ya que es el máximo de ataques
        this.fitness = this.genotipo.length * 6;
        //Recorremos todas las reinas
        for(int reina_actual = 0; reina_actual < this.genotipo.length;reina_actual++){
            //Evaluamos la reina en la posicion 'posicionGenotipo' con las demás reinas
            for(int reina_comparar = 0;reina_comparar < this.genotipo.length;reina_comparar++){
                //Verificamos que no sea la comparacion entre la misma reina para evitar errores
                if(reina_actual != reina_comparar){
                    //Comparamos si la reina actual está al mismo nivel que la reina que estamos comparando
                    //Si es asi, se atacan horizontalmente
                    if(this.genotipo[reina_actual] == this.genotipo[reina_comparar]){
                            //Restamos 1 por tener un ataque
                            this.fitness--;
                    }
                    //Obtenemos la distancia entre las 2 reinas que se están comparando
                    int distancia_entre_ellas = reina_actual - reina_comparar;
                    //Si es negativo, lo convertimos a positivo
                    distancia_entre_ellas = (distancia_entre_ellas < 0) ? distancia_entre_ellas * -1 : distancia_entre_ellas;
                    
                    //Ahora checamos si la posicion de la reina actual se cruza diagonalmente con las demas reinas
                    if(this.genotipo[reina_comparar] == (this.genotipo[reina_actual] - distancia_entre_ellas)){
                        //Verificamos la diagonal superior hacia la izquierda o a la derecha
                        this.fitness--;
                    }else if(this.genotipo[reina_comparar] == (this.genotipo[reina_actual] + distancia_entre_ellas)){
                        //Verificamos la diagonal inferior hacia la izquierda o a la derecha
                        this.fitness--;
                    }
                } 
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
