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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author depot
 */
public class Tokenizador {
    
    private BufferedReader bufferLector;
    private PrintWriter escritor;
    private int cantidadBits;

    public Tokenizador(int cantidadBits) throws FileNotFoundException, IOException{
        this.bufferLector = null;
        this.escritor = null;
        this.cantidadBits = cantidadBits;
    }
    
    public ArrayList<String> obtenerDatos(String tema, int numGeneracion) throws IOException{
        ArrayList<String> datos = new ArrayList<>();
        //Creamos un buffer para manipular el flujo
        File nuevoArchivo = new File("resultados_"+tema+"_"+numGeneracion+".txt");
        FileReader fr = new FileReader(nuevoArchivo);
        this.bufferLector = new BufferedReader(fr);
        String textoAcumulado = "", textoLeido = "";
        
        //Leemos linea por linea el archivo de texto
        while((textoLeido = this.bufferLector.readLine()) != null){
            textoAcumulado += textoLeido + ",";
        }
        
        //Ya teniendo todo el contenido del archivo, lo tokenizamos
        StringTokenizer tokenizador = new StringTokenizer(textoAcumulado, ",");
        String genotipo = "";
        int contador = 0;
        
        while(tokenizador.hasMoreTokens()){
            //Agregamos los datos al array
            String valor = tokenizador.nextToken();
            if(contador == this.cantidadBits){
                contador = 0;
                datos.add(genotipo);
                genotipo = "";
                genotipo += valor;
            }else{
                contador++;
                genotipo += valor;
            }
                    
        }
        datos.add(genotipo);
        fr.close();
        return datos;
    }
    
    public void guardarDatos(ArrayList<String> datos,String tema,int numGeneracion) throws FileNotFoundException, IOException{
        File nuevoArchivo = new File("resultados_"+tema+"_"+numGeneracion+".txt");
        nuevoArchivo.createNewFile();
        FileWriter fw = new FileWriter(nuevoArchivo,true);
        this.escritor = new PrintWriter(fw);
        
        for(String dato : datos){
            this.escritor.append("\n"+dato);
        }

        fw.close();
    }
    
}
