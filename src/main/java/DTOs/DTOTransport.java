package main.java.DTOs;

import java.awt.*;

public class DTOTransport {



    private Integer idTransport;
    private String name;
    private String colour;
    private Boolean status;

    public DTOTransport() {

    }

    public DTOTransport(Integer idTransport, String name, String colour, Boolean status) {
        this.idTransport = idTransport;
        this.name = name;
        this.colour = colour;
        this.status = status;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
