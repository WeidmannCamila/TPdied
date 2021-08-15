package main.java.DTOs;

import java.awt.*;

public class DTOStation {

    private Integer idStation;
    private String name;
    private String open; // VER TIPO
    private String closed;
    private String status;
    private Paint colour;


    public DTOStation(Integer idStation, String name, String status, String open, String closed ) {
        this.idStation = idStation;
        this.name = name;
        this.open = open;
        this.closed = closed;
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

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Paint getColour() {
        return colour;
    }

    public void setColour(Paint colour) {
        this.colour = colour;
    }
}
