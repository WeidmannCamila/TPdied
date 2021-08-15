package main.java.Vista;

import main.java.Managers.TicketManager;
import main.java.classes.ListRoute;
import main.java.classes.Station;
import main.java.classes.TransportRoute;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class ticketGUI {
    private JPanel panelColour;
    private JTextField date;
    private JTextField idText;

    private JLabel email;
    private JLabel destino;
    private JLabel distancia;
    private JPanel jpanel1;
    private JLabel costo;
    private JLabel duracion;
    private JSeparator separador;
    private JTextField transports;
    private JLabel namelabel;
    private JLabel name;
    public TicketManager tm = TicketManager.getInstance();
    public JFrame frameTicket;
    private JFrame anterior;

    public ticketGUI(ListRoute routes, String name, String email) {
        this.initialize(routes, name, email);
    }

    private void initialize(ListRoute routes, String name, String email) {

        this.frameTicket = new JFrame();

        this.frameTicket.setContentPane(jpanel1);
        this.frameTicket.setBounds(100, 100, 450, 250);
        this.frameTicket.setResizable(false);

        ZoneId z = ZoneId.of("America/Argentina/Buenos_Aires");
        ZonedDateTime zdt = ZonedDateTime.now(z);

        int id = tm.createTicket(routes, name, email, zdt.toLocalDate());

        this.idText.setText(String.valueOf(id));
        this.name.setText(name);
        this.name.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.name.setText(name);

        this.date.setText(zdt.toLocalDate().toString());
        this.date.setBorder(new EmptyBorder(0, 0, 0, 0));

        this.destino.setText(routes.getDestination().getName());
        this.destino.setBorder(new EmptyBorder(0, 0, 0, 0));

        this.distancia.setText(routes.getTotalDistance().toString());
        this.distancia.setBorder(new EmptyBorder(0, 0, 0, 0));

        this.costo.setText(routes.getTotalCost().toString());

        this.costo.setBorder(new EmptyBorder(0, 0, 0, 0));

        this.email.setText(email);
        this.email.setBorder(new EmptyBorder(0, 0, 0, 0));

        this.duracion.setText(routes.getTotalDuration().toString());
        this.duracion.setBorder(new EmptyBorder(0, 0, 0, 0));

        StringBuilder t = new StringBuilder("");
        for ( String tt : routes.getTransports()){
            t.append(tt);

        }
        this.transports.setText(t.toString());
        this.transports.setBorder(new EmptyBorder(0, 0, 0, 0));



    }


    public void setAnterior(JFrame a) {
        this.anterior = a;
    }


}
