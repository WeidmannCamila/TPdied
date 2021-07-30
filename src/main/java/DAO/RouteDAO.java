package main.java.DAO;

import com.sun.jdi.connect.Transport;
import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.classes.Route;
import main.java.classes.Station;
import main.java.classes.TransportRoute;

import java.sql.*;
import java.util.ArrayList;


//agregar ruta con todos los valores
public class RouteDAO {
    public void addRoute(DTORoute dto) {
    }

    //busca ruta
    public Route searchRoute(Station station, Station station1) {
        System.out.println("rutas start end search" + station.getIdStation() + " " + station1.getIdStation());

        Route route= new Route();
        Connection conexion = null;
        ResultSet rs = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection( url, user, pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.route WHERE idStationOrigin= ? AND idStationDestination = ?;");
            st.setInt(1,station.getIdStation());
            st.setInt(2,station1.getIdStation());

            rs = st.executeQuery();

            while(rs.next()){
                TransportRoute transport = new TransportRoute();

                TransportDAO daoT = new TransportDAO();
                transport =  daoT.getTransport(rs.getInt("idTransport"));
                System.out.println("Id del transporte de la ruta" +  rs.getInt("idTransport"));
                route = new Route(rs.getInt(1), station, station1, rs.getDouble("distance"), rs.getInt("duration"), rs.getInt("cost"), transport);
            //     rss.add(transport);
            }

            System.out.println("Encontro ruta" + route.getIdRoute());

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
    }

    public ArrayList<Route> getRoutes() {
        ArrayList routes = new ArrayList();

        Connection conexion = null;
        ResultSet rs = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.route;" ) ;
            rs = st.executeQuery();

            while(rs.next()) {

                System.out.println("entra a getRoute dao ");
                Station start = new Station();
                Station end = new Station();
                StationDAO daoS = new StationDAO();
                start =  daoS.getStation(rs.getInt("idStationOrigin"));

                end = daoS.getStation(rs.getInt("idStationDestination"));

                Route route = new Route(rs.getInt("idRoute"), start, end);
                routes.add(route);
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
        return routes;
    }
}
