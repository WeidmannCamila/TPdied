package main.java.classes;

import java.sql.Timestamp;

public class Maintenance {

    private Integer idMaintenance;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;

    //constructor

    public Maintenance(Integer idMaintenance, String description) {
        this.idMaintenance = idMaintenance;
        this.description = description;
    }

    //getters and setters
    public Integer getIdMaintenance() {
        return idMaintenance;
    }

    public void setIdMaintenance(Integer idMaintenance) {
        this.idMaintenance = idMaintenance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
