package trilane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;


/**
 * Servlet implementation class PopulateDatabaseFromExcel
 */
@WebServlet("/PopulateDatabaseFromExcel")
public class PopulateDatabaseFromExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopulateDatabaseFromExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		populateDatabase(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		populateDatabase(request,response);
	}
	
	
	
	protected void populateDatabase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		 
		try
		{

				ServletContext context = request.getServletContext();
				CommonDatabase objCommonDatabase = new CommonDatabase();
				
				String excelFilePath = context.getInitParameter("excelfile-path");
				String excelFileName = context.getInitParameter("excelfile-initial");
				String fileName = excelFilePath + "\\" +excelFileName ;

				
				
			
				FileInputStream fis = new FileInputStream(new File(fileName));
				XSSFWorkbook workbook = new XSSFWorkbook (fis);
				XSSFSheet sheet = workbook.getSheetAt(0);
				XSSFRow row;
				
				String strDate = "";
				String patient_name = "";
				String patient_nhi = "";
				String consultant_name = "";
				String procedure = "";
				int primary_angio_op = -1;
				String indication = "";
				String access="";
				int arterial=0;
				int venous=0;
				String Angio_final_imp="";
				String vessels_diseased="";
				String intervention="";
				int primary_op=0;
				int bms=0;
				int des=0;
				int deb=0;
				int lms=0;
				int lad=0;
				int cx=0;
				int rca=0;
				int ramus=0;
				int graft=0;
				int branch_vessel=0;
				int additional_proc=0;
				int lvgram=0;
				int aortogram=0;
				int bypass_angio=0;
				int rhc=-0;
				int hocm=0;
				int ivus=0;
				int oct=0;
				int ffr=0;
				int angioseal=0;
				int proglide=0;
				int balloon_pump=0;
				int angio_sculpt = 0;
				int rotablation = 0;
				int pami = 0;
				int complex_cto = 0;
				int complex_bifuc= 0;
				int complications = 0;
				String complication_desc="";
				String comments="";
				
				for (int i = 1; i <= sheet.getLastRowNum(); i++) 
				{
		            row = sheet.getRow(i);
		      		DataFormatter formatter = new DataFormatter();
		      		
		      		
		           
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(1))))
		            {
		            	 Cell dateCell =  row.getCell(1);
		            	strDate = formatter.formatCellValue(dateCell);
			            //strDate = row.getCell(1).getStringCellValue();	
		            }
		            
		            
		           
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(2))))
		            {
		            	 patient_name =  row.getCell(2).getStringCellValue();
		            }
		           
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(3))))
		            {
		            	patient_nhi = row.getCell(3).getStringCellValue();
		            }
		            
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(4))))
		            {
		            	consultant_name = row.getCell(4).getStringCellValue();
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(5))))
		            {
		            	procedure = row.getCell(5).getStringCellValue();
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(6))))
		            {
		            	primary_angio_op = (int)(row.getCell (6).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(7))))
		            {
		            	indication = row.getCell(7).getStringCellValue();
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(8))))
		            { 
		            	access = row.getCell(8).getStringCellValue();
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(9))))
		            {
		            	arterial = (int)(row.getCell(9).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(10))))
		            {
		            	venous = (int)(row.getCell(10).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(11))))
		            {
		            	Angio_final_imp = row.getCell(11).getStringCellValue();
		            }
		            
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(12))))
		            {
		            	//vessels_diseased = row.getCell(12).getStringCellValue();
			            Cell vesselsCell =  row.getCell(12);
			            vessels_diseased = formatter.formatCellValue(vesselsCell);
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(13))))
		            {
			            //intervention = row.getCell(13).getStringCellValue();
			            Cell interventionCell = row.getCell(13); 
			            intervention = formatter.formatCellValue(interventionCell);
		            }
		            
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(14))))
		            {
		            	primary_op = (int)(row.getCell(14).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(15))))
		            {
		            	bms = (int)(row.getCell(15).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(16))))
		            {
		            	des = (int)(row.getCell(16).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(17))))
		            {
		            	deb = (int)(row.getCell(17).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(18))))
		            {
		            	lms = (int)(row.getCell(18).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(19))))
		            {
		            	lad = (int)(row.getCell(19).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(20))))
		            {
		            	cx = (int)(row.getCell(20).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(21))))
		            {
		            	rca = (int)(row.getCell(21).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(22))))
		            {
		            	ramus = (int)(row.getCell(22).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(23))))
		            {
		            	graft = (int)(row.getCell(23).getNumericCellValue());
		            }
		           
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(24))))
		            {
		            	
		            	branch_vessel = (int)(row.getCell(24).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(25))))
		            {
		            	additional_proc= (int)(row.getCell(25).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(26))))
		            {
		            	lvgram = (int)(row.getCell(26).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(27))))
		            {
		            	aortogram = (int)(row.getCell(27).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(28))))
		            {
		            	bypass_angio = (int)(row.getCell(28).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(29))))
		            {
		            	rhc = (int)(row.getCell(29).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(30))))
		            {
		            	hocm = (int)(row.getCell(30).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(31))))
		            {
		            	
		            	ivus =  (int)(row.getCell(31).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(32))))
		            {
		            	oct = (int)(row.getCell(32).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(33))))
		            {
		            	ffr = (int)(row.getCell(33).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(34))))
		            {
		            	angioseal = (int)(row.getCell(34).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(35))))
		            {
		            	proglide = (int)(row.getCell(35).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(36))))
		            {
		            	balloon_pump = (int)(row.getCell(36).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(37))))
		            {
		            	angio_sculpt = (int)(row.getCell(37).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(38))))
		            {
		            	rotablation = (int)(row.getCell(38).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(39))))
		            {
		            	pami = (int)(row.getCell(39).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(40))))
		            {
		            	complex_cto = (int)(row.getCell(40).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(41))))
		            {
		            	complex_bifuc = (int)(row.getCell(41).getNumericCellValue());
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(42))))
		            {
		            	complications = (int)(row.getCell(42).getNumericCellValue());
		            }
		           
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(43))))
		            {
		            	//complication_desc = row.getCell(43).getStringCellValue();
		            	Cell complicationDescCell =  row.getCell(43);
		            	complication_desc = formatter.formatCellValue(complicationDescCell);
		            }
		            if(!(objCommonDatabase.isCellEmpty(row.getCell(34))))
		            {
		            	//comments = row.getCell(44).getStringCellValue();
		                Cell commentsCell =  row.getCell(44);
		            	comments = formatter.formatCellValue(commentsCell);
		            }
		            
		            //check if patient record exists, else save in patient table
		            if(objCommonDatabase.checkPatientExists(context, patient_nhi)==false)
		            {
		            	objCommonDatabase.InsertRowInDBPatient(context, patient_nhi, patient_name);
		            }
		            
		            //insert row in procedure_on_patient
		            objCommonDatabase.InsertRowInDBProcedure(context, strDate, patient_name, patient_nhi, consultant_name, procedure, primary_angio_op, indication, access, arterial, venous, Angio_final_imp, vessels_diseased, intervention, primary_op, bms, des, deb, lms, lad, cx, rca, ramus, graft, branch_vessel, additional_proc, lvgram, aortogram, bypass_angio, rhc, hocm, ivus, oct, ffr, angioseal, proglide, balloon_pump, angio_sculpt, rotablation, pami, complex_cto, complex_bifuc, complications, complication_desc, comments);
		            
		            
				}
				
				
				fis.close();
				
				
				
				
		}
		 catch (FileNotFoundException e) 
		{
			System.err.println("File Not Found: "  + e.getMessage());
		}
	
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("error:" + e.getMessage());
		}
		catch(Exception e)
		{
	      //Handle errors for Class.forName
	      e.printStackTrace();
	      System.out.println("error:" + e.getMessage());
	   	}
			

		finally
		{
			
			  

			
		}

	}

}
