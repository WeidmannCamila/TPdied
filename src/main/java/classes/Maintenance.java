package main.java.classes;

public class Maintenance {

    private Integer idMaintenance;
    private String description;

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
}
