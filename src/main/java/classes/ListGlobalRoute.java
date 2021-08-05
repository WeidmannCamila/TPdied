package main.java.classes;

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



    @Override
    public int compareTo(ListGlobalRoute o) {
        return 0;
    }
}