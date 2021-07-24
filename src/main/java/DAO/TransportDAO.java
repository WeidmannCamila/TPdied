package main.java.DAO;


import main.java.DTOs.DTOTransport;
import main.java.classes.TransportRoute;

import java.sql.*;
import java.util.ArrayList;

public class TransportDAO {

    public TransportDAO() {
    }

    public void addTransport(DTOTransport T){
        System.out.println("añadir. holi");


    }

    public void deleteTransport(DTOTransport deleteT){
        System.out.println("eliminar. holi");
    }

    public void updateTransport(DTOTransport dto){
        System.out.println("actualizar. holi");
    }

    public static ArrayList<DTOTransport> getTransports(DTOTransport t){
        ArrayList l = new ArrayList();
        return l;
    }
    public ArrayList<TransportRoute> getTranport() {
        System.out.println("Entro al getTransport");
        ArrayList<TransportRoute> transportes = new ArrayList<>();
        Connection conexion = null;
        ResultSet resultado= null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";
        try{
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport;");
            resultado = st.executeQuery();
            while(resultado.next()){
                TransportRoute transport = new TransportRoute(resultado.getInt(1),resultado.getString(2),resultado.getString(3), resultado.getBoolean(4));
                transportes.add(transport);
            }
            st.executeUpdate();
            st.close();

        }catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {

        }
        return transportes;
        }
    }