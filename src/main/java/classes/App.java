package main.java.classes;


import main.java.Vista.GrafoPanel;
import main.java.Vista.HomeGUI;

import javax.swing.*;
import java.awt.*;

public class App {




    public static void main(String[] args){
        GrafoPanel grafoPanel = GrafoPanel.getInstance();;
        ListGlobalTransport listT = ListGlobalTransport.getInstance();
        ListGlobalStation listS = ListGlobalStation.getInstance();
        ListGlobalRoute listR = ListGlobalRoute.getInstance();
        grafoPanel.initVertex(ListGlobalStation.getInstance().getList());

        grafoPanel.initArista(ListGlobalRoute.getInstance().getList());


        // Color myBlue = new Color(25,25,112);
       // final Constant Color myBlue = new Color(25,25,112);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {

                    HomeGUI win = new HomeGUI();
                    win.framePrincipal.setLocationRelativeTo((Component)null);
                    win.framePrincipal.setVisible(true);
                } catch (Exception var2){
                    var2.printStackTrace();
                }
            }
        });

    }


}
