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
 * Servlet implementation class SaveNewVessel
 */
@WebServlet("/SaveNewVessel")
public class SaveNewVessel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNewVessel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveVessel(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveVessel(request, response);
	}
	
	
	protected void saveVessel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	   
	    HttpSession session = request.getSession();
	    
	    ServletContext context = request.getServletContext();
	    
	    String vesselName  = "";
	    
	    
		
		
		// name
		if(request.getParameter("vesselname")!=null && request.getParameter("vesselname")!="")
		{
			vesselName = request.getParameter("vesselname");
			
		}
		
		
		Connection conVessel = null;
		Statement stVessel = null;
		
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
			String existsQuery = "select * from vessels where lower (vessel_name) =lower('" + vesselName  + "')" ;
			//System.out.println("exists query:" + existsQuery);
			
			rsExists = stCheckExists.executeQuery(existsQuery);
			
			if (rsExists.next())
			{
				
				response.getWriter().write("This vessel already exists!");
			}
			else
			{
				conVessel = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
				
				 stVessel = conVessel.createStatement();
				
				 String sqlQuery = "insert into vessels(vessel_name) values ('" + vesselName + "')";
				 int i = stVessel.executeUpdate(sqlQuery);
				 
				 if (i > 0)
				 {
					
					
					
					response.getWriter().write("Saved vessel.");
					
					
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
			if (stVessel != null) try { stVessel.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conVessel != null) try { conVessel.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conCheckExists != null) try { conCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (stCheckExists != null) try { stCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsExists != null) try { rsExists.close(); } catch (SQLException e) {e.printStackTrace();}

			
			
			
		}
	    
		
		
		
		
	}

}
