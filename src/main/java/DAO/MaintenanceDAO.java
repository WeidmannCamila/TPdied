package main.java.DAO;

import main.java.classes.Constants;
import main.java.classes.ListGlobalStation;
import main.java.classes.Maintenance;
import main.java.classes.Station;

import java.sql.*;
import java.util.ArrayList;

public class MaintenanceDAO {


    public ArrayList<Maintenance> getMaintenanceById(int estacionParametro) {
      //  System.out.println("llego al dao de maintenance");
        ArrayList<Maintenance> maintenances = new ArrayList();
        Connection con = null;
        ResultSet resultado = null;


        try {
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);


            PreparedStatement st = con.prepareStatement("SELECT idMaintenance, description, startDate, endDate  FROM tp_died.maintenance WHERE idStation = ? ;");
            st.setInt(1, estacionParametro);
            resultado = st.executeQuery();

           //no va un while porque es solo un elemento
            while(resultado.next()) {
                Maintenance m1 = new Maintenance(resultado.getInt(1), resultado.getString(2), resultado.getTimestamp(3), resultado.getTimestamp(4));
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
    public void nuevoMantenimiento (Maintenance mant, Integer idStation) {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.maintenance (idstation, startDate, description ) VALUES (?, ?, ?);");

            st.setInt(1,idStation);
            st.setTimestamp(2, mant.getStartDate());
            st.setString(3, mant.getDescription());


            st.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }
        }
    }



    //METODO PARA OBTENER TODOS LOS MANTENIMIENTOS

    public ArrayList<Maintenance> getMaintenance() {
        //  System.out.println("llego al dao de maintenance");
        ArrayList<Maintenance> maintenances = new ArrayList();
        Connection con = null;
        ResultSet resultado = null;

        try {
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = con.prepareStatement("SELECT idMaintenance, description, startDate, endDate  FROM tp_died.maintenance;");
            resultado = st.executeQuery();

            //no va un while porque es solo un elemento
            while(resultado.next()) {
                Maintenance m1 = new Maintenance(resultado.getInt(1), resultado.getString(2), resultado.getTimestamp(3), resultado.getTimestamp(4));
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


    public void updateM(Maintenance mant) {
        Connection con = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            Statement updateId = con.createStatement();

            updateId.executeUpdate("UPDATE tp_died.maintenance SET endDate = '" + mant.getEndDate() + "' WHERE idMaintenance = " + mant.getIdMaintenance() + ";");


        } catch (Exception var12) {
            System.out.println(var12.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception var11) {
                    System.out.println(var11.getMessage());
                }
            }

        }
    }
}
