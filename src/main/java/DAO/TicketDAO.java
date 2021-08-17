package main.java.DAO;

import main.java.classes.Constants;
import main.java.classes.ListGlobalStation;
import main.java.classes.ListRoute;
import main.java.classes.Station;

import java.sql.*;
import java.time.LocalDate;

public class TicketDAO {
    public TicketDAO() {
    }


    public int addTicket(ListRoute listRoute, String nameuser, String email, LocalDate date) {
        Connection conexion = null;
        int id = 0;

        try {
            conexion = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
            PreparedStatement st = conexion.prepareStatement("INSERT INTO tp_died.ticket (name , emailClient , dateTicket, nameOriginStation, nameDestinationStation, cost ) VALUES (? , ?, ?,?,?,?);", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, nameuser);
            st.setString(2, email);
            st.setDate(3, Date.valueOf(date));
            st.setString(4, listRoute.getOrigin().getIdStation().toString());
            st.setString(5, listRoute.getDestination().getIdStation().toString());
            st.setDouble(6, listRoute.getTotalCost());
            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {

                    id = generatedKeys.getInt(1);
                    System.out.println("id tiii" + id);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            
            
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
        return id;
    }
}
