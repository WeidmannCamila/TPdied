package main.java.DAO;


import main.java.DTOs.DTOTransport;
import main.java.classes.TransportRoute;

import java.sql.*;
import java.util.ArrayList;

public class TransportDAO {

    public TransportDAO() {
    }

    public void addTransport(DTOTransport T){
        Connection conexion = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";
        System.out.println("entro a dao pero no al try");
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.transport_route (idTransport, name, colour, status) VALUES (? , ?, ?,?);");
            st.setInt(1, T.getIdTransport());
            st.setString(2, T.getName());
            st.setString(3, T.getColour());
            st.setBoolean(4, T.getStatus());
            st.executeUpdate();
            st.close();
            System.out.println("paso el close");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(conexion!= null){
                try{
                    conexion.close();
                }catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }


    }

    public static void deleteTransport(DTOTransport deleteT){
        Connection conexion = null;
        ResultSet rs = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";
        
        try{
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("DELETE FROM tp_died.transport_route WHERE idTransport = ? ;");
            st.setInt(1, deleteT.getIdTransport());
            st.executeUpdate();
            st.close();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally{
            if(conexion!= null){
                try{
                    conexion.close();
                }catch(Exception e1){
                    System.out.println(e1.getMessage());
                }
            }
        }
        
        
    }

    public void updateTransport(DTOTransport dto){
        System.out.println("actualizar. holi");
    }

    public static ArrayList<DTOTransport> getTransports(DTOTransport t){
        ArrayList l = new ArrayList();
        return l;
    }
    public ArrayList<DTOTransport> getTranport() {
        System.out.println("Entro al getTransport");
        ArrayList<DTOTransport> transportes = new ArrayList<>();
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
                DTOTransport transport = new DTOTransport(resultado.getInt(1),resultado.getString(2),resultado.getString(3), resultado.getBoolean(4));
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