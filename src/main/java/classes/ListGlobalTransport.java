package main.java.classes;

import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.Managers.TransportManager;

import java.util.ArrayList;

public class ListGlobalTransport implements Comparable<ListGlobalTransport>{

    private static ListGlobalTransport listT;

    private ArrayList<TransportRoute> listsT;

    public ListGlobalTransport(ArrayList<TransportRoute> listsT) {
        this.listsT = listsT;
    }

    public static ListGlobalTransport getInstance() {
        if (listT==null) {
            TransportManager tm = new TransportManager();
            listT = new ListGlobalTransport(tm.getListTransportFromDao());
        }
        return listT;
    }

    public ArrayList<TransportRoute> getList() {

        return listsT;

    }

    public void deleteTransport(DTOTransport deleteT) {
        this.listsT.removeIf(t -> t.getIdTransport().equals(deleteT.getIdTransport()));

    }

    public void addTransport(TransportRoute t){
        this.listsT.add(t);
    }

    public void editTransport(TransportRoute t){

        for(TransportRoute t1 : this.listsT){
            if(t1.getIdTransport().equals(t.getIdTransport())){
                t1.setColour(t.getColour());
                t1.setName(t.getName());
                System.out.println("ESTADO DEL TRASPORTE ANTESSS" + t1.isStatus());
                t1.setStatus(t.isStatus());
                System.out.println("ESTADO DEL TRASPORTE DESPUESSS" + t1.isStatus());
            }
        }
    }

    @Override
    public int compareTo(ListGlobalTransport o) {
        return 0;
    }


}
