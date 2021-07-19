package main.java.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RouteGUI {
    private JPanel panel1;
    private JPanel panel2;
    private JButton button3;
    private JButton añadirTransporteButton;
    private JButton buscarButton;
    private JButton modificarTransporteButton;
    private JLabel exitButton;
    public JFrame frameRoute;
    private JFrame anterior;


    public RouteGUI() {
        this.initialize();

    }


    private void initialize(){
        this.frameRoute = new JFrame();

        this.frameRoute.setContentPane(panel1);
        this.frameRoute.setBounds(100, 100, 1200, 720);
        this.frameRoute.setResizable(false);

        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RouteGUI.this.anterior.setVisible(true);
                RouteGUI.this.frameRoute.dispose();
            }

        });

        JButton btnNuevoInsumo = new JButton("Nuevo insumo");


        btnNuevoInsumo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                CreateInsumoDialog();
            }
        });

        this.frameRoute.setContentPane(btnNuevoInsumo);

        // Editar insumo
        JButton btnEditar_1 = new JButton("Editar");



        // Eliminar insumo
        JButton btnEliminar = new JButton("Eliminar");





    }




    public void setAnterior(JFrame a) {
        this.anterior = a;
    }

    public void CreateInsumoDialog() {
        JDialog jdialog = new JDialog(frameRoute, "Crear insumo", Dialog.ModalityType.DOCUMENT_MODAL);
        JPanel contentPane;

        JTextField txtDescripcion = new JTextField();
        JTextField txtCosto = new JTextField();
        JTextField txtPeso = new JTextField();
       // JComboBox<> comboUnidades = new JComboBox<UnidadDeMedida>();
        JCheckBox chckbxS = new JCheckBox("Insumo refrigerado");
        JButton btnGuardar = new JButton("Guardar");}


}
