package trilane;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveNewConsultant
 */
@WebServlet("/SaveNewConsultant")
public class SaveNewConsultant extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNewConsultant() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveConsultant(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveConsultant(request, response);
	}
	
	protected void saveConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	   
	    HttpSession session = request.getSession();
	    
	    ServletContext context = request.getServletContext();
	    
	    String conFName  = "";
	    String conLName = "";
	    
		
		
		//first name
		if(request.getParameter("fname")!=null && request.getParameter("fname")!="")
		{
			conFName = request.getParameter("fname");
			
		}
		
		//last name
		if(request.getParameter("lname")!=null && request.getParameter("lname")!="")
		{
			conLName = request.getParameter("lname");
			
		}
		
		Connection conDoctor = null;
		Statement stDoctor = null;
		
		Connection conCheckExists = null;
		Statement stCheckExists = null;
		ResultSet rsExists = null;
		
		
		
		try
		{
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			
			//first check if this consultant exists...if so..dont save
			
			conCheckExists = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
			stCheckExists = conCheckExists.createStatement();
			String existsQuery = "select * from consultants where lower (con_fname) =lower('" + conFName  + "') and lower(con_lname) = lower('" + conLName + "')";
			//System.out.println("exists query:" + existsQuery);
			
			rsExists = stCheckExists.executeQuery(existsQuery);
			
			if (rsExists.next())
			{
				
				response.getWriter().write("This Consultant already exists!");
			}
			else
			{
				 conDoctor = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
				
				 stDoctor = conDoctor.createStatement();
				
				 String sqlQuery = "insert into consultants(con_fname,con_lname) values ('" + conFName + "','" +  conLName + "' )";
				 int i = stDoctor.executeUpdate(sqlQuery);
				 
				 if (i > 0)
				 {
					
					
					
					response.getWriter().write("Saved consultant.");
					
					
					} 
				else 
					{
					response.getWriter().write("Error");
					
					
					}
			}
			
		}
		catch(SQLException se)
		{
	      //Handle errors for JDBC
	      System.out.println(se.toString());
	    }
		catch(Exception e)
		{
	      //Handle errors for Class.forName
			System.out.println(e.toString());
	   	}
		finally
		{
			if (stDoctor != null) try { stDoctor.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conDoctor != null) try { conDoctor.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conCheckExists != null) try { conCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (stCheckExists != null) try { stCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsExists != null) try { rsExists.close(); } catch (SQLException e) {e.printStackTrace();}

			
			
			
			
		}
	    
	}

}
