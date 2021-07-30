package main.java.Managers;

import com.sun.jdi.connect.Transport;
import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;

import java.util.ArrayList;

public class TransportManager {
    private static TransportManager instan = null;
    private TransportDAO tDAO = new TransportDAO();

    public TransportManager(){}


    public ArrayList<Transport> searchTransport(Integer s, String s1, String s2, Boolean s3) {

        DTOTransport t = new DTOTransport(s,s1,s2,s3);

        ArrayList<Transport> list = new ArrayList(TransportDAO.getTransports(t));

        return list;
    }

    public static void deleteTransportRoute(DTOTransport t){
        TransportDAO.deleteTransport(t);
    }

    public void addTransport(DTOTransport dto) {
        tDAO.addTransport(dto);
    }
}
