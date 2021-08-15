package main.java.Vista;

import main.java.Managers.StationManager;
import main.java.classes.ListGlobalStation;
import main.java.classes.Station;
import main.java.classes.StationMaintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.*;

public class ListNextMaintenance extends JPanel {

    public JFrame frameNextMaint ;
    private JButton exitButton;
    private JTable stationTable;
    private JScrollPane stationPanel;
    private JPanel nextStationPanel;
    private JFrame anterior;
    StationManager sm = new StationManager();

    public ListNextMaintenance(){
        this.initialize();
    }

    private void initialize(){
        this.frameNextMaint = new JFrame();
        this.frameNextMaint.setContentPane(nextStationPanel);
        this.frameNextMaint.setResizable(false);
        this.frameNextMaint.setBounds(30,30,500,700);
        this.frameNextMaint.setLocationRelativeTo(null);



        //volver atras
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ListNextMaintenance.this.anterior.setVisible(true);
                ListNextMaintenance.this.frameNextMaint.dispose();
            }
        });

    }

    public void setAnterior(JFrame a ){
        this.anterior = a;
    }


    //metodo de mostrar estaciones
    public void showStationNextMaintenance( ){

        ListGlobalStation listS = ListGlobalStation.getInstance();
        HashMap<Integer, Station> listaEstaciones = new HashMap<Integer,Station>(listS.getList());
        ArrayList<StationMaintenance> listStation = new ArrayList<StationMaintenance>();
        int indice =0;
        for(Station s: listaEstaciones.values()){
            StationMaintenance stationMaintenance = new StationMaintenance();
            sm.searchMaintenanceStation(s);
            if(s.getMaintenanceHistory().size()>0){
                indice = s.getMaintenanceHistory().size();
                stationMaintenance.setIdStationMaintenance(s.getIdStation());
                stationMaintenance.setNameStationMaintenance(s.getName());
                if (indice > 0) {
                    stationMaintenance.setLastDateMaintenance(s.getMaintenanceHistory().get(indice-1).getEndDate());
                }
                listStation.add(stationMaintenance);
            }
        }

        Comparator<StationMaintenance> timestampComparator = new Comparator<StationMaintenance>() {
            @Override
            public int compare(StationMaintenance o1, StationMaintenance o2) {
                if(o1.getLastDateMaintenance().before(o2.getLastDateMaintenance())){
                    return -1;
                }else
                if(o2.getLastDateMaintenance().before(o1.getLastDateMaintenance())){
                    return 1;
                }else {return 0;}
            }
        };
       PriorityQueue<StationMaintenance> priorityQueueStation= new PriorityQueue<StationMaintenance>(timestampComparator);

        for(StationMaintenance s :listStation){
            priorityQueueStation.add(s);
        }
        String[] valores = {"Id", "Nombre", "Fecha Ultimo Mantenimiento"};

        DefaultTableModel tabla = new DefaultTableModel(valores, 0);

       Iterator<StationMaintenance> timestampIterator = priorityQueueStation.iterator();

        System.out.println("El proximo elemento a salir es : "+ priorityQueueStation.peek().getNameStationMaintenance());

        while (timestampIterator.hasNext()){
            Integer id = priorityQueueStation.peek().getIdStationMaintenance();
            String name = priorityQueueStation.peek().getNameStationMaintenance();
            Timestamp lastMaint = priorityQueueStation.peek().getLastDateMaintenance();

            Object[] data = {id, name, lastMaint};
            tabla.addRow(data);
            priorityQueueStation.remove();
        }
/*
        for(StationMaintenance man: listStation){
            Integer id = man.getIdStationMaintenance();
            String name = man.getNameStationMaintenance();
            Timestamp time = man.getLastDateMaintenance();

            Object[] data = {id, name, time};
            tabla.addRow(data);
        }*/
        stationTable.setModel(tabla);

    }

}
