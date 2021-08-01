package main.java.Vista;

import main.java.DAO.RouteDAO;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Route;
import main.java.classes.Station;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
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
                    posY=350;
                    break;
                }
                case 2:{
                    posY=150;
                    break;
                }
                case 3: {
                    posY=450;
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

    /*public void initArista(ArrayList<Route> listRoutes) {

        edges.clear();
        ArrayList<Route> aux = new ArrayList<>();



        for (Route r : listRoutes) {
            ViewVertex VertexStart = this.getVertex(r.getOrigin());
            ViewVertex VertexEnd = this.getVertex(r.getDestination());
            ViewEdges e;

            if (!aux.isEmpty() && TwoRoutesSameStartEnd(r, aux)) {
                e = new ViewEdges(VertexStart, VertexEnd, r, r.getTransport().getColour(), 40);
            } else {
                e = new ViewEdges(VertexStart, VertexEnd, r, r.getTransport().getColour());

            }

            Route routeaux = new Route(r.getOrigin(), r.getDestination());
            System.out.println("QUE MEIDA ES NULL??" + routeaux.toString());
            aux.add(routeaux);
            edges.add(e);

        }
        getInstance().repaint();
    }
*/

    public void initArista(ArrayList<Route> listRoutes) {

        edges.clear();
        ArrayList<Route> aux = new ArrayList<>();



        for (Route r : listRoutes) {
            ViewVertex VertexStart = this.getVertex(r.getOrigin());
            ViewVertex VertexEnd = this.getVertex(r.getDestination());
            ViewEdges e;
            int offset=0;
            Paint c;
            if (TwoRoutesSameStartEnd(r, listRoutes)) {
                Route routeaux = new Route(r.getIdRoute(), r.getOrigin(), r.getDestination());

               if(TwoRoutesSameStartEnd(r, aux)){
                   System.out.println("entro al esta en aux... ");

                   offset =VertexEnd.RADIO/3;
               } else {
                   System.out.println("entro al esta en noo aux... ");

                    offset = VertexEnd.RADIO/3+ (VertexEnd.RADIO/3);
                }

                aux.add(routeaux);


            } else {

                offset = VertexEnd.RADIO/2;


            }
            e = new ViewEdges(VertexStart, VertexEnd, r, r.getTransport().getColour(), offset);

            edges.add(e);

        }
        getInstance().repaint();
    }

    private boolean TwoRoutesSameStartEnd(Route r, ArrayList<Route> aux) {

        for(Route ro: aux){
             if(ro.getIdRoute() != r.getIdRoute() && ro.getOrigin().getIdStation() == r.getOrigin().getIdStation() && ro.getDestination().getIdStation() == r.getDestination().getIdStation()){

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


            g2d.drawString(v.info(), v.getCoordX() + 30, v.getCoordY() + 30);

            g2d.setPaint(v.getColour());
            g2d.fill(v.getNode());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        for (ViewEdges a : this.getEdges()) {
            int puntoMedioX = (a.getStart().getCoordX() + a.getEnd().getCoordX()) / 2;
            int puntoMedioY = (a.getStart().getCoordY() + a.getEnd().getCoordY()) / 2;
            Route ruta = a.getRoutCon();

            g2d.setPaint(a.getColour());
            g2d.setStroke(a.getLineF());
            g2d.drawString(ruta.getIdRoute() + " [km]", puntoMedioX + 20, puntoMedioY + 20);
            g2d.drawString(ruta.getCost() + " [Tn]", puntoMedioX + 20, puntoMedioY + 33);
            g2d.drawString(ruta.getDuration() + " [min]", puntoMedioX + 20, puntoMedioY + 46);
            g2d.draw(a.getLin());
            this.arrow(g2d, new Point(a.getEnd().getCoordX() + a.getoffset(), a.getEnd().getCoordY() + a.getoffset()), new Point(a.getStart().getCoordX() - a.getoffset(), a.getStart().getCoordY() - a.getoffset()), (Color) a.getColour());
        }
    }

    /*public void paintRoute(ArrayList<Station> bestRoute) {


        this.updateEdge();
        RouteManager rm = RouteManager.getInstance();
        for (int i = 0; i < bestRoute.size() - 1; i++) {
            Route r = rDAO.searchRoute(bestRoute.get(i), bestRoute.get(i+1));


            this.updateColourE(this.getEdge(r), r.getTransport().getColour());
        }
    }*/

    public void paintRoutes(ArrayList<ArrayList<Station>> bestRoutes){
        this.updateEdge();
        RouteManager rm = RouteManager.getInstance();
        for(ArrayList<Station> s : bestRoutes){


            for (int i = 0; i < s.size() - 1; i++) {
                Route r = rDAO.searchRoute(s.get(i), s.get(i+1));


                this.updateColourE(this.getEdge(r), r.getTransport().getColour());
            }

        }

    }


    private void updateEdge() {
        for(ViewEdges e : this.edges){
            this.updateColourE(e, Color.gray);
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

    private void arrow(Graphics2D g2, Point tip, Point tail, Color color) {
        double phi = Math.toRadians(20.0D);
        int barb = 20;
        g2.setPaint(color);
        double dy = (tip.y - tail.y);
        double dx = (tip.x - tail.x);
        double theta = Math.atan2(dy, dx);
        double rho = theta + phi;

        for(int j = 0; j < 2; ++j) {
            double x = (double)tip.x - (double)barb * Math.cos(rho);
            double y = (double)tip.y - (double)barb * Math.sin(rho);
            g2.draw(new Line2D.Double(tip.x ,tip.y, x,y));
            rho = theta - phi;
        }

    }
}
