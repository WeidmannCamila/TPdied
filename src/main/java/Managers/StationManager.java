package main.java.Managers;

import main.java.DAO.StationDAO;
import main.java.DTOs.DTOStation;
import main.java.classes.ListGlobalStation;
import main.java.classes.Maintenance;
import main.java.classes.Station;


import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class StationManager {


    public static ArrayList<DTOStation> search(Integer idStation, String name, Time openingTime, Time closingTime, String status, List<Maintenance> maintenanceHistory) {
        DTOStation station = new DTOStation(idStation, name, openingTime,  closingTime,  status);


        ArrayList<DTOStation> listas = StationDAO.searchStation(station);

        return listas;
    }

    public static ArrayList<DTOStation> search4name(String param) {
        System.out.println("entro a seacrh 4 name");

        ArrayList<DTOStation> listas = StationDAO.searchStation(param);


        return listas;
    }

    public static ArrayList<DTOStation> search4id(String param) {
        ArrayList<DTOStation> listas = StationDAO.searchStation(param);
        return listas;
    }

    public static ArrayList<DTOStation> search4status(String toString) {
        ArrayList<DTOStation> listas = StationDAO.searchStation(param);
        return listas;
    }





    public ArrayList searchStation() {
        System.out.println("entro a seach station");
        DTOStation st = new DTOStation();

        ListGlobalStation lgs = ListGlobalStation.getInstance();
        ArrayList<DTOStation> l = StationDAO.getStations();


        return l;
    }







}
