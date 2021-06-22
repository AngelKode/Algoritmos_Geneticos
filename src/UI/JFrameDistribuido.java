/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import TSP.TSP;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author Angel Saldivar
 */
public class JFrameDistribuido extends JFrame{

    //Arreglo para almacenar los objetos que estarán evolucionando
    private Object[] geneticosAEvolucionar;
    //Paneles y objetos de la ui
    private JPanel panelPrincipal;//Panel que contendrá todos los objetos que están evolucionando
    private HashMap<String, JLabel> lblProbMutaGeneticos;//Probabilidad de muta de cada genético
    private HashMap<String, JLabel> lblProbCruzaGeneticos;//Probabilidad de cruza de cada genético
    private HashMap<String, JLabel> lblCantidadGeneracionesGeneticos;//Numero de generaciones de cada genético a evolucionar
    private HashMap<String, JLabel> lblCantidadPoblacionGeneticos;//Tamaño de la población de cada genético
    private HashMap<String, JLabel> lblMejorIndividuoGeneticos;//Mejor individuo obtenido de cada genetico
    private HashMap<String, JButton> btnEstadisticasGeneticos;//Botones para ver las estadísticas de cada genético
    private JButton btnIntercambiarPoblacion;

    public JFrameDistribuido(Object[] geneticosAEvolucionar) throws CloneNotSupportedException, InterruptedException{
       this.geneticosAEvolucionar = geneticosAEvolucionar;
       //Inicializamos los objetos
       this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BoxLayout(this.panelPrincipal, BoxLayout.Y_AXIS));
        this.panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(this.panelPrincipal);
       this.lblProbMutaGeneticos = new HashMap<>();
       this.lblProbCruzaGeneticos = new HashMap<>();
       this.lblCantidadGeneracionesGeneticos = new HashMap<>();
       this.lblCantidadPoblacionGeneticos = new HashMap<>();
       this.lblMejorIndividuoGeneticos = new HashMap<>();
       this.btnEstadisticasGeneticos = new HashMap<>();
       this.btnIntercambiarPoblacion = new JButton("Intercambiar Población");
       initComponents();
    }
    
    //TODO
    //Hacer UI para el intercambio de poblaciones entre N geneticos y 
    //tambien implementar el codigo para la interaccion
    
    private void initComponents() throws CloneNotSupportedException, InterruptedException{
        //Listener de los botones
        ListenerEstadisticas listenerBotones = new ListenerEstadisticas();
        //Creamos un hashmap que se le asignara al listener
        HashMap<String, JFrameEstadisticas> mapAux = new HashMap<>();
        //Creamos un HashMap para obtener los objetos que evolucionan a cada genetico
        HashMap<String, TSP> mapTSP = new HashMap<>();
        
        //Aqui vamos a ir añadiendo los paneles de cada genético
        int numeroGenetico = 1;
        for(Object genetico : this.geneticosAEvolucionar){
            //Checamos que tipo de objeto manda
            if(genetico instanceof TSP){
                TSP geneticoAux = (TSP) genetico;
                mapTSP.put(geneticoAux.getKey(), geneticoAux);
                
                //Creamos un panel auxiliar donde irán todos los componentes
                JPanel panelAuxPrincipalGenetico = new JPanel();
                    panelAuxPrincipalGenetico.setLayout(new BoxLayout(panelAuxPrincipalGenetico, BoxLayout.X_AXIS));
                    panelAuxPrincipalGenetico.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
                //Creamos el panel donde iran el titulo, mejor individuo y el boton para ver las estadisticas
                JPanel panelAuxTituloGenetico = new JPanel(new GridLayout(4,1));
                    panelAuxTituloGenetico.setBorder(BorderFactory.createEmptyBorder(0,0,0,5));
                //Creamos el panel donde van los datos del genetico
                JPanel panelAuxDatosGenetico = new JPanel(new GridLayout(4,1));
                    panelAuxPrincipalGenetico.add(panelAuxTituloGenetico);
                    panelAuxPrincipalGenetico.add(panelAuxDatosGenetico);
                    this.panelPrincipal.add(panelAuxPrincipalGenetico);
                    //Separador
                    JSeparator separador = new JSeparator(JSeparator.HORIZONTAL);
                    this.panelPrincipal.add(separador);
                //Vamos agregando cada elemento
                
                //**TITULO**
                JLabel lblTitulo = new JLabel("Genetico " + numeroGenetico + ": " + geneticoAux.getKey());
                //**MEJOR INDIVIDUO**
                JLabel lblMejorIndividuo = new JLabel("Mejor individuo: 0 fitness");
                //**ETIQUETA DE EVOLUCION**
                JLabel lblEvolucionando = new JLabel("Evolucionando...");
                //**BOTON ESTADÍSTICAS**
                JButton btnEstadisticas = new JButton("Estadísticas");
                    //Agregamos su listener
                    btnEstadisticas.addActionListener(listenerBotones);
                    btnEstadisticas.setActionCommand(geneticoAux.getKey());
                //**PROBABILIDAD DE CRUZA**
                JLabel lblProbabilidadMuta = new JLabel("Probabilidad de Muta: " + (int)((double)geneticoAux.getProbMuta() * (double)100.0) + "%");
                //**PROBABLIDAD DE CRUZA**
                JLabel lblProbabilidadCruza = new JLabel("Probabilidad de Cruza: " + (int)((double)geneticoAux.getProbCruza() * (double)100.0) + "%");
                //**NUMERO DE GENERACIONES**
                JLabel lblNumeroGeneraciones = new JLabel("Generaciones: " + geneticoAux.getGeneraciones());
                //**TAMAÑO DE POBLACIÓN**
                JLabel lblNumeroPoblacion = new JLabel("Tamaño de la Población: " + geneticoAux.getPoblacion());
                
                //Agregamos a los paneles
                panelAuxTituloGenetico.add(lblTitulo);
                panelAuxTituloGenetico.add(lblMejorIndividuo);
                panelAuxTituloGenetico.add(lblEvolucionando);
                panelAuxTituloGenetico.add(btnEstadisticas);
                
                panelAuxDatosGenetico.add(lblProbabilidadMuta);
                panelAuxDatosGenetico.add(lblProbabilidadCruza);
                panelAuxDatosGenetico.add(lblNumeroGeneraciones);
                panelAuxDatosGenetico.add(lblNumeroPoblacion);
                
                //Por ultimo, agregamos el elemento al hashmap
                JFrameEstadisticas aux = new JFrameEstadisticas(geneticoAux);
                mapAux.put(geneticoAux.getKey(), aux);
            }else{
                throw new Error("Se esperaban objetos de tipo 'GeneticoTSP'");
            }
            //Aumentamos en 1 el contador
            numeroGenetico++;
        }
        listenerBotones.setJFrames(mapAux);
        //Por ultimo, agregamos el boton para poder intercambiar poblaciones
        JPanel panelBtn = new JPanel();
            JFrameIntercambioPoblacion frame = new JFrameIntercambioPoblacion(mapTSP);
            ListenerIntercambioPoblación listenerPoblacion = new ListenerIntercambioPoblación(frame);
            panelBtn.add(this.btnIntercambiarPoblacion);
            this.btnIntercambiarPoblacion.addActionListener(listenerPoblacion);
            this.btnIntercambiarPoblacion.setActionCommand("AbrirFrame");
        this.panelPrincipal.add(panelBtn);
        
        this.setVisible(true);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
