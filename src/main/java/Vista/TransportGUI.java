package main.java.Vista;

import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;
import main.java.Managers.TransportManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class TransportGUI {


    private JPanel panel1;
    private JButton addTransportButton;
    private JLabel exitButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton buscarButton;
    private JComboBox CBsearchTransport;
    private JTable table;
    private JTextField textTransport;
    private JScrollPane tableTransporte;
    private TransportDAO transportDAO = new TransportDAO();
    public JFrame frameTransport;

    private JFrame anterior;


    public TransportGUI() {
        this.initialize();

    }


    private void initialize(){
        this.frameTransport = new JFrame();

        this.frameTransport.setContentPane(panel1);
        this.frameTransport.setBounds(10, 10, 1200, 720);
        this.frameTransport.setResizable(false);

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TransportGUI.this.anterior.setVisible(true);
                TransportGUI.this.frameTransport.dispose();
            }

        });

        // Añadir transporte

        addTransportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                TransportAddGUI tadd = new TransportAddGUI();
                tadd.setAnterior(TransportGUI.this.anterior);
                tadd.frameTransportAdd.setVisible(true);



            }
        });


        // Editar transporte

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                TransportEditGUI te = new TransportEditGUI();
                te.setAnterior(TransportGUI.this.anterior);
                te.frameTransportEdit.setVisible(true);



            }
        });

        // Eliminar transporte
       deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indice = TransportGUI.this.table.getSelectedRow();
                if (indice == -1) {
                    AlertPanel a= new AlertPanel(EnumTipoAlerta.ERROR, "error", "eerror con", "eeee", null );
                    a.frame.setVisible(true);
                  //  JOptionPane.showMessageDialog(frameTransport,"Error" , "Failure", JOptionPane.ERROR_MESSAGE);
                   /* Aviso_ERROR error = new Aviso_ERROR("Seleccionar un camino.");
                    error.frameTransport.setLocationRelativeTo((Component)null);
                    error.frameTransport.setVisible(true);*/
                } else {
                    deleteTransport();
                    JOptionPane.showMessageDialog(frameTransport,"Estacion eliminada" , "Failure", JOptionPane.INFORMATION_MESSAGE);
                }

                    /*
                    Aviso_Confirmacion msj = new Aviso_Confirmacion("Esta seguro de eliminar el camino?");
                    msj.setAnterior(TransportGUI.this.a);
                    msj.frameTransport.setLocationRelativeTo((Component)null);
                    msj.frameTransport.setVisible(true);*/
                }


        });
        //busqueda de transportes
        String[] atributosTransporte = {"Id", "nombre", "Color", "Estado"};
        CBsearchTransport.setModel(new DefaultComboBoxModel<String>(atributosTransporte));

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String param = textTransport.getText();
                DTOTransport transportParam = new DTOTransport();
                TransportManager tm = new TransportManager();
                switch (CBsearchTransport.getSelectedIndex()){
                    case 0: {
                        //busqueda por id
                        Integer id = Integer.parseInt(param);
                        transportParam.setIdTransport(id);
                        ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);
                        break;
                    }
                    case 1:{
                        //busca por nombre
                        transportParam.setName(param);
                        ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);
                        break;
                    }
                    case 2:{
                        //busca por color
                        transportParam.setColour(param);
                        ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);
                        break;
                    }
                    case 3:{
                        System.out.println("Selecciono la busqueda por estado");
                        /*
                        //busca por estado de linea

                        transportParam.setStatus(param);
                        ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);
                        break;*/
                    }
                    default:
                }

            }
        });




    }

    private void deleteTransport() {
        int selected = table.getSelectedRow();
        int id = Integer.parseInt(this.table.getModel().getValueAt(selected, 0).toString());
        DTOTransport deleteT = new DTOTransport();
        deleteT.setIdTransport(id);
        TransportManager.deleteTransportRoute(deleteT);

    }

    public void updateTabla (ArrayList<DTOTransport> result){
        String [] atributos = {"ID", "Nombre", "Color", "Estado"};
        DefaultTableModel t = new DefaultTableModel(atributos, 0);

        /*table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel c = table.getColumnModel();
        c.getColumn(0).setPreferredWidth(15);
        c.getColumn(1).setPreferredWidth(35);
        c.getColumn(2).setPreferredWidth(35);
        c.getColumn(3).setPreferredWidth(25);*/

        ArrayList<DTOTransport> transpList = result;
        for(DTOTransport transportes : transpList){
            Integer id = transportes.getIdTransport();
            String name= transportes.getName();
            String color = transportes.getColour();
            Boolean estado = transportes.getStatus();

            Object[] datos = {id, name, color, estado};
            t.addRow(datos);
        }
        table.setModel(t);

    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
