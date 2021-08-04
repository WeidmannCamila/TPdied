package main.java.Vista;

import main.java.DTOs.DTOStation;
import main.java.classes.Station;

import java.util.ArrayList;
import java.util.List;

public class ViewPageRank {

    private ViewVertex vertice;
    private List<ViewVertex> nodesIn;
    private Double pageRank;
    private Double nodesOut;


    public ViewPageRank() {

    }

    public ViewVertex getVertice() {
        return vertice;
    }

    public void setVertice(ViewVertex vertice) {
        this.vertice = vertice;
    }

    public List<ViewVertex> getNodesIn() {
        return nodesIn;
    }

    public void setNodesIn(List<ViewVertex> nodesIn) {
        this.nodesIn = nodesIn;
    }

    public Double getPageRank() {
        return pageRank;
    }

    public void setPageRank(Double pageRank) {
        this.pageRank = pageRank;
    }

    public Double getNodesOut() {
        return nodesOut;
    }

    public void setNodesOut(Double nodesOut) {
        this.nodesOut = nodesOut;
    }
}
