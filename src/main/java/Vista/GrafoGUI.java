package main.java.Vista;

import main.java.DTOs.DTOTicket;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.Managers.TicketManager;
import main.java.classes.ListRoute;
import main.java.classes.Station;
import main.java.classes.Ticket;

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
    static TicketManager tm = TicketManager.getInstance();
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

        gbl_panel.columnWidths = new int[] { 185, 0};
        gbl_panel.rowHeights = new int[] { 0, 0, 0, 0};
        gbl_panel.columnWeights = new double[] { 0.0, 1.0 };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0 , 1.0, 1.0};

        panel.setLayout(gbl_panel);

        //panel arriba
        JLabel lblPanelDeAdministracin = new JLabel("Trayectos disponibles");
        lblPanelDeAdministracin.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        GridBagConstraints gbc_lblPanelDeAdministracin = new GridBagConstraints();
        gbc_lblPanelDeAdministracin.gridwidth = 4;
        gbc_lblPanelDeAdministracin.insets = new Insets(0, 1, 5, 1);
        gbc_lblPanelDeAdministracin.gridx = 0;
        gbc_lblPanelDeAdministracin.gridy = 0;
        panel.add(lblPanelDeAdministracin, gbc_lblPanelDeAdministracin);


     /*  JButton btnVolver_1 = new JButton("");
        btnVolver_1.setForeground(Color.WHITE);
        btnVolver_1.setText("COMPRAR");

        btnVolver_1.setOpaque(false);
        btnVolver_1.setContentAreaFilled(false);
        btnVolver_1.setBorderPainted(false);
        btnVolver_1.setBorder(null);
        btnVolver_1.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GridBagConstraints gbc_btnVolver_1 = new GridBagConstraints();
        gbc_btnVolver_1.anchor = GridBagConstraints.SOUTHEAST ;
        gbc_btnVolver_1.insets = new Insets(0, 0, 0, 0);
        gbc_btnVolver_1.gridx = 1;
        gbc_btnVolver_1.gridy = 0;
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
        gbc_panel_555522.fill = GridBagConstraints.EAST;
        gbc_panel_555522.gridx = 6;
        gbc_panel_555522.gridy = 1;
        panel.add(panel_555522, gbc_panel_555522);*/


        // Panelcito abajo del título para separar y que no quede tan pegado
        JPanel panel_2 = new JPanel();
        GridBagConstraints gbc_panel_2 = new GridBagConstraints();
        gbc_panel_2.insets = new Insets(0, 0, 2, 0);
        gbc_panel_2.fill = GridBagConstraints.BOTH;
        gbc_panel_2.gridx = 0;
        gbc_panel_2.gridy = 1;
        panel.add(panel_2, gbc_panel_2);


       /* // Panel a la izquierda para separar
        JPanel panel_5 = new JPanel();
        GridBagConstraints gbc_panel_5 = new GridBagConstraints();
        gbc_panel_5.insets = new Insets(0, 0, 5, 5);
        gbc_panel_5.fill = GridBagConstraints.BOTH;
        gbc_panel_5.gridx = 0;
        gbc_panel_5.gridy = 5;
        panel.add(panel_5, gbc_panel_5);
*/
        // Y aca a la derecha
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
        buyTicketbutton.setVisible(false);


        JTextField userName = new JTextField();
        JTextField userEmail = new JTextField("");


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



        userName.setToolTipText("Esciba su Nombre y Apellido");
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.gridheight =1;
        gbc_textField_1.gridwidth =2;
        gbc_textField_1.insets = new Insets(0, 0, 7, 5);
        gbc_textField_1.fill = GridBagConstraints.BOTH;
        gbc_textField_1.gridx = 4;
        gbc_textField_1.gridy = 4;
        userName.setColumns(4);
        //userName.addActionListener(buyTicketbutton);

        panel.add(userName, gbc_textField_1);

        userEmail.setToolTipText("Escriba su Email");
        GridBagConstraints camponame = new GridBagConstraints();
        camponame.gridheight =1;
        camponame.gridwidth =2;
        camponame.insets = new Insets(0, 0, 6, 5);
        camponame.fill = GridBagConstraints.BOTH;
        camponame.gridx = 4;
        camponame.gridy = 3;
        userEmail.setColumns(2);
        //userEmail.addActionListener(buyTicketbutton);

        panel.add(userEmail, camponame);







        //panel derecha
        table = new JTable();
        JScrollPane panel_scrlpn = new JScrollPane(table);
        GridBagConstraints gbc_panel_12 = new GridBagConstraints();
        gbc_panel_12.gridwidth = 3;
        gbc_panel_12.gridheight =2;
        gbc_panel_12.insets = new Insets(0, 2, 0, 0);
        gbc_panel_12.fill = GridBagConstraints.BOTH;
        gbc_panel_12.gridx =3;
        gbc_panel_12.gridy =1;

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
            refreshRutaTable(listPaths);
            buyTicketbutton.setVisible(true);

        }


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

        //TODO guardar fecha del ticket!!
        Date t = new Date();
      //  tm.createTicket(listRoute, name, email, t.setDate());

        JDialog jDialog =new JDialog(frameGrafo, "Mostrando la solución generada",
                Dialog.ModalityType.DOCUMENT_MODAL);
        JPanel contentPane;
        jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jDialog.setResizable(false);
        jDialog.setMinimumSize(new Dimension(200, 200));
        jDialog.setBounds(100, 100, 450, 475);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        jDialog.setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 200 };
        gbl_panel.rowHeights = new int[] { 10};
        gbl_panel.columnWeights = new double[] { 1.0 };
        gbl_panel.rowWeights = new double[] { 0.0 };
        panel.setLayout(gbl_panel);

        DefaultListModel<String> dataList = new DefaultListModel<String>();

        StringBuilder s = new StringBuilder("");
        for ( Station ss : routes.listStation){
            s.append(ss.getName());

        }

        dataList.addElement("FECHA DE COMPRA:" + Instant.now().toString());

        dataList.addElement("NOMBRE COMPRADOR:" + name + "  EMAIL: " + email);
        dataList.addElement("DESDE " + routes.getOrigin().getIdStation().toString() + "  HASTA " + routes.getDestination().getIdStation().toString());

        dataList.addElement("COSTO: " + routes.getTotalCost().toString());
        dataList.addElement("DISTANCIA " + routes.getTotalDistance());
        dataList.addElement("ESTACIONES RECORRIDAS: " + s);
        dataList.addElement("DURACION DEL RECORRIDO: " + routes.getTotalDuration());


        JList<String> insumoList = new JList<String>(dataList);
        GridBagConstraints gbc_InsumoList = new GridBagConstraints();
        gbc_InsumoList.anchor = GridBagConstraints.BOTH;
        gbc_InsumoList.insets = new Insets(0, 0, 0, 0);
        gbc_InsumoList.gridx = 0;
        gbc_InsumoList.gridy = 1;
        insumoList.setFixedCellWidth(200);
        panel.add(new JScrollPane(insumoList), gbc_InsumoList);

        jDialog.setVisible(true);

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

            StringBuilder s = new StringBuilder("");
            for ( Station ss : stations){
                s.append(ss.getName());

            }

            Object[] data = { origen, s.toString(), destino, distancia, duracion, costo };
            tableModel.addRow(data);
        }

        table.setModel(tableModel);
    }
}
