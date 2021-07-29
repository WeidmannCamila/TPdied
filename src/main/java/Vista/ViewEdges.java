package main.java.Vista;

import main.java.classes.Route;
import main.java.classes.Station;

import java.awt.*;
import java.awt.geom.Line2D;

public class ViewEdges {

        private ViewVertex start;
        private ViewVertex end;
        private Shape lin;
        private int offset;
        private Paint Colour;
        private Stroke lineF;
        private Route routCon;


    public ViewEdges(ViewVertex start, ViewVertex end, Route r, Color colour) {
        System.out.println("coordenadas inicio"+ start.getCoordX() + start.getCoordY());
        this.offset = start.RADIO/2;
        this.start = start;
        this.end = end;
        this.lineF = new BasicStroke();
        this.Colour = colour;
        this.lin = new Line2D.Double(start.getCoordX() + offset, start.getCoordY() + offset,
                end.getCoordX() + offset, end.getCoordY() + offset);

        this.routCon = r;

    }

    public ViewEdges(){

    }

    public ViewVertex getStart() {
        return start;
    }

    public void setStart(ViewVertex start) {
        this.start = start;
    }

    public ViewVertex getEnd() {
        return end;
    }

    public void setEnd(ViewVertex end) {
        this.end = end;
    }

    public Shape getLin() {
        return lin;
    }

    public void setLin(Shape lin) {
        this.lin = lin;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Stroke getLineF() {
        return lineF;
    }

    public void setLineF(Stroke lineF) {
        this.lineF = lineF;
    }

    public Route getRoutCon() {
        return routCon;
    }

    public void setRoutCon(Route routCon) {
        this.routCon = routCon;
    }

    //public Paint getColour() {return Colour;    }

    public void setColour(Paint colour) {
        Colour = colour;
    }

    public void update() {
        this.lin = new Line2D.Double(start.getCoordX() + offset, start.getCoordY() + offset,
                end.getCoordX() + offset, end.getCoordY() + offset);
    }

    public Paint getColour() {
        if (this.Colour == null)
            this.Colour = new GradientPaint((float) start.getCoordX() + 100, (float) start.getCoordY() + 100,
                    (Color) end.getBaseColour(), (float) end.getCoordX() + 100,
                    (float) end.getCoordY() + 100, (Color) start.getBaseColour());
        return Colour;
    }

}
