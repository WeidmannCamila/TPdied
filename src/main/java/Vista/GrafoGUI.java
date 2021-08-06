package main.java.Vista;

import main.java.DTOs.DTOTicket;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.Managers.TicketManager;
import main.java.Managers.TransportManager;
import main.java.classes.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        this.frameGrafo.setBounds(100, 100, 1250, 720);
        this.frameGrafo.setResizable(false);

        JPanel panelView = new JPanel();
        frameGrafo.getContentPane().add(panelView);
        panelView.setLayout(new BorderLayout(0,0));

        panelView.add(new Panel(), BorderLayout.SOUTH);

        JPanel panel = new JPanel();
        panelView.add(panel, BorderLayout.WEST);

        GridBagLayout gbl_panel = new GridBagLayout();

        gbl_panel.columnWidths = new int[] { 185, 0};
        gbl_panel.rowHeights = new int[] { 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[] { 0.0, 1.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 , 1.0, 1.0};

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


            for(ArrayList<Station> s : bestRoute){
                Double distance = rm.distanceTotalRoute(s);
                Double duration = rm.durationTotalRoute(s);
                Double cost = rm.costTotalRoute(s);

                ArrayList<Station> aux = new ArrayList<>(s.subList(1, s.size()-1));
                ArrayList<String> usedTransports = new ArrayList<>();
                Route ro;
                for (int i =0; i< s.size()-1 ; i++) {

                    ro = rm.getRoute(s.get(i), s.get(i+1));
                    if(!usedTransports.contains(ro.getTransport().getName())){
                        usedTransports.add(ro.getTransport().getName());
                        System.out.println("LO CONTIENEE ");
                    }

                }

                listRoute = new ListRoute(s.get(0),s.get(s.size()-1), distance, duration, cost, aux, usedTransports);

                listPaths.add(listRoute);
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
                    routes = listPaths.get(table.convertRowIndexToModel(rowId));

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
            gbc_panel_1.gridy = 5;
            panel.add(buyTicketbutton, gbc_panel_1);
            userEmail.setToolTipText("Esciba su email");
            GridBagConstraints gbc_textField_1 = new GridBagConstraints();
            gbc_textField_1.gridheight =1;
            gbc_textField_1.gridwidth =2;
            gbc_textField_1.insets = new Insets(0, 0, 7, 5);
            gbc_textField_1.fill = GridBagConstraints.BOTH;
            gbc_textField_1.gridx = 4;
            gbc_textField_1.gridy = 4;
            userEmail.setColumns(4);
            //userName.addActionListener(buyTicketbutton);

            panel.add(userEmail, gbc_textField_1);

            userName.setToolTipText("Escriba su nombre");
            GridBagConstraints camponame = new GridBagConstraints();
            camponame.gridheight =1;
            camponame.gridwidth =2;
            camponame.insets = new Insets(0, 0, 6, 5);
            camponame.fill = GridBagConstraints.BOTH;
            camponame.gridx = 4;
            camponame.gridy = 3;
            userName.setColumns(1);
            //userEmail.addActionListener(buyTicketbutton);

            panel.add(userName, camponame);


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
            table.setVisible(true);
            panel.add(panelscroll, tablepanel);

            //table.setVisible(true);
            refreshRutaTable(listPaths);

        }
         // Panel para el grafo

        GridBagConstraints gbc_panel_91 = new GridBagConstraints();
        gbc_panel_91.gridwidth = 3;
        gbc_panel_91.gridheight = 4;
        gbc_panel_91.insets = new Insets(0, 0, 0, 1);
        gbc_panel_91.fill = GridBagConstraints.BOTH;
        gbc_panel_91.gridx = 0;
        gbc_panel_91.gridy = 2;

        grafoPanel.setBackground(new Color(0xcccccc));
        grafoPanel.setBorder(BorderFactory.createLineBorder(new Color(0x7A8A99)));
        panel.add(grafoPanel, gbc_panel_91);


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
        String row[] = { "Origen", "Pasa", "Destino", "Distancia", "Duración", "Costo" , "Lineas"};
        DefaultTableModel tableModel = new DefaultTableModel(row, 0){

           // private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int i, int i1) {
                return false;
            }
        };

        for (ListRoute ruta : listPaths) {
            String origen = ruta.getOrigin().getName();
            String destino = ruta.getDestination().getName();
            String distancia = ruta.getTotalDistance() + " km";
            String duracion = ruta.getTotalDuration() + " min";
            String costo = ruta.getTotalCost() + " $";
            ArrayList<Station> stations = ruta.listStation;

            StringBuilder s = new StringBuilder("");
            for ( Station ss : stations){
                s.append(ss.getName());

            }
            StringBuilder t = new StringBuilder("");
            for ( String tt : ruta.getTransports()){
                t.append(tt);

            }

            Object[] data = { origen, s.toString(), destino, distancia, duracion, costo, t };
            tableModel.addRow(data);
        }

        table.setModel(tableModel);
    }
}
