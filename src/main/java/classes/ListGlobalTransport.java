package main.java.classes;

import main.java.Managers.TransportManager;

public class ListGlobalTransport {
    private static ListGlobalTransport listT;

    public ListGlobalTransport(Object searchTransport) {
    }

    public static ListGlobalTransport getInstance() {
        if (listT==null) {
            TransportManager tm = new TransportManager();
            listT = new ListGlobalTransport(tm.searchTransport(null, "", "", ""));
        }
        return listT;
    }
}
