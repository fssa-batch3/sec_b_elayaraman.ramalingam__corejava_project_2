package in.fssa.sundaratravels.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionUtil {

    public static Connection getConnection() {

        String url = null;
        String username = null;
        String password = null;


        //url = System.getenv("DATABASE_HOSTNAME");
        //username = System.getenv("DATABASE_USERNAME");
        //password = System.getenv("DATABASE_PASSWORD");


        if(url == null) {
			url = "jdbc:mysql://127.0.0.1:3306/sundaratravels";
        }

        
        if(username == null) {
   	 	username = "root";
        }

        if(password == null) {
  		password = "Elaya@007";
        }


        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return connection;

    }

    public static void close(Connection connection, PreparedStatement ps) {

        try {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(Connection connection, PreparedStatement ps, ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
