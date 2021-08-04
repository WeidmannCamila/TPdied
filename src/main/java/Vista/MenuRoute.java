package main.java.Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuRoute {
    private JPanel panel1;
    private JLabel exitButton;
    private JButton queTrayectoPuedoTomarButton;
    private JButton flujoMax;
    private JButton queRecorridosLleganButton;
    public JFrame frameMenuRoute;
    private JFrame anterior;


    public MenuRoute(){
        this.initialize();}

    private void initialize() {



        this.frameMenuRoute = new JFrame();
        this.frameMenuRoute.setContentPane(panel1);
        this.frameMenuRoute.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameMenuRoute.setBounds(10, 10, 1200, 720);
        this.frameMenuRoute.setResizable(false);

        // boton para ir a la seccion de transporte
        queTrayectoPuedoTomarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RouteGUI t = new RouteGUI();
                t.frameRoute.setVisible(true);
                t.setAnterior(MenuRoute.this.frameMenuRoute);


                frameMenuRoute.dispose();
            }
        });

        // para la seccion de estaciones
        flujoMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlujoMaxGUI s = new FlujoMaxGUI();
                s.setAnterior(MenuRoute.this.anterior);
                s.frameFluj.setVisible(true);



                frameMenuRoute.dispose();
            }
        });

        queRecorridosLleganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageRankGUI r = new PageRankGUI();
                r.framePageRank.setVisible(true);
                r.setAnterior(MenuRoute.this.frameMenuRoute);


                frameMenuRoute.dispose();
            }
        });


    }



    public void setAnterior(JFrame a) {
        this.anterior = a;
    }






}
