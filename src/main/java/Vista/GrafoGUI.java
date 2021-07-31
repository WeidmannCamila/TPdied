package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Station;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GrafoGUI {
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel6;
    private JPanel panel4;
    private JPanel gradop;
    private JTable table1;

    public JFrame frameGrafo;
    private JFrame anterior;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();
    private Boolean aux = false;
    static StationManager sm = StationManager.getInstance();
    static RouteManager rm = RouteManager.getInstance();

    public GrafoGUI(ArrayList<Station> bestRoute) {
        this.initialize();
        this.aux = true;

    }
    public GrafoGUI() {
        System.out.println("entro a grafo gui");
        this.initialize();

    }

    public GrafoGUI(GrafoPanel grafoPanel) {
        this.grafoPanel= grafoPanel;
        this.initialize();
    }


    private void initialize() {
        this.frameGrafo = new JFrame();

        this.frameGrafo.getContentPane().add(panel1);
        this.frameGrafo.setBounds(10, 10, 1200, 720);
        this.frameGrafo.setResizable(false);

        JPanel panelView = new JPanel();
        frameGrafo.getContentPane().add(panelView, "info");
        panelView.setLayout(new BorderLayout(0,0));

        panelView.add(new Panel(), BorderLayout.SOUTH);

        JPanel panel = new JPanel();

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 20, 150, 150, 150, 150, 150, 150, 150, 150, 150, 40 };
        gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        panel.setLayout(gbl_panel);

        JLabel lblPanelDeAdministracin = new JLabel("Visualización de la información guardada"); // TODO: pensar un
        // nombre mejor
        lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
        gbc_lblPanelDeAdministracin.gridwidth = 7;
        gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 5);
        gbc_lblPanelDeAdministracin.gridx = 2;
        gbc_lblPanelDeAdministracin.gridy = 2;
        panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);


        JButton btnVolver_1 = new JButton("");
        btnVolver_1.setForeground(Color.WHITE);
        btnVolver_1.setIcon(new ImageIcon(new ImageIcon("src/main/resources/icons8_undo_32px.png").getImage().getScaledInstance(32,
                32, Image.SCALE_DEFAULT)));

        btnVolver_1.setOpaque(false);
        btnVolver_1.setContentAreaFilled(false);
        btnVolver_1.setBorderPainted(false);
        btnVolver_1.setBorder(null);
        btnVolver_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc_btnVolver_1 = new GridBagConstraints();
        gbc_btnVolver_1.anchor = GridBagConstraints.WEST;
        gbc_btnVolver_1.insets = new Insets(0, 0, 5, 5);
        gbc_btnVolver_1.gridx = 1;
        gbc_btnVolver_1.gridy = 2;
        panel.add(btnVolver_1, gbc_btnVolver_1);

        btnVolver_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                GrafoGUI.this.anterior.setVisible(true);
                GrafoGUI.this.frameGrafo.dispose();
            }
        });
        // Panelcito arriba para separar de la barra de título
        JPanel panel_555522 = new JPanel();
        GridBagConstraints gbc_panel_555522 = new GridBagConstraints();
        gbc_panel_555522.insets = new Insets(0, 0, 5, 5);
        gbc_panel_555522.fill = GridBagConstraints.BOTH;
        gbc_panel_555522.gridx = 6;
        gbc_panel_555522.gridy = 1;
        panel.add(panel_555522, gbc_panel_555522);

        // Panelcito abajo del título para separar y que no quede tan pegado
        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 5, 5);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 6;
        gbc_panel_2.gridy = 3;
        panel.add(panel_2, gbc_panel_2);

        // Panel a la izquierda para separar
        JPanel panel_5 = new JPanel();
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.insets = new Insets(0, 0, 5, 5);
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.gridx = 0;
        gbc_panel_5.gridy = 5;
        panel.add(panel_5, gbc_panel_5);

        // Y aca a la derecha
        JPanel panel_133 = new JPanel();
        GridBagConstraints gbc_panel_1 = new GridBagConstraints();
        gbc_panel_1.insets = new Insets(0, 0, 5, 0);
        gbc_panel_1.fill = GridBagConstraints.BOTH;
        gbc_panel_1.gridx = 11;
        gbc_panel_1.gridy = 5;
        panel.add(panel_133, gbc_panel_1);

        // ------------------------------------------------------------------------------------------------
        // Panel para visualización
        // ------------------------------------------------------------------------------------------------
        GridBagConstraints gbc_panel_91 = new GridBagConstraints();
        gbc_panel_91.gridwidth = 7;
        gbc_panel_91.insets = new Insets(0, 0, 0, 5);
        gbc_panel_91.fill = GridBagConstraints.BOTH;
        gbc_panel_91.gridx = 1;
        gbc_panel_91.gridy = 6;

        grafoPanel.setBackground(Color.RED);
        grafoPanel.setBorder(BorderFactory.createLineBorder(new Color(0x7A8A99)));
        panel.add(grafoPanel, gbc_panel_91);




    }
    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
