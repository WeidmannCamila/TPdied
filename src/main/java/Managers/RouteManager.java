package main.java.Managers;

import main.java.DAO.RouteDAO;
import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOStation;
import main.java.Enumeration.EnumStatus;
import main.java.Vista.GrafoPanel;
import main.java.classes.ListGlobalRoute;
import main.java.classes.ListGlobalStation;
import main.java.classes.Route;
import main.java.classes.Station;

import java.time.Instant;
import java.util.ArrayList;
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
        System.out.println("EN list route DAOO" + listRoutes.size());
        return listRoutes;
    }



    public ArrayList<Route> getListRoutes(){
        ListGlobalRoute rl = ListGlobalRoute.getInstance();


        ArrayList<Route> l = rl.getList();

        return l;
    }


    public ArrayList<Route> getListRoutes(Station start, Station end){
        ArrayList<Route> aux = new ArrayList<>();

        for (Route r : this.getListRoutes()) {
         //   System.out.println("GET LIST ROUTES" + start.getIdStation() + " " + end.getIdStation() + " estadp " + r.getStatus());


            if(r.getStatus()){

                if (r.getOrigin().getIdStation().equals(start.getIdStation()) && r.getDestination().getIdStation().equals(end.getIdStation())) {
               //     System.out.println("ENTRA DENTRO DEL IF !RGETSTATUS " + r.getStatus() + " " + r.getIdRoute());
                    aux.add(r);

                }}

        }
        return aux;
    }


    public Route getRoute(Station start, Station end) {
        for (Route r : this.getListRoutes()) {

            if(r.getStatus()){

            if (r.getOrigin().getIdStation().equals(start.getIdStation()) && r.getDestination().getIdStation().equals(end.getIdStation())) {

                return r;
            }}

        }
        return null;
    }

    public void createRoute(DTORoute dto) {
        //se agrega ruta a la bdd
        rDAO.addRoute(dto);
        //se lo crea para agregar a la lista de rutas

    }

    /* recibe nodos inicio y final y la forma por lo que lo quiere buscar


     */
    public ArrayList<ArrayList<Station>> bestRoute4crit(Station start, Station end, String crit) {
        RouteManager rm = RouteManager.getInstance();

        // [[inicio, estaciones, fin],[inicio, estaciones, fin],[inicio, estaciones, fin]]
        ArrayList<ArrayList<Station>> listpaths = this.paths(start, end);
        System.out.println("TAMAÑOO LISTA " + listpaths.size());
        ArrayList<ArrayList<Station>> resultadoaux = new  ArrayList<ArrayList<Station>>();
        ArrayList<ArrayList<Station>> resultado = new  ArrayList<ArrayList<Station>>();
        if(listpaths.size() != 0){
         switch(crit){

            case "MAS_BARATO": {
                resultadoaux =cheaper(listpaths, resultado);
                break;

            }
            case "MAS_RAPIDO" : {
                resultadoaux= faster(listpaths, resultado);
                break;
            }
            case "MENOR_DISTANCIA": {

                resultadoaux =shortest(listpaths, resultado);
                break;
            }
             case "TODOS": {
                 resultadoaux= listpaths;
                break;
             }
             default:{
                 break;
             }

        }
            System.out.println("TAMAÑO RESULTADOAUX " + resultadoaux.size());

            grafoPanel.paintRoutes(resultadoaux);
            grafoPanel.repaint();

        }
        else{
            resultadoaux= null;
        }




        return resultadoaux;
    }


   // por cada lista de estaciones, tengo q obtener la ruta de ellas y calcular por atributo

    private  ArrayList<ArrayList<Station>> shortest(ArrayList<ArrayList<Station>> listpathss, ArrayList<ArrayList<Station>> resultado) {

        Double distance = Double.MAX_VALUE;
        ArrayList<ArrayList<Station>> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpathss) {

            Double distanceAux = distanceTotalRoute(cs);
            minim.add(distanceAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        resultado.add(listpathss.get(i));
        listpathss.remove(listpathss.get(i));

        if(!listpathss.isEmpty()){    shortest(listpathss, resultado);}


        return resultado;
    }

    public Double distanceTotalRoute(ArrayList<Station> cs) {
        System.out.println("DISTANCIA TAOTAL" + cs.size());
        Double distanceAux = 0.0;

        Route ro = new Route();

        for (int i =0; i< cs.size()-1 ; i++) {
          //  System.out.println("ESTACIONES DE LA LISTA dentro for estacio una" + cs.get(i).getIdStation() + " estacon dos " + cs.get(i+1).getIdStation());
            ro = getRoute(cs.get(i), cs.get(i+1));
         //   System.out.println("ESTACIONES DE LA LISTA encontro ruta?" + ro.getIdRoute());
            distanceAux += ro.getDistance();

        }
        return distanceAux;
    }

    //para bucar por mas rapido
    private  ArrayList<ArrayList<Station>> faster(ArrayList<ArrayList<Station>> listpaths, ArrayList<ArrayList<Station>> resultado) {
       // System.out.println("entra a faster");
        Double duration = Double.MAX_VALUE;
        ArrayList<ArrayList<Station>> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpaths) {

            RouteDAO rdao= new RouteDAO();
            Route ro = new Route();
            Double durationAux = durationTotalRoute(cs);


            minim.add(durationAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        resultado.add(listpaths.get(i));
        listpaths.remove(listpaths.get(i));

        if(!listpaths.isEmpty()){  faster(listpaths, resultado);}


        return resultado;
    }

    public Double durationTotalRoute(ArrayList<Station> cs) {

        Route ro = new Route();
        Double durationAux = 0.0;
        for (int i =0; i< cs.size()-1 ; i++) {
            ro = getRoute(cs.get(i), cs.get(i+1));
            durationAux += ro.getDuration();
        }
        return durationAux;
    }

    private  ArrayList<ArrayList<Station>> cheaper(ArrayList<ArrayList<Station>> listpaths, ArrayList<ArrayList<Station>> resultado) {


        ArrayList<ArrayList<Station>> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpaths) {

            Route ro = new Route();
            Double costeAux = costTotalRoute(cs);

            minim.add(costeAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        resultado.add(listpaths.get(i));
        listpaths.remove(listpaths.get(i));

        if(!listpaths.isEmpty()){  cheaper(listpaths, resultado);}


        return resultado;
    }

    public Double costTotalRoute(ArrayList<Station> cs) {
        Route ro = new Route();
        Double costeAux = 0.0;
        for (int i =0; i< cs.size()-1 ; i++) {
            ro = getRoute(cs.get(i), cs.get(i+1));
            costeAux += ro.getCost();
        }

        return costeAux;
    }

    public List<String> flujoMax(Station start, Station end){

        ArrayList<ArrayList<Station>> listRoutesFlujo = this.paths(start, end);
        List<String> returnString =new ArrayList<>();
        Integer TotalFlujoMax = 0;

        for(ArrayList<Station> cs : listRoutesFlujo) {

            Route ro = new Route();
            Integer flujRoute = flowPerPart(cs);
            TotalFlujoMax += flujRoute;

            // Se le setea los valores nuevos a la cap max (Pesos)
            for (int i = 0; i < cs.size() - 1; i++) {
                ro = getRoute(cs.get(i), cs.get(i+1));
                ro.setMaxPassagers(ro.getMaxPassagers() - flujRoute);
            }

        }

        returnString.add(start.getName());
        returnString.add(end.getName());
        returnString.add(TotalFlujoMax.toString());

        return returnString;
    }

    private Integer flowPerPart(ArrayList<Station> cs) {
        Route ro;
        Integer flujAux = 0;
        for (int i =0; i< cs.size()-1 ; i++) {
            ro = getRoute(cs.get(i), cs.get(i+1));
          if(ro.getMaxPassagers() < flujAux || flujAux ==0){
                flujAux = ro.getIdRoute();
            }
           }


        return flujAux;

    }


    public ArrayList<ArrayList<Station>> paths(Station start, Station end) {

        ArrayList<ArrayList<Station>> listpaths = new ArrayList<ArrayList<Station>>();
        ArrayList markedStations = new ArrayList();

        markedStations.add(start);

        searchPaths(start, end, markedStations, listpaths);
        return listpaths;

    }


    private void searchPaths(Station start, Station end, ArrayList markedStations, ArrayList<ArrayList<Station>> listpaths) {

        ArrayList<Station> adjacentStations = this.getAdjacentStations(start);

        ArrayList<Station> marked = null;
        for(Station s : adjacentStations){
            marked = (ArrayList<Station>) markedStations.stream().collect(Collectors.toList());
                if (end.getIdStation().equals(s.getIdStation())) {
                    marked.add(s);
                    listpaths.add(new ArrayList<Station>(marked));


                } else {
                    if (!marked.contains(s)) {
                        marked.add(s);
                        this.searchPaths(s, end, marked, listpaths);
                    }


                }

        }


    }

    private ArrayList<Station> getAdjacentStations(Station start) {
        ArrayList<Station> adjacents = new ArrayList<>();

        for(Route r: this.getListRoutes() ){
            //Se verifica que tanto la ruta como el transporte esten activo y la estacion en operativa
            System.out.println("RUTA " + r.getIdRoute() + " status " + r.getStatus() + " status tranport " + r.getTransport().isStatus() + " destino status " + r.getDestination().getStatus());
            if(r.getStatus() && r.getTransport().isStatus() && r.getDestination().getStatus().equals("OPERATIVA")){

                if(start.getIdStation().equals(r.getOrigin().getIdStation())){

                    adjacents.add(r.getDestination());
                }
            }



        }

        return adjacents;
    }

    public void deleteRoute(Route r) {
        System.out.println("TIENE Q PASAR DOS VECES PORQ SON DOS RUTAS A ELIMINAR");
        ListGlobalRoute lr= ListGlobalRoute.getInstance();
        lr.deleteRoute(r);

        rDAO.deleteRoute(r);
    }

    public void editRoute(Boolean isStart, Station s) {

        ListGlobalRoute lr= ListGlobalRoute.getInstance();
        for(Route r1 : this.getListRoutes()){
            if(r1.getOrigin().getIdStation().equals(s.getIdStation()) || r1.getDestination().getIdStation().equals(s.getIdStation())){
                System.out.println("COMPARACION SOBRE LO Q TENGO Y LO Q QUIERO PONER ROUTEMAN" + isStart + " " + r1.getStatus());
                rDAO.editRoute(isStart, r1);
                lr.editRoute(isStart, r1);
            }
        }


    }
}
