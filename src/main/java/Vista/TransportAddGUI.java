package main.java.Vista;

import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class TransportAddGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField transportName;
    private JTextField transportColour;
    private JComboBox statusCB;
    private JTextField transportId;
    private JButton addButton;
    private DTOTransport dto;
    private TransportDAO tDAO = new TransportDAO();
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

        final ArrayList<DTOTransport> listTransport = tDAO.getTransports();

    }

    public void onRegisterTransport(){
        final ArrayList<DTOTransport> listTransport = tDAO.getTransports();
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(TransportAddGUI.this.transportId.getText().length() <= 0 || TransportAddGUI.this.transportName.getText().length() <= 0 || TransportAddGUI.this.transportColour.getText().length() <= 0){
                    AlertPanel a = new AlertPanel(EnumTipoAlerta.INFORMACION, "Valores incompletos", "error" , "Verifique valores", null );
                }else {
                    Iterator v = listTransport.iterator();
                    boolean encontrado = false;
                    while(v.hasNext()) {
                        dto = (DTOTransport) v.next();
                        if (dto.getIdTransport() == (Integer.parseInt(TransportAddGUI.this.transportId.getText()))) {
                            encontrado = true;
                        }
                    }


                    if(encontrado) {
                        System.out.println("error ya existe");
                        // crear ventana emergente
                    }
                    else {
                        dto = new DTOTransport();
                        dto.setName(transportName.getText().substring(0,1).toUpperCase() + transportName.getText().substring(1).toLowerCase());
                        dto.setColour(transportColour.getText().substring(0,1).toUpperCase() + transportColour.getText().substring(1).toLowerCase());
                      //  dto.setStatus(statusCB.getSelectedItem()); ver forma de decir en mantenimiento o no
                        dto.setIdTransport(Integer.parseInt(transportId.getText()));
                        tDAO.addTransport(dto);

                    }
                }
            }
        });
    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
