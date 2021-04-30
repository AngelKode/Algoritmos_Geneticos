/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos_geneticos;

import Damas.Damas;
import TSP.TSP;
import datos.Tokenizador;
import datos.TokenizadorCiudades;
import graficador.Graficador;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author depot
 */
public class Algoritmos_Geneticos {

    /**
     * @param args the command line arguments
     * @throws java.lang.CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        // TODO code application logic here
        
        System.out.println("Calculando...");
        TSP tsp = new TSP();
        
        Graficador graficador = new Graficador();
        int n_ciudades = 8;
        int siguientesCiudades = 0;
        int contador = 1;
        int ciudades[] = new int[5];
        ciudades[0] = 4;
        ciudades[1] = 5;
        ciudades[2] = 10;
        ciudades[3] = 15;
        ciudades[4] = 20;
        
        boolean mejor_individuo_alcanzado;
        
        for(n_ciudades = 4;contador < 5;n_ciudades = siguientesCiudades){
            System.out.println("N: " + n_ciudades);
            long tiempo_inicial = System.currentTimeMillis();
            mejor_individuo_alcanzado = tsp.evolucionarCiudades(200, 100, 0.25, 0.020, n_ciudades);
            long tiempo_total = System.currentTimeMillis() - tiempo_inicial;
            System.out.println("Tiempo tomado: " + tiempo_total);
            graficador.agregarSerie(n_ciudades+"", tiempo_total,mejor_individuo_alcanzado);
            siguientesCiudades = ciudades[contador];
            contador++;
        }
        
        graficador.initGraph();
        JFrame frameGrafica = new JFrame();
        frameGrafica.setVisible(true);
        frameGrafica.setLocationRelativeTo(null);
        frameGrafica.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameGrafica.add(graficador.getChartPanel());
         
        
        
       
        
    }
    
}
