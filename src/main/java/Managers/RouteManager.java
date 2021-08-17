package main.java.Managers;

import main.java.DAO.RouteDAO;
import main.java.DTOs.DTORoute;
import main.java.Vista.GrafoPanel;
import main.java.Vista.ViewPageRank;
import main.java.classes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            if(r.getStatus()){
                if (r.getOrigin().getIdStation().equals(start.getIdStation()) && r.getDestination().getIdStation().equals(end.getIdStation())) {
                    aux.add(r);
                }}

        }
        return aux;
    }

    public ArrayList<Route> getRoutesFilter(Station start, Station end){
        ArrayList<Route> aux = new ArrayList<>();

        for (Route r : this.getListRoutes()) {
            if(r.getStatus() && r.getTransport().isStatus()){
                if (r.getOrigin().getIdStation().equals(start.getIdStation()) && r.getDestination().getIdStation().equals(end.getIdStation())) {
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

    /* recibe nodos inicio y final y la forma por lo que lo quiere buscar */
    public ArrayList<ListRoute> bestRoute4crit(Station start, Station end, String crit) {

        // [[inicio, estaciones, fin],[inicio, estaciones, fin],[inicio, estaciones, fin]]
        ArrayList<ArrayList<Station>> listpaths = this.paths(start, end);
        System.out.println("TAMAÑO LISTPATH" + listpaths.size());
        ArrayList<ListRoute> listTotable = null;

        ArrayList<ArrayList<Station>> resultadoaux = new  ArrayList<ArrayList<Station>>();

        if(listpaths.size() != 0){
            listTotable = recursiveRoutes(listpaths, start, end);
            resultadoaux= listpaths;
         switch(crit){

            case "MENOR_COSTO": {

                listCheaper(listTotable);
                break;
            }
            case "MAS_RAPIDO" : {
                listFaster(listTotable);
                break;
            }
            case "MENOR_DISTANCIA": {
                listShortest(listTotable);
                break;
            }
             case "TODOS": {
                 resultadoaux= listpaths;
                break;
             }
             default:{

             }

        }
            //System.out.println("TAMAÑO RESULTADOAUX " + resultadoaux.size());

         grafoPanel.paintRoutes(resultadoaux);
            grafoPanel.repaint();

        }
        else{
            resultadoaux= null;
        }


        return listTotable;
    }

    public ArrayList<ListRoute> recursiveRoutes(ArrayList<ArrayList<Station>> listpaths, Station start, Station end) {
        ArrayList<ListRoute> listResult1 = new ArrayList<>();
        ListRoute trayecto = new ListRoute();
        System.out.println("recursive 1" + start.getName() + " " + end.getName());

        for(int i = 0; i < listpaths.size(); i++){
            int contador =0;
            ArrayList<Route> lr = new ArrayList<Route>();

            ArrayList<Station> Initstations = new ArrayList<>();
            ArrayList<ListRoute> listResult = new ArrayList<>();

         System.out.println("TANAÑOS LISTHPATH" +  i);

            listResult1.addAll(recursive(listpaths.get(i), listResult, lr, start, end,Initstations));
            System.out.println("recursive2" + listResult1.size());

            contador =listResult.size();
            if(contador!=0){
                i= i+(contador-1);
           }

            System.out.println("TANAÑOS II" + listResult.size() + i);
        }

        return listResult1;
    }

    // le doy una lista de estaciones [start, ..., end]
    private ArrayList<ListRoute> recursive(ArrayList<Station> stations, ArrayList<ListRoute> listResult , ArrayList<Route> lr, Station start, Station end,  ArrayList<Station> Initstations) {
      //  ArrayList<ListRoute> listResult = new ArrayList<>();
        ListRoute trayecto = new ListRoute();
        ListRoute trayecto2 = new ListRoute();


        if(stations.get(0).getIdStation().equals(start.getIdStation())){
          
           Initstations = new ArrayList<>(stations.subList(1, stations.size()-1));

        }

        ArrayList<Route> routes = this.getRoutesFilter(stations.get(0) , stations.get(1));
         System.out.println("RUTAS tamaño " + routes.size() +  " "+stations.get(0).getName() + stations.get(1).getName());
        int n = routes.size();
        System.out.println("numer N " + n);

        if(stations.get(1).getIdStation().equals(stations.get(stations.size()-1).getIdStation())) {
                if(routes.size()==1) {
                    lr.add(routes.get(0));
                 System.out.println("termina con una sola ruta" + routes.get(0).getDistance());
                    /*if(lr2.size() != 0 && !lr.containsAll(lr2)){
                        lr2.add(routes.get(0));
                        trayecto2.setListRoute(lr2);
                        listResult.add(trayecto2);
                        trayecto2.setOrigin(start);
                        trayecto2.setDestination(end);
                        trayecto2.setListStation(Initstations);

                    }*/
                    trayecto.setOrigin(start);
                    trayecto.setDestination(end);
                    trayecto.setListRoute(lr);
                    trayecto.setListStation(Initstations);
               System.out.println("trayecto " + trayecto.listRoute);
                 listResult.add(trayecto);
                    System.out.println("ListResult " + listResult.size());

                }
                else{
                    System.out.println("ENTRA A TERMINA EN DOS ");
                   /* lr.add(routes.get(0));
                    lr2.add(routes.get(1));

                    trayecto.listRoute = lr;
                    trayecto2.listRoute =lr2;
                    trayecto.setOrigin(start);
                    trayecto.setDestination(end);
                    trayecto2.setOrigin(start);
                    trayecto2.setDestination(end);
                    trayecto2.setListStation(Initstations);
                    trayecto.setListStation(Initstations);

                    listResult.add(trayecto);
                    listResult.add(trayecto2);*/
                    while(n !=0){
                        lr.add(routes.get(n-1));
                        trayecto.listRoute = lr;
                        trayecto.setOrigin(start);
                        trayecto.setDestination(end);
                        trayecto.setListStation(Initstations);
                        listResult.add(trayecto);
                        lr.remove(routes.get(n-1));
                        n--;
                    }

                }
            return listResult;


        } else {
                if(routes.size()==1) {

                  System.out.println("una sola ruta");

                    lr.add(routes.get(0));
                  //  lr2.add(routes.get(0));
                    ArrayList<Station> scopy = new ArrayList<Station>(stations.subList(1, stations.size()));
                  //  System.out.println("arrelo " + scopy );
                    recursive(scopy, listResult, lr, start, end, Initstations);


                }
                else {
                    System.out.println("ENTRO AL ELSE DE DOS RUTAS " + n);
                    while(n!=0){
                        System.out.println("entra al while");
                                lr.add(routes.get(n-1));
                        ArrayList<Station> scopy = new ArrayList<Station>(stations.subList(1, stations.size()));
                        recursive(scopy, listResult, lr, start, end, Initstations);
                        lr.remove(routes.get(n-1));
                        n--;
                    }


                   /* lr.add(routes.get(0));
                    lr2.add(routes.get(1));
                    ArrayList<Station> scopy = new ArrayList<Station>(stations.subList(1, stations.size()));
                    recursive(scopy, listResult, lr, lr2, start, end, Initstations);
                    recursive(scopy, listResult, lr2, lr, start, end, Initstations);*/
                }


            }


        return listResult;
    }

    private ArrayList<ListRoute> listShortest(ArrayList<ListRoute> listr){

        for(ListRoute r: listr){

            r.setTotalDistance(distanceTotal(r.listRoute));
        }

        Comparator <ListRoute> distanceComparator = new Comparator<ListRoute>() {

            @Override
            public int compare(ListRoute r1, ListRoute r2) {
                return (int) (r1.getTotalDistance() - r2.getTotalDistance());
            }

        };

        Collections.sort(listr, distanceComparator);
        return listr;
    }

    public Double distanceTotal(ArrayList<Route> listRoute) {
        Double distanceAux = 0.0;
        for(Route r: listRoute){
            distanceAux+= r.getDistance();
        }

        return distanceAux;
    }

    private ArrayList<ListRoute> listFaster(ArrayList<ListRoute> listr){

        for(ListRoute r: listr){
            r.setTotalDuration(durationTotal(r.listRoute));
        }

        Comparator <ListRoute> distanceComparator = new Comparator<ListRoute>() {

            @Override
            public int compare(ListRoute r1, ListRoute r2) {
                return (int) (r1.getTotalDuration() - r2.getTotalDuration());
            }

        };

        Collections.sort(listr, distanceComparator);
        return listr;
    }


    public Double durationTotal(ArrayList<Route> listRoute) {
        Double durantionAux = 0.0;
        for(Route r: listRoute){
            durantionAux+= r.getDuration();
        }

        return durantionAux;
    }



    private ArrayList<ListRoute> listCheaper(ArrayList<ListRoute> listr){
        System.out.println("COSTO list " + listr);
        for(ListRoute r: listr){

            r.setTotalCost(costTotal(r.listRoute));
        }

     Comparator <ListRoute> costeComparator = new Comparator<ListRoute>() {

            @Override
            public int compare(ListRoute r1, ListRoute r2) {
                return (int) (r1.getTotalCost() - r2.getTotalCost());
            }

        };

        //listr.sort((ListRoute v, ListRoute v2) -> v.getTotalCost().compareTo(v2.getTotalCost()));


    Collections.sort(listr,costeComparator );
        return listr;
    }

    public Double costTotal(ArrayList<Route> listRoute) {
        Double costAux = 0.0;
        for(Route r: listRoute){
            System.out.println("hay ruta" + r);
            costAux+= r.getCost();
        }
        System.out.println("COSTO" + costAux);
        return costAux;
    }



    public List<String> flujoMax(Station start, Station end){
        ArrayList<ListRoute> listRflujo= this.bestRoute4crit(start,end,"TODOS");

        //como el numero de pasajeros se va modificando, guardo los originales para volver a setearlos
        ArrayList<Integer> maxPaux = new ArrayList<>();
        for(ListRoute l : listRflujo){
            maxPaux.add(l.getMaxPassagers());
        }

        ArrayList<ArrayList<Station>> listRoutesFlujo = this.paths(start, end);
        //string para presentar en tabla
        List<String> returnString =new ArrayList<>();
        Integer TotalFlujoMax = 0;


        for(ListRoute cs : listRflujo) {

            Route ro = new Route();

            Integer flujRoute = flowPerPart(cs);
            System.out.println("Integer "+ flujRoute);
            TotalFlujoMax += flujRoute;

            // Se le setea los valores nuevos a la cap max (Pesos)
            for (int i = 0; i < cs.listRoute.size() ; i++) {
                ro = cs.listRoute.get(i);
                ro.setMaxPassagers(ro.getMaxPassagers() - flujRoute);
            }

        }

        returnString.add(start.getName());
        returnString.add(end.getName());
        returnString.add(TotalFlujoMax.toString());

        int i =0;
        for(ListRoute l : listRflujo){
           l.setMaxPassagers(maxPaux.get(i));
           i++;
        }


        return returnString;
    }

    private Integer flowPerPart(ListRoute cs) {
        Route ro;
        Integer flujAux = 0;

        for (int i =0; i< cs.listRoute.size() ; i++) {
            ro = cs.listRoute.get(i);

          if(ro.getMaxPassagers() < flujAux || flujAux ==0){
                flujAux = ro.getMaxPassagers();
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

            if(r.getStatus() && r.getTransport().isStatus() && r.getDestination().getStatus().equals("OPERATIVA")){

                if(start.getIdStation().equals(r.getOrigin().getIdStation())){

                    adjacents.add(r.getDestination());
                }
            }



        }

        return adjacents;
    }

    public void deleteRoute(Route r) {

        ListGlobalRoute lr= ListGlobalRoute.getInstance();
        lr.deleteRoute(r);

        rDAO.deleteRoute(r);
    }

    public void editRoute(Boolean isStart, Station s) {

        ListGlobalRoute lr= ListGlobalRoute.getInstance();
        for(Route r1 : this.getListRoutes()){
            if(r1.getOrigin().getIdStation().equals(s.getIdStation()) || r1.getDestination().getIdStation().equals(s.getIdStation())){

                rDAO.editRoute(isStart, r1);
                lr.editRoute(isStart, r1);
            }
        }


    }
}
