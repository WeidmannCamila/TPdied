package main.java.Managers;

import main.java.DAO.MaintenanceDAO;
import main.java.DAO.StationDAO;
import main.java.DTOs.DTOMaintenance;
import main.java.DTOs.DTOStation;
import main.java.classes.ListGlobalStation;
import main.java.classes.Maintenance;
import main.java.classes.Station;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationManager {

    private static final StationManager INSTANCE = new StationManager();
    private StationDAO sDAO = new StationDAO();
    private HashMap<Integer, Station> listStation= new HashMap<Integer, Station>();
    public StationManager(){}
    public static StationManager getInstance() {return INSTANCE;    }



    public HashMap<Integer, Station> getListStations() {

         this.listStation= sDAO.getStationsV();


        System.out.println("LISTA DE ESTACIONES DE LA BDD" + listStation.get(1) + listStation.get(2));
        return listStation;
    }



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


    public static ArrayList<DTOMaintenance> searchMaintenance(int estacionParametro) {
        ArrayList<DTOMaintenance> mantenimientos = MaintenanceDAO.getMaintenanceById(estacionParametro);
        return mantenimientos;
    }

    public ArrayList<Station> searchStation1(){
        System.out.println("entro a seach station");
        Station st = new Station();

        ListGlobalStation lgs = ListGlobalStation.getInstance();
        ArrayList<Station> l = StationDAO.getStations();


        return l;
    }

    public void addStation(DTOStation dto){
        sDAO.addStation(dto);

        Station ss= new Station(dto.getIdStation(), dto.getName(), dto.getOpen(), dto.getClouse(), dto.getStatus());

        listStation.put(ss.getIdStation(), ss);

    }

    //buscar estacion por string
    public Station getStation(String s) {
        System.out.println("nombre string estacion" + s);
        Station station = new Station();
        station =sDAO.getStationString(s);

        return station;
    }
}
