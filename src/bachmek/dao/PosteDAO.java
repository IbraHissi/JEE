package bachmek.dao;
import java.sql.*;
import java.util.ArrayList;

import bachmek.model.Poste;
public class PosteDAO {
	private static final String SELECT_Postes = "select nomposte,description from poste";
	private static final String SELECT_Poste_By_Nom = "select nomposte,description from poste where nomposte=?";
	public static ArrayList<Poste> select_Postes() throws SQLException {
		ArrayList<Poste> postes = new ArrayList<Poste>();
		Connection connection = Connect.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_Postes);
		ResultSet rs =	ps.executeQuery();
		Poste p=null;
			while(rs.next()) {
				if(rs.getString(1).equals("AC")) {
					for(int i = 1;i<=3;i++) {
						p = new Poste(rs.getString(1)+i,rs.getString(2));
						postes.add(p);
					}
				}
				else if (rs.getString(1).equals("EP")) {
					for(int i = 1;i<=2;i++) {
						p = new Poste(rs.getString(1)+i,rs.getString(2));
						postes.add(p);
					}
				}	
				else if (rs.getString(1).equals("SE")) {
					for(int i = 1;i<=2;i++) {
						p = new Poste(rs.getString(1)+i,rs.getString(2));
						postes.add(p);
					}
				}
				else {
					p = new Poste(rs.getString(1),rs.getString(2));
					postes.add(p);
				}
			}
			return postes;
	}
	public static Poste select_Poste_By_Nom(String nom) throws SQLException {
		Connection connection = Connect.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_Poste_By_Nom);
		ps.setString(1, nom);
		ResultSet rs =	ps.executeQuery();
		Poste p=null;
			while(rs.next()) {
				p = new Poste(rs.getString(1),rs.getString(2));
				
			}
		return p;
	}
}
