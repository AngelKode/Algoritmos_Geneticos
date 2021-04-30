/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Damas;

import interfaces.OperadoresBases;
import static interfaces.OperadoresBases.obtenerGenotipoAleatorio;
import static interfaces.OperadoresBases.probabilidadMuta;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author depot
 */
public class OperadoresDamas implements OperadoresBases{
    
    private final ArrayList<GeneticoDamas> individuos;
    private int[] mascara;
    private int individuoEscogido;
    private int cantidad_reinas;

    public OperadoresDamas() {
        this.individuos = new ArrayList<>();
        this.individuoEscogido = 0;
        this.cantidad_reinas = 0;
        this.mascara = null;
    }

    @Override
    public int[] cruza(Object g1, Object g2) throws CloneNotSupportedException {
       //Definimos la máscara la cual nos servirá para la curza
        
        //Obtenemos los genotipos(arreglo de bits) 
        //Pero clonamos la direccion del arreglo para evitar errores
        GeneticoDamas[] geneticos_aux = new GeneticoDamas[2];
        
        GeneticoDamas g1_ = (GeneticoDamas) g1;
        GeneticoDamas g2_ = (GeneticoDamas) g2;
        
        GeneticoDamas g1_aux = (GeneticoDamas) g1_.clone();
        GeneticoDamas g2_aux = (GeneticoDamas) g2_.clone();
        
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
        GeneticoDamas hijoAux = (GeneticoDamas) hijo;
        int[] genotipoNuevo = hijoAux.getGenotipo();
        int bitACambiar = obtenerIndividuoAleatorio(genotipoNuevo.length);
        Random rand = new Random();
        int nuevoBit = rand.nextInt(max_Value);
        genotipoNuevo[bitACambiar] = nuevoBit;
    }

    @Override
    public void evolucion(int poblacion, int generaciones, double probabilidad_cruza, double probabilidad_muta) throws CloneNotSupportedException {
        generarPoblacionAleatoria(poblacion);//Generamos poblaciones aleatorias
        
        for(int numGeneracion = 0;numGeneracion < generaciones;numGeneracion++){
            GeneticoDamas[] nueva_poblacion = new GeneticoDamas[poblacion];//La siguiente generacion
            for(int individuo = 0; individuo < poblacion; individuo++){
                
                //Cruzamos a los padres
                //Pero hasta que la probabilidad de cruza sea mayor
                GeneticoDamas hijo = new GeneticoDamas(null);
                double p;
                do{
                    p = Math.random();
                    //Escogemos 2 individuos aleatorios
                    obtenerIndividuoAleatorio(this.individuos.size());
                    GeneticoDamas padre = (GeneticoDamas) this.individuos.get(this.individuoEscogido).clone();
                    obtenerIndividuoAleatorio(this.individuos.size());
                    GeneticoDamas madre = (GeneticoDamas) this.individuos.get(this.individuoEscogido).clone();
                    this.individuoEscogido = -1;//Para saber que ya escogimos los 2
                    hijo = new GeneticoDamas(cruza(padre,madre));
                }while(p > probabilidad_cruza);//Seguimos ciclando hasta que esos 2 individuos puedan cruzarse
                
                //Una vez teniendo al hijo, vemos si muta o no
                if(probabilidadMuta(probabilidad_muta)){
                    muta(hijo,this.mascara.length);
                }
                
                //Agregamos al hijo
                hijo.obtenerFitness();
                nueva_poblacion[individuo] = hijo;
            }
            //Actualizamos la poblacion que generamos
            for(int numIndividuo = 0; numIndividuo < poblacion;numIndividuo++){
                this.individuos.set(numIndividuo, (GeneticoDamas) nueva_poblacion[numIndividuo].clone());
            }
        }
    }

    @Override
    public GeneticoDamas getMejorIndividuo() throws CloneNotSupportedException {
        GeneticoDamas bestGenetico = (GeneticoDamas) this.individuos.get(0).clone();
        for(GeneticoDamas geneticoActual : this.individuos){
            if(geneticoActual.getFitness() > bestGenetico.getFitness()){
                bestGenetico = (GeneticoDamas) geneticoActual.clone();
            }
        }
        return bestGenetico;
    }

    @Override
    public void generarPoblacionAleatoria(int tamPoblacion) {
        this.individuos.clear();
        for(int i = 0; i < tamPoblacion; i++){
            this.individuos.add(new GeneticoDamas(obtenerGenotipoAleatorio(this.cantidad_reinas,this.cantidad_reinas)));
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
    
    public void setCantidadReinas(int cantidad){
        this.cantidad_reinas = cantidad;
    }
    
    public void setMascara(int numReinas){
        this.mascara = new int[numReinas];
        Random random = new Random();
        for(int i=0;i<numReinas;i++){
            this.mascara[i] = random.nextInt(2);
        }
    }
    
}
