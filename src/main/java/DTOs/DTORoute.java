package main.java.DTOs;

import com.sun.jdi.connect.Transport;
import main.java.classes.Station;
import main.java.classes.TransportRoute;

import java.util.List;

public class DTORoute {
    private Integer idRoute;
    private Station origin;

    private Station destination;
    private Double distance;
    private Double duration ;
    private Integer maxPassagers;
    private boolean status;
    private Double cost;
    private TransportRoute transport;

    /*
    * Indicar la lista de trayectos que puede completar.
      Para cada tramo entre 2 estaciones, se debe registrar:
    * */
  //  private List<Station> listStation;



    //constructor
    public DTORoute(){};

    public DTORoute(Station origin, Station destination, Double distance, Double duration, Integer maxPassagers, boolean status, Double cost, TransportRoute transport) {

        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.maxPassagers = maxPassagers;
        this.status = status;
        this.cost = cost;
        this.transport = transport;
    }


    public Integer getIdRoute() {
        return idRoute;
    }

    public void setIdRoute(Integer idRoute) {
        this.idRoute = idRoute;
    }

    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getMaxPassagers() {
        return maxPassagers;
    }

    public void setMaxPassagers(Integer maxPassagers) {
        this.maxPassagers = maxPassagers;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

   /* public List<Station> getListStation() {
        return listStation;
    }

    public void setListStation(List<Station> listStation) {
        this.listStation = listStation;
    }*/

    public TransportRoute getTransport() {
        return transport;
    }

    public void setTransport(TransportRoute transport) {
        this.transport = transport;
    }

}
