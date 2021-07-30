package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DTOs.DTOMaintenance;
import main.java.DTOs.DTOStation;
import main.java.Enumeration.EnumStatus;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;
import main.java.Managers.StationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
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
    private JTable maintenanceTable;
    private JButton verHistorialDeMantenimientosButton;

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
                int indice = StationGUI.this.table.getSelectedRow();
                if (indice == -1) {
                    AlertPanel a= new AlertPanel(EnumTipoAlerta.ERROR, "error", "eerror con", "eeee", null );
                    a.frame.setVisible(true);
                } else {
                    deleteStation();
                    JOptionPane.showMessageDialog(frameStation,"La estacion seleccionada fue eliminada. Por favor regrese al menu anterior para ver los cambios" , "Informacion", JOptionPane.INFORMATION_MESSAGE);

                }


            }


        });

        //busqueda de estaciones


       String[] items = {"Id", "Nombre", "Estado", "Hora Apertura", "Hora Clausura"};

       CBsearch.setModel(new DefaultComboBoxModel<String>(items));

       searchButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String param = textStation.getText();
                DTOStation estacionParametro = new DTOStation();

              switch (CBsearch.getSelectedIndex()){
                   case 0:{
                       // por id
                       Integer id;
                       try{
                           id = Integer.parseInt(param);
                       } catch(NumberFormatException i){
                           System.out.println("tiene que ser un id");
                           return;
                       }
                       estacionParametro.setIdStation(id);
                       ArrayList<DTOStation> result = StationManager.search4id(estacionParametro);
                       updateTable(result);
                       break;
                   }
                   case 1: {
                       //se buscar por nombre
                       estacionParametro.setName(param);
                       ArrayList<DTOStation> result = StationManager.search4name(estacionParametro);
                       updateTable(result);
                       break;


                   }
                   case 2: {
                       //por estado
                       CBstatus.setVisible(true);
                       CBstatus.setModel(new DefaultComboBoxModel<EnumStatus>(EnumStatus.values()));
                       estacionParametro.setStatus(EnumStatus.values().toString());
                       ArrayList<DTOStation> result = StationManager.search4status(estacionParametro);
                       updateTable(result);
                    break;
                   }
                   case 3: {
                       // por hora de apertura
                   }
                   case 4:{
                       // y aca de clausura

                   }
                   default:

               }

           }
       });

       //mostrar tabla de lista de mantenimientos

        String[] atributosMantenimiento = {"Fecha Inicio Mant", "Fecha Fin Mant", "Observaciones" };
        verHistorialDeMantenimientosButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                DTOStation estacionParametro = new DTOStation();
                Integer id = estacionParametro.getIdStation();
                ArrayList<DTOMaintenance> mantenimientos = StationManager.searchMaintenance(estacionParametro);
                updateTableMaintenances(mantenimientos);
                //buscar los mantenimientos que tiene esa estacion y mostrarlos

            }
        });

    }

    public void updateTableMaintenances(ArrayList<DTOMaintenance> m){
        String atributes[] = {"Id" , "Descripcion" , "Hs Inicio",  "Hs Fin"};
        DefaultTableModel tabla = new DefaultTableModel(atributes, 0 );
        ArrayList<DTOMaintenance> listMaintenance = m;

        for(DTOMaintenance maint: listMaintenance){
            Integer id = maint.getIdMaintenance();
            String desc = maint.getDescription();
            Timestamp hsInicio = maint.getStartDate();
            Timestamp hsFin = maint.getEndDate();

            Object[] data = {id, desc, hsInicio, hsFin};
            tabla.addRow(data);
        }
        maintenanceTable.setModel(tabla);

    }

    private void updateTable(ArrayList<DTOStation> result) {

        String column[] = {"ID", "Nombre",  "Estado" , "Hora a", "Hora c"};
        DefaultTableModel tm = new DefaultTableModel(column, 0);

        ArrayList<DTOStation> listStations = result;

        for (DTOStation station : listStations) {
            Integer id = station.getIdStation();
            String name = station.getName();
            String status = "MANTENIMIENTO" ;//station.getStatus().toString();
            String ha = "hora";//station.getOpen().toString();
            String hc ="hora"; //station.getClouse().toString();

            Object[] data = {id, name, status, ha, hc};
            tm.addRow(data);

        }
        table.setModel(tm);


    }

    private void deleteStation() {
        int selected = table.getSelectedRow();
        //System.out.println("El indice de la tabla es " + selected );
        int id = Integer.parseInt(this.table.getModel().getValueAt(selected, 0).toString());
        DTOStation deleteS = new DTOStation();
        //System.out.println("El id de la estacion es " + id);
        deleteS.setIdStation(id);
        StationManager.deleteStationObject(deleteS);

        /*
       TODO una vez borrada la estacion hacer una funcion que limpie/actualice la tabla
        */
    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }


    /*
* Falta agregar un boton que permita seleccionar una estacion y al presionarlo muestre una lista de los mantenimientos que tuvo
* la lista tiene que mostrar el id, fechas de inicio y fin, y descripcion
*
* */

}
