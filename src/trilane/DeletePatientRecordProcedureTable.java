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
 * Servlet implementation class DeletePatientRecordProcedureTable
 */
@WebServlet("/DeletePatientRecordProcedureTable")
public class DeletePatientRecordProcedureTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePatientRecordProcedureTable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		deletePatient(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		deletePatient(request,response);
	}
	protected void deletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	   
	    HttpSession session = request.getSession();
	    
	    ServletContext context = request.getServletContext();
	    
	    String patient_nhi  = "";
	    String original_procedure_date="";
	    
	    
		
		
		// nhi
		if(request.getParameter("patientnhi")!=null && request.getParameter("patientnhi")!="")
		{
			patient_nhi = request.getParameter("patientnhi");
			
		}
		
		if(request.getParameter("originalDate")!=null && request.getParameter("originalDate")!="")
		{
			original_procedure_date = request.getParameter("originalDate");
			
		}
		
		Connection conDelete = null;
		Statement stDelete = null;
		
		
		
		try
		{
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			
			//first check if this procedure exists...if so..dont save
			
			conDelete = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
			stDelete = conDelete.createStatement();
			String deleteQuery = "delete from procedure_on_patient where lower (patient_nhi) = lower('" + patient_nhi  + "') and lower(replace(proc_date,' ','')) = lower('" + original_procedure_date  + "')" ; ;
			
			int deleted = stDelete.executeUpdate(deleteQuery);
		
			 if (deleted > 0)
			 {
				 //response.sendRedirect("jsp/showDataOnWeb.jsp");
				 response.getWriter().write("deleted");
					
				
			 } 
			 else 
			 {
				 //session.setAttribute("errormessage", "Error occured when trying to delete record");
				// response.sendRedirect("jsp/error.jsp");
				response.getWriter().write("Error Occured when trying to delete patient record.");
				
				
			 }
			
			
		}
		catch(SQLException se)
		{
	      //Handle errors for JDBC
		 response.getWriter().write("Error Occured when trying to delete: " + se.getMessage());
			
	      System.out.println(se.toString());
	    }
		catch(Exception e)
		{
	      //Handle errors for Class.forName
		 response.getWriter().write("Error Occured when trying to delete: " + e.getMessage());
				
			System.out.println(e.toString());
	   	}
		finally
		{
			if (stDelete != null) try { conDelete.close(); } catch (SQLException e) {e.printStackTrace();}
			if (conDelete != null) try { conDelete.close(); } catch (SQLException e) {e.printStackTrace();}
			
			
			
			
		}
		
	}

}
