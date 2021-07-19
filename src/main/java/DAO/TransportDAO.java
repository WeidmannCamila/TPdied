package main.java.DAO;

import main.java.DTOs.DTOTransport;

import java.util.ArrayList;

public class TransportDAO {

    public TransportDAO() {
    }

    public void addTransport(DTOTransport T){
        System.out.println("añadir. holi");
    }

    public void deleteTransport(DTOTransport deleteT){
        System.out.println("eliminar. holi");
    }

    public void updateTransport(DTOTransport dto){
        System.out.println("actualizar. holi");
    }

    public ArrayList<DTOTransport> getTransports(){
        ArrayList l = new ArrayList();
        return l;
    }


}
