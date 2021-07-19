package main.java.DTOs;

import java.sql.Time;

public class DTOStation {

    private Integer idTransport;
    private String name;
    private Time open; // VER TIPO
    private Time clouse;
    private Boolean status;


    public DTOStation(Integer idTransport, String name, Time open, Time clouse, Boolean status) {
        this.idTransport = idTransport;
        this.name = name;
        this.open = open;
        this.clouse = clouse;
        this.status = status;
    }

    public DTOStation() {

    }

    public Integer getIdTransport() {
        return idTransport;
    }

    public void setIdTransport(Integer idTransport) {
        this.idTransport = idTransport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getOpen() {
        return open;
    }

    public void setOpen(Time open) {
        this.open = open;
    }

    public Time getClouse() {
        return clouse;
    }

    public void setClouse(Time clouse) {
        this.clouse = clouse;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
