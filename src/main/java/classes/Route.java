package main.java.classes;

import java.sql.Time;
import java.util.List;

public class Route {
    private Integer idRoute;
    private Station origin;
    private Station destination;
    private long distance;
    private Time duration ;
    private Integer maxPassagers;
    private boolean status;
    private Integer cost;
    private List<Station> listStation;



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

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
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
}
