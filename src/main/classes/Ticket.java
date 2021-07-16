package main.classes;

import java.util.Date;

public class Ticket {
    private Integer idTicket;
    private Date dateTicket;
    private String nameOriginStation;
    private String nameDestionationStation;
    private Route route;
    private float cost;


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

    public void setDateTicket(Date dateTicket) {
        this.dateTicket = dateTicket;
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
