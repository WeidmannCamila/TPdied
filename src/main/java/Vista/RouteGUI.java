package main.java.Vista;

import main.java.Enumeration.EnumBestRoute;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
        this.frameRoute.setBounds(100, 100, 1200, 720);
        this.frameRoute.setResizable(false);

        final ArrayList<Station> listStation = sm.searchStation1();

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

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RouteGUI.this.anterior.setVisible(true);
                RouteGUI.this.frameRoute.dispose();
            }

        });



        // Editar insumo
        JButton btnEditar_1 = new JButton("Editar");



        // Eliminar insumo
        JButton btnEliminar = new JButton("Eliminar");


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
                Station end = new Station();
                Station start = new Station();
                String s = CBStart.getSelectedItem().toString();
                start = sm.getStation(s);
                String en = CBEnd.getSelectedItem().toString();
                end =sm.getStation(en);


                if(start == null || end == null){
                    System.out.println("seleccione estaciones");
                }

                if (start == end){
                    System.out.println("son la misma estacion");
                }
                String crit = CBparamSearch.getSelectedItem().toString();

                ArrayList<ArrayList<Station>> bestRoute = rm.bestRoute4crit(start, end, crit);

                System.out.println("mejor trayecto" + bestRoute);
                //mostrar en tabla y grafico


                GrafoGUI graf = new GrafoGUI(bestRoute);
                graf.frameGrafo.setVisible(true);
                graf.setAnterior(RouteGUI.this.frameRoute);



            }
        }));



    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }



}
