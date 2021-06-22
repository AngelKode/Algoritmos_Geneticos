/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import TSP.TSP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author Angel
 */
public class ListenerIntercambioPoblación implements ActionListener{
    
    private JFrameIntercambioPoblacion frame;
    private JComboBox<String> comboFrom, comboTo;
    private HashMap<String, TSP> geneticos;
    
    public ListenerIntercambioPoblación(JFrameIntercambioPoblacion frame) {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getActionCommand().equals("AbrirFrame")){
           this.frame.setVisible(true);
       }else if(e.getActionCommand().equals("Intercambiar")){
           //TODO
           //Hacer que funcione el intercambio de poblacion entre geneticos
           //Obtenemos las llaves de cada genetico
           String llave1 = (String)this.comboFrom.getSelectedItem();
           String llave2 = (String)this.comboTo.getSelectedItem();
           
           //Una vez teniendo las llaves, obtenemos cada genetico
           TSP genetico1 = this.geneticos.get(llave1);
           TSP genetico2 = this.geneticos.get(llave2);
           
           //Ya con cada genético lo que basta es hacer ese 'switch', pasando los mejores individuos de uno a otro y visceversa
           genetico1.getOperador().setNuevaPoblacion(genetico2.getOperador().getPoblacion());
           genetico2.getOperador().setNuevaPoblacion(genetico1.getOperador().getPoblacion());
          
       }
    }
    
    public void setComboBox(JComboBox<String> comboFrom, JComboBox<String> comboTo){
        this.comboFrom = comboFrom;
        this.comboTo = comboTo;
    }
    
    public void setGeneticos(HashMap<String, TSP> geneticos){
        this.geneticos = geneticos;
    }
}
