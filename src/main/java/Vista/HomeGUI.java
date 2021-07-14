package main.java.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private JFrame framePrincipal;

    public HomeGUI(){
        this.initialize();}

    private void initialize(){
        this.framePrincipal = new JFrame();
        this.framePrincipal.setContentPane(panel1);
        this.framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.framePrincipal.setBounds(100, 100, 1200, 720);
        this.framePrincipal.setResizable(false);

        transporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransportGUI t = new TransportGUI();
                t.frameTransport.setVisible(true);
                t.setAnterior(HomeGUI.this.framePrincipal);


                framePrincipal.dispose();
            }
        });

        estacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StationGUI s = new StationGUI();
                s.frameStation.setVisible(true);
                s.setAnterior(HomeGUI.this.framePrincipal);


                framePrincipal.dispose();
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
