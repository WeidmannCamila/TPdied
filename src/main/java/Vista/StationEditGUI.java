package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DTOs.DTOStation;
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
    private JTextField stationId;
    private JButton editButton;
    private JTextField stationName;
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

                if(StationEditGUI.this.stationId.getText().length() <= 0 || StationEditGUI.this.stationName.getText().length() <= 0){
                    AlertPanel a = new AlertPanel(EnumTipoAlerta.INFORMACION, "Valores incompletos", "error" , "Verifique valores", null );
                }else {
                    dto.setIdStation(Integer.parseInt(stationId.getText()));
                    dto.setName(stationName.getText());
                    sDAO.updateStation(dto);
                }


            }
        });


    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
        /*
            CUANDO apretas el boton editar tenes que preguntar si se modifico el estado de la estacion
            si pasa de operativa a en mantenimiento crear una nuevo obj manteminiento y agregarlo a la lista de la estacion

            guardar la fecha de inicio y fin de cada mantenimiento

         */




}
