package main.java.classes;

import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOTransport;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;

import java.util.ArrayList;
import java.util.HashMap;

public class ListGlobalRoute implements Comparable<ListGlobalRoute> {


    private ArrayList<Route> listsR;
    private static ListGlobalRoute listS;


    public ListGlobalRoute(ArrayList<Route> listsR) {
        this.listsR= listsR;
    }


    public static ListGlobalRoute getInstance() {
        if (listS == null) {
            RouteManager rm = new RouteManager();
            listS = new ListGlobalRoute(rm.getListRoutesFromDao());
        }
        return listS;
    }

    public ArrayList<Route> getList() {

        return listsR;

    }


    public void deleteRoute(Route deleteT) {
        this.listsR.removeIf(t -> t.getIdRoute().equals(deleteT.getIdRoute()));

    }

    public void addRoute(Route t){
        this.listsR.add(t);
    }

    public void editRoute(Boolean status, Route r) {

        for(Route r1 : this.listsR){
            if(r1.getIdRoute().equals(r.getIdRoute())){

                r1.setStatus(status);

            }
        }

    }

    @Override
    public int compareTo(ListGlobalRoute o) {
        return 0;
    }


}