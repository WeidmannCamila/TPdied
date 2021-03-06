package main.java.classes;

import java.awt.*;
import java.util.ArrayList;

public class Station {

    private Integer idStation;
    private String name;
    private String openingTime;
    private String closingTime;
    private String status;
    private ArrayList<Maintenance> maintenanceHistory;
    private Paint colour;

    //constructor

    public Station(Integer idStation, String name, String openingTime, String closingTime, String status, ArrayList<Maintenance> maintenanceHistory) {
        this.idStation = idStation;
        this.name = name;
        this.openingTime =  openingTime;
        this.closingTime = closingTime;
        this.status = status;
        this.maintenanceHistory = maintenanceHistory;
    }

    public Station() {

    }

    public Station(Integer idStation, String name, String open, String clouse, String status) {
    }

    public Station(String name, String status) {
        this.name = name;

        this.status = status;
    }

    public Station(Integer idStation, String name, String status) {
        this.idStation = idStation;
        this.name = name;

        this.status = status;
    }


    //getters and setters

    public Integer getIdStation() {
        return idStation;
    }

    public void setIdStation(Integer idStation) {
        this.idStation = idStation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime =  openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime =  closingTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Maintenance> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    public void setMaintenanceHistory(ArrayList<Maintenance> maintenanceHistory) {
        this.maintenanceHistory = maintenanceHistory;
    }
    public void addMaintenance(Maintenance m){
        this.maintenanceHistory.add(m);
    }

    public Paint getColour() {
        return colour;
    }

    public void setColour(Paint colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Station{" +
                "idStation=" + idStation +
                ", name='" + name + '\'' +
                ", colour=" + colour +
                '}';
    }
}
