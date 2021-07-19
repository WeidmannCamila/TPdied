package main.java.Vista;

import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransportEditGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField transportColour;
    private JComboBox statusCB;
    private JTextField transportId;
    private JButton editButton;
    private JTextField transportName;
    private DTOTransport dto;
    private TransportDAO tDAO = new TransportDAO();
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


        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(TransportEditGUI.this.transportId.getText().length() <= 0 || TransportEditGUI.this.transportName.getText().length() <= 0 || TransportEditGUI.this.transportColour.getText().length() <= 0){
                    AlertPanel a = new AlertPanel(EnumTipoAlerta.INFORMACION, "Valores incompletos", "error" , "Verifique valores", null );
                }else {
                        dto.setIdTransport(Integer.parseInt(transportId.getText()));
                    dto.setColour(transportColour.getText());
                    dto.setName(transportName.getText());
                    tDAO.updateTransport(dto);
                }


            }
        });


    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
