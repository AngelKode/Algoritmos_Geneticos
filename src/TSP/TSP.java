/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class TSP implements Runnable{
    
    private final OperadoresTSP operador;
    private int poblacion, generaciones,cantidadCiudades;
    private double probCruza, probMuta;
    private String key;

    public TSP(int poblacion, int generaciones,double probCruza, double probMuta, int cantidadCiudades, String key) {
        this.operador = new OperadoresTSP(poblacion,generaciones,probCruza,probMuta);
        this.poblacion = poblacion;
        this.generaciones = generaciones;
        this.cantidadCiudades = cantidadCiudades;
        this.probCruza = probCruza;
        this.probMuta = probMuta;
        this.key = key;
    }
    
    
    public boolean evolucionarCiudades() throws CloneNotSupportedException{
        operador.setMascara(cantidadCiudades);//Creamos una máscara aleatoria
        operador.setCantidadCiudades(cantidadCiudades);//Seteamos la cantidad de reinas
        operador.run();//Empezamos a evolucionar
        
        return true;
        
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public int getGeneraciones() {
        return generaciones;
    }

    public void setGeneraciones(int generaciones) {
        this.generaciones = generaciones;
    }

    public int getCantidadCiudades() {
        return cantidadCiudades;
    }

    public void setCantidadCiudades(int cantidadCiudades) {
        this.cantidadCiudades = cantidadCiudades;
    }

    public double getProbCruza() {
        return probCruza;
    }

    public void setProbCruza(double probCruza) {
        this.probCruza = probCruza;
    }

    public double getProbMuta() {
        return probMuta;
    }

    public void setProbMuta(double probMuta) {
        this.probMuta = probMuta;
    }
    
    public String getKey(){
        return this.key;
    }
    
    public OperadoresTSP getOperador(){
        return this.operador;
    }
    
    @Override
    public void run() {
        try {
            evolucionarCiudades();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(TSP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
