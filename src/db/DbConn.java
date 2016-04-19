package db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public final class DbConn {
	public static Connection getconn() {
		Connection conn = null;
		String user = "root";
		String passwd = "123";
		String url = "jdbc:mysql://127.0.0.1:3306/shopping";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,passwd);
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
