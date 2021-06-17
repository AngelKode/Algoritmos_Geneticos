/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import _3SAT._3SAT;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

/**
 *
 * @author depot
 */
public class ListenerAplicarCambios implements ActionListener{
    
    JTextField numGen, numPob, probCruza, probMuta;
    _3SAT manipulador;
    
    public ListenerAplicarCambios(JTextField txtNumGeneraciones,JTextField txtNumeroPoblacion,JTextField txtProbCruza,JTextField txtProbMuta, _3SAT sat) {
        this.numGen = txtNumGeneraciones;
        this.numPob = txtNumeroPoblacion;
        this.probCruza = txtProbCruza;
        this.probMuta = txtProbMuta;
        this.manipulador = sat;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Aplicar")){
            System.out.println("Quiere cambiar...");
            if(!this.numGen.getText().equals("")){
                this.manipulador.setGeneraciones(Integer.parseInt(this.numGen.getText()));
            }
            if(!this.numPob.getText().equals("")){
                
                this.manipulador.setPoblacion(Integer.parseInt(this.numPob.getText()));
            }
            
            if(!this.probCruza.getText().equals("")){
                this.manipulador.setProbCruza(Double.parseDouble(this.probCruza.getText()));
            }
            
            if(!this.probMuta.getText().equals("")){
                this.manipulador.setProbMuta(Double.parseDouble(this.probMuta.getText()));
            }
        }else if(e.getActionCommand().equals("Reiniciar")){
            this.manipulador.run();
        }
    }
    
}
