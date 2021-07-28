package main.java.classes;

import main.java.Managers.StationManager;

import java.util.ArrayList;

public class ListGlobalStation implements Comparable<ListGlobalStation>{



    private ArrayList<Station> listss;
    private static ListGlobalStation listS;


    public ListGlobalStation(ArrayList<Station> listss) {
        this.listss = listss;
    }


    public static ListGlobalStation getInstance() {
        if (listS==null) {
            StationManager sm = new StationManager();
         //   listS = new ListGlobalStation(sm.searchStation1());
        }
        return listS;
    }

    public ArrayList<Station> getList(){

        return listss;

    }

    @Override
    public int compareTo(ListGlobalStation o) {
        return 0;
    }
}
