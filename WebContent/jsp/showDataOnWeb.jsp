<%@ page import ="java.sql.*" %>
<%@ page import ="java.lang.Integer" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View and Edit Logbook Data</title>
</head>
<link rel="stylesheet" href="../css/tableStyles.css">
<body>
<%
	ServletContext context = request.getServletContext();
	String loginUrl = context.getInitParameter("login-url");
	
	if ((session.getAttribute("loginname") == null) || (session.getAttribute("loginname") == ""))
	{
%>
		You are not logged in<br/>
		<a href="<%=loginUrl %>" class="three">Please Login</a>

<%
	}
	else 
	{

	
		String strDate = "";
		String patientName = "";
		String patientNHI = "";
		String consultantName = "";
		String procedureName = "";
		int primaryAngioOperator = 0;
		String indicationName = "";
		String accessName = "";
		int arterial = 0;
		int venous = 0;
		String final_impression = "";
		String vessels_diseased = "";
		String intervention = "";
		int primary_operator = 0;
		int bms = 0;
		int des = 0;
		int deb = 0;
		int lms = 0;
		int lad = 0;
		int cx = 0;
		int rca = 0;
		int ramus = 0;
		int graft = 0;
		int branch_vessel = 0;
		int additional_proc = 0;
		int lvgram = 0;
		int aortogram = 0;
		int bypass_angio = 0;
		int rhc = 0;
		int hocm = 0;
		int ivus = 0;
		int oct = 0;
		int ffr = 0;
		
		int angioseal = 0;
		int proglide = 0;
		int balloon_pump = 0;
		int angio_sculpt = 0;
		int rotablation = 0;
		int pami = 0;
		int complex_cto = 0;
		int complex_bifuc = 0;
		int complications = 0;
		String complication_desc = "";
		String comments ="";
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection= null;
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
			 
		%>	
		
		 <div class="header  ">
		 <a href='mainPage.jsp' class="three" title="Go to Main Page">Goto Main Page</a>
		
		 </div>
	 
						<table cellspacing="0" cellpadding="1" border="1" width="2000"  >
							<tr>
								<th class="bold8" bgcolor="e29d89">S.NO</th>
								<th class="bold8" bgcolor="e29d89">Date</th>
								<!--  <th class="bold8" bgcolor="e29d89">Patient Name</th>-->
								<th class="bold8" bgcolor="e29d89">Patient NHI</th>
								<th class="bold8" bgcolor="e29d89">Consultant</th>
								<th class="bold8" bgcolor="e29d89">Procedure</th>
								<th class="bold8" bgcolor="e29d89">Primary Angio Operator</th>
								<th class="bold8" bgcolor="e29d89">Indication</th>
								<th class="bold8"bgcolor="e29d89">Access</th>
								<th class="bold8" bgcolor="e29d89">Arterial</th>
								<th class="bold8" bgcolor="e29d89">Venous</th>
								<th class="bold8" bgcolor="e29d89">Angio Final Impression</th>
								
								<th class="bold8" bgcolor="e29d89">Vessels Diseased</th>
								<th class="bold8" bgcolor="e29d89">Intervention</th>
								<th class="bold8" bgcolor="e29d89">Primary Operator</th>
								<th class="bold8" bgcolor="e29d89">BMS</th>
								<th class="bold8" bgcolor="e29d89">DES</th>
								<th class="bold8" bgcolor="e29d89">DEB</th>
								<th class="bold8" bgcolor="e29d89">LMS</th>
								<th class="bold8" bgcolor="e29d89">LAD</th>
								<th class="bold8" bgcolor="e29d89">Cx</th>
								<th class="bold8" bgcolor="e29d89">RCA</th>
								<th class="bold8" bgcolor="e29d89">Ramus</th>
								
								<th class="bold8" bgcolor="e29d89">Graft</th>
								<th class="bold8" bgcolor="e29d89">Branch Vessel</th>
								<th class="bold8"bgcolor="e29d89">Additional Procedures</th>
								<th class="bold8" bgcolor="e29d89">LV Gram</th>
								<th class="bold8" bgcolor="e29d89">Aortogram</th>
								<th class="bold8" bgcolor="e29d89">Bypass Angio</th>
								<th class="bold8" bgcolor="e29d89">RHC</th>
								<th class="bold8" bgcolor="e29d89">HOCM Assessment</th>
								<th class="bold8"bgcolor="e29d89">IVUS</th>
								<th class="bold8" bgcolor="e29d89">OCT</th>
								<th class="bold8" bgcolor="e29d89">FFR</th>
								<th class="bold8" bgcolor="e29d89">Angioseal</th>
								
								<th class="bold8" bgcolor="e29d89">Proglide</th>
								<th class="bold8" bgcolor="e29d89">Balloon Pump</th>
								<th class="bold8"bgcolor="e29d89">Angio Sculpt/cutting</th>
								<th class="bold8" bgcolor="e29d89">Rotablation</th>
								<th class="bold8" bgcolor="e29d89">PAMI/Rescue</th>
								<th class="bold8" bgcolor="e29d89">Complex CTO</th>
								<th class="bold8" bgcolor="e29d89">Complex Bifucation</th>
								<th class="bold8"bgcolor="e29d89">Complications</th>
								<th class="bold8" bgcolor="e29d89">Complication Description</th>
								<th class="bold8" bgcolor="e29d89">Comments</th>
								
							</tr>
						
				
				
					
	<%
		while(resultset.next())
		{	if(resultset.getString("proc_date")!="" && resultset.getString("proc_date")!=null)
			strDate = resultset.getString("proc_date");
		
			if(resultset.getString("patient_name")!="" && resultset.getString("patient_name")!=null)
			patientName = resultset.getString("patient_name");
			else
			patientName = "";	
			
			if(resultset.getString("patient_nhi")!="" && resultset.getString("patient_nhi")!=null)
			patientNHI = resultset.getString("patient_nhi");
			
			if(resultset.getString("con_name")!="" && resultset.getString("con_name")!=null)
			consultantName = resultset.getString("con_name");

			if(resultset.getString("medproc_names")!="" && resultset.getString("medproc_names")!=null)
			procedureName = resultset.getString("medproc_names");
			
			
			primaryAngioOperator = resultset.getInt("primary_operator_angio");
			
			if(resultset.getString("indication_names")!="" && resultset.getString("indication_names")!=null)
			indicationName = resultset.getString("indication_names");
			
			if(resultset.getString("access_name")!="" && resultset.getString("access_name")!=null)
			accessName = resultset.getString("access_name");
			
			
			arterial = resultset.getInt("arterial");
			
			venous = resultset.getInt("venous");
			
			if(resultset.getString("final_impression")!="" && resultset.getString("final_impression")!=null)
			final_impression = resultset.getString("final_impression");
			
			if(resultset.getString("vessels_diseased")!="" && resultset.getString("vessels_diseased")!=null)
			vessels_diseased = resultset.getString("vessels_diseased");
			
			if(resultset.getString("intervention")!="" && resultset.getString("intervention")!=null)
			intervention = resultset.getString("intervention");
			
			
			primary_operator = resultset.getInt("primary_op");
			bms = resultset.getInt("bms");
			des = resultset.getInt("des");
			deb = resultset.getInt("deb");
			lms = resultset.getInt("lms");
			lad = resultset.getInt("lad");
			cx = resultset.getInt("cx");
			rca = resultset.getInt("rca");
			ramus = resultset.getInt("ramus");
			graft = resultset.getInt("graft");
			branch_vessel = resultset.getInt("branch_vessel");
			additional_proc = resultset.getInt("additional_proc");
			lvgram = resultset.getInt("lvgram");
			aortogram = resultset.getInt("aortogram");
			bypass_angio = resultset.getInt("bypass_angio");
			rhc = resultset.getInt("rhc");
			hocm = resultset.getInt("hocm_assessment");
			ivus = resultset.getInt("ivus");
			oct = resultset.getInt("oct");
			ffr = resultset.getInt("ffr");
			
			angioseal = resultset.getInt("angioseal");
			proglide = resultset.getInt("proglide");
			balloon_pump = resultset.getInt("balloon_pump");
			angio_sculpt = resultset.getInt("angio_sculpt");
			rotablation = resultset.getInt("rotablation");
			pami = resultset.getInt("pami");
			complex_cto = resultset.getInt("complex_cto");
			complex_bifuc = resultset.getInt("complex_bifuc");
			complications = resultset.getInt("complications");
			complication_desc = resultset.getString("complication_desc");
			comments = resultset.getString("comments");
	
	
	
	%>


	<tr>
	
	
							<td class="pt8"><%=i %></td>
							<td class="pt8"><%=strDate %></td>
							<!--  <td class="pt8"><b> <a href="edit.jsp" class="one" onclick="window.open('edit.jsp?nhi=<%=patientNHI %>&name=<%=patientName%>&date=<%=strDate %>&consultant=<%=consultantName %>&procedure=<%=procedureName %>&priAngOp=<%=primaryAngioOperator %>&indication=<%=indicationName %>&access=<%=accessName %>&arterial=<%=arterial %>&venous=<%=venous %>&finalimp=<%=final_impression %>&vessels=<%=vessels_diseased %>&intervention=<%=intervention %>&primOp=<%=primary_operator %>&bms=<%=bms %>&des=<%=des %>&deb=<%=deb %>&lms=<%=lms %>&lad=<%=lad %>&cx=<%=cx %>&rca=<%=rca %>&ramus=<%=ramus %>&graft=<%=graft %>&branchvessel=<%=branch_vessel %>&additionalproc=<%=additional_proc %>&lvgram=<%=lvgram %>&aortogram=<%=aortogram %>&bypassangio=<%=bypass_angio %>&rhc=<%=rhc %>&hocm=<%=hocm %>&ivus=<%=ivus %>&oct=<%=oct %>&ffr=<%=ffr %>&angioseal=<%=angioseal %>&proglide=<%=proglide %>&balloon=<%=balloon_pump %>&angiosculpt=<%=angio_sculpt %>&rotablation=<%=rotablation %>&pami=<%=pami %>&complexcto=<%=complex_cto %>&complexbifuc=<%=complex_bifuc %>&complication=<%=complications %>&compdesc=<%=complication_desc %>&comments=<%=comments %>', '_self', 'width=300, height=250'); return false;" title="Click here to edit <%=patientName %>"> <%=patientName %> </a></b></td>-->
							<td class="pt8"><b> <a href="edit.jsp" class="one" onclick="window.open('edit.jsp?nhi=<%=patientNHI %>&name=<%=patientName%>&date=<%=strDate %>&consultant=<%=consultantName %>&procedure=<%=procedureName %>&priAngOp=<%=primaryAngioOperator %>&indication=<%=indicationName %>&access=<%=accessName %>&arterial=<%=arterial %>&venous=<%=venous %>&finalimp=<%=final_impression %>&vessels=<%=vessels_diseased %>&intervention=<%=intervention %>&primOp=<%=primary_operator %>&bms=<%=bms %>&des=<%=des %>&deb=<%=deb %>&lms=<%=lms %>&lad=<%=lad %>&cx=<%=cx %>&rca=<%=rca %>&ramus=<%=ramus %>&graft=<%=graft %>&branchvessel=<%=branch_vessel %>&additionalproc=<%=additional_proc %>&lvgram=<%=lvgram %>&aortogram=<%=aortogram %>&bypassangio=<%=bypass_angio %>&rhc=<%=rhc %>&hocm=<%=hocm %>&ivus=<%=ivus %>&oct=<%=oct %>&ffr=<%=ffr %>&angioseal=<%=angioseal %>&proglide=<%=proglide %>&balloon=<%=balloon_pump %>&angiosculpt=<%=angio_sculpt %>&rotablation=<%=rotablation %>&pami=<%=pami %>&complexcto=<%=complex_cto %>&complexbifuc=<%=complex_bifuc %>&complication=<%=complications %>&compdesc=<%=complication_desc %>&comments=<%=comments %>', '_self', 'width=300, height=250'); return false;" title="Click here to edit patient with NHI: <%=patientNHI %>"> <%=patientNHI %> </a></b></td>
							
							<td class="pt8"><%=consultantName %></td>							
							<td class="pt8"><%=procedureName %></td>						
							<td class="pt8"><%=primaryAngioOperator %></td>
							
							<td class="pt8"><%=indicationName %></td>
							<td class="pt8"><%=accessName %></td>
							<td class="pt8"><%=arterial %></td>
							<td class="pt8"><%=venous %></td>							
							<td class="pt8"><%=final_impression %></td>						
							<td class="pt8"><%=vessels_diseased %></td>
							
							<td class="pt8"><%=intervention %></td>
							<td class="pt8"><%=primary_operator %></td>
							<td class="pt8"><%=bms %></td>
							<td class="pt8"><%=des %></td>							
							<td class="pt8"><%=deb %></td>						
							<td class="pt8"><%=lms%></td>
							
							<td class="pt8"><%=lad %></td>
							<td class="pt8"><%=cx %></td>
							<td class="pt8"><%=rca %></td>
							<td class="pt8"><%=ramus %></td>							
							<td class="pt8"><%=graft %></td>						
							<td class="pt8"><%=branch_vessel%></td>

							<td class="pt8"><%=additional_proc %></td>
							<td class="pt8"><%=lvgram %></td>
							<td class="pt8"><%=aortogram %></td>
							<td class="pt8"><%=bypass_angio %></td>							
							<td class="pt8"><%=rhc %></td>						
							<td class="pt8"><%=hocm%></td>
							
							<td class="pt8"><%=ivus %></td>
							<td class="pt8"><%=oct %></td>
							<td class="pt8"><%=ffr %></td>
							<td class="pt8"><%=angioseal %></td>							
							<td class="pt8"><%=proglide %></td>						
							<td class="pt8"><%=balloon_pump%></td>
							
			
							<td class="pt8"><%=angio_sculpt %></td>
							<td class="pt8"><%=rotablation %></td>
							<td class="pt8"><%=pami %></td>
							<td class="pt8"><%=complex_cto %></td>							
							<td class="pt8"><%=complex_bifuc %></td>						
							<td class="pt8"><%=complications%></td>
							<td class="pt8"><%=complication_desc %></td>						
							<td class="pt8"><%=comments%></td>
		</tr>

		
	<%
			i++;
			}
				
		}
	
		catch(SQLException se)
		{
			//Handle errors for JDBC
			 se.printStackTrace();
		}
		catch(Exception e)
		{
			 //Handle errors for Class.forName
				e.printStackTrace();
		}
		finally
		{
		
		if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
		if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
		
		if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		
		}

	
	
	
	%>
				
		</table>
		
	
	


</body>

<%

	}
%>
</html>