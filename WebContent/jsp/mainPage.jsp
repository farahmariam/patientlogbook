<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Patient Logbook Main Page</title>
<link rel="stylesheet" href="../css/scheduleStyles.css">
</head>
<body >
<img src="../images/sky.jpg"  style="width:1500px;height:180px;">

<%

ServletContext context = request.getServletContext();
String loginUrl = context.getInitParameter("login-url");

int userId = -1;
String userFullNameFromSession = "";
String loginName="";



if ((session.getAttribute("loginname") == null) || (session.getAttribute("loginname") == ""))
{
	
	
%>
<h3 class="settingText">You are not logged in!</h3> <br/>
 <a href="<%=loginUrl %>" class="three" >Please Login..</a> 
	
<%
 	}
	else
	{
	userId =(Integer)session.getAttribute("userid");
	userFullNameFromSession = (String)session.getAttribute("username"); 
	
	loginName = (String)session.getAttribute("loginname");
	
	
	String logbookUrl = context.getInitParameter("logbook-url");
	String logbookInResponseUrl = context.getInitParameter("logbook-in-response-url");

%>

<form name="frm" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
	    <td width="90%" class="two">
	     <h3 class="indexSettings">Welcome, <%=userFullNameFromSession  %>.</h3>
	    </td>
	    <td>
	    <select name="adminmenu" id ="adminmenu" onchange="goToNewPage()" class="options">
	    
	    <option value=""  selected> <h4 > Actions</h4> </option>
	    <option value="dataEntry.jsp"> ADD PATIENT DATA  </option>
	    <option value="<%= logbookInResponseUrl%>"> DOWNLOAD DATA IN EXCEL</option>
	    <!--  <option value="<%= logbookUrl%>"> VIEW DATA IN LOGBOOK</option>-->
	     <option value="showDataOnWeb.jsp"> VIEW AND EDIT PATIENT DATA ON WEB  </option>
	    <option value="logout.jsp"> LOGOUT </option>
	    </select>
	    </td>
	    
	    
</tr>

</table>
</form>  
<%

	}
%>  

</body>
<script type="text/javascript">



function goToNewPage()
{
	var dropdown = document.forms["frm"]["adminmenu"];
	var url = dropdown.value;
	 if (url != "")
	 {
		 if(url=="statusReport.jsp" || url=="Reports.jsp" || url=="showLog.jsp")
			 {
			 window.open(url,'_blank');
			 dropdown.value="";
			 }
		 else
			 {
			 	window.open(url,'_self');
			 }
	 	
	 }
	

	
	}
	</script>
</html>