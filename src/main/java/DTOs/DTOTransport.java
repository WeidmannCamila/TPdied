package main.java.DTOs;

public class DTOTransport {



    private Integer idTransport;
    private String name;
    private String colour;
    private String status;

    public DTOTransport() {

    }

    public DTOTransport(Integer idTransport, String name, String colour, String status) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
