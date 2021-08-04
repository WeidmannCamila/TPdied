package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.ListGlobalStation;
import main.java.classes.Station;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlujoMaxGUI {
    public JFrame frameFluj;
    private JPanel panel1;
    private JLabel exitButton;
    private JComboBox CBStart;
    private JTable table;
    private JButton buscarButton;
    private JComboBox CBEnd;
    private JFrame anterior;
    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();

    ListGlobalStation ls = ListGlobalStation.getInstance();




    public FlujoMaxGUI() {
        this.initialize();

    }


    private void initialize() {
        this.frameFluj = new JFrame();

        this.frameFluj.setContentPane(panel1);
        this.frameFluj.setBounds(100, 100, 1200, 720);
        this.frameFluj.setResizable(false);

        HashMap<Integer, Station> lists =new HashMap<Integer, Station>(ls.getList());


        String[] array = new String[lists.size()];
       int i=0;
        for(Station s : lists.values()){
            array[i] = s.getName();
            i++;
        }


        CBStart.setModel(new DefaultComboBoxModel<>(array));


        CBEnd.setModel(new DefaultComboBoxModel<>(array));




        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String row[] = { "Inicio" , "Fin", "Flujo Maximo" };

                DefaultTableModel tableModel = new DefaultTableModel(row, 0);
                if(CBStart.getSelectedItem().toString().equals(CBEnd.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, "Los nombres de las estaciones Origen y Final no pueden ser iguales.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                }else {


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
                    List<String> listToTable = rm.flujoMax(start,end);


                    String fluj = listToTable.get(listToTable.size()-1);
                    Object[] data = { start.getName(), end.getName(), fluj};
                    tableModel.addRow(data);


                }
                table.setModel(tableModel);

            }
        });


    }
    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
