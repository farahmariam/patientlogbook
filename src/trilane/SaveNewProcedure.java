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
 * Servlet implementation class SaveNewProcedure
 */
@WebServlet("/SaveNewProcedure")
public class SaveNewProcedure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNewProcedure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveProcedure(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		saveProcedure(request,response);
	}
	
	protected void saveProcedure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	   
	    HttpSession session = request.getSession();
	    
	    ServletContext context = request.getServletContext();
	    
	    String procName  = "";
	    
	    
		
		
		// name
		if(request.getParameter("procname")!=null && request.getParameter("procname")!="")
		{
			procName = request.getParameter("procname");
			
		}
		
		
		Connection conProcedure = null;
		Statement stProcedure = null;
		
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
			String existsQuery = "select * from medical_procedure where lower (medproc_name) =lower('" + procName  + "')" ;
			//System.out.println("exists query:" + existsQuery);
			
			rsExists = stCheckExists.executeQuery(existsQuery);
			
			if (rsExists.next())
			{
				
				response.getWriter().write("This Procedure already exists!");
			}
			else
			{
				 conProcedure = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
				
				 stProcedure = conProcedure.createStatement();
				
				 String sqlQuery = "insert into medical_procedure(medproc_name) values ('" + procName + "')";
				 int i = stProcedure.executeUpdate(sqlQuery);
				 
				 if (i > 0)
				 {
					
					
					
					response.getWriter().write("Saved procedure.");
					
					
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
			if (stProcedure != null) try { stProcedure.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conProcedure != null) try { conProcedure.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conCheckExists != null) try { conCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (stCheckExists != null) try { stCheckExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsExists != null) try { rsExists.close(); } catch (SQLException e) {e.printStackTrace();}

			
			
			
		}
	    
	}


}
