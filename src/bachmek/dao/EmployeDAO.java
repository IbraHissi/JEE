package bachmek.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bachmek.model.Employe;

public class EmployeDAO {
	 
	    private static final String INSERT_Employes_SQL = "INSERT INTO employe" + "  (nom,prenom,email,mdps,role,salaire) VALUES " +
	            " (?, ?,?, ?, ?,?);";
	        private static final String SELECT_Employes_BY_ID = "select idemploye,nom,prenom,email,role,salaire from employe where idemploye =?";
	        private static final String SELECT_ALL_Employes = "select * from employe";
	        private static final String DELETE_Employes_SQL = "delete from employe where idemploye = ?;";
	        private static final String UPDATE_Employes_SQL = "update employe set nom = ?,prenom = ?,email= ?,role= ?,salaire =? where idemploye = ?;";
	        private static final String SELECT_Employes_BY_idEmploye = "select nom,prenom,email,role,salaire from employe where idemploye =?";

	    
	    
	    public static void insertEmploye(Employe employe) throws SQLException {
	        System.out.println(INSERT_Employes_SQL);
	        try (Connection connection = Connect.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Employes_SQL)) {
	            preparedStatement.setString(1, employe.getNom());
	            preparedStatement.setString(2, employe.getPrenom());
	            preparedStatement.setString(3, employe.getEmail());
	            preparedStatement.setString(4, employe.getMdps());
	            preparedStatement.setString(5, employe.getRole());
	            preparedStatement.setDouble(6, employe.getSalaire());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public Employe selectEmploye(int idemploye) {
	        Employe employe = null;
	        try (Connection connection =Connect.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Employes_BY_ID);) {
	            preparedStatement.setInt(1, idemploye);
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                String nom = rs.getString("nom");
	                String prenom = rs.getString("prenom");
	                String email = rs.getString("email");
	                String role = rs.getString("role");
	                double salaire = rs.getDouble("salaire");
	                employe = new Employe(idemploye,nom,prenom,email,role,salaire);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        return employe;
	    }
	public static Employe selectEmployes(int idemploye) {
		Employe employe = null;
		try (Connection connection =Connect.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_Employes_BY_idEmploye);) {
			preparedStatement.setInt(1, idemploye);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				String email=rs.getString("email");
				String role=rs.getString("role");
				double salaire=rs.getDouble("salaire");
				System.out.println("id employe et salaire"+ idemploye);
				employe=new Employe(nom,prenom,email,role,salaire);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employe;
	}
	    
	    public static ArrayList <Employe> selectAllEmployes() {
	    	ArrayList <Employe> employes = new ArrayList <Employe> ();
	        try (Connection connection =Connect.getConnection();
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Employes);) {
	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	                int idemploye = rs.getInt("idemploye");
	                String nom = rs.getString("nom");
	                String prenom = rs.getString("prenom");
	                String email = rs.getString("email");
	                String role = rs.getString("role");
	                Double salaire = rs.getDouble("salaire");
	                
	                employes.add(new Employe(idemploye, nom, prenom,email,role, salaire));
	            }
	        } catch (SQLException e) {
e.printStackTrace();	        }
	        return employes;
	    }
	    
	    public static boolean deleteEmploye(int idemploye) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection =Connect.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_Employes_SQL);) {
	            statement.setInt(1, idemploye);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }
	    public boolean updateEmploye(Employe employe) throws SQLException {
	    	PreparedStatement ps = Connect.getConnection().prepareStatement(UPDATE_Employes_SQL);
	    	System.out.println(ps);
	    	ps.setString(1, employe.getNom());
	    	ps.setString(2, employe.getPrenom());
	    	ps.setString(3, employe.getEmail());
	    	ps.setString(4, employe.getRole());
	    	ps.setDouble(5, employe.getSalaire());
	    	ps.setInt(6, employe.getIdemploye());
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