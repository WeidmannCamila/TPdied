package main.java.DAO;

import com.sun.jdi.connect.Transport;
import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.classes.Constants;
import main.java.classes.Route;
import main.java.classes.Station;
import main.java.classes.TransportRoute;

import java.sql.*;
import java.util.ArrayList;


//agregar ruta con todos los valores
public class RouteDAO {

    StationDAO daoS = new StationDAO();

    public void addRoute(DTORoute dto) {
        Connection conexion = null;


        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.route (origin, destination, distance, duration, maxPassagers, status, cost) VALUES (? , ?, ?,?,?,?,?);");

            st.setString(1, dto.getOrigin().getName());
            st.setString(2, dto.getDestination().getName());
            st.setDouble(3, dto.getDistance());
            st.setDouble(4, dto.getDuration());
            st.setInt(5, dto.getMaxPassagers());
            st.setBoolean(6, dto.isStatus());
            st.setDouble(7, dto.getCost());

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
    }

    /*busca ruta
    public Route searchRoute(Station station, Station station1) {


        Route route= new Route();
        Connection conexion = null;
        ResultSet rs = null;


        try {
            conexion = DriverManager.getConnection( Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.route WHERE idStationOrigin= ? AND idStationDestination = ?;");
            st.setInt(1,station.getIdStation());
            st.setInt(2,station1.getIdStation());

            rs = st.executeQuery();
            TransportRoute transport = new TransportRoute();
            TransportDAO daoT = new TransportDAO();
            while(rs.next()){
                transport =  daoT.getTransport1(rs.getInt("idTransport"));

                route = new Route(rs.getInt(1), station, station1, rs.getDouble("distance"), rs.getDouble("duration"), rs.getDouble("cost"), transport, rs.getInt("maxPassagers"), rs.getBoolean("status"));
            //     rss.add(transport);
            }


            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(conexion!= null){
                try{
                    conexion.close();
                }catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }


        return route;
    }*/

    public ArrayList<Route> getRoutes() {
        ArrayList routes = new ArrayList();

        Connection conexion = null;
        ResultSet rs = null;


        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.route;" ) ;
            rs = st.executeQuery();
            TransportRoute transport = new TransportRoute();
            TransportDAO daoT = new TransportDAO();
            while(rs.next()) {

                Station start = new Station();
                Station end = new Station();

                transport =  daoT.getTransport1(rs.getInt("idTransport"));
                start =  daoS.getStation(rs.getInt("idStationOrigin"));

                end = daoS.getStation(rs.getInt("idStationDestination"));

                Route route = new Route(rs.getInt("idRoute"), start, end,  rs.getDouble("distance"), rs.getDouble("duration"), rs.getDouble("cost"), transport, rs.getInt("maxPassagers"), rs.getBoolean("status"));

                routes.add(route);
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
        return routes;
    }
}
