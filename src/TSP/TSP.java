/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

/**
 *
 * @author depot
 */
public class TSP {
    private final OperadoresTSP operador;

    public TSP() {
        this.operador = new OperadoresTSP();
    }
    
    
    public boolean evolucionarCiudades(int poblacion, int generaciones,double prob_cruza, double prob_muta, int cantidadCiudades) throws CloneNotSupportedException{
        
        
        operador.setMascara(cantidadCiudades);//Creamos una máscara aleatoria
        operador.setCantidadCiudades(cantidadCiudades);//Seteamos la cantidad de reinas
        operador.evolucion(poblacion,generaciones,prob_cruza,prob_muta);//Empezamos a evolucionar
        
        System.out.print("Recorrido mejores ciudades: ");
        for(int pos: operador.getMejorIndividuo().getGenotipo()){
            System.out.print(pos);
            System.out.print(",");
        }
        System.out.println("");
        System.out.println("Fitness:" + operador.getMejorIndividuo().getFitness());
        return true;
        
    }
    
}
