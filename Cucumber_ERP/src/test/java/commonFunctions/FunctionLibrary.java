package commonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FunctionLibrary {
	public static WebDriver driver;
	
	//method for launching browser
	public static WebDriver startBrowser()throws Throwable
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	//method for launching url
	public static void openUrl(WebDriver driver,String url) throws Throwable
	{
		driver.get(url);
	}
	//method wait to element
	public static void waitForElement(WebDriver driver,String Locator_Type,String Locator_Value,String wait)
	{
		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
		}
		else if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));
		}
		else if(Locator_Type.equalsIgnoreCase("id"))
		{
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
			
		}
		      
	}
	
	//method textboxes
	public static void typeAction(WebDriver driver,String Locator_Type,String Locator_Value,String TestData)
	{
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locator_Value)).clear();
			driver.findElement(By.name(Locator_Value)).sendKeys(TestData);
		}
		else if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locator_Value)).clear();
			driver.findElement(By.xpath(Locator_Value)).sendKeys(TestData);
		}
		else if(Locator_Type.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locator_Value)).clear();
			driver.findElement(By.id(Locator_Value)).sendKeys(TestData);
		}
	}
	//method for buttons,links,radio buttons,checkboxes and imgaes
	public static void clickAction(WebDriver driver,String Locator_Type,String Locator_Value)
	{
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locator_Value)).click();
		}
		else if(Locator_Type.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locator_Value)).click();
		}
		else if(Locator_Type.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locator_Value)).sendKeys(Keys.ENTER);
		}
	}
	//method for title validation
	public static void validateTitle(WebDriver driver,String Expected_Title)
	{
		String Actual_Title =driver.getTitle();
		try {
		Assert.assertEquals(Actual_Title, Expected_Title,"Title Not Matching");
		}catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	//method for mouse click
	public static void mouseClick(WebDriver driver) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("//a[starts-with(text(),'Stock Items ')]"))).perform();
		Thread.sleep(2000);
		ac.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Stock Categories')])[2]"))).click().perform();
	}
	
	//method for listboxes
	public static void dropDownAction(WebDriver driver, String Locator_Type, String Locator_Value, String TestData) throws Exception
	{
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			int value = Integer.parseInt(TestData);
			WebElement element = driver.findElement(By.xpath(Locator_Value));
			Select select = new Select(element);
			select.selectByIndex(value);
			
		}
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			int value = Integer.parseInt(TestData);
			WebElement element = driver.findElement(By.id(Locator_Value));
			Select select = new Select(element);
			select.selectByIndex(value);
			
		}
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			int value =Integer.parseInt(TestData);
			WebElement element = driver .findElement(By.name(Locator_Value));
			Select select = new Select(element);
			select.selectByIndex(value);
		}
		
	}
	//capture data
	public static void captureData(WebDriver driver,String Locator_Type,String Locator_value)throws Throwable
	{
		String data ="";
		if(Locator_Type.equalsIgnoreCase("id"))
		{
			data =driver.findElement(By.id(Locator_value)).getAttribute("value");
		}
		else if(Locator_Type.equalsIgnoreCase("name"))
		{
			data =driver.findElement(By.name(Locator_value)).getAttribute("value");
		}
		else if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			data =driver.findElement(By.xpath(Locator_value)).getAttribute("value");
		}
		//write into notepad
		FileWriter fw = new FileWriter("./CaptureData/capturefile.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.flush();
		bw.close();
	}
	
	//method for supplier table
	public static void supplierTable(WebDriver driver)throws Throwable
	{
		//read from note pad
			FileReader fr = new FileReader("./CaptureData/capturefile.txt");
			BufferedReader br = new BufferedReader(fr);
			String Exp_data =br.readLine();
			//if search textbox is already displyed dont click search panel button
				if(!driver.findElement(By.xpath("//input[@id='psearch']")).isDisplayed())
					//if not textbox displayed click search panel button
					driver.findElement(By.xpath("//span[@data-caption='Search']")).click();
				driver.findElement(By.xpath("//input[@id='psearch']")).sendKeys(Exp_data);
				driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
				Thread.sleep(5000);
				String Act_data= driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
				System.out.println(Exp_data+"     "+Act_data);
				try {
				Assert.assertEquals(Exp_data, Act_data,"Supplier number not matching");
				}catch(Throwable t)
				{
					System.out.println(t.getMessage());
				}
	}
}
