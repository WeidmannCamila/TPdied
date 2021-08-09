package main.java.Vista;

import main.java.DAO.RouteDAO;
import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.Managers.TransportManager;
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
    private JTextField distRoute;

    private DTORoute dto;
    private RouteDAO rDAO = new RouteDAO();
    public JFrame frameRouteAdd;
    private JFrame anterior;
    private StationManager sm = StationManager.getInstance();
    private RouteManager rm = RouteManager.getInstance();
    private TransportManager tm = TransportManager.getInstance();



    public RouteAddGUI() {
        this.initialize();

    }

    private void initialize() {
        this.frameRouteAdd = new JFrame();

        this.frameRouteAdd.setContentPane(panel1);
        this.frameRouteAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameRouteAdd.setBounds(10, 10, 1200, 720);
        this.frameRouteAdd.setResizable(false);
        this.frameRouteAdd.setLocationRelativeTo(null);



        //buscar toda las estaciones para meter en el combobox
        List<Station> ls = new ArrayList<Station>(sm.searchStation1());


        String[] array = new String[ls.size()];
        int i =0;
        array[0] = "--Seleccione";
        for(i = 1; i < array.length; i++) {
            array[i] = ls.get(i).getName();
        }

        CBStart.setModel(new DefaultComboBoxModel<>(array));

        CBEnd.setModel(new DefaultComboBoxModel<>(array));


        String[] items = {"--Selecione--", "Activa", "Inactiva"};

        CBStatus.setModel(new DefaultComboBoxModel<String>(items));

        //TODO para agregar un trayecto se debe verificar que haya al menos dos estaciones cargadas

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (CBStart.getSelectedIndex() == 0 || CBEnd.getSelectedIndex() == 0 || CBStatus.getSelectedIndex() == 0 || costRoute.getText().length() <= 0 || distanceRoute.getText().length() <= 0 || durRoute.getText().length() <= 0 || maxPRoute.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(null, "Campos vacios.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Station end = new Station();
                    Station start = new Station();
                    String s = CBStart.getSelectedItem().toString();
                    start = sm.getStation(s);
                    String en = CBEnd.getSelectedItem().toString();
                    end = sm.getStation(en);

                    if (rm.getRoute(start, end) != null && tm.getTransport(CBtransport.getSelectedItem().toString()) != null) {
                        JOptionPane.showMessageDialog(null, "La ruta con ese transporte ya existe",
                                "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                    } else {

                        dto = new DTORoute();
                        dto.setCost(Integer.parseInt(costRoute.getText()));
                        dto.setDestination((Station) CBEnd.getSelectedItem());
                        dto.setDistance(Integer.parseInt(distanceRoute.getText()));
                        dto.setDuration(Integer.parseInt(durRoute.getText()));
                        dto.setOrigin((Station) CBStart.getSelectedItem());

                        boolean stat;
                        if(CBStatus.getSelectedIndex() ==1){
                            stat= true;
                        } else { stat= false;}
                        dto.setStatus(stat);
                        rm.createRoute(dto);
                    }

                }
            }
        });


    }



    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
