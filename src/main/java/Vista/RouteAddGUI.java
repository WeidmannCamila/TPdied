package main.java.Vista;

import main.java.DAO.RouteDAO;
import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.Managers.TransportManager;
import main.java.classes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouteAddGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JTextField costRoute;
    private JButton addButton;

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



        //buscar toda las estaciones y transporte para meter en el combobox
        ListGlobalStation ls = ListGlobalStation.getInstance();

        HashMap<Integer, Station> lists =new HashMap<Integer, Station>(ls.getList());
        String[] array = new String[lists.size()+1];
        int i=0;
        array[0] = "--Seleccionar--";
        for(Station s : lists.values()){
            i++;
            array[i] = s.getName();

        }

        ListGlobalTransport lt = ListGlobalTransport.getInstance();
        ArrayList<TransportRoute> listT = new ArrayList<TransportRoute>(lt.getList());
        String[] arrayT = new String[listT.size()+1];
        int j=0;
        arrayT[0] = "--Seleccionar--";
        for(TransportRoute t : listT){
            j++;
            arrayT[j] = t.getName();

        }

        CBtransport.setModel(new DefaultComboBoxModel<>(arrayT));

        CBStart.setModel(new DefaultComboBoxModel<>(array));

        CBEnd.setModel(new DefaultComboBoxModel<>(array));


        String[] items = {"--Selecione--", "Activa", "Inactiva"};

        CBStatus.setModel(new DefaultComboBoxModel<String>(items));

        //TODO para agregar un trayecto se debe verificar que haya al menos dos estaciones cargadas

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


                if (CBStart.getSelectedIndex() == 0 || CBEnd.getSelectedIndex() == 0 || CBStatus.getSelectedIndex() == 0 || costRoute.getText().length() <= 0 || distRoute.getText().length() <= 0 || durRoute.getText().length() <= 0 || maxPRoute.getText().length() <= 0) {
                    JOptionPane.showMessageDialog(null, "Campos vacios.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                } else {
                    Station end = new Station();
                    Station start = new Station();
                    String s = CBStart.getSelectedItem().toString();
                    start = sm.getStation(s);
                    String en = CBEnd.getSelectedItem().toString();
                    end = sm.getStation(en);
                    TransportRoute transport = new TransportRoute();
                    String t = CBtransport.getSelectedItem().toString();
                    transport = tm.getTransport(t);

                    if (rm.getRoute(start, end) != null){
                        Route aux = rm.getRoute(start, end);
                        if(aux.getTransport().getIdTransport().equals(transport.getIdTransport())){
                            JOptionPane.showMessageDialog(null, "La ruta con ese transporte ya existe",
                                    "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                        } else{
                            createRoute(start, end, transport, costRoute.getText(),distRoute.getText(), durRoute.getText(), CBStatus.getSelectedIndex(),maxPRoute.getText());
                        }
                    } else {
                        createRoute(start, end, transport, costRoute.getText(),distRoute.getText(), durRoute.getText(), CBStatus.getSelectedIndex(), maxPRoute.getText());
                    }

                }
            }
        });


    }

    private void createRoute(Station start, Station end, TransportRoute transport, String cost, String distance, String duracion, int status, String maxP) {
        dto = new DTORoute();
        dto.setCost(Double.parseDouble(cost));
        dto.setDestination(end);
        dto.setDistance(Double.parseDouble(distance));
        dto.setDuration(Double.parseDouble(duracion));
        dto.setOrigin(start);
        dto.setTransport(transport);
        dto.setMaxPassagers(Integer.parseInt(maxP));

        boolean stat;
        if(status==1){
            stat= true;
        } else { stat= false;}
        dto.setStatus(stat);
        rm.createRoute(dto);

        JOptionPane.showMessageDialog(null, "Recorrido cargado con Exito",
                "EXISTO", JOptionPane.ERROR_MESSAGE);
        RouteAddGUI.this.anterior.setVisible(true);
        RouteAddGUI.this.frameRouteAdd.dispose();

    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
