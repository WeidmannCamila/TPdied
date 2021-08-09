package main.java.Managers;

import main.java.DAO.StationDAO;
import main.java.DAO.TicketDAO;
import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTicket;
import main.java.classes.ListRoute;
import main.java.classes.Station;
import main.java.classes.Ticket;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;

public class TicketManager {


    private static final TicketManager INSTANCE = new TicketManager();
    private TicketDAO tDAO = new TicketDAO();


    public TicketManager(){}

    public static TicketManager getInstance() {return INSTANCE;    }


    public void createTicket(ListRoute routes, String name, String email, LocalDate date){
        tDAO.addTicket(routes, name, email, date);



    }



}
