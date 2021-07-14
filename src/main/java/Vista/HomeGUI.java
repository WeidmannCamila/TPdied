package main.java.Vista;

import javax.swing.*;
import java.awt.*;

public class HomeGUI {

    private JPanel panel1;

    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    private JPanel panel6;
    private JButton transporteButton;
    private JButton recorridosButton;
    private JButton button3;
    private JLabel JLabelinfo;


    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }


    private static void createAndShowGUI(){


        JFrame framePrincipal = new JFrame("HomeGUI");
        framePrincipal.setContentPane(new HomeGUI().panel1);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        framePrincipal.pack();
        framePrincipal.setVisible(true);

        }






}
