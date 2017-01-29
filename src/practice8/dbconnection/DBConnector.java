package practice8.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/practice8?useSSL=false";
			String user = "user";
			String pass = "pass";
			return DriverManager.getConnection(url, user, pass);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
