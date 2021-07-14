package main.java.Vista;

import javax.swing.*;
import java.awt.*;

public class TransportGUI {


    private JPanel panel1;
    private JPanel panel2;
    private JButton añadirTransporteButton;
    private JButton button3;
    private JButton modificarTransporteButton;
    private JButton buscarButton;

    public JFrame frameTransport;

    private JFrame anterior;


    public TransportGUI() {
        this.initialize();

    }


    private void initialize(){
        this.frameTransport = new JFrame();

        this.frameTransport.setContentPane(panel1);
        this.frameTransport.setBounds(100, 100, 1200, 720);
        this.frameTransport.setResizable(false);

    }




    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
