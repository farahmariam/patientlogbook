package trilane;

import java.io.IOException;
import java.sql.Connection;
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

import com.google.gson.Gson;

/**
 * Servlet implementation class GetProcedure
 */
@WebServlet("/GetProcedure")
public class GetProcedure extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProcedure() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getProcedure(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getProcedure(request, response);
	}
	
	protected void getProcedure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				response.setContentType("application/json");

				ServletContext context = request.getServletContext();
				HttpSession session = request.getSession();
				Procedure procObj = null;
				
				
				
				 Connection connection = null;		
				 Statement stGetResource= null;		 
				 ResultSet rsGetResource  = null;
				 String procName = "";
				
				
				//first name
					if(request.getParameter("procname")!=null && request.getParameter("procname")!="")
					{
						procName = request.getParameter("procname");
						
					}
				
				 try
				 {
					CommonDatabase DatabaseObj = new CommonDatabase();
					procObj = DatabaseObj.getProcedure(context, session, procName);
								
					
								
					Gson gson = new Gson();
							
					String jsonList = gson.toJson(procObj);
							        
					//System.out.println("consultant name: " + conObj.consultantFirstName);
								
					response.getWriter().write(jsonList);
					
				}
				
					catch(IOException e){
						e.printStackTrace();
						System.out.println("error:" + e.getMessage());
					}
					catch(Exception e)
					{
				      //Handle errors for Class.forName
				      e.printStackTrace();
				      System.out.println("error:" + e.getMessage());
				   	}
					

					finally
					{
						if (rsGetResource != null) try { rsGetResource.close(); } catch (SQLException e) {e.printStackTrace();}
						if (stGetResource != null) try { stGetResource.close(); } catch (SQLException e) {e.printStackTrace();}

				   	 	
						if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
						
						  

						
					}
	}

}
