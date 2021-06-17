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
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author depot
 */
public class Tokenizador3SAP {
    
    private BufferedReader bufferLector;
    private final PrintWriter escritor;
    private final int cantidadElementos;

    public Tokenizador3SAP(int cantidadElementos) {
        this.bufferLector = null;
        this.escritor = null;
        this.cantidadElementos = cantidadElementos;
    }
    
    
    public ArrayList<int[]> leerDatos() throws FileNotFoundException, IOException{
        ArrayList<int[]> instanciasPrueba = new ArrayList<>();
        
        //Creamos un buffer para manipular el flujo
        File nuevoArchivo = new File("C:\\Users\\depot\\desktop\\G2-100-"+this.cantidadElementos + "-1.txt");
        FileReader fr = new FileReader(nuevoArchivo);
        this.bufferLector = new BufferedReader(fr);
        String textoAcumulado = "", textoLeido = "";
        
        //Leemos linea por linea el archivo de texto
        while((textoLeido = this.bufferLector.readLine()) != null){
            textoAcumulado += textoLeido + " ";
        }
        
        //Ya teniendo todo el contenido del archivo, lo tokenizamos
        StringTokenizer tokenizador = new StringTokenizer(textoAcumulado, " ");
        int[] pruebas = new int[3];
        int contador = 0;

        while(tokenizador.hasMoreTokens()){
            
            if(contador == 3){
                instanciasPrueba.add(pruebas.clone());
                contador = 0;//Reiniciamos el contador
                pruebas = new int[3];
            }else{
                String valor = tokenizador.nextToken();
                pruebas[contador] = Integer.parseInt(valor);
                contador++;
            }
                    
        }
        //Agregamos el ultimo elemento de prueba
        instanciasPrueba.add(pruebas.clone());
        
        return instanciasPrueba;
    }
}
