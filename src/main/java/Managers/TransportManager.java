package main.java.Managers;

import com.sun.jdi.connect.Transport;
import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.classes.*;

import java.util.ArrayList;

public class TransportManager {

    private TransportDAO tDAO = new TransportDAO();
    private static final TransportManager INSTANCE = new TransportManager();
    private ArrayList<TransportRoute> listTransport = new ArrayList<TransportRoute>();
    private RouteManager rm = new RouteManager();

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
    public ArrayList<DTOTransport> getTransports(){
        ArrayList<DTOTransport> listresult = new ArrayList<>();
        System.out.println("SIEZE " + this.getListTransport().size());

        for(TransportRoute t : this.getListTransport()){
            DTOTransport d = new DTOTransport(t.getIdTransport(), t.getName(), t.getColor(t.getColour()) ,t.isStatus());
            listresult.add(d);
        }
        return listresult;
    }


    //buscar transportes solo para DTOTransport
    public ArrayList<DTOTransport> searchDTOTransport(DTOTransport t) {

        ArrayList<DTOTransport> list = new ArrayList(tDAO.searchTransportByAtributte(t));

        return list;
    }

    public void updateTransport(DTOTransport dto) {
        TransportRoute t = this.getTransport(dto.getIdTransport());
        t.setIdTransport(dto.getIdTransport());
        t.setStatus(dto.getStatus());
        t.setName(dto.getName());
        t.setColour(t.getColour());

        ListGlobalTransport lgc = ListGlobalTransport.getInstance();
        lgc.editTransport(t);

        tDAO.updateTransport(dto);
    }


    public void deleteTransportRoute(DTOTransport t){
        ArrayList<Route> aux = rm.getListRoutes();

        for(int i= 0; i<aux.size();i++  ){
            if(aux.get(i).getTransport().getIdTransport().equals(t.getIdTransport())){
                System.out.println("encuentra una ruta " + aux.get(i));
             rm.deleteRoute(aux.get(i));

            }
        }
        ListGlobalTransport lt = ListGlobalTransport.getInstance();
        lt.deleteTransport(t);

        tDAO.deleteTransport(t);
    }

    public void addTransport(DTOTransport dto) {

        System.out.println("PASA POR ADDTRANSPORT");
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
