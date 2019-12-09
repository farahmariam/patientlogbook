package trilane;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		createUser(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		createUser(request,response);
	}
	
	protected void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{

				ServletContext context = request.getServletContext();
				HttpSession session = request.getSession();
				//Initialise variables
				String user_fname="";
				String user_lname = "";
				String user_email="";
				String login_id = "";
				String login_password="";
				
				//first name
				if(request.getParameter("fname")!=null && request.getParameter("fname")!="")
				{
					user_fname = request.getParameter("fname");
					
				}
				
				//last name
				if(request.getParameter("lname")!=null && request.getParameter("lname")!="")
				{
					user_lname = request.getParameter("lname");
					
				}
				
				//email
				if(request.getParameter("adminemail")!=null && request.getParameter("adminemail")!="")
				{
					user_email = request.getParameter("adminemail");
					
				}
				
				//login_id
				if(request.getParameter("adminname")!=null && request.getParameter("adminname")!="")
				{
					login_id= request.getParameter("adminname");
				}
				
				//login_password
				if(request.getParameter("adminpass")!=null && request.getParameter("adminpass")!="")
				{
					login_password= request.getParameter("adminpass");
				}
				
				CommonDatabase objCommonDatabase = new CommonDatabase();
				
				
				
				
				boolean isUserSaved = objCommonDatabase.saveUserDetails(context, session, user_fname, user_lname, user_email, login_id, login_password);
				
				if(isUserSaved)
				{
					response.sendRedirect(getServletContext().getContextPath() +  "/jsp/index.jsp");
				}
				else
				{
					response.sendRedirect(getServletContext().getContextPath() +  "/jsp/error.jsp");
				}
			
		
		}
		catch(Exception ex)
		{
			System.out.println("Error occured in createDatabase.java: " + ex.toString() + ex.getStackTrace());
		}
		
	}

}
