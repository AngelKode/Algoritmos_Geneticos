/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *
 * @author Angel Saldivar
 */
public class TokenizadorHibrido {
    private BufferedReader bufferLector;
    private final PrintWriter escritor;
    private final int cantidadCiudades;

    public TokenizadorHibrido(int cantidadCiudades) {
        this.bufferLector = null;
        this.escritor = null;
        this.cantidadCiudades = cantidadCiudades;
    }
    
    
    public int[][] leerDatos() throws FileNotFoundException, IOException{
        int distancias[][] = new int[this.cantidadCiudades][this.cantidadCiudades];
        
        //Creamos un buffer para manipular el flujo
        File nuevoArchivo = new File("C:\\Users\\200an\\OneDrive\\Escritorio\\prueba"+this.cantidadCiudades+".txt");
        FileReader fr = new FileReader(nuevoArchivo);
        this.bufferLector = new BufferedReader(fr);
        String textoAcumulado = "", textoLeido = "";
        
        //Leemos linea por linea el archivo de texto
        while((textoLeido = this.bufferLector.readLine()) != null){
            textoAcumulado += textoLeido + ",";
        }
        
        //Ya teniendo todo el contenido del archivo, lo tokenizamos
        StringTokenizer tokenizador = new StringTokenizer(textoAcumulado, ",");
        int contador = 0;
        int numeroCiudad = 0;
        
        while(tokenizador.hasMoreTokens()){
            //Agregamos los datos al array
            String valor = tokenizador.nextToken().trim();
            if(!valor.equals(">")){
               if(contador == this.cantidadCiudades - 1){
                    distancias[numeroCiudad][contador] = Integer.parseInt(valor);//Agregamos el siguiente dato
                    contador = 0;//Reiniciamos el contador
                    numeroCiudad++;//Siguiente columna de la matriz
                }else{
                    distancias[numeroCiudad][contador] = Integer.parseInt(valor);
                    contador++;
                } 
                
            }else{
                //Avanzamos hasta el ultimo token
                while(tokenizador.hasMoreTokens()){ tokenizador.nextToken();}
            }         
        }
        //Cerramos el flujo de datos
        this.bufferLector.close();
        return distancias;
    }
    
    public int[][] obtenerDisenioPiso() throws FileNotFoundException, IOException{
        int[][] disenio = new int[this.cantidadCiudades][this.cantidadCiudades];
        
        //Creamos un buffer para manipular el flujo
        File nuevoArchivo = new File("C:\\Users\\200an\\OneDrive\\Escritorio\\prueba"+this.cantidadCiudades+".txt");
        FileReader fr = new FileReader(nuevoArchivo);
        this.bufferLector = new BufferedReader(fr);
        String textoAcumulado = "", textoLeido = "";
        
        //Leemos linea por linea el archivo de texto
        while((textoLeido = this.bufferLector.readLine()) != null){
            textoAcumulado += textoLeido + ",";
        }
        
        //Ya teniendo todo el contenido del archivo, lo tokenizamos
        StringTokenizer tokenizador = new StringTokenizer(textoAcumulado, ",");
        int contador = 0;
        int numeroCiudad = 0;
        boolean dataAvailable = false;
        
        while(tokenizador.hasMoreTokens()){
            //Agregamos los datos al array
            String valor = tokenizador.nextToken().trim();
            if(dataAvailable){
                if(contador == this.cantidadCiudades - 1){
                    disenio[numeroCiudad][contador] = Integer.parseInt(valor);//Agregamos el siguiente dato
                    contador = 0;//Reiniciamos el contador
                    numeroCiudad++;//Siguiente columna de la matriz
                }else{
                    disenio[numeroCiudad][contador] = Integer.parseInt(valor);
                    contador++;
                }
            }else{
                if(valor.equals(">")){
                    dataAvailable = true;
                }  
            }          
        }
        this.bufferLector.close();
        return disenio;
    }
}
