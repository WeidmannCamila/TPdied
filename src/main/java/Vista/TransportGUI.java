package main.java.Vista;

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
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JTextField textField3;
    private JTable table;

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

        // Eliminar insumo
       deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indice = TransportGUI.this.table.getSelectedRow();
                if (indice == -1) {
                    Aviso_ERROR error = new Aviso_ERROR("Seleccionar un camino.");
                    error.frameTransport.setLocationRelativeTo((Component)null);
                    error.frameTransport.setVisible(true);
                } else {
                    Aviso_Confirmacion msj = new Aviso_Confirmacion("Esta seguro de eliminar el camino?");
                    msj.setAnterior(TransportGUI.this.a);
                    msj.frameTransport.setLocationRelativeTo((Component)null);
                    msj.frameTransport.setVisible(true);
                }

            }
        });



    }

    public void CreateInsumoDialog() {
        JDialog jdialog = new JDialog(frameTransport, "Crear insumo", Dialog.ModalityType.DOCUMENT_MODAL);
        JPanel contentPane;

        JTextField txtDescripcion = new JTextField();
        JTextField txtCosto = new JTextField();
        JTextField txtPeso = new JTextField();
        // JComboBox<> comboUnidades = new JComboBox<UnidadDeMedida>();
        JCheckBox chckbxS = new JCheckBox("Insumo refrigerado");
        JButton btnGuardar = new JButton("Guardar");}


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
