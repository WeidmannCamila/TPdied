package main.java.Vista;

import main.java.DAO.RouteDAO;
import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Route;
import main.java.classes.Station;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class RouteAddGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField costRoute;
    private JButton addButton;
    private JTextArea distanceRoute;
    private JComboBox CBStart;
    private JComboBox CBEnd;
    private JComboBox CBtransport;
    private JTextField durRoute;
    private JTextField maxPRoute;
    private JComboBox CBStatus;

    private DTORoute dto;
    private RouteDAO rDAO = new RouteDAO();
    public JFrame frameRouteAdd;
    private JFrame anterior;
    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();



    public RouteAddGUI() {
        this.initialize();

    }

    private void initialize() {
        this.frameRouteAdd = new JFrame();

        this.frameRouteAdd.setContentPane(panel1);
        this.frameRouteAdd.setBounds(10, 10, 1200, 720);
        this.frameRouteAdd.setResizable(false);


        //buscar toda las estaciones para meter en el combobox
        List<Station> ls = new ArrayList<Station>(sm.searchStation1());


        String[] array = new String[ls.size()];
        for(int i = 0; i < array.length; i++) {
            array[i] = ls.get(i).getName();
        }

        CBStart.setModel(new DefaultComboBoxModel<>(array));

        String[] arraydos = new String[ls.size()];
        for(int i = 0; i < arraydos.length; i++) {
            arraydos[i] = ls.get(i).getName();
        }

        CBEnd.setModel(new DefaultComboBoxModel<>(arraydos));


        String[] items = {"Activa", "Inactiva"};

        CBStatus.setModel(new DefaultComboBoxModel<String>(items));

        //TODO para agregar un trayecto se debe verificar que haya al menos dos estaciones cargadas

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                addNewRoute();

            }
        });


    }

    private void addNewRoute() {

        dto = new DTORoute();
        dto.setCost(Integer.parseInt(costRoute.getText()));
        dto.setDestination((Station) CBEnd.getSelectedItem());
        dto.setDistance(Integer.parseInt(distanceRoute.getText()));
        dto.setDuration(Integer.parseInt(durRoute.getText()));
        dto.setOrigin((Station) CBStart.getSelectedItem());
        dto.setStatus(true);

/*

        if(start == null || end == null){
            System.out.println("seleccione estaciones");
        }

        if (start == end){
            System.out.println("son la misma estacion");
        }
*/


        Route newR = rm.createRoute(dto);

        // oarsear costo duracion y demas, para ver si son datos numericos o no
        /*newR.setCost(Integer.parseInt(costRoute.getText()));
        newR.setDistance(Integer.parseInt(distanceRoute.getText()));
        newR.setDuration(14);*/




    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
