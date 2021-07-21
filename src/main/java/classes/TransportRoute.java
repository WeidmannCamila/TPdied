package main.java.classes;

public class TransportRoute {

    private Integer idTransport;
    private String name;
    private String colour;
    private boolean status;

    //constructor

    public TransportRoute(Integer idTransport, String name, String colour, boolean status) {
        this.idTransport = idTransport;
        this.name = name;
        this.colour = colour;
        this.status = status;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
