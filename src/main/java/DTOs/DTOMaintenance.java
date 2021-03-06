package main.java.DTOs;

import java.sql.Timestamp;

public class DTOMaintenance {
    private Integer idMaintenance;
    private String description;
    private Timestamp startDate;
    private Timestamp endDate;


    public DTOMaintenance(Integer idMaintenance, String description, Timestamp startDate, Timestamp endDate) {
        this.idMaintenance = idMaintenance;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DTOMaintenance(Integer idMaintenance, String description){
        this.idMaintenance= idMaintenance;
        this.description=description;
    }

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
