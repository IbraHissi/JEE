package bachmek.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bachmek.model.Employe;
public class loginDAO {
		private static Connection connection;
		static {
			String url= "jdbc:mysql://localhost:3306/myproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			String user="root";
			String password="Ibrahim01";
				
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				 
				connection=DriverManager.getConnection(url, user, password);
				System.out.println("connexion établie avec BD !!! ");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace(); 
			}	
		}
		public static Connection getConnection() {
			return connection;
		}
		public Employe getEmploye(String email , String mdps) {
			Employe employe =new Employe();
			try {
				PreparedStatement pt=connection.prepareStatement("Select * from employe where email=? and mdps=?");
				pt.setString(1,email);
				pt.setString(2,mdps);
				ResultSet rs =pt.executeQuery();
				if(rs.next()) {
					employe.setEmail(rs.getString("email"));
					employe.setRole(rs.getString("role"));
				}
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			return employe;
		}
    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}