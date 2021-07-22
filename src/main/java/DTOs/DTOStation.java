package main.java.DTOs;

import main.java.Enumeration.EnumStatus;

import java.sql.Time;

public class DTOStation {

    private Integer idStation;
    private String name;
    private Time open; // VER TIPO
    private Time clouse;
    private EnumStatus status;


    public DTOStation(Integer idStation, String name, Time open, Time clouse, EnumStatus status) {
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

    public EnumStatus getStatus() {
        return status;
    }

    public void setStatus(EnumStatus status) {
        this.status = status;
    }
}
