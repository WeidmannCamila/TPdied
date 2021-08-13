package main.java.Vista;

import main.java.Enumeration.EnumBestRoute;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.ListGlobalStation;
import main.java.classes.ListRoute;
import main.java.classes.Station;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class PageRankGUI {
    public JFrame framePageRank;
    private JFrame anterior;
    ArrayList<ViewPageRank> listaDatos = new ArrayList<>();
    ListGlobalStation ls = ListGlobalStation.getInstance();
    private JPanel panel1;
    private JLabel exitButton;
    private JComboBox CBStart;
    private JTable table;
    private JButton buscarButton;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();

    public PageRankGUI() {
        this.initialize();

    }


    private void initialize() {

        //Frame gui
        this.framePageRank = new JFrame();
        this.framePageRank.setContentPane(panel1);
        this.framePageRank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.framePageRank.setBounds(10, 10, 1200, 720);
        this.framePageRank.setResizable(false);
        this.framePageRank.setLocationRelativeTo(null);

        table.setFont(new Font("Serif", Font.BOLD, 20));



        //Boton buscar, busca todos depende si se seleccionó TODOS o alguna estacion en especial

        System.out.println("Entra a if sea page");
        listaDatos = grafoPanel.getPageRank();
        pageRankTable(listaDatos);



                  //  listaDatos = grafoPanel.getPageRank(CBStart.getSelectedItem());



        // Boton para volver
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PageRankGUI.this.anterior.setVisible(true);
                PageRankGUI.this.framePageRank.dispose();
            }

        });

    }

    // Tabla
    private void pageRankTable(ArrayList<ViewPageRank> listaDatos) {

        String row[] = { "ID", "NOMBRE", "PAGERANK" };
        DefaultTableModel tableModel = new DefaultTableModel(row, 0);

        for (ViewPageRank dat : listaDatos) {
            Float pager =Float.parseFloat(dat.getPageRank().toString());
            String id = dat.getVertice().getId().toString();
            String nombre = dat.getVertice().getName();
            String pageR = pager.toString();
            Object[] data = { id, nombre, pageR};
            tableModel.addRow(data);
        }

        table.setModel(tableModel);

    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }
}
