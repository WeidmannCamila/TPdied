package main.java.classes;



import java.sql.Time;
import java.util.List;

public class Route {
    private Integer idRoute;
    private Station origin;
    private Station destination;
    private Double distance;
    private Integer duration ;
    private Integer maxPassagers;
    private boolean status;
    private Integer cost;
    private List<Station> listStation;
    private TransportRoute transport;


    //constructor

    public Route(Integer idRoute, Station origin, Station destination, Double distance, Integer duration, Integer maxPassagers, boolean status, Integer cost, List<Station> listStation, TransportRoute transport) {
        this.idRoute = idRoute;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.duration = duration;
        this.maxPassagers = maxPassagers;
        this.status = status;
        this.cost = cost;
        this.listStation = listStation;
        this.transport = transport;

    }

    public Route(Station start, Station end) {
    }

    public Route() {

    }


    //getters and setters

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public List<Station> getListStation() {
        return listStation;
    }

    public void setListStation(List<Station> listStation) {
        this.listStation = listStation;
    }

    public TransportRoute getTransport() {
        return transport;
    }

    public void setTransport(TransportRoute transport) {
        this.transport = transport;
    }

}
