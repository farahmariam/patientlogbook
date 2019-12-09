package trilane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
import java.util.Calendar;

/**
 * Servlet implementation class EmailLoginCredentials
 */
@WebServlet("/EmailLoginCredentials")
public class EmailLoginCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailLoginCredentials() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		emailLoginDetails(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		emailLoginDetails(request,response);
	}
	
	protected void emailLoginDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//get the email id
		
		String emailId  =  request.getParameter("email");
		
		//query global_admin  table to get the company id.
		
		 ServletContext context = request.getServletContext();
			
			
		Connection connection = null;
		Statement stGetCompanyId = null;
		ResultSet rsGetCompanyId = null;
		Statement stGetCompanyLogin = null;
		ResultSet rsGetCompanyLogin = null;
		
		String admin_login_id="";
		String admin_login_password="";
				
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				//String databaseUrl = "jdbc:mysql://localhost:3306/patientlogbook?useSSL=false";
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				String globalQuery = "select * from users where email like '%" + emailId  + "%'";
				stGetCompanyId = connection.createStatement();
				rsGetCompanyId = stGetCompanyId.executeQuery(globalQuery);
				if(rsGetCompanyId.next())
				{
					admin_login_id = rsGetCompanyId.getString("uname");
					admin_login_password = rsGetCompanyId.getString("pwd"); 
					
				}
				
				
				
				//now send email with the details
				//user name and password of email account from which mail has to be sent.
				
				String fromEmail = "resourcemailer@gmail.com";
				String fromPassword = "resource_mailer_2016";
				
				String toEmail = emailId;
				
				GMailServer sender = new GMailServer(fromEmail, fromPassword);
				
				StringBuilder mailbody = new StringBuilder();
				
				String subject = "Patient Logbook project login details!";
				
				mailbody.append("<html><body>");
				mailbody.append("<p> Login Credentials for Patient Logbook project</p>"  );
				mailbody.append("<table style='border:2px solid black'>");
				//mailbody.append("<table class='settingstable'>");
			
				mailbody.append("<tr bgcolor=\"#EE485E\" >");
				mailbody.append("<th colspan='2'>");
				mailbody.append("LOGIN DETAILS ");
				mailbody.append("</th>");
				
				mailbody.append("</tr>");
				
				mailbody.append("<tr>");
				
				mailbody.append("<th>");
				mailbody.append("User Login Id:");
				mailbody.append("</th>");
				
				mailbody.append("<th>");
				mailbody.append(admin_login_id);
				mailbody.append("</th>");
				
				mailbody.append("</tr>");
				
				
				mailbody.append("<tr >");
				
				mailbody.append("<th>");
				mailbody.append("User Login Password:");
				mailbody.append("</th>");
				
				mailbody.append("<th>");
				mailbody.append(admin_login_password);
				mailbody.append("</th>");
				
				mailbody.append("</tr>");
				
				
				mailbody.append("<th colspan='2'>");
				mailbody.append("Please click on this link to login:  <a href='http://localhost:8080/PatientLogbook/login' title='Click here to login'> Login </a>");
				
				
				mailbody.append("</th>");
				
				mailbody.append("</tr>");
				
				
				
				mailbody.append("</table></body></html>");
				
				
				sender.sendMail(subject,mailbody.toString(),fromEmail,toEmail);
				System.out.println("Email Sent Succesfully...at: " + Calendar.getInstance().getTime().toString() );
				
				
				response.sendRedirect(getServletContext().getContextPath() +  "/jsp/userLoginDetailsSent.jsp");
				
				
				
				
		}
		catch (SQLException e)
		{
			
			System.out.println(e.toString()); 
			
		}
		catch (Exception e)
		{
			
			System.out.println(e.toString()); 
			
		}
		finally
		{
			
			if (stGetCompanyId != null) try { stGetCompanyId.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetCompanyId != null) try { rsGetCompanyId.close(); } catch (SQLException e) {e.printStackTrace();}
			if (stGetCompanyLogin != null) try { stGetCompanyLogin.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetCompanyLogin != null) try { rsGetCompanyLogin.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
		}


	}

}
