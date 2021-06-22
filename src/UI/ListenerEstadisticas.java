/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import TSP.TSP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 200an
 */
public class ListenerEstadisticas implements ActionListener{
    
    private HashMap<String,JFrameEstadisticas> frameEstadisticas;
    
    public ListenerEstadisticas() {
        this.frameEstadisticas = new HashMap<>();
    }
    
    public void setJFrames(HashMap<String, JFrameEstadisticas> operadores){
        this.frameEstadisticas = operadores;
        ExecutorService executor = Executors.newFixedThreadPool(operadores.size());
        operadores.forEach((k,v) -> executor.execute(v));//Para cada jframe de las estadisticas y para cada genetico, ejecutamos su hilo
        executor.shutdown();//Cuando se terminen todos, apagamos
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
            JFrameEstadisticas aux = (JFrameEstadisticas)this.frameEstadisticas.get(e.getActionCommand());
            aux.setVisible(true);
    }
    
}
