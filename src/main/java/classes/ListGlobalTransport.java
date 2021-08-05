package main.java.classes;

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


    @Override
    public int compareTo(ListGlobalTransport o) {
        return 0;
    }
}
