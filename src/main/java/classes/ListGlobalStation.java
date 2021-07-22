package main.java.classes;

import main.java.Managers.StationManager;
import main.java.Managers.TransportManager;

public class ListGlobalStation {



    private static ListGlobalStation listS;

    public ListGlobalStation(Object searchTransport) {
    }

    public static ListGlobalStation getInstance() {
        if (listS==null) {
            StationManager sm = new StationManager();
            listS = new ListGlobalStation(sm.searchStation(null, "", "", ""));
        }
        return listS;
    }
}
