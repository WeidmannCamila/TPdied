package main.java.DAO;

import main.java.DTOs.DTOMaintenance;
import main.java.DTOs.DTOStation;

import java.sql.*;
import java.util.ArrayList;

public class MaintenanceDAO {


    public static ArrayList<DTOMaintenance> getMaintenanceById(DTOStation estacionParametro) {
        System.out.println("llego al dao de maintenance");
        ArrayList maintenances = new ArrayList();
        Connection con = null;
        ResultSet resultado = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            con = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = con.prepareStatement("SELECT * FROM tp_died.maintenance WHERE idStation = ? ;");
            st.setInt(1, estacionParametro.getIdStation());
            st.executeQuery();

            while(resultado.next()){
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
