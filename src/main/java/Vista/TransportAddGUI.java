package main.java.Vista;

import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumColour;

import main.java.Managers.TransportManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransportAddGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField transportName;
    private JTextField transportColour;
    private JTextField transportId;
    private JButton addButton;
    private JRadioButton noActivaRadioButton;
    private JRadioButton activaRadioButton;
    private JComboBox CBTransportColour;
    private JComboBox CBstatus;
    private DTOTransport dto;
    private TransportDAO tDAO = new TransportDAO();
    public JFrame frameTransportAdd;
    private ButtonGroup statusBotton;

    private TransportManager tm =new TransportManager();
    private JFrame anterior;

    public TransportAddGUI() {
        this.initialize();

    }


    private void initialize() {
        this.frameTransportAdd = new JFrame();

        this.frameTransportAdd.setContentPane(panel1);
        this.frameTransportAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameTransportAdd.setBounds(10, 10, 1200, 720);
        this.frameTransportAdd.setLocationRelativeTo(null);

        this.frameTransportAdd.setResizable(false);
        this.statusBotton = new ButtonGroup();
        statusBotton.add(noActivaRadioButton);
        statusBotton.add(activaRadioButton);
        CBTransportColour.setModel(new DefaultComboBoxModel<EnumColour>(EnumColour.values()));

        String[] estado = {"--seleccionar--", "Activa" , "No activa"};
        CBstatus.setModel(new DefaultComboBoxModel<String>(estado));


        //salir a la pantalla anterior
        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                TransportAddGUI.this.anterior.setVisible(true);
                TransportAddGUI.this.frameTransportAdd.dispose();
            }

        });

        // final ArrayList<DTOTransport> listTransport = tDAO.getTransports();
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(transportName.getText().length() <= 0 || CBTransportColour.getSelectedIndex() == 0 || CBstatus.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "Campos vacios.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                }else {

                   if(tm.getTransport(transportName.getText()) !=null){
                       JOptionPane.showMessageDialog(null, "El transporte ya existe",
                               "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                   }
                    else {
                        dto = new DTOTransport();
                        dto.setName(transportName.getText().substring(0,1).toUpperCase() + transportName.getText().substring(1).toLowerCase());
                        dto.setColour(CBTransportColour.getSelectedItem().toString());
                        boolean stat;
                        if(CBstatus.getSelectedIndex() ==1){
                            stat= true;
                        } else { stat= false;}
                        dto.setStatus(stat);
                        tm.addTransport(dto);
                       JOptionPane.showMessageDialog(null, "Transporte cargada con Exito",
                               "EXITO", JOptionPane.INFORMATION_MESSAGE);
                       TransportAddGUI.this.anterior.setVisible(true);
                       TransportAddGUI.this.frameTransportAdd.dispose();

                    }
                }
            }
        });

    }



    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
