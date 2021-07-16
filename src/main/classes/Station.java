package main.classes;

import java.util.Date;
import java.util.List;

public class Station {

    private Integer idStation;
    private String name;
    private Date openingTime;
    private Date closingTime;
    private boolean status;
    private List<Maintenance> maintenanceHistory;

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
        this.openingTime = openingTime;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Maintenance> getMaintenanceHistory() {
        return maintenanceHistory;
    }

    public void setMaintenanceHistory(List<Maintenance> maintenanceHistory) {
        this.maintenanceHistory = maintenanceHistory;
    }
}
