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
    
    
    public boolean evolucionarDamas(int poblacion, int generaciones,double prob_cruza, double prob_muta,int cantidad_reinas) throws CloneNotSupportedException{
        
        operador.setMascara(cantidad_reinas);//Creamos una máscara aleatoria
        operador.setCantidadReinas(cantidad_reinas);//Seteamos la cantidad de reinas
        operador.evolucion(poblacion,generaciones,prob_cruza,prob_muta);//Empezamos a evolucionar
        
        System.out.print("Posiciones reinas: ");
        for(int pos: operador.getMejorIndividuo().getGenotipo()){
            System.out.print(pos);
            System.out.print(",");
        }
        System.out.println("");
        System.out.println("Fitness:" + operador.getMejorIndividuo().getFitness());
        if(operador.getMejorIndividuo().getFitness() != (operador.getMejorIndividuo().getGenotipo().length*6)){
            return false;
        }
        return true;
        
    }
    
    
}
