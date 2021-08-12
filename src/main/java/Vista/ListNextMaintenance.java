package main.java.Vista;

import main.java.Managers.StationManager;
import main.java.classes.ListGlobalStation;
import main.java.classes.Station;

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

        for(Station s: listaEstaciones.values()){
            s.setMaintenanceHistory(sm.searchMaintenance(s.getIdStation()));
        }


        Comparator<Station> timestampComparator = new Comparator<Station>() {
            @Override
            public int compare(Station o1, Station o2) {
                if(returnLastDateMaintenance(o1).after(returnLastDateMaintenance(o2))){
                    return -1;
                }else
                if(returnLastDateMaintenance(o2).after(returnLastDateMaintenance(o1))){
                    return 1;
                }else {return 0;}
            }
        };



        PriorityQueue<Station> priorityQueueStation= new PriorityQueue<Station>(timestampComparator);

        for(Station s : listaEstaciones.values()){
            priorityQueueStation.add(s);
        }
        /*
           *    Por cada estacion buscar el ultimo mantenimiento y guardar la fecha
           *    Crear un comparator y listo wey
         */



        String[] valores = {"Id", "Nombre", "Fecha Ultimo Mantenimiento"};

        DefaultTableModel tabla = new DefaultTableModel(valores, 0);
        Iterator<Station> timestampIterator = priorityQueueStation.iterator();
        while (timestampIterator.hasNext()){
            Integer id = priorityQueueStation.poll().getIdStation();
            String name = priorityQueueStation.poll().getName();
            Timestamp lastMaint = returnLastDateMaintenance(priorityQueueStation.poll());

            Object[] data = {id, name, lastMaint};
            tabla.addRow(data);

        }
        stationTable.setModel(tabla);

    }

    /*
        *Metodo que recibe una estacion y retorna la fecha del ultimo mantenimiento que se hizo
     */
    public Timestamp returnLastDateMaintenance(Station s){

        int posUltimoMant = s.getMaintenanceHistory().size();
        return s.getMaintenanceHistory().get(posUltimoMant-1).getEndDate();

    }


}
