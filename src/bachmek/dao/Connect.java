package bachmek.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
		private static String jdbcURL = "jdbc:mysql://localhost:3306/myproject?useSSL=false&serverTimezone=UTC";
	    private static String jdbcemail = "root";
	    private static String jdbcmdps = "Ibrahim01";
	    protected static Connection getConnection() {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection(jdbcURL, jdbcemail, jdbcmdps);
	            System.out.println("connexion établie avec BD !!! ");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    }
}
