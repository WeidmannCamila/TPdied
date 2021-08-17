package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.TicketManager;
import main.java.Managers.TransportManager;
import main.java.classes.ListRoute;
import main.java.classes.Route;
import main.java.classes.Station;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GrafoGUI {

    private static JTable table;
    public JFrame frameGrafo;
    private JFrame anterior;
    private GrafoPanel grafoPanel = GrafoPanel.getInstance();
    private Boolean aux = false;
    public ListRoute listRoute;
    public TicketManager tm = TicketManager.getInstance();
    public RouteManager rm = RouteManager.getInstance();
    public TransportManager ttm = TransportManager.getInstance();
    ArrayList<ListRoute> bestRoute;
    public ArrayList<ListRoute> listPaths = new ArrayList<>();

    public GrafoGUI(ArrayList<ListRoute> bestRoute) {

        this.initialize(bestRoute);
        this.bestRoute =bestRoute;
        this.aux = true;

    }


    public GrafoGUI() {
        //    this.grafoPanel= grafoPanel;
        this.initialize(null);
    }


    private void initialize(ArrayList<ListRoute> bestRoute) {
        this.frameGrafo = new JFrame();
        this.frameGrafo.setBounds(100, 100, 1350, 720);
        this.frameGrafo.setResizable(false);
        this.frameGrafo.setLocationRelativeTo(null);



        JPanel panelView = new JPanel();
        frameGrafo.getContentPane().add(panelView);
        panelView.setLayout(new BorderLayout(0,0));

        panelView.add(new Panel(), BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panelView.add(panel, BorderLayout.WEST);

        GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWidths = new int[] { 185, 0};
        gbl_panel.rowHeights = new int[] { 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[] { 0.0, 1.7 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 , 0.0, 0.0 ,0.0, 1.0};

        panel.setLayout(gbl_panel);

        JLabel lblPanelDeAdministracin = new JLabel("Trayectos disponibles");
        lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
        gbc_lblPanelDeAdministracin.gridwidth = 4;
        gbc_lblPanelDeAdministracin.insets = new Insets(0, 1, 5, 1);
        gbc_lblPanelDeAdministracin.gridx = 0;
        gbc_lblPanelDeAdministracin.gridy = 0;
        panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);

        if(bestRoute != null){
                //lista de listroutes

             for(ListRoute lr : bestRoute) {


                 if (lr.getTotalCost() == null){
                    lr.setTotalCost(rm.costTotal(lr.listRoute));
                 }
                 if(lr.getTotalDuration() == null){
                     lr.setTotalDuration(rm.durationTotal(lr.listRoute));
                 }
                 if(lr.getTotalDistance() == null){
                     lr.setTotalDistance(rm.distanceTotal(lr.listRoute));
                 }
                 ArrayList<String> usedTransports = new ArrayList<>();

                 for(Route r : lr.listRoute){

                     if(!usedTransports.contains(r.getTransport().getName())){
                         usedTransports.add(r.getTransport().getName());

                     }
                 }
                 lr.setTransports(usedTransports);


                }



            JPanel panel_2 = new JPanel();
            GridBagConstraints gbc_panel_2 = new GridBagConstraints();
            gbc_panel_2.insets = new Insets(0, 0, 2, 0);
            gbc_panel_2.fill = GridBagConstraints.BOTH;
            gbc_panel_2.gridx = 0;
            gbc_panel_2.gridy = 1;
            panel.add(panel_2, gbc_panel_2);


            JButton buyTicketbutton = new JButton("");
            buyTicketbutton.setBackground(Color.CYAN);
            buyTicketbutton.setForeground(Color.BLACK);
            buyTicketbutton.setFont(new Font ("Segoe UI", Font.PLAIN, 20));
            buyTicketbutton.setText(" Comprar boleto ");
            buyTicketbutton.setSize(200, 100);
            buyTicketbutton.setOpaque(true);
            buyTicketbutton.setContentAreaFilled(true);
            buyTicketbutton.setBorderPainted(true);
            buyTicketbutton.setBorder(new LineBorder(Color.BLACK));

            buyTicketbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            //buyTicketbutton.setVisible(false);
            JTextField userName = new JTextField();
            JTextField userEmail = new JTextField();


            buyTicketbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {

                    String name= userName.getText();
                    String email = userEmail.getText();


                    Integer rowId = table.getSelectedRow();

                    if (rowId < 0) {
                        JOptionPane.showMessageDialog(frameGrafo, "No hay ningún recorrido seleccionado", "Información",
                                JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }

                    ListRoute routes;
                    routes = bestRoute.get(table.convertRowIndexToModel(rowId));

                    RefreshBuyTicket(routes, name , email);
                }
            });

            //   JPanel panel_133 = new JPanel();
            GridBagConstraints gbc_panel_1 = new GridBagConstraints();
            gbc_panel_1.insets = new Insets(0, 0, 5, 0);
            gbc_panel_1.gridheight =3;
            gbc_panel_1.gridwidth =2;
            gbc_panel_1.fill = GridBagConstraints.SOUTHEAST;
            gbc_panel_1.gridx = 4;
            gbc_panel_1.gridy = 6;
            panel.add(buyTicketbutton, gbc_panel_1);

            userEmail.setToolTipText("Escriba su email");
            userEmail.setDisabledTextColor(Color.LIGHT_GRAY);
            GridBagConstraints gbc_textField_1 = new GridBagConstraints();
            gbc_textField_1.gridheight =1;
            gbc_textField_1.gridwidth =2;
            gbc_textField_1.insets = new Insets(0, 0, 7, 5);
            gbc_textField_1.fill = GridBagConstraints.BOTH;
            gbc_textField_1.gridx = 4;
            gbc_textField_1.gridy = 6;
            userEmail.setColumns(4);
            //userName.addActionListener(buyTicketbutton);

            panel.add(userEmail, gbc_textField_1);

            JLabel lblPanelName = new JLabel("Nombre del comprador:");
            lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            GridBagConstraints GBCName = new GridBagConstraints();
            GBCName.gridwidth = 4;
            GBCName.insets = new Insets(1, 1, 0, 1);
            GBCName.gridx = 4;
            GBCName.gridy = 3;
            panel.add(lblPanelName, GBCName);


            userName.setToolTipText("Escriba su nombre");
            GridBagConstraints camponame = new GridBagConstraints();
            camponame.gridheight =1;
            camponame.gridwidth =2;
            camponame.insets = new Insets(3, 0, 8, 6);
            camponame.fill = GridBagConstraints.BOTH;
            camponame.gridx = 4;
            camponame.gridy = 4;
            userName.setColumns(1);
            //userEmail.addActionListener(buyTicketbutton);

            panel.add(userName, camponame);

            JLabel lblPanelEmail = new JLabel("Email del comprador:");
            lblPanelEmail.setFont(new Font("Segoe UI", Font.PLAIN, 20));
            GridBagConstraints GBCEmail = new GridBagConstraints();
            GBCEmail.gridwidth = 4;
            GBCEmail.insets = new Insets(1, 1, 0, 1);
            GBCEmail.gridx = 4;
            GBCEmail.gridy = 5;
            panel.add(lblPanelEmail, GBCEmail);

            //panel derecha
            table = new JTable();
            JScrollPane panelscroll = new JScrollPane(table);
            GridBagConstraints tablepanel = new GridBagConstraints();
            tablepanel.gridwidth = 3;
            tablepanel.gridheight =2;
            tablepanel.insets = new Insets(0, 2, 0, 0);
            tablepanel.fill = GridBagConstraints.BOTH;
            tablepanel.gridx =3;
            tablepanel.gridy =1;
            table.setFont(new Font("Roboto Light", Font.CENTER_BASELINE, 14));
            table.setVisible(true);
            panel.add(panelscroll, tablepanel);

            //table.setVisible(true);
            refreshRutaTable(bestRoute);

        }
        // Panel para el grafo

        GridBagConstraints gbcGrafo = new GridBagConstraints();
        gbcGrafo.gridwidth = 3;
        gbcGrafo.gridheight = 6;
        gbcGrafo.insets = new Insets(0, 0, 0, 1);
        gbcGrafo.fill = GridBagConstraints.BOTH;
        gbcGrafo.gridx = 0;
        gbcGrafo.gridy = 2;

        grafoPanel.setBackground(new Color(0x242424));
        grafoPanel.setBorder(BorderFactory.createLineBorder(new Color(0x7A8A99)));
        panel.add(grafoPanel, gbcGrafo);


        this.frameGrafo.setContentPane(panel);


    }

    private void RefreshBuyTicket(ListRoute routes, String name, String email) {


        ticketGUI graf = new ticketGUI(routes, name, email);
        graf.frameTicket.setVisible(true);
        graf.setAnterior(GrafoGUI.this.frameGrafo);


    }

    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


    public void refreshRutaTable(ArrayList<ListRoute> listPaths) {
        String row[] = { "Origen", "Pasa por", "Destino", "Distancia", "Duración", "Costo" , "Lineas"};
        DefaultTableModel tableModel = new DefaultTableModel(row, 0){


            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        for (ListRoute ruta : listPaths) {
            String origen = ruta.getOrigin().getName();
            String destino =ruta.getDestination().getName();
            String distancia = ruta.getTotalDistance() + " km";
            String duracion = ruta.getTotalDuration() + " '";
            String costo = ruta.getTotalCost() + " $";
           // ArrayList<Station> stations = ruta.listStation;
            StringBuilder s = new StringBuilder("");
            for ( Station ss : ruta.listStation){
                s.append(ss.getName());

            }
            StringBuilder t = new StringBuilder("");
            for ( String tt : ruta.getTransports()){
                t.append(tt);

            }

            Object[] data = { origen, s, destino, distancia, duracion, costo, t };
            tableModel.addRow(data);
        }

        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(40);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);

    }
}