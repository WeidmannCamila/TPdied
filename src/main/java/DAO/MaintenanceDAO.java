package main.java.DAO;

import main.java.DTOs.DTOMaintenance;
import main.java.classes.Constants;

import java.sql.*;
import java.util.ArrayList;

public class MaintenanceDAO {


    public static ArrayList<DTOMaintenance> getMaintenanceById(int estacionParametro) {
      //  System.out.println("llego al dao de maintenance");
        ArrayList<DTOMaintenance> maintenances = new ArrayList();
        Connection con = null;
        ResultSet resultado = null;


        try {
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);


            PreparedStatement st = con.prepareStatement("SELECT idMaintenance, description, startDate, endDate  FROM tp_died.maintenance WHERE idStation = ? ;");
            st.setInt(1, estacionParametro);
            resultado = st.executeQuery();

           //no va un while porque es solo un elemento
            while(resultado.next()) {
                DTOMaintenance m1 = new DTOMaintenance(resultado.getInt(1), resultado.getString(2), resultado.getTimestamp(3), resultado.getTimestamp(4));
                maintenances.add(m1);
            }
            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(con!= null){
                try{
                    con.close();
                }catch (Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }

        return maintenances;
    }
}
