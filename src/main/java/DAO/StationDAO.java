package main.java.DAO;

import main.java.DTOs.DTOStation;

import java.sql.*;
import java.util.ArrayList;

public class StationDAO {

    public StationDAO() {
    }




    public void deleteStation(DTOStation deleteS){
        System.out.println("eliminar. holi");
    }

    public void updateStation(DTOStation dto){
        //buscar la estacion con id luego hacer un update


        System.out.println("actualizar. holi");
    }
    //obtiene todas las estaciones de la BD
    public ArrayList<DTOStation> getStations(){
        ArrayList l = new ArrayList();
        return l;
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
