package main.java.Vista;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListNextMaintenance extends JPanel {

    public JFrame frameNextMaint ;
    private JButton exitButton;
    private JTable stationTable;
    private JScrollPane stationPanel;
    private JFrame anterior;

    public ListNextMaintenance(){
        this.initialize();
    }

    private void initialize(){
        this.frameNextMaint = new JFrame();
       // this.nextMaint.setContentPane();
        this.frameNextMaint.setResizable(false);
        this.frameNextMaint.setBounds(15,15,300,450);


        //volver atras
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ListNextMaintenance.this.anterior.setVisible(true);
                ListNextMaintenance.this.frameNextMaint.dispose();
            }
        });

    }

    public void setAnterior(JFrame a ){
        this.anterior = a;
    }


    //metodo de mostrar estaciones

}
