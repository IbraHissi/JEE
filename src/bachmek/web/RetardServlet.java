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
import bachmek.dao.RetardDAO;
import bachmek.model.Employe;
import bachmek.model.Retard;



/**
 * Servlet implementation class EmployeServlet
 */
@WebServlet({"/retard","/retard/new","/retard/edit","/retard/insert","/retard/delete","/retard/update", "/retard/list"})
public class RetardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RetardDAO retardDAO;
	private EmployeDAO employeDAO;
	
    public void init() {
        retardDAO = new RetardDAO();
        employeDAO = new EmployeDAO();
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetardServlet() {
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
                case "/retard/new":
                    showNewForm(request, response);
                    break;
                case "/retard/insert":
                    insertRetard(request, response);
                    break;
                case "/retard/delete":
                    deleteRetard(request, response);
                    break;
                case "/retard/edit":
                    showEditForm(request, response);
                    break;
                case "/retard/list":
                    listRetard(request, response);
                    break;
                default:
                    listRetard(request, response);
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
        if(action.equals("/retard/edit")) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
				RetardDAO.updateRetard(id);
				listRetard(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	
        }
     
        else if(action.equals("/retard/delete")) {
        	int id = Integer.parseInt(request.getParameter("id"));
        	try {
				RetardDAO.deleteRetard(id);
				listRetard(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        else if(action.equals("/retard/insert")) {
        	String dateretard = request.getParameter("dateretard");
        	System.out.println(dateretard);
        	int idemploye = Integer.parseInt(request.getParameter("idemploye"));
        	Retard ret = new Retard(dateretard,idemploye);
        	try {
				RetardDAO.insertRetard(ret);
				listRetard(request,response);
			} catch (SQLException e) {
				e.printStackTrace();
				showNewForm(request,response);
			}
        }
		
	}

    private void listRetard(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Retard > listRetard = retardDAO.selectAllRetards();
        request.setAttribute("listRetard", listRetard);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Retard-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	System.out.println("BEFORE");
    	List < Employe > listEmploye = employeDAO.selectAllEmployes();
        request.setAttribute("listEmploye", listEmploye);
       request.getRequestDispatcher("/Retard-form.jsp").forward(request, response);
       System.out.println("AFTER");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, ServletException, IOException {
    	        int idretard = Integer.parseInt(request.getParameter("idretard"));
    	        Retard existingRetard = retardDAO.selectRetard(idretard);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("Retard-form.jsp");
    	        request.setAttribute("Retard", existingRetard);
    	        dispatcher.forward(request, response);

    	    }
    private void insertRetard(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException {
    	        String dateretard = request.getParameter("dateretard");
    	        int idemploye = Integer.parseInt(request.getParameter("idemploye"));
    	        Retard newRetard = new Retard(dateretard,idemploye);
    	        retardDAO.insertRetard(newRetard);
    	        response.sendRedirect("list");
    	    }

    private void deleteRetard(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int idretard = Integer.parseInt(request.getParameter("idretard"));
        retardDAO.deleteRetard(idretard);
        response.sendRedirect("list");
    }	
}