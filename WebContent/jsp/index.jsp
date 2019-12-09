<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Patient Logbook Entry</title>
<link rel="stylesheet" href="../css/scheduleStyles.css">
</head>
<body>

<form name="login" method="post" action="${pageContext.request.contextPath}/Login" onsubmit="return validateForm()">
            <center>
<img src="../images/sky.jpg"  style="width:1500px;height:180px;">
<table cellspacing="2" cellpadding="5" border="2" width="500">
				<thead class="backcolourpurple bold8">
                    <tr>
                        <th colspan="2">Login Here</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="index">Login Id </td>
                        <td><input type="text" name="uname" value=""   /></td>
                    </tr>
                    <tr>
                        <td class="index">Password</td>
                        <td><input type="password" name="pass" value="" /></td>
                    </tr>
 					
					
                    <tr>
                        <td ><input type="submit" value="Login" /></td>
                        <td ><input type="reset" value="Reset" /></td>
                    </tr>
					
                   
                </tbody>
            </table>

			<table cellspacing="2" cellpadding="5" border="2" width="500">
					<tr>
                        <td ><a href='forgotPassword.jsp' class="three">Forgot Password and Id?</a></td>
                        <td ><a href='userSetup.jsp' class="three">First Time User? Please Setup User.</a></td>
                    </tr>
			</table>
            </center>
        </form>

</body>
<script type="text/javascript">
function validateForm() 
{
	    var x = document.forms["login"]["uname"].value;
	    if (x == null || x == "")
			{
	        alert(" User name must be filled out");
	        return false;
	   		 }
		var y = document.forms["login"]["pass"].value;
	    if (y == null || y == "")
			{
	        alert(" Password must be filled out");
	        return false;
	    	}
		
	
		

}
function checkEmpty(name) {
	  var x = document.forms["login"][name].value;
	    if (x == null || x == "")
			{
	        alert("This field cannot be empty! ");  
	document.forms["login"][name].focus();
	
	        return false;
	   		 }
}


</script>


</body>
</html>