package main.java.DAO;

import main.java.DTOs.DTOStation;
import main.java.Enumeration.EnumStatus;
import main.java.classes.Constants;
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
                System.out.println("Entro al if del getId");
                rs = st.executeQuery();
            }else
                //pregunto si el filtro es por nombre
                if(station.getName()!=null) {
                    PreparedStatement st = conexion.prepareStatement("SELECT idStation,nameStation, status, openingTime, closingTime FROM tp_died.station WHERE nameStation LIKE '%" + station.getName() +"%';");
                    System.out.println("Entro al if del getName");
                    rs = st.executeQuery();
                }else
                    //pregunto si el filtro es por status
                    if(station.getStatus()!=null) {
                        PreparedStatement st = conexion.prepareStatement("SELECT idStation,nameStation, status, openingTime, closingTime FROM tp_died.station WHERE status LIKE '%" + station.getStatus() +"%';");
                        System.out.println("Entro al if del getStatus");
                        rs = st.executeQuery();
                    }else
                        if(station.getOpen()!= null){
                            System.out.println("la hora es : "+ station.getOpen());
                            PreparedStatement st = conexion.prepareStatement("SELECT idStation,nameStation, status, openingTime, closingTime FROM tp_died.station WHERE openingTime = '" +station.getOpen()+"';");

                            System.out.println("Entro al if del getOpen");
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


        try{
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = con.prepareStatement("DELETE FROM tp_died.station WHERE idStation = ? ;");
            st.setInt(1, deleteS.getIdStation());
            st.executeUpdate();
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
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station;" ) ;
            rs = st.executeQuery();

            while(rs.next()) {
                EnumStatus e = EnumStatus.valueOf(rs.getString("status"));

                Station station = new Station(rs.getInt(1), rs.getString(2), rs.getString(3), null, e, null);

                stations.add(station);
            }


            st.executeUpdate();
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


    public void addStation (DTOStation s){
        Connection conexion = null;


        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.station (idStation, name, openingTime, closingTime, status ) VALUES (?, ?, ?, ?, ?);" ) ;
            st.setInt(1, s.getIdStation());
            st.setString(2, s.getName());
            st.setString(3, s.getOpen());
            st.setString(4, s.getClouse());
            st.setBoolean(5, false /*s.getStatus()*/);
            st.executeUpdate();
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
                Station station = new Station(rs.getInt(1), rs.getString(2), rs.getString(3), null, null, null);
                stations.put(station.getIdStation(), station);
            }


            st.executeUpdate();
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
