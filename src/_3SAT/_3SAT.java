/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3SAT;

import graficador.Graficador;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author depot
 */
public class _3SAT implements Runnable{
    
    private final Operadores3SAT operador;
    int poblacion, generaciones,cantidad_elementos;
    double prob_cruza, prob_muta;
    
    public _3SAT(int poblacion, int generaciones,double prob_cruza, double prob_muta,int cantidad_elementos, JTable table, Graficador grafica) {
        this.poblacion = poblacion;
        this.generaciones = generaciones;
        this.cantidad_elementos = cantidad_elementos;
        this.prob_cruza = prob_cruza;
        this.prob_muta = prob_muta;
        this.operador = new Operadores3SAT( poblacion, generaciones,prob_cruza,prob_muta,cantidad_elementos, table,grafica);
    }
    
    public int getPoblacion(){
        return this.poblacion;
    }
    public boolean evolucionarDamas() throws CloneNotSupportedException{  
        operador.setMascara(this.cantidad_elementos);//Creamos una máscara aleatoria
        operador.setCantidadElementos(this.cantidad_elementos);//Seteamos la cantidad de elementos
        operador.evolucion(this.poblacion,this.generaciones,this.prob_cruza,this.prob_muta);//Empezamos a evolucionar
        
        System.out.print("Resultado mejor invididuo: ");
        for(int pos: operador.getMejorIndividuo().getGenotipo()){
            System.out.print(pos);
            System.out.print(",");
        }
        System.out.println("");
        System.out.println("Fitness:" + operador.getMejorIndividuo().getFitness());
        
        if(operador.getMejorIndividuo().getFitness() == 550){
            return true;
        }
        return false;
    }
    
    public void setPoblacion(int numPoblacion){
        this.operador.setPoblacion(numPoblacion);
    }
    
    public void setGeneraciones(int numGeneraciones){
        this.operador.setNumGeneraciones(numGeneraciones);
    }
    
    public void setProbCruza(double probCruza){
        this.operador.setCruza(probCruza);
    }
    
    public void setProbMuta(double probMuta){
        this.operador.setMuta(probMuta);
    }

    @Override
    public void run() {
        try {
            evolucionarDamas();
        } catch (CloneNotSupportedException ex) {}
    }
    
}
