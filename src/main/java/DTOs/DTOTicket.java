package main.java.DTOs;

import main.java.classes.Route;

import java.sql.Timestamp;

public class DTOTicket {

    private Integer idTicket;


    private Timestamp dateTicket;
    private String nameOriginStation;
    private String nameDestionationStation;
    private Route route;
    private float cost;

    public DTOTicket(Integer idTicket, Timestamp dateTicket, String nameOriginStation, String nameDestionationStation, Route route, float cost) {
        this.idTicket = idTicket;
        this.dateTicket = dateTicket;
        this.nameOriginStation = nameOriginStation;
        this.nameDestionationStation = nameDestionationStation;
        this.route = route;
        this.cost = cost;
    }

    public DTOTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }
    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Timestamp getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(Timestamp dateTicket) {
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
