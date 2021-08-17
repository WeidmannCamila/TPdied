package main.java.Vista;

import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;
import main.java.Enumeration.EnumColour;
import main.java.Managers.TransportManager;
import main.java.classes.ListGlobalTransport;
import main.java.classes.Station;
import main.java.classes.TransportRoute;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class TransportEditGUI {
    private JPanel panel1;
    private JLabel exitButton;
    private EnumColour colours;
    private JComboBox statusCB;
    private JTextField transportId;
    private JButton editButton;
    private JTextField transportName;
    private JComboBox CBcolour;
    private DTOTransport dto = new DTOTransport();
    private TransportDAO tDAO = new TransportDAO();
    public JFrame frameTransportEdit;
    private TransportManager tm = TransportManager.getInstance();

    private JFrame anterior;

    public TransportEditGUI(int id) {
        this.initialize(id);

    }

    private void initialize(int id) {

        TransportRoute transportToEdit = tm.getTransport(id);


        this.frameTransportEdit = new JFrame();
        this.frameTransportEdit.setContentPane(panel1);
        this.frameTransportEdit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frameTransportEdit.setBounds(10, 10, 1200, 720);
        this.frameTransportEdit.setResizable(false);
        this.frameTransportEdit.setLocationRelativeTo(null);

        this.transportName.setText(transportToEdit.getName());

        String[] estado = {"--seleccionar--", "Activa" , "No activa"};
        statusCB.setModel(new DefaultComboBoxModel<String>(estado));


        String[] array = new String[7];
        int i=0;


        for(EnumColour s : colours.values()){
            if(s.getvColor().toString().equals(transportToEdit.getColour().toString())){
                array[0] = s.getvString();

            }

            i++;
            array[i] = s.getvString();


        }
        CBcolour.setModel(new DefaultComboBoxModel<>(array));

        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if(TransportEditGUI.this.transportName.getText().length() <= 0 || statusCB.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null, "Campos vacios.",
                            "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
                }else {
                    //dto.setIdTransport(Integer.parseInt(transportId.getText()));

                    dto.setIdTransport(transportToEdit.getIdTransport());
                    dto.setColour((String) CBcolour.getSelectedItem());
                    dto.setName(transportName.getText());
                    boolean stat;
                    if(statusCB.getSelectedIndex() ==1){
                        stat= true;
                    } else { stat= false;}

                    dto.setStatus(stat);

                    tm.updateTransport(dto);
                    JOptionPane.showMessageDialog(null, "Transporte actualizado.",
                            "EXITO", JOptionPane.INFORMATION_MESSAGE);

                    TransportEditGUI.this.anterior.setVisible(true);
                    TransportEditGUI.this.frameTransportEdit.dispose();
                }


            }
        });
        // salir transporte
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TransportEditGUI.this.anterior.setVisible(true);
                TransportEditGUI.this.frameTransportEdit.dispose();
            }

        });

    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
