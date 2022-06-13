package bachmek.web;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bachmek.dao.EmployeDAO;
import bachmek.model.Employe;



/**
 * Servlet implementation class EmployeServlet
 */
@WebServlet({"/employe","/employe/new","/employe/edit","/employe/delete","/employe/insert","/employe/update", "/employe/list"})
public class EmployeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeDAO employeDAO;

	
    public void init() {
        employeDAO = new EmployeDAO();
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
        String context = request.getContextPath();

        try {
            switch (action) {
                case "/employe/new":
                    showNewForm(request, response);
                    break;
                /*case "/employe/insert":
                    insertEmploye(request, response);
                    break;*/
                case "/employe/delete":
                    deleteEmploye(request, response);
                    break;
                case "/employe/edit":
                    showEditForm(request, response);
                    break;
                case "/employe/update":
                	System.out.println("UPDATE");
                    updateEmploye(request, response);
                    break;
                case "/employe/list":
                    listEmploye(request, response);
                    break;
                default:
                    listEmploye(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
        if(action.equals("/employe/edit")) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	Employe e = EmployeDAO.selectEmployes(id);
        	request.setAttribute("employe", e);
        	request.setAttribute("id", id);
        	request.getRequestDispatcher("/Employe-form.jsp").forward(request, response);
        	
        }
        else if(action.equals("/employe/update")) {
        	System.out.println("UPDATE");
            try {
				updateEmploye(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}}
        else if(action.equals("/employe/delete")) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
				EmployeDAO.deleteEmploye(id);
				request.getRequestDispatcher("/Employe-list.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        else if(action.equals("/employe/insert")) {
        	System.out.println("-------INSERT");
        	String nom = request.getParameter("nom");
        	String prenom = request.getParameter("prenom");
        	String email = request.getParameter("email");
        	String mdps = request.getParameter("mdps");
        	String role = request.getParameter("role");
        	Double salaire = Double.parseDouble(request.getParameter("salaire"));
        	Employe emp = new Employe(nom,prenom,email,mdps,role,salaire);
        	try {
				EmployeDAO.insertEmploye(emp);
				List < Employe > listEmploye = employeDAO.selectAllEmployes();
		        request.setAttribute("listEmploye", listEmploye);
				request.getRequestDispatcher("/Employe-list.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
				request.getRequestDispatcher("/Employe-form.jsp").forward(request, response);
			}
        }
		
	}

    private void listEmploye(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Employe > listEmploye = employeDAO.selectAllEmployes();
        request.setAttribute("listEmploye", listEmploye);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Employe-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	System.out.println("BEFORE");
       request.getRequestDispatcher("/Employe-form.jsp").forward(request, response);
       System.out.println("AFTER");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int idemploye = Integer.parseInt(request.getParameter("idemploye"));
    	        Employe existingEmploye = employeDAO.selectEmploye(idemploye);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("Employe-form.jsp");
    	        request.setAttribute("Employe", existingEmploye);
    	        dispatcher.forward(request, response);

    	    }
    private void insertEmploye(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String nom = request.getParameter("nom");
    	        String prenom = request.getParameter("prenom");
    	        String email = request.getParameter("email");
    	        String mdps=  request.getParameter("mdps");
    	        String role = request.getParameter("role");
    	        Double salaire = Double.parseDouble(request.getParameter("salaire"));
    	        Employe newEmploye= new Employe(nom,prenom,email,mdps,role,salaire);
    	        employeDAO.insertEmploye(newEmploye);
    	        response.sendRedirect("list");
    	    }
    private void updateEmploye(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
    	System.out.println("BEGIN");
        int idemploye = Integer.parseInt(request.getParameter("idemploye"));
        System.out.println("----------------------"+idemploye);
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String mdps=  request.getParameter("mdps");
        String role=  request.getParameter("role");
        Double salaire = Double.parseDouble(request.getParameter("salaire"));
        Employe e = new Employe(idemploye,nom,prenom,email,role,salaire);
        boolean updt = employeDAO.updateEmploye(e);
        System.out.println(updt);
        listEmploye(request,response);
    }
    private void deleteEmploye(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int idemploye = Integer.parseInt(request.getParameter("idemploye"));
        employeDAO.deleteEmploye(idemploye);
        response.sendRedirect("list");
    }	
}