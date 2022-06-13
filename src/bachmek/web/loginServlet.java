package bachmek.web;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bachmek.dao.loginDAO;
@WebServlet("/login")
public class loginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private loginDAO loginDAO;
    public void init() {
        loginDAO = new loginDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    if(request.getServletPath().equals("/login")) {
		String email = request.getParameter("email");
		String mdps = request.getParameter("mdps");
			if("admin".equals(loginDAO.getEmploye(email,mdps).getRole())){
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				request.getRequestDispatcher("homeAdmin.jsp").forward(request,response);
				}	
			else if("employe".equals(loginDAO.getEmploye(email,mdps).getRole())){
				HttpSession session = request.getSession();
				session.setAttribute("email", email);
				request.getRequestDispatcher("homeEmploye.jsp").forward(request,response);
			}
			else {
				request.setAttribute("mdpInc", "email ou mot de passe incorrect");
				this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
}
}
    }