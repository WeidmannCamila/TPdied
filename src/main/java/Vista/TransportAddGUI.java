package main.java.Vista;

import javax.swing.*;

public class TransportAddGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JButton AÑADIRButton;


    public JFrame frameTransportAdd;

    private JFrame anterior;

    public TransportAddGUI() {
        this.initialize();

    }

    private void initialize() {
        this.frameTransportAdd = new JFrame();

        this.frameTransportAdd.setContentPane(panel1);
        this.frameTransportAdd.setBounds(10, 10, 1200, 720);
        this.frameTransportAdd.setResizable(false);

    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
