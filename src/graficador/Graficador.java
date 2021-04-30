/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficador;

import java.awt.Color;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author depot
 */
public class Graficador{
    
    private JFreeChart xyChart;
    private ChartPanel panel;
    private final XYSeriesCollection dataSet;
    private int cantidad_series;
    private long posicion_y_anterior;
    private final ArrayList<Boolean> mejor_individuo_alcanzado;

    public Graficador() {
        this.xyChart = null;
        this.panel = null;
        this.dataSet = new XYSeriesCollection();
        this.cantidad_series = 0;
        this.posicion_y_anterior = -1;
        this.mejor_individuo_alcanzado = new ArrayList<>();
    }
    
    public ChartPanel getChartPanel(){
        return this.panel;
    }
    
    public void agregarSerie(String n, long tiempo,boolean logrado) throws CloneNotSupportedException{
        XYSeries serieAux = new XYSeries("N_"+n);//Creamos la serie
        this.mejor_individuo_alcanzado.add(logrado);
        if(this.posicion_y_anterior == -1){
            serieAux.add(Double.parseDouble(n),0);
            serieAux.add(Double.parseDouble(n), tiempo);//Agregamos los puntos
        }else{
            serieAux.add(Double.parseDouble(n) - 1,this.posicion_y_anterior);
            serieAux.add(Double.parseDouble(n), tiempo);
            serieAux.add(Double.parseDouble(n),0);
        }
        this.posicion_y_anterior = tiempo;
        this.dataSet.addSeries((XYSeries) serieAux.clone());
        this.cantidad_series++;//Aumentamos la cantidad de series
    }
    public void initGraph(){
        createJFreeChart();//Creamos el JFreeChart
    }
    
    
    private void createJFreeChart(){
        this.xyChart = ChartFactory.createXYLineChart("Histograma",
                                                      "N reinas",
                                                      "Tiempo ejecución (ms)",
                                                      this.dataSet,
                                                      PlotOrientation.VERTICAL,
                                                      true,true,false);
        setColorGraph();
        this.panel = new ChartPanel(this.xyChart);
    }

    private void setColorGraph() {
        XYPlot plot = this.xyChart.getXYPlot();
        XYLineAndShapeRenderer render = new XYLineAndShapeRenderer();
        for(int serie = 0; serie < this.cantidad_series;serie++){
            if(this.mejor_individuo_alcanzado.get(serie)){
                render.setSeriesPaint(serie, Color.GREEN);
            }else{
                render.setSeriesPaint(serie, Color.RED);
            }
        }
        plot.setRenderer(render);
    }
    
    
}
