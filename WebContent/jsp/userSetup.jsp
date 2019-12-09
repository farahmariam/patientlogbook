<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/scheduleStyles.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Setup Login Details</title>
</head>
<body>
<form name="settingform" method="post" action="${pageContext.request.contextPath}/CreateUser" onsubmit="return validate()" onkeypress="stopSubmitOnEnter(window.event);">
        

   
    <center>
	<img src="../images/sky.jpg"  style="width:1500px;height:180px;">

 		<table class="settingstable" border="1" width="80%" cellpadding="5" id="tableDatabase" >
				 	<th colspan="2" class="header  ">
				     <h3 class="settingHeader">ADD NEW USER </h3> 
				    </th>
				    
               		<tr>
                        <td class="index">First Name</td>
                        <td><input type="text" name="fname" id="fname" value=""  onClick="clearSpan('divfname')"  /><span id="divfname"></span></td>
                    </tr>
					<tr>
                        <td class="index">Last Name</td>
                        <td><input type="text" name="lname" id="lname" value=""  onClick="clearSpan('divlname')"  /><span id="divlname"></span></td>
                    </tr>
					<tr>
                        <td class="index"> Email Id</td>
                        <td><input type="text" name="adminemail" id="adminemail"  value="" onBlur="checkEmail()" onClick="clearSpan('divadminmail')" /><span id="divadminmail"></span></td>
                    </tr>
					<tr>
                        <td class="index">Login User Id/Name</td>
                        <td><input type="text" name="adminname" id="adminname" value=""  onBlur="checkLoginIdExists()" onClick="clearSpan('divadminname')"  /><span id="divadminname"></span></td>
                    </tr>
					<tr>
                        <td class="index">Login User Password</td>
                        <td><input type="password" name="adminpass" id="adminpass" value=""  onBlur="checkPasswordMatch()" onClick="changePwdBoxStyle()"  /><span id="divadminpass"></span></td>
                    </tr>
					<tr>
                        <td class="index">Confirm Password</td>
                        <td><input type="password" name="confirmpass" id="confirmpass" value="" onBlur="checkPasswordMatch()" onClick="changePwdBoxStyle()"/><span id="pwdMatch"></span></td>
                    </tr>
              

					
		</table>
		
		 <table  border="0" width="80%" cellpadding="5">
		
		  <tr>
             <td class="bold10"><input type="Submit" value="Save User" class="skedSetup"/></td>
          </tr>
		</table>
	
    </center>
 

       
    

 </form>

</body>




<script type='text/javascript' >

function validate()
{
	
	var firstName=document.getElementById("fname").value;
	var lastName = document.getElementById("lname").value;
	
	var loginName = document.getElementById("adminname").value;
	var loginPass = document.getElementById("adminpass").value;
	var cpass = document.getElementById("confirmpass").value;
	
	var userEmail = document.getElementById("adminemail").value;
	
	//checking first name
	if(firstName==null || firstName.trim()=="")
	{
		document.getElementById("divfname").style.color = "red";
        document.getElementById("divfname").innerHTML = "Please enter first name.";
		return false;
	}
	
	//emailchecking
	if(userEmail==null || userEmail.trim()=="")
	{
		document.getElementById("divadminmail").style.color = "red";
        document.getElementById("divadminmail").innerHTML = "Please enter admin email id.";
		return false;
	}
	
	var isEmail = checkEmail();
	if(isEmail==false)
	{
		return false;
	}
	
	var isEmailExists = checkEmailIdExists();
	if(isEmailExists==false)
	{
		return false;
	}
	
	
	
	//checking login id
	if(loginName==null || loginName.trim()=="")
	{
		document.getElementById("divadminname").style.color = "red";
        document.getElementById("divadminname").innerHTML = "Please enter login id.";
		return false;
	}
	
	//checking login password
	if(loginPass==null || loginPass.trim()=="")
	{
		document.getElementById("divadminpass").style.color = "red";
        document.getElementById("divadminpass").innerHTML = "Please enter login password.";
		return false;
	}
	
	//checking confirm password
    if (cpass == null || cpass == "")
	{
        document.getElementById("pwdMatch").style.color = "red";
		document.getElementById("pwdMatch").innerHTML = "Please confirm password.";
		return false;
    }
	
	if (loginPass != cpass) 
	{
		document.getElementById("pwdMatch").innerHTML = "Passwords do not match!";
	    document.forms["settingform"]["adminpass"].style.borderColor = "#E34234";
	    document.forms["settingform"]["confirmpass"].style.borderColor = "#E34234";
	    return false;
	}
	
	
	
	
}

function checkEmail()
{
	clearSpan("divadminmail");
	
	var email =  document.forms["settingform"]["adminemail"].value;
	if(email!="")
	{
		var reEmail = /^(?:[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+\.)*[\w\!\#\$\%\&\'\*\+\-\/\=\?\^\`\{\|\}\~]+@(?:(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9\-](?!\.)){0,61}[a-zA-Z0-9]?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9\-](?!$)){0,61}[a-zA-Z0-9]?)|(?:\[(?:(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\.){3}(?:[01]?\d{1,2}|2[0-4]\d|25[0-5])\]))$/;

	  	if(!email.match(reEmail)) 
	  	{
	  		document.getElementById("divadminmail").style.color = "red";
	        document.getElementById("divadminmail").innerHTML = "INVALID EMAIL";
	        //document.forms["settingform"]["adminemail"].value = "";
	        return false;
	  	}
		
     }
	
	
}

function checkEmailIdExists()
{
	
	var email =  document.forms["settingform"]["adminemail"].value;
	
	//ajax to check if the login id exists!
	if (window.XMLHttpRequest) 
	{
		   xmlhttp = new XMLHttpRequest();
	} else 
	{
		    // code for IE6, IE5
		    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	

	var returnValue = "0";
	
	 xmlhttp.onreadystatechange = function()
   {
       if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
       {
       	var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

       	
       	var emailExists  = "exists";
	    	var emailNotExists = "not exists";
	    	
			//alert(trimmedResponse);
	    	if(trimmedResponse.toLowerCase()  == emailExists.toLowerCase())
	    	{
	    		document.getElementById("divadminmail").style.color = "red";
		        document.getElementById("divadminmail").innerHTML = "The email is associated with another user!";
		        document.getElementById("adminemail").value = "";
		        returnValue="1";
			}
	    	else if(trimmedResponse.toLowerCase()  == emailExists.toLowerCase())
	   		{
	    		returnValue="0";
	   		}
           
          
       }
	};
	
	try
   {
	 
	 var posturl = "email=" + email;
   
	 
	 xmlhttp.open("POST", "${pageContext.request.contextPath}/checkEmailExists", false);
	
	 xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 xmlhttp.send(posturl);
	   
	   
	}
   catch(e)
   {
   	alert("unable to connect to server");
   }	
   
   if(returnValue=="1")
	{
		// The user email is not the one provided. so we cant let submit  work. hence return false
		
		return false;
		
	}
   else
	{
   	return true;
	}

	
	
	
	
	
	
}

function checkLoginIdExists()
{
	
	var loginName = document.getElementById("adminname").value;
	
	//ajax to check if the login id exists!
	if (window.XMLHttpRequest) 
	{
		   xmlhttpobj = new XMLHttpRequest();
	} else 
	{
		    // code for IE6, IE5
		    xmlhttpobj = new ActiveXObject("Microsoft.XMLHTTP");
	}
	

	xmlhttpobj.onreadystatechange = function()
    {
        if(xmlhttpobj.readyState == 4 && xmlhttpobj.status == 200)
        {
        	var trimmedResponse = xmlhttpobj.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

        	var exists  = "exists";
        	var error = "error";
        	var notexists = "not exists";
        	
        	if(trimmedResponse.toLowerCase()==exists.toLowerCase())
        	{
        		
        		document.getElementById("divadminname").style.color = "red";
        		document.getElementById("divadminname").innerHTML = "Login id Exists! Choose another name!";
        		document.getElementById("adminname").value = "";
        		return false;
        		
        		
        	}
        	
           
        }
	};

	 try
	    {
		  
		 var posturl = "loginid=" + loginName ;
	    
		 
		 xmlhttpobj.open("POST", "${pageContext.request.contextPath}/CheckUserExists", true);
		
		 xmlhttpobj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		 xmlhttpobj.send(posturl);
		   
		   
		}
	    catch(e)
	    {
	    	alert("unable to connect to server");
	    	return false;
	    }
	    
	
	
	
}


function checkPasswordMatch()
{
			clearSpan("pwdMatch");
			var z = document.forms["settingform"]["adminpass"].value;
			var cpass = document.forms["settingform"]["confirmpass"].value;
	    	if (z != null && z != "" && cpass != null && cpass != "" )
			{
	        	if (z != cpass) 
				 	{
						document.getElementById("pwdMatch").style.color = "red";
        				document.getElementById("pwdMatch").innerHTML = "PASSWORDS DO NOT MATCH";
        				document.forms["settingform"]["adminpass"].value = "";
        				document.forms["settingform"]["confirmpass"].value = "";
				        
				        document.forms["settingform"]["adminpass"].style.borderColor = "#E34234";
				        document.forms["settingform"]["confirmpass"].style.borderColor = "#E34234";
				        
				    }

	    	}
			
	    	
	
	
	
}


function changePwdBoxStyle()
{
	
	document.forms["settingform"]["adminpass"].style.borderColor = "";
    document.forms["settingform"]["confirmpass"].style.borderColor = "";
    clearSpan("pwdMatch");
    clearSpan("divadminpass");
	
}
	
function clearSpan(elementId)
{
	document.getElementById(elementId).innerHTML ="";	
}

function stopSubmitOnEnter (e)
{
	  var eve = e || window.event;
	  var keycode = eve.keyCode || eve.which || eve.charCode;

	  if (keycode == 13) 
	  {
	    eve.cancelBubble = true;
	    eve.returnValue = false;

	    if (eve.stopPropagation) 
	    {   
	      eve.stopPropagation();
	      eve.preventDefault();
	    }

	    return false;
	  }
}
</script>
	
</html>