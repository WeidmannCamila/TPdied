package main.java.classes;

import main.java.Enumeration.EnumStatus;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Station {

    private Integer idStation;
    private String name;
    private Timestamp openingTime;
    private Timestamp closingTime;
    private EnumStatus status;
    private List<Maintenance> maintenanceHistory;

    //constructor

    public Station(Integer idStation, String name, Date openingTime, Date closingTime, EnumStatus status, List<Maintenance> maintenanceHistory) {
        this.idStation = idStation;
        this.name = name;
        this.openingTime = (Timestamp) openingTime;
        this.closingTime = (Timestamp) closingTime;
        this.status = status;
        this.maintenanceHistory = maintenanceHistory;
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

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = (Timestamp) openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = (Timestamp) closingTime;
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
