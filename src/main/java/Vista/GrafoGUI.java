package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.ListRoute;
import main.java.classes.Station;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GrafoGUI {

    private static JTable table;
    public JFrame frameGrafo;
    private JFrame anterior;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();
    private Boolean aux = false;
    public ListRoute listRoute;
    static StationManager sm = StationManager.getInstance();
    static RouteManager rm = RouteManager.getInstance();
    ArrayList<ArrayList<Station>> bestRoute;
    public ArrayList<ListRoute> listPaths = new ArrayList<>();

    public GrafoGUI(ArrayList<ArrayList<Station>> bestRoute) {
        System.out.println("ENTRA AL CONSTRUCTOR Y BEST ROUTE ES" + bestRoute.size());
        this.initialize(bestRoute);
        this.bestRoute =bestRoute;
        this.aux = true;

    }


    public GrafoGUI() {
    //    this.grafoPanel= grafoPanel;
        this.initialize(null);
    }


    private void initialize(ArrayList<ArrayList<Station>> bestRoute) {
        this.frameGrafo = new JFrame();
        this.frameGrafo.setBounds(10, 10, 1250, 720);
        this.frameGrafo.setResizable(false);

        JPanel panelView = new JPanel();
        frameGrafo.getContentPane().add(panelView);
        panelView.setLayout(new BorderLayout(0,0));

        panelView.add(new Panel(), BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panelView.add(panel, BorderLayout.WEST);

        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 350, 350};
        gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };

        panel.setLayout(gbl_panel);

        //panel arriba
        JLabel lblPanelDeAdministracin = new JLabel("Trayectos disponibles");
        lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
        gbc_lblPanelDeAdministracin.gridwidth = 6;
        gbc_lblPanelDeAdministracin.insets = new Insets(0, 0, 5, 1);
        gbc_lblPanelDeAdministracin.gridx = 2;
        gbc_lblPanelDeAdministracin.gridy = 2;
        panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);


        /*JButton btnVolver_1 = new JButton("");
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

        // Panel separador
        JPanel panel_555522 = new JPanel();
        GridBagConstraints gbc_panel_555522 = new GridBagConstraints();
        gbc_panel_555522.insets = new Insets(0, 0, 2, 2);
        gbc_panel_555522.fill = GridBagConstraints.BOTH;
        gbc_panel_555522.gridx = 6;
        gbc_panel_555522.gridy = 1;
        panel.add(panel_555522, gbc_panel_555522);*/

        // Panelcito abajo del título para separar y que no quede tan pegado
        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 2, 0);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 3;
        gbc_panel_2.gridy = 2;
        panel.add(panel_2, gbc_panel_2);


       /* // Panel a la izquierda para separar
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
        panel.add(panel_133, gbc_panel_1);*/

        //panel derecha
        table = new JTable();
        JScrollPane panel_scrlpn = new JScrollPane(table);
        GridBagConstraints gbc_panel_12 = new GridBagConstraints();
        gbc_panel_12.gridwidth = 3;
        gbc_panel_12.insets = new Insets(0, 2, 0, 0);
        gbc_panel_12.fill = GridBagConstraints.BOTH;
        gbc_panel_12.gridx =8;
        gbc_panel_12.gridy = 6;
        System.out.println("NO ENTRA AL ID DE NULL" + bestRoute.size());
        if(bestRoute != null){
            System.out.println("ENTRA AL ID DE BEST ROUTE");

            for(ArrayList<Station> s : bestRoute){
                Double distance = rm.distanceTotalRoute(s);
                Double duration = rm.durationTotalRoute(s);
                Double cost = rm.costTotalRoute(s);

                ArrayList<Station> aux = new ArrayList<>(s.subList(1, s.size()-1));

                System.out.println("DURACION: " + duration + "  dostance:" + distance + "  costo;" + cost);
                System.out.println("LISTA COMPLETA DE ESTACIONES : " + aux.size() + " este es el tamaño del la lista completa " + bestRoute.size());

                listRoute = new ListRoute(s.get(0),s.get(s.size()-1), distance, duration, cost, aux);
                listPaths.add(listRoute);
            }

        }

        refreshRutaTable(listPaths);
        panel.add(panel_scrlpn, gbc_panel_12);


      /*  GridBagLayout gbl_panel_1 = new GridBagLayout();
        gbl_panel_1.columnWidths = new int[] { 30, 0, 0 };
        gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        panel_scrlpn.setLayout(gbl_panel_1);*/
        // ------------------------------------------------------------------------------------------------
        // Panel para visualización
        // ------------------------------------------------------------------------------------------------
        GridBagConstraints gbc_panel_91 = new GridBagConstraints();
        gbc_panel_91.gridwidth = 7;
        gbc_panel_91.insets = new Insets(0, 0, 0, 1);
        gbc_panel_91.fill = GridBagConstraints.BOTH;
        gbc_panel_91.gridx = 0;
        gbc_panel_91.gridy = 6;

        grafoPanel.setBackground(new Color(0xcccccc));
        grafoPanel.setBorder(BorderFactory.createLineBorder(new Color(0x7A8A99)));
        panel.add(grafoPanel, gbc_panel_91);


        this.frameGrafo.setContentPane(panel);





    }
    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


   public void refreshRutaTable(ArrayList<ListRoute> listPaths) {
        String row[] = { "Origen", "Pasa", "Destino", "Distancia", "Duración", "Costo" };
        DefaultTableModel tableModel = new DefaultTableModel(row, 0);

        for (ListRoute ruta : listPaths) {
            String origen = ruta.getOrigin().getName();
            String destino = ruta.getDestination().getName();
            String distancia = ruta.getTotalDistance() + " km";
            String duracion = ruta.getTotalDuration() + " min";
            String costo = ruta.getTotalCost() + " $";
            ArrayList<Station> stations = ruta.listStation;
            String s = null;
            for ( Station ss : stations){
                s +=ss.getName();
            }

            Object[] data = { origen, stations, destino, distancia, duracion, costo };
            tableModel.addRow(data);
        }

        table.setModel(tableModel);
    }
}
