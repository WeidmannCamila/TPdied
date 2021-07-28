package main.java.Vista;

import main.java.classes.Route;
import main.java.classes.Station;
import main.structures.Vertex;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Component;

public class GrafoPanel extends JPanel {

    private ArrayList<ViewVertex> vertices = new ArrayList<>();
    private ArrayList<ViewEdges> edges = new ArrayList<>();
    private static final GrafoPanel INSTANCE = new GrafoPanel();

    public static GrafoPanel getInstance() {
        return INSTANCE;
    }

    public void initVertex(ArrayList<Vertex<Station>> list) {
        this.vertices.clear();
        int posY = 100;
        int posX= 0;

        int aux = 0;
        Iterator v = list.iterator();

        while(v.hasNext()){
            Vertex<Station> s = (Vertex<Station>) v.next();
            ++aux;
            posX +=60;

            if(aux % 2 == 0){
                posY =100;
            } else { posX=200;}
            ViewVertex vx = new ViewVertex(posX, posY, (Station) s.getData());
            vertices.add(vx);
        }

        getInstance().repaint();

    }

    public void initArista(ArrayList<Route> listRoutes) {
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



}
