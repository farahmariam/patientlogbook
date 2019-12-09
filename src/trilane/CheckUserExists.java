package trilane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import trilane.CommonDatabase;

/**
 * Servlet implementation class CheckUserExists
 */
@WebServlet("/CheckUserExists")
public class CheckUserExists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUserExists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	    
	    ServletContext context = request.getServletContext();
	    HttpSession session = request.getSession();
		
		String loginName = request.getParameter("loginid");
		
		
		
		try
		{
				CommonDatabase objCommon = new CommonDatabase();
				boolean userExists = objCommon.checkUserExists(context, session, loginName);
				if(userExists)
				{
					response.getWriter().write("exists");
					
				}
				else
				{
					response.getWriter().write("not exists");
					
				}
				
				
		}
		
		catch (Exception e)
		{
			response.getWriter().write("error");
			System.out.println(e.toString());  
		}
		finally
		{
			
			
		}
		
		
	}


}
