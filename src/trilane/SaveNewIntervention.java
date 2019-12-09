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
 * Servlet implementation class SaveNewIntervention
 */
@WebServlet("/SaveNewIntervention")
public class SaveNewIntervention extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNewIntervention() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveIntervention(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveIntervention(request,response);
	}
	
	
	protected void saveIntervention(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	   
	    HttpSession session = request.getSession();
	    
	    ServletContext context = request.getServletContext();
	    
	    String interventionName  = "";
	    
	    
		
		
		// name
		if(request.getParameter("interventionname")!=null && request.getParameter("interventionname")!="")
		{
			interventionName = request.getParameter("interventionname");
			
		}
		
		
		Connection conIntervention = null;
		Statement stIntervention = null;
		
		Connection conCheckExists = null;
		Statement stCheckExists = null;
		ResultSet rsExists = null;
		
		
		
		try
		{
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			
			//first check if this procedure exists...if so..dont save
			
			conCheckExists = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
			stCheckExists = conCheckExists.createStatement();
			String existsQuery = "select * from intervention where lower (intervention_name) =lower('" + interventionName  + "')" ;
			//System.out.println("exists query:" + existsQuery);
			
			rsExists = stCheckExists.executeQuery(existsQuery);
			
			if (rsExists.next())
			{
				
				response.getWriter().write("This intervention already exists!");
			}
			else
			{
				 conIntervention = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
				
				 stIntervention= conIntervention.createStatement();
				
				 String sqlQuery = "insert into intervention (intervention_name) values ('" + interventionName + "')";
				 int i = stIntervention.executeUpdate(sqlQuery);
				 
				 if (i > 0)
				 {
					
					
					
					response.getWriter().write("Saved intervention.");
					
					
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
			if (stIntervention != null) try { stIntervention.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conIntervention!= null) try { conIntervention.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conCheckExists != null) try { conCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (stCheckExists != null) try { stCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsExists != null) try { rsExists.close(); } catch (SQLException e) {e.printStackTrace();}

			
			
			
		}
	}


}
