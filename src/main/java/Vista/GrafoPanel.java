package main.java.Vista;

import main.java.DAO.RouteDAO;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Route;
import main.java.classes.Station;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GrafoPanel extends JPanel {

    private List<ViewVertex> vertices = new ArrayList<>();
    private ArrayList<ViewEdges> edges = new ArrayList<>();
    private static final GrafoPanel INSTANCE = new GrafoPanel();
    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();
    private RouteDAO rDAO = new RouteDAO();
    public static GrafoPanel getInstance() {
        return INSTANCE;
    }

 GrafoPanel(){}

    public void initVertex(HashMap<Integer, Station> list) {

        this.vertices.clear();
        int posY = 100;
        int posX= 0;

        int aux = 0;


       for(Station s: list.values()){

            aux++;
            posX +=80;

            switch (aux%4){
                case 0: {
                    posY =100;
                    break;
                }
                case 1: {
                    posY=200;
                    break;
                }
                case 2:{
                    posY=300;
                    break;
                }
                case 3: {
                    posY=370;
                    break;
                }
            }


            ViewVertex vx = new ViewVertex(posX, posY, s);
            vx.setId(s.getIdStation());
            vx.setName(s.getName());
            vertices.add(vx);

        }

        getInstance().repaint();

    }

    public void initArista(ArrayList<Route> listRoutes) {

        System.out.println("entro a initArista grafo panel" + listRoutes.get(1));
        edges.clear();
        ArrayList<Route> aux = new ArrayList<>() ;


        for(Route r: listRoutes){
            ViewVertex VertexStart = this.getVertex(r.getOrigin());
            ViewVertex VertexEnd = this.getVertex(r.getDestination());
            ViewEdges e;

           if(!aux.isEmpty() && TwoRoutesSameStartEnd(r, aux)){
               e = new ViewEdges(VertexStart, VertexEnd, r,  r.getTransport().getColour(), 40);
           } else {
               e = new ViewEdges(VertexStart, VertexEnd, r,  r.getTransport().getColour());
               Route routeaux = new Route(r.getOrigin(), r.getDestination());
               System.out.println("QUE MEIDA ES NULL??" + routeaux.toString());
               aux.add(routeaux);
           }




            edges.add(e);

        }
        getInstance().repaint();
    }

    private boolean TwoRoutesSameStartEnd(Route r, ArrayList<Route> aux) {

        for(Route ro: aux){
            System.out.println("get origen" + ro.getOrigin().getIdStation());

            if(ro.getOrigin().getIdStation() == r.getOrigin().getIdStation() && ro.getDestination().getIdStation() == r.getDestination().getIdStation()){
                return true;
            }

        }
        return false;
    }

    private ViewVertex getVertex(Station s) {

        ViewVertex l= this.vertices.stream().filter((v) ->
            v.getStationV().getName().equals(s.getName())
        ).findFirst().get();
        System.out.println(l);
        return l;
    }

    public List<ViewVertex> getVertices() {
        return vertices;
    }

    public ArrayList<ViewEdges> getEdges() {
        return edges;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.dibujarAristas(g2d);
        this.dibujarVertices(g2d);
    }


    private void dibujarVertices(Graphics2D g2d) {
        for (ViewVertex v : this.getVertices()) {
            g2d.setPaint(v.getColour());
            g2d.drawString(v.info(), v.getCoordX() + 65, v.getCoordY() + 65);
            System.out.println("COLOR DE DIBUJAR VERTICE" + v.getColour().toString());

            g2d.setPaint(v.getColour());
            g2d.fill(v.getNode());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        for (ViewEdges a : this.getEdges()) {
            int puntoMedioX = (int) (a.getStart().getCoordX() + a.getEnd().getCoordX()) / 2;
            int puntoMedioY = (int) (a.getStart().getCoordY() + a.getEnd().getCoordY()) / 2;
            Route ruta = a.getRoutCon();
            System.out.println("COLOR DE DIBUJAR arista" + a.getColour());
            g2d.setPaint(a.getColour());
            g2d.setStroke(a.getLineF());
            g2d.drawString(ruta.getIdRoute() + " [km]", puntoMedioX + 20, puntoMedioY + 20);
            g2d.drawString(ruta.getCost() + " [Tn]", puntoMedioX + 20, puntoMedioY + 33);
            g2d.drawString(ruta.getDuration() + " [min]", puntoMedioX + 20, puntoMedioY + 46);
            g2d.draw(a.getLin());
        }
    }

    public void paintRoute(ArrayList<Station> bestRoute) {
        System.out.println("mejor rta dentro del paint " + bestRoute.size() + " " + bestRoute.toString());
        this.updateEdge();
        RouteManager rm = RouteManager.getInstance();
        for (int i = 0; i < bestRoute.size() - 1; i++) {
            Route r = rDAO.searchRoute(bestRoute.get(i), bestRoute.get(i+1));

            System.out.println("ruta q se encuentra en teoria" + this.getEdge(r).getRoutCon());
            System.out.println("trasporrte de ruta" + r.getTransport());
            this.updateColourE(this.getEdge(r), r.getTransport().getColour());
        }
    }

    private void updateEdge() {
        for(ViewEdges e : this.edges){
            this.updateColourE(e, Color.PINK);
        }
    }

    private void updateColourE(ViewEdges edge, Paint c) {
        edge.setColour(c);
        edge.update();

    }

    private ViewEdges getEdge(Route r) {

        for(ViewEdges e: this.edges){

            if(e.getRoutCon().getIdRoute().equals(r.getIdRoute())){
                System.out.println("entra el if");
                return e;
            }
        }
        return null;
    }


}
