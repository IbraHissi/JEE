package bachmek.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bachmek.dao.EmployeDAO;
import bachmek.dao.PosteDAO;
import bachmek.model.Employe;
import bachmek.model.Poste;


@WebServlet("/PlanningServlet")
public class PlanningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PlanningServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				int l = -1;
				ArrayList<Integer> libres = new ArrayList<Integer>();
				List<Employe> employes = EmployeDAO.selectAllEmployes();
				for(int i = 0;i<employes.size();i++) {
					if(employes.get(i).getRole().equals("admin")) {
						employes.remove(i);i--;}
					else {
						System.out.println(employes.get(i).getIdemploye()+" - "+employes.get(i).getNom());
					}
					}
				ArrayList<Poste> postes = PosteDAO.select_Postes();
				for(int i = 0;i<postes.size();i++) {
					postes.get(i).affecter(employes.get(i).getIdemploye());
					if(postes.get(10).getEmploye()!=-1) {
						l = postes.get(10).liberer();
					System.out.println("Libération de :"+ l);
					libres.add(l);}
					System.out.println(postes.get(i).getNom()+" : "+postes.get(i).getEmploye());}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
	}

}
