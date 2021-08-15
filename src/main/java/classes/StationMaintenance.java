package main.java.classes;

import java.sql.Timestamp;

public class StationMaintenance {
    private Integer idStationMaintenance;
    private String nameStationMaintenance;
    private Timestamp lastDateMaintenance;

    public StationMaintenance(){    }

    public Integer getIdStationMaintenance() {
        return idStationMaintenance;
    }

    public void setIdStationMaintenance(Integer idStationMaintenance) {
        this.idStationMaintenance = idStationMaintenance;
    }

    public String getNameStationMaintenance() {
        return nameStationMaintenance;
    }

    public void setNameStationMaintenance(String nameStationMaintenance) {
        this.nameStationMaintenance = nameStationMaintenance;
    }

    public Timestamp getLastDateMaintenance() {
        return lastDateMaintenance;
    }

    public void setLastDateMaintenance(Timestamp lastDateMaintenance) {
        this.lastDateMaintenance = lastDateMaintenance;
    }
}
