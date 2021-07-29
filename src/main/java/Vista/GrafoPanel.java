package main.java.Vista;

import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Route;
import main.java.classes.Station;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GrafoPanel extends JPanel {

    private List<ViewVertex> vertices = new ArrayList<>();
    private ArrayList<ViewEdges> edges = new ArrayList<>();
    private static final GrafoPanel INSTANCE = new GrafoPanel();
    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();

    public static GrafoPanel getInstance() {
        return INSTANCE;
    }

 GrafoPanel(){}

    public void initVertex(HashMap<Integer, Station> list) {
        System.out.println("DENTRO DEL INIT VERTEX" + list.get(1));
        this.vertices.clear();
        int posY = 100;
        int posX= 0;

        int aux = 0;


       for(Station s: list.values()){

            aux++;
            posX +=90;

            if(aux % 2 == 0){
                posY =300;
            } else { posY=200;}

            ViewVertex vx = new ViewVertex(posX, posY, s);
            vx.setId(s.getIdStation());
            vx.setName(s.getName());
            vertices.add(vx);
            System.out.println("vertices añadidos" + vertices.size());
        }

        getInstance().repaint();

    }

    public void initArista(ArrayList<Route> listRoutes) {

        System.out.println("entro a initArista grafo panel" + listRoutes.get(1));
        edges.clear();

        for(Route r: listRoutes){
            System.out.println("viewvertex init arista :"+ r.getOrigin().getName());
            ViewVertex VertexStart = this.getVertex(r.getOrigin());
            ViewVertex VertexEnd = this.getVertex(r.getDestination());
            System.out.println("viewvertex init arista :" + VertexEnd + VertexStart);
            System.out.println("viewvertex init arista :" + r );
       //     System.out.println("viewvertex init arista color de trasporte:" + r.getTransport() );
            ViewEdges e = new ViewEdges(VertexStart, VertexEnd, r, Color.BLUE);

            edges.add(e);

        }
        getInstance().repaint();
    }

    private ViewVertex getVertex(Station s) {
        System.out.println("VERTICE TAMAÑO" + vertices.size());
        System.out.println("VERTICE station" + s.getName());

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
            g2d.setPaint(v.getColour());
            g2d.fill(v.getNode());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        for (ViewEdges a : this.getEdges()) {
            int puntoMedioX = (int) (a.getStart().getCoordX() + a.getEnd().getCoordX()) / 2;
            int puntoMedioY = (int) (a.getStart().getCoordY() + a.getEnd().getCoordY()) / 2;
            Route ruta = a.getRoutCon();

            g2d.setPaint(a.getColour());
            g2d.setStroke(a.getLineF());
            g2d.drawString(ruta.getIdRoute() + " [km]", puntoMedioX + 20, puntoMedioY + 20);
            g2d.drawString(ruta.getCost() + " [Tn]", puntoMedioX + 20, puntoMedioY + 33);
            g2d.drawString(ruta.getDuration() + " [min]", puntoMedioX + 20, puntoMedioY + 46);
            g2d.draw(a.getLin());
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
