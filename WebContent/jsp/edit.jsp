<%@ page import ="java.sql.*" %>
<%@ page import ="java.util.*" %>
<%@ page import ="trilane.Consultant" %>
<%@ page import ="trilane.CommonDatabase" %>
<%@ page import ="trilane.Procedure" %>
<%@ page import ="trilane.Indication" %>
<%@ page import ="trilane.Impression" %>
<%@ page import ="trilane.Vessels" %>
<%@ page import ="trilane.Intervention" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/tableStyles.css">
<link rel="stylesheet" href="../css/scheduleStyles.css">
<title>Edit Patient Details</title>
</head>
<body onload="setDate()">

<%
	ServletContext context = request.getServletContext();
	String loginUrl = context.getInitParameter("login-url");
	ArrayList<Consultant> conList = null;
	ArrayList<Procedure> procList = null;
	ArrayList<Indication> indicList = null;
	ArrayList<Impression> finalImpList = null;
	ArrayList<Vessels> vesselsDiseasedList = null;
	ArrayList<Intervention> interventionList = null;
	
	//selected 
	ArrayList<Procedure> procListOriginalSelected = null;
	ArrayList<Indication> indicListOriginalSelected = null;
	ArrayList<Impression> finalImpListOriginalSelected= null;
	ArrayList<Vessels> vesselsDiseasedListOriginalSelected = null;
	ArrayList<Intervention> interventionListOriginalSelected = null;

	if ((session.getAttribute("loginname") == null) || (session.getAttribute("loginname") == ""))
	{
%>
		You are not logged in<br/>
		<a href="<%=loginUrl %>" class="three">Please Login</a>

<%
	}
	else 
	{
		
	
		String strDate = request.getParameter("date");
		String patientName =  request.getParameter("name");
		String patientNHI =  request.getParameter("nhi");
		String consultantName = request.getParameter("consultant");
		String procedureName = request.getParameter("procedure");
		int primaryAngioOperator = Integer.parseInt(request.getParameter("priAngOp"));
		String indicationName = request.getParameter("indication");
		String accessName = request.getParameter("access");
		int arterial =Integer.parseInt(request.getParameter("arterial"));
		int venous = Integer.parseInt(request.getParameter("venous"));
		String final_impression =request.getParameter("finalimp");
		String vessels_diseased = request.getParameter("vessels");
		String intervention = request.getParameter("intervention");
		int primary_operator = Integer.parseInt(request.getParameter("primOp"));
		int bms = Integer.parseInt(request.getParameter("bms"));
		int des = Integer.parseInt(request.getParameter("des"));
		int deb = Integer.parseInt(request.getParameter("deb"));
		int lms = Integer.parseInt(request.getParameter("lms"));
		int lad = Integer.parseInt(request.getParameter("lad"));
		int cx = Integer.parseInt(request.getParameter("cx"));
		int rca =Integer.parseInt(request.getParameter("rca"));
		int ramus = Integer.parseInt(request.getParameter("ramus"));
		int graft = Integer.parseInt(request.getParameter("graft"));
		int branch_vessel = Integer.parseInt(request.getParameter("branchvessel"));
		int additional_proc =Integer.parseInt(request.getParameter("additionalproc"));
		int lvgram = Integer.parseInt(request.getParameter("lvgram"));
		int aortogram = Integer.parseInt(request.getParameter("aortogram"));
		int bypass_angio = Integer.parseInt(request.getParameter("bypassangio"));
		int rhc = Integer.parseInt(request.getParameter("rhc"));
		int hocm = Integer.parseInt(request.getParameter("hocm"));
		int ivus =Integer.parseInt(request.getParameter("ivus"));
		int oct = Integer.parseInt(request.getParameter("oct"));
		int ffr = Integer.parseInt(request.getParameter("ffr"));
		
		int angioseal = Integer.parseInt(request.getParameter("angioseal"));
		int proglide =Integer.parseInt(request.getParameter("proglide"));
		int balloon_pump =Integer.parseInt( request.getParameter("balloon"));
		int angio_sculpt = Integer.parseInt(request.getParameter("angiosculpt"));
		int rotablation = Integer.parseInt(request.getParameter("rotablation"));
		int pami = Integer.parseInt(request.getParameter("pami"));
		int complex_cto = Integer.parseInt(request.getParameter("complexcto"));
		int complex_bifuc = Integer.parseInt(request.getParameter("complexbifuc"));
		int complications = Integer.parseInt(request.getParameter("complication"));
		String complication_desc = request.getParameter("compdesc");
		String comments =request.getParameter("comments");
		
		CommonDatabase objCommon = new CommonDatabase();
		
		//getting selected medical procedures
		String[] splitProcedure = procedureName.split(",");
		procListOriginalSelected = new ArrayList<Procedure>();
		for(int count=0;count<splitProcedure.length;count++)
		{
			String procName = splitProcedure[count];
			//out.println("the selected proc name : " + procName);
			Procedure objProcedure = objCommon.getProcedure(context, session, procName);
			//out.println("the selected proc name from procedure object: " + objProcedure.getProcedureName());
			procListOriginalSelected.add(objProcedure);
			
		}
		
		//getting selected indication ids
		String[] splitIndication = indicationName.split(",");
		indicListOriginalSelected = new ArrayList<Indication>();
		for(int count=0;count<splitIndication.length;count++)
		{
			String indicName = splitIndication[count];
			//out.println("the selected proc name : " + procName);
			Indication objIndication = objCommon.getIndication(context, session, indicName);
			indicListOriginalSelected.add(objIndication);
			
		}
		
		//getting selected impression ids
		String[] splitImpression =final_impression.split(",");
		finalImpListOriginalSelected = new ArrayList<Impression>();
		for(int count=0;count<splitImpression.length;count++)
		{
			String impName = splitImpression[count];
			//out.println("the selected proc name : " + procName);
			Impression objImpression = objCommon.getFinalImpression(context, session, impName);
			finalImpListOriginalSelected.add(objImpression);
			
		}
		
		//getting selected impression ids
		String[] splitVessels =vessels_diseased.split(",");
		vesselsDiseasedListOriginalSelected = new ArrayList<Vessels>();
		for(int count=0;count<splitVessels.length;count++)
		{
			String vesselName = splitVessels[count];
			
			if(vesselName.trim().equals("0"))
			{
				vesselName="None";
				
			}
			//out.println("the selected proc name : " + procName);
			Vessels objVessels= objCommon.getVessel(context, session, vesselName);
			vesselsDiseasedListOriginalSelected.add(objVessels);
			
		}
		
		//getting selected intervention ids
		String[] splitIntervention =intervention.split(",");
		interventionListOriginalSelected = new ArrayList<Intervention>();
		for(int count=0;count<splitIntervention.length;count++)
		{
			String interName = splitIntervention[count];
			//out.println("the selected proc name : " + procName);
			if(interName.trim().equals("0"))
			{
				interName="None";
			}
				
				
				
			Intervention objIntervention= objCommon.getIntervention(context, session, interName);
			interventionListOriginalSelected.add(objIntervention);
			
		}

	%>
	
	 <div class="header  ">
		<a href='mainPage.jsp' class="three" title="Go to Main Page">Goto Main Page</a>
		<a href='showDataOnWeb.jsp' class="three" title="Go Back to Edit Page">Go Back to Edit Page</a>
	</div>
<div class="container">
    <div class="header  ">
     <h3 class="settingHeader">DATA ENTRY INTO LOGBOOK </h3> 
    </div>
    <div class="mainbody1">
    	<form name="settingform" id="settingform" method="post" action="#" onsubmit="savePatient();return false;" >
            <center>
			 <tbody>
				
            <table class="settingstable" border="1" width="80%" cellpadding="5" id="tableDatabase">

					<!--  
               		<tr>
                        <td class="index">Patient Name</td>
                        <td><input type="text" name="patientname" id="patientname" value="<%=patientName %>"  onClick="clearSpan('divpatientname')"  /><span id="divpatientname"></span></td>
                    </tr>
					-->
					<tr>
                        <td class="index">Patient NHI</td>
                        <td><input type="text" name="patientnhi" id="patientnhi" value="<%=patientNHI %>"  readonly  /><span id="divpatientnhi"></span></td>
                    </tr>
					<tr>
                        <td class="index">Date of Procedure</td>
                        <td><input type="date" name="date" id="datepicker"  /><span id="divdate"></span> </td>
					</tr>
					<tr>
						<td class="index"> Consultant </td>
						<td> 
           					<select name="consultant" id="consultant" onChange="showNewConsultantDiv()" >
	           				<%
           						
           					    conList = objCommon.getAllConsultants(context, session);
           					    int con_Id=-1;
           					    String con_firstname="";
           					 	String con_lastname="";
           					 	
           					 	String conFullName="";
           					    
           						//Get the consultants
           						for(int loop=0; loop<conList.size(); loop++)
           						{
           							Consultant conObject = conList.get(loop);
           							con_Id = conObject.getConsultantId();
           							con_firstname = conObject.getConsultantfName();
           							con_lastname = conObject.getConsultantlName();
           							
           							conFullName = con_firstname + " " + con_lastname;
           							
           							
           							
           							if(conFullName.equalsIgnoreCase(consultantName))
           							{
           									
           								
           								
           					%>
           								<option value="<%=con_Id  %>" selected><%=conFullName.toUpperCase()  %> </option>
           					
           					<% 			
           							}
           							else
           							{
           									
           									
           					%>
           					
           								<option value="<%=con_Id  %>" ><%=conFullName.toUpperCase()  %> </option>
           					
           					<% 
           							}
           								
           						}
           						
           						
           					%>
           					<option value="-1" style="font-weight: bold;">ADD NEW CONSULTANT </option>
    						</select>
    						<div id="newConDiv">
    						<table>
    						<tr>
    						<td class="index">First Name</td>
    						<td><input type="text" name="addConsultantFirstName" id="addConsultantFirstName" value="" onClick="clearSpan('divnewConFirstName')" /><span id="divnewConFirstName"></span>
    						 </td>
    						</tr>
    						<tr>
    						<td class="index">Last Name</td>
    						<td><input type="text" name="addConsultantLastName" id="addConsultantLastName" value=""  onClick="clearSpan('divnewConLastName')"/><span id="divnewConLastName"></span>
    						 </td>
    						</tr>
    						
    						</table>
    						<input type="button" value="Save Consultant" class="button1" onClick="saveConsultant()"/><span id="divbuttonConsultant"></span>
    						</div>
    					</td>
    					
    					
    					
    					</tr>
    						
    					<tr>
    					
    					<td class="index"> Procedure </td>
						
           				
    						<td >
    						<div id="procedurecheckboxtd">
    						<div id="procedurecheckboxdiv">
    						<%
           						
           					    procList = objCommon.getAllProcedures(context, session);
           					    int proc_id=-1;
           					    String proc_name="";
           					 	
           					    
           						//Get the procedures
           						for(int loop=0; loop<procList.size(); loop++)
           						{
           							Procedure procObject = procList.get(loop);
           							proc_id = procObject.getProcedureId();
           							proc_name = procObject.getProcedureName();
           							
           							boolean isChecked=false;
           							//out.println("size of array:" + procListOriginalSelected.size());
           							for(int check=0; check<procListOriginalSelected.size();check++)
           							{
           								
           								if(procListOriginalSelected.get(check)!=null)
           								{
	           								Procedure selectedProcedure = procListOriginalSelected.get(check);
	           								//out.println("selected proc name:" + selectedProcedure.getProcedureName());
	           								int checked_proc_id = selectedProcedure.getProcedureId();
	           								//out.println("selected id :" + checked_proc_id);
	           								if(proc_id==checked_proc_id)
	           								{
	           									isChecked	= true;
	           								}
           								}
           								else
           								{
           									out.println("procedure object is null");
	           								
           								}
           							}
           							if( isChecked)
           							{
           							
           					%>
           					 			<input type="checkbox" name="ProcedureCheckBox" id="ProcedureCheckBox<%=(proc_id) %>" onClick="clearSpan('divbuttonProcedure');" class="chkProcedure" value="<%=proc_id  %> " checked > <input type="text" name="ProcedureTextBox" class = 'txtProcedure' value=" <%=proc_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					<% 
           							}
           							else
           							{
           								
           								
           					%>
           								<input type="checkbox" name="ProcedureCheckBox" id="ProcedureCheckBox<%=(proc_id) %>" onClick="clearSpan('divbuttonProcedure');" class="chkProcedure" value="<%=proc_id  %> "  > <input type="text" name="ProcedureTextBox" class = 'txtProcedure' value=" <%=proc_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					
           					<% 			
           							}
           								
           						}
           						
           						
           					%>
           					</div>
           					</div>
           					<br>
           					<div id="newProcDiv">
    						<table>
    						<tr>
    						<td class="index">Add New Procedure</td>
    						<td>
    						<input type="text" name="addProcedureName" id="addProcedureName" value="" onClick="clearSpan('divnewProcName')" /><span id="divnewProcName"></span>
    						 </td>
    						</tr>
    						
    						</table>
    						<input type="button" value="Save Procedure" class="button1" onClick="saveProcedure()"/><span id="divbuttonProcedure"></span>
    						</div>
    						
    						</td>
    					
    					</tr>
    					
    				
    					
    					
    					
    							
    					<tr>
    					
    					<td class="index"> Primary Angio Operator </td>
						<td> 
           					<select name="primaryAngioOpSelect" id="primaryAngioOpSelect" " >
           					
           					<%
           					if(primaryAngioOperator==1)
           					{
           					
           					%>
	           				<option value="1" selected> ME (1) </option>
	           				<option value="0"  >NOT ME (0) </option>
           					<%
           					}
           					else
           					{
           					%>
           					
           					
           					<option value="0" selected >NOT ME (0) </option>
           					<option value="1" > ME (1) </option>
           					<%
           					}
           					%>
           					
           					
           					</select>
    						
    					</td>
    					
    					</tr>
    					<tr>
    					
    					
    					<td class="index"> Indication </td>
						
           				
    						<td >
    						<div id="indicationcheckboxtd">
    						<div id="indicationcheckboxdiv">
    						<%
           						
    							indicList = objCommon.getAllIndications(context, session);
           					    int ind_id=-1;
           					    String ind_name="";
           					 	
           					    
           						//Get the procedures
           						for(int loop=0; loop<indicList.size(); loop++)
           						{
           							Indication indObject = indicList.get(loop);
           							ind_id = indObject.getIndicationId();
           							ind_name = indObject.getIndicationName();
           							
           							boolean isIndicationChecked=false;
           							for(int check=0; check<indicListOriginalSelected.size();check++)
           							{
           								if(indicListOriginalSelected.get(check)!=null)
           								{
	           								Indication selectedIndication = indicListOriginalSelected.get(check);
	           								int checked_ind_id = selectedIndication.getIndicationId();
	           								//out.println("selected id :" + checked_proc_id);
	           								if(ind_id==checked_ind_id)
	           								{
	           									isIndicationChecked	= true;
	           								}
           								}
           							}
           							if( isIndicationChecked)
           							{
           							
           							
           							
           							
           					%>
           					 <input type="checkbox" name="IndicationCheckBox" id="IndicationCheckBox<%=(ind_id) %>" onClick="clearSpan('divbuttonIndication');" class="chkIndication" value="<%=ind_id  %> "  checked> <input type="text" name="IndicationTextBox" class = 'txtIndication' value=" <%=ind_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					<% 
           							}
           							else
           							{
           					%>
           					
           						 <input type="checkbox" name="IndicationCheckBox" id="IndicationCheckBox<%=(ind_id) %>" onClick="clearSpan('divbuttonIndication');" class="chkIndication" value="<%=ind_id  %> "  > <input type="text" name="IndicationTextBox" class = 'txtIndication' value=" <%=ind_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					
           					<% 
           							}
           						}
           						
           						
           					%>
           					</div>
           					</div>
           						<br>
           					<div id="newIndiDiv">
    						<table>
    						<tr>
    						<td class="index">Add New Indication</td>
    						<td>
    						<input type="text" name="addIndicationName" id="addIndicationName" value="" onClick="clearSpan('divnewIndiName')" /><span id="divnewIndiName"></span>
    						 </td>
    						</tr>
    						
    						</table>
    						<input type="button" value="Save Indication" class="button1" onClick="saveIndication()"/><span id="divbuttonIndication"></span>
    						</div>
    						
    						</td>
    				
    					</tr>
    					
    					<tr>
						<td class="index"> Access </td>
						<td> 
           					<select name="access" id="access"  >
           					
           					<%
           					if (accessName.equalsIgnoreCase("femoral"))
           					{	
           						
           					%>
	           				<option value="femoral" selected>Femoral </option>
	           				<option value="radial"  >Radial </option>
           					<%
           					}
           					else
           					{
           						
           					
           					%>
           					
           					<option value="radial" selected >Radial </option>
           					<option value="femoral" >Femoral </option>
           					
           					<%
           					}
           					
           					%>
           					</select>
    						
    					</td>
    					
    					</tr>
    					
    					<tr>
						<td class="index"> Arterial </td>
						<td> 
           					<select name="arterial" id="arterial"  >
           					
           					
           					<%
           					if(arterial==1)
           					{
           					%>
	           				<option value="1" selected>1 </option>
           					
           					
           					
           					<option value="0" >0 </option>
           					<%
           					}
           					else
           					{
           					
           					%>
           					
           					<option value="0" selected>0 </option>
           					<option value="1" >1 </option>
           					
           					<%
           					}
           					%>
           					</select>
    						
    					</td>
    					
    					</tr>
    					
    					<tr>
						<td class="index"> Venous </td>
						<td> 
           					<select name="venous" id="venous"  >
           					<%
           					if(venous==1)
           					{
           					%>
	           				<option value="1" selected>1 </option>
      						<option value="0" >0 </option>
           					<%
           					}
           					else
           					{
           					
           					%>
           					
           					
      						<option value="0"  selected>0 </option>
      						<option value="1" >1 </option>
      						<%
           					}
      						%>
           					
           					</select>
    						
    					</td>
    					
    					</tr>
    					
    					
    					<tr>
    					
    					<td class="index"> Angio Final Impression </td>
						
           				
    						<td >
    						<div id="finalimpcheckboxtd">
    						<div id="finalimpcheckboxdiv">
    						<%
           						
           					    finalImpList = objCommon.getAllImpressions(context, session);
    						
           					    int imp_id=-1;
           					    String imp_name="";
           					 	
           					    
           						//Get the procedures
           						for(int loop=0; loop<finalImpList.size(); loop++)
           						{
           							Impression impObject = finalImpList.get(loop);
           							imp_id = impObject.getImpressionId();
           							imp_name = impObject.getImpressionName();
           							
           							boolean isImpressionChecked=false;
           							for(int check=0; check<finalImpListOriginalSelected.size();check++)
           							{
           								if(finalImpListOriginalSelected.get(check)!=null)
           								{
	           								Impression selectedImpression = finalImpListOriginalSelected.get(check);
	           								int checked_finalImp_id = selectedImpression.getImpressionId();
	           								
	           								if(imp_id==checked_finalImp_id)
	           								{
	           									isImpressionChecked	= true;
	           								}
           								}
           							}
           							if( isImpressionChecked)
           							{
           							
           							
           							
           							
           					%>
           					 <input type="checkbox" name="FinalImpressionCheckBox" id="FinalImpressionCheckBox <%=(imp_id) %>" onClick="clearSpan('divbuttonImpression');" class="chkImpression" value="<%=imp_id  %> " checked> <input type="text" name="ImpressionTextBox" class = 'txtImpression' value=" <%=imp_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					<% 
           							}
           							else
           							{
           								
           								
           					%>
           					
           						 <input type="checkbox" name="FinalImpressionCheckBox" id="FinalImpressionCheckBox <%=(imp_id) %>" onClick="clearSpan('divbuttonImpression');" class="chkImpression" value="<%=imp_id  %> "> <input type="text" name="ImpressionTextBox" class = 'txtImpression' value=" <%=imp_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					
           					<% 
           							}
           							
           								
           						}
           						
           						
           					%>
           					</div>
           					</div>
           					<br>
           					<div id="newImpDiv">
    						<table>
    						<tr>
    						<td class="index">Add New Impression</td>
    						<td>
    						<input type="text" name="addImpressionName" id="addImpressionName" value="" onClick="clearSpan('divnewImpName')" /><span id="divnewImpName"></span>
    						 </td>
    						</tr>
    						
    						</table>
    						<input type="button" value="Save Final Impression" class="button1" onClick="saveImpression()"/><span id="divbuttonImpression"></span>
    						</div>
    						
    						</td>
    					
    					</tr>
    					
    					
    					
    					
    					
    					
    					
    					
    					<tr>
    					
    					<td class="index"> Vessels Diseased </td>
						
           				
    						<td >
    						<div id="vesseldiseasedtd">
    						<div id="vesseldiseaseddiv">
    						<%
           						
           					    vesselsDiseasedList = objCommon.getAllDiseasedVessels(context, session);
    						
           					    int vessel_id=-1;
           					    String vessel_name="";
           					 	
           					    
           						//Get the procedures
           						for(int loop=0; loop<vesselsDiseasedList.size(); loop++)
           						{
           							Vessels vesselObject = vesselsDiseasedList.get(loop);
           							vessel_id = vesselObject.getVesselId();
           							vessel_name = vesselObject.getVesselName();
           							
           							boolean isVesselChecked=false;
           							for(int check=0; check<vesselsDiseasedListOriginalSelected.size();check++)
           							{
           								if(vesselsDiseasedListOriginalSelected.get(check)!=null)
           								{
	           								Vessels selectedVessel = vesselsDiseasedListOriginalSelected.get(check);
	           								int checked_vessel_id = selectedVessel.getVesselId();
	           								
	           								if(vessel_id==checked_vessel_id)
	           								{
	           									isVesselChecked	= true;
	           								}
           								}
           							}
           							if( isVesselChecked)
           							{
           							
           							
           							
           							
           					%>
           					 <input type="checkbox" name="VesselsCheckBox" id="VesselsCheckBox <%=(vessel_id) %>" onClick="clearSpan('divbuttonVessel');" class="chkVessels" value="<%=vessel_id  %> " checked> <input type="text" name="VesselsTextBox" class = 'txtVessels' value=" <%=vessel_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					<% 
           							}
           							else
           							{
           					%>
           					
           						<input type="checkbox" name="VesselsCheckBox" id="VesselsCheckBox <%=(vessel_id) %>" onClick="clearSpan('divbuttonVessel');" class="chkVessels" value="<%=vessel_id  %> "> <input type="text" name="VesselsTextBox" class = 'txtVessels' value=" <%=vessel_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					
           					<% 
           					
           							}
           						}
           						
           						
           					%>
           					</div>
           					</div>
           					<br>
           					<div id="newVesselDiv">
    						<table>
    						<tr>
    						<td class="index">Add New Diseased Vessel</td>
    						<td>
    						<input type="text" name="addVesselName" id="addVesselName" value="" onClick="clearSpan('divnewVesselName')" /><span id="divnewVesselName"></span>
    						 </td>
    						</tr>
    						
    						</table>
    						<input type="button" value="Save Vessel" class="button1" onClick="saveVessel()"/><span id="divbuttonVessel"></span>
    						</div>
    						
    						</td>
    					
    					</tr>
    					
    					
    					
    					
    					
    					<tr>
    					
    					<td class="index"> Intervention </td>
						
           				
    						<td >
    						<div id="interventiontd">
    						<div id="interventiondiv">
    						<%
           						
    						interventionList = objCommon.getAllInterventions(context, session);
    						
           					    int intervention_id=-1;
           					    String intervention_name="";
           					 	
           					    
           						//Get the interventions
           						for(int loop=0; loop<interventionList.size(); loop++)
           						{
           							Intervention interventionObject = interventionList.get(loop);
           							intervention_id = interventionObject.getInterventionId();
           							intervention_name = interventionObject.getInterventionName();
           							
           							
           							boolean isInterventionChecked=false;
           							for(int check=0; check<interventionListOriginalSelected.size();check++)
           							{
           								if(interventionListOriginalSelected.get(check)!=null)
           								{
	           								Intervention selectedIntervention = interventionListOriginalSelected.get(check);
	           								int checked_intervention_id = selectedIntervention.getInterventionId();
	           								
	           								if(intervention_id==checked_intervention_id)
	           								{
	           									isInterventionChecked	= true;
	           								}
           								}
           							}
           							if( isInterventionChecked)
           							{
           							
           							
           							
           							
           							
           					%>
           					 <input type="checkbox" name="interventionCheckBox" id="interventionCheckBox <%=(intervention_id) %>" onClick="clearSpan('divbuttonIntervention');" class="chkIntervention" value="<%=intervention_id  %> " checked> <input type="text" name="interventionTextBox" class = 'txtIntervention' value=" <%=intervention_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					<% 
           							}
           							else
           							{
           					%>
           							 <input type="checkbox" name="interventionCheckBox" id="interventionCheckBox <%=(intervention_id) %>" onClick="clearSpan('divbuttonIntervention');" class="chkIntervention" value="<%=intervention_id  %> "> <input type="text" name="interventionTextBox" class = 'txtIntervention' value=" <%=intervention_name.toUpperCase()  %>" style='width: 500px;' readonly/></br>
           					
           					
           					<% 
           					
           							}
           						}
           						
           						
           					%>
           					</div>
           					</div>
           					<br>
           					<div id="newInterventionDiv">
    						<table>
    						<tr>
    						<td class="index">Add New Intervention</td>
    						<td>
    						<input type="text" name="addInterventionName" id="addInterventionName" value="" onClick="clearSpan('divnewInterventionName')" /><span id="divnewInterventionName"></span>
    						 </td>
    						</tr>
    						
    						</table>
    						<input type="button" value="Save Intervention" class="button1" onClick="saveIntervention()"/><span id="divbuttonIntervention"></span>
    						</div>
    						
    						</td>
    					
    					</tr>
    					
    					
    								
    					<tr>
    					
    					<td class="index"> Primary  Operator </td>
						<td> 
           					<select name="primaryOpSelect" id="primaryOpSelect" " >
           					<%
           					if(primary_operator==1)
           					{
           					%>
	           				<option value="1" selected>1 </option>
         					<option value="0" >0 </option>
           					<%
           					}
           					else
           					{
           					%>
           					
         					<option value="0" selected >0 </option>
         					<option value="1" >1 </option>
         					
         					<%
         					
           					}
         					%>
           					</select>
    						
    					</td>
    					
    					</tr>
    					
    					<tr>
    						<td class="index">Number of BMS</td>
    						<td>
    						<input type="number" name="numOfBMS" id="numOfBMS" value="<%=bms %>" onClick="clearSpan('divBMS')" /><span id="divBMS"></span>
    						</td>
    				   </tr>
    				   
    				   <tr>
    						<td class="index">Number of DES</td>
    						<td>
    						<input type="number" name="numOfDES" id="numOfDES" value="<%=des %>" onClick="clearSpan('divDES')" /><span id="divDES"></span>
    						</td>
    				   </tr>
    				   
    				   
    				     <tr>
    						<td class="index">Number of DEB</td>
    						<td>
    						<input type="number" name="numOfDEB" id="numOfDEB" value="<%=deb %>" onClick="clearSpan('divDEB')" /><span id="divDEB"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Number of LMS</td>
    						<td>
    						<input type="number" name="numOfLMS" id="numOfLMS" value="<%=lms %>" onClick="clearSpan('divLMS')" /><span id="divLMS"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">Number of LAD</td>
    						<td>
    						<input type="number" name="numOfLAD" id="numOfLAD" value="<%=lad %>" onClick="clearSpan('divLAD')" /><span id="divLAD"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Number of Cx</td>
    						<td>
    						<input type="number" name="numOfCx" id="numOfCx" value="<%=cx %>" onClick="clearSpan('divCx')" /><span id="divCx"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Number of RCA</td>
    						<td>
    						<input type="number" name="numOfRCA" id="numOfRCA" value="<%=rca %>" onClick="clearSpan('divRCA')" /><span id="divRCA"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">Ramus</td>
    						<td>
    						<input type="number" name="ramus" id="ramus" value="<%=ramus %>" onClick="clearSpan('divRamus')" /><span id="divRamus"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">Graft</td>
    						<td>
    						<input type="number" name="graft" id="graft" value="<%=graft %>" onClick="clearSpan('divGraft')" /><span id="divGraft"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Branch Vessel</td>
    						<td>
    						<input type="number" name="branchVessel" id="branchVessel" value="<%=branch_vessel %>" onClick="clearSpan('divBranchVessel')" /><span id="divBranchVessel"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">Additional Procedures</td>
    						<td>
    						<input type="number" name="additionalProcedures" id="additionalProcedures" value="<%=additional_proc %>" onClick="clearSpan('divAdditionalProcedures')" /><span id="divAdditionalProcedures"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">LV GRAM</td>
    						<td>
    						<input type="number" name="lvgram" id="lvgram" value="<%=lvgram %>" onClick="clearSpan('divLvgram')" /><span id="divLvgram"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">AORTOGRAM</td>
    						<td>
    						<input type="number" name="aortogram" id="aortogram" value="<%=aortogram %>" onClick="clearSpan('divAortogram')" /><span id="divAortogram"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Bypass Angio</td>
    						<td>
    						<input type="number" name="bypassangio" id="bypassangio" value="<%=bypass_angio %>" onClick="clearSpan('divBypassAngio')" /><span id="divBypassAngio"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">RHC</td>
    						<td>
    						<input type="number" name="rhc" id="rhc" value="<%=rhc %>" onClick="clearSpan('divRhc')" /><span id="divRhc"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">HOCM Assessment</td>
    						<td>
    						<input type="number" name="hocm" id="hocm" value="<%=hocm %>" onClick="clearSpan('divHOCM')" /><span id="divHOCM"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">IVUS</td>
    						<td>
    						<input type="number" name="ivus" id="ivus" value="<%=ivus %>" onClick="clearSpan('divIvus')" /><span id="divIvus"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">OCT</td>
    						<td>
    						<input type="number" name="oct" id="oct" value="<%=oct %>" onClick="clearSpan('divOCT')" /><span id="divOCT"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">FFR</td>
    						<td>
    						<input type="number" name="ffr" id="ffr" value="<%=ffr %>" onClick="clearSpan('divFFR')" /><span id="divFFR"></span>
    						</td>
    				    </tr>
    				     <tr>
    						<td class="index">Angioseal</td>
    						<td>
    						<input type="number" name="angioseal" id="angioseal" value="<%=angioseal %>" onClick="clearSpan('divAngioseal')" /><span id="divAngioseal"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Proglide</td>
    						<td>
    						<input type="number" name="proglide" id="proglide" value="<%=proglide %>" onClick="clearSpan('divProglide')" /><span id="divProglide"></span>
    						</td>
    				    </tr>
					
						<tr>
    						<td class="index">Balloon Pump</td>
    						<td>
    						<input type="number" name="balloon" id="balloon" value="<%=balloon_pump %>" onClick="clearSpan('divBalloon')" /><span id="divBalloon"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">Angio Sculpt Cutting</td>
    						<td>
    						<input type="number" name="angiosculpt" id="angiosculpt" value="<%=angio_sculpt %>" onClick="clearSpan('divAngiosculpt')" /><span id="divAngiosculpt"></span>
    						</td>
    				    </tr>
						 <tr>
    						<td class="index">Rotablation</td>
    						<td>
    						<input type="number" name="rotablation" id="rotablation" value="<%=rotablation %>" onClick="clearSpan('divRotablation')" /><span id="divRotablation"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">PAMI / Rescue</td>
    						<td>
    						<input type="number" name="pami" id="pami" value="<%=pami %>" onClick="clearSpan('divPami')" /><span id="divPami"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Complex CTO</td>
    						<td>
    						<input type="number" name="complexcto" id="complexcto" value="<%=complex_cto %>" onClick="clearSpan('divComplexCto')" /><span id="divComplexCto"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">Complex Bifucation</td>
    						<td>
    						<input type="number" name="complexbifucation" id="complexbifucation" value="<%=complex_bifuc %>" onClick="clearSpan('divComplexBifucation')" /><span id="divComplexBifucation"></span>
    						</td>
    				    </tr>
						 <tr>
    						<td class="index">Complications</td>
    						<td>
    						<input type="number" name="complications" id="complications" value="<%=complications %>" onClick="clearSpan('divComplications')" /><span id="divComplications"></span>
    						</td>
    				    </tr>
    				    
    				     <tr>
    						<td class="index">Complication Description</td>
    						<td>
    						<input type="text" name="complicationdesc" id="complicationdesc" value="<%=complication_desc %>" onClick="clearSpan('divComplicationDesc')" /><span id="divComplicationDesc"></span>
    						</td>
    				    </tr>
    				    
    				    <tr>
    						<td class="index">Comment</td>
    						<td>
    						<input type="text" name="comment" id="comment" value="<%=comments %>" onClick="clearSpan('divComment')" /><span id="divComment"></span>
    						</td>
    				    </tr>
					
			</table>
			
			<table>
			
			<tr>
                        <td class="index"><input type="Submit" value="Edit Patient Details" class="admin"/>&nbsp<span id="savepatient"></span></td>
                        <td class="index"><input type="button" value="Delete Patient" class="button2" onClick="deletePatient()"/> </td>
            </tr>
			
			</table>
						<input type="hidden" name="OriginalPatientName" id="OriginalPatientName" value="<%=patientName%>"/>
						<input type="hidden" name="OriginalDate" id="OriginalDate" value="<%=strDate%>"/>
			
			<div class="footer">
			</div>
			</tbody>
         </center>

				
        </form>
      
     </div>
   
</div> 

<div class="footer">
</div>

</body>

<%
}
%>



<script type="text/javascript">


function deletePatient()
{
	//get and validate patient nhi
	var patientnhi = document.getElementById("patientnhi").value;
	if(patientnhi==null || patientnhi.trim()=="")
	{
		
		document.getElementById("divpatientnhi").style.color = "red";
        document.getElementById("divpatientnhi").innerHTML = "Please enter patient NHI.";
        alert("Enter the Patient NHI number of patient record to be deleted.");
		return false;
	} 
	
	var oldDate = document.getElementById("OriginalDate").value
	
	
	var result = confirm("Do you want to delete patient with NHI :" + patientnhi );
	if (result) 
	{
	    //Logic to delete the item
		
		
		var xmlhttp = new XMLHttpRequest();
	    
	    xmlhttp.onreadystatechange = function()
	    {
	        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
	        {
	    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

	        	var deletedPatient = "deleted";
	        	
	        	if(trimmedResponse.toLowerCase()  == deletedPatient.toLowerCase())
	             {
	        		document.getElementById("savepatient").style.color = "red";
	        		document.getElementById("savepatient").innerHTML = trimmedResponse.toUpperCase();
	        		
	        	 }
	        	else 
	       		{
	        		document.getElementById("savepatient").style.color = "red";
	        		document.getElementById("savepatient").innerHTML = trimmedResponse.toUpperCase();
	       		}
	           
	        
	       
	    	}
	    
		};
	    try
	    {
			var posturl = "patientnhi=" + patientnhi + "&&originalDate=" + oldDate   ;
	    
		 
			xmlhttp.open("POST", "${pageContext.request.contextPath}/DeletePatientRecordProcedureTable", true);
		
		 	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		 	xmlhttp.send(posturl);
		}
	    catch(e)
	    {
	    	alert("unable to connect to server");
	    }
	    
	    
	    
	}
	
	
}

function clearSpan(elementId)
{
	document.getElementById(elementId).innerHTML ="";
	document.getElementById("savepatient").innerHTML = "";
}

function hideDiv()
{
	var resourceSelect = document.getElementById("consultant"); 
	if(resourceSelect.options[0].value != "-1")
	{
		document.getElementById('newConDiv').style.display='none';
	}
	
	/*
	var procedureSelect = document.getElementById("procedureSelect"); 
	if(procedureSelect.options[0].value != "-1")
	{
		document.getElementById('newProcDiv').style.display='none';
	}
	*/
	
	
}

function showNewConsultantDiv()
{
	//clear div near button
	document.getElementById("divbuttonConsultant").innerHTML = "";
	
	//clear textbox entry
	document.getElementById('addConsultantFirstName').value = "";
	document.getElementById('addConsultantLastName').value = "";
	
	
	var consultantId = document.getElementById("consultant").value;
	if(consultantId=='-1')
	{
		document.getElementById('newConDiv').style.display='block';
	}
	else
	{
		document.getElementById('newConDiv').style.display='none';
	}
	
}

function saveIndication()
{
	var indicationName = document.getElementById("addIndicationName").value;
	
	//checking  name
	if(indicationName==null || indicationName.trim()=="")
	{
		document.getElementById("divnewIndiName").style.color = "red";
        document.getElementById("divnewIndiName").innerHTML = "Please enter indication name.";
		return false;
	}
	
//ajax to save the new indication
	
	var saved= false;
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

        	var savedIndication = "Saved indication.";
        	var indicationExists = "This indication already exists!"
        	if(trimmedResponse.toLowerCase()  == savedIndication.toLowerCase())
             {
        		document.getElementById("divbuttonIndication").style.color = "red";
        		document.getElementById("divbuttonIndication").innerHTML = trimmedResponse.toUpperCase();
        		saved = true;
        	 }
        	else if(trimmedResponse.toLowerCase()  == indicationExists.toLowerCase())
       		{
        		document.getElementById("divbuttonIndication").style.color = "red";
        		document.getElementById("divbuttonIndication").innerHTML = trimmedResponse.toUpperCase();
       		}
           
        
       
    	}
    
	};
    try
    {
		var posturl = "indicationname=" + indicationName   ;
    
	 
		xmlhttp.open("POST", "${pageContext.request.contextPath}/SaveNewIndication", false);
	
	 	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 	xmlhttp.send(posturl);
	}
    catch(e)
    {
    	alert("unable to connect to server");
    }
    
	
    if(saved)
 	{
  	
  		
  		//get the selected checkboxes in an array
  		var selIds = getCheckedIds("chkIndication");
  		
		//remove all checkboxes
	  	removeCheckBoxes("indicationcheckboxdiv");
  		
	  //ajax to reload the check boxes
	  	populateIndicationCheckBoxes(selIds);
	  
	 
  		//clear textbox and div values
	  	clearSpan("divbuttonIndication");
	  	document.getElementById("addIndicationName").value ="";	
  		
 	}

	
	
}






function saveIntervention()
{
	var interventionName = document.getElementById("addInterventionName").value;
	
	//checking  name
	if(interventionName==null || interventionName.trim()=="")
	{
		document.getElementById("divnewInterventionName").style.color = "red";
        document.getElementById("divnewInterventionName").innerHTML = "Please enter intervention name.";
		return false;
	}
	
//ajax to save the new intervention
	
	var saved= false;
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

        	var savedIntervention = "Saved intervention.";
        	var interventionExists = "This intervention already exists!"
        	if(trimmedResponse.toLowerCase()  == savedIntervention.toLowerCase())
             {
        		document.getElementById("divbuttonIntervention").style.color = "red";
        		document.getElementById("divbuttonIntervention").innerHTML = trimmedResponse.toUpperCase();
        		saved = true;
        	 }
        	else if(trimmedResponse.toLowerCase()  == interventionExists.toLowerCase())
       		{
        		document.getElementById("divbuttonIntervention").style.color = "red";
        		document.getElementById("divbuttonIntervention").innerHTML = trimmedResponse.toUpperCase();
       		}
           
        
       
    	}
    
	};
    try
    {
		var posturl = "interventionname=" + interventionName   ;
    
	 
		xmlhttp.open("POST", "${pageContext.request.contextPath}/SaveNewIntervention", false);
	
	 	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 	xmlhttp.send(posturl);
	}
    catch(e)
    {
    	alert("unable to connect to server");
    }
    
	
    if(saved)
 	{
  	
  		
  		//get the selected checkboxes in an array
  		var selIds = getCheckedIds("chkIntervention");
  		
		//remove all checkboxes
	  	removeCheckBoxes("interventiondiv");
  		
	  //ajax to reload the check boxes
	  	populateInterventionCheckBoxes(selIds);
	  
	 
  		//clear textbox and div values
	  	clearSpan("divbuttonIntervention");
	  	document.getElementById("addInterventionName").value ="";	
  		
 	}

	
	
}







function saveProcedure()
{
	var procName = document.getElementById("addProcedureName").value;
	
	//checking  name
	if(procName==null || procName.trim()=="")
	{
		document.getElementById("divnewProcName").style.color = "red";
        document.getElementById("divnewProcName").innerHTML = "Please enter procedure name.";
		return false;
	}
	
//ajax to save the new procedure
	
	var saved= false;
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

        	var savedProcedure = "Saved procedure.";
        	var procedureExists = "This procedure already exists!"
        	if(trimmedResponse.toLowerCase()  == savedProcedure.toLowerCase())
             {
        		document.getElementById("divbuttonProcedure").style.color = "red";
        		document.getElementById("divbuttonProcedure").innerHTML = trimmedResponse.toUpperCase();
        		saved = true;
        	 }
        	else if(trimmedResponse.toLowerCase()  == procedureExists.toLowerCase())
       		{
        		document.getElementById("divbuttonProcedure").style.color = "red";
        		document.getElementById("divbuttonProcedure").innerHTML = trimmedResponse.toUpperCase();
       		}
           
        
       
    	}
    
	};
    try
    {
		var posturl = "procname=" + procName   ;
    
	 
		xmlhttp.open("POST", "${pageContext.request.contextPath}/SaveNewProcedure", false);
	
	 	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 	xmlhttp.send(posturl);
	}
    catch(e)
    {
    	alert("unable to connect to server");
    }
    
  //var procedureSelect = document.getElementById("procedureSelect"); 
    
    //if new procedure has been saved, update the select box and set the procedure as selected
  if(saved)
 	{
  	
  		
  		//get the selected checkboxes in an array
  		var selIds = getCheckedIds("chkProcedure");
  		
		//remove all checkboxes
	  	removeCheckBoxes("procedurecheckboxdiv");
  		
	  //ajax to reload the check boxes
	  	populateProcedureCheckBoxes(selIds);
	  
	 
  		//clear textbox and div values
	  	clearSpan("divbuttonProcedure");
	  	document.getElementById("addProcedureName").value ="";	
  		
 	}


    
	
	
}

function saveVessel()
{
	var vesselName = document.getElementById("addVesselName").value;
	
	//checking  name
	if(vesselName==null || vesselName.trim()=="")
	{
		document.getElementById("divnewVesselName").style.color = "red";
        document.getElementById("divnewVesselName").innerHTML = "Please enter vessel.";
		return false;
	}
	
	//ajax to save the new vessel
	
	var saved= false;
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

        	var savedVessel = "Saved vessel.";
        	var vesselExists = "This vessel already exists!"
        	if(trimmedResponse.toLowerCase()  == savedVessel.toLowerCase())
             {
        		document.getElementById("divbuttonVessel").style.color = "red";
        		document.getElementById("divbuttonVessel").innerHTML = trimmedResponse.toUpperCase();
        		saved = true;
        	 }
        	else if(trimmedResponse.toLowerCase()  == vesselExists.toLowerCase())
       		{
        		document.getElementById("divbuttonVessel").style.color = "red";
        		document.getElementById("divbuttonVessel").innerHTML = trimmedResponse.toUpperCase();
       		}
           
        
       
    	}
    
	};
    try
    {
		var posturl = "vesselname=" + vesselName ;
    
	 
		xmlhttp.open("POST", "${pageContext.request.contextPath}/SaveNewVessel", false);
	
	 	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 	xmlhttp.send(posturl);
	}
    catch(e)
    {
    	alert("unable to connect to server");
    }
    
    
    //if new impression has been saved, update the select box and set the procedure as selected
  if(saved)
 	{
  	
  		
  		//get the selected checkboxes in an array
  		var selIds = getCheckedIds("chkVessels");
  		
		//remove all checkboxes
	  	removeCheckBoxes("vesseldiseaseddiv");
  		
	  //ajax to reload the check boxes
	  	populateVesselCheckBoxes(selIds);
	  
	 
  		//clear textbox and div values
	  	clearSpan("divbuttonVessel");
	  	document.getElementById("addVesselName").value ="";	
  		
 	}


	
	
}




function saveImpression()
{
	var impName = document.getElementById("addImpressionName").value;
	
	//checking  name
	if(impName==null || impName.trim()=="")
	{
		document.getElementById("divnewImpName").style.color = "red";
        document.getElementById("divnewImpName").innerHTML = "Please enter final impression.";
		return false;
	}
	
//ajax to save the new impression
	
	var saved= false;
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

        	var savedImpression = "Saved impression.";
        	var impressionExists = "This impression already exists!"
        	if(trimmedResponse.toLowerCase()  == savedImpression.toLowerCase())
             {
        		document.getElementById("divbuttonImpression").style.color = "red";
        		document.getElementById("divbuttonImpression").innerHTML = trimmedResponse.toUpperCase();
        		saved = true;
        	 }
        	else if(trimmedResponse.toLowerCase()  == impressionExists.toLowerCase())
       		{
        		document.getElementById("divbuttonImpression").style.color = "red";
        		document.getElementById("divbuttonImpression").innerHTML = trimmedResponse.toUpperCase();
       		}
           
        
       
    	}
    
	};
    try
    {
		var posturl = "impname=" + impName   ;
    
	 
		xmlhttp.open("POST", "${pageContext.request.contextPath}/SaveNewImpression", false);
	
	 	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 	xmlhttp.send(posturl);
	}
    catch(e)
    {
    	alert("unable to connect to server");
    }
    
    
    //if new impression has been saved, update the select box and set the procedure as selected
  if(saved)
 	{
  	
  		
  		//get the selected checkboxes in an array
  		var selIds = getCheckedIds("chkImpression");
  		
		//remove all checkboxes
	  	removeCheckBoxes("finalimpcheckboxdiv");
  		
	  //ajax to reload the check boxes
	  	populateImpressionCheckBoxes(selIds);
	  
	 
  		//clear textbox and div values
	  	clearSpan("divbuttonImpression");
	  	document.getElementById("addImpressionName").value ="";	
  		
 	}


    
	
	
}


























function getCheckedIds(chkClassname)
{
	 var boxes = document.getElementsByClassName(chkClassname);
	 var selchbox = [];
	 for(var i = 0; i<boxes.length; i++)
	    {
	       var  box = boxes[i];
	       if(box.checked == true)
	    	   {
	    	   //alert(box.value);
	    	   		selchbox.push(box.value)
	    	   }
	    }
	 return selchbox;
}


function removeCheckBoxes(divname)
{
	
	
	var myNode = document.getElementById(divname);
    while (myNode.firstChild) 
    {
        myNode.removeChild(myNode.firstChild);
    }
    /*
	    var boxes = document.getElementsByClassName(chkClassname);
	 
	    for(var i = 0; i<boxes.length; i++)
	    {
	        box = boxes[i];
	      
	        var parent = box.parentElement;
	        parent.parentElement.removeChild(parent);
			
	    }
	 
	 */  

	
}

function saveConsultant()
{
	var consultantFirstName = document.getElementById("addConsultantFirstName").value;
	var consultantLastName = document.getElementById("addConsultantLastName").value;
	
	
	//checking first name
	if(consultantFirstName==null || consultantFirstName.trim()=="")
	{
		document.getElementById("divnewConFirstName").style.color = "red";
        document.getElementById("divnewConFirstName").innerHTML = "Please enter first name.";
		return false;
	}
	//checking last name
	if(consultantLastName==null || consultantLastName.trim()=="")
	{
		document.getElementById("divnewConLastName").style.color = "red";
        document.getElementById("divnewConLastName").innerHTML = "Please enter last name.";
		return false;
	}
	
	//ajax to save the new consultant
	
	var saved= false;
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();

        	var savedConsultant = "Saved consultant.";
        	var consultantExists = "This Consultant already exists!"
        	if(trimmedResponse.toLowerCase()  == savedConsultant.toLowerCase())
             {
        		document.getElementById("divbuttonConsultant").style.color = "red";
        		document.getElementById("divbuttonConsultant").innerHTML = trimmedResponse.toUpperCase();
        		saved = true;
        	 }
        	else if(trimmedResponse.toLowerCase()  == consultantExists.toLowerCase())
       		{
        		document.getElementById("divbuttonConsultant").style.color = "red";
        		document.getElementById("divbuttonConsultant").innerHTML = trimmedResponse.toUpperCase();
       		}
           
        
       
    	}
    
	};
    try
    {
		var posturl = "fname=" + consultantFirstName  + "&&lname=" + consultantLastName ;
    
	 
		xmlhttp.open("POST", "${pageContext.request.contextPath}/SaveNewConsultant", false);
	
	 	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 	xmlhttp.send(posturl);
	}
    catch(e)
    {
    	alert("unable to connect to server");
    }
    
    
    var resourceSelect = document.getElementById("consultant"); 
      
      //if new consultant has been saved, update the select box and set the consultant as selected
    if(saved)
   	{
    	
    	//remove all select options
    	removeOptions(resourceSelect);
    	
    	//ajax to reload the select box
    	populateConsultantSelectBox();
    	
    	//ajax to get the id of the new consultant and to select it
    	var selId = GetSelectedId(consultantFirstName, consultantLastName);
    	
    	
    	//set that option as selected
    	 for(var i=0; i < resourceSelect.options.length; i++)
    	 {
    	    if(resourceSelect.options[i].value === selId) 
    	    {
    	    	resourceSelect.selectedIndex = i;
    	      break;
    	    }
    	 }

    	//at the end clear the textboxes
    		document.getElementById("addConsultantFirstName").value ="";	
    		document.getElementById("addConsultantLastName").value ="";	
   	
   	}


    
	
	
	
}

function populateIndicationCheckBoxes(idArray)
{
	
	var indicationdiv = document.getElementById("indicationcheckboxdiv");
	
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
    		
			var myArr = JSON.parse(trimmedResponse);
			

			for(var i=0; i<myArr.length ; i++)
			{	
				
				
				//get the procedure object name and id
				var indication =myArr[i]; 
				
				var indName = indication.indicationname;
				
				var indId =  indication.indicationid;
				
				idCount = i+1;
				
				var checkbox = document.createElement('input');
				
				checkbox.setAttribute("type", "checkbox");
				checkbox.setAttribute("name", "IndicationCheckBox");
				checkbox.setAttribute("id", 'IndicationCheckBox'+indId);
				checkbox.setAttribute("class", "chkIndication");
				checkbox.setAttribute("value", indId);
				
				 for (var arrayCount = 0; arrayCount < idArray.length; arrayCount++) 
				 {
					 arrayValue = idArray[arrayCount];
					
					 if(parseInt(indId,10)==parseInt(arrayValue,10))
						{
						 checkbox.setAttribute("checked", true);
						}
				 }
				 
				
				var textbox = document.createElement('input');
				textbox.type = "text";
				textbox.name = "IndicationTextBox";
				textbox.class="txtIndication";
				textbox.value = indName.toUpperCase();
				textbox.id = "IndicationTextBox" + indId;
				textbox.setAttribute("class", "txtIndication");
				textbox.setAttribute("style", "width: 500px");
				textbox.readOnly = true;
				
				
		      
				
				indicationdiv.appendChild(checkbox);
			
				indicationdiv.appendChild(textbox);
				indicationdiv.appendChild(document.createElement("br"));
				
				


				
			}
			
    	}
    
	};
    try
    {
	 	var url = "${pageContext.request.contextPath}/PopulateIndicationBox";
	 	xmlhttp.open("GET", url, true);
	 	xmlhttp.send();

	}
    catch(e)
    {
    	alert("unable to connect to server"); 
    }
	
	
	
	
	
	
	
	
	
}




function populateInterventionCheckBoxes(idArray)
{
	
	var interventiondiv = document.getElementById("interventiondiv");
	
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
    		
			var myArr = JSON.parse(trimmedResponse);
			

			for(var i=0; i<myArr.length ; i++)
			{	
				
				
				//get the procedure object name and id
				var intervention =myArr[i]; 
				
				var intName = intervention.interventionname;
				
				var intId =  intervention.interventionid;
				
				idCount = i+1;
				
				var checkbox = document.createElement('input');
				
				checkbox.setAttribute("type", "checkbox");
				checkbox.setAttribute("name", "interventionCheckBox");
				checkbox.setAttribute("id", 'interventionCheckBox'+intId);
				checkbox.setAttribute("class", "chkIntervention");
				checkbox.setAttribute("value", intId);
				
				 for (var arrayCount = 0; arrayCount < idArray.length; arrayCount++) 
				 {
					 arrayValue = idArray[arrayCount];
					
					 if(parseInt(intId,10)==parseInt(arrayValue,10))
						{
						 checkbox.setAttribute("checked", true);
						}
				 }
				 
				
				var textbox = document.createElement('input');
				textbox.type = "text";
				textbox.name = "interventionTextBox";
				textbox.class="txtIntervention";
				textbox.value = intName.toUpperCase();
				textbox.id = "interventionTextBox" + intId;
				textbox.setAttribute("class", "txtIntervention");
				textbox.setAttribute("style", "width: 500px");
				textbox.readOnly = true;
				
				
		      
				
				interventiondiv.appendChild(checkbox);
			
				interventiondiv.appendChild(textbox);
				interventiondiv.appendChild(document.createElement("br"));
				
				


				
			}
			
    	}
    
	};
    try
    {
	 	var url = "${pageContext.request.contextPath}/PopulateInterventionBox";
	 	xmlhttp.open("GET", url, true);
	 	xmlhttp.send();

	}
    catch(e)
    {
    	alert("unable to connect to server"); 
    }
	
	
	
	
	
	
	
	
	
}









function populateVesselCheckBoxes(idArray)
{
	var vesseldiv = document.getElementById("vesseldiseaseddiv");
	
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
    		
			var myArr = JSON.parse(trimmedResponse);
			

			for(var i=0; i<myArr.length ; i++)
			{	
				
				
				//get the impression object name and id
				var vessels =myArr[i]; 
				
				var vesselName = vessels.vesselname;
				
				var vesselId =  vessels.vesselid;
				
				idCount = i+1;
				
				var checkbox = document.createElement('input');
				
				
				checkbox.setAttribute("type", "checkbox");
				checkbox.setAttribute("name", "VesselsCheckBox");
				checkbox.setAttribute("id", 'VesselsCheckBox'+vesselId);
				checkbox.setAttribute("class", "chkVessels");
				checkbox.setAttribute("value", vesselId);
				
				
				 for (var arrayCount = 0; arrayCount < idArray.length; arrayCount++) 
				 {
					 arrayValue = idArray[arrayCount];
					
					 if(parseInt(vesselId,10)==parseInt(arrayValue,10))
						{
						 checkbox.setAttribute("checked", true);
						}
				 }
				 
				
				var textbox = document.createElement('input');
				textbox.type = "text";
				textbox.name = "VesselsTextBox";
				textbox.class="txtVessels";
				textbox.value = vesselName.toUpperCase();
				textbox.id = "VesselsTextBox" + vesselId;
				textbox.setAttribute("class", "txtVessels");
				textbox.setAttribute("style", "width: 500px");
				textbox.readOnly = true;
				
				
		      
				
				vesseldiv.appendChild(checkbox);
			
				vesseldiv.appendChild(textbox);
				vesseldiv.appendChild(document.createElement("br"));
				
				


				
			}
			//proceduretd.appendChild(procedurediv);
    	}
    
	};
    try
    {
	 	var url = "${pageContext.request.contextPath}/PopulateVesselBox";
	 	xmlhttp.open("GET", url, true);
	 	xmlhttp.send();

	}
    catch(e)
    {
    	alert("unable to connect to server"); 
    }
	
	
	
	
	
	
	
	
}



function populateImpressionCheckBoxes(idArray)
{
	
	
	var impressiondiv = document.getElementById("finalimpcheckboxdiv");
	
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
    		
			var myArr = JSON.parse(trimmedResponse);
			

			for(var i=0; i<myArr.length ; i++)
			{	
				
				
				//get the impression object name and id
				var impression =myArr[i]; 
				
				var impName = impression.impressionname;
				
				var impId =  impression.impressionid;
				
				idCount = i+1;
				
				var checkbox = document.createElement('input');
				
				
				checkbox.setAttribute("type", "checkbox");
				checkbox.setAttribute("name", "FinalImpressionCheckBox");
				checkbox.setAttribute("id", 'FinalImpressionCheckBox'+impId);
				checkbox.setAttribute("class", "chkImpression");
				checkbox.setAttribute("value", impId);
				
				
				 for (var arrayCount = 0; arrayCount < idArray.length; arrayCount++) 
				 {
					 arrayValue = idArray[arrayCount];
					
					 if(parseInt(impId,10)==parseInt(arrayValue,10))
						{
						 checkbox.setAttribute("checked", true);
						}
				 }
				 
				
				var textbox = document.createElement('input');
				textbox.type = "text";
				textbox.name = "ImpressionTextBox";
				textbox.class="txtImpression";
				textbox.value = impName.toUpperCase();
				textbox.id = "ImpressionTextBox" + impId;
				textbox.setAttribute("class", "txtImpression");
				textbox.setAttribute("style", "width: 500px");
				textbox.readOnly = true;
				
				
		      
				
				impressiondiv.appendChild(checkbox);
			
				impressiondiv.appendChild(textbox);
				impressiondiv.appendChild(document.createElement("br"));
				
				


				
			}
			//proceduretd.appendChild(procedurediv);
    	}
    
	};
    try
    {
	 	var url = "${pageContext.request.contextPath}/PopulateImpressionBox";
	 	xmlhttp.open("GET", url, true);
	 	xmlhttp.send();

	}
    catch(e)
    {
    	alert("unable to connect to server"); 
    }
	
	
	
	
	
	
}




function populateProcedureCheckBoxes(idArray)
{
	
	
	var procedurediv = document.getElementById("procedurecheckboxdiv");
	
	
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
    		
			var myArr = JSON.parse(trimmedResponse);
			

			for(var i=0; i<myArr.length ; i++)
			{	
				
				
				//get the procedure object name and id
				var procedure =myArr[i]; 
				
				var procName = procedure.procedurename;
				
				var procId =  procedure.procedureid;
				
				idCount = i+1;
				
				var checkbox = document.createElement('input');
				/*
				checkbox.type = "checkbox";
				checkbox.name = "ProcedureCheckBox";
				checkbox.class="chkProcedure";
				checkbox.value = procId;
				checkbox.id = "ProcedureCheckBox" + procId ;
				*/
				
				checkbox.setAttribute("type", "checkbox");
				checkbox.setAttribute("name", "ProcedureCheckBox");
				checkbox.setAttribute("id", 'ProcedureCheckBox'+procId);
				checkbox.setAttribute("class", "chkProcedure");
				checkbox.setAttribute("value", procId);
				/*checkbox.addEventListener( 'change', function() {
				    if(this.checked) {
				    	
				        
				        checkbox.setAttribute("checked", true);

				    } else {
				    	 checkbox.setAttribute("checked", false);
				    }
				});*/
				
				 for (var arrayCount = 0; arrayCount < idArray.length; arrayCount++) 
				 {
					 arrayValue = idArray[arrayCount];
					
					 if(parseInt(procId,10)==parseInt(arrayValue,10))
						{
						 checkbox.setAttribute("checked", true);
						}
				 }
				 
				
				var textbox = document.createElement('input');
				textbox.type = "text";
				textbox.name = "ProcedureTextBox";
				textbox.class="txtProcedure";
				textbox.value = procName.toUpperCase();
				textbox.id = "ProcedureTextBox" + procId;
				textbox.setAttribute("class", "txtProcedure");
				textbox.setAttribute("style", "width: 500px");
				textbox.readOnly = true;
				
				
		      
				
				procedurediv.appendChild(checkbox);
			
				procedurediv.appendChild(textbox);
				procedurediv.appendChild(document.createElement("br"));
				
				


				
			}
			//proceduretd.appendChild(procedurediv);
    	}
    
	};
    try
    {
	 	var url = "${pageContext.request.contextPath}/PopulateProcedureBox";
	 	xmlhttp.open("GET", url, true);
	 	xmlhttp.send();

	}
    catch(e)
    {
    	alert("unable to connect to server"); 
    }
	
	
	
	
}


function populateConsultantSelectBox()
{
	var xmlhttp = new XMLHttpRequest();
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
    		
			var myArr = JSON.parse(trimmedResponse);
			var resourceSelect = document.getElementById("consultant");
			

			for(var i=0; i<myArr.length ; i++)
			{	
				var resFName="";
				var resLName="";
				
				//get the consultant object name and id
				var consultant =myArr[i]; 
				
				var resFName = consultant.consultantfirstname;
				var resLName = consultant.consultantlastname;
				var resId =  consultant.consultantid;
				
				var resFullName = resFName + " " + resLName;
				
				
				
				//create an option element and add to select box
				var element = document.createElement("option");
				var resTextContent = resFullName;
				element.textContent = resTextContent.toUpperCase();
				element.value = resId;
				resourceSelect.appendChild(element);
				
				
			}
			var element = document.createElement("option");
			var NewConTextContent = "Add New Consultant";
			element.textContent = NewConTextContent.toUpperCase();
			element.value = "-1";
			resourceSelect.appendChild(element);
    	}
    
	};
    try
    {
	 	var url = "${pageContext.request.contextPath}/PopulateConsultantBox";
	 	xmlhttp.open("GET", url, true);
	 	xmlhttp.send();

	}
    catch(e)
    {
    	alert("unable to connect to server"); 
    }
	
	
	}
	
function removeOptions(selectbox)
{
    var i;
    for(i = selectbox.options.length - 1 ; i >= 0 ; i--)
    {
        selectbox.remove(i);
    }
}

function GetSelectedConsultantId(fname, lname)
{
	
	var xmlhttp = new XMLHttpRequest();
	var id = "1";
    
    xmlhttp.onreadystatechange = function()
    {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
    		
			var consultant = JSON.parse(trimmedResponse);
			id = consultant.consultantid;
			
			
    	}
    
	};
    try
    {
	 	var url = "${pageContext.request.contextPath}/GetConsultant?fname=" + fname + "&&lname=" + lname;
	 	xmlhttp.open("GET", url, false);
	 	xmlhttp.send();

	}
    catch(e)
    {
    	alert("unable to connect to server"); 
    }
   
    return id;
	}
	
	
	function GetSelectedProcedureId(procname)
	{
		var xmlhttp = new XMLHttpRequest();
		var id = "1";
	    
	    xmlhttp.onreadystatechange = function()
	    {
	        if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
	        {
	    		var trimmedResponse = xmlhttp.responseText.replace(/^\s*/,'').replace(/\s*$/,'').toLowerCase();
	    		
				var procedure = JSON.parse(trimmedResponse);
				id = procedure.procedureid;
				
				
	    	}
	    
		};
	    try
	    {
		 	var url = "${pageContext.request.contextPath}/GetProcedure?procname=" + procname ;
		 	xmlhttp.open("GET", url, false);
		 	xmlhttp.send();

		}
	    catch(e)
	    {
	    	alert("unable to connect to server"); 
	    }
	   
	    return id;
	}
	
	
	
	function savePatient()
	{ 
		/*
		//get and validate patient name
		var patientname = document.getElementById("patientname").value;
		if(patientname==null || patientname.trim()=="")
		{
			
			document.getElementById("divpatientname").style.color = "red";
	        document.getElementById("divpatientname").innerHTML = "Please enter patient name.";
			return false;
		} 
		*/
		var patientname = "";
		//get original patient name
		var originalName = document.getElementById("OriginalPatientName").value;
		
		
		//get and validate patient nhi
		var patientnhi = document.getElementById("patientnhi").value;
		
		/*
		if(patientnhi==null || patientnhi.trim()=="")
		{
			
			document.getElementById("divpatientnhi").style.color = "red";
	        document.getElementById("divpatientnhi").innerHTML = "Please enter patient NHI.";
			return false;
		} 
		*/

		//get and validate date
		var dateofEntry = document.getElementById("datepicker").value; 
		if(dateofEntry==null || dateofEntry.trim()=="")
		{
			document.getElementById("divdate").style.color = "red";
	        document.getElementById("divdate").innerHTML = "Please enter date.";
			return false;
		}
		
		var d = new Date(dateofEntry);
		
		var dt = d.getDate();
		var mn = d.getMonth();
		mn++;
		var yy = d.getFullYear();
		
		var dateString = mn + "/" + dt + "/" + yy;
		
		var oldDate = document.getElementById("OriginalDate").value
		
		//get consultant id
		var consultantId = document.getElementById("consultant").value;
		
		
		if(consultantId=='-1')
		{
			
		
			document.getElementById("divbuttonConsultant").style.color = "red";
	        document.getElementById("divbuttonConsultant").innerHTML = "Please choose the Consultant.";
			return false;
		}
		
		//get procedure ids
		var procedureIdArray = [];
		var procedureItems=document.getElementsByName('ProcedureCheckBox');
		
		
		
		for(var i=0; i<procedureItems.length; i++)
		{
			if(procedureItems[i].type=='checkbox' && procedureItems[i].checked==true)
			{
				
				procedureIdArray.push(procedureItems[i].value);
			}	
			
		}
		//if none of procedures are selected then prompt...
		if(procedureIdArray.length=='0')
		{
			document.getElementById("divbuttonProcedure").style.color = "red";
	        document.getElementById("divbuttonProcedure").innerHTML = "Please select procedures.";
			return false;
		
		}
		
		//get primary angio operator
		var primaryAngioOperator = document.getElementById("primaryAngioOpSelect").value;
		if(primaryAngioOperator=="1")
		{
			primaryOperator = 1;
		}
		else
		{
			primaryAngioOperator = 0;
		}
		
		//get indication ids
		var indicationIdArray = [];
		var indicationItems=document.getElementsByName('IndicationCheckBox');
		
		
		
		for(var i=0; i<indicationItems.length; i++)
		{
			if(indicationItems[i].type=='checkbox' && indicationItems[i].checked==true)
			{
				
				indicationIdArray.push(indicationItems[i].value);
			}	
			
		}
		//if none of indiactions are selected then prompt...
		if(indicationIdArray.length=='0')
		{
			document.getElementById("divbuttonIndication").style.color = "red";
	        document.getElementById("divbuttonIndication").innerHTML = "Please select Indications.";
			return false;
		
		}
		
		

		//get access
		var access = document.getElementById("access").value;
	
		
		//get arterial
		var arterial = document.getElementById("arterial").value;
		if(arterial=="1")
		{
			arterial = 1;
		}
		else
		{
			arterial = 0;
		}
		
		//get venous
		var venous = document.getElementById("venous").value;
		if(venous=="1")
		{
			venous = 1;
		}
		else
		{
			venous = 0;
		}
		
		
		//get angio final impression
		var finalImpIdArray = [];
		var finalImpressionItems=document.getElementsByName('FinalImpressionCheckBox');
		
		
		
		for(var i=0; i<finalImpressionItems.length; i++)
		{
			if(finalImpressionItems[i].type=='checkbox' && finalImpressionItems[i].checked==true)
			{
				
				finalImpIdArray.push(finalImpressionItems[i].value);
			}	
			
		}
		//if none of indiactions are selected then prompt...
		if(finalImpIdArray.length=='0')
		{
			document.getElementById("divbuttonImpression").style.color = "red";
	        document.getElementById("divbuttonImpression").innerHTML = "Please select Angio Final Impressions.";
			return false;
		
		}
		
		
		
		//get vessels diseased
		var vesselsIdArray = [];
		var vesselItems=document.getElementsByName('VesselsCheckBox');
		
		
		
		for(var i=0; i<vesselItems.length; i++)
		{
			if(vesselItems[i].type=='checkbox' && vesselItems[i].checked==true)
			{
				
				vesselsIdArray.push(vesselItems[i].value);
			}	
			
		}
		//if none of vessels are selected then prompt...
		if(vesselsIdArray.length=='0')
		{
			document.getElementById("divbuttonVessel").style.color = "red";
	        document.getElementById("divbuttonVessel").innerHTML = "Please select Vessels dieased.";
			return false;
		
		}
		
		
		//get intervention diseased
		var interventionIdArray = [];
		var interventionItems=document.getElementsByName('interventionCheckBox');
		
		
		
		for(var i=0; i<interventionItems.length; i++)
		{
			if(interventionItems[i].type=='checkbox' && interventionItems[i].checked==true)
			{
				
				interventionIdArray.push(interventionItems[i].value);
			}	
			
		}
		//if none of interventions are selected then prompt...
		if(interventionIdArray.length=='0')
		{
			document.getElementById("divbuttonIntervention").style.color = "red";
	        document.getElementById("divbuttonIntervention").innerHTML = "Please select Intervention.";
			return false;
		
		}
		
		
		//get primary operator
		var primaryOperator = document.getElementById("primaryOpSelect").value;
		if(primaryOperator=="1")
		{
			primaryOperator = 1;
		}
		else
		{
			primaryOperator = 0;
		}
		

		//get bms
		var numBMS = document.getElementById("numOfBMS").value;
		
		if(numBMS==null || numBMS.trim()=="")
		{
			numBMS=0;
		}
		
		else
		{
			if (isNaN(numBMS)) 
			{
				
				document.getElementById("divBMS").style.color = "red";
		        document.getElementById("divBMS").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		
		//get des
		var numDES = document.getElementById("numOfDES").value;
		if(numDES==null || numDES.trim()=="")
		{
			numDES=0;
		}
		else
		{
			if (isNaN(numDES)) 
			{
				document.getElementById("divDES").style.color = "red";
		        document.getElementById("numDES").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		
		
		//get numOfDEB
		var numDEB = document.getElementById("numOfDEB").value;
		if(numDEB==null || numDEB.trim()=="")
		{
			numDEB=0;
		}
		else
		{
			if (isNaN(numDEB)) 
			{
				document.getElementById("divDEB").style.color = "red";
		        document.getElementById("numDEB").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get numOfLMS
		var numLMS = document.getElementById("numOfLMS").value;
		if(numLMS==null || numLMS.trim()=="")
		{
			numLMS=0;
		}
		else
		{
			if (isNaN(numLMS))
			{
				document.getElementById("divLMS").style.color = "red";
		        document.getElementById("divLMS").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get numofLAD
		var numLAD = document.getElementById("numOfLAD").value;
		if(numLAD==null || numLAD.trim()=="")
		{
			numLAD=0;
		}
		else
		{
			if (isNaN(numLAD)) 
			{
				document.getElementById("divLAD").style.color = "red";
		        document.getElementById("numLAD").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get Cx
		var numCx = document.getElementById("numOfCx").value;
		if(numCx==null || numCx.trim()=="")
		{
			numCx=0;
		}
		else
		{
			if (isNaN(numCx)) 
			{
				document.getElementById("divCx").style.color = "red";
		        document.getElementById("divCx").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		//get rca
		var numRCA = document.getElementById("numOfRCA").value;
		if(numRCA==null || numRCA.trim()=="")
		{
			numRCA=0;
		}
		else
		{
			if (isNaN(numRCA)) 
			{
				document.getElementById("divRCA").style.color = "red";
		        document.getElementById("divRCA").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get ramus
		var ramus = document.getElementById("ramus").value;
		if(ramus==null || ramus.trim()=="")
		{
			ramus=0;
		}
		else
		{
			if (isNaN(ramus)) 
			{
				document.getElementById("divRamus").style.color = "red";
		        document.getElementById("divRamus").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		
		//get graft
		var graft = document.getElementById("graft").value;
		if(graft==null || graft.trim()=="")
		{
			graft=0;
		}
		else
		{
			if (isNaN(graft)) 
			{
				document.getElementById("divGraft").style.color = "red";
		        document.getElementById("divGraft").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get branchvessel
		var branchVessel = document.getElementById("branchVessel").value;
		if(branchVessel==null || branchVessel.trim()=="")
		{
			branchVessel=0;
		}
		else
		{
			if (isNaN(branchVessel)) 
			{
				document.getElementById("divBranchVessel").style.color = "red";
		        document.getElementById("divBranchVessel").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		
		//get additional procedures
		var addProcedure = document.getElementById("additionalProcedures").value;
		if(addProcedure==null || addProcedure.trim()=="")
		{
			addProcedure=0;
		}
		else
		{
			if (isNaN(addProcedure)) 
			{
				document.getElementById("divAdditionalProcedures").style.color = "red";
		        document.getElementById("divAdditionalProcedures").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get lvGram
		var lvGram = document.getElementById("lvgram").value;
		if(lvGram==null || lvGram.trim()=="")
		{
			lvGram=0;
		}
		
		else
		{
			if (isNaN(lvGram)) 
			{
				document.getElementById("divLvgram").style.color = "red";
		        document.getElementById("divLvgram").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get aortogram
		var aortogram = document.getElementById("aortogram").value;
		if(aortogram==null || aortogram.trim()=="")
		{
			aortogram=0;
		}
		else
		{
			if (isNaN(aortogram)) 
			{
				document.getElementById("divAortogram").style.color = "red";
		        document.getElementById("divAortogram").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get bypass angio
		var byPassAngio = document.getElementById("bypassangio").value;
		if(byPassAngio==null || byPassAngio.trim()=="")
		{
			byPassAngio=0;
		}
		else
		{
			if (isNaN(byPassAngio)) 
			{
				document.getElementById("divBypassAngio").style.color = "red";
		        document.getElementById("divBypassAngio").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get rhc
		var rhc = document.getElementById("rhc").value;
		if(rhc==null || rhc.trim()=="")
		{
			rhc=0;
		}
		else
		{
			if (isNaN(rhc)) 
			{
				document.getElementById("divRhc").style.color = "red";
		        document.getElementById("divRhc").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get hocm
		var hocm = document.getElementById("hocm").value;
		if(hocm==null || hocm.trim()=="")
		{
			hocm=0;
		}
		else
		{
			if (isNaN(hocm)) 
			{
				document.getElementById("divHOCM").style.color = "red";
		        document.getElementById("divHOCM").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get ivus
		var ivus = document.getElementById("ivus").value;
		if(ivus==null || ivus.trim()=="")
		{
			ivus=0;
		}
		else
		{
			if (isNaN(ivus)) 
			{
				document.getElementById("divIvus").style.color = "red";
		        document.getElementById("divIvus").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		

		//get oct
		var oct = document.getElementById("oct").value;
		if(oct==null || oct.trim()=="")
		{
			oct=0;
		}
		else
		{
			if (isNaN(oct)) 
			{
				document.getElementById("divOCT").style.color = "red";
		        document.getElementById("divOCT").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get ffr
		var ffr = document.getElementById("ffr").value;
		if(ffr==null || ffr.trim()=="")
		{
			ffr=0;
		}
		else
		{
			if (isNaN(ffr)) 
			{
				document.getElementById("divFFR").style.color = "red";
		        document.getElementById("divFFR").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		
		//get angioseal
		var angioseal = document.getElementById("angioseal").value;
		if(angioseal==null || angioseal.trim()=="")
		{
			angioseal=0;
		}
		else
		{
			if (isNaN(angioseal)) 
			{
				document.getElementById("divAngioseal").style.color = "red";
		        document.getElementById("divAngioseal").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get proglide
		var proglide = document.getElementById("proglide").value;
		if(proglide==null || proglide.trim()=="")
		{
			proglide=0;
		}
		else
		{
			if (isNaN(proglide)) 
			{
				document.getElementById("divProglide").style.color = "red";
		        document.getElementById("divProglide").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get balloon pump
		var balloonPump = document.getElementById("balloon").value;
		if(balloonPump==null || balloonPump.trim()=="")
		{
			balloonPump=0;
		}
		else
		{
			if (isNaN(balloonPump)) 
			{
				document.getElementById("divBalloon").style.color = "red";
		        document.getElementById("divBalloon").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get angio-sculpt
		var angioSculpt = document.getElementById("angiosculpt").value;
		if(angioSculpt==null || angioSculpt.trim()=="")
		{
			angioSculpt=0;
		}
		else
		{
			if (isNaN(angioSculpt)) 
			{
				document.getElementById("divAngiosculpt").style.color = "red";
		        document.getElementById("divAngiosculpt").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get rotablation
		var rotablation = document.getElementById("rotablation").value;
		if(rotablation==null || rotablation.trim()=="")
		{
			rotablation=0;
		}
		else
		{
			if (isNaN(rotablation)) 
			{
				document.getElementById("divRotablation").style.color = "red";
		        document.getElementById("divRotablation").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		
		
		//get pami
		var pami = document.getElementById("pami").value;
		if(pami==null || pami.trim()=="")
		{
			pami=0;
		}
		else
		{
			if (isNaN(pami)) 
			{
				document.getElementById("divPami").style.color = "red";
		        document.getElementById("divPami").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get complex cto
		var complexCto = document.getElementById("complexcto").value;
		if(complexCto==null || complexCto.trim()=="")
		{
			complexCto=0;
		}
		else
		{
			if (isNaN(complexCto)) 
			{
				document.getElementById("divComplexCto").style.color = "red";
		        document.getElementById("divComplexCto").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get complex bifucation
		var complexBifucation = document.getElementById("complexbifucation").value;
		if(complexBifucation==null || complexBifucation.trim()=="")
		{
			complexBifucation=0;
		}
		else
		{
			if (isNaN(complexBifucation)) 
			{
				document.getElementById("divComplexBifucation").style.color = "red";
		        document.getElementById("divComplexBifucation").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		
		//get complications
		var complications = document.getElementById("complications").value;
		if(complications==null || complications.trim()=="")
		{
			complications=0;
		}
		else
		{
			if (isNaN(complications)) 
			{
				document.getElementById("divComplications").style.color = "red";
		        document.getElementById("divComplications").innerHTML = "Please enter a whole number";
				return false;
			 }	
		}
		
		//get complication description
		var complicationDescription = document.getElementById("complicationdesc").value;
		if(complicationDescription==null || complicationDescription.trim()=="")
		{
			complicationDescription=" ";
		}
		//get comment
		var comment = document.getElementById("comment").value;
		if(comment==null || comment.trim()=="")
		{
			comment=" ";
		}
		
		
		
//code to call java class through ajax to save the details
		
		
		
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
		
			        	var saved  = "Patient details edited";
			        	
			        	
			        	//alert(trimmedResponse);
			        	if(trimmedResponse.toLowerCase()==saved.toLowerCase())
		            	{
		            		
		            		document.getElementById("savepatient").style.color = "red";
		            		document.getElementById("savepatient").innerHTML = "Edited Patient Record!";
		            		resetValues();
		            		
		            		
		            		
		            	}
			        	else
		        		{
			        		document.getElementById("savepatient").style.color = "red";
		           		 	document.getElementById("savepatient").innerHTML =trimmedResponse;
		           		 	//resetValues();
		        		}
		           		
			           
			            
			           
			        }
				};
			
			
			 try
			    {
				 var jStringProcedureIds = JSON.stringify(procedureIdArray);
				 var jStringIndicationIds = JSON.stringify(indicationIdArray);
				 var jStringAngioFinalImpressionIds = JSON.stringify(finalImpIdArray);
				 var jStringDiseasedVesselsIds = JSON.stringify(vesselsIdArray);
				 var jStringInterventionIds = JSON.stringify(interventionIdArray);
				 
				 
				 
				 
				 
				 
					
				  
				 var posturl = "oldDate=" + oldDate + "&&originalPatientName=" + originalName + "&&patientName=" + patientname  +"&&nhi=" + patientnhi + "&&date=" + dateString  + "&&consultantId=" + consultantId  + "&&procedureIdArray=" + jStringProcedureIds  + "&&primaryAngioOperator=" + primaryAngioOperator  + "&&indicationIdArray=" + jStringIndicationIds  +"&&access=" + access  + "&&arterial=" + arterial  + "&&venous=" + venous  + "&&finalImpIdArray=" + jStringAngioFinalImpressionIds  + "&&vesselsIdArray=" + jStringDiseasedVesselsIds  + "&&interventionIdArray=" + jStringInterventionIds  + "&&primaryOperator=" + primaryOperator  + "&&numBMS=" + numBMS  + "&&numDES=" + numDES  + "&&numDEB=" + numDEB + "&&numLMS=" + numLMS  + "&&numLAD=" + numLAD  + "&&numCx=" + numCx  + "&&numRCA=" + numRCA  + "&&ramus=" + ramus  +"&&graft=" + graft  + "&&branchVessel=" + branchVessel  + "&&addProcedure=" + addProcedure  + "&&lvGram=" + lvGram  + "&&aortogram=" + aortogram  + "&&byPassAngio=" + byPassAngio + "&&rhc=" + rhc  + "&&hocm=" + hocm  + "&&ivus="  + ivus  + "&&oct=" + oct  + "&&ffr=" + ffr  + "&&angioseal=" + angioseal  + "&&proglide=" + proglide  + "&&balloonPump=" + balloonPump  + "&&angioSculpt=" + angioSculpt  + "&&rotablation=" + rotablation  + "&&pami=" + pami  + "&&complexCto=" + complexCto  + "&&complexBifucation=" + complexBifucation  + "&&complications=" + complications  + "&&complicationDescription=" + complicationDescription  + "&&comment=" + comment ;
			    //alert(posturl);
				 
				 xmlhttpobj.open("POST", "${pageContext.request.contextPath}/EditPatient", true);
				
				 xmlhttpobj.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				 xmlhttpobj.send(posturl);
				   
				   
				}
			    catch(e)
			    {
			    	alert("unable to connect to server");
			    }	
		
	
	}
	
	function resetValues()
	{
		//document.forms["settingform"]["patientname"].value = "";
		document.forms["settingform"]["date"].value = "";
		document.forms["settingform"]["consultant"].value = "";
		
		unCheckCheckBox('ProcedureCheckBox');
		unCheckCheckBox('IndicationCheckBox');
		unCheckCheckBox('FinalImpressionCheckBox');
		unCheckCheckBox('VesselsCheckBox');
		unCheckCheckBox('interventionCheckBox');
		
		
		
		
		document.forms["settingform"]["numOfBMS"].value = "";
		document.forms["settingform"]["numOfDES"].value = "";
		document.forms["settingform"]["numOfDEB"].value = "";
		document.forms["settingform"]["numOfLMS"].value = "";
		document.forms["settingform"]["numOfLAD"].value = "";
		document.forms["settingform"]["numOfCx"].value = "";
		document.forms["settingform"]["numOfRCA"].value = "";
		
		document.forms["settingform"]["ramus"].value = "";
		document.forms["settingform"]["graft"].value = "";
		document.forms["settingform"]["branchVessel"].value = "";
		document.forms["settingform"]["additionalProcedures"].value = "";
		document.forms["settingform"]["lvgram"].value = "";
		document.forms["settingform"]["aortogram"].value = "";
		document.forms["settingform"]["bypassangio"].value = "";
		
		document.forms["settingform"]["rhc"].value = "";
		document.forms["settingform"]["ivus"].value = "";
		document.forms["settingform"]["hocm"].value = "";
		document.forms["settingform"]["oct"].value = "";
		document.forms["settingform"]["ffr"].value = "";
		document.forms["settingform"]["angioseal"].value = "";
		document.forms["settingform"]["proglide"].value = "";
		
		document.forms["settingform"]["balloon"].value = "";
		document.forms["settingform"]["angiosculpt"].value = "";
		document.forms["settingform"]["rotablation"].value = "";
		document.forms["settingform"]["pami"].value = "";
		document.forms["settingform"]["complexcto"].value = "";
		document.forms["settingform"]["complexbifucation"].value = "";
		document.forms["settingform"]["complications"].value = "";
		document.forms["settingform"]["complicationdesc"].value = "";
		document.forms["settingform"]["comment"].value = "";
		
	}
	
	function unCheckCheckBox(cn)
	{
		var cbarray = document.getElementsByName(cn);
		for(var i = 0; i < cbarray.length; i++)
		{

		     
		  cbarray[i].checked = false;
		  
		} 
	}
	
	function setDate()
	{
		var dtString = document.getElementById("OriginalDate").value;
		
		var oldDate = new Date(dtString);
		
		
		var year = oldDate.getFullYear();
		var month = oldDate.getMonth();
		month++;
		var monthAsString = month.toString();
		if (monthAsString.length === 1)
		{
			monthAsString = "0"+monthAsString;
		}
		var day = oldDate.getDate();
		var dayAsString = day.toString();
		if (dayAsString.length === 1)
		{
			dayAsString = "0"+dayAsString;
		}
		var oldDateStr = year.toString() + "-" + monthAsString + "-" + dayAsString;
		
		document.getElementById("datepicker").value = oldDateStr;
		
	}
</script>

</html>