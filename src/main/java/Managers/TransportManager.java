package main.java.Managers;

import com.sun.jdi.connect.Transport;
import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.classes.*;

import java.util.ArrayList;

public class TransportManager {
    private static TransportManager instan = null;
    private TransportDAO tDAO = new TransportDAO();
    private static final TransportManager INSTANCE = new TransportManager();
    private ArrayList<TransportRoute> listTransport = new ArrayList<TransportRoute>();

    public TransportManager(){}

    public static TransportManager getInstance(){ return INSTANCE;}

    // funciona para la lista global de trasportes
    public ArrayList<TransportRoute> getListTransportFromDao() {
        this.listTransport = tDAO.getTranport();
        return listTransport;

    }
    public ArrayList<TransportRoute> getListTransport(){
        ListGlobalTransport tl = ListGlobalTransport.getInstance();


        ArrayList<TransportRoute> l = tl.getList();

        return l;
    }


    //buscar transportes solo para DTOTransport
    public ArrayList<DTOTransport> searchDTOTransport(DTOTransport t) {

        ArrayList<DTOTransport> list = new ArrayList(tDAO.searchTransportByAtributte(t));

        return list;
    }

    public void updateTransport(DTOTransport dto) {
        tDAO.updateTransport(dto);
    }


    public static void deleteTransportRoute(DTOTransport t){
        TransportDAO.deleteTransport(t);
    }

    public void addTransport(DTOTransport dto) {
        tDAO.addTransport(dto);
    }

    //buscar estacion por nombre o id
    public TransportRoute getTransport(String s) {
        for (TransportRoute s1 : this.getListTransport()) {
            if (s1.getName().equals(s)) {
                return s1;
            }

        }
        return null;
    }

    public TransportRoute getTransport(int s) {
        for (TransportRoute s1 : this.getListTransport()) {
            if (s1.getIdTransport().equals(s)) {
                return s1;
            }

        }
        return null;


    }

}
