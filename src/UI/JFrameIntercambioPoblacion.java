/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import TSP.TSP;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Angel
 */
public class JFrameIntercambioPoblacion extends JFrame{
    
    private JPanel panelPrincipal, panelTitulo, panelSelecciones,panelBtn;
    private JLabel lblTitulo, lblSeparacion;
    private JButton btnCambiarPoblación;
    private JComboBox<String> opcionesGeneticosFrom, opcionesGeneticosTo;
    private HashMap<String, TSP> geneticos;
    
    public JFrameIntercambioPoblacion(HashMap<String, TSP> geneticos){
        this.geneticos = geneticos;
        initComponents();
    }
    
    private void initComponents(){
        this.panelPrincipal = new JPanel();
            this.panelPrincipal.setLayout(new BoxLayout(this.panelPrincipal, BoxLayout.Y_AXIS));
        
        this.panelTitulo = new JPanel();
        this.lblTitulo = new JLabel("Seleccione las ciudades que intercambiarán población: ");
        this.lblTitulo.setFont(new Font("Arial",Font.ITALIC,18));
            this.panelTitulo.add(this.lblTitulo);
            
        this.panelSelecciones = new JPanel();
            this.panelSelecciones.setLayout(new BoxLayout(this.panelSelecciones, BoxLayout.X_AXIS));
        this.opcionesGeneticosFrom = new JComboBox<>();
        this.opcionesGeneticosFrom.setToolTipText("Escoga un genético...");
            this.geneticos.forEach((k,v)->{                
                    this.opcionesGeneticosFrom.addItem(v.getKey());
                }        
            );
        this.opcionesGeneticosTo = new JComboBox<>();  
        this.opcionesGeneticosTo.setToolTipText("Escoga un genético");
            this.geneticos.forEach((k,v)->{                
                    this.opcionesGeneticosTo.addItem(v.getKey());
                }        
            );
        this.lblSeparacion = new JLabel("con");
        this.lblSeparacion.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
        
        this.panelSelecciones.add(this.opcionesGeneticosFrom);
        this.panelSelecciones.add(this.lblSeparacion);
        this.panelSelecciones.add(this.opcionesGeneticosTo);
        
        this.panelBtn = new JPanel();
        this.btnCambiarPoblación = new JButton("Intercambiar Población");
        this.btnCambiarPoblación.setActionCommand("Intercambiar");
       
        ListenerIntercambioPoblación listener = new ListenerIntercambioPoblación(this);
        this.btnCambiarPoblación.addActionListener(listener);
        listener.setComboBox(this.opcionesGeneticosFrom, this.opcionesGeneticosTo);
        listener.setGeneticos(this.geneticos);
        
            this.panelBtn.add(this.btnCambiarPoblación);
        this.panelBtn.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        this.panelPrincipal.add(this.panelTitulo);
        this.panelPrincipal.add(this.panelSelecciones);
        this.panelPrincipal.add(this.panelBtn);
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        this.setTitle("Intercambio de Población");
        this.add(this.panelPrincipal);
        this.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
    }
    
    
}
