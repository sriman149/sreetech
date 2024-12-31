package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features= {"FeatureFiles/Supplier.feature"},
glue= {"stepDefinition"},
plugin= {"pretty","html:target/SupplierReports/supplier.html"}
	
		)

public class AppTest extends AbstractTestNGCucumberTests {
	
	/*
	 * @Test public void kickstart() throws FileNotFoundException, IOException {
	 * AppUtil.launchBrowser(); }
	 */

}

