package main.java.DAO;

import main.java.classes.ListRoute;

import java.sql.*;

public class TicketDAO {
    public TicketDAO() {
    }


    public void addTicket(ListRoute listRoute, String nameuser, String email, Timestamp date) {
        Connection conexion = null;
        final String url = "jdbc:postgresql://tuffi.db.elephantsql.com:5432/hshhreor";
        final String user = "hshhreor";
        final String pass = "x1oNEbdlMN1CrjfidEjVPBuhK9kVEyE4";

        try {
            conexion = DriverManager.getConnection(url, user, pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.ticket (name , emailClient , dateTicket, nameOriginStation, nameDestinationStation, cost ) VALUES (? , ?, ?,?);");
            st.setString(1, nameuser);
            st.setString(2, email);
            st.setTimestamp(3, date);
            st.setString(4, listRoute.getOrigin().getIdStation().toString());
            st.setString(5, listRoute.getDestination().getIdStation().toString());
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

}
