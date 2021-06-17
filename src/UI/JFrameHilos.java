/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import _3SAT._3SAT;
import graficador.Graficador;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import listeners.ListenerAplicarCambios;

/**
 *
 * @author depot
 */
public class JFrameHilos extends JFrame{

    
    public JFrameHilos() throws CloneNotSupportedException{
        initComponents();
    }
    
    private void initComponents() throws CloneNotSupportedException{
        //Elementos del JFrame
        //JLabel
        JLabel lblTablaGen,lblGraficaTiempos,lblProbCruza, lblProbMuta, lblNumGeneraciones, lblNumeroPoblacion, lblConfiguraciones;
            lblTablaGen = new JLabel("Generaciones");
                lblTablaGen.setFont(new Font("Arial", Font.BOLD, 18));
            lblGraficaTiempos = new JLabel("Tiempo de Ejecución");
                lblGraficaTiempos.setFont(new Font("Arial", Font.BOLD, 18));
            lblConfiguraciones = new JLabel("Configuraciones");
                lblConfiguraciones.setFont(new Font("Arial", Font.BOLD, 18));
            lblProbMuta = new JLabel("Probabilidad Muta");
            lblProbCruza = new JLabel("Probabilidad de Cruza");
            lblNumGeneraciones = new JLabel("Limite de Generaciones");
            lblNumeroPoblacion = new JLabel("Cantidad de Poblacion");
        //JButton
        JButton btnAplicar = new JButton("Aplicar Cambios");
        JButton btnReiniciarEvolucion = new JButton("Reiniciar");
        //JTextField
        JTextField txtProbCruza, txtProbMuta, txtNumGeneraciones, txtNumeroPoblacion;
            txtProbCruza = new JTextField(12);
                txtProbCruza.setSize(60, 40);
            txtProbMuta = new JTextField(12);
                txtProbMuta.setSize(60, 40);
            txtNumGeneraciones = new JTextField(12);
                txtNumGeneraciones.setSize(60, 40);
            txtNumeroPoblacion = new JTextField(12);
                txtNumeroPoblacion.setSize(60, 40);
        //Grafica
        Graficador grafica = new Graficador();
        grafica.initGraph("Gráfica de tiempos de ejecución por # de Generaciones", "# de Generacion", "Tiempo de Ejecución (ms)");
        //Tabla
        String headers[] = new String[]{"# Generación","Fitness"};
        String data[][] = new String[][]{};
        DefaultTableModel modelo = new DefaultTableModel(data,headers);
        JTable tablaResultados;
            tablaResultados = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaResultados);
            scroll.setSize(scroll.getWidth(), tablaResultados.getHeight());
        
        JPanel panelTablaGeneraciones, panelGraficaTiempos, panelPrincipalCambios,panelConfiguraciones,panelTituloConfiguraciones,panelPrincipalConfiguraciones, panelBotones, panelPrincipal;
        
        //----------------------------Configurando los paneles---------------------------------
        
        //Panel Principal
        panelPrincipal = new JPanel(new BorderLayout(0,10));
        
        //Panel de las tablas
        panelTablaGeneraciones = new JPanel(new BorderLayout());
            JPanel panelLblGen = new JPanel();
            panelLblGen.add(lblTablaGen);
        panelTablaGeneraciones.add(panelLblGen, BorderLayout.NORTH);
        panelTablaGeneraciones.add(scroll, BorderLayout.CENTER);
        panelTablaGeneraciones.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        
        //PanelTiempos
        panelGraficaTiempos = new JPanel(new BorderLayout());
            JPanel panelLblTiempos = new JPanel();
            panelLblTiempos.add(lblGraficaTiempos);
        panelGraficaTiempos.add(panelLblTiempos, BorderLayout.NORTH);
        panelGraficaTiempos.add(grafica.getChartPanel(), BorderLayout.CENTER);
        panelGraficaTiempos.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        //Panel cambios
        panelPrincipalCambios = new JPanel(new GridLayout(1, 2,10,5));
        panelPrincipalCambios.add(panelTablaGeneraciones);
        panelPrincipalCambios.add(panelGraficaTiempos);
        
        
        //Panel Configuraciones
        panelTituloConfiguraciones = new JPanel();
            panelTituloConfiguraciones.add(lblConfiguraciones);
        panelConfiguraciones = new JPanel(new GridLayout(1,4));
            JPanel panelNumGen = new JPanel(new BorderLayout());
            lblNumGeneraciones.setHorizontalAlignment(JLabel.CENTER);
            panelNumGen.add(lblNumGeneraciones, BorderLayout.NORTH);
                JPanel panelTxtFieldNumGen = new JPanel();
                    panelTxtFieldNumGen.add(txtNumGeneraciones);
            panelNumGen.add(panelTxtFieldNumGen, BorderLayout.CENTER);
        panelConfiguraciones.add(panelNumGen);
            JPanel panelProbCruza = new JPanel(new BorderLayout());
            lblProbCruza.setHorizontalAlignment(JLabel.CENTER);
            panelProbCruza.add(lblProbCruza, BorderLayout.NORTH);
                JPanel panelTxtFieldCruza = new JPanel();
                    panelTxtFieldCruza.add(txtProbCruza);
            panelProbCruza.add(panelTxtFieldCruza);
        panelConfiguraciones.add(panelProbCruza, BorderLayout.CENTER);
            JPanel panelProbMuta = new JPanel(new BorderLayout());
            lblProbMuta.setHorizontalAlignment(JLabel.CENTER);
            panelProbMuta.add(lblProbMuta, BorderLayout.NORTH);
                JPanel panelTxtFieldMuta = new JPanel();
                    panelTxtFieldMuta.add(txtProbMuta);
            panelProbMuta.add(panelTxtFieldMuta);
        panelConfiguraciones.add(panelProbMuta, BorderLayout.CENTER);
            JPanel panelNumPoblacion = new JPanel(new BorderLayout());
            lblNumeroPoblacion.setHorizontalAlignment(JLabel.CENTER);
            panelNumPoblacion.add(lblNumeroPoblacion, BorderLayout.NORTH);
                JPanel panelTxtFieldPoblacion = new JPanel();
                    panelTxtFieldPoblacion.add(txtNumeroPoblacion);
            panelNumPoblacion.add(panelTxtFieldPoblacion);
        panelConfiguraciones.add(panelNumPoblacion);
        
        panelPrincipalConfiguraciones = new JPanel(new BorderLayout());
            panelPrincipalConfiguraciones.add(panelTituloConfiguraciones, BorderLayout.NORTH);
            panelPrincipalConfiguraciones.add(panelConfiguraciones, BorderLayout.CENTER);
        //Panel Botones
        _3SAT sat = new _3SAT(100, 10000, 0.45, 0.20,100,tablaResultados, grafica);
        panelBotones = new JPanel();
            btnAplicar.addActionListener(new ListenerAplicarCambios(txtNumGeneraciones,txtNumeroPoblacion,txtProbCruza,txtProbMuta, sat));
            btnReiniciarEvolucion.addActionListener(new ListenerAplicarCambios(txtNumGeneraciones,txtNumeroPoblacion,txtProbCruza,txtProbMuta, sat));
            btnReiniciarEvolucion.setActionCommand("Reiniciar");
            btnAplicar.setActionCommand("Aplicar");
        panelBotones.add(btnAplicar);
        panelBotones.add(btnReiniciarEvolucion);
        //Modificamos los atributos del jframe
        this.setVisible(true);
        this.setTitle("Algoritmos Genéticos");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panelPrincipal);
        panelPrincipal.add(panelPrincipalCambios, BorderLayout.NORTH);
        panelPrincipal.add(panelPrincipalConfiguraciones,BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        this.pack();
        sat.run();
    }
    
    
}
