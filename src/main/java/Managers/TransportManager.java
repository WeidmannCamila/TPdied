package main.java.Managers;

import com.sun.jdi.connect.Transport;
import main.java.DAO.TransportDAO;
import main.java.DTOs.DTOTransport;

import java.util.ArrayList;

public class TransportManager {
    private static TransportManager instan = null;


    public TransportManager(){}


    public ArrayList<Transport> searchTransport(Integer s, String s1, String s2, Boolean s3) {

        DTOTransport t = new DTOTransport(s,s1,s2,s3);

        ArrayList<Transport> list = new ArrayList(TransportDAO.getTransports(t));

        return list;
    }
}
