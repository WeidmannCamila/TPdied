package main.java.Vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        this.frameMenuRoute.setLocationRelativeTo(null);



        // Rutas, trayectos posibles
        queTrayectoPuedoTomarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RouteGUI t = new RouteGUI();
                t.frameRoute.setVisible(true);
                t.setAnterior(MenuRoute.this.frameMenuRoute);


                frameMenuRoute.dispose();
            }
        });

        // FLUJO MAXIMO
        flujoMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlujoMaxGUI s = new FlujoMaxGUI();
                s.setAnterior(MenuRoute.this.anterior);
                s.frameFluj.setVisible(true);



                frameMenuRoute.dispose();
            }
        });

        // gui para el Page Rank
        queRecorridosLleganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageRankGUI r = new PageRankGUI();
                r.framePageRank.setVisible(true);
                r.setAnterior(MenuRoute.this.frameMenuRoute);


                frameMenuRoute.dispose();
            }
        });
        // salir menu
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuRoute.this.anterior.setVisible(true);
                MenuRoute.this.frameMenuRoute.dispose();
            }

        });

    }



    public void setAnterior(JFrame a) {
        this.anterior = a;
    }






}
