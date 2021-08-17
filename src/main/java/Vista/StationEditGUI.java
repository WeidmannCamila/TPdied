package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DTOs.DTOStation;
import main.java.Enumeration.EnumStatus;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Maintenance;
import main.java.classes.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;
import java.util.logging.SimpleFormatter;

public class StationEditGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private JComboBox statusCB;
    private JTextField stationId;
    private JButton editButton;
    private JTextField stationName;
    private JTextField HourOpenTField;
    private JTextField MinuteOpenTField;
    private JTextField HourClosedTField;
    private JTextField MinuteCloseTf;
    private JTextArea textAreaM;
    private JLabel textM;
    private DTOStation dto = new DTOStation();
    private StationDAO sDAO = new StationDAO();
    public JFrame frameStationEdit;
    private JFrame anterior;
    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();


    public StationEditGUI(int selectedRow) {
        this.initialize(selectedRow);

    }

    private void initialize(int selectedRow) {

        Station stationToEdit = sm.getStation(selectedRow);
        this.frameStationEdit = new JFrame();

        this.frameStationEdit.setContentPane(panel1);
        this.frameStationEdit.setBounds(10, 10, 1200, 720);
        this.frameStationEdit.setResizable(false);
        this.frameStationEdit.setLocationRelativeTo(null);

        this.stationName.setText(stationToEdit.getName());

        String horarioApertura = stationToEdit.getOpeningTime();
        String horarioCierre= stationToEdit.getClosingTime();
        String[] partesApertura = horarioApertura.split(":");
        String[] partesCierre = horarioCierre.split(":");

        SimpleDateFormat nuevoFormato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        this.HourOpenTField.setText(partesApertura[0]);
        this.MinuteOpenTField.setText(partesApertura[1]);
        this.HourClosedTField.setText(partesCierre[0]);
        this.MinuteCloseTf.setText(partesCierre[1]);

        String[] estado = {stationToEdit.getStatus() ,"OPERATIVA", "MANTENIMIENTO"} ;
        statusCB.setModel(new DefaultComboBoxModel<String>(estado));

        statusCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (statusCB.getSelectedItem().toString() == "MANTENIMIENTO") {
                    textAreaM.setVisible(true);
                    textM.setVisible(true);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    if(stationName.getText() != null){

                        dto.setIdStation(stationToEdit.getIdStation());
                        dto.setName(stationName.getText());

                        String fechaApertura = HourOpenTField.getText()+ ":" + MinuteOpenTField.getText();
                        dto.setOpen(fechaApertura);
                        String fechaCierre = HourClosedTField.getText()+ ":" + MinuteCloseTf.getText();
                        dto.setClosed(fechaCierre);
                        dto.setStatus(statusCB.getSelectedItem().toString());
                        if(stationToEdit.getStatus().equals("OPERATIVA")){
                            if(stationToEdit.getStatus()!= dto.getStatus()){
                                Calendar cal = GregorianCalendar.getInstance();
                                Timestamp tApertura = new Timestamp(cal.getTimeInMillis());




                                Maintenance maint = new Maintenance(tApertura);
                                maint.setDescription(textAreaM.getText());
                                System.out.println("MANTENIMIENTO " + maint + " " + maint.getDescription());
                                sm.addMaintenance(maint, stationToEdit.getIdStation());

                                //stationToEdit.getMaintenanceHistory().add(maint);
                                //stationToEdit.addMaintenance(maint);
                                //aca va un update Station
                                System.out.println("El tiempo de apertura de mantenimiento es : "+ tApertura);
                            }
                        }else
                            if(stationToEdit.getStatus().equals("MANTENIMIENTO")){
                                if(stationToEdit.getStatus()!= dto.getStatus()){
                                    Calendar cal = GregorianCalendar.getInstance();
                                    Timestamp tCierre = new Timestamp(cal.getTimeInMillis());
                                    System.out.println("ultimo mantenimiento " + sm.searchMaintenance(stationToEdit.getIdStation()));
                                    ArrayList<Maintenance> manta =sm.searchMaintenance(stationToEdit.getIdStation());
                                    Maintenance mant = manta.get(manta.size()-1);

                                  //  ArrayList<Maintenance> mant = sm.searchMaintenance(stationToEdit.getMaintenanceHistory().get(stationToEdit.getMaintenanceHistory().size()-1).getIdMaintenance());
                                    mant.setEndDate(tCierre);
                                    sm.updateMaintenance(mant);
                                    //hacer un update del mantenimiento creado
                                  //  System.out.println("La fecha de cierre de mant es : " + mant.get(0).getEndDate());
                                }
                            }


                        sm.updateStation(dto);
                        JOptionPane.showMessageDialog(null, "Estacion  actualizada.","EXITO", JOptionPane.INFORMATION_MESSAGE);
                        StationEditGUI.this.anterior.setVisible(true);
                        StationEditGUI.this.frameStationEdit.dispose();


                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Campos vacios.",
                                "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                    }

            }
        });
        // salir estacion
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StationEditGUI.this.anterior.setVisible(true);
                StationEditGUI.this.frameStationEdit.dispose();
            }

        });

    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }






    /*
            CUANDO apretas el boton editar tenes que preguntar si se modifico el estado de la estacion
            si pasa de operativa a en mantenimiento crear una nuevo obj manteminiento y agregarlo a la lista de la estacion

            guardar la fecha de inicio y fin de cada mantenimiento

         */




}
