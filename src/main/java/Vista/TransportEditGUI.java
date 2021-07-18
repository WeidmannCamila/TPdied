package main.java.Vista;

import javax.swing.*;

public class TransportEditGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JButton editButton;
    private JTextField textField1;

    public JFrame frameTransportEdit;

    private JFrame anterior;

    public TransportEditGUI() {
        this.initialize();

    }

    private void initialize() {
        this.frameTransportEdit = new JFrame();

        this.frameTransportEdit.setContentPane(panel1);
        this.frameTransportEdit.setBounds(10, 10, 1200, 720);
        this.frameTransportEdit.setResizable(false);

    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
