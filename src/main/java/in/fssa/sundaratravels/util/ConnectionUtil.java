package in.fssa.sundaratravels.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionUtil {

    public static Connection getConnection() {

        String url;
        String username;
        String password;


        url = System.getenv("DATABASE_HOSTNAME");
        username = System.getenv("DATABASE_USERNAME");
        password = System.getenv("DATABASE_PASSWORD");

        //if(url == null)
        url = "jdbc:mysql://164.52.216.41:3306/elayaraman_ramalingam_corejava_project" ;

        //if(username == null)
        username = "cIeKvqxHC9lX" ;

        //if(password == null)
        password = "c3fe8bfb-9dae-4aaa-a8ce-54f39d812a60" ;

//        url = "jdbc:mysql://127.0.0.1:3306/sundaratravels";
//        username = "root";
//        password = "Elaya@007";

//        url = env.get("DATABASE_HOSTNAME");
//        username = env.get("DATABASE_USERNAME");
//        password = env.get("DATABASE_PASSWORD");

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
