package main.java.classes;

import main.java.Managers.StationManager;

import java.util.HashMap;

public class ListGlobalStation implements Comparable<ListGlobalStation>{



    private HashMap<Integer, Station> listss;
    private static ListGlobalStation listS;


    public ListGlobalStation(HashMap<Integer, Station> listss) {
        this.listss = listss;
    }


    public static ListGlobalStation getInstance() {
        if (listS==null) {
            StationManager sm = new StationManager();
            listS = new ListGlobalStation(sm.getListStations());
        }
        return listS;
    }

    public HashMap<Integer, Station> getList(){

        return listss;

    }

    @Override
    public int compareTo(ListGlobalStation o) {
        return 0;
    }
}
