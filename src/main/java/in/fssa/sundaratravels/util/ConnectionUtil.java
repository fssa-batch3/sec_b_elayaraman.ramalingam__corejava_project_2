package in.fssa.sundaratravels.util;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() {

		Dotenv dotenv = Dotenv.load();

		String url = null;
		String username = null;
		String password = null;

		 url = System.getenv("DATABASE_HOSTNAME");
		 username = System.getenv("DATABASE_USERNAME");
		 password = System.getenv("DATABASE_PASSWORD");

		if(url == null)
			url = System.getenv("LOCAL_DATABASE_HOSTNAME");

		if(username == null)
			username = System.getenv("LOCAL_DATABASE_USERNAME");

		if(password == null)
			password = System.getenv("LOCAL_DATABASE_PASSWORD");

		if(url == null)
			url = dotenv.get("DATABASE_HOSTNAME");

		if(username == null)
		username = dotenv.get("DATABASE_USERNAME");

		if(password == null)
		password = dotenv.get("DATABASE_PASSWORD");




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
