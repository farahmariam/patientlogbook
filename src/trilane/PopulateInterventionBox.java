package trilane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class PopulateInterventionBox
 */
@WebServlet("/PopulateInterventionBox")
public class PopulateInterventionBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateInterventionBox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		populateIntervention(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		populateIntervention(request,response);
	}
	
	protected void populateIntervention(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");

		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		
		
		 Connection connection = null;		
		 Statement stGetIntervention= null;		 
		 ResultSet rsGetIntervention = null;
		
		 ArrayList<Intervention> interventionList = new ArrayList();
		 
		 
		
		 
		 try
			{
				
					Class.forName("com.mysql.jdbc.Driver");
					String databaseUrl = context.getInitParameter("data-url");
					String databaseUser = context.getInitParameter("data-user");
					String databasePwd = context.getInitParameter("data-pwd");
					connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);

					String query = "select * from intervention order by intervention_name"; 
					stGetIntervention= connection.createStatement();
					rsGetIntervention = stGetIntervention.executeQuery(query);
					int count =0;
				
					int int_id = -1;
					String int_name="";
					
					
					while(rsGetIntervention.next())
					{
												
						int_id = rsGetIntervention.getInt("intervention_id");
						
						int_name = rsGetIntervention.getString("intervention_name");
						
						Intervention intObj = new Intervention(int_name,int_id);
						interventionList.add(intObj);
						
					}
					
					Gson gson = new Gson();
				
					String jsonList = gson.toJson(interventionList);
				        

					
					response.getWriter().write(jsonList);


					//new Gson().toJson(list, response.getWriter());
					
					
					
			}
		 catch(SQLException se)
			{
		      //Handle errors for JDBC
		      se.printStackTrace();
		      System.out.println("error:" + se.getMessage());
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
				if (rsGetIntervention != null) try { rsGetIntervention.close(); } catch (SQLException e) {e.printStackTrace();}
				if (stGetIntervention != null) try { stGetIntervention.close(); } catch (SQLException e) {e.printStackTrace();}

		   	 	
				if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
				
				  

				
			}
	}

}
