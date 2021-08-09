package main.java.Managers;

import main.java.DAO.MaintenanceDAO;
import main.java.DAO.RouteDAO;
import main.java.DAO.StationDAO;
import main.java.DTOs.DTOMaintenance;
import main.java.DTOs.DTOStation;
import main.java.Vista.GrafoGUI;
import main.java.Vista.GrafoPanel;
import main.java.Vista.ViewVertex;
import main.java.classes.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StationManager {

    private RouteManager rm = new RouteManager();
    private RouteDAO rDAO = new RouteDAO();
    private static final StationManager INSTANCE = new StationManager();
    private StationDAO sDAO = new StationDAO();
    private HashMap<Integer, Station> listStation = new HashMap<Integer, Station>();
    private GrafoPanel grafopanel = GrafoPanel.getInstance();

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


    public void deleteStationObject(DTOStation s) {
        // busco las rutas que tengan esta estacion y poder eliminarlas tambien
        ArrayList<Route> aux = rm.getListRoutes();
        for(Route r : aux){
            if(r.getOrigin().getIdStation().equals(s.getIdStation()) || r.getDestination().getIdStation().equals(r.getIdRoute())){
                rm.deleteRoute(r);
                ListGlobalRoute lr= ListGlobalRoute.getInstance();
                lr.deleteRoute(r);
            }
        }

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

        ListGlobalStation ls = ListGlobalStation.getInstance();

        Station s = this.getStation(dto.getIdStation());

        s.setName(dto.getName());

        if(!dto.getStatus().equals(s.getStatus())){

            if(dto.getStatus().equals("MANTENIMIENTO")){
                rm.editRoute(false, s);

            }else { rm.editRoute(true, s);}


        }
        ls.editStation(s);


        sDAO.updateStation(dto);
    }
}