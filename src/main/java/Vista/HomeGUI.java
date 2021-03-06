package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.ListGlobalRoute;
import main.java.classes.ListGlobalStation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class HomeGUI{

    private JPanel panel1;

    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JButton transporteButton;
    private JButton recorridosButton;
    private JButton estacionButton;
    private JLabel JLabelinfo;
    private JLabel exitButton;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();;
    public JFrame framePrincipal;
    static StationManager sm = StationManager.getInstance();
    static RouteManager rm = RouteManager.getInstance();
    public HomeGUI(){
        this.initialize();}

    private void initialize(){




        this.framePrincipal = new JFrame();
        this.framePrincipal.setContentPane(panel1);
        this.framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.framePrincipal.setBounds(100, 100, 1200, 720);
        this.framePrincipal.setResizable(false);

        // boton para ir a la seccion de transporte
        transporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransportGUI t = new TransportGUI();
                t.frameTransport.setVisible(true);
                t.setAnterior(HomeGUI.this.framePrincipal);


                framePrincipal.dispose();
            }
        });

        // para la seccion de estaciones
        estacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StationGUI s = new StationGUI();
                s.frameStation.setVisible(true);
                s.setAnterior(HomeGUI.this.framePrincipal);


                framePrincipal.dispose();
            }
        });

        recorridosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuRoute r = new MenuRoute();
                r.frameMenuRoute.setVisible(true);
                r.setAnterior(HomeGUI.this.framePrincipal);


                framePrincipal.dispose();
            }
        });



        //para salir
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

        });

        JLabelinfo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO obtener la lista de estaciones
                grafoPanel.initVertex(ListGlobalStation.getInstance().getList());

                grafoPanel.initArista(ListGlobalRoute.getInstance().getList());


                GrafoGUI graf = new GrafoGUI();
                    graf.frameGrafo.setVisible(true);
                    graf.setAnterior(HomeGUI.this.framePrincipal);




            }
        });

    }













}
