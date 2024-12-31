package stepDefinition;

import org.openqa.selenium.WebDriver;

import commonFunctions.FunctionLibrary;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {
	WebDriver driver;
	@When("Open Browser")
	public void open_browser() throws Throwable {
		
		driver=FunctionLibrary.startBrowser();
	    
	}
	@When("Open Application url {string}")
	public void open_application_url(String url) throws Throwable {
	    FunctionLibrary.openUrl(driver, url);
	}
	@When("Wait For Username with {string} and {string}")
	public void wait_for_username_with_and(String LocatorType, String LocatorValue) {
	    FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, "10");
	}
	@When("Enter Username with {string} and {string}")
	public void enter_username_with_and(String LocatorType, String LocatorValue) {
	    FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, "admin");
	}
	@When("Enter Password with {string} and {string}")
	public void enter_password_with_and(String LocatorType, String LocatorValue) {
	    FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, "master");
	}
	@When("Click On Login with {string} and {string}")
	public void click_on_login_with_and(String LocatorType, String LocatorValue) {
	    FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
	}
		
	@When("^Wait For Supplier with \"([^\"]*)\" and\"([^\"]*)\"$")
	public void wait_For_Supplier_with_and(String LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, "10");
	}

	@When("^Click On Supplier with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_On_Supplier_with_and(String LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
	}
	
	@When("^Wait For AddButton with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void wait_For_AddButton_with_and(String LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, "10");
	}

	@When("^Click On AddButton with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_On_AddButton_with_and(String LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
	}

	@When("^Wait For SupplierNumber with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void wait_For_SupplierNumber_with_and(String LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, "10");
	}

	@Then("^Capture Data with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void capture_Data_with_and(String LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.captureData(driver, LocatorType, LocatorValue);
	}

	@When("^Enter in \"([^\"]*)\" with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void enter_in_with_and(String TestData,String  LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.typeAction(driver, LocatorType, LocatorValue, TestData);
	}

	@When("^Click On Add Button after adding notes with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_On_Add_Button_after_adding_notes_with_and(String LocatorType, String LocatorValue) throws Throwable {
	   FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
	}

	@When("^Wait For Ok Button with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void wait_For_Ok_Button_with_and(String LocatorType, String LocatorValue) throws Throwable {
	    FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, "10");
	}

	@When("^Click On Ok Button with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_On_Ok_Button_with_and(String LocatorType, String LocatorValue) throws Throwable {
		FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
	}

	@When("^Wait For Alert with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void wait_For_Alert_with_and(String LocatorType, String LocatorValue) throws Throwable {
		FunctionLibrary.waitForElement(driver, LocatorType, LocatorValue, "10");
	}

	@When("^Click On Alert with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void click_On_Alert_with_and(String LocatorType, String LocatorValue) throws Throwable {
		FunctionLibrary.clickAction(driver, LocatorType, LocatorValue);
	}

	@Then("^user validate the supplier table$")
	public void user_validate_the_supplier_table() throws Throwable {
	    FunctionLibrary.supplierTable(driver);
	}

	@When("^user closes the browser$")
	public void user_closes_the_browser() throws Throwable {
	    FunctionLibrary.closeBrowser(driver);
	}
	
	//Customer Module
	@When("wait for customer with {string} and {string}")
	public void wait_for_customer_with_and(String LocatorType, String LocatorValue) {
		FunctionLibrary.waitForElement(driver, LocatorType,  LocatorValue,"10");
	}
	@When("Click on customer with {string} and {string}")
	public void click_on_customer_with_and(String LocatorType, String LocatorValue) {
	   FunctionLibrary.clickAction(driver, LocatorType,  LocatorValue);
	}
}
