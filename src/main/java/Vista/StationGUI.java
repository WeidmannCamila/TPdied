package main.java.Vista;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StationGUI extends JPanel{
    private JPanel panel1;
    private JLabel exitButton;
    private JButton buscarButton;
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JButton añadirTransporteButton;
    private JTable Transportes;
    private JButton modificarButton;
    private JButton eliminarButton;


    public JFrame frameStation;

    private JFrame anterior;


    public StationGUI() {
        this.initialize();

    }


    private void initialize(){
        this.frameStation = new JFrame();

        this.frameStation.setContentPane(panel1);
        this.frameStation.setBounds(100, 100, 1200, 720);
        this.frameStation.setResizable(false);



        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StationGUI.this.anterior.setVisible(true);
                StationGUI.this.frameStation.dispose();
            }

        });

    }




    public void setAnterior(JFrame a) {
        this.anterior = a;
    }




}
