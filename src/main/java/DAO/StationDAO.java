package main.java.DAO;

import main.java.DTOs.DTOStation;
import main.java.classes.Station;

import java.sql.*;
import java.util.ArrayList;

public class StationDAO {

    public StationDAO() {
    }

    public static ArrayList<DTOStation> searchStation(DTOStation station) {
        ArrayList<DTOStation> stations = new ArrayList<>();
        Connection conexion = null;
        ResultSet rs = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            //String consulta = ArmarConsultaBuscar(station);
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station;");
            rs = st.executeQuery();

            while(rs.next()) {

                DTOStation station1 = new DTOStation(rs.getInt(1), rs.getString(2), rs.getTime(3), rs.getTime(4), rs.getString(5));
                stations.add(station1);

            }

            st.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        }

        return stations;
    }

    public static ArrayList<DTOStation> searchStationWithAtribute (DTOStation station){
        ArrayList<DTOStation> estaciones = new ArrayList<>();
        Connection conexion = null;
        ResultSet rs = null;

        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection( url, user, pass);
            //pregunto si el filtro es por id
            if(station.getIdStation()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station WHERE idStation= ?;");
                st.setInt(1,station.getIdStation());
                System.out.println("Entro al if del getId");
                rs = st.executeQuery();
            }
            //pregunto si el filtro es por nombre
            if(station.getName()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station WHERE name= ?;");
                st.setString(1,station.getName());
                System.out.println("Entro al if del getName");
                rs = st.executeQuery();
            }
            //pregunto si el filtro es por status
            if(station.getStatus()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station WHERE status= ?;");
                st.setString(1,station.getStatus());
                System.out.println("Entro al if del getStatus");
                rs = st.executeQuery();
            }

            while(rs.next()){
               DTOStation station1 = new DTOStation(rs.getInt(1), rs.getString(2), rs.getTime(3), rs.getTime(4), rs.getString(5));
                estaciones.add(station1);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return estaciones;

    }



    public void deleteStation(DTOStation deleteS){
        System.out.println("eliminar. holi");
    }

    public void updateStation(DTOStation dto){
        //buscar la estacion con id luego hacer un update


        System.out.println("actualizar. holi");
    }
    //obtiene todas las estaciones de la BD
    public static ArrayList<DTOStation> getStations(){
        System.out.println("llego a dao sataion");
        ArrayList stations = new ArrayList();

        Connection conexion = null;
        ResultSet rs = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.station;" ) ;
            rs = st.executeQuery();

            while(rs.next()) {
                Station station = new Station(rs.getInt(1), rs.getString(2), rs.getDate(3), null, null, null);
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
    //obtener una estacion por el id
    /*
    public ArrayList<DTOStation> getStationById(Integer id){
        return ;
    }
    */

    /*public BinarySearchTree<> getStation4name() {

    }
*/
    public void addStation (DTOStation s){
        Connection conexion = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.station (idStation, name, openingTime, closingTime, status ) VALUES (?, ?, ?, ?, ?);" ) ;
            st.setInt(1, s.getIdStation());
            st.setString(2, s.getName());
            st.setTime(3, s.getOpen());
            st.setTime(4, s.getClouse());
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

}
