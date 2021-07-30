package main.java.classes;

import java.awt.*;

public class TransportRoute {

    private Integer idTransport;
    private String name;
    private Paint colour;
    private boolean status;

    //constructor

    public TransportRoute(Integer idTransport, String name, Paint colour, boolean status) {
        this.idTransport = idTransport;
        this.name = name;
        this.colour = colour;
        this.status = status;
    }

    public TransportRoute() {

    }



    //getters and setters

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

    public Paint getColour() {
        return colour;
    }

    public void setColour(Paint colour) {
        this.colour = colour;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
