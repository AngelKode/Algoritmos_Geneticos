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
         //Evaluamos el fenotipo
        this.fitness = 48;
        //Recorremos todas las reinas
        for(int posicionGenotipo = 0; posicionGenotipo < this.genotipo.length;posicionGenotipo++){
            /*
            Evaluamos la reina en la posicion 'posicionGenotipo' con la reina en la posicion
            'posiciones_reinas'
            */
            for(int posiciones_reinas = 0;posiciones_reinas < this.genotipo.length;posiciones_reinas++){
                //Verificamos que no sea la comparacion entre la misma reina para evitar errores
                if(posicionGenotipo != posiciones_reinas){
                    //Comparamos si la reina actual está al mismo nivel que la reina que estamos comparando
                    //Si es asi, se atacan horizontalmente
                    if(this.genotipo[posicionGenotipo] == this.genotipo[posiciones_reinas]){
                            //Restamos 1 por tener un ataque
                            this.fitness--;
                    }
                    
                    int distancia_entre_ellas = posicionGenotipo - posiciones_reinas;
                    //Si es negativo, lo convertimos a positivo
                    distancia_entre_ellas = (distancia_entre_ellas < 0) ? distancia_entre_ellas * -1 : distancia_entre_ellas;
                    //Ahora checamos si la posicion de la reina actual se cruza diagonalmente
                    //con las demas reinas
                    
                    if(this.genotipo[posiciones_reinas] == (this.genotipo[posicionGenotipo] - distancia_entre_ellas)){
                        //Verificamos la diagonal superior hacia la izquierda o a la derecha
                        this.fitness--;
                    }else if(this.genotipo[posiciones_reinas] == (this.genotipo[posicionGenotipo] + distancia_entre_ellas)){
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
