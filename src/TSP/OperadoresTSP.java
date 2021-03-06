/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TSP;

import interfaces.OperadoresBases;
import static interfaces.OperadoresBases.obtenerGenotipoAleatorio;
import static interfaces.OperadoresBases.probabilidadMuta;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel
 */
public class OperadoresTSP implements OperadoresBases, Runnable{
    
    private ArrayList<GeneticoTSP> individuos, nuevosIndividuos;
    private int[] mascara;
    private int individuoEscogido;
    private int cantidadCiudades;
    private ArrayList<Long> tiemposOperacion;
    private int cantidadPoblacion, numeroGeneraciones;
    private double probabilidadCruza, probabilidadMuta;
    private boolean getMejorIndividuo, isEvolucionando, isChangingPoblacion, canChangePoblacion;
    private DefaultTableModel modelo;

    public OperadoresTSP(int poblacion, int generaciones, double probabilidad_cruza, double probabilidad_muta) {
        this.individuos = new ArrayList<>();
        this.nuevosIndividuos = new ArrayList<>();
        this.cantidadPoblacion = poblacion;
        this.numeroGeneraciones = generaciones;
        this.probabilidadCruza = probabilidad_cruza;
        this.probabilidadMuta = probabilidad_muta;
        this.getMejorIndividuo = false;
        this.isEvolucionando = false;
        this.isChangingPoblacion = false;
        this.canChangePoblacion = false;
    }
    
     @Override
    public int[] cruza(Object g1, Object g2) throws CloneNotSupportedException{
        //Casteamos a tipo GeneticoTSP los objetos g1 y g2, que son la madre y el padre respectivamente
        GeneticoTSP g1_ = (GeneticoTSP) g1;
        GeneticoTSP g2_ = (GeneticoTSP) g2;
        
        //Obtenemos los genotipos(arreglo de bits) 
        //Pero clonamos la direccion del arreglo para evitar errores
        GeneticoTSP[] geneticos_aux = new GeneticoTSP[2];
        geneticos_aux[0] = (GeneticoTSP) g1_.clone();;
        geneticos_aux[1] = (GeneticoTSP) g2_.clone();
        
        //Recorremos la mascara para hacer la cruza
        for(int bit = 0; bit < mascara.length; bit++){
            //Recorremos la cantidad de geneticos, que en este caso ser??an 2,
            //la madre y el padre
            for(int genetico = 0; genetico < 2; genetico++){
                //Obtenemos el genotipo de el individuo en la posicion 'genetico'
                //pudiendo ser la madre o el padre
                int genotipoActual[] = geneticos_aux[genetico].getGenotipo().clone();
                int genotipoAnterior[] = geneticos_aux[genetico].getGenotipo().clone();
                
                //Checamos si el bit de la mascara es '1'
                if(mascara[bit] == 1){
                    //Dependiendo el individuo con el que estemos trabajando, obtendremos el bit
                    if(genetico + 1 < 2){//Padre
                        genotipoActual[bit] = g2_.getGenotipo()[bit];
                    }else{//Madre
                        genotipoActual[bit] = g1_.getGenotipo()[bit]; 
                    }
                    
                    //Actualizamos el genetico con la nueva ciudad agregada en la posicion 'bit' para no tener repetidos
                    for(int posicionArreglo = 0; posicionArreglo < this.mascara.length; posicionArreglo++){
                        if(posicionArreglo != bit){//Si la posicion es diferente para evitar eliminar el que ya se cambi??
                            //Si son iguales, encontramos a la ciudad repetida
                            if(genotipoAnterior[posicionArreglo] == genotipoActual[bit]){
                                //Actualizamos su valor con el arreglo auxiliar que tiene las ciudades antes de hacer 
                                //el cambio del bit
                                genotipoActual[posicionArreglo] = genotipoAnterior[bit];
                            }
                        }
                    }
                    //Actualizamos el genotipo
                    geneticos_aux[genetico].setGenotipo(genotipoActual);
                }
            }
        }
        
        //Obtenemos el fenotipo y el fitness de los nuevos hijos
        for(int genetico = 0; genetico < 2; genetico++){
            geneticos_aux[genetico].obtenerFitness();
        }
        
        //Retornamos el individuo que est?? en la posicion 0 si es mejor individuo que el otro
        //Si no, regresamos el que est?? en la posicion 1
        if(geneticos_aux[0].getFitness() < geneticos_aux[1].getFitness()){
            return geneticos_aux[0].getGenotipo();
        }
        
        return geneticos_aux[1].getGenotipo();
    }
    
    @Override
    public void muta(Object hijo, int max_Value) {
        //Al mutar, tenemos que verificar que no queden ciudades repetidas
        //Generamos un objeto de tipo GenotipoTSP, y obtenemos un bit al azar que
        //ser?? el bit que vamos a mutar
        GeneticoTSP hijoAux = (GeneticoTSP) hijo;
        int[] genotipoNuevo = hijoAux.getGenotipo();
        int posicionCiudadNueva = obtenerIndividuoAleatorio(genotipoNuevo.length);//Obtenemos la posicion a cambiar
        Random rand = new Random();
        int ciudadNueva = rand.nextInt(max_Value);//Obtenemos el valor que tendr?? ese bit
        
        //Como vamos a modificar un bit, checamos que ciudad es, y solamente
        //hacemos un switch entre el valor de esa ciudad con otra
        
        //Encontramos la posicion con el valor igual a 'nuevoBit'
        int posicionCiudadRepetida = 0;
        for(int posicionGenetico = 0; posicionGenetico < genotipoNuevo.length;posicionGenetico++){
            //Da verdadero si encontramos la posicion donde se repite la ciudad que se va a modificar
            if(genotipoNuevo[posicionGenetico] == ciudadNueva){
                posicionCiudadRepetida = posicionGenetico;//Guardamos la posicion donde actualizaremos el valor
                break;
            }
        }
        
        //Obtenemos la posicion de la ciudad que se va a mutar
        int ciudadAnterior = genotipoNuevo[posicionCiudadNueva];
        //Actualizamos el bit donde est?? el valor de la ciudad anterior, es decir
        // si tenemos [1,0,3,2] y se elige cambiar el bit de la posicion 0 y se va a cambiar
        //por el 2,quedaria de la siguiente manera: [2,0,3,1]
        genotipoNuevo[posicionCiudadNueva] = ciudadNueva;
        genotipoNuevo[posicionCiudadRepetida] = ciudadAnterior;
    }

    @Override
    public void evolucion(int poblacion, int generaciones, double probabilidad_cruza, double probabilidad_muta) throws CloneNotSupportedException {
        
    }

    @Override
    public GeneticoTSP getMejorIndividuo() throws CloneNotSupportedException {
        //Recorremos la poblaci??n actual, y obtenemos el mejor individuo, que ser?? aquel
        //que tenga el menor fitness(el recorrido de menor distancia)
        GeneticoTSP bestGenetico = (GeneticoTSP) this.individuos.get(0).clone();
        for(GeneticoTSP geneticoActual : this.individuos){
            if(geneticoActual.getFitness() < bestGenetico.getFitness()){
                bestGenetico = (GeneticoTSP) geneticoActual.clone();
            }
        }
        return bestGenetico;
    }

    @Override
    public void generarPoblacionAleatoria(int tamPoblacion){
        this.individuos.clear();
        for(int i = 0; i < tamPoblacion; i++){
            try {
                this.individuos.add(new GeneticoTSP(obtenerGenotipoAleatorio(this.cantidadCiudades,this.cantidadCiudades)));
            } catch (IOException ex) {
                ex.printStackTrace();
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
    
    public void setCantidadCiudades(int cantidad){
        this.cantidadCiudades = cantidad;
    }
    
    public void setMascara(int numCiudades){
        //Instanciamos el tama??o de la mascara
        this.mascara = new int[numCiudades];
        
        //Creamos una mascara con una sucesi??n de 1010...
        for(int posicionBit = 0; posicionBit < numCiudades; posicionBit++){
            if((posicionBit % 2) == 0 || posicionBit == 0){//Si es par o es la posicion 0
                this.mascara[posicionBit] = 0;
            }else{//Si es immpar
                this.mascara[posicionBit] = 1;
            }
        }
    }
    
    public int getCantidadCiudades(){
        return this.cantidadCiudades;
    }
    
    public ArrayList<Long> getTiemposEjecucion(){
        return this.tiemposOperacion;
    }

    @Override
    public void run(){
        //Inicializo el arreglo de los tiempos de operacion
        this.tiemposOperacion = new ArrayList<>();
        this.isEvolucionando = true;
        generarPoblacionAleatoria(this.cantidadPoblacion);//Generamos poblaciones aleatorias

        //Creo 2 variables para guardar el tiempo inicial y total de ejecui??n
        long tiempoInicial, tiempoTotal;
        
        for(int numGeneracion = 0;numGeneracion < this.numeroGeneraciones;numGeneracion++){
            if(this.isChangingPoblacion){//Checamos si se va a cambiar la poblacion
                try {
                    switchPoblacion();
                } catch (IOException ex) {
                } catch (CloneNotSupportedException ex) {}
            }
            while(this.isChangingPoblacion){}
            this.canChangePoblacion = false;
            tiempoInicial = System.currentTimeMillis();//Obtengo el tiempo inicial
            GeneticoTSP[] nueva_poblacion = new GeneticoTSP[this.cantidadPoblacion];//La siguiente generacion
            for(int individuo = 0; individuo < this.cantidadPoblacion; individuo++){
                
                //Cruzamos a los padres
                //Pero hasta que la probabilidad de cruza sea mayor
                GeneticoTSP hijo = null;
                try {
                    hijo = new GeneticoTSP(new int[this.mascara.length]);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                double p;
                do{
                    p = Math.random();
                    //Escogemos 2 individuos aleatorios
                    obtenerIndividuoAleatorio(this.individuos.size());
                    GeneticoTSP padre = null;
                    GeneticoTSP madre = null;
                    try {
                        padre = (GeneticoTSP) this.individuos.get(this.individuoEscogido).clone();
                        obtenerIndividuoAleatorio(this.individuos.size());
                        madre = (GeneticoTSP) this.individuos.get(this.individuoEscogido).clone();
                        this.individuoEscogido = -1;//Para saber que ya escogimos los 2
                    } catch (CloneNotSupportedException e) {}
                    
                    
                    
                    //Creamos el nuevo hijo que salga de la cruza entre el padre y la madre
                    try {
                        hijo = new GeneticoTSP(cruza(padre,madre));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch(CloneNotSupportedException e){
                        
                    }
                    
                }while(p > this.probabilidadCruza);//Seguimos ciclando hasta que esos 2 individuos puedan cruzarse
                
                //Una vez teniendo al hijo, vemos si muta o no
                if(probabilidadMuta(this.probabilidadMuta)){
                    muta(hijo,this.mascara.length);
                }
                
                //Agregamos al hijo y le obtenemos el fitness
                hijo.obtenerFitness();
                nueva_poblacion[individuo] = hijo;
            }
            //Actualizamos la poblacion que generamos
            try {
                for(int numIndividuo = 0; numIndividuo < this.cantidadPoblacion;numIndividuo++){
                    if(numIndividuo<this.cantidadPoblacion){
                        this.individuos.set(numIndividuo, (GeneticoTSP) nueva_poblacion[numIndividuo].clone());
                    }  
                }
            } catch (CloneNotSupportedException e) {}
            this.canChangePoblacion = true;
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(OperadoresTSP.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Obtengo el tiempo total
            tiempoTotal = System.currentTimeMillis() - tiempoInicial;
            this.tiemposOperacion.add(tiempoTotal);
            try {
                this.modelo.addRow(new Object[]{numGeneracion,Arrays.toString(this.getMejorIndividuo().getGenotipo()),this.getMejorIndividuo().getFitness()});
            } catch (CloneNotSupportedException ex) {}
        }
        this.isEvolucionando = false;
    }
    
    public boolean isEvolucionandoGenetico(){
        return this.isEvolucionando;
    }
    
    public void setModelToUpdate(DefaultTableModel modelo){
        this.modelo = modelo;
    }
    
    public void switchPoblacion() throws IOException, CloneNotSupportedException{
        if(this.individuos.size() > this.nuevosIndividuos.size()){
            //Si es mayor el tama??o, llenamos con el que se manda y los dem??s aleatorios
            this.individuos.clear();
            for(int i=0;i < this.nuevosIndividuos.size();i++){
               this.individuos.add((GeneticoTSP) this.nuevosIndividuos.get(i).clone());
            }
            int siguientePosicion = this.cantidadPoblacion - (this.cantidadPoblacion - this.nuevosIndividuos.size());
            for(int pos = siguientePosicion;pos<this.cantidadPoblacion;pos++){
                this.individuos.add(new GeneticoTSP(obtenerGenotipoAleatorio(this.cantidadCiudades,this.cantidadCiudades)));
            }
        }else if(this.individuos.size() < this.nuevosIndividuos.size()){
            //Si es menor, llenamos hasta que sea el limite
            this.individuos.clear();
            for(int i=0;i<this.cantidadPoblacion;i++){
               this.individuos.add((GeneticoTSP) this.nuevosIndividuos.get(i).clone());
            }
        }else{
            this.individuos = (ArrayList<GeneticoTSP>) nuevosIndividuos.clone();
        }
        
        this.isChangingPoblacion = false;  
    }
    
    public void setNuevaPoblacion(ArrayList<GeneticoTSP> nuevos){
        System.out.println(nuevos.size());
        System.out.println(this.individuos.size());
        this.isChangingPoblacion = true;
        this.nuevosIndividuos = (ArrayList<GeneticoTSP>) nuevos.clone();
    }
    public ArrayList<GeneticoTSP> getPoblacion(){
        return this.individuos;
    }
}
