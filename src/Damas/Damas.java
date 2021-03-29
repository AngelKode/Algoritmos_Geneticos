/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Damas;

import bases.Operadores;

/**
 *
 * @author depot
 */
public class Damas{
  
    private final OperadoresDamas operador;

    public Damas() {
        this.operador = new OperadoresDamas();
    }
    
    
    public void evolucionarDamas(int poblacion, int generaciones,double prob_cruza, double prob_muta) throws CloneNotSupportedException{
            
        operador.evolucion(poblacion,generaciones,prob_cruza,prob_muta);
        
        int[] posiciones = new int[operador.getMejorIndividuo().getGenotipo().length];
        posiciones = operador.getMejorIndividuo().getGenotipo();
        System.out.print("Posiciones reinas: ");
        for(int pos: operador.getMejorIndividuo().getGenotipo()){
            System.out.print(pos);
            System.out.print(",");
        }
        System.out.println("");
        System.out.println("Fitness:" + operador.getMejorIndividuo().getFitness());
        if(operador.getMejorIndividuo().getFitness() != 48){
            System.out.println("Casi!");
        }else{
            System.out.println("Mejor individuo alcanzado!");
        }
        System.out.println("");
        
    }
    
    
}
