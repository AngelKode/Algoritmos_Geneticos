/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos_geneticos;

import Damas.Damas;

/**
 *
 * @author depot
 */
public class Algoritmos_Geneticos {

    /**
     * @param args the command line arguments
     * @throws java.lang.CloneNotSupportedException
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO code application logic here

        Damas damas = new Damas();
        damas.evolucionarDamas(50,1000,0.65,0.002);
    }
    
}
