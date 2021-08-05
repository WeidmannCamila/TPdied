package main.java.Managers;

import main.java.DAO.MaintenanceDAO;
import main.java.DAO.RouteDAO;
import main.java.DAO.StationDAO;
import main.java.DTOs.DTOMaintenance;
import main.java.DTOs.DTOStation;
import main.java.classes.ListGlobalStation;
import main.java.classes.Maintenance;
import main.java.classes.Station;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationManager {

    private RouteManager rm = new RouteManager();
    private RouteDAO rDAO = new RouteDAO();
    private static final StationManager INSTANCE = new StationManager();
    private StationDAO sDAO = new StationDAO();
    private HashMap<Integer, Station> listStation = new HashMap<Integer, Station>();

    public StationManager() {
    }

    public static StationManager getInstance() {
        return INSTANCE;
    }


    //funciona para la lista global
    public HashMap<Integer, Station> getListStations() {

        this.listStation = sDAO.getStationsV();

        return listStation;
    }


    public ArrayList<DTOStation> searchStation(DTOStation s) {
        System.out.println("entro a seacrh 4 name");
        ArrayList<DTOStation> listas = sDAO.searchStationWithAtribute(s);
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

    public static ArrayList<DTOStation> search4hours(DTOStation s) {
        ArrayList<DTOStation> listas = StationDAO.searchStationWithAtribute(s);
        return listas;
    }

    public static void deleteStationObject(DTOStation s) {
        StationDAO.deleteStation(s);
    }


    public static ArrayList<DTOMaintenance> searchMaintenance(int estacionParametro) {
        ArrayList<DTOMaintenance> mantenimientos = MaintenanceDAO.getMaintenanceById(estacionParametro);
        return mantenimientos;
    }

    public ArrayList<Station> searchStation1() {
        System.out.println("entro a seach station");
        Station st = new Station();

        ListGlobalStation lgs = ListGlobalStation.getInstance();
        ArrayList<Station> l = StationDAO.getStations();


        return l;
    }

    public void addStation(DTOStation dto) {
        sDAO.addStation(dto);

        Station ss = new Station(dto.getIdStation(), dto.getName(), dto.getOpen(), dto.getClouse(), dto.getStatus());

        listStation.put(ss.getIdStation(), ss);

    }
    public void deleteStation(DTOStation s ){
        sDAO.deleteStation(s);
    }

    //buscar estacion por nombre o id
    public Station getStation(String s) {
        for (Station s1 : this.getListStations().values()) {
            if (s1.getName().equals(s)) {
                return s1;
            }

        }
        return null;
    }

    public Station getStation(int s) {
        for (Station s1 : this.getListStations().values()) {
            if (s1.getIdStation().equals(s)) {
                return s1;
            }

        }
        return null;


    }

    public void updateStation(DTOStation dto) {
        sDAO.updateStation(dto);
    }
}