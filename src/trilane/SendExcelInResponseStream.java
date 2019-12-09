package trilane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Servlet implementation class SendExcelInResponseStream
 */
@WebServlet("/SendExcelInResponseStream")
public class SendExcelInResponseStream extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendExcelInResponseStream() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		createAndSendExcel(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		createAndSendExcel(request,response);
	}
	
	protected void createAndSendExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = request.getServletContext();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename=patientlogbook.xlsx");
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		try
		{
			
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
			
			
			
			
			//Create Header Row
			XSSFRow row0 = sheet.createRow(0);
			row0.setHeight( (short) 0x349 );
		
			
			
			XSSFCell r0c1 = row0.createCell(0);
			r0c1.setCellValue("S NO.");
			r0c1.setCellStyle(headerStyle);
			
			XSSFCell r0c2 = row0.createCell(1);
			r0c2.setCellValue("Date");
			r0c2.setCellStyle(headerStyle);
			/*
			XSSFCell r0c3 = row0.createCell(2);
			r0c3.setCellValue("Patient Name");
			r0c3.setCellStyle(headerStyle);
			*/
			
			
			XSSFCell r0c4 = row0.createCell(2);
			r0c4.setCellValue("NHI.");
			r0c4.setCellStyle(headerStyle);
			
			XSSFCell r0c5 = row0.createCell(3);
			r0c5.setCellValue("Consultant");
			r0c5.setCellStyle(headerStyle);
			
			XSSFCell r0c6 = row0.createCell(4);
			r0c6.setCellValue("Procedure");
			r0c6.setCellStyle(headerStyle);
			
			XSSFCell r0c7 = row0.createCell(5);
			r0c7.setCellValue("Primary Op Angio");
			r0c7.setCellStyle(headerStyle);
			
			XSSFCell r0c8 = row0.createCell(6);
			r0c8.setCellValue("Indication");
			r0c8.setCellStyle(headerStyle);
			
			XSSFCell r0c9 = row0.createCell(7);
			r0c9.setCellValue("Access");
			r0c9.setCellStyle(headerStyle);
			
			XSSFCell r0c10= row0.createCell(8);
			r0c10.setCellValue("Arterial");
			r0c10.setCellStyle(headerStyle);
			
			XSSFCell r0c11 = row0.createCell(9);
			r0c11.setCellValue("Venous");
			r0c11.setCellStyle(headerStyle);
			
			XSSFCell r0c12 = row0.createCell(10);
			r0c12.setCellValue("Angio Final Impression");
			r0c12.setCellStyle(headerStyle);
			
			XSSFCell r0c13= row0.createCell(11);
			r0c13.setCellValue("Vessels Diseased");
			r0c13.setCellStyle(headerStyle);
			
			XSSFCell r0c14 = row0.createCell(12);
			r0c14.setCellValue("Intervention");
			r0c14.setCellStyle(headerStyle);
			
			XSSFCell r0c15 = row0.createCell(13);
			r0c15.setCellValue("Primary Op");
			r0c15.setCellStyle(headerStyle);
			
			XSSFCell r0c16= row0.createCell(14);
			r0c16.setCellValue("Num of BMS");
			r0c16.setCellStyle(headerStyle);
			
			XSSFCell r0c17 = row0.createCell(15);
			r0c17.setCellValue("Num of DES");
			r0c17.setCellStyle(headerStyle);
			
			XSSFCell r0c18 = row0.createCell(16);
			r0c18.setCellValue("DEB");
			r0c18.setCellStyle(headerStyle);
			
			XSSFCell r0c19= row0.createCell(17);
			r0c19.setCellValue("LMS");
			r0c19.setCellStyle(headerStyle);
			
			XSSFCell r0c20 = row0.createCell(18);
			r0c20.setCellValue("LAD");
			r0c20.setCellStyle(headerStyle);
			
			XSSFCell r0c21 = row0.createCell(19);
			r0c21.setCellValue("Cx");
			r0c21.setCellStyle(headerStyle);
			
			XSSFCell r0c22= row0.createCell(20);
			r0c22.setCellValue("RCA");
			r0c22.setCellStyle(headerStyle);
			
			XSSFCell r0c23 = row0.createCell(21);
			r0c23.setCellValue("Ramus");
			r0c23.setCellStyle(headerStyle);
			
			XSSFCell r0c24 = row0.createCell(22);
			r0c24.setCellValue("Graft");
			r0c24.setCellStyle(headerStyle);
			
			XSSFCell r0c25= row0.createCell(23);
			r0c25.setCellValue("Branch Vessel");
			r0c25.setCellStyle(headerStyle);
			
			XSSFCell r0c26 = row0.createCell(24);
			r0c26.setCellValue("Additional Procedures");
			r0c26.setCellStyle(headerStyle);
			
			XSSFCell r0c27 = row0.createCell(25);
			r0c27.setCellValue("LV Gram");
			r0c27.setCellStyle(headerStyle);
			
			XSSFCell r0c28= row0.createCell(26);
			r0c28.setCellValue("Aortogram");
			r0c28.setCellStyle(headerStyle);
			
			XSSFCell r0c29 = row0.createCell(27);
			r0c29.setCellValue("Bypass Angio");
			r0c29.setCellStyle(headerStyle);
			
			XSSFCell r0c30 = row0.createCell(28);
			r0c30.setCellValue("RHC");
			r0c30.setCellStyle(headerStyle);
			
			XSSFCell r0c31= row0.createCell(29);
			r0c31.setCellValue("HOCM Assessment");
			r0c31.setCellStyle(headerStyle);
			
			XSSFCell r0c32 = row0.createCell(30);
			r0c32.setCellValue("IVUS");
			r0c32.setCellStyle(headerStyle);
			
			XSSFCell r0c33 = row0.createCell(31);
			r0c33.setCellValue("OCT");
			r0c33.setCellStyle(headerStyle);
			
			XSSFCell r0c34= row0.createCell(32);
			r0c34.setCellValue("FFR");
			r0c34.setCellStyle(headerStyle);
			
			XSSFCell r0c35 = row0.createCell(33);
			r0c35.setCellValue("Angioseal");
			r0c35.setCellStyle(headerStyle);
			
			XSSFCell r0c36 = row0.createCell(34);
			r0c36.setCellValue("Proglide");
			r0c36.setCellStyle(headerStyle);
			
			XSSFCell r0c37= row0.createCell(35);
			r0c37.setCellValue("Balloon Pump");
			r0c37.setCellStyle(headerStyle);
			
			XSSFCell r0c38 = row0.createCell(36);
			r0c38.setCellValue("Angiosculpt/Cutting");
			r0c38.setCellStyle(headerStyle);
			
			XSSFCell r0c39 = row0.createCell(37);
			r0c39.setCellValue("Rotablation");
			r0c39.setCellStyle(headerStyle);
			
			XSSFCell r0c40= row0.createCell(38);
			r0c40.setCellValue("PAMI/Rescue");
			r0c40.setCellStyle(headerStyle);
			
			XSSFCell r0c41 = row0.createCell(39);
			r0c41.setCellValue("Complex CTO");
			r0c41.setCellStyle(headerStyle);
			
			XSSFCell r0c42 = row0.createCell(40);
			r0c42.setCellValue("Complex Bifucation");
			r0c42.setCellStyle(headerStyle);
			
			XSSFCell r0c43= row0.createCell(41);
			r0c43.setCellValue("Complications");
			r0c43.setCellStyle(headerStyle);
			
			XSSFCell r0c44 = row0.createCell(42);
			r0c44.setCellValue("Complication Description");
			r0c44.setCellStyle(headerStyle);
			
			XSSFCell r0c45 = row0.createCell(43);
			r0c45.setCellValue("Comment");
			r0c45.setCellStyle(headerStyle);
			
			
			
			//now to add rows
			
			 XSSFCellStyle cellStyle = workbook.createCellStyle();
			 
			 Font cellFont = workbook.createFont();
			 cellFont.setColor(IndexedColors.BLACK.getIndex());
			 cellFont.setFontName("Arial Unicode MS");
			 cellStyle.setFont(cellFont);
			 
			 

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
				
				//Create First Row
				XSSFRow row1 = sheet.createRow(i);
				row1.setHeight( (short) 0x250 );
				
				
			
				
				
				XSSFCell r1c1 = row1.createCell(0);
				r1c1.setCellValue(i);
				r1c1.setCellStyle(cellStyle);
				
				XSSFCell r1c2 = row1.createCell(1);
				r1c2.setCellValue(strDate);
				r1c2.setCellStyle(cellStyle);
				
				/*
				XSSFCell r1c3 = row1.createCell(2);
				r1c3.setCellValue(patientName);
				r1c3.setCellStyle(cellStyle);
				*/
				
				XSSFCell r1c4 = row1.createCell(2);
				r1c4.setCellValue(patientNHI);
				r1c4.setCellStyle(cellStyle);
				
				
				XSSFCell r1c5 = row1.createCell(3);
				r1c5.setCellValue(consultantName);
				r1c5.setCellStyle(cellStyle);
				
				XSSFCell r1c6 = row1.createCell(4);
				r1c6.setCellValue(procedureName);
				r1c6.setCellStyle(cellStyle);
				
				
				XSSFCell r1c7 = row1.createCell(5);
				r1c7.setCellValue(primaryAngioOperator);
				r1c7.setCellStyle(cellStyle);
				
				XSSFCell r1c8 = row1.createCell(6);
				r1c8.setCellValue(indicationName);
				r1c8.setCellStyle(cellStyle);
				
				XSSFCell r1c9 = row1.createCell(7);
				r1c9.setCellValue(accessName);
				r1c9.setCellStyle(cellStyle);
				
				
				XSSFCell r1c10= row1.createCell(8);
				r1c10.setCellValue(arterial);
				r1c10.setCellStyle(cellStyle);
				
				XSSFCell r1c11 = row1.createCell(9);
				r1c11.setCellValue(venous);
				r1c11.setCellStyle(cellStyle);
				
				XSSFCell r1c12 = row1.createCell(10);
				r1c12.setCellValue(final_impression);
				r1c12.setCellStyle(cellStyle);
				
				
				XSSFCell r1c13= row1.createCell(11);
				r1c13.setCellValue(vessels_diseased);
				r1c13.setCellStyle(cellStyle);
				
				XSSFCell r1c14 = row1.createCell(12);
				r1c14.setCellValue(intervention);
				r1c14.setCellStyle(cellStyle);
				
				XSSFCell r1c15 = row1.createCell(13);
				r1c15.setCellValue(primary_operator);
				r1c15.setCellStyle(cellStyle);
				
				
				XSSFCell r1c16= row1.createCell(14);
				r1c16.setCellValue(bms);
				r1c16.setCellStyle(cellStyle);
				
				XSSFCell r1c17 = row1.createCell(15);
				r1c17.setCellValue(des);
				r1c17.setCellStyle(cellStyle);
				
				
				XSSFCell r1c18 = row1.createCell(16);
				r1c18.setCellValue(deb);
				r1c18.setCellStyle(cellStyle);
				
				
				
				XSSFCell r1c19= row1.createCell(17);
				r1c19.setCellValue(lms);
				r1c19.setCellStyle(cellStyle);
				
				
				XSSFCell r1c20 = row1.createCell(18);
				r1c20.setCellValue(lad);
				r1c20.setCellStyle(cellStyle);
				
				
				XSSFCell r1c21 = row1.createCell(19);
				r1c21.setCellValue(cx);
				r1c21.setCellStyle(cellStyle);
				
				XSSFCell r1c22= row1.createCell(20);
				r1c22.setCellValue(rca);
				r1c22.setCellStyle(cellStyle);
				
				XSSFCell r1c23 = row1.createCell(21);
				r1c23.setCellValue(ramus);
				r1c23.setCellStyle(cellStyle);
				
				XSSFCell r1c24 = row1.createCell(22);
				r1c24.setCellValue(graft);
				r1c24.setCellStyle(cellStyle);
				
				XSSFCell r1c25= row1.createCell(23);
				r1c25.setCellValue(branch_vessel);
				r1c25.setCellStyle(cellStyle);
				
				XSSFCell r1c26 = row1.createCell(24);
				r1c26.setCellValue(additional_proc);
				r1c26.setCellStyle(cellStyle);
				
				XSSFCell r1c27 = row1.createCell(25);
				r1c27.setCellValue(lvgram);
				r1c27.setCellStyle(cellStyle);
				
				
				XSSFCell r1c28= row1.createCell(26);
				r1c28.setCellValue(aortogram);
				r1c28.setCellStyle(cellStyle);
				
				XSSFCell r1c29 = row1.createCell(27);
				r1c29.setCellValue(bypass_angio);
				r1c29.setCellStyle(cellStyle);
				
				XSSFCell r1c30 = row1.createCell(28);
				r1c30.setCellValue(rhc);
				r1c30.setCellStyle(cellStyle);
				
				
				XSSFCell r1c31= row1.createCell(29);
				r1c31.setCellValue(hocm);
				r1c31.setCellStyle(cellStyle);
				
				
				XSSFCell r1c32 = row1.createCell(30);
				r1c32.setCellValue(ivus);
				r1c32.setCellStyle(cellStyle);
				
				
				XSSFCell r1c33 = row1.createCell(31);
				r1c33.setCellValue(oct);
				r1c33.setCellStyle(cellStyle);
				
				
				
				XSSFCell r1c34= row1.createCell(32);
				r1c34.setCellValue(ffr);
				r1c34.setCellStyle(cellStyle);
				
				
				XSSFCell r1c35 = row1.createCell(33);
				r1c35.setCellValue(angioseal);
				r1c35.setCellStyle(cellStyle);
				
				
				XSSFCell r1c36 = row1.createCell(34);
				r1c36.setCellValue(proglide);
				r1c36.setCellStyle(cellStyle);
				
				
				XSSFCell r1c37= row1.createCell(35);
				r1c37.setCellValue(balloon_pump);
				r1c37.setCellStyle(cellStyle);
				
			
				XSSFCell r1c38 = row1.createCell(36);
				r1c38.setCellValue(angio_sculpt);
				r1c38.setCellStyle(cellStyle);
				
				
				XSSFCell r1c39 = row1.createCell(37);
				r1c39.setCellValue(rotablation);
				r1c39.setCellStyle(cellStyle);
				
				
				
				XSSFCell r1c40= row1.createCell(38);
				r1c40.setCellValue(pami);
				r1c40.setCellStyle(cellStyle);
				
				
				XSSFCell r1c41 = row1.createCell(39);
				r1c41.setCellValue(complex_cto);
				r1c41.setCellStyle(cellStyle);
				
				XSSFCell r1c42 = row1.createCell(40);
				r1c42.setCellValue(complex_bifuc);
				r1c42.setCellStyle(cellStyle);
				
				XSSFCell r1c43= row1.createCell(41);
				r1c43.setCellValue(complications);
				r1c43.setCellStyle(cellStyle);
				
				XSSFCell r1c44 = row1.createCell(42);
				r1c44.setCellValue(complication_desc);
				r1c44.setCellStyle(cellStyle);
				
				XSSFCell r1c45 = row1.createCell(43);
				r1c45.setCellValue(comments);
				r1c45.setCellStyle(cellStyle);
				
			
				i++;
			}
			
			//write to response stream
			
			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);

            out.flush();
            out.close();
		
		}
		catch (SQLException e)
		{
			
			System.out.println(e.toString()); 
			
		}
		 catch (FileNotFoundException e) 
		{
			System.err.println("File Not Found: "  + e.getMessage());
		}
		//Catch all other IO exceptions
		catch (IOException e) 
		{
		    System.err.println("Caught IOException" + e.getMessage());
		}
		catch (Exception e) 
		{
		    System.err.println("Caught Exception" + e.getMessage());
		}
		finally
		{
			
			if (statement != null) try { statement.close(); } catch (SQLException e) {e.printStackTrace();}
			if (resultset != null) try { resultset.close(); } catch (SQLException e) {e.printStackTrace();}
			
	   	 	if (connection != null) try { connection.close(); } catch (SQLException e) {e.printStackTrace();}
		
		}
	}

}
