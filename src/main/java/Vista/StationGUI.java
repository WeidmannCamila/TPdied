package main.java.Vista;

import javax.swing.*;

public class StationGUI extends JPanel{
    private JPanel panel1;
    private JPanel panel22;
    private JButton eliminarEstacionButton;
    private JButton añadirEstacionButton;
    private JButton buscarEstacionButton;
    private JButton modificarEstacionButton;
    private JButton button1;
    private JLabel LabelBack;


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

    }




    public void setAnterior(JFrame a) {
        this.anterior = a;
    }




}
