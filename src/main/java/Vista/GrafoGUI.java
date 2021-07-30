package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Station;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GrafoGUI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel6;
    private JPanel panel4;
    private JPanel gradop;
    private JTable table1;

    public JFrame frameGrafo;
    private JFrame anterior;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();
    private Boolean aux = false;
    static StationManager sm = StationManager.getInstance();
    static RouteManager rm = RouteManager.getInstance();

    public GrafoGUI(ArrayList<Station> bestRoute) {
        this.initialize();
        this.aux = true;

    }
    public GrafoGUI() {
        System.out.println("entro a grafo gui");
        this.initialize();

    }

    public GrafoGUI(GrafoPanel grafoPanel) {
        this.grafoPanel= grafoPanel;
        this.initialize();
    }


    private void initialize() {
        this.frameGrafo = new JFrame();

        this.frameGrafo.setContentPane(panel1);
        this.frameGrafo.setBounds(10, 10, 1200, 720);
        this.frameGrafo.setResizable(false);



      //  grafoPanel.repaint();

        this.frameGrafo.setContentPane(grafoPanel);
        System.out.println("agregar a panel" );
        //this.frameGrafo.setContentPane(grafoPanel);

        if(!aux) {
            //se muestra todos los trayectos
            this.frameGrafo.setContentPane(grafoPanel);

        } else {
            //se crea segun trayecto elegido

        }
    }
    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
