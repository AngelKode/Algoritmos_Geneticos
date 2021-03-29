/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bases;

import interfaces.OperadoresBases;
import Damas.GeneticoDamas;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author depot
 */
public class Operadores implements OperadoresBases{
    
    private ArrayList<Genetico> individuos;
    private final int[] mascara = new int[]{1,0,1,0,1,0,1,0};
    private int individuoEscogido;

    public Operadores() {
        this.individuos = new ArrayList<>();
        this.individuoEscogido = 0;
    }
        
    /*TODO
    Metodo de cruza
    Recibe 2 inviduos
    Mascara de cruza
    Nos regresa la representacion genotipica del mejor hijo
    */
    
    @Override
    public int[] cruza(Object g1, Object g2) throws CloneNotSupportedException{
        //Definimos la máscara la cual nos servirá para la curza
        
        //Obtenemos los genotipos(arreglo de bits) 
        //Pero clonamos la direccion del arreglo para evitar errores
        Genetico[] geneticos_aux = new Genetico[2];
        
        Genetico g1_ = (Genetico) g1;
        Genetico g2_ = (Genetico) g2;
        
        Genetico g1_aux = (Genetico) g1_.clone();
        Genetico g2_aux = (Genetico) g2_.clone();
        
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
        Genetico hijoAux = (Genetico) hijo;
        int[] genotipoNuevo = hijoAux.getGenotipo();
        int bitACambiar = obtenerIndividuoAleatorio(genotipoNuevo.length);
        Random rand = new Random();
        int nuevoBit = rand.nextInt(max_Value);
        genotipoNuevo[bitACambiar] = nuevoBit;
    }
    
    @Override
    public void evolucion(int poblacion, int generaciones, double probabilidad_cruza, double probabilidad_muta) throws CloneNotSupportedException{

        generarPoblacionAleatoria(poblacion);//Generamos poblaciones aleatorias
        
        for(int numGeneracion = 0;numGeneracion < generaciones;numGeneracion++){
            Genetico[] nueva_poblacion = new Genetico[poblacion];//La siguiente generacion
            for(int individuo = 0; individuo < poblacion; individuo++){
                
                //Cruzamos a los padres
                //Pero hasta que la probabilidad de cruza sea mayor
                Genetico hijo = new Genetico(null);
                double p;
                do{
                    p = Math.random();
                    //Escogemos 2 individuos aleatorios
                    obtenerIndividuoAleatorio(this.individuos.size());
                    Genetico padre = (Genetico) this.individuos.get(this.individuoEscogido).clone();
                    obtenerIndividuoAleatorio(this.individuos.size());
                    Genetico madre = (Genetico) this.individuos.get(this.individuoEscogido).clone();
                    this.individuoEscogido = -1;//Para saber que ya escogimos los 2
                    hijo = new Genetico(cruza(padre,madre));
                }while(p > probabilidad_cruza);//Seguimos ciclando hasta que esos 2 individuos puedan cruzarse
                
                //Una vez teniendo al hijo, vemos si muta o no
                if(probabilidadMuta(probabilidad_muta)){
                    muta(hijo,2);
                }
                
                //Agregamos al hijo
                hijo.obtenerFenotipo();
                hijo.obtenerFitness();
                nueva_poblacion[individuo] = hijo;
            }
            //Actualizamos la poblacion que generamos
            for(int numIndividuo = 0; numIndividuo < poblacion;numIndividuo++){
                this.individuos.set(numIndividuo, (Genetico) nueva_poblacion[numIndividuo].clone());
            }
        }
    }
    
    public void evolucionDamas(int poblacion, int generaciones, double probabilidad_cruza, double probabilidad_muta) throws CloneNotSupportedException{
        
        generarPoblacionAleatoria(poblacion);//Generamos poblaciones aleatorias
        
        for(int numGeneracion = 0;numGeneracion < generaciones;numGeneracion++){
            Genetico[] nueva_poblacion = new Genetico[poblacion];//La siguiente generacion
            for(int individuo = 0; individuo < poblacion; individuo++){
                
                //Cruzamos a los padres
                //Pero hasta que la probabilidad de cruza sea mayor
                Genetico hijo = new Genetico(null);
                double p;
                do{
                    p = Math.random();
                    //Escogemos 2 individuos aleatorios
                    obtenerIndividuoAleatorio(this.individuos.size());
                    Genetico padre = (Genetico) this.individuos.get(this.individuoEscogido).clone();
                    obtenerIndividuoAleatorio(this.individuos.size());
                    Genetico madre = (Genetico) this.individuos.get(this.individuoEscogido).clone();
                    this.individuoEscogido = -1;//Para saber que ya escogimos los 2
                    hijo = new Genetico(cruza(padre,madre));
                }while(p > probabilidad_cruza);//Seguimos ciclando hasta que esos 2 individuos puedan cruzarse
                
                //Una vez teniendo al hijo, vemos si muta o no
                if(probabilidadMuta(probabilidad_muta)){
                    muta(hijo,8);
                }
                
                //Agregamos al hijo
                hijo.obtenerFitness();
                nueva_poblacion[individuo] = hijo;
            }
            //Actualizamos la poblacion que generamos
            for(int numIndividuo = 0; numIndividuo < poblacion;numIndividuo++){
                this.individuos.set(numIndividuo, (Genetico) nueva_poblacion[numIndividuo].clone());
            }
        }
    }
    @Override
    public Genetico getMejorIndividuo() throws CloneNotSupportedException{
        Genetico bestGenetico = (Genetico) this.individuos.get(0).clone();
        for(Genetico geneticoActual : this.individuos){
            if(geneticoActual.getFitness() > bestGenetico.getFitness()){
                bestGenetico = (Genetico) geneticoActual.clone();
            }
        }
        return bestGenetico;
    }
    
    @Override
    public void generarPoblacionAleatoria(int tamPoblacion){
        this.individuos.clear();
        for(int i = 0; i < tamPoblacion; i++){
            this.individuos.add(new Genetico(obtenerGenotipoAleatorio(8,8)));
        }
    }
    
    
    private static int[] obtenerGenotipoAleatorio(int max,int numBits){
        int[] genotipo = new int[numBits];
        Random aleatorio = new Random();
        for(int i = 0; i < genotipo.length;i++){
            genotipo[i] = aleatorio.nextInt(max);
        }
        return genotipo;
    }
    
    @Override
    public int obtenerIndividuoAleatorio(int maximo){
        
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
    
    private boolean probabilidadMuta(double probabilidad){
        return Math.random() < probabilidad;
    }

 
}
