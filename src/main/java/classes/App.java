package main.java.classes;


import main.java.Vista.HomeGUI;

import javax.swing.*;
import java.awt.*;
import main.java.classes.ListGlobalStation;

public class App {


    public static void main(String[] args){

        ListGlobalTransport listT = ListGlobalTransport.getInstance();
        ListGlobalStation listS = ListGlobalStation.getInstance();



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
