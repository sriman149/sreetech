package testRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppUtil {
	public static WebDriver driver;
	public static Properties prop;
	
static String file = "C:\\Users\\Sreeman\\eclipse-workspace\\Cucumber_ERP\\src\\test\\java\\config\\config.properties";

	@BeforeTest
	public static WebDriver launchBrowser() throws FileNotFoundException, IOException {
		prop = new Properties();
		prop.load(new FileInputStream(file));
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else {
			if (prop.getProperty("browser").equalsIgnoreCase("edge")) {
				driver = new EdgeDriver();
			}
		}
		
		return driver;
	}

	@Test
	public static void openUrl()
	{
		
		
		driver.get(prop.getProperty("url"));
		
	}
}
