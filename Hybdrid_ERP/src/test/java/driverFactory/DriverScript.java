package driverFactory;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import utilities.ExcelFileUtil;

public class DriverScript {

	public static WebDriver driver;
	String inputpath = "./FileInput/Controller.xlsx";
	String outputpath = "./FileOutput/HybridResults.xlsx";
	String TCSheet = "MasterTestCases";
	String TCModule = "Module_Name";
	ExtentReports reports;
	ExtentTest logger;
	
	public void startTest() throws IOException {
		String Module_status = "";
		String Module_new = "";
		// create object ExcelFileUtil 
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		// iterate all rows in TCsheet

		for (int i = 1; i <= xl.rowCount(TCSheet); i++) {
			if (xl.getCellData(TCSheet, i, 2).equalsIgnoreCase("Y")) {
						
				// store corresponding sheet or testcases into TCModule
				TCModule = xl.getCellData(TCSheet, i, 1);
				//define path for html report
				reports =new ExtentReports("./target/ExtentReports/"+TCModule+FunctionLibrary.generateDate()+".html");
				logger = reports.startTest(TCModule);
				logger.assignAuthor("sreeman");	
				// iterate corresponding sheet
				for (int j = 1; j <= xl.rowCount(TCModule); j++) {
					// read cell from TCModule sheet
					String Description = xl.getCellData(TCModule, j, 0);
					String Object_type = xl.getCellData(TCModule, j, 1);
					String Ltype = xl.getCellData(TCModule, j, 2);
					String Lvalue = xl.getCellData(TCModule, j, 3);
					String TData = xl.getCellData(TCModule, j, 4);
					try {
						if (Object_type.equalsIgnoreCase("startBrowser")) {
							driver = FunctionLibrary.startBrowser();
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_type.equalsIgnoreCase("openUrl")) {
							FunctionLibrary.openUrl();
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_type.equalsIgnoreCase("waitForElement")) {
							FunctionLibrary.waitForElement(Ltype, Lvalue, TData);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_type.equalsIgnoreCase("typeAction")) {
							FunctionLibrary.typeAction(Ltype, Lvalue, TData);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_type.equalsIgnoreCase("clickAction")) {
							FunctionLibrary.clickAction(Ltype, Lvalue);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_type.equalsIgnoreCase("validateTitle")) {
							FunctionLibrary.validateTitle(TData);
							logger.log(LogStatus.INFO, Description);
						}
						if (Object_type.equalsIgnoreCase("closeBrowser")) {
							FunctionLibrary.closeBrowser();
							logger.log(LogStatus.INFO, Description);
						}
						if(Object_type.equalsIgnoreCase("dropDownAction"))
								{
								FunctionLibrary.dropDownAction(Ltype, Lvalue, TData);
								logger.log(LogStatus.INFO,Description);
								}
						if(Object_type.equalsIgnoreCase("capStock"))
						{
							FunctionLibrary.capStock(Ltype, Lvalue);
							logger.log(LogStatus.INFO,Description);
						}
						if(Object_type.equalsIgnoreCase("stockTable"))
						{
							FunctionLibrary.stockTable();
							logger.log(LogStatus.INFO,Description);
						}
						//write pass into status cell TCModule 
						xl.setCellData(TCModule, j, 5, "Pass",outputpath);
						logger.log(LogStatus.PASS, Description);
						Module_status="True";
					} catch (Exception e) {
						System.out.println(e.getMessage());
						//write as fail into status cell TCModule
						xl.setCellData(TCModule, j, 5, "Fail",outputpath);
						logger.log(LogStatus.FAIL, Description);
						Module_new="False";
						File screen=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(screen, new File("./target/Screenshot/"+TCModule+Description+FunctionLibrary.generateDate()+".png"));

					}
					reports.endTest(logger);
					reports.flush();
					if(Module_status.equalsIgnoreCase("True"))
					{
						//write as pass into status TCSheet
						xl.setCellData(TCSheet, i, 3, "Pass", outputpath);
					}
					if(Module_new.equalsIgnoreCase("False"))
					{
						xl.setCellData(TCSheet, i, 3, "Fail",outputpath);
					}
					
				}

			} else {
				// write as blocked into status cell in TCSheet
				xl.setCellData(TCSheet, i, 2, "Blocked", outputpath);
			}

		}

	}

}
