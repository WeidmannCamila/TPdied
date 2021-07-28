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

    private static final StationManager INSTANCE = new StationManager();


    public static ArrayList<DTOStation> search(Integer idStation, String name, Time openingTime, Time closingTime, String status, List<Maintenance> maintenanceHistory) {
        DTOStation station = new DTOStation(idStation, name, openingTime,  closingTime,  status);

        ArrayList<DTOStation> listas = StationDAO.searchStation(station);

        return listas;
    }

    public static ArrayList<DTOStation> search4name(DTOStation s) {
        System.out.println("entro a seacrh 4 name");
        ArrayList<DTOStation> listas = StationDAO.searchStationWithAtribute(s);
        return listas;
    }

    public static ArrayList<DTOStation> search4id(DTOStation s) {
        ArrayList<DTOStation> listas = StationDAO.searchStationWithAtribute(s);
        return listas;
    }

    public static ArrayList<DTOStation> search4status(DTOStation s) {
        ArrayList<DTOStation> listas = StationDAO.searchStationWithAtribute(s);
        return listas;
    }

    public static void deleteStationObject (DTOStation s){
        StationDAO.deleteStation(s);
    }

    public static StationManager getInstance() {return INSTANCE;    }

    public ArrayList<DTOStation> searchStation1() {
        System.out.println("entro a seach station");
        DTOStation st = new DTOStation();

        ListGlobalStation lgs = ListGlobalStation.getInstance();
        ArrayList<DTOStation> l = StationDAO.getStations();


        return l;
    }







}
