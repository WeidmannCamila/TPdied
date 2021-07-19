package main.java.Vista;

import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumTipoAlerta;
import main.java.Herramientas.AlertPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TransportGUI {


    private JPanel panel1;
    private JButton addTransportButton;
    private JLabel exitButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton buscarButton;
    private JComboBox comboBox1;
    private JTable table;
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
                    JOptionPane.showMessageDialog(frameTransport,"Error" , "Failure", JOptionPane.INFORMATION_MESSAGE);
                    deleteTransport();
                }

                    /*
                    Aviso_Confirmacion msj = new Aviso_Confirmacion("Esta seguro de eliminar el camino?");
                    msj.setAnterior(TransportGUI.this.a);
                    msj.frameTransport.setLocationRelativeTo((Component)null);
                    msj.frameTransport.setVisible(true);*/
                }


        });



    }

    private void deleteTransport() {
        int selected = table.getSelectedRow();
        int id = Integer.parseInt(this.table.getModel().getValueAt(selected, 0).toString());
        DTOTransport deleteT = new DTOTransport();
        deleteT.setIdTransport(id);
        transportDAO.deleteTransport(deleteT);

    }




    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
