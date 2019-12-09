package trilane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		login(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		login(request,response);
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		String username = request.getParameter("uname");
		String pwd = request.getParameter("pass");


		String databaseName = "patientlogbook";
		String userFirstName = "";
		String userLastName = "";
		
		String userName = "";
		int userId = -1;
		
		
		
		Connection loginConnection = null;
		
		Statement stLogin = null;
		ResultSet rsLogin = null;
		
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");
			//String databaseUrlforLogin = "jdbc:mysql://localhost:3306/" + databaseName  +"?useSSL=false";
			String databaseUrlforLogin = context.getInitParameter("data-url");
			
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
		
			loginConnection = DriverManager.getConnection(databaseUrlforLogin,databaseUser, databasePwd);
			
			
			
			
					
					stLogin = loginConnection.createStatement();
					
					rsLogin = stLogin.executeQuery("select * from users where uname='" + username + "' and pwd='" + pwd + "'");
					if (rsLogin.next()) 
					{
						userFirstName = rsLogin.getString("fname");
						userLastName = rsLogin.getString("lname");
						userId = rsLogin.getInt("user_id");
						
						if(userFirstName==null) userFirstName="";
						if(userLastName==null) userLastName="";
						userName= userFirstName + " " + userLastName;
						
						
						session.setAttribute("username", userName);
						session.setAttribute("userid", userId);
						session.setAttribute("loginname", username);
						
						session.setAttribute("data-url-logbook", databaseUrlforLogin);
						
						
						response.sendRedirect(getServletContext().getContextPath() +  "/jsp/mainPage.jsp");
						}
					else
					{
						System.out.println("Invalid login attempt! ");
						session.setAttribute("errormessage", "Invalid login attempt!");
						
						response.sendRedirect(getServletContext().getContextPath() +  "/jsp/error.jsp");
					}
					
				
			
			
			
		}
		catch(SQLException se)
		{
	   		//Handle errors for JDBC
	  		// se.printStackTrace();
	  		session.setAttribute("errormessage", "Invalid Login!Please setup your company details before you  login! If you have already done so, please enter the correct companyId.!Also, verify the database server is running.");
			response.sendRedirect(getServletContext().getContextPath() +  "/jsp/error.jsp");
	  		 
		 }
		catch(Exception e)
		{
	  		 //Handle errors for Class.forName
	   			//e.printStackTrace();
	   			session.setAttribute("errormessage", "Invalid Login!Please setup your company details before you  login!If you have already done so, check if database server is running, and also enter the correct companyId.");
				response.sendRedirect(getServletContext().getContextPath() +  "/jsp/error.jsp");
		}
		finally
		{
			if (rsLogin != null) try { rsLogin.close(); } catch (SQLException e) {e.printStackTrace();}
			if (stLogin != null) try { stLogin.close(); } catch (SQLException e) {e.printStackTrace();}
			
		
			
			if (loginConnection != null) try { loginConnection.close(); } catch (SQLException e) {e.printStackTrace();}
		

		}

	}
	

}
