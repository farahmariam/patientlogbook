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
 * Servlet implementation class PopulateIndicationBox
 */
@WebServlet("/PopulateIndicationBox")
public class PopulateIndicationBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateIndicationBox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		populateIndication(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		populateIndication(request,response);
	}
	
	protected void populateIndication(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");

		ServletContext context = request.getServletContext();
		HttpSession session = request.getSession();
		
		
		
		 Connection connection = null;		
		 Statement stGetIndication= null;		 
		 ResultSet rsGetIndication = null;
		
		 ArrayList<Indication> indicationList = new ArrayList();
		 
		 
		
		 
		 try
			{
				
					Class.forName("com.mysql.jdbc.Driver");
					String databaseUrl = context.getInitParameter("data-url");
					String databaseUser = context.getInitParameter("data-user");
					String databasePwd = context.getInitParameter("data-pwd");
					connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);

					String query = "select * from indication order by ind_name"; 
					stGetIndication = connection.createStatement();
					rsGetIndication = stGetIndication.executeQuery(query);
					int count =0;
				
					int ind_id = -1;
					String ind_name="";
					
					
					while(rsGetIndication.next())
					{
												
						ind_id = rsGetIndication.getInt("ind_id");
						
						ind_name = rsGetIndication.getString("ind_name");
						
						Indication indObj = new Indication(ind_name,ind_id);
						indicationList.add(indObj);
						
					}
					
					Gson gson = new Gson();
				
					String jsonList = gson.toJson(indicationList);
				        

					
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
				if (rsGetIndication != null) try { rsGetIndication.close(); } catch (SQLException e) {e.printStackTrace();}
				if (stGetIndication != null) try { stGetIndication.close(); } catch (SQLException e) {e.printStackTrace();}

		   	 	
				if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
				
				  

				
			}
	}

}
