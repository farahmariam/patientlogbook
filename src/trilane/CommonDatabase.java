package trilane;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CommonDatabase 
{
	public CommonDatabase()
	{
		
	}
	public boolean saveUserDetails(ServletContext context,HttpSession session, String fName,String lName, String email, String loginId, String loginPwd)
	{
		Connection connection = null;


		Statement stUser = null;
		
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				//String databaseUrl = "jdbc:mysql://localhost:3306/patientlogbook?useSSL=false";
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				stUser = connection.createStatement();
				
				
				
				String sqlQuery = "insert into users(fname, lname,email,uname,pwd) values ('" + fName + "' , '" + lName + "','" + email + "' , '" + loginId + "','" + loginPwd + "')";
				int i = stUser.executeUpdate(sqlQuery);
				if (i>0)
				{
					return true;
				}
				else
				{
					return false;
				}
				
		}
		catch (SQLException e)
		{
			
			System.out.println(e.toString()); 
			session.setAttribute("errormessage", "Error occured . Please check if database is running.");
			
			return false;
		}
		catch (Exception e)
		{
			
			System.out.println(e.toString()); 
			session.setAttribute("errormessage", "Error occured . Please check if database is running.");
			
			return false;
		}
		finally
		{
			
			if (stUser != null) try { stUser.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		
		
	}
	
	
	
	
	public boolean checkUserExists(ServletContext context,HttpSession session, String loginId)
	{
		Connection connection = null;
		


		Statement stUser = null;
		ResultSet rsUser = null;
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				//String databaseUrl = "jdbc:mysql://localhost:3306/patientlogbook?useSSL=false";
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				stUser = connection.createStatement();
				
				
				
				String sqlQuery = "select * from users where uname='" + loginId + "'";
				rsUser = stUser.executeQuery(sqlQuery);
				
				if (rsUser.next())
				{
					return true;
				}
				else
				{
					return false;
				}
				
		}
		catch (SQLException e)
		{
			
			System.out.println(e.toString()); 
			session.setAttribute("errormessage", "Error occured . Please check if database is running.");
			
			return false;
		}
		catch (Exception e)
		{
			
			System.out.println(e.toString()); 
			session.setAttribute("errormessage", "Error occured . Please check if database is running.");
			
			return false;
		}
		finally
		{
			
			if (stUser != null) try { stUser.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsUser != null) try { rsUser.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		}
		
		
	}
	
	
	
	public ArrayList<Consultant> getAllConsultants( ServletContext context,HttpSession session)
	{
		ArrayList<Consultant> conList = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getStatus = "select * from consultants order by con_fname";
				resultset = statement.executeQuery(getStatus);
				int i=0;
				while(resultset.next())
				{
					int con_id = resultset.getInt("con_id");
					String first_name = resultset.getString("con_fname"); 
					String last_name = resultset.getString("con_lname");
					
					Consultant obj = new Consultant(first_name,last_name, con_id);
					conList.add(i,obj);
					i++;
				}
				
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return conList;
		
		
		
		
	}
	
	
	public ArrayList<Indication> getAllIndications( ServletContext context,HttpSession session)
	{
		ArrayList<Indication> indList = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getIndication = "select * from indication order by ind_name";
				resultset = statement.executeQuery(getIndication);
				int i=0;
				while(resultset.next())
				{
					int ind_id = resultset.getInt("ind_id");
					String ind_name = resultset.getString("ind_name"); 
					
					
					Indication obj = new Indication(ind_name,ind_id);
					indList.add(i,obj);
					i++;
				}
				
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return indList;
		
		
		
		
		
	}
	
	
	
	public ArrayList<Impression> getAllImpressions( ServletContext context,HttpSession session)
	{
		ArrayList<Impression> impList = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getImpressions = "select * from final_impression order by impression_name";
				resultset = statement.executeQuery(getImpressions);
				int i=0;
				while(resultset.next())
				{
					int imp_id = resultset.getInt("impression_id");
					String imp_name = resultset.getString("impression_name"); 
					
					
					Impression obj = new Impression(imp_name,imp_id);
					impList.add(i,obj);
					i++;
				}
				
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return impList;
		
		
		
		
		
	}
	
	
	public ArrayList<Vessels> getAllDiseasedVessels( ServletContext context,HttpSession session)
	{
		ArrayList<Vessels> vesselList = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getVessels = "select * from vessels order by vessel_name";
				resultset = statement.executeQuery(getVessels);
				int i=0;
				while(resultset.next())
				{
					int vessel_id = resultset.getInt("vessel_id");
					String vessel_name = resultset.getString("vessel_name"); 
					
					
					Vessels obj = new Vessels(vessel_name,vessel_id);
					vesselList.add(i,obj);
					i++;
				}
				
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return vesselList;
		
		
		
		
		
	}
	
	
	
	
	public ArrayList<Procedure> getAllProcedures( ServletContext context,HttpSession session)
	{
		ArrayList<Procedure> procList = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getStatus = "select * from medical_procedure order by medproc_name";
				resultset = statement.executeQuery(getStatus);
				int i=0;
				while(resultset.next())
				{
					int proc_id = resultset.getInt("medproc_id");
					String proc_name = resultset.getString("medproc_name"); 
					
					
					Procedure obj = new Procedure(proc_name,proc_id);
					procList.add(i,obj);
					i++;
				}
				
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return procList;
		
		
		
		
	}
	
	
	public Consultant getConsultant( ServletContext context,HttpSession session,String fName,String lName)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		Consultant obj = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getConsultant = "select * from consultants where con_fname='" + fName + "' and con_lname='" + lName + "'";
				resultset = statement.executeQuery(getConsultant);
				
				if(resultset.next())
				{
					int con_id = resultset.getInt("con_id");
					String first_name = resultset.getString("con_fname"); 
					String last_name = resultset.getString("con_lname");
					
				   obj = new Consultant(first_name,last_name, con_id);
					
				}
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return obj;
		
		
		
		
	}
	
	public Intervention getIntervention(ServletContext context,HttpSession session,String inter_name)
	{
		

		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		Intervention obj = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getIntervention= "select * from intervention where lower(intervention_name) = lower('" + inter_name.trim()  + "')" ;
				//System.out.println("Inside get procedure :" + getProcedure);
				resultset = statement.executeQuery(getIntervention);
				
				if(resultset.next())
				{
					int intervention_id = resultset.getInt("intervention_id");
					String intervention_name = resultset.getString("intervention_name"); 
					//System.out.println("Inside getProcedure:" + medproc_name +" , " +  medproc_id);
					
					
				   obj = new Intervention(intervention_name,intervention_id);
					
				}
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
		
		}
		
		return obj;
		
	}
	
	
	
	public Vessels getVessel(ServletContext context,HttpSession session,String vessel_name)
	{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		Vessels obj = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getVessels= "select * from vessels where lower(vessel_name) = lower('" + vessel_name.trim()  + "')" ;
				//System.out.println("Inside get procedure :" + getVessels);
				resultset = statement.executeQuery(getVessels);
				
				if(resultset.next())
				{
					int ves_id = resultset.getInt("vessel_id");
					String ves_name = resultset.getString("vessel_name"); 
					//System.out.println("Inside getProcedure:" + medproc_name +" , " +  medproc_id);
					
					
				   obj = new Vessels(ves_name,ves_id);
					
				}
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
		
		}
		
		return obj;
		
	}
	
	
	
	
	
	public Impression getFinalImpression(ServletContext context,HttpSession session,String imp_name)
	{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		Impression obj = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getImpression = "select * from final_impression where lower(impression_name) = lower('" + imp_name.trim()  + "')" ;
				//System.out.println("Inside get procedure :" + getProcedure);
				resultset = statement.executeQuery(getImpression);
				
				if(resultset.next())
				{
					int imp_id = resultset.getInt("impression_id");
					String impression_name = resultset.getString("impression_name"); 
					//System.out.println("Inside getProcedure:" + medproc_name +" , " +  medproc_id);
					
					
				   obj = new Impression(impression_name,imp_id);
					
				}
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
		
		}
		
		return obj;
		
		
	}
	
	
	
	
	
	
	public Indication getIndication(ServletContext context,HttpSession session,String indic_name)
	{
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		Indication obj = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getProcedure = "select * from Indication where lower(ind_name) = lower('" + indic_name.trim()  + "')" ;
				//System.out.println("Inside get procedure :" + getProcedure);
				resultset = statement.executeQuery(getProcedure);
				
				if(resultset.next())
				{
					int ind_id = resultset.getInt("ind_id");
					String ind_name = resultset.getString("ind_name"); 
					//System.out.println("Inside getProcedure:" + medproc_name +" , " +  medproc_id);
					
					
				   obj = new Indication(ind_name,ind_id);
					
				}
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return obj;
		
		
	}
	
	
	
	public Procedure getProcedure( ServletContext context,HttpSession session,String proc_name)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		Procedure obj = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getProcedure = "select * from medical_procedure where lower(medproc_name) = lower('" + proc_name.trim()  + "')" ;
				//System.out.println("Inside get procedure :" + getProcedure);
				resultset = statement.executeQuery(getProcedure);
				
				if(resultset.next())
				{
					int medproc_id = resultset.getInt("medproc_id");
					String medproc_name = resultset.getString("medproc_name"); 
					//System.out.println("Inside getProcedure:" + medproc_name +" , " +  medproc_id);
					
					
				   obj = new Procedure(medproc_name,medproc_id);
					
				}
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return obj;
		
		
		
		
	}
	
	
	
	public ArrayList<Intervention> getAllInterventions( ServletContext context,HttpSession session)
	{
		ArrayList<Intervention> interventionList = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getIntervention = "select * from intervention order by intervention_name";
				resultset = statement.executeQuery(getIntervention);
				int i=0;
				while(resultset.next())
				{
					int int_id = resultset.getInt("intervention_id");
					String int_name = resultset.getString("intervention_name"); 
					
					
					Intervention obj = new Intervention(int_name,int_id);
					interventionList.add(i,obj);
					i++;
				}
				
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
		
		return interventionList;
		
		
		
		
	}
	
	
	public boolean isExcelFileOpen(String fileName)
	{
		 File file = new File(fileName);

	     // try to rename the file with the same name
		 File sameFileName = new File(fileName);

	    if(file.renameTo(sameFileName))
	    {
	        // if the file is renamed
	       // System.out.println("file is closed"); 
	    	return false;
	    }
	    else
	    {
	        // if the file didnt accept the renaming operation
	       // System.out.println("file is opened");
	    	return true;
	    }
	}
	
	public boolean doesExcelExist(File excelFile) throws Exception
	{
		try
		{
			
			if(excelFile.exists())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		 
			//Catch all other IO exceptions
			catch (Exception e) 
			{
				throw new Exception("Error occured when checking if file exists.." + e.getMessage());
			    //System.err.println("Caught IOException" + e.getMessage());
			}
	
	
	}
	
	public void openExcelDocument(String fileName)
	{
		try 
		{
		     Desktop.getDesktop().open(new File(fileName));
		     
		}
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}
	}
	
	public void populateExcelFromDatabase(ServletContext context,XSSFSheet sheet)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
	
		try
		{
				Class.forName("com.mysql.jdbc.Driver");
				String databaseUrl = context.getInitParameter("data-url");
				
				String databaseUser = context.getInitParameter("data-user");
				String databasePwd = context.getInitParameter("data-pwd");
				connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
				
				statement = connection.createStatement();
				
				
				String getIntervention = "select * from procedure_on_patient";
				resultset = statement.executeQuery(getIntervention);
				int i=1;
				while(resultset.next())
				{ 
					String strDate = resultset.getString("proc_date");
					String patientName = resultset.getString("patient_name");
					String patientNHI = resultset.getString("patient_nhi");
					String consultantName = resultset.getString("con_name");
					String procedureName = resultset.getString("medproc_names");
					int primaryAngioOperator = resultset.getInt("primary_operator_angio");
					String indicationName = resultset.getString("indication_names");
					String accessName = resultset.getString("access_name");
					int arterial = resultset.getInt("arterial");
					int venous = resultset.getInt("venous");
					String final_impression = resultset.getString("final_impression");
					String vessels_diseased = resultset.getString("vessels_diseased");
					String intervention = resultset.getString("intervention");
					int primary_operator = resultset.getInt("primary_op");
					int bms = resultset.getInt("bms");
					int des = resultset.getInt("des");
					int deb = resultset.getInt("deb");
					int lms = resultset.getInt("lms");
					int lad = resultset.getInt("lad");
					int cx = resultset.getInt("cx");
					int rca = resultset.getInt("rca");
					int ramus = resultset.getInt("ramus");
					int graft = resultset.getInt("graft");
					int branch_vessel = resultset.getInt("branch_vessel");
					int additional_proc = resultset.getInt("additional_proc");
					int lvgram = resultset.getInt("lvgram");
					int aortogram = resultset.getInt("aortogram");
					int bypass_angio = resultset.getInt("bypass_angio");
					int rhc = resultset.getInt("rhc");
					int hocm = resultset.getInt("hocm_assessment");
					int ivus = resultset.getInt("ivus");
					int oct = resultset.getInt("oct");
					int ffr = resultset.getInt("ffr");
					
					int angioseal = resultset.getInt("angioseal");
					int proglide = resultset.getInt("proglide");
					int balloon_pump = resultset.getInt("balloon_pump");
					int angio_sculpt = resultset.getInt("angio_sculpt");
					int rotablation = resultset.getInt("rotablation");
					int pami = resultset.getInt("pami");
					int complex_cto = resultset.getInt("complex_cto");
					int complex_bifuc = resultset.getInt("complex_bifuc");
					int complications = resultset.getInt("complications");
					String complication_desc = resultset.getString("complication_desc");
					String comments = resultset.getString("comments");
					
					saveRowInExcelSheet(sheet, i, strDate, patientName, patientNHI,consultantName,procedureName,primaryAngioOperator,indicationName,accessName,arterial,venous,final_impression,vessels_diseased,intervention,primary_operator,bms,des,deb,lms,lad,cx,rca,ramus,graft,branch_vessel,additional_proc,lvgram,aortogram,bypass_angio,rhc, hocm, ivus,oct,angioseal,proglide,balloon_pump,angio_sculpt,rotablation,pami, complex_cto,complex_bifuc,complications,complication_desc,comments);
					
					i++;
				}
				
				
				
				
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
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	
	   	 	
	   	 	
	   	 
			
			
			
		}
	}
	
	public void saveRowInExcelSheet(XSSFSheet sheet, int i, String strDate, String patientName, String patientNHI,String consultantName, String procedureName,int primaryAngioOperator,String indicationName,String accessName,int arterial,int venous,String final_impression,String vessels_diseased,String intervention,int primary_operator,int bms,int des,int deb,int lms,int lad,int cx,int rca,int ramus,int graft,int branch_vessel,int additional_proc,int lvgram,int aortogram,int bypass_angio,int rhc,int hocm, int ivus,int oct,int angioseal,int proglide,int balloon_pump,int angio_sculpt,int rotablation,int pami,int complex_cto,int complex_bifuc,int complications,String complication_desc,String comments)
	{
		
	}
	
	public void createNewExcel(File excelfile) throws IOException,FileNotFoundException
	{
		try
		{
			
			
			File parent_directory = excelfile.getParentFile();
			if(!(parent_directory.exists()))
			{
				parent_directory.mkdirs();
			}
			
			 XSSFWorkbook workbook = new XSSFWorkbook();
	     
			 XSSFSheet sheet = workbook.createSheet();
			 
			 XSSFCellStyle headerStyle = workbook.createCellStyle();
			 Font headerFont = workbook.createFont();
			 headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN .getIndex());
			 headerFont.setColor(IndexedColors.BLACK.getIndex());
			 headerFont.setBold(true);
			 headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			 headerStyle.setFont(headerFont);
			 headerStyle.setBorderBottom(BorderStyle.THIN);  
			 headerStyle.setBorderLeft(BorderStyle.THIN); 
			 headerStyle.setBorderRight(BorderStyle.THIN); 
			
			
			
			
			//Create First Row
			XSSFRow row1 = sheet.createRow(0);
			row1.setHeight( (short) 0x349 );
		
			
			
			XSSFCell r1c1 = row1.createCell(0);
			r1c1.setCellValue("S NO.");
			r1c1.setCellStyle(headerStyle);
			
			XSSFCell r1c2 = row1.createCell(1);
			r1c2.setCellValue("Date");
			r1c2.setCellStyle(headerStyle);
			
			XSSFCell r1c3 = row1.createCell(2);
			r1c3.setCellValue("Patient Name");
			r1c3.setCellStyle(headerStyle);
			
			
			
			XSSFCell r1c4 = row1.createCell(3);
			r1c4.setCellValue("NHI.");
			r1c4.setCellStyle(headerStyle);
			XSSFCell r1c5 = row1.createCell(4);
			r1c5.setCellValue("Consultant");
			r1c5.setCellStyle(headerStyle);
			XSSFCell r1c6 = row1.createCell(5);
			r1c6.setCellValue("Procedure");
			r1c6.setCellStyle(headerStyle);
			
			XSSFCell r1c7 = row1.createCell(6);
			r1c7.setCellValue("Primary Op Angio");
			r1c7.setCellStyle(headerStyle);
			XSSFCell r1c8 = row1.createCell(7);
			r1c8.setCellValue("Indication");
			r1c8.setCellStyle(headerStyle);
			XSSFCell r1c9 = row1.createCell(8);
			r1c9.setCellValue("Access");
			r1c9.setCellStyle(headerStyle);
			
			XSSFCell r1c10= row1.createCell(9);
			r1c10.setCellValue("Arterial");
			r1c10.setCellStyle(headerStyle);
			XSSFCell r1c11 = row1.createCell(10);
			r1c11.setCellValue("Venous");
			r1c11.setCellStyle(headerStyle);
			XSSFCell r1c12 = row1.createCell(11);
			r1c12.setCellValue("Angio Final Impression");
			r1c12.setCellStyle(headerStyle);
			
			XSSFCell r1c13= row1.createCell(12);
			r1c13.setCellValue("Vessels Diseased");
			r1c13.setCellStyle(headerStyle);
			XSSFCell r1c14 = row1.createCell(13);
			r1c14.setCellValue("Intervention");
			r1c14.setCellStyle(headerStyle);
			XSSFCell r1c15 = row1.createCell(14);
			r1c15.setCellValue("Primary Op");
			r1c15.setCellStyle(headerStyle);
			
			XSSFCell r1c16= row1.createCell(15);
			r1c16.setCellValue("Num of BMS");
			r1c16.setCellStyle(headerStyle);
			XSSFCell r1c17 = row1.createCell(16);
			r1c17.setCellValue("Num of DES");
			r1c17.setCellStyle(headerStyle);
			XSSFCell r1c18 = row1.createCell(17);
			r1c18.setCellValue("DEB");
			r1c18.setCellStyle(headerStyle);
			
			XSSFCell r1c19= row1.createCell(18);
			r1c19.setCellValue("LMS");
			r1c19.setCellStyle(headerStyle);
			XSSFCell r1c20 = row1.createCell(19);
			r1c20.setCellValue("LAD");
			r1c20.setCellStyle(headerStyle);
			XSSFCell r1c21 = row1.createCell(20);
			r1c21.setCellValue("Cx");
			r1c21.setCellStyle(headerStyle);
			
			XSSFCell r1c22= row1.createCell(21);
			r1c22.setCellValue("RCA");
			r1c22.setCellStyle(headerStyle);
			XSSFCell r1c23 = row1.createCell(22);
			r1c23.setCellValue("Ramus");
			r1c23.setCellStyle(headerStyle);
			XSSFCell r1c24 = row1.createCell(23);
			r1c24.setCellValue("Graft");
			r1c24.setCellStyle(headerStyle);
			
			XSSFCell r1c25= row1.createCell(24);
			r1c25.setCellValue("Branch Vessel");
			r1c25.setCellStyle(headerStyle);
			XSSFCell r1c26 = row1.createCell(25);
			r1c26.setCellValue("Additional Procedures");
			r1c26.setCellStyle(headerStyle);
			XSSFCell r1c27 = row1.createCell(26);
			r1c27.setCellValue("LV Gram");
			r1c27.setCellStyle(headerStyle);
			
			XSSFCell r1c28= row1.createCell(27);
			r1c28.setCellValue("Aortogram");
			r1c28.setCellStyle(headerStyle);
			XSSFCell r1c29 = row1.createCell(28);
			r1c29.setCellValue("Bypass Angio");
			r1c29.setCellStyle(headerStyle);
			XSSFCell r1c30 = row1.createCell(29);
			r1c30.setCellValue("RHC");
			r1c30.setCellStyle(headerStyle);
			
			XSSFCell r1c31= row1.createCell(30);
			r1c31.setCellValue("HOCM Assessment");
			r1c31.setCellStyle(headerStyle);
			XSSFCell r1c32 = row1.createCell(31);
			r1c32.setCellValue("IVUS");
			r1c32.setCellStyle(headerStyle);
			XSSFCell r1c33 = row1.createCell(32);
			r1c33.setCellValue("OCT");
			r1c33.setCellStyle(headerStyle);
			
			XSSFCell r1c34= row1.createCell(33);
			r1c34.setCellValue("FFR");
			r1c34.setCellStyle(headerStyle);
			XSSFCell r1c35 = row1.createCell(34);
			r1c35.setCellValue("Angioseal");
			r1c35.setCellStyle(headerStyle);
			XSSFCell r1c36 = row1.createCell(35);
			r1c36.setCellValue("Proglide");
			r1c36.setCellStyle(headerStyle);
			
			XSSFCell r1c37= row1.createCell(36);
			r1c37.setCellValue("Balloon Pump");
			r1c37.setCellStyle(headerStyle);
			XSSFCell r1c38 = row1.createCell(37);
			r1c38.setCellValue("Angiosculpt/Cutting");
			r1c38.setCellStyle(headerStyle);
			XSSFCell r1c39 = row1.createCell(38);
			r1c39.setCellValue("Rotablation");
			r1c39.setCellStyle(headerStyle);
			
			XSSFCell r1c40= row1.createCell(39);
			r1c40.setCellValue("PAMI/Rescue");
			r1c40.setCellStyle(headerStyle);
			XSSFCell r1c41 = row1.createCell(40);
			r1c41.setCellValue("Complex CTO");
			r1c41.setCellStyle(headerStyle);
			XSSFCell r1c42 = row1.createCell(41);
			r1c42.setCellValue("Complex Bifucation");
			r1c42.setCellStyle(headerStyle);
			
			XSSFCell r1c43= row1.createCell(42);
			r1c43.setCellValue("Complications");
			r1c43.setCellStyle(headerStyle);
			XSSFCell r1c44 = row1.createCell(43);
			r1c44.setCellValue("Complication Description");
			r1c44.setCellStyle(headerStyle);
			XSSFCell r1c45 = row1.createCell(44);
			r1c45.setCellValue("Comment");
			r1c45.setCellStyle(headerStyle);
			
			
			 
			 
	        FileOutputStream fos = new FileOutputStream(excelfile);
	        workbook.write(fos);
	        fos.close();
	        workbook.close();
		
			
			
			System.out.println("Created New Excel Document");	
				
		
			
		}
		 catch (FileNotFoundException e) 
		{
			throw new FileNotFoundException("File Not Found: "  + e.getMessage());
			// System.err.println("Caught IOException" + e.getMessage());
		}
		//Catch all other IO exceptions
		catch (IOException e) 
		{
			throw new IOException();
		    //System.err.println("Caught IOException" + e.getMessage());
		}
	}
		
	
	public void deleteAllRowsExceptFirst(XSSFSheet sheet ) throws Exception
	{

		try
		{
		
		//code to delete all rows except the first one
		 for(int i=1; i<= sheet.getLastRowNum(); i++)
		 {
			 XSSFRow row = sheet.getRow(i);
			 int lastRowNum = sheet.getLastRowNum();     
		      if(lastRowNum !=0 && lastRowNum >0)
		      {
		            int rowIndex = row.getRowNum();
		            XSSFRow removingRow = sheet.getRow(rowIndex);
		            if(removingRow != null)
		            {
		                sheet.removeRow(removingRow);
		                 //System.out.println("Deleting.... ");
		            }    
		       }
	      }
		}
		 
		//Catch all other IO exceptions
		catch (Exception e) 
		{
		    throw new Exception ("Caught Exception" + e.getMessage());
		}

		
	}
	
	 public String[] getConsultantName(ServletContext context,int consultantid) throws SQLException, Exception
	 {
		 Connection connection = null;
		 Statement statement=null;
		 ResultSet resultset = null;
		 String[] consultant_names = new String[2] ;
		 try
		 {
			 
			
			Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
			
			statement=connection.createStatement();
	        
	        String strSql="select * from consultants where con_id = " + consultantid ;
	        resultset = statement.executeQuery(strSql);
			
			if(resultset.next())
			{
				String fName = resultset.getString("con_fname");
				consultant_names[0] = fName;
				String lName = resultset.getString("con_lname");
				consultant_names[1] = lName;
				
			
			}
			return consultant_names;
		 }
		 catch(SQLException ex)
		 {
			 throw new SQLException("SQL EXCEPTION :" + ex.getMessage());
		 }
		 catch(Exception ex)
		 {
			 throw new Exception("Error :" + ex.getMessage());
		 }
		 finally
		 {
			 if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			 if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			 
		   	 	
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
				
		 }
		 
	 
	 }
	
	
	
	
	
	
	 public int getConsultantId(ServletContext context,String consultant_name_full) throws SQLException, Exception
	 {
		 Connection connection = null;
		 Statement statement=null;
		 ResultSet resultset = null;
		 int consultant_id =-1;
		 try
		 {
			 
			String[] splitStrName = consultant_name_full.trim().split("\\s+");
			String firstName = "";
			String lastName = "";
			
			 for (int i = 0; i < splitStrName.length; i++) 
			 {

		         if (i == 0) 
		         {
		        	 firstName = splitStrName[i];
		         }
		         else if (i == 1) 
		         {
		             lastName = splitStrName[i];
		         }
		         else  
		         {
		            lastName = lastName + " " +  splitStrName[i];
		         }
		     }
			 
			Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
			
			statement=connection.createStatement();
	        
	        String strSql="select * from consultants where lower (con_fname) =lower('" + firstName  + "') and lower(con_lname) = lower('" + lastName + "')";
	        resultset = statement.executeQuery(strSql);
			
			if(resultset.next())
			{
				consultant_id = resultset.getInt(1);
			
			}
			return consultant_id;
		 }
		 catch(SQLException ex)
		 {
			 throw new SQLException("SQL EXCEPTION :" + ex.getMessage());
		 }
		 catch(Exception ex)
		 {
			 throw new Exception("Error :" + ex.getMessage());
		 }
		 finally
		 {
			 if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			 if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			 
		   	 	
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
				
		 }
		 
	 
	 }
	
	 public String getPatientName(ServletContext context,String patient_nhi, int patient_id) throws SQLException, Exception
	 {
		 Connection connection = null;
		 Statement statement=null;
		 ResultSet resultset = null;
		 String patient_name ="";
		 try
		 {
			 
			Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
			
			statement=connection.createStatement();
	        
	        String strSql="select patient_name from patient where patient_id = " + patient_id + " and nhi = '" + patient_nhi + "'";
	        resultset = statement.executeQuery(strSql);
			
			if(resultset.next())
			{
				patient_name = resultset.getString(1);
			
			}
			return patient_name;
		 }
		 catch(SQLException ex)
		 {
			 throw new SQLException("SQL EXCEPTION :" + ex.getMessage());
		 }
		 catch(Exception ex)
		 {
			 throw new Exception("Error :" + ex.getMessage());
		 }
		 finally
		 {
			 if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			 if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			 
		   	 	
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
				
		 }
	
	 }
	 
	 public boolean checkPatientExists(ServletContext context, String patient_nhi) throws SQLException, Exception
	 {
		 Connection connection = null;
		 Statement statement=null;
		 ResultSet resultset = null;
		 boolean exists = false;
		 try
		 {
			 
			Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
			
			statement=connection.createStatement();
	        
	        String strSql="select * from patient where lower(replace(nhi,' ','')) = lower('" +  patient_nhi + "')" ;
	        resultset = statement.executeQuery(strSql);
			
			if(resultset.next())
			{
				exists = true;
			
			}
			return exists;
		 }
		 catch(SQLException ex)
		 {
			 throw new SQLException("SQL EXCEPTION :" + ex.getMessage());
		 }
		 catch(Exception ex)
		 {
			 throw new Exception("Error :" + ex.getMessage());
		 }
		 finally
		 {
			 if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			 if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			 
		   	 	
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
				
		 }
			 
	 }
	
	
	
	 public void InsertRowInDBPatient(ServletContext context,String patient_nhi, String patient_name) throws SQLException, Exception
	 {
		 Connection connection = null;
		 PreparedStatement ps=null;
		 try
		 {
			 
			Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
			
	        String strSql="Insert into patient(patient_name,nhi) values (?,?)";
	        ps=connection.prepareStatement(strSql);
	        ps.setString(1, patient_name);
	        ps.setString(2,patient_nhi);
	       
	        ps.executeUpdate();
	        System.out.println("Values Inserted Successfully"); 
		 }
		 catch(SQLException ex)
		 {
			 throw new SQLException("SQL EXCEPTION :" + ex.getMessage());
		 }
		 catch(Exception ex)
		 {
			 throw new Exception("Error :" + ex.getMessage());
		 }
		 finally
		 {
			 if (ps != null) try { ps.close(); } catch (SQLException e) {e.printStackTrace();}

		   	 	
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
				
		 }
	 }
	
	 public void InsertRowInDBProcedure(ServletContext context,String date, String patient_name,String patient_nhi, String con_name, String medproc,int primary_angio_op,String indication, String access, int arterial, int venous, String final_imp, String diseased_vessels, String intervention, int primary_op, int bms, int des, int deb, int lms, int lad, int cx,int rca, int ramus, int graft, int branch_vessel,int additional_proc, int lvgram, int aortogram, int bypass_angio, int rhc, int hocm, int ivus, int oct,int ffr, int angioseal, int proglide, int ballon, int angiosculpt, int rotablation, int pami, int complex_cto, int complex_bifuc, int complications, String complication_desc, String comments) throws SQLException, Exception
	 {
		 Connection connection = null;
		 PreparedStatement ps=null;
		 try
		 {
			 
			Class.forName("com.mysql.jdbc.Driver");
			String databaseUrl = context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
			connection =(Connection) DriverManager.getConnection(databaseUrl, databaseUser, databasePwd);
			
			Statement stmt=connection.createStatement();
	        
	        String sql="Insert into procedure_on_patient(proc_date,patient_name , patient_nhi ,con_name,medproc_names,primary_operator_angio,indication_names,access_name,arterial,venous,final_impression,vessels_diseased,intervention,primary_op,bms,des,deb,lms,lad,cx,rca,ramus,graft,branch_vessel,additional_proc,lvgram,aortogram,bypass_angio,rhc,hocm_assessment,ivus,oct,ffr,angioseal,proglide,balloon_pump,angio_sculpt,rotablation,pami,complex_cto,complex_bifuc,complications,complication_desc,comments) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	        ps=connection.prepareStatement(sql);
	        ps.setString(1, date);
	        ps.setString(2,patient_name);
	        ps.setString(3, patient_nhi);
	        ps.setString(4, con_name);
	        ps.setString(5, medproc);
	        ps.setInt(6, primary_angio_op);
	        ps.setString(7, indication);
	        ps.setString(8, access);
	        ps.setInt(9, arterial);
	        ps.setInt(10, venous);
	        ps.setString(11, final_imp);
	        ps.setString(12, diseased_vessels);
	        ps.setString(13, intervention);
	        ps.setInt(14, primary_op);
	        ps.setInt(15, bms);
	        ps.setInt(16, des);
	        ps.setInt(17, deb);
	        ps.setInt(18, lms);
	        ps.setInt(19, lad);
	        ps.setInt(20, cx);
	        ps.setInt(21, rca);
	        ps.setInt(22, ramus);
	        ps.setInt(23, graft);
	        ps.setInt(24, branch_vessel);
	        ps.setInt(25, additional_proc);
	        ps.setInt(26, lvgram);
	        ps.setInt(27, aortogram);
	        ps.setInt(28, bypass_angio);
	        ps.setInt(29, rhc);
	        ps.setInt(30, hocm);
	        ps.setInt(31, ivus);
	        ps.setInt(32, oct);
	        ps.setInt(33, ffr);
	        ps.setInt(34, angioseal);
	        ps.setInt(35, proglide);
	        ps.setInt(36, ballon);
	        ps.setInt(37, angiosculpt);
	        ps.setInt(38, rotablation);
	        ps.setInt(39, pami);
	        ps.setInt(40, complex_cto);
	        ps.setInt(41, complex_bifuc);
	        ps.setInt(42, complications);
	       
	        ps.setString(43, complication_desc);
	        ps.setString(44, comments);
	        ps.executeUpdate();
	       // System.out.println("Values Inserted Successfully"); 
		 }
		 catch(SQLException ex)
		 {
			 throw new SQLException("SQL EXCEPTION :" + ex.getMessage());
		 }
		 catch(Exception ex)
		 {
			 throw new Exception("Error :" + ex.getMessage());
		 }
		 finally
		 {
			 if (ps != null) try { ps.close(); } catch (SQLException e) {e.printStackTrace();}

		   	 	
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
				
		 }
	       
	    }
	
	 
	 	public  boolean isCellEmpty(Cell cell) 
	 	{
		    if (cell == null) { // use row.getCell(x, Row.CREATE_NULL_AS_BLANK) to avoid null cells
		        return true;
		    }

		    if (cell.getCellType() == CellType.BLANK) {
		        return true;
		    }

		    if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().trim().isEmpty()) {
		        return true;
		    }

		    return false;
		}
	
}
