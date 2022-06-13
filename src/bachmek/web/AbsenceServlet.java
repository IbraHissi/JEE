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
import bachmek.dao.AbsenceDAO;
import bachmek.model.Employe;
import bachmek.model.Absence;



/**
 * Servlet implementation class EmployeServlet
 */
@WebServlet({"/absence","/absence/new","/absence/edit","/absence/insert","/absence/delete","/absence/update", "/absence/list"})
public class AbsenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AbsenceDAO absenceDAO;
	private EmployeDAO employeDAO;
	
    public void init() {
    	absenceDAO = new AbsenceDAO();
        employeDAO = new EmployeDAO();
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbsenceServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
        System.out.println("action = "+action);
        String context = request.getContextPath();
        System.out.println(context+action);

        try {
            switch (action) {
                case "/absence/new":
                    showNewForm(request, response);
                    break;
                case "/absence/insert":
                    insertAbsence(request, response);
                    break;
                case "/absence/delete":
                    deleteAbsence(request, response);
                    break;
                case "/absence/edit":
                    showEditForm(request, response);
                    break;
                case "/absence/list":
                    listAbsence(request, response);
                    break;
                default:
                    listAbsence(request, response);
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
        if(action.equals("/absence/edit")) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
        		AbsenceDAO.updateAbsence(id);
				listAbsence(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        }
     
        else if(action.equals("/absence/delete")) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
        		AbsenceDAO.deleteAbsence(id);
				listAbsence(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        else if(action.equals("/absence/insert")) {
        	String dateabsence = request.getParameter("dateabsence");
        	int idemploye = Integer.parseInt(request.getParameter("idemploye"));
        	Absence abs = new Absence(dateabsence,idemploye);
        	try {
        		AbsenceDAO.insertAbsence(abs);
				listAbsence(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
				showNewForm(request,response);
			}
        }
		
	}

    private void listAbsence(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Absence > listAbsence = absenceDAO.selectAllAbsences();
        request.setAttribute("listAbsence", listAbsence);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Absence-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	List < Employe > listEmploye = employeDAO.selectAllEmployes();
        request.setAttribute("listEmploye", listEmploye);
       request.getRequestDispatcher("/Absence-form.jsp").forward(request, response);
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int idabsence = Integer.parseInt(request.getParameter("idabsence"));
    	        Absence existingAbsence = absenceDAO.selectAbsence(idabsence);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("Absence-form.jsp");
    	        request.setAttribute("Employe", existingAbsence);
    	        dispatcher.forward(request, response);

    	    }
    private void insertAbsence(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String dateabsence = request.getParameter("dateabsence");
    	        int idemploye = Integer.parseInt(request.getParameter("idemploye"));
    	        Absence newAbsence = new Absence(dateabsence,idemploye);
    	        absenceDAO.insertAbsence(newAbsence);
    	        response.sendRedirect("list");
    	    }

    private void deleteAbsence(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int idabsence = Integer.parseInt(request.getParameter("idabsence"));
        absenceDAO.deleteAbsence(idabsence);
        response.sendRedirect("list");
    }	
}