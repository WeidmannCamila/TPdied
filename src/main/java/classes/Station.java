package main.java.classes;

import main.java.Enumeration.EnumStatus;

import java.util.List;

public class Station {

    private Integer idStation;
    private String name;
    private String openingTime;
    private String closingTime;
    private EnumStatus status;
    private List<Maintenance> maintenanceHistory;

    //constructor

    public Station(Integer idStation, String name, String openingTime, String closingTime, EnumStatus status, List<Maintenance> maintenanceHistory) {
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

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }

    public List<Maintenance> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    public void setMaintenanceHistory(List<Maintenance> maintenanceHistory) {
        this.maintenanceHistory = maintenanceHistory;
    }


}
