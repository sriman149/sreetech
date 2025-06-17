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
		String browser = prop.getProperty("browser");
        String headless = prop.getProperty("headless", "false");

        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\drivers\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--start-maximized");

            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\drivers\\msedgedriver.exe");

            EdgeOptions options = new EdgeOptions();
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
            }

            driver = new EdgeDriver(options);
        }
	}

	@Test
	public static void openUrl()
	{
		
		
		driver.get(prop.getProperty("url"));
		
	}
}
