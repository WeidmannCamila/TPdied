package main.java.DAO;

import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.structures.BinarySearchTree;

import java.util.ArrayList;

public class StationDAO {

    public StationDAO() {
    }

    public void addStation(DTOStation S){
        System.out.println("añadir. holi");
    }

    public void deleteStation(DTOStation deleteS){
        System.out.println("eliminar. holi");
    }

    public void updateStation(DTOStation dto){
        System.out.println("actualizar. holi");
    }

    public ArrayList<DTOStation> getStations(){
        ArrayList l = new ArrayList();
        return l;
    }


    /*public BinarySearchTree<> getStation4name() {


    }
*/


}
