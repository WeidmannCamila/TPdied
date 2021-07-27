package main.java.Managers;

import main.java.DAO.RouteDAO;
import main.java.DAO.StationDAO;
import main.java.DTOs.DTORoute;
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

    private ArrayList<Route> listRoutes = new ArrayList<>();

    private static final RouteManager INSTANCE = new RouteManager();
    private RouteDAO rDAO = new RouteDAO();

    private static RouteManager getInstance() {
            return INSTANCE;
    }



    public ArrayList<Route> getListPath() {
        return this.listRoutes;
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

      /*  switch(crit){
            case "MAS_BARATO": {
                cheaper(listpaths);

            }
            case "MAS_RAPIDO" : {
                faster(listpaths);
            }
            case "MENOR_DISTANCIA": {

                shortest(listpaths);
            }


        }*/


        return listpaths;
    }


   // por cada lista de estaciones, tengo q obtener la ruta de ellas y calcular por atributo

    private ArrayList<Station> shortest(ArrayList<ArrayList<Station>> listpaths) {
        Double distance = Double.MAX_VALUE;
        ArrayList<Station> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpaths) {

            RouteDAO rdao= new RouteDAO();
            Route ro = new Route();
            Double distanceAux = 0.0;
            //recorro la lista, y averiguo las rutas q hay entre cada estacion

            for (int i =0; i< cs.size() ; i++) {
                ro = rdao.searchRoute(cs.get(i), cs.get(i+1));
                distanceAux += ro.getDistance();
            }

            minim.add(distanceAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        shortroute = listpaths.get(i);

        return shortroute;


    }

    private void faster(List<List<Station>> listpaths) {
    }

    private void cheaper(List<List<Station>> listpaths) {
    }


    /*
        Busca todos los caminos que existen desde inicio a fin. usando una lista que tiene uns lista de rutas
        usa la lsita de marcados para ir agregando y formando cada uno de los treyectos
        no es lo mismo q el start y el end de route
     */
    public ArrayList<ArrayList<Station>> paths(Station start, Station end) {
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
        ArrayList<Station> adjacentStations = this.getAdjacentStations(start);
        ArrayList<Station> marked = null;

        for(Station s : adjacentStations){
            marked = (ArrayList<Station>) markedStations.stream().collect(Collectors.toList());

            if( end != s){
                if(!marked.contains(s)){
                    marked.add(s);
                    this.searchPaths(s, end, marked, listpaths);
                }
            } else {
                marked.add(s);
                listpaths.add(new ArrayList<Station>(marked));

            }

        }


    }

    private ArrayList<Station> getAdjacentStations(Station start) {
        ArrayList<Station> adjacents = new ArrayList<>();
        for(Route r: this.getListPath() ){
            if(start == r.getOrigin()){
                adjacents.add(r.getDestination());
            }
        }

        return adjacents;
    }
}
