

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionJDBC {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = (Connection) DriverManager.getConnection(("jdbc:mysql://localhost/comptebanquedb"), "root", "root");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
			System.out.println("error de connection");
		}
		return conn;
	}
	
    public static ResultSet getResults(String request) {
        ResultSet res = null;
        try {
            res = getConnection().createStatement().executeQuery(request);
        } catch (SQLException ex) {
        	System.out.println("error get result set");
        }
        return res;
    }

    public static int update(String request) {
        int res = -1;
        try {
            res = getConnection().createStatement().executeUpdate(request);
        } catch (SQLException ex) {
        	System.out.println("error de mise à jour");
        }
        return res;
    }
	
}
