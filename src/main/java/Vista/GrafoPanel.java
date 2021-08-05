package main.java.Vista;

import main.java.DAO.RouteDAO;
import main.java.Managers.RouteManager;
import main.java.Managers.StationManager;
import main.java.classes.Route;
import main.java.classes.Station;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.*;
import java.util.List;

public class GrafoPanel extends JPanel {

    private List<ViewVertex> vertices = new ArrayList<>();
    private ArrayList<ViewEdges> edges = new ArrayList<>();

    private StationManager sm =new StationManager();
    private RouteManager rm = new RouteManager();
    private RouteDAO rDAO = new RouteDAO();

    private ViewVertex nodeClicked = null;
    private static final GrafoPanel INSTANCE = new GrafoPanel();
    public static GrafoPanel getInstance() {
        return INSTANCE;
    }

 GrafoPanel(){this.initialize();}


    private void initialize() {
        //permite mover el vertice
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                ViewVertex v = nodePressed(e.getPoint());
                if (v != null) {
                    nodeClicked = v;
                   updateVertex(nodeClicked, e.getPoint());
                }

            }

            public void mouseReleased(MouseEvent e) {
                if (nodeClicked != null) {

                    updateVertex(nodeClicked, e.getPoint());
                   updateRoutes();
                }

                nodeClicked = null;
            }
        });
        this.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (nodeClicked != null) {
                    updateVertex(nodeClicked, e.getPoint());
                    updateRoutes();
                }

            }
        });
    }

    private ViewVertex nodePressed(Point p) {
        for (ViewVertex v : this.getVertices()) {
            if (v.getNode().contains(p)) {
                return v;
            }
        }

        return null;
    }


    private void updateVertex(ViewVertex v, Point nuevo) {
        v.setCoordX(nuevo.x);
        v.setCoordY(nuevo.y);
        v.update();
        repaint();
    }

    public void updateRoutes() {
        Iterator var2 = this.edges.iterator();

        while(var2.hasNext()) {
            ViewEdges a = (ViewEdges)var2.next();
            a.update();
        }

        repaint();
    }


    public void initVertex(HashMap<Integer, Station> list) {

        this.vertices.clear();
        int posY = 10;
        int posX= 10;
        int aux = 0;


       for(Station s: list.values()){

            aux++;

            switch (aux%6){
                case 0: {
                    posX =100;
                    break;
                }
                case 1: {
                    posX=400;
                    break;
                }
                case 2:{
                    posX=210;
                    break;
                }
                case 3: {
                    posX=600;
                    break;
                }
                case 4: {
                    posX=300;
                    break;
                }
                case 5: {
                    posX=500;
                    break;
                }

            }

            ViewVertex vx = new ViewVertex(posX, posY, s);
            vx.setId(s.getIdStation());
            vx.setName(s.getName());
            vertices.add(vx);
            posY +=55;

        }

        getInstance().repaint();

    }

    public void initArista(ArrayList<Route> listRoutes) {

        edges.clear();
        ArrayList<Route> aux = new ArrayList<>();
        for (Route r : listRoutes) {
            ViewVertex VertexStart = this.getVertex(r.getOrigin());
            ViewVertex VertexEnd = this.getVertex(r.getDestination());
            ViewEdges e;
            int offset=0;
            if (TwoRoutesSameStartEnd(r, listRoutes)) {
                Route routeaux = new Route(r.getIdRoute(), r.getOrigin(), r.getDestination());

                //si son dos caminos al mismo nodo, pongo una arista arriba del medio del nodo y por abajo del medio  ==O (RADIO +-3) --O (RADIO/2)
               if(TwoRoutesSameStartEnd(r, aux)){

                   offset =VertexEnd.RADIO/3;
               } else {
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
        this.drawEdges(g2d);
        this.drawVertices(g2d);
    }


    private void drawVertices(Graphics g2d) {
        for (ViewVertex v : this.getVertices()) {



            g2d.setColor((Color) v.getColour());
            g2d.fillOval(v.getCoordX(), v.getCoordY(), v.RADIO, v.RADIO);

            g2d.setFont(new Font("Serif", Font.BOLD, 15));
            g2d.setColor(Color.WHITE);
            g2d.drawString(v.info(), v.getCoordX()+8, v.getCoordY()+15);


        }

    }

    private void drawEdges(Graphics2D g2d) {
        for (ViewEdges a : this.getEdges()) {
            int puntx = (a.getoffset());
            int punty = 0;
            if(puntx == 20){
                System.out.println("entro");
                punty += 20;

            } if(puntx == 10){
                punty -= 30;
            }



            int puntoMedioX = punty +(a.getStart().getCoordX() + a.getEnd().getCoordX()) / 2;
            int puntoMedioY = punty +(a.getStart().getCoordY() + a.getEnd().getCoordY()) / 2;
            Route ruta = a.getRoutCon();

            g2d.setPaint(a.getColour());
            g2d.setStroke(a.getLineF());
            g2d.drawString(ruta.getTransport().getName() , puntoMedioX + 20, puntoMedioY + 10);
            g2d.drawString(ruta.getIdRoute() + " [km]", puntoMedioX + 20, puntoMedioY + 20);
            g2d.drawString(ruta.getCost() + " [Tn]", puntoMedioX + 20, puntoMedioY + 33);
            g2d.drawString(ruta.getDuration() + " [min]", puntoMedioX + 20, puntoMedioY + 46);
            g2d.setPaint(a.getColour());

            g2d.draw(a.getLin());
            this.arrow(g2d, new Point(a.getEnd().getCoordX() + a.getoffset(), a.getEnd().getCoordY() + a.getoffset()), new Point(a.getStart().getCoordX() - a.getoffset(), a.getStart().getCoordY() - a.getoffset()), (Color) a.getColour());
        }
    }



    public void paintRoutes(ArrayList<ArrayList<Station>> bestRoutes){
        this.updateEdge();
        RouteManager rm = RouteManager.getInstance();

        //creo una lista de rutas por si ya existe una entre las mismas estaciones, para poder visualizar ambas
        ArrayList<Route> listRouteaux = new ArrayList<>();
        for(ArrayList<Station> s : bestRoutes){

            for (int i = 0; i < s.size() - 1; i++) {

                listRouteaux = rm.getListRoutes(s.get(i), s.get(i+1));
                 for(Route r : listRouteaux){
                     System.out.println("COLORES RUTAS A PINTA de" + r.getOrigin().getName() + " a " + r.getDestination().getName() + " " + r.getTransport().getColour() + " id " + r.getIdRoute());
                     this.updateColourE(this.getEdge(r), r.getTransport().getColour());
                 }

            }

        }

    }


    private void updateEdge() {
        for(ViewEdges e : this.edges){
            this.updateColourE(e, Color.GRAY);
        }
    }

    private void updateColourE(ViewEdges edge, Paint c) {
        edge.setColour(c);
        edge.update();

    }

    private ViewEdges getEdge(Route r) {
        for(ViewEdges e: this.edges){
            if(e.getRoutCon().getIdRoute().equals(r.getIdRoute())){
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

    public ArrayList<ViewPageRank> getPageRank() {
        ArrayList<ViewPageRank> result = new ArrayList<>();
        List<ViewVertex> listRoute = this.vertices;
        ViewPageRank e;
        for (ViewVertex s : listRoute) {
            e = new ViewPageRank();
            e.setVertice(s);
            e.setNodesIn(s.getNodeIn(this.edges));
            e.setNodesOut(s.getNodesOut(this.edges));
            e.setPageRank(1.0);
            result.add(e);
        }

        int j = 0;
        for (int i = 0; i < result.size(); i++) {
            ArrayList<ViewPageRank> aux = new ArrayList<>();


            for (ViewPageRank s : result) {
                ViewPageRank set = new ViewPageRank();
                set.setVertice(s.getVertice());
                set.setNodesIn(s.getNodesIn());
                set.setNodesOut(s.getNodesOut());
                set.setPageRank(s.getPageRank());
                aux.add(set);
            }



            for(j = 0; j < result.size(); ++j) {
                Double PRA = 0.0D;
                for (ViewVertex v2 : result.get(j).getNodesIn()) {
                    Double PRi = 0.0D;
                    Double C = 0.0D;

                    Iterator aux1 = aux.iterator();

                    while (aux1.hasNext()) {
                        ViewPageRank v1 = (ViewPageRank) aux1.next();
                        if (v2.equals(v1.getVertice())) {
                            PRi = v1.getPageRank();
                            C = v1.getNodesOut();
                            PRA= PRA + PRi / C;

                        }
                    }

                }
                // PR(A) = (1-D)+D * (PRi/C)
                Double TotalPageR = 0.5 + 0.5 * PRA;

                result.get(j).setPageRank(TotalPageR);
            }
        }

            result.sort((ViewPageRank v, ViewPageRank v2) -> v.getPageRank().compareTo(v2.getPageRank()));
            return result;
        }


}
