package main.java.Vista;

import main.java.DAO.StationDAO;
import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StationGUI extends JPanel{
    private JPanel panel1;
    private JLabel exitButton;
    private JButton buscarButton;
    private JTextField nameStation;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JButton addStationButton;
    private JTable tableT;
    private JButton editStationButton;
    private JButton deleteStationButton;
    private JScrollPane table;

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

    }

    private void deleteStation() {
        int selected = tableT.getSelectedRow();
        int id = Integer.parseInt(this.tableT.getModel().getValueAt(selected, 0).toString());
        DTOStation deleteS = new DTOStation();
        deleteS.setIdTransport(id);
        stationDAO.deleteStation(deleteS);
    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }




}
