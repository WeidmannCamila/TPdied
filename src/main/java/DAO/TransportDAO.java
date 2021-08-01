package main.java.DAO;


import main.java.DTOs.DTOTransport;
import main.java.classes.TransportRoute;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class TransportDAO {
    public Color greenplus = new Color(22,70,32);
    public Color greenmenor = new Color(184,218,186);
    public TransportDAO() {
    }

    public void addTransport(DTOTransport T) {
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

    public static void deleteTransport(DTOTransport deleteT) {
        Connection conexion = null;
        ResultSet rs = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("DELETE FROM tp_died.transport_route WHERE idTransport = ? ;");
            st.setInt(1, deleteT.getIdTransport());
            st.executeUpdate();
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

    public void updateTransport(DTOTransport dto) {
        System.out.println("actualizar. holi");
    }


    public static ArrayList<DTOTransport> getTransports(DTOTransport t) {
        ArrayList l = new ArrayList();
        return l;
    }

    public ArrayList<TransportRoute> getTranport() {
        System.out.println("Entro al getTransport");
        ArrayList<TransportRoute> transportes = new ArrayList<>();
        Connection conexion = null;
        ResultSet resultado = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route;");
            resultado = st.executeQuery();
            System.out.println(resultado.toString());
            while (resultado.next()) {
                Paint c;
                c = colourTransport(resultado.getString("colour"));

                TransportRoute transport = new TransportRoute(resultado.getInt(1), resultado.getString(2), c, resultado.getBoolean(4));
                transportes.add(transport);
            }

            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }

        }
        return transportes;
    }

    public static ArrayList<DTOTransport> searchTransportByAtributte(DTOTransport t) {
        ArrayList<DTOTransport> transportes = new ArrayList<>();
        Connection conexion = null;
        ResultSet rs = null;

        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection( url, user, pass);
            //pregunto si el filtro es por id
            if(t.getIdTransport()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route WHERE idTransport= ?;");
                st.setInt(1,t.getIdTransport());
                rs = st.executeQuery();
            }
            //pregunto si el filtro es por nombre
            if(t.getName()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route WHERE name LIKE '%" + t.getName() + "%';");
                rs = st.executeQuery();
            }
            //pregunto si el filtro es por color
            if(t.getColour()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route WHERE colour LIKE '%\" + t.getName() + \"%';");
                rs = st.executeQuery();
            }
            //pregunto si el filtro es por status
            if(t.getColour()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route WHERE status= ?;");
                st.setString(1,t.getColour());
                rs = st.executeQuery();
            }

            while(rs.next()){
                DTOTransport trans1 = new DTOTransport(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
                transportes.add(trans1);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return transportes;
    }


    private Paint colourTransport(String colour) {
        Paint co;

        Color verde = new Color(100,149,237);
        Color verdecla = new Color(100,149,237);

        switch (colour){
            case "verdeAR":{
                co = Color.GREEN;
                break;
            }
            case "verdeAB" : {
                co = verdecla;
                break;
            }
            case "amarillo" : {
                co = Color.yellow;
                break;
            }
            case  "cyan": {
                co = Color.cyan;
                break;
            }
            case "rojo":{
                co =Color.red;
                break;
            }
            case "naranja": {
                co = Color.orange;
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + Color.BLUE);
        };

        return co;
    }


    //yo llamaba la funcion lista transporte q devolvia toda la lista y desp comparaba
    //es hacer llamadas alpedo, hacer para buscar un transporte en especifico.
    public TransportRoute getTransport1(int id) {
        TransportRoute transport = new TransportRoute();
        Connection conexion = null;
        ResultSet resultado = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route where idTransport = ? ;");
            st.setInt(1, id);
            resultado = st.executeQuery();
            while (resultado.next()) {
                Paint c;
                c = colourTransport(resultado.getString("colour"));

                transport = new TransportRoute(resultado.getInt(1), resultado.getString(2), c, resultado.getBoolean(4));

            }
            st.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }

        }
        return transport;
    }


}