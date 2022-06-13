package bachmek.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bachmek.model.Employe;
import bachmek.model.Absence;

public class AbsenceDAO {
	 private static String jdbcURL = "jdbc:mysql://localhost:3306/myproject?useSSL=false&serverTimezone=UTC";
	 
	 
	 //
	    private static String jdbcemail = "root";
	    private static String jdbcmdps = "Ibrahim01";
	    private static final String INSERT_Absences_SQL = "INSERT INTO absence" + "  (dateabsence,idemploye) VALUES " + " (?, ?);";
	        private static final String SELECT_Absences_BY_ID = "select idabsence,dateabsence,idemploye from absence where idabsence =?";
	        private static final String SELECT_ALL_Absences = "select * from absence";
	        private static final String DELETE_Absences_SQL = "delete from absence where idabsence = ?;";
	        private static final String UPDATE_Absences_SQL = "update absence set justif = ? where idabsence = ?;";
	        private static final String SELECT_Absences_BY_idAbsence = "select dateabsence,idemploye from absence where idabsence =?";

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
	    public static void insertAbsence(Absence absence) throws SQLException {
	        try (Connection connection = getConnection(); 
	        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Absences_SQL)) {
	            preparedStatement.setString(1, absence.getDateabsence());
	            preparedStatement.setInt(2, absence.getIdemploye());
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public Absence selectAbsence(int idabsence) {
	    	Absence absence = null;
	        try (Connection connection = getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Absences_BY_ID);) {
	            preparedStatement.setInt(1, idabsence);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String dateabsence = rs.getString("dateabsence");
	                int idemploye = rs.getInt("idemploye");
	                absence = new Absence(idabsence,dateabsence,idemploye);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return absence;
	    }
	public static Absence selectAbsences(int idabsence) {
		Absence absence = null;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Absences_BY_idAbsence);) {
			preparedStatement.setInt(1, idabsence);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String dateretard = rs.getString("dateretard");
                int idemploye = rs.getInt("idemploye");
                absence=new Absence(dateretard,idemploye);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return absence;
	}
	    public List < Absence > selectAllAbsences() {
	        List < Absence > absences = new ArrayList < > ();
	        try (Connection connection = getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Absences);) {
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	int idabsence = rs.getInt("idabsence");
	            	String dateabsence = rs.getString("dateabsence");
	                int idemploye = rs.getInt("idemploye");
	                int justif = rs.getInt("justif");
	                absences.add(new Absence(idabsence,dateabsence,idemploye,justif));
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return absences;
	    }
	    public static boolean deleteAbsence(int idabsence) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Absences_SQL);) {
	            statement.setInt(1, idabsence);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }
	    public static boolean updateAbsence(int idabsence) throws SQLException {
	    	PreparedStatement ps = Connect.getConnection().prepareStatement(UPDATE_Absences_SQL);
	    	System.out.println(ps);
	    	ps.setInt(1, 1);
	    	ps.setInt(2, idabsence);
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