package main.java.Vista;

import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;

import main.java.Managers.TransportManager;
import main.java.classes.ListGlobalTransport;
import main.java.classes.TransportRoute;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
    private JComboBox CBStatus;
    private JScrollPane tableTransporte;
    private TransportDAO transportDAO = new TransportDAO();
    public JFrame frameTransport;
    private TransportManager tm = TransportManager.getInstance();
    private JFrame anterior;
    ListGlobalTransport lt = ListGlobalTransport.getInstance();


    public TransportGUI() {
        this.initialize();
        this.tableInit();
    }


    private void initialize(){
        this.frameTransport = new JFrame();

        this.frameTransport.setContentPane(panel1);
        this.frameTransport.setBounds(10, 10, 1200, 720);
        this.frameTransport.setResizable(false);
        this.CBStatus.setVisible(false);

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
                int indice = TransportGUI.this.table.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione una transport para editar.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);

                } else {

                    TransportEditGUI te = new TransportEditGUI(Integer.parseInt(TransportGUI.this.table.getModel().getValueAt(indice, 0).toString()));
                    te.setAnterior(TransportGUI.this.anterior);
                    te.frameTransportEdit.setLocationRelativeTo(null);
                    te.frameTransportEdit.setVisible(true);

                }

            }
        });

        // Eliminar transporte
       deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indice = TransportGUI.this.table.getSelectedRow();
                if (indice == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione una estacion para eliminar.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                } else {
                    deleteTransport();
                    JOptionPane.showMessageDialog(frameTransport,"Estacion eliminada" , "Failure", JOptionPane.INFORMATION_MESSAGE);
                }

            }


        });
        //busqueda de transportes
        String[] atributosTransporte = {"Id", "Nombre", "Color", "Estado"};
        CBsearchTransport.setModel(new DefaultComboBoxModel<String>(atributosTransporte));
        DTOTransport transportParam = new DTOTransport();
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String param = textTransport.getText();

                switch (CBsearchTransport.getSelectedIndex()){
                    case 0: {
                        //busqueda por id
                        Integer id = Integer.parseInt(param);
                        transportParam.setIdTransport(id);
                        /*ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);*/
                        break;
                    }
                    case 1:{
                        //busca por nombre
                        transportParam.setName(param);
                      /*  ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);*/
                        break;
                    }
                    case 2:{
                        //busca por color
                        transportParam.setColour(param);
                     /*   ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);*/
                        break;
                    }
                    case 3:{
                        //busca por estado de linea
                        if(CBStatus.getSelectedIndex()==0){
                            transportParam.setStatus(true);
                        }
                        if(CBStatus.getSelectedIndex()==1){
                            transportParam.setStatus(false);
                        }
                      /*  ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                        updateTabla(result);*/
                        break;
                    }
                    default:
                }
                ArrayList<DTOTransport> result = tm.searchDTOTransport(transportParam);
                updateTabla(result);

            }
        });
        CBsearchTransport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] estado = {"--seleccionar--", "Activa" , "No activa"};
                CBStatus.setModel(new DefaultComboBoxModel<String>(estado));
                //si el filtro por estado es seleecionado mostrar el CB del filtro por estado, sino ocultarlo
                if(CBsearchTransport.getSelectedIndex()==3){
                    CBStatus.setVisible(true);
                }else
                if(CBsearchTransport.getSelectedIndex()!=3){
                    CBStatus.setVisible(false);
                }
                if(CBStatus.getSelectedIndex()==1){
                    transportParam.setStatus(true);
                }
                if(CBStatus.getSelectedIndex()==2){
                    transportParam.setStatus(false);
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
        table.repaint();

    }

    public void updateTabla (ArrayList<DTOTransport> result){
        String [] atributos = {"ID", "Nombre", "Color", "Estado"};
        DefaultTableModel t = new DefaultTableModel(atributos, 0);

        ArrayList<DTOTransport> transpList = result;
        for(DTOTransport transportes : transpList){
            Integer id = transportes.getIdTransport();
            String name= transportes.getName();
            String color = transportes.getColour();
            String estado =null;
            if(transportes.getStatus()){
                estado= "Activa";
            }else{
                estado= "No Activa";
            }
            Object[] datos = {id, name, color, estado};
            t.addRow(datos);
        }
        table.setModel(t);

    }
    public void tableInit(){
        String [] atributos = {"ID", "Nombre", "Color", "Estado"};
        DefaultTableModel model = (DefaultTableModel)this.table.getModel();
        model.setRowCount(0);
        ArrayList<TransportRoute> listT = this.lt.getList();
        for(TransportRoute t: listT){
            Integer id = t.getIdTransport();
            String name= t.getName();
            Paint color = t.getColour();
            String estado = null;
            if(t.isStatus()){
                estado= "Activa";
            }else{
                estado= "No Activa";
            }
            Object[] datos = {id, name, color, estado};
            model.addRow(datos);

        }
    }




    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
