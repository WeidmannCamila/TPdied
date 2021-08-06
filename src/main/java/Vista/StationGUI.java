package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DTOs.DTOMaintenance;
import main.java.DTOs.DTOStation;
import main.java.Enumeration.EnumStatus;


import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    private JScrollPane maintenanceJPanel;
    private JTextField HourOpenTField;
    private JTextField HourClosedTField;
    private JTextField MinuteClosedTField;
    private JTextField MinuteOpenTField;
    private JComboBox CBtime;
    private Integer idStationSelected;
    private StationDAO stationDAO = new StationDAO();
    public JFrame frameStation;
    private JFrame anterior;
    public StationManager sm = StationManager.getInstance();

    public StationGUI() {
        this.initialize();

    }


    private void initialize(){
        this.frameStation = new JFrame();
        this.frameStation.setContentPane(panel1);
        this.frameStation.setBounds(100, 100, 1200, 720);
        this.frameStation.setResizable(false);
        this.maintenanceJPanel.setVisible(true);
        this.maintenanceJPanel.setPreferredSize(new Dimension(450,800));
        this.HourOpenTField.setEditable(false);
        this.MinuteOpenTField.setEditable(false);
        this.HourClosedTField.setEditable(false);
        this.MinuteClosedTField.setEditable(false);

        // Añadir estacion

        addStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                StationAddGUI sadd = new StationAddGUI();
                sadd.setAnterior(StationGUI.this.anterior);
                sadd.frameStationAdd.setVisible(true);
            }
        });

        // Editar estacion, se selecciona un elemento de la tabla y continua en la pantalla de editStation
        editStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                int indice = StationGUI.this.table.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione una estacion para editar.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);

                } else {

                    StationEditGUI te = new StationEditGUI(Integer.parseInt(StationGUI.this.table.getModel().getValueAt(indice, 0).toString()));
                    te.setAnterior(StationGUI.this.anterior);
                    te.frameStationEdit.setLocationRelativeTo(null);
                    te.frameStationEdit.setVisible(true);
                }

            }
        });

        // Eliminar estacion
        deleteStationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indice = StationGUI.this.table.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione una estacion para eliminar.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                } else {
                    int id = (int) table.getModel().getValueAt(table.getSelectedRow(),0);
                    int resp=  JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar? Considere que se quitaran las rutas a dicha estacion");
                    if(JOptionPane.OK_OPTION == resp){
                        deleteStation(id);
                    }

                }

            }

        });

        // salir estacion
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StationGUI.this.anterior.setVisible(true);
                StationGUI.this.frameStation.dispose();
            }

        });

        //busqueda de estaciones
       String[] items = {"Id", "Nombre", "Estado", "Hora Apertura", "Hora Clausura"};

       CBsearch.setModel(new DefaultComboBoxModel<String>(items));

       DTOStation estacionParametro = new DTOStation();

        searchButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               String param = textStation.getText();

               estacionParametro.setStatus(null);
               estacionParametro.setName(null);
               estacionParametro.setIdStation(null);
               ArrayList<DTOStation> result = new ArrayList<>();
               switch (CBsearch.getSelectedIndex()){
                   case 0:{
                       // por id
                       Integer id;
                       try{
                           id = Integer.parseInt(param);
                       }catch(NumberFormatException i){
                           System.out.println("tiene que ser un id");
                           return;
                       }
                       estacionParametro.setIdStation(id);

                       break;
                   }
                   case 1: {
                       //se buscar por nombre
                       estacionParametro.setName(param);

                       int id = table.getSelectedRow();
                       break;
                   }
                   case 2: {
                       //por estado
                       //avisar que no se encontraron estaciones
                       if(CBstatus.getSelectedIndex()==1){
                           estacionParametro.setStatus("OPERATIVA");
                       }
                       if(CBstatus.getSelectedIndex()==2){
                           estacionParametro.setStatus("MANTENIMIENTO");
                       }

                       break;
                   }
                   case 3: {
                       String fechaApertura = HourOpenTField.getText()+ ":" + MinuteOpenTField.getText();
                       estacionParametro.setOpen(fechaApertura);

                       break;
                   }
                   case 4:{
                       String fechaCierre = HourClosedTField.getText()+ ":" + MinuteClosedTField.getText();
                       estacionParametro.setOpen(fechaCierre);

                       break;
                    }
                   default:
               }
               result = sm.searchStation(estacionParametro);
               if(result.size()>0){
                   updateTable(result);
               }else{
                   showStationListEmpty();
               }
           }
       });

       //mostrar tabla de lista de mantenimientos
        String[] atributosMantenimiento = {"Id Mant, Fecha Inicio Mant", "Fecha Fin Mant", "Observaciones" };

        verHistorialDeMantenimientosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id= (int) table.getModel().getValueAt(table.getSelectedRow(),0);

                //llamar al dao para buscar la estacion y luego crear un mantenimiento y setearle la estacion
                ArrayList<DTOMaintenance> mantenimientos = StationManager.searchMaintenance(id);
                if(mantenimientos.size()>0){
                    updateTableMaintenances(mantenimientos);
                }else{
                    showMaintenanceListEmpty();
                }
            }
        });

        CBsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] estado = {"--seleccionar--","OPERATIVA", "MANTENIMIENTO"} ;
                CBstatus.setModel(new DefaultComboBoxModel<String>(estado));
                estacionParametro.setStatus(EnumStatus.values().toString());
                if(CBsearch.getSelectedIndex()==2){
                    CBstatus.setVisible(true);
                }
                else if(CBsearch.getSelectedIndex()!=2){
                    CBstatus.setVisible(false);
                }
                if(CBsearch.getSelectedIndex()==3){
                    HourOpenTField.setEditable(true);
                    MinuteOpenTField.setEditable(true);
                }
                else if(CBsearch.getSelectedIndex()!=3){
                    HourOpenTField.setEditable(false);
                    MinuteOpenTField.setEditable(false);
                }
                if(CBsearch.getSelectedIndex()==4){
                    HourClosedTField.setEditable(true);
                    MinuteClosedTField.setEditable(true);
                }
                else if(CBsearch.getSelectedIndex()!=4){
                    HourClosedTField.setEditable(false);
                    MinuteClosedTField.setEditable(false);
                }


            }
        });

        //combo box del status
        CBstatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CBstatus.getSelectedIndex()==1){
                    estacionParametro.setStatus("OPERATIVA");
                }
                if(CBstatus.getSelectedIndex()==2){
                    estacionParametro.setStatus("MANTENIMIENTO");
                }
                ArrayList<DTOStation> result = sm.searchStation(estacionParametro);
                updateTable(result);
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
            String status = station.getStatus();
            String ha = station.getOpen();
            String hc = station.getClouse();

            Object[] data = {id, name, status, ha, hc};
            tm.addRow(data);
        }
        table.setModel(tm);
    }

    private void deleteStation(int id) {

        int selected = table.getSelectedRow();

        DTOStation deleteS = new DTOStation();

        deleteS.setIdStation(id);
        sm.deleteStationObject(deleteS);
        JOptionPane.showMessageDialog(null, "Se ha eliminado la estacion",
                "EXITO", JOptionPane.INFORMATION_MESSAGE);
        table.repaint();


    }

    public String crearFecha(String hora, String minuto){

        String fecha = "2021-03-01 ";
        fecha+= hora;
        fecha+=":";
        fecha+= minuto;
        fecha+= ":00";

        return fecha;
    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }

    public void showStationListEmpty(){

            JOptionPane.showMessageDialog(null, "No se encuentran estaciones con los atributos seleccionados.",
                    "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);

    }
    public void showMaintenanceListEmpty(){

        JOptionPane.showMessageDialog(null, "No se encuentran Mantenimientos de la estacion seleccionada.",
                "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);

    }



    /*
* Falta agregar un boton que permita seleccionar una estacion y al presionarlo muestre una lista de los mantenimientos que tuvo
* la lista tiene que mostrar el id, fechas de inicio y fin, y descripcion
*
* */

}
