package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DTOs.DTOStation;
import main.java.Enumeration.EnumStatus;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;
import main.java.Managers.StationManager;
import main.java.classes.Station;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StationGUI extends JPanel{
    private JPanel panel1;
    private JLabel exitButton;
    private JComboBox<String> CBsearch;
    private JTable table;
    private JButton searchButton;
    private JButton addStationButton;
    private JTable tableT;
    private JButton editStationButton;
    private JButton deleteStationButton;
    private JTextField textStation;
    private JComboBox CBstatus;

    private StationDAO stationDAO = new StationDAO();
    public JFrame frameStation;

    private JFrame anterior;


    public StationGUI() {
        this.initialize();

    }


    private void initialize(){
        this.frameStation = new JFrame();

        this.frameStation.setContentPane(panel1);
        this.frameStation.setBounds(100, 100, 1200, 720);
        this.frameStation.setResizable(false);


        // salir estacion
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StationGUI.this.anterior.setVisible(true);
                StationGUI.this.frameStation.dispose();
            }

        });


        // Añadir estacion

        addStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                StationAddGUI sadd = new StationAddGUI();
                sadd.setAnterior(StationGUI.this.anterior);
                sadd.frameStationAdd.setVisible(true);



            }
        });


        // Editar estacion

        editStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                StationEditGUI te = new StationEditGUI();
                te.setAnterior(StationGUI.this.anterior);
                te.frameStationEdit.setVisible(true);



            }
        });

        // Eliminar estacion
        deleteStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indice = StationGUI.this.tableT.getSelectedRow();
                if (indice == -1) {
                    AlertPanel a= new AlertPanel(EnumTipoAlerta.ERROR, "error", "eerror con", "eeee", null );
                    a.frame.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(frameStation,"Error" , "Failure", JOptionPane.INFORMATION_MESSAGE);
                    deleteStation();
                }


            }


        });

        //busqueda de estaciones


       String[] items = {"Nombre", "Id", "Estado", "Hora Apertura", "Hora Clausura"};

       CBsearch.setModel(new DefaultComboBoxModel<String>(items));

       searchButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String param = textStation.getText();

               System.out.println("entro a buscar");

               switch (CBsearch.getSelectedIndex()){
                   case 0:{
                       //se buscar por nombre
                       System.out.println("entro a buscar por nombre");
                       ArrayList<DTOStation> result = StationManager.search4name(param);
                       updateTable(result);
                       break;

                   }
                   case 1: {
                       // por id
                       Integer id;
                       try{
                           id = Integer.parseInt(param);
                       } catch(NumberFormatException i){
                          System.out.println("tiene que ser un id");
                          return;
                       }
                       ArrayList<DTOStation> result = StationManager.search4id(param);
                       updateTable(result);
                        
                   }
                   case 2: {
                       //por estado
                       CBstatus.setVisible(true);
                       CBstatus.setModel(new DefaultComboBoxModel<EnumStatus>(EnumStatus.values()));
                       ArrayList<DTOStation> result = StationManager.search4status(CBstatus.getSelectedItem().toString());
                       updateTable(result);

                   }
                   case 3: {
                       // por hora de apertura
                   }
                   case 4:{
                       // y aca de clausura

                   }

               }

           }
       });

    }

    private void updateTable(ArrayList<DTOStation> result) {

        String column[] = {"Nombre", "ID", "Estado" , "Hora a", "Hora c"};
        DefaultTableModel tm = new DefaultTableModel(column, 0);

        ArrayList<DTOStation> listStations = result;

        for (DTOStation station : listStations) {
            Integer id = station.getIdStation();
            String name = station.getName();
           String status = station.getStatus().toString();
           String ha = "hora";//station.getOpen().toString();
            String hc ="hora"; //station.getClouse().toString();

            Object[] data = {id, name, status, ha, hc};
            tm.addRow(data);

        }
        table.setModel(tm);


    }

    private void deleteStation() {
        int selected = tableT.getSelectedRow();
        int id = Integer.parseInt(this.tableT.getModel().getValueAt(selected, 0).toString());
        DTOStation deleteS = new DTOStation();
        deleteS.setIdStation(id);
        stationDAO.deleteStation(deleteS);
    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }




}
