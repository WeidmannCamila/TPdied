package main.java.Vista;

import main.java.classes.Station;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class ViewVertex {

    private String name;
    private Integer id;

    private Integer coordX;
    private Integer coordY;
    private Paint colour;
    private Paint baseColour;
    private Station stationV;
    private Shape node;
    public final Integer RADIO = 25;
    Color myBlue = new Color(100,149,237);





    public ViewVertex(Integer coordX, Integer coordY, Station stationV) {

        this.coordX = coordX;
        this.coordY = coordY;
        this.colour = myBlue;
        this.baseColour = Color.black;
        this.stationV = stationV;
        this.node = new Ellipse2D.Double(coordX, coordY, RADIO, RADIO);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoordX() {
        return coordX;
    }

    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }

    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }

    public Paint getColour() {
        return colour;
    }

    public void setColour(Paint colour) {
        this.colour = colour;
    }

    public Paint getBaseColour() {
        return baseColour;
    }

    public void setBaseColour(Paint baseColour) {
        this.baseColour = baseColour;
    }

    public Station getStationV() {
        return stationV;
    }

    public void setStationV(Station stationV) {
        this.stationV = stationV;
    }

    public Shape getNode() {
        return node;
    }

    public void setNode(Shape node) {
        this.node = node;
    }

    public Integer getRADIO() {
        return RADIO;
    }

    public void update() {
        this.node = new Ellipse2D.Double(coordX, coordY, RADIO, RADIO);
    }

    public String info(){
        return "[" + id + "] -" + this.name + ".";}

}
