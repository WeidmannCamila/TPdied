package main.java.DAO;

import main.java.DTOs.DTORoute;
import main.java.DTOs.DTOStation;
import main.java.classes.Route;
import main.java.classes.Station;

import java.sql.*;


//agregar ruta con todos los valores
public class RouteDAO {
    public void addRoute(DTORoute dto) {
    }

    //busca ruta
    public Route searchRoute(Station station, Station station1) {
        Route route= new Route();
    /*    Connection conexion = null;
        ResultSet rs = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";


        try {
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
*/  return route;
    }
}
