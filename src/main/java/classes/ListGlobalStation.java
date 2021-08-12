package main.java.classes;

import main.java.DTOs.DTOStation;
import main.java.Managers.StationManager;

import java.util.HashMap;

public class ListGlobalStation implements Comparable<ListGlobalStation>{



    private HashMap<Integer, Station> listss;
    private static ListGlobalStation listS;


    public ListGlobalStation(HashMap<Integer, Station> listss) {
        this.listss = listss;
    }


    public static ListGlobalStation getInstance() {
        StationManager sm = StationManager.getInstance();
        if (listS==null) {

            listS = new ListGlobalStation(sm.getListStations());
        }
        return listS;
    }

    public HashMap<Integer, Station> getList(){

        return listss;

    }
    public void deleteStation(DTOStation deleteS) {
        this.listss.remove(deleteS.getIdStation());

    }

    public void addStation(Station s) {

        this.listss.put(s.getIdStation(), s);
    }

    public void editStation(Station s) {
        for(Station s1: this.listss.values()){
            if(s1.getIdStation().equals(s.getIdStation())){
                s1.setName(s.getName());
                s1.setStatus(s.getStatus());
                s1.setOpeningTime(s.getOpeningTime());
                s1.setClosingTime(s.getOpeningTime());

            }

        }


    }

    @Override
    public int compareTo(ListGlobalStation o) {
        return 0;
    }



}
