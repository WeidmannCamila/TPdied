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
import java.util.ArrayList;
import java.util.Iterator;

public class StationAddGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField stationOpen;
    private JComboBox statusCB;
    private JTextField stationId;
    private JButton addButton;
    private JTextField stationName;
    private JTextArea stationClose;
    private DTOStation dto;
    private StationDAO sDAO = new StationDAO();
    public JFrame frameStationAdd;
    private JFrame anterior;

    public StationAddGUI() {
        this.initialize();

    }

    private void initialize() {
        this.frameStationAdd = new JFrame();

        this.frameStationAdd.setContentPane(panel1);
        this.frameStationAdd.setBounds(10, 10, 1200, 720);
        this.frameStationAdd.setResizable(false);

        final ArrayList<DTOStation> listStation = sDAO.getStations();




    }

    public void onRegisterTransport(){
        final ArrayList<DTOStation> listStation = sDAO.getStations();
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(StationAddGUI.this.stationId.getText().length() <= 0 || StationAddGUI.this.stationName.getText().length() <= 0 ){
                    AlertPanel a = new AlertPanel(EnumTipoAlerta.INFORMACION, "Valores incompletos", "error" , "Verifique valores", null );
                }else {
                    Iterator v = listStation.iterator();
                    boolean encontrado = false;
                    while(v.hasNext()) {
                        dto = (DTOStation) v.next();
                        if (dto.getIdTransport() == (Integer.parseInt(StationAddGUI.this.stationId.getText()))) {
                            encontrado = true;
                        }
                    }


                    if(encontrado) {
                        System.out.println("error ya existe");
                    }
                    else {
                        dto = new DTOStation();
                        dto.setName(stationName.getText().substring(0,1).toUpperCase() + stationName.getText().substring(1).toLowerCase());

                        //  dto.setStatus(statusCB.getSelectedItem()); ver forma de decir en mantenimiento o no
                        dto.setIdTransport(Integer.parseInt(stationId.getText()));
                        sDAO.addStation(dto);

                    }
                }
            }
        });
    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
