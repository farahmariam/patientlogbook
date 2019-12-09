<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<img src="../images/sky.jpg"  style="width:1500px;height:180px;">

<%

ServletContext context = request.getServletContext();
String loginUrl = context.getInitParameter("login-url");


if ((session.getAttribute("loginname") == null) || (session.getAttribute("loginname") == ""))
{
	
	
%>
<h3 class="settingText">You are not logged in!</h3> <br/>
 <a href="<%=loginUrl %>" class="three" >Please Login..</a> 
	
<%
 	}
	else
	{
	

%>

	<h3 class="settingText">The Excel File is already open!Please close it and try again!</h3> <br/>
 <a href="mainPage.jsp" class="three" >Go to the Main page and try again</a> 

<%
	}
%>
</body>
</html>