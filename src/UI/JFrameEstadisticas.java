/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import TSP.GeneticoTSP;
import TSP.OperadoresTSP;
import TSP.TSP;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel
 */
public class JFrameEstadisticas extends JFrame implements Runnable{
    
    private Object geneticoData;
    private DefaultTableModel modeloTable;
    
    public JFrameEstadisticas(Object genetico) throws CloneNotSupportedException, InterruptedException{
        this.geneticoData = genetico;
        initComponents();
    }
   
    private void initComponents() throws CloneNotSupportedException, InterruptedException{
        //Creamos un objeto de tipo TSP
        
        //Creamos los paneles a usar
        JPanel panelPrincipal = new JPanel();
            panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));
        JPanel panelMejoresIndividuos = new JPanel();
        JPanel panelGraficaTiempos = new JPanel();
        
        //Agregamos los paneles al principal
        panelPrincipal.add(panelMejoresIndividuos);
        panelPrincipal.add(panelGraficaTiempos);
        //Agregamos al JFrame
        this.add(panelPrincipal);
        
        //Creamos el modelo de la tabla
        
        JTable tablaData = new JTable();
        JScrollPane scroll = new JScrollPane(tablaData);
        
        this.modeloTable = (DefaultTableModel) tablaData.getModel();
        
        this.modeloTable.addColumn("# de Generación");
        this.modeloTable.addColumn("Genético Resultante");
        this.modeloTable.addColumn("Fitness");

        //Agregamos la tabla al panel
        panelMejoresIndividuos.add(scroll);
        panelMejoresIndividuos.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        this.setVisible(false);
        this.pack();
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    @Override
    public void run() {
        TSP aux = (TSP)this.geneticoData;
        aux.getOperador().setModelToUpdate(this.modeloTable);
        aux.run();
    }
}
