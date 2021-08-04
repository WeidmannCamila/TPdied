package main.java.Vista;

import main.java.Enumeration.EnumBestRoute;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.ListGlobalStation;
import main.java.classes.Station;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PageRankGUI {
    public JFrame framePageRank;
    private JFrame anterior;

    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();
    ArrayList<ViewPageRank> listaDatos = new ArrayList<>();
    ListGlobalStation ls = ListGlobalStation.getInstance();
    private JPanel panel1;
    private JLabel exitButton;
    private JComboBox CBStart;
    private JTable table;
    private JButton buscarButton;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();

    public PageRankGUI() {
        this.initialize();

    }


    private void initialize() {
        this.framePageRank = new JFrame();

        this.framePageRank.setContentPane(panel1);
        this.framePageRank.setBounds(100, 100, 1200, 720);
        this.framePageRank.setResizable(false);

        HashMap<Integer, Station> lists =new HashMap<Integer, Station>(ls.getList());


        String[] array = new String[lists.size()];
        int i=1;
        array[0]= "TODOS";
        for(Station s : lists.values()){

            array[i] = s.getName();
            i++;
        }


        CBStart.setModel(new DefaultComboBoxModel<>(array));



        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (CBStart.getSelectedIndex() == 0){

                        listaDatos = grafoPanel.getPageRank();


                    } else {
                  //  listaDatos = grafoPanel.getPageRank(CBStart.getSelectedItem());
                }

            }
        });


    }



    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
