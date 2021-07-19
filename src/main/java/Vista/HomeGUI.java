package main.java.Vista;

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

    private JFrame framePrincipal;

    public HomeGUI(){
        this.initialize();}

    private void initialize(){
        this.framePrincipal = new JFrame();
        this.framePrincipal.setContentPane(panel1);
        this.framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.framePrincipal.setBounds(10, 10, 1200, 720);
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
                RouteGUI r = new RouteGUI();
                r.frameRoute.setVisible(true);
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



    }


    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    HomeGUI win = new HomeGUI();
                    win.framePrincipal.setLocationRelativeTo((Component)null);
                    win.framePrincipal.setVisible(true);
                } catch (Exception var2){
                        var2.printStackTrace();
                }
            }
        });

    }










}
