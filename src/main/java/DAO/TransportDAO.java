package main.java.DAO;


import main.java.DTOs.DTOTransport;
import main.java.classes.Station;
import main.java.classes.TransportRoute;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;

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
                System.out.println("Color del trasporte" + c.toString());
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

    private Paint colourTransport(String colour) {
        Paint co;
        System.out.println("ENTRO A COLOR " + colour);
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
                System.out.println("ENTRO A COLOR gettrasport" + c);
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