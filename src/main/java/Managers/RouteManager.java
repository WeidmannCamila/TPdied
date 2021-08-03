package main.java.Managers;

import main.java.DAO.RouteDAO;
import main.java.DTOs.DTORoute;
import main.java.Vista.GrafoPanel;
import main.java.classes.ListGlobalRoute;
import main.java.classes.ListGlobalStation;
import main.java.classes.Route;
import main.java.classes.Station;

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
        System.out.println("EN list route DAOO" + listRoutes.get(1).getDistance());
        return listRoutes;
    }



    public ArrayList<Route> getListRoutes(){
        ListGlobalRoute rl = ListGlobalRoute.getInstance();


        ArrayList<Route> l = rl.getList();

        System.out.println("EN GET LIIIIIIIIIIST ROUTE" + l.get(1).getDistance());


        return l;
    }

    public Route getRoute(Station start, Station end) {
        for (Route r : this.getListRoutes()) {

            if (r.getOrigin().getIdStation().equals(start.getIdStation()) && r.getDestination().getIdStation().equals(end.getIdStation())) {
                System.out.println("EN geeeeeeeeeeet ROUTE" + r.getDistance());
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
    public ArrayList<ArrayList<Station>> bestRoute4crit(Station start, Station end, String crit) {
        RouteManager rm = RouteManager.getInstance();

        // [[inicio, estaciones, fin],[inicio, estaciones, fin],[inicio, estaciones, fin]]
        ArrayList<ArrayList<Station>> listpaths = this.paths(start, end);
        ArrayList<ArrayList<Station>> resultadoaux = new  ArrayList<ArrayList<Station>>();
        ArrayList<ArrayList<Station>> resultado = new  ArrayList<ArrayList<Station>>();
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


        }
        System.out.println("tamaño en  menor distancia" + resultado.size());

        grafoPanel.paintRoutes(resultado);
        grafoPanel.repaint();

        return resultadoaux;
    }


   // por cada lista de estaciones, tengo q obtener la ruta de ellas y calcular por atributo

    private  ArrayList<ArrayList<Station>> shortest(ArrayList<ArrayList<Station>> listpathss, ArrayList<ArrayList<Station>> resultado) {
       // System.out.println("entra a shortest");
        Double distance = Double.MAX_VALUE;
        ArrayList<ArrayList<Station>> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();
        System.out.println("TAMAÑOOOOOOOOOOOOOOOOOOOOOOOOOOOOO listpa " + listpathss.size());
        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpathss) {

            /*RouteDAO rdao= new RouteDAO();
            Route ro = new Route();*/
            Double distanceAux = distanceTotalRoute(cs);

            //recorro la lista, y averiguo las rutas q hay entre cada estacion
          /*  for (int i =0; i< cs.size()-1 ; i++) {

                ro = rdao.searchRoute(cs.get(i), cs.get(i+1));

                distanceAux += ro.getDistance();

            }*/

            minim.add(distanceAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        resultado.add(listpathss.get(i));
        listpathss.remove(listpathss.get(i));
        System.out.println("TAMAÑOOOOOOOOOOOOOOOOOOOOOOOOOOOOO listpa 2 " + listpathss.size());
        if(!listpathss.isEmpty()){    System.out.println("ENTRA AL IF " + listpathss.size()); shortest(listpathss, resultado);}

        System.out.println("TAMAÑOOOOOOOOOOOOOOOOOOOOOOOOOOOOO " + shortroute.size());
        return resultado;


    }

    public Double distanceTotalRoute(ArrayList<Station> cs) {

        Double distanceAux = 0.0;

        Route ro = new Route();

        for (int i =0; i< cs.size()-1 ; i++) {

            ro = getRoute(cs.get(i), cs.get(i+1));

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
            //recorro la lista, y averiguo las rutas q hay entre cada estacion

           /* for (int i =0; i< cs.size() ; i++) {
                ro = rdao.searchRoute(cs.get(i), cs.get(i+1));
                durationAux += ro.getDistance();
            }
*/
            minim.add(durationAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        resultado.add(listpaths.get(i));
        listpaths.remove(listpaths.get(i));

        if(!listpaths.isEmpty()){    System.out.println("ENTRA AL IF " + listpaths.size()); shortest(listpaths, resultado);}


        return resultado;
    }

    public Double durationTotalRoute(ArrayList<Station> cs) {

        Route ro = new Route();
        Double durationAux = 0.0;
        for (int i =0; i< cs.size()-1 ; i++) {
            ro = rDAO.searchRoute(cs.get(i), cs.get(i+1));
            durationAux += ro.getDuration();
        }
        return durationAux;
    }

    private  ArrayList<ArrayList<Station>> cheaper(ArrayList<ArrayList<Station>> listpaths, ArrayList<ArrayList<Station>> resultado) {
       // System.out.println("entra a cheaper");
        Double duration = Double.MAX_VALUE;
        ArrayList<ArrayList<Station>> shortroute = new  ArrayList<>();
        ArrayList<Double> minim = new ArrayList<>();

        // [inic, estaciones, fin]
        for(ArrayList<Station> cs : listpaths) {

            Route ro = new Route();
            Double costeAux = costTotalRoute(cs);
            //recorro la lista, y averiguo las rutas q hay entre cada estacion

            /*for (int i =0; i< cs.size() ; i++) {
                ro = rdao.searchRoute(cs.get(i), cs.get(i+1));
                costeAux += ro.getDistance();
            }*/

            minim.add(costeAux);

        }

        int i =  minim.indexOf(Collections.min(minim));

        resultado.add(listpaths.get(i));
        listpaths.remove(listpaths.get(i));

        if(!listpaths.isEmpty()){    System.out.println("ENTRA AL IF " + listpaths.size()); shortest(listpaths, resultado);}


        return resultado;
    }

    public Double costTotalRoute(ArrayList<Station> cs) {
        Route ro = new Route();
        Double costeAux = 0.0;
        for (int i =0; i< cs.size()-1 ; i++) {
            ro = rDAO.searchRoute(cs.get(i), cs.get(i+1));
            costeAux += ro.getCost();
        }

        return costeAux;
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
