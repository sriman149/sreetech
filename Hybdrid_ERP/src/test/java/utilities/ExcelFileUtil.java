package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {

	XSSFWorkbook wb;

	// write constructor for reading excel path
	// constructor is used to initializing object for class, In order to invoke
	// those class methods
	public ExcelFileUtil(String excelPath) throws IOException {
		FileInputStream fi = new FileInputStream(excelPath);
		wb = new XSSFWorkbook(fi);
	}

	// method for counting rows in a sheet
	public int rowCount(String SheetName) {
		return wb.getSheet(SheetName).getLastRowNum();
	}

	// method for reading cell data
	public String getCellData(String SheetName, int row, int column) {
		String data = "";
		if (wb.getSheet(SheetName).getRow(row).getCell(column).getCellType() == CellType.NUMERIC) {
			int celldata = (int) wb.getSheet(SheetName).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		} else {
			data = wb.getSheet(SheetName).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}

	// method for writing
	public void setCellData(String sheetName, int row, int column, String status, String WriteExcel)
			throws IOException {
		// get sheet from wb
		XSSFSheet ws = wb.getSheet(sheetName);
		// get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		// create cell
		XSSFCell cell = rowNum.createCell(column);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("Pass")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		} else if (status.equalsIgnoreCase("Fail")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		} else if (status.equalsIgnoreCase("Block")) {
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(WriteExcel);
		wb.write(fo);

	}

	public static void main(String[] args) throws IOException {
		ExcelFileUtil xl=new ExcelFileUtil("D:/QEdgeTechnologies/Sample.xlsx");
		//count no of rows in Emp
		int rc=xl.rowCount("Emp");
		System.out.println(rc);
		for(int i=1;i<=rc;i++)
		{
			String fname=xl.getCellData("Emp",i,0);
			String mname=xl.getCellData("Emp",i,1);
			String lname=xl.getCellData("Emp",i,2);
			String eid=xl.getCellData("Emp",i,3);
			System.out.println(fname+"   "+mname+" "+lname+" "+eid);
			//xl.setCellData("Emp",i,4,"Pass","D:/Results.xlsx");
			xl.setCellData("Emp",i,4,"Fail","D:/Results.xlsx");
		}
	}
}
