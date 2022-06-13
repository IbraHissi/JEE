package bachmek.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bachmek.model.Employe;
import bachmek.model.Retard;

public class RetardDAO {
	 private static String jdbcURL = "jdbc:mysql://localhost:3306/myproject?useSSL=false&serverTimezone=UTC";
	 
	 
	 //
	    private static String jdbcemail = "root";
	    private static String jdbcmdps = "Ibrahim01";
	    private static final String INSERT_Retards_SQL = "INSERT INTO retard" + "  (dateretard,idemploye) VALUES " + " (?, ?);";
	        private static final String SELECT_Retards_BY_ID = "select idretard,dateretard,idemploye from retard where idretard =?";
	        private static final String SELECT_ALL_Retards = "select * from retard";
	        private static final String DELETE_Retards_SQL = "delete from retard where idretard = ?;";
	        private static final String UPDATE_Retards_SQL = "update retard set justif = ? where idretard = ?;";
	        private static final String SELECT_Retards_BY_idRetard = "select dateretard,idemploye from retard where idretard =?";

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
	    public static void insertRetard(Retard retard) throws SQLException {
	        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Retards_SQL)) {
	            preparedStatement.setString(1, retard.getDateretard());
	            preparedStatement.setInt(2, retard.getIdemploye());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public Retard selectRetard(int idretard) {
	        Retard retard = null;
	        try (Connection connection = getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Retards_BY_ID);) {
	            preparedStatement.setInt(1, idretard);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String dateretard = rs.getString("dateretard");
	                int idemploye = rs.getInt("idemploye");
	                retard = new Retard(idretard,dateretard,idemploye);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return retard;
	    }
	public static Retard selectRetards(int idretard) {
		Retard retard = null;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Retards_BY_idRetard);) {
			preparedStatement.setInt(1, idretard);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String dateretard = rs.getString("dateretard");
                int idemploye = rs.getInt("idemploye");
				retard=new Retard(dateretard,idemploye);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retard;
	}
	    public List < Retard > selectAllRetards() {
	        List < Retard > retards = new ArrayList < > ();
	        try (Connection connection = getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Retards);) {
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	int idretard = rs.getInt("idretard");
	            	String dateretard = rs.getString("dateretard");
	                int idemploye = rs.getInt("idemploye");
	                int justif = rs.getInt("justif");
	                retards.add(new Retard(idretard,dateretard,idemploye,justif));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return retards;
	    }
	    public static boolean deleteRetard(int idretard) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Retards_SQL);) {
	            statement.setInt(1, idretard);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }
	    public static boolean updateRetard(int idretard) throws SQLException {
	    	PreparedStatement ps = Connect.getConnection().prepareStatement(UPDATE_Retards_SQL);
	    	System.out.println(ps);
	    	ps.setInt(1, 1);
	    	ps.setInt(2, idretard);
	    	System.out.println(ps);
	    	int updt = ps.executeUpdate();
	    	if(updt==1) return true;
	    	else return false;
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