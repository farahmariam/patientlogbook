<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/scheduleStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<img src="../images/sky.jpg"  style="width:1500px;height:180px;">


<%

String errormessage = "";
if(session.getAttribute("errormessage")!=null && session.getAttribute("errormessage")!="")
{
	errormessage = (String)session.getAttribute("errormessage");	
}


%>


 <div class="header">
    <p class="settingHeader"> Error Occurred!</p>
    <p><%=errormessage %> </p>
	<p >Please Try again Here -  <a href='index.jsp' class="three">Go to Login</a></p>
 </div>
	
</body>
</html>