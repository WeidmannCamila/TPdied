package main.java.classes;

import java.util.ArrayList;

public class ListRoute {
    private Station origin;
    private Station destination;
    private Double totalDistance;
    private Double totalDuration ;
    private Double totalCost;
    public ArrayList<Station> listStation;
    public ArrayList<Route> listRoute;
    private Integer maxPassagers;
    private ArrayList<String> transports;

    public ListRoute(Station origin, Station destination, Double totalDistance, Double totalDuration, Double totalCost, ArrayList<Station> listStation, ArrayList<String> transports) {
        this.origin = origin;
        this.destination = destination;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
        this.totalCost = totalCost;
        this.listStation = listStation;
        this.transports = transports;

    }

    public ListRoute(Station origin, Station destination, Double totalDistance, Double totalDuration, Integer maxPassagers, Double totalCost, ArrayList<Station> listStation) {
        this.origin = origin;
        this.destination = destination;
        this.totalDistance = totalDistance;
        this.totalDuration = totalDuration;
        this.maxPassagers = maxPassagers;
        this.totalCost = totalCost;
        this.listStation = listStation;
    }

    public ListRoute() {

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

    public Double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Integer getMaxPassagers() {
        return maxPassagers;
    }

    public void setMaxPassagers(Integer maxPassagers) {
        this.maxPassagers = maxPassagers;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }


    public ArrayList<Station> getListStation() {
        return listStation;
    }

    public void setListStation(ArrayList<Station> listStation) {
        this.listStation = listStation;
    }

    public ArrayList<String> getTransports() {
        return transports;
    }

    public void setTransports(ArrayList<String> transports) {
        this.transports = transports;
    }

    public ArrayList<Route> getListRoute() {
        return listRoute;
    }

    public void setListRoute(ArrayList<Route> listRoute) {
        this.listRoute = listRoute;
    }
}
