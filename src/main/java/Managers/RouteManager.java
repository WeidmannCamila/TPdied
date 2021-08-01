package main.java.Managers;

import main.java.DAO.RouteDAO;
import main.java.DAO.StationDAO;
import main.java.DTOs.DTORoute;
import main.java.Vista.GrafoPanel;
import main.java.classes.ListGlobalRoute;
import main.java.classes.ListGlobalStation;
import main.java.classes.Route;
import main.java.classes.Station;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RouteManager {

    /* ruta tiene origen y destino. se usa una lista de rutas.
     * una funcion path q va a ser una lista de listas de estaciones que representa cada camino con origen y destino
     * lista de marcados, con una funcion auxiliar que va a tomar la estacion origen y guardando
     * las estaciones adyacentes , si encuentra el destino guarda esa planta pero sigue buscando las siguientes adyacentes
     * la funcion para buscar los adyacentes va a agarrar la lista de rutas y agrega las q tengan origen y destino
     * */
    private static final RouteManager INSTANCE = new RouteManager();
    private ArrayList<Route> listRoutes = new ArrayList<Route>();

    private GrafoPanel grafoPanel = GrafoPanel.getInstance();
    private RouteDAO rDAO = new RouteDAO();

    public RouteManager(){}

    public static RouteManager getInstance() {
            return INSTANCE;
    }



    public ArrayList<Route> getListRoutesFromDao() {
        this.listRoutes = rDAO.getRoutes();
        System.out.println("lista de RUTAS TRANSPORTE...");
        System.out.println(listRoutes.get(1).getTransport());
        System.out.println(listRoutes.get(1).getTransport().getColour());
        return listRoutes;
    }

    public ArrayList<Route> getListRoutes(){
        ListGlobalRoute rl = ListGlobalRoute.getInstance();


        ArrayList<Route> l = rl.getList();

        System.out.println("get list rout CON LOCAL" + l.size());


        return l;
    }

    public Route getRoute(Station start, Station end) {
        for (Route r : this.getListRoutes()) {
            if (r.getOrigin() == start && r.getDestination() == end) {
                return r;
            }
        }
        return null;
    }

    public Route createRoute(DTORoute dto) {
        //se agrega ruta a la bdd
        rDAO.addRoute(dto);
        //se lo crea para agregar a la lista de rutas
        Route r = new Route(dto.getOrigin(), dto.getDestination());


        listRoutes.add(r);
        return r;

    }

    /* recibe nodos inicio y final y la forma por lo que lo quiere buscar


     */
    public ArrayList bestRoute4crit(Station start, Station end, String crit) {
        RouteManager rm = RouteManager.getInstance();

        // [[inicio, estaciones, fin],[inicio, estaciones, fin],[inicio, estaciones, fin]]
        ArrayList<ArrayList<Station>> listpaths = this.paths(start, end);

        ArrayList resultado = new ArrayList();
         switch(crit){

            case "MAS_BARATO": {
                resultado =cheaper(listpaths);
                break;

            }
            case "MAS_RAPIDO" : {
                resultado= faster(listpaths);
                break;
            }
            case "MENOR_DISTANCIA": {

                resultado =shortest(listpaths);
                break;
            }
             case "TODOS": {

                 resultado= listpaths;

             }


        }
        System.out.println("SALIO DEL DEFAULT, NO HACE CASO AL RETURN");

        grafoPanel.paintRoutes(resultado);
        grafoPanel.repaint();

        return resultado;
    }


   // por cada lista de estaciones, tengo q obtener la ruta de ellas y calcular por atributo

    private ArrayList<Station> shortest(ArrayList<ArrayList<Station>> listpathss) {
       // System.out.println("entra a shortest");
        Double distance = Double.MAX_VALUE;
        ArrayList<Station> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpathss) {

            RouteDAO rdao= new RouteDAO();
            Route ro = new Route();
            Double distanceAux = 0.0;

            //recorro la lista, y averiguo las rutas q hay entre cada estacion
            for (int i =0; i< cs.size()-1 ; i++) {

                ro = rdao.searchRoute(cs.get(i), cs.get(i+1));

                distanceAux += ro.getDistance();

            }

            minim.add(distanceAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        shortroute = listpathss.get(i);

        return shortroute;


    }

    //para bucar por mas rapido
    private ArrayList<Station> faster(ArrayList<ArrayList<Station>> listpaths) {
       // System.out.println("entra a faster");
        Double duration = Double.MAX_VALUE;
        ArrayList<Station> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpaths) {

            RouteDAO rdao= new RouteDAO();
            Route ro = new Route();
            Double durationAux = 0.0;
            //recorro la lista, y averiguo las rutas q hay entre cada estacion

            for (int i =0; i< cs.size() ; i++) {
                ro = rdao.searchRoute(cs.get(i), cs.get(i+1));
                durationAux += ro.getDistance();
            }

            minim.add(durationAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        shortroute = listpaths.get(i);

        return shortroute;
    }

    private ArrayList<Station> cheaper(ArrayList<ArrayList<Station>> listpaths) {
       // System.out.println("entra a cheaper");
        Double duration = Double.MAX_VALUE;
        ArrayList<Station> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpaths) {

            RouteDAO rdao= new RouteDAO();
            Route ro = new Route();
            Double costeAux = 0.0;
            //recorro la lista, y averiguo las rutas q hay entre cada estacion

            for (int i =0; i< cs.size() ; i++) {
                ro = rdao.searchRoute(cs.get(i), cs.get(i+1));
                costeAux += ro.getDistance();
            }

            minim.add(costeAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        shortroute = listpaths.get(i);

        return shortroute;
    }


    /*
        Busca todos los caminos que existen desde inicio a fin. usando una lista que tiene uns lista de rutas
        usa la lsita de marcados para ir agregando y formando cada uno de los treyectos
        no es lo mismo q el start y el end de route
     */
    public ArrayList<ArrayList<Station>> paths(Station start, Station end) {

      //  System.out.println("start y en de paths " + start.getIdStation() + " " + end.getIdStation());
        ArrayList<ArrayList<Station>> listpaths = new ArrayList<ArrayList<Station>>();
        ArrayList markedStations = new ArrayList();

        markedStations.add(start);

        searchPaths(start, end, markedStations, listpaths);

        return listpaths;

    }

    /*
        tina el nodo inical y va buscando sus adyacentes, agregandolo hasta encontrar el nodo final
        si encuentra agrega esa lista a la lista de caminos y continua con su busqueda.
     */
    private void searchPaths(Station start, Station end, ArrayList markedStations, ArrayList<ArrayList<Station>> listpaths) {
       // System.out.println("searchPaths start:" + start.getIdStation() + " " + end.getIdStation() + " marked :" + markedStations.size() );
        ArrayList<Station> adjacentStations = this.getAdjacentStations(start);
       // System.out.println("adyacentes:" + adjacentStations.size());
        ArrayList<Station> marked = null;

        for(Station s : adjacentStations){
            marked = (ArrayList<Station>) markedStations.stream().collect(Collectors.toList());

            if( end.getIdStation() == s.getIdStation()){
                marked.add(s);
                listpaths.add(new ArrayList<Station>(marked));
                System.out.println("ENCONTRO CAMINO " + listpaths.toString());

            } else {
                if(!marked.contains(s)){
                    marked.add(s);
                    this.searchPaths(s, end, marked, listpaths);
                }

            }

        }


    }

    private ArrayList<Station> getAdjacentStations(Station start) {
        ArrayList<Station> adjacents = new ArrayList<>();

    // System.out.println("estamos en adyacentes " + this.getListRoutes().size());
      //  System.out.println("start:" + start.getIdStation());
        for(Route r: this.getListRoutes() ){


            if(start.getIdStation() == r.getOrigin().getIdStation()){
              //  System.out.println("agrego el " + r.getDestination().getIdStation());
                adjacents.add(r.getDestination());
            }
        }

        return adjacents;
    }
}
