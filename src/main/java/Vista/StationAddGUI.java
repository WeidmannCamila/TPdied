package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DTOs.DTOStation;
import main.java.Enumeration.EnumStatus;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.ListGlobalStation;
import main.java.classes.Station;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class StationAddGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JComboBox statusCB;
    private JTextField stationId;
    private JButton addButton;
    private JTextField stationName;
    private JTextField HourOpenTField;
    private JTextField MinuteOpenTField;
    private JTextField HourClosedTField;
    private JTextField MinuteCloseTf;
    private DTOStation dto;
    private StationDAO sDAO = new StationDAO();
    public JFrame frameStationAdd;
    private JFrame anterior;
    private StationManager sm =new StationManager();


    public StationAddGUI() {
        this.initialize();

    }

    private void initialize() {
        this.frameStationAdd = new JFrame();

        this.frameStationAdd.setContentPane(panel1);
        this.frameStationAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameStationAdd.setBounds(10, 10, 1200, 720);
        this.frameStationAdd.setResizable(false);
        this.frameStationAdd.setLocationRelativeTo(null);




        ListGlobalStation ls = ListGlobalStation.getInstance();

        HashMap<Integer, Station> lists =new HashMap<Integer, Station>(ls.getList());
        String[] array = new String[lists.size()];
        int i=0;
        for(Station s : lists.values()){
            array[i] = s.getName();
            i++;
        }




        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(stationName.getText().length() <= 0 || HourClosedTField.getText().length() <= 0 || HourOpenTField.getText().length() <= 0){
                    JOptionPane.showMessageDialog(null, "Campos vacios.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                }else {

                    if(sm.getStation(stationName.getText()) != null){
                        JOptionPane.showMessageDialog(null, "La estacion con tal nombre ya existe",
                                "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                    }else {
                        dto = new DTOStation();
                        dto.setName(stationName.getText().substring(0,1).toUpperCase() + stationName.getText().substring(1).toLowerCase());
                        dto.setOpen(HourOpenTField.getText()+ ":" + MinuteOpenTField.getText());
                        dto.setClosed(HourClosedTField.getText()+ ":" + MinuteCloseTf.getText());


                        dto.setStatus( "OPERATIVA");

                        sm.addStation(dto);


                        JOptionPane.showMessageDialog(null, "Estacion cargada con Exito",
                                "EXITO", JOptionPane.ERROR_MESSAGE);
                        StationAddGUI.this.anterior.setVisible(true);
                        StationAddGUI.this.frameStationAdd.dispose();


                    }
                }
            }
        });

        exitButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                StationAddGUI.this.anterior.setVisible(true);
                StationAddGUI.this.frameStationAdd.dispose();
            }

        });

    }




    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
