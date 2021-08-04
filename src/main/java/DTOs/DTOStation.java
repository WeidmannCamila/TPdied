package main.java.DTOs;

public class DTOStation {

    private Integer idStation;
    private String name;
    private String open; // VER TIPO
    private String clouse;
    private String status;


    public DTOStation(Integer idStation, String name, String status, String open, String clouse ) {
        this.idStation = idStation;
        this.name = name;
        this.open = open;
        this.clouse = clouse;
        this.status = status;
    }

    public DTOStation() {

    }

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

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClouse() {
        return clouse;
    }

    public void setClouse(String clouse) {
        this.clouse = clouse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
