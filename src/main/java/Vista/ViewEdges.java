package main.java.Vista;

import main.java.classes.Route;
import main.java.classes.Station;
import main.structures.Edge;

import java.awt.*;
import java.awt.geom.Line2D;

public class ViewEdges {

        private ViewVertex start;
        private ViewVertex end;
        private Shape lin;
        private int offset;
        private Paint Colour;
        private Edge<Station> route;
        private Route routCon;


    public ViewEdges(ViewVertex start, ViewVertex end, Route r, Paint colour) {
        this.start = start;
        this.end = end;
        this.Colour = colour;
        this.lin = new Line2D.Double();
        this.offset = start.RADIO / 2;
    }

    public Route getRoutCon() {
        return routCon;
    }

    public void setRoutCon(Route routCon) {
        this.routCon = routCon;
    }

    public Paint getColour() {
        return Colour;
    }

    public void setColour(Paint colour) {
        Colour = colour;
    }

    public void update() {
        this.lin = new Line2D.Double(start.getCoordX() + offset, start.getCoordY() + offset,
                end.getCoordX() + offset, end.getCoordY() + offset);
    }

}
