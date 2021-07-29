package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Route;
import main.java.classes.Station;
import main.structures.Vertex;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoPanel extends JPanel {

    private ArrayList<ViewVertex> vertices = new ArrayList<>();
    private ArrayList<ViewEdges> edges = new ArrayList<>();
    private static final GrafoPanel INSTANCE = new GrafoPanel();
    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();

    public static GrafoPanel getInstance() {
        return INSTANCE;
    }

    public void initVertex(HashMap<Integer, Station> list) {
        System.out.println("DENTRO DEL INIT VERTEX" + list.get(1));
        this.vertices.clear();
        int posY = 100;
        int posX= 0;

        int aux = 0;


       for(Station s: list.values()){

            ++aux;
            posX +=60;

            if(aux % 2 == 0){
                posY =100;
            } else { posX=200;}
            ViewVertex vx = new ViewVertex(posX, posY, s);
            vertices.add(vx);
        }

        getInstance().repaint();

    }

    public void initArista(ArrayList<Route> listRoutes) {
        System.out.println("entro a initArista grafo panel");
        edges.clear();

        for(Route r: listRoutes){
            ViewVertex VertexStart = this.getVertex(r.getOrigin());
            ViewVertex VertexEnd = this.getVertex(r.getDestination());

            ViewEdges e = new ViewEdges(VertexStart, VertexEnd, r, r.getTransport().getColour());

            edges.add(e);

        }
        getInstance().repaint();
    }

    private ViewVertex getVertex(Station s) {
        return vertices.stream().filter(v -> v.getStationV() == s).findFirst().get();
    }

    public ArrayList<ViewVertex> getVertices() {
        return vertices;
    }

    public ArrayList<ViewEdges> getEdges() {
        return edges;
    }



    private void dibujarVertices(Graphics2D g2d) {
        for (ViewVertex v : this.getVertices()) {
            g2d.setPaint(v.getColour());
            g2d.drawString(v.info(), v.getCoordX() + 25, v.getCoordY() + 25);
            g2d.setPaint(v.getColour());
            g2d.fill(v.getNode());
        }
    }


    public void paintRoute(ArrayList<Station> bestRoute) {
        this.updateEdge();
        RouteManager rm = RouteManager.getInstance();
        for (int i = 0; i < bestRoute.size() - 1; i++) {
            Route r = rm.getRoute(bestRoute.get(i), bestRoute.get(i + 1));
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
            if(e.getRoutCon() == r){
                return e;
            }
        }
        return null;
    }


}
