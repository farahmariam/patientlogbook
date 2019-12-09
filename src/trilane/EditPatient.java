package trilane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

/**
 * Servlet implementation class EditPatient
 */
@WebServlet("/EditPatient")
public class EditPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPatient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		editPatient(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		editPatient(request,response);
	}
	protected void editPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	 
		response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	    response.setCharacterEncoding("UTF-8");
	   
	    HttpSession session = request.getSession();
	    
	    ServletContext context = request.getServletContext();
	    
	    
	    
	    
	    Gson gson = new Gson();
	    Connection connection = null;	
		
		Statement stGetProcedureNames = null;
		ResultSet rsGetProcedureNames = null;
		
		Statement stGetIndicationNames = null;
		ResultSet rsGetIndicationNames = null;
		
		Statement stGetFinalImpressionNames = null;
		ResultSet rsGetFinalImpressionNames = null;
		
		Statement stGetDiseasedVesselNames = null;
		ResultSet rsGetDiseasedVesselNames = null;

		Statement stGetIntervention = null;
		ResultSet rsGetIntervention = null;
		
		Statement stCheckPatientExists = null;
		ResultSet rsExists = null;
		
		Statement stSavePatient = null;
		
		Statement stSavePatientRecord = null;
		
		Statement stGetPatientId = null;
		ResultSet rsGetPatientId = null;
		
		
		String dateofEntry ="";
		
		try
		{
			
			int consultantId=0;
			
			
			//get original patient name
			String	originalPatientName = request.getParameter("originalPatientName");
			
			
			String originalDate = request.getParameter("oldDate"); 
			//System.out.println("original date = " + originalDate );
			
			String patientName = request.getParameter("patientName");
			String patientNHI= request.getParameter("nhi");
			dateofEntry = request.getParameter("date");
			if((request.getParameter("consultantId")!=null) && (request.getParameter("consultantId")!="") )
			{
				consultantId = Integer.parseInt(request.getParameter("consultantId"));
			}
			
			
			int[] procedureIds = gson.fromJson(request.getParameter("procedureIdArray"), int[].class);
			int primaryAngioOperator = Integer.parseInt(request.getParameter("primaryAngioOperator"));
			int[] indicationIds = gson.fromJson(request.getParameter("indicationIdArray"), int[].class);
			
			
			
			String access = request.getParameter("access");			
			int arterial = Integer.parseInt(request.getParameter("arterial"));
			int venous = Integer.parseInt(request.getParameter("venous"));
			int[] finalImpressionIds = gson.fromJson(request.getParameter("finalImpIdArray"), int[].class);
			int[] diseasedVesselsIds = gson.fromJson(request.getParameter("vesselsIdArray"), int[].class);
			int[] interventionIds = gson.fromJson(request.getParameter("interventionIdArray"), int[].class);
			int primaryOperator = Integer.parseInt(request.getParameter("primaryOperator"));
			
			int numBMS = Integer.parseInt(request.getParameter("numBMS"));
			int numDES = Integer.parseInt(request.getParameter("numDES"));
			int numDEB = Integer.parseInt(request.getParameter("numDEB"));
			int numLMS = Integer.parseInt(request.getParameter("numLMS"));
			int numLAD = Integer.parseInt(request.getParameter("numLAD"));
			int numCx = Integer.parseInt(request.getParameter("numCx"));
			int numRCA = Integer.parseInt(request.getParameter("numRCA"));
			
			
			int ramus = Integer.parseInt(request.getParameter("ramus"));
			int graft = Integer.parseInt(request.getParameter("graft"));
			int branchVessel = Integer.parseInt(request.getParameter("branchVessel"));
			int additionalProcedure = Integer.parseInt(request.getParameter("addProcedure"));
			int lvGram = Integer.parseInt(request.getParameter("lvGram"));
			int aortogram = Integer.parseInt(request.getParameter("aortogram"));
			
	
			int byPassAngio = Integer.parseInt(request.getParameter("byPassAngio"));
			int rhc = Integer.parseInt(request.getParameter("rhc"));
			int hocm = Integer.parseInt(request.getParameter("hocm"));
			int ivus = Integer.parseInt(request.getParameter("ivus"));
			int oct = Integer.parseInt(request.getParameter("oct"));
			int ffr = Integer.parseInt(request.getParameter("ffr"));
			
			int angioseal = Integer.parseInt(request.getParameter("angioseal"));
			int proglide = Integer.parseInt(request.getParameter("proglide"));
			int balloonPump = Integer.parseInt(request.getParameter("balloonPump"));
			int angioSculpt = Integer.parseInt(request.getParameter("angioSculpt"));
			int rotablation = Integer.parseInt(request.getParameter("rotablation"));
			int pami = Integer.parseInt(request.getParameter("pami"));
			
		
			int complexCto = Integer.parseInt(request.getParameter("complexCto"));
			int complexBifucation = Integer.parseInt(request.getParameter("complexBifucation"));
			int complications = Integer.parseInt(request.getParameter("complications"));
			String complicationDescription = request.getParameter("complicationDescription");
			String comment = request.getParameter("comment");
			
				
			
			Class.forName("com.mysql.jdbc.Driver");
			
			String databaseUrl = (String) context.getInitParameter("data-url");
			String databaseUser = context.getInitParameter("data-user");
			String databasePwd = context.getInitParameter("data-pwd");
		
			connection = DriverManager.getConnection(databaseUrl,databaseUser, databasePwd);
			
			CommonDatabase objCommon = new CommonDatabase();
			
			
			//getting the consultant name
			String[] strArrayName = objCommon.getConsultantName(context, consultantId);
			String firstName = strArrayName[0];
			String lastName = strArrayName[1];
			String consultant_name = firstName + " " + lastName;
			
			//getting the procedure names
			ArrayList<Integer> procedureIdList = new ArrayList<Integer>();
			

			for( int i = 0; i < procedureIds.length ; i++)
			{
				procedureIdList.add(Integer.valueOf(procedureIds[i]));
			    
			}
			
			
			stGetProcedureNames = connection.createStatement();
			String procedureNames = "";
			ArrayList<String> procedureNameList = new ArrayList();
			
			
			for(int j=0;j<procedureIdList.size();j++)
			{
				int procedureId = procedureIdList.get(j);
				String sqlQueryGetProcedure = "select medproc_name from medical_procedure where medproc_id = " + procedureId;
				rsGetProcedureNames = stGetProcedureNames.executeQuery(sqlQueryGetProcedure);
				
				if (rsGetProcedureNames.next())
				{
					String procedureName = rsGetProcedureNames.getString(1);
					procedureNameList.add(procedureName); 
					if(j==0)
					{
						procedureNames = procedureNames.concat(procedureName);
					}
					else
					{
						procedureNames = procedureNames.concat("," + procedureName);
					}
					
				}
			}
			//replace the commas with spaces in string
			if( procedureNames.indexOf(",") != -1 )
			 {
				procedureNames = procedureNames.replaceAll(","," ");
			 }
			
			//getting the indication names
			ArrayList<Integer> indicationIdList = new ArrayList<Integer>();
			

			for( int i = 0; i < indicationIds.length ; i++)
			{
				indicationIdList.add(Integer.valueOf(indicationIds[i]));
			    
			}
			
			stGetIndicationNames = connection.createStatement();
			String indicationNames = "";
			ArrayList<String> indicationNameList = new ArrayList();
			
			for(int j=0;j<indicationIdList.size();j++)
			{
				int indicationId = indicationIdList.get(j);
				String sqlQueryGetIndication = "select ind_name from indication where ind_id = " + indicationId;
				rsGetIndicationNames = stGetIndicationNames.executeQuery(sqlQueryGetIndication);
				
				if (rsGetIndicationNames.next())
				{
					String indicationName = rsGetIndicationNames.getString(1);
					indicationNameList.add(indicationName);
					if(j==0)
					{
						indicationNames = indicationNames.concat(indicationName);
					}
					else
					{
						indicationNames = indicationNames.concat("," + indicationName);
					}
				}
			}
			//replace the commas with spaces in string
			if( indicationNames.indexOf(",") != -1 )
			 {
				indicationNames = indicationNames.replaceAll(","," ");
			 }
			
			
			//getting the angio final impression
			ArrayList<Integer> AngioFinalImpressionIdList = new ArrayList<Integer>();
			

			for( int i = 0; i < finalImpressionIds.length ; i++)
			{
				AngioFinalImpressionIdList.add(Integer.valueOf(finalImpressionIds[i]));
			    
			}
			
			stGetFinalImpressionNames = connection.createStatement();
			String finalImpressionNames="";
			ArrayList<String> FinalImpressionNameList = new ArrayList();
			
			for(int j=0;j<AngioFinalImpressionIdList.size();j++)
			{
				int finalImpressionId = AngioFinalImpressionIdList.get(j);
				String sqlQueryGetImpression = "select impression_name from final_impression where impression_id = " + finalImpressionId;
				rsGetFinalImpressionNames = stGetFinalImpressionNames.executeQuery(sqlQueryGetImpression);
				
				if (rsGetFinalImpressionNames.next())
				{
					String impressionName = rsGetFinalImpressionNames.getString(1);
					FinalImpressionNameList.add(impressionName);
					
					if(j==0)
					{
						finalImpressionNames = finalImpressionNames.concat(impressionName);
					}
					else
					{
						finalImpressionNames = finalImpressionNames.concat("," + impressionName);
					}
				}
			}
			
			//replace the commas with spaces in string
			if( finalImpressionNames.indexOf(",") != -1 )
			 {
				finalImpressionNames = finalImpressionNames.replaceAll(","," ");
			 }
			
			
			//getting the vessels diseased
			ArrayList<Integer> AngioVesselsDiseasedIdList = new ArrayList<Integer>();
			

			for( int i = 0; i < diseasedVesselsIds.length ; i++)
			{
				AngioVesselsDiseasedIdList.add(Integer.valueOf(diseasedVesselsIds[i]));
			    
			}
			
			stGetDiseasedVesselNames = connection.createStatement();
			String diseasedVessels = "";
			ArrayList<String> VesselsDiseasedNameList = new ArrayList();
			
			for(int j=0;j<AngioVesselsDiseasedIdList.size();j++)
			{
				int diseasedVesselId = AngioVesselsDiseasedIdList.get(j);
				String sqlQueryGetDiseasedVessel = "select vessel_name from vessels where vessel_id = " + diseasedVesselId;
				rsGetDiseasedVesselNames = stGetDiseasedVesselNames.executeQuery(sqlQueryGetDiseasedVessel);
				
				if (rsGetDiseasedVesselNames.next())
				{
					String diseasedVesselName = rsGetDiseasedVesselNames.getString(1);
					if (diseasedVesselName.trim().toLowerCase().equals("none")) diseasedVesselName = "0";
					VesselsDiseasedNameList.add(diseasedVesselName);
					if(j==0)
					{
						diseasedVessels = diseasedVessels.concat(diseasedVesselName);
					}
					else
					{
						diseasedVessels = diseasedVessels.concat("," + diseasedVesselName);
					}
				}
			}
			
			//replace the commas with spaces in string
			if( diseasedVessels.indexOf(",") != -1 )
			 {
				diseasedVessels = diseasedVessels.replaceAll(","," ");
			 }
			
			
			//getting the intervention
			ArrayList<Integer> InterventionIdList = new ArrayList<Integer>();
			

			for( int i = 0; i < interventionIds.length ; i++)
			{
				InterventionIdList.add(Integer.valueOf(interventionIds[i]));
			    
			}
			
			stGetIntervention = connection.createStatement();
			String interventionNames = "";
			ArrayList<String> InterventionNameList = new ArrayList();
			
			for(int j=0;j<InterventionIdList.size();j++)
			{
				int interventionId = InterventionIdList.get(j);
				String sqlQueryGetIntervention = "select intervention_name from intervention where intervention_id = " + interventionId;
				
				rsGetIntervention = stGetIntervention.executeQuery(sqlQueryGetIntervention);
				
				if (rsGetIntervention.next())
				{
					String InterventionName = rsGetIntervention.getString(1);
					if (InterventionName.trim().toLowerCase().equals("none")) InterventionName = "0";
					InterventionNameList.add(InterventionName);
					if(j==0)
					{
						interventionNames = interventionNames.concat(InterventionName);
					}
					else
					{
						interventionNames = interventionNames.concat("," + InterventionName);
					}
				}
			}
			
			//replace the commas with spaces in string
			if( interventionNames.indexOf(",") != -1 )
			 {
				interventionNames = interventionNames.replaceAll(","," ");
			 }
			/*
			//check if the patient name has changed. If so update the patient table
			if(!(originalPatientName.trim().equalsIgnoreCase(patientName.trim())))
			{
				
				//code to check if patient record exists in patient table...else save it.
				
				stCheckPatientExists = connection.createStatement();
				String patientNameNoSpace =patientName.replace(" ", "") ;
				//String existsQuery = "select * from patient where lower(replace(patient_name,' ','')) = lower('" +  patientNameNoSpace + "')" ;
				String existsQuery = "select * from patient where lower(replace(nhi,' ','')) = lower('" +  patientNHI + "')" ;
				
				
				
				rsExists = stCheckPatientExists.executeQuery(existsQuery);
				
				if (rsExists.next())
				{
					//edit patient name in patient table
					
					 stSavePatient = connection.createStatement();
					
					 String sqlQuery = "update patient set patient_name='" + patientName  + "' where nhi= '" + patientNHI + "'";
			
						
					 int i = stSavePatient.executeUpdate(sqlQuery);
					
				}
		
			}
	
			*/
		
			
				
				//code to update the patient record in procedure_on_patient table
				 stSavePatientRecord = connection.createStatement();
				 
				String sqlQuerySave = "update procedure_on_patient set proc_date = '" + dateofEntry + "',patient_name = '" + patientName +  "',con_name = '" + consultant_name + "',medproc_names = '" + procedureNames + "',primary_operator_angio=" + primaryAngioOperator + ",indication_names='" + indicationNames + "',access_name = '" + access + "',arterial = " + arterial + ",venous = " + venous + ",final_impression ='" + finalImpressionNames.toString() + "',vessels_diseased = '" + VesselsDiseasedNameList.toString() +"',intervention = '" + interventionNames.toString() + "',primary_op = " + primaryOperator + ",bms = " + numBMS + ",des = " + numDES + ",deb = " + numDEB + ",lms = " + numLMS + ",lad = " + numLAD + ",cx = " + numCx + ",rca = " + numRCA + ",ramus = " + ramus + ",graft = " + graft + ",branch_vessel = " + branchVessel + ",additional_proc = " + additionalProcedure + ",lvgram = " + lvGram + ",aortogram = " + aortogram + ",bypass_angio = " + byPassAngio + ",rhc = " + rhc + ",hocm_assessment = " + hocm + ",ivus = " + ivus + ",oct = " + oct + ",ffr = " + ffr + ",angioseal = " + angioseal + ",proglide = " + proglide + ",balloon_pump = " + balloonPump + ",angio_sculpt = " + angioSculpt + ",rotablation = " + rotablation + ",pami = " + pami + ",complex_cto = " + complexCto + ",complex_bifuc = " + complexBifucation + ",complications = " + complications + ",complication_desc = '" + complicationDescription + "',comments = '" + comment + "' where lower(replace(patient_nhi,' ','')) = lower('" +  patientNHI + "') and lower(replace(proc_date,' ','')) = lower('" + originalDate  + "')" ;
				//System.out.println("edit query: " + sqlQuerySave);
				
				 int i = stSavePatientRecord.executeUpdate(sqlQuerySave);
				 
				if (i > 0)
				{
					
					response.getWriter().write("Patient details edited");
				
					
				} 
				else 
				{
					response.getWriter().write("error");
					
				}
				
				
			
		
			
			
		
		}
		
		catch(SQLException se)
		{
	      //Handle errors for JDBC
		  response.getWriter().write("Error: " + se.getMessage() );
			
			
	      System.out.println(se.toString());
	    }
	   
		catch(Exception e)
		{
			 response.getWriter().write("Error: " + e.getMessage() );
				
			
		
	      //Handle errors for Class.forName
			System.out.println(e.toString());
	   	}
		finally
		{
			
			if (stGetProcedureNames != null) try { stGetProcedureNames.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetProcedureNames != null) try { rsGetProcedureNames.close(); } catch (SQLException e) {e.printStackTrace();}
			
			if (stGetIndicationNames != null) try { stGetIndicationNames.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetIndicationNames != null) try { stGetIndicationNames.close(); } catch (SQLException e) {e.printStackTrace();}
			
			if (stGetFinalImpressionNames != null) try { stGetFinalImpressionNames.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetFinalImpressionNames != null) try { rsGetFinalImpressionNames.close(); } catch (SQLException e) {e.printStackTrace();}
			
			
			if (stGetDiseasedVesselNames != null) try { stGetDiseasedVesselNames.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetDiseasedVesselNames != null) try { rsGetDiseasedVesselNames.close(); } catch (SQLException e) {e.printStackTrace();}
			
			if (stGetIntervention != null) try { stGetIntervention.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetIntervention != null) try { rsGetIntervention.close(); } catch (SQLException e) {e.printStackTrace();}
			
			
			if (stGetIntervention != null) try { stGetIntervention.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetIntervention != null) try { rsGetIntervention.close(); } catch (SQLException e) {e.printStackTrace();}
			
			
			if (stCheckPatientExists != null) try { stCheckPatientExists.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsExists != null) try { rsExists.close(); } catch (SQLException e) {e.printStackTrace();}
			
			if (stSavePatient != null) try { stSavePatient.close(); } catch (SQLException e) {e.printStackTrace();}
			
			if (stSavePatientRecord != null) try { stSavePatientRecord.close(); } catch (SQLException e) {e.printStackTrace();}
			

			if (stGetPatientId != null) try { stGetPatientId.close(); } catch (SQLException e) {e.printStackTrace();}
			if (rsGetPatientId != null) try { rsGetPatientId.close(); } catch (SQLException e) {e.printStackTrace();}
			
			
			
			
			if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
			
			
		}
		
	 
	}

}
