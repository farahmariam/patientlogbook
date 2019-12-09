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
 * Servlet implementation class PopulateProcedureBox
 */
@WebServlet("/PopulateProcedureBox")
public class PopulateProcedureBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateProcedureBox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		populateProcedure(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		populateProcedure(request,response);
	}
	
	protected void populateProcedure(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				response.setContentType("application/json");

				ServletContext context = request.getServletContext();
				HttpSession session = request.getSession();
				
				
				
				 Connection connection = null;		
				 Statement stGetResource= null;		 
				 ResultSet rsGetResource  = null;
				
				 ArrayList<Procedure> procList = new ArrayList();
				 
				 
				
				 
				 try
					{
						
							Class.forName("com.mysql.jdbc.Driver");
							String databaseUrl = context.getInitParameter("data-url");
							String databaseUser = context.getInitParameter("data-user");
							String databasePwd = context.getInitParameter("data-pwd");
							connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);

							String query = "select * from medical_procedure order by medproc_name"; 
							stGetResource = connection.createStatement();
							rsGetResource = stGetResource.executeQuery(query);
							int count =0;
						
							int proc_id = -1;
							String proc_name="";
							
							
							while(rsGetResource.next())
							{
														
								proc_id = rsGetResource.getInt("medproc_id");
								
								proc_name = rsGetResource.getString("medproc_name");
								
								//System.out.println("consultant :" + fName + " " + lName);
								
								
																
								//System.out.println("login name:" + userName + ",user id :" + user_id + ",full name: " + fullName );
								Procedure resObj = new Procedure(proc_name,proc_id);
								procList.add(resObj);
								//System.out.println("fname: " + resObj.consultantFirstName );
							}
							
							Gson gson = new Gson();
						
							String jsonList = gson.toJson(procList);
						        

							
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
						if (rsGetResource != null) try { rsGetResource.close(); } catch (SQLException e) {e.printStackTrace();}
						if (stGetResource != null) try { stGetResource.close(); } catch (SQLException e) {e.printStackTrace();}

				   	 	
						if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
						
						  

						
					}
	}

}
