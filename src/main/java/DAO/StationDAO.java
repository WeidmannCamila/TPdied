package main.java.DAO;

import main.java.DTOs.DTOStation;
import main.java.classes.Constants;
import main.java.classes.ListGlobalStation;
import main.java.classes.Station;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StationDAO {

    public StationDAO() {
    }



    public static ArrayList<DTOStation> searchStationWithAtribute (DTOStation station){
        ArrayList<DTOStation> estaciones = new ArrayList<>();
        Connection conexion = null;
        ResultSet rs = null;


        try {
            conexion = DriverManager.getConnection( Constants.url, Constants.user, Constants.pass);
            //pregunto si el filtro es por id
            if(station.getIdStation()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT idStation,nameStation, status, openingTime, closingTime FROM tp_died.station WHERE idStation= ?;");
                st.setInt(1,station.getIdStation());

                rs = st.executeQuery();
            }else
                //pregunto si el filtro es por nombre
                if(station.getName()!=null) {
                    PreparedStatement st = conexion.prepareStatement("SELECT idStation,nameStation, status, openingTime, closingTime FROM tp_died.station WHERE nameStation LIKE '%" + station.getName() +"%';");

                    rs = st.executeQuery();
                }else
                    //pregunto si el filtro es por status
                    if(station.getStatus()!=null) {
                        PreparedStatement st = conexion.prepareStatement("SELECT idStation,nameStation, status, openingTime, closingTime FROM tp_died.station WHERE status LIKE '%" + station.getStatus() +"%';");

                        rs = st.executeQuery();
                    }else
                        if(station.getOpen()!= null){

                            PreparedStatement st = conexion.prepareStatement("SELECT idStation,nameStation, status, openingTime, closingTime FROM tp_died.station WHERE openingTime = '" +station.getOpen()+"';");

                            rs = st.executeQuery();
                        }



            while(rs.next()){
               DTOStation station1 = new DTOStation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                estaciones.add(station1);
            }
            rs.close();
            System.out.println("La longitud de estaciones es: "+ estaciones.size());

            //no trae ninguna estacion ver esto

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(conexion!= null){
                try{
                    conexion.close();
                }catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }
        return estaciones;

    }

    public static void deleteStation(DTOStation deleteS){
        Connection con = null;
        ResultSet rs = null;

        System.out.println("ID EN DAO DE ESTACION " + deleteS.getIdStation());
        try{
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = con.prepareStatement("DELETE FROM tp_died.station WHERE idStation = ? ;");
            st.setInt(1, deleteS.getIdStation());

            int i =st.executeUpdate();
            System.out.println("lo q se edito " + i);

            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            if(con!= null){
                try{
                    con.close();
                }catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }
    }
    /*
    public String createDate (String h, String m){

    }
*/
    public void updateStation(DTOStation dto){
        Connection con = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            Statement updateId = con.createStatement();

            updateId.executeUpdate("UPDATE tp_died.station SET nameStation = '" + dto.getName() + "' WHERE idStation = " + dto.getIdStation() + ";");
            updateId.executeUpdate("UPDATE tp_died.station SET status = '" + dto.getStatus() + "' WHERE idStation = " + dto.getIdStation() + ";");
            updateId.executeUpdate("UPDATE tp_died.station SET openingTime = '" + dto.getOpen() + "' WHERE idStation = " + dto.getIdStation() + ";");
            updateId.executeUpdate("UPDATE tp_died.station SET closingTime = '" + dto.getClosed() + "' WHERE idStation = " + dto.getIdStation() + ";");

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
    //obtiene todas las estaciones de la BD
    public static ArrayList<Station> getStations(){
       // System.out.println("llego a dao station");
        ArrayList stations = new ArrayList();

        Connection conexion = null;
        ResultSet rs = null;


        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station;");
            rs = st.executeQuery();

            while(rs.next()) {
                Station station = new Station(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString("closingTime"), rs.getString("status"), null);
                stations.add(station);
            }

            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(conexion != null){
                try{
                    conexion.close();
                }
                catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }
        return stations;
    }


    public void addStation (DTOStation s) {
        Connection conexion = null;


        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.station (nameStation, openingTime, closingTime, status ) VALUES ( ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, s.getName());
            st.setString(2, s.getOpen());
            st.setString(3, s.getClosed());
            st.setString(4, s.getStatus());
            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    s.setIdStation(generatedKeys.getInt(1));
                    System.out.println("llega a guardar el id " + s.getIdStation());
                    ListGlobalStation ls = ListGlobalStation.getInstance();
                    Station s1 = new Station(s.getIdStation(), s.getName(), s.getStatus());
                    ls.addStation(s1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }


               // st.executeUpdate();
                st.close();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                if (conexion != null) {
                    try {
                        conexion.close();
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

        //funciona para la lista global
    public static HashMap<Integer, Station> getStationsV(){

        HashMap<Integer, Station> stations = new HashMap<Integer, Station>();

        Connection conexion = null;
        ResultSet rs = null;

        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station;" ) ;
            rs = st.executeQuery();

            while(rs.next()) {
                Station station = new Station(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString("closingTime"), rs.getString("status"), null);
                stations.put(station.getIdStation(), station);
            }

            st.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if(conexion != null){
                try{
                    conexion.close();
                }
                catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }
        return stations;
    }




    public Station getStation(int id) {
        ArrayList<Station> lista = this.getStations();
        Station resultado = null;
        Iterator var5 = lista.iterator();

        while(var5.hasNext()) {
            Station a = (Station) var5.next();
            if (a.getIdStation() == id) {
                resultado = a;
            }
        }

        return resultado;
    }

    public Station getStationString(String s) {
        ArrayList<Station> lista = this.getStations();

        Station resultado = null;
        Iterator var5 = lista.iterator();

        while(var5.hasNext()) {
            Station a = (Station) var5.next();

            if (a.getName().equals(s)) {
                resultado = a;
            }
        }

        return resultado;
    }
}
