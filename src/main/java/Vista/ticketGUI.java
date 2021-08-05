package main.java.Vista;

import main.java.classes.ListRoute;

import javax.swing.*;

public class ticketGUI {
    private JPanel panelColour;
    private JTextField date;
    private JTextField textField1;
    private JTextField Name;
    private JTextField email;
    private JTextField destino;
    private JTextField duracion;
    private JTextField costo;
    private JTextField distancia;
    private JPanel jpanel1;

    public JFrame frameTicket;
    private JFrame anterior;

    public ticketGUI(ListRoute routes, String name, String email) {
        this.initialize();
    }

    private void initialize() {

        this.frameTicket = new JFrame();

        this.frameTicket.setContentPane(jpanel1);
        this.frameTicket.setBounds(100, 100, 300, 150);
        this.frameTicket.setResizable(false);




    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
