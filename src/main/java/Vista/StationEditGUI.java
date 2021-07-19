package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StationEditGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField transportColour;
    private JComboBox statusCB;
    private JTextField transportId;
    private JButton editButton;
    private JTextField transportName;
    private JTextField textField1;
    private DTOStation dto;
    private StationDAO sDAO = new StationDAO();
    public JFrame frameStationEdit;
    private JFrame anterior;

    public StationEditGUI() {
        this.initialize();

    }

    private void initialize() {
        this.frameStationEdit = new JFrame();

        this.frameStationEdit.setContentPane(panel1);
        this.frameStationEdit.setBounds(10, 10, 1200, 720);
        this.frameStationEdit.setResizable(false);


        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(StationEditGUI.this.transportId.getText().length() <= 0 || StationEditGUI.this.transportName.getText().length() <= 0){
                    AlertPanel a = new AlertPanel(EnumTipoAlerta.INFORMACION, "Valores incompletos", "error" , "Verifique valores", null );
                }else {
                    dto.setIdTransport(Integer.parseInt(transportId.getText()));

                    dto.setName(transportName.getText());
                    sDAO.updateStation(dto);
                }


            }
        });


    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }

}
