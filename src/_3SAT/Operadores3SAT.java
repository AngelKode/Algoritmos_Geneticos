/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package _3SAT;

import graficador.Graficador;
import interfaces.OperadoresBases;
import static interfaces.OperadoresBases.obtenerGenotipoAleatorio;
import static interfaces.OperadoresBases.probabilidadMuta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author depot
 */
public class Operadores3SAT  implements OperadoresBases{
    
    private final ArrayList<Genetico3SAT> individuos;
    private int[] mascara;
    private int individuoEscogido;
    private int cantidad_elementos;
    private int cantidadPoblacion, numeroGeneraciones;
    private double probMuta, probCruza;
    private DefaultTableModel modeloTablaGeneraciones;
    private Graficador graficadorTiempos;

    public Operadores3SAT(int poblacion, int generaciones,double prob_cruza, double prob_muta,int cantidad_elementos, JTable table, Graficador grafica) {
        this.individuos = new ArrayList<>();
        this.individuoEscogido = 0;
        this.cantidad_elementos = 0;
        this.mascara = null;
        this.cantidadPoblacion = 0;
        this.numeroGeneraciones = 0;
        this.probCruza = 0.0;
        this.probMuta = 0.0;
        this.modeloTablaGeneraciones = (DefaultTableModel)table.getModel();
        this.graficadorTiempos = grafica;
    }

    @Override
    public int[] cruza(Object g1, Object g2) throws CloneNotSupportedException {
       //Definimos la máscara la cual nos servirá para la curza
        
        //Obtenemos los genotipos(arreglo de bits) 
        //Pero clonamos la direccion del arreglo para evitar errores
        Genetico3SAT[] geneticos_aux = new Genetico3SAT[2];
        
        Genetico3SAT g1_ = (Genetico3SAT) g1;
        Genetico3SAT g2_ = (Genetico3SAT) g2;
        
        Genetico3SAT g1_aux = (Genetico3SAT) g1_.clone();
        Genetico3SAT g2_aux = (Genetico3SAT) g2_.clone();
        
        geneticos_aux[0] = g1_aux;
        geneticos_aux[1] = g2_aux;
        
        //Recorremos
        for(int bit = 0; bit < mascara.length; bit++){
            for(int genetico = 0; genetico < 2; genetico++){
                int aux[] = geneticos_aux[genetico].getGenotipo().clone();
                if(mascara[bit] != 1){
                    if(genetico + 1 < 2){
                        aux[bit] = g2_.getGenotipo()[bit];
                    }else{
                        aux[bit] = g1_.getGenotipo()[bit]; 
                    }
                    geneticos_aux[genetico].setGenotipo(aux);
                }
            }
        }
        //Obtenemos el fenotipo y el fitness de los nuevos hijos
        for(int genetico = 0; genetico < 2; genetico++){
            geneticos_aux[genetico].obtenerFitness();
        }

        if(geneticos_aux[0].getFitness() > geneticos_aux[1].getFitness()){
            return geneticos_aux[0].getGenotipo();
        }

        return geneticos_aux[1].getGenotipo();
    }

    @Override
    public void muta(Object hijo, int max_Value) {
        Genetico3SAT hijoAux = (Genetico3SAT) hijo;
        int[] genotipoNuevo = hijoAux.getGenotipo();
        int bitACambiar = obtenerIndividuoAleatorio(genotipoNuevo.length);
        Random rand = new Random();
        int nuevoBit = rand.nextInt(max_Value);
        genotipoNuevo[bitACambiar] = nuevoBit;
    }

    @Override
    public void evolucion(int poblacion, int generaciones, double probabilidad_cruza, double probabilidad_muta) throws CloneNotSupportedException {
        for(int row = 0; row < this.modeloTablaGeneraciones.getRowCount(); row++){
            this.modeloTablaGeneraciones.removeRow(row);
        } 
        this.graficadorTiempos.removeAll();
        this.cantidadPoblacion = poblacion;
        this.numeroGeneraciones = generaciones;
        this.probCruza = probabilidad_cruza;
        this.probMuta = probabilidad_muta;
        
        generarPoblacionAleatoria(this.cantidadPoblacion);//Generamos poblaciones aleatorias
        for(int numGeneracion = 0;numGeneracion < this.numeroGeneraciones;numGeneracion++){
            long tiempoInicial = System.currentTimeMillis();
            Genetico3SAT[] nueva_poblacion = new Genetico3SAT[this.cantidadPoblacion];//La siguiente generacion
            for(int individuo = 0; individuo < this.cantidadPoblacion; individuo++){
                
                //Cruzamos a los padres
                //Pero hasta que la probabilidad de cruza sea mayor
                Genetico3SAT hijo = null;
                try {
                    hijo = new Genetico3SAT(null);
                } catch (IOException ex) {
                }
                
                double p;
                do{
                    p = Math.random();
                    //Escogemos 2 individuos aleatorios
                    obtenerIndividuoAleatorio(this.individuos.size());
                    Genetico3SAT padre = (Genetico3SAT) this.individuos.get(this.individuoEscogido).clone();
                    obtenerIndividuoAleatorio(this.individuos.size());
                    Genetico3SAT madre = (Genetico3SAT) this.individuos.get(this.individuoEscogido).clone();
                    this.individuoEscogido = -1;//Para saber que ya escogimos los 2
                    try {
                        hijo = new Genetico3SAT(cruza(padre,madre));
                    } catch (IOException ex) {
                    }
                }while(p > this.probCruza);//Seguimos ciclando hasta que esos 2 individuos puedan cruzarse
                
                //Una vez teniendo al hijo, vemos si muta o no
                if(probabilidadMuta(this.probMuta)){
                    muta(hijo,2);
                }
                
                //Agregamos al hijo
                hijo.obtenerFitness();
                nueva_poblacion[individuo] = hijo;
            }
            //Actualizamos la poblacion que generamos
            for(int numIndividuo = 0; numIndividuo < this.cantidadPoblacion;numIndividuo++){
                this.individuos.set(numIndividuo, (Genetico3SAT) nueva_poblacion[numIndividuo].clone());
            }
            //Agregamos un nuevo renglon con los datos del mejor individuo
            this.modeloTablaGeneraciones.addRow(new Object[]{numGeneracion+"",getMejorIndividuo().getFitness()+""});
            //Obtenemos el tiempo total
            long tiempoTotal = System.currentTimeMillis() - tiempoInicial;
            if(getMejorIndividuo().getFitness() == 550){
                this.graficadorTiempos.agregarSerie(numGeneracion+"",tiempoTotal,true);
                this.graficadorTiempos.setColorGraph();
            }else{
                this.graficadorTiempos.agregarSerie(numGeneracion+"",tiempoTotal,false);
                this.graficadorTiempos.setColorGraph();
            }
            
        }
    }

    @Override
    public Genetico3SAT getMejorIndividuo() throws CloneNotSupportedException {
        Genetico3SAT bestGenetico = (Genetico3SAT) this.individuos.get(0).clone();
        for(Genetico3SAT geneticoActual : this.individuos){
            if(geneticoActual.getFitness() > bestGenetico.getFitness()){
                bestGenetico = (Genetico3SAT) geneticoActual.clone();
            }
        }
        return bestGenetico;
    }

    @Override
    public void generarPoblacionAleatoria(int tamPoblacion) {
        this.individuos.clear();
        for(int i = 0; i < tamPoblacion; i++){
            try {
                this.individuos.add(new Genetico3SAT(obtenerGenotipoAleatorio(2,this.cantidad_elementos)));
            } catch (IOException ex) {
            }
        }
    }

    @Override
    public int obtenerIndividuoAleatorio(int maximo) {
        if(this.individuoEscogido != -1){
            Random random = new Random();
            int nuevo = random.nextInt(maximo);
            while(nuevo == this.individuoEscogido){
                nuevo = random.nextInt(maximo);
            }
            individuoEscogido = nuevo;
        }else{
            Random random = new Random();
            individuoEscogido = random.nextInt(maximo);
        }
       
        return individuoEscogido;
    }
    
    public void setCantidadElementos(int cantidad){
        this.cantidad_elementos = cantidad;
    }
    
    public void setMascara(int numElementos){
        this.mascara = new int[numElementos];
        Random random = new Random();
        for(int i=0;i<numElementos;i++){
            this.mascara[i] = random.nextInt(2);
        }
    }
    
    public void setPoblacion(int cantidadPoblacion){
        this.cantidadPoblacion = cantidadPoblacion;
    }
    
    public void setNumGeneraciones(int numGeneraciones){
        this.numeroGeneraciones = numGeneraciones;
    }
    
    public void setCruza(double probCruza){
        this.probCruza = probCruza;
    }
    
    public void setMuta(double probMuta){
        this.probMuta = probMuta;
    }
}
