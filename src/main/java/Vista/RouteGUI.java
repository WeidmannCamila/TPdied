package main.java.Vista;

import main.java.Enumeration.EnumBestRoute;
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
import java.util.List;

public class RouteGUI {
    private JPanel panel1;
    private JButton addRouteButton;
    private JButton searchButton;
    private JLabel exitButton;
    private JButton addTransportButton;
    private JComboBox CBStart;
    private JComboBox CBEnd;
    private JButton addButton;
    private JComboBox CBparamSearch;
    public JFrame frameRoute;
    private JFrame anterior;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();;
    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();

    public RouteGUI() {
        this.initialize();

    }


    private void initialize(){
        this.frameRoute = new JFrame();

        this.frameRoute.setContentPane(panel1);
        this.frameRoute.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameRoute.setBounds(10, 10, 1200, 720);
        this.frameRoute.setResizable(false);
        this.frameRoute.setLocationRelativeTo(null);

        ListGlobalStation ls = ListGlobalStation.getInstance();

        HashMap<Integer, Station> lists =new HashMap<Integer, Station>(ls.getList());
        String[] array = new String[lists.size()+1];
        int i=0;
        array[0] = "--Seleccione--";
        for(Station s : lists.values()){
            i++;
            array[i] = s.getName();

        }


        CBStart.setModel(new DefaultComboBoxModel<>(array));
        CBEnd.setModel(new DefaultComboBoxModel(array));

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RouteGUI.this.anterior.setVisible(true);
                RouteGUI.this.frameRoute.dispose();
            }

        });



        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                RouteAddGUI sadd = new RouteAddGUI();
                sadd.setAnterior(RouteGUI.this.anterior);
                sadd.frameRouteAdd.setVisible(true);



            }
        });

        //buscar rutas

        // segun criterio de busqueda, se tiene q ahcer una funcion que compare cada unod de los caminos y elija depende del combobox

        CBparamSearch.setVisible(true);
        CBparamSearch.setModel(new DefaultComboBoxModel<EnumBestRoute>(EnumBestRoute.values()));

        searchButton.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(CBStart.getSelectedIndex()==0 || CBEnd.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "Seleccione estaciones.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (CBStart.getSelectedIndex() == CBEnd.getSelectedIndex()) {
                        JOptionPane.showMessageDialog(null, "Seleccione estaciones diferentes.",
                                "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                    } else {
                        Station end = new Station();
                        Station start = new Station();
                        String s = CBStart.getSelectedItem().toString();
                        start = sm.getStation(s);
                        String en = CBEnd.getSelectedItem().toString();
                        end =sm.getStation(en);


                        if(start.getStatus().equals("MANTENIMIENTO") || end.getStatus().equals("MANTENIMIENTO")){
                            JOptionPane.showMessageDialog(null, "La estacion de la que quiere partir/llegar esta en mantenimiento.",
                                    "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                        } else{
                            String crit = CBparamSearch.getSelectedItem().toString();

                            ArrayList<ArrayList<Station>> bestRoute = rm.bestRoute4crit(start, end, crit);

                            if(bestRoute== null){
                                JOptionPane.showMessageDialog(null, "No hay recorridos disponibles",
                                        "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                            } else {
                                GrafoGUI graf = new GrafoGUI(bestRoute);
                                graf.frameGrafo.setVisible(true);
                                graf.setAnterior(RouteGUI.this.frameRoute);
                            }
                        }
                    }

                }




            }
        }));



    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }



}
