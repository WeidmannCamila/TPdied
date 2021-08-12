package main.java.DAO;


import main.java.DTOs.DTOStation;
import main.java.DTOs.DTOTransport;
import main.java.classes.*;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransportDAO {
    public Color greenplus = new Color(22,70,32);
    public Color greenmenor = new Color(184,218,186);
    public TransportDAO() {
    }



    public void addTransport(DTOTransport t) {
        Connection conexion = null;


        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.transport_route ( name, colour, status) VALUES (? , ?, ?);", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, t.getName());
            st.setString(2, t.getColour().toString());
            st.setBoolean(3, t.getStatus());

            int affectedRows = st.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    System.out.println("PASA POR ADDTRANSPORT DENTRO DEL IF");
                    t.setIdTransport(generatedKeys.getInt(1));
                    Paint c;
                    c = colourTransport(t.getColour());

                    ListGlobalTransport lgc = ListGlobalTransport.getInstance();
                    TransportRoute t1 = new TransportRoute(t.getIdTransport(), t.getName(),c , t.getStatus());
                    lgc.addTransport(t1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }


               // st.executeUpdate();
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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public void deleteTransport(DTOTransport deleteT) {
        System.out.println("LLEGO A DAO TRANPORTE DELECE");
        Connection conexion = null;
        ResultSet rs = null;


        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("DELETE FROM tp_died.transport_route WHERE idTransport = ? ;");
            st.setInt(1, deleteT.getIdTransport());
            st.executeUpdate();

            ListGlobalTransport lgc = ListGlobalTransport.getInstance();
            lgc.deleteTransport(deleteT);
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
        Connection con = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            Statement updateId = con.createStatement();
            System.out.println("ESTADO" + dto.getStatus());
            updateId.executeUpdate("UPDATE tp_died.transport_route SET name = '" + dto.getName() + "' WHERE idTransport = " + dto.getIdTransport() + ";");
            updateId.executeUpdate("UPDATE tp_died.transport_route SET colour = '" + dto.getColour() + "' WHERE idTransport = " + dto.getIdTransport() + ";");
            updateId.executeUpdate("UPDATE tp_died.transport_route SET status = '" + dto.getStatus() + "' WHERE idTransport = " + dto.getIdTransport() + ";");




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




    public ArrayList<TransportRoute> getTranport() {

        ArrayList<TransportRoute> transportes = new ArrayList<>();
        Connection conexion = null;
        ResultSet resultado = null;

        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
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

    public ArrayList<DTOTransport> searchTransportByAtributte(DTOTransport t) {
        ArrayList<DTOTransport> transportes = new ArrayList<>();
        Connection conexion = null;
        ResultSet rs = null;

        try {
            conexion = DriverManager.getConnection( Constants.url, Constants.user, Constants.pass);
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
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route WHERE colour LIKE '%" + t.getColour() + "%';");
                rs = st.executeQuery();
            }

            //pregunto si el filtro es por status
            if(t.getStatus()!=null) {
                PreparedStatement st = conexion.prepareStatement("SELECT * FROM tp_died.transport_route WHERE status=" +t.getStatus()+" ;");
                rs = st.executeQuery();
            }

            while(rs.next()){
                Paint c;
                c = colourTransport(rs.getString("colour"));
                DTOTransport trans1 = new DTOTransport(rs.getInt(1), rs.getString(2), rs.getString("colour"), rs.getBoolean(4));
                transportes.add(trans1);
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
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

        Color verde = new Color(100,149,237);
       // Color verdecla = new Color(173, 237, 100);

        switch (colour){
            case "verdeAR":{
                co = Color.GREEN;
                break;
            }
            case "verdeAB" : {
                co = Constants.verdecla;
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
                co = Color.BLUE;
        };

        return co;
    }




    public TransportRoute getTransport(int id) {
        ListGlobalTransport lt = ListGlobalTransport.getInstance();
        ArrayList<TransportRoute> lista = lt.getList();
        TransportRoute resultado = null;
        Iterator var5 = lista.iterator();

        while(var5.hasNext()) {
            TransportRoute a = (TransportRoute) var5.next();
            if (a.getIdTransport() == id) {
                resultado = a;
            }
        }

        return resultado;
    }

}