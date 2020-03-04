package automation_suit.tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.BorderFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import automation_suit.pages.baseTest;
import automation_suit.pages.reusableMethods;

public class AutomationTest {
	
	/*
	 * Global Declaration
	 */
	static ExtentHtmlReporter Reporter;
	static ExtentReports extent;
	ExtentTest logger;
	public static WebDriver driver;
	public static String Username = "somuk1";
	public static String AccessKey = "4w1qxDXmntR4yfoeoCwy";
	public static String URL = "https://"+Username+":"+AccessKey+"@hub.browserstack.com/wd/hub";
	
	/*
	 *   TEST CASES FOR EXECUTION 
	 */
	
	@Test (dataProvider = "getTestData", dataProviderClass = automation_suit.pages.reusableMethods.class)
	public void TC01_To_check_if_Home_page_Help_page_SignIn_page_About_page_Contact_Page_and_Sign_Up_pages_are_loaded_after_clicking_on_the_respective_Links(HashMap<String,String> currentRow) throws MalformedURLException, InterruptedException 
	{			
		Browserdetails(currentRow.get("browser"),currentRow.get("version"));
		driver.get(currentRow.get("url"));
		
		 // As there can be sync issues in the webpage, default wait time of 3 second is given
		 // Implicit wait can also be used.
		 
		Thread.sleep(3000);
		reports();
		logger = extent.createTest("TC01_To_check_if_Home_page_Help_page_SignIn_page_About_page_Contact_Page_and_Sign_Up_pages_are_loaded_after_clicking_on_the_respective_Links", "");
		logger.log(Status.INFO, "URL is Launched");
		baseTest testfact = PageFactory.initElements(driver, baseTest.class);	
		logger.log(Status.INFO, "Test Execution is Started");
		
		//Validation to check if Home link is present and it navigates to Home page after clicking
		testfact.HomeLink.click();
		if (testfact.sPrice.isDisplayed()) 
		{
			logger.log(Status.PASS, "Home Page is loaded");}
		  else{
			logger.log(Status.FAIL, "Home Page is not loaded");
		}	
		
		//Validation to check if Help link is present and it navigates to Help page after clicking
		testfact.HelpLink.click();
		if (testfact.sHelppage.isDisplayed()) 
		{
			logger.log(Status.PASS, "Help Page is loaded");}
		  else{
			logger.log(Status.FAIL, "Help Page is not loaded");
		}			
		
		//Validation to check if SignIn link is present and it navigates to SignIn page after clicking
		testfact.SignInLink.click();
		if (testfact.sSessionemail.isDisplayed()) 
		{
			logger.log(Status.PASS, "SignIn Page is loaded");}
		  else{
			logger.log(Status.FAIL, "SignIn is not loaded");
		}	
		
		//Validation to check if Contact link is present and it navigates to Contact page after clicking
		testfact.ContactLink.click();
		if (testfact.sContactPage.isDisplayed()) {
			logger.log(Status.PASS, "About Page is loaded");}
		 else{
			logger.log(Status.FAIL, "About is not loaded");
		}
		
		// Validation to check if About link is present and it navigates to About page after clicking
		testfact.AboutLink.click();
		if (testfact.sAboutPage.isDisplayed()) {
			logger.log(Status.PASS, "Contact Page is loaded");}
		  else{
			logger.log(Status.FAIL, "Contact is not loaded");
		}	
		
		// Validation to check if SignUp link is present and it navigates to SignUp page after clicking
		testfact.HomeLink.click();
		testfact.SignUpHome.click();
		if (testfact.sUseremail.isDisplayed()) {
			logger.log(Status.PASS, "SignUp Page is loaded");}
		  else{
			logger.log(Status.FAIL, "SignUp is not loaded");
		}		
	
		logger.log(Status.INFO, "Test Execution is completed");
	
		/*
		 * Driver to close and extent reports to add all results in testReport.html which is present under test-output folder 
		 */
		driver.quit();
		extent.flush();
	}
	
	
	@Test (dataProvider = "getTestData", dataProviderClass = automation_suit.pages.reusableMethods.class)
	public void TC02_To_check_if_Sign_up_process_is_completed_successfully_after_giving_postive_values_and_navigates_to_sign_in_page_and_sign_In_is_also_done_successfully(HashMap<String,String> currentRow) throws MalformedURLException, InterruptedException {
		
		Browserdetails(currentRow.get("browser"),currentRow.get("version"));
		driver.get(currentRow.get("url"));
		
		 // As there can be sync issues in the webpage, default wait time of 3 second is given
		 // Implicit wait can also be used.
		 
		Thread.sleep(3000);
		reports();
		logger = extent.createTest("TC02_To_check_if_Sign_up_process_is_completed_successfully_after_giving_postive_values_and_navigates_to_sign_in_page_and_sign_In_is_also_done_successfully", "");
		logger.log(Status.INFO, "URL is Launched");
		baseTest testfact = PageFactory.initElements(driver, baseTest.class);	
		logger.log(Status.INFO, "Test Execution is Started");
		
		
		// Values to be given for registration
		testfact.SignUpHome.click();Thread.sleep(2000);
		testfact.sUsername.sendKeys(currentRow.get("sUsername"));
		testfact.sMailId.sendKeys(currentRow.get("sMailId"));
		testfact.sPassword.sendKeys(currentRow.get("sPassword"));
		testfact.sConfPassword.sendKeys(currentRow.get("sConfPassword"));
		testfact.SignUpbtn.click();
		
		if (testfact.sSuccesspage.isDisplayed()) {
			logger.log(Status.PASS, "SignUp is Completed Successfully");}
		  else{
			logger.log(Status.FAIL, "SignUp process is not completed");
		}	
				
		driver.quit();
		extent.flush();
	}
	
    
/*
 * Method to invoke the browser with version details into browser stack and passing the URL
 * DesiredCapabilities class is used here.
 * Parameter - browser is passed while calling the method
 * Parameter - version is passed while calling the method
 * Parameter - URL is browserstack URL which is appended with userlogin details
 */
	public static void Browserdetails (String browser,String version) throws MalformedURLException 
		{
			DesiredCapabilities cap = new DesiredCapabilities();  
			cap.setPlatform(Platform.MAC);
			cap.setBrowserName(browser);
			cap.setVersion(version);	
			URL brsURL = new URL(URL);
			driver = new RemoteWebDriver (brsURL,cap);	
		}
	
	
/*
 *  EXTENT Report is third party report method which is integrated with TESTNG Framework
 *  
 */
	public static void reports() {
		
		Reporter  = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/testReport.html");
	    extent = new ExtentReports();
	    extent.attachReporter(Reporter);
	}
	
}
