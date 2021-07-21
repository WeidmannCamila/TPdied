package main.java.classes;

import java.sql.Timestamp;
import java.util.Date;

public class Ticket {
    private Integer idTicket;
    private Timestamp dateTicket;
    private String nameOriginStation;
    private String nameDestionationStation;
    private Route route;
    private float cost;

    //constructor

    public Ticket(Integer idTicket, Timestamp dateTicket, String nameOriginStation, String nameDestionationStation, Route route, float cost) {
        this.idTicket = idTicket;
        this.dateTicket =  dateTicket;
        this.nameOriginStation = nameOriginStation;
        this.nameDestionationStation = nameDestionationStation;
        this.route = route;
        this.cost = cost;
    }


    //getters and setters

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Date getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket (Timestamp dateTicket) {
        this.dateTicket =  dateTicket;
    }

    public String getNameOriginStation() {
        return nameOriginStation;
    }

    public void setNameOriginStation(String nameOriginStation) {
        this.nameOriginStation = nameOriginStation;
    }

    public String getNameDestionationStation() {
        return nameDestionationStation;
    }

    public void setNameDestionationStation(String nameDestionationStation) {
        this.nameDestionationStation = nameDestionationStation;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
