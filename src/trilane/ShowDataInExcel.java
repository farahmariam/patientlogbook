package trilane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.io.OutputStream;

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
 * Servlet implementation class ShowDataInExcel
 */
@WebServlet("/ShowDataInExcel")
public class ShowDataInExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDataInExcel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		showDataInExcel(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		showDataInExcel(request,response);
	}
	
	protected void showDataInExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//If it exists, check if it is open...if open close it.
		ServletContext context = request.getServletContext();
		String excelFilePath = context.getInitParameter("excelfile-path");
		String excelFileName = context.getInitParameter("excelfile-final");
		String fileName = excelFilePath + "\\" + excelFileName;
		CommonDatabase commonObj = new CommonDatabase();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet resultset = null;
		
		try
		{
			File excelfile = new File(fileName);
			
			//First check if the excel file exists, else create it.
			boolean boolFileExists = commonObj.doesExcelExist(excelfile);
			
			if(!(boolFileExists))
			{
				//create the file
				commonObj.createNewExcel(excelfile);
			}
			else
			{
				//if file exists...first check if the file is already opened. If so, you cannot write to it. Ask the user to close it and then try again.
				boolean boolFileOpened = commonObj.isExcelFileOpen(fileName);
				if(boolFileOpened)
				{
					response.sendRedirect("jsp/ExcelAlreadyOpened.jsp");
				}
				
			}
			//code to write to the file
			
			FileInputStream fis = new FileInputStream(excelfile);
			XSSFWorkbook workbook = new XSSFWorkbook (fis);
			
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			
			
			 XSSFCellStyle cellStyle = workbook.createCellStyle();
			 
			 Font cellFont = workbook.createFont();
			 cellFont.setColor(IndexedColors.BLACK.getIndex());
			// cellFont.setFontHeightInPoints((short) 30);
			 cellFont.setFontName("Arial Unicode MS");
			
			 //cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			 cellStyle.setFont(cellFont);
			 
			
			
			//first delete all rows except the first.
			commonObj.deleteAllRowsExceptFirst(sheet);
			
			//now write to excel file
			
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
				
				XSSFCell r1c3 = row1.createCell(2);
				r1c3.setCellValue(patientName);
				r1c3.setCellStyle(cellStyle);
				
				
				XSSFCell r1c4 = row1.createCell(3);
				r1c4.setCellValue(patientNHI);
				r1c4.setCellStyle(cellStyle);
				
				
				XSSFCell r1c5 = row1.createCell(4);
				r1c5.setCellValue(consultantName);
				r1c5.setCellStyle(cellStyle);
				
				XSSFCell r1c6 = row1.createCell(5);
				r1c6.setCellValue(procedureName);
				r1c6.setCellStyle(cellStyle);
				
				
				XSSFCell r1c7 = row1.createCell(6);
				r1c7.setCellValue(primaryAngioOperator);
				r1c7.setCellStyle(cellStyle);
				
				XSSFCell r1c8 = row1.createCell(7);
				r1c8.setCellValue(indicationName);
				r1c8.setCellStyle(cellStyle);
				
				XSSFCell r1c9 = row1.createCell(8);
				r1c9.setCellValue(accessName);
				r1c9.setCellStyle(cellStyle);
				
				
				XSSFCell r1c10= row1.createCell(9);
				r1c10.setCellValue(arterial);
				r1c10.setCellStyle(cellStyle);
				
				XSSFCell r1c11 = row1.createCell(10);
				r1c11.setCellValue(venous);
				r1c11.setCellStyle(cellStyle);
				
				XSSFCell r1c12 = row1.createCell(11);
				r1c12.setCellValue(final_impression);
				r1c12.setCellStyle(cellStyle);
				
				
				XSSFCell r1c13= row1.createCell(12);
				r1c13.setCellValue(vessels_diseased);
				r1c13.setCellStyle(cellStyle);
				
				XSSFCell r1c14 = row1.createCell(13);
				r1c14.setCellValue(intervention);
				r1c14.setCellStyle(cellStyle);
				
				XSSFCell r1c15 = row1.createCell(14);
				r1c15.setCellValue(primary_operator);
				r1c15.setCellStyle(cellStyle);
				
				
				XSSFCell r1c16= row1.createCell(15);
				r1c16.setCellValue(bms);
				r1c16.setCellStyle(cellStyle);
				
				XSSFCell r1c17 = row1.createCell(16);
				r1c17.setCellValue(des);
				r1c17.setCellStyle(cellStyle);
				
				
				XSSFCell r1c18 = row1.createCell(17);
				r1c18.setCellValue(deb);
				r1c18.setCellStyle(cellStyle);
				
				
				
				XSSFCell r1c19= row1.createCell(18);
				r1c19.setCellValue(lms);
				r1c19.setCellStyle(cellStyle);
				
				
				XSSFCell r1c20 = row1.createCell(19);
				r1c20.setCellValue(lad);
				r1c20.setCellStyle(cellStyle);
				
				
				XSSFCell r1c21 = row1.createCell(20);
				r1c21.setCellValue(cx);
				r1c21.setCellStyle(cellStyle);
				
				XSSFCell r1c22= row1.createCell(21);
				r1c22.setCellValue(rca);
				r1c22.setCellStyle(cellStyle);
				
				XSSFCell r1c23 = row1.createCell(22);
				r1c23.setCellValue(ramus);
				r1c23.setCellStyle(cellStyle);
				
				XSSFCell r1c24 = row1.createCell(23);
				r1c24.setCellValue(graft);
				r1c24.setCellStyle(cellStyle);
				
				XSSFCell r1c25= row1.createCell(24);
				r1c25.setCellValue(branch_vessel);
				r1c25.setCellStyle(cellStyle);
				
				XSSFCell r1c26 = row1.createCell(25);
				r1c26.setCellValue(additional_proc);
				r1c26.setCellStyle(cellStyle);
				
				XSSFCell r1c27 = row1.createCell(26);
				r1c27.setCellValue(lvgram);
				r1c27.setCellStyle(cellStyle);
				
				
				XSSFCell r1c28= row1.createCell(27);
				r1c28.setCellValue(aortogram);
				r1c28.setCellStyle(cellStyle);
				
				XSSFCell r1c29 = row1.createCell(28);
				r1c29.setCellValue(bypass_angio);
				r1c29.setCellStyle(cellStyle);
				
				XSSFCell r1c30 = row1.createCell(29);
				r1c30.setCellValue(rhc);
				r1c30.setCellStyle(cellStyle);
				
				
				XSSFCell r1c31= row1.createCell(30);
				r1c31.setCellValue(hocm);
				r1c31.setCellStyle(cellStyle);
				
				
				XSSFCell r1c32 = row1.createCell(31);
				r1c32.setCellValue(ivus);
				r1c32.setCellStyle(cellStyle);
				
				
				XSSFCell r1c33 = row1.createCell(32);
				r1c33.setCellValue(oct);
				r1c33.setCellStyle(cellStyle);
				
				
				
				XSSFCell r1c34= row1.createCell(33);
				r1c34.setCellValue(ffr);
				r1c34.setCellStyle(cellStyle);
				
				
				XSSFCell r1c35 = row1.createCell(34);
				r1c35.setCellValue(angioseal);
				r1c35.setCellStyle(cellStyle);
				
				
				XSSFCell r1c36 = row1.createCell(35);
				r1c36.setCellValue(proglide);
				r1c36.setCellStyle(cellStyle);
				
				
				XSSFCell r1c37= row1.createCell(36);
				r1c37.setCellValue(balloon_pump);
				r1c37.setCellStyle(cellStyle);
				
			
				XSSFCell r1c38 = row1.createCell(37);
				r1c38.setCellValue(angio_sculpt);
				r1c38.setCellStyle(cellStyle);
				
				
				XSSFCell r1c39 = row1.createCell(38);
				r1c39.setCellValue(rotablation);
				r1c39.setCellStyle(cellStyle);
				
				
				
				XSSFCell r1c40= row1.createCell(39);
				r1c40.setCellValue(pami);
				r1c40.setCellStyle(cellStyle);
				
				
				XSSFCell r1c41 = row1.createCell(40);
				r1c41.setCellValue(complex_cto);
				r1c41.setCellStyle(cellStyle);
				
				XSSFCell r1c42 = row1.createCell(41);
				r1c42.setCellValue(complex_bifuc);
				r1c42.setCellStyle(cellStyle);
				
				XSSFCell r1c43= row1.createCell(42);
				r1c43.setCellValue(complications);
				r1c43.setCellStyle(cellStyle);
				
				XSSFCell r1c44 = row1.createCell(43);
				r1c44.setCellValue(complication_desc);
				r1c44.setCellStyle(cellStyle);
				
				XSSFCell r1c45 = row1.createCell(44);
				r1c45.setCellValue(comments);
				r1c45.setCellStyle(cellStyle);
				
			
				i++;
			}
			
		
			
			fis.close();
			
			
			FileOutputStream fos =new FileOutputStream(excelfile,false);
			
		    workbook.write(fos);
		    fos.flush();
		    fos.close();
		    
		    
			/*
			OutputStream out=response.getOutputStream();
			workbook.write(out);
		    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet ");
			System.out.println("Finished writing in excel sheet");
			response.getOutputStream().flush();
			*/
			//open the excel file
			commonObj.openExcelDocument(fileName);
			
			response.sendRedirect("jsp/mainPage.jsp");
			
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
