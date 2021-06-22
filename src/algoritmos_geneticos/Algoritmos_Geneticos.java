/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos_geneticos;

import Damas.Damas;
import TSP.TSP;
import UI.JFrameDistribuido;
import UI.JFrameEstadisticas;
import UI.JFrameHilos;
import UI.JFrameIntercambioPoblacion;
import _3SAT._3SAT;
import datos.Tokenizador;
import datos.Tokenizador3SAP;
import datos.TokenizadorCiudades;
import graficador.Graficador;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author Angel
 */
public class Algoritmos_Geneticos {

    /**
     * @param args the command line arguments
     * @throws java.lang.CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException, IOException, InterruptedException {
        // TODO code application logic here
        /*
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
         */
        
        //_3SAT sat = new _3SAT();
        //sat.evolucionarDamas(100, 10000, 0.45, 0.20,100);
        //JFrameHilos hilos = new JFrameHilos();
        TSP tsp1 = new TSP(200,1400,0.65,0.1,10,"Ciudad 1");
        TSP tsp2 = new TSP(120,1200,0.70,0.08,10,"Ciudad 2");
        TSP tsp3 = new TSP(150,1100,0.45,0.06,15,"Ciudad 3");
        TSP tsp4 = new TSP(170,1100,0.39,0.09,10,"Ciudad 4");
        
        JFrameDistribuido frame = new JFrameDistribuido(new Object[]{tsp1,tsp2,tsp3,tsp4});
    }
    
}
