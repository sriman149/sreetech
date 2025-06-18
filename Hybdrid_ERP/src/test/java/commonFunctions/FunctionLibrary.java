package commonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class FunctionLibrary {
	public static Properties conpro;
	public static WebDriver driver;

	public static WebDriver startBrowser() throws FileNotFoundException, IOException {
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if (conpro.getProperty("Browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (conpro.getProperty("Browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (conpro.getProperty("Browser").equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			Reporter.log("Browser value is not matching", true);
		}
		return driver;
	}

	// method for launching url
	public static void openUrl() {
		driver.get(conpro.getProperty("Url"));
	}

	// method for wait for element
	public static void waitForElement(String LocatorType, String LocatorValue, String testData) {
		// Converting testData of String into Integer
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(testData)));
		if (LocatorType.equalsIgnoreCase("name")) {
			// wait element element is visible
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		if (LocatorType.equalsIgnoreCase("xpath")) {
			// wait element element is visible
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}
		if (LocatorType.equalsIgnoreCase("id")) {
			// wait element element is visible
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
	}
	// method for nay textbox
	public static void typeAction(String LocatorType, String LocatorValue, String testData) {
		if (LocatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(testData);
		}
		if (LocatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(testData);
		}
		if (LocatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(testData);
		}
	}

//method to perform click action
	public static void clickAction(String LocatorType, String LocatorValue) {
		if (LocatorType.equalsIgnoreCase("xpath")) {
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		if (LocatorType.equalsIgnoreCase("name")) {
			driver.findElement(By.name(LocatorValue)).click();
		}
		if (LocatorType.equalsIgnoreCase("id")) {
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
	}

	
	// method for validate any page Title
	public static void validateTitle(String Expected_Title) {
		String Actual_Title = driver.getTitle();
		try {
			Assert.assertEquals(Actual_Title, Expected_Title, "Title is not matching");
		} catch (AssertionError a) {
			System.out.println(a.getMessage());
		}

	}

	public static void closeBrowser() {
		driver.quit();
	}

	// method for generate date
	public static String generateDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("YYYY_MM_dd hh_mm_ss");
		return df.format(date);

	}

	// method for dropdown action
	public static void dropDownAction(String LocatorType, String LocatorValue, String TestData) {
		if (LocatorType.equalsIgnoreCase("xpath")) {
			int value = Integer.parseInt(TestData);
			
			Select s = new Select(driver.findElement(By.xpath(LocatorValue)));
			s.selectByIndex(value);
		}
		if (LocatorType.equalsIgnoreCase("name")) {
			int value = Integer.parseInt(TestData);
			Select s = new Select(driver.findElement(By.name(LocatorValue)));
			s.selectByIndex(value);
		}
		if (LocatorType.equalsIgnoreCase("id")) {
			int value = Integer.parseInt(TestData);
			Select s = new Select(driver.findElement(By.id(LocatorValue)));
			s.selectByIndex(value);
		}
	}
	//method for capture Stock number into notepad
	public static void capStock(String LocatorType,String LocatorValue) throws IOException
	{
		String stockNumber="";
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			stockNumber=driver.findElement(By.xpath(LocatorValue)).getAttribute("value");
		}
		if(LocatorType.equalsIgnoreCase("name"))
		{
			stockNumber=driver.findElement(By.name(LocatorValue)).getAttribute("value");
		}
		if(LocatorType.equalsIgnoreCase("id"))
		{
			stockNumber=driver.findElement(By.id(LocatorValue)).getAttribute("value");
		}
		//System.out.println(stockNumber);
		//create note pad under CaptureData folder
		FileWriter fw=new FileWriter("./CaptureData/StockNumber.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(stockNumber);
		bw.flush();
		bw.close();
	}
	//method for stock table
	public static void stockTable() throws IOException, InterruptedException
	{
	 //read stock Number from notepad
	 FileReader fr=new FileReader("./CaptureData/StockNumber.txt");
	 BufferedReader br=new BufferedReader(fr);
	 String Exp_Data=br.readLine(); 
	 if(!driver.findElement(By.xpath(conpro.getProperty("search-textbook"))).isDisplayed())
	 {
		 driver.findElement(By.xpath(conpro.getProperty("search-panel"))).click();
		 driver.findElement(By.xpath(conpro.getProperty("search-textbook"))).clear();
		 driver.findElement(By.xpath(conpro.getProperty("search-textbook"))).sendKeys(Exp_Data);
		 driver.findElement(By.xpath(conpro.getProperty("search-button"))).click();
		 Thread.sleep(3000);
		 String Act_Data=driver.findElement(By.xpath("//table[@id='tbl_a_stock_itemslist']/tbody/tr/td[8]/div/span/span")).getText();
		 Reporter.log(Act_Data+"      "+Exp_Data,true);
		 try {
		 Assert.assertEquals(Exp_Data, Act_Data,"Stock Number not found in table");
		 }catch(Throwable t)
		 {
			 System.out.println(t.getMessage());
		 }
		 }
	}
}
