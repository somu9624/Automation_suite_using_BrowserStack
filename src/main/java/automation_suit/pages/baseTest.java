package automation_suit.pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class baseTest {
	
	/*
	 * Gobal Declaration
	 */
	WebDriver driver;
	
	/*
	 * Constructor for baseTest class which has driver being called from base class
	 */
	public baseTest(WebDriver driver) {		
		this.driver = driver;
	}
	
	/*
	 * Page Object Model with Pagefactory is implemented  for finding Xpath and storing it in variables. 
	 * This is done to reduce the rework if the co-ordinates are changed by the developer
	 * Here in this class xpaths are stored in one single class but as per POM framework - we need to have one class for each page
	 */
	
	@FindBy 
	(how=How.XPATH, using ="//*[text()='Home']")
	public WebElement HomeLink;

	@FindBy 
	(how=How.XPATH, using ="//*[contains(text(),'price')]")
	public WebElement sPrice;

	@FindBy 
	(how=How.XPATH, using ="//*[text()='Help']")
	public WebElement HelpLink;
	
	@FindBy 
	(how=How.XPATH, using ="//*[@class='container']/section/h1")
	public WebElement sHelppage;
	
	@FindBy 
	(how=How.XPATH, using ="//input[@id='session_email']")
	public WebElement sSessionemail;
	
	@FindBy 
	(how=How.XPATH, using ="//*[text()='Sign in']")
	public WebElement SignInLink;
	
	@FindBy 
	(how=How.XPATH, using ="//*[text()='About']")
	public WebElement AboutLink;
	
	@FindBy 
	(how=How.XPATH, using ="//h1[contains(text(),'About Page')]")
	public WebElement sAboutPage;
	
	@FindBy 
	(how=How.XPATH, using ="//h1[contains(text(),'Contact Page')]")
	public WebElement sContactPage;
	
	@FindBy 
	(how=How.XPATH, using ="//*[text()='Contact']")
	public WebElement ContactLink;
	
	@FindBy 
	(how=How.XPATH, using ="//*[@class='signup_button round']")
	public WebElement SignUpHome;
	
	@FindBy 
	(how=How.XPATH, using ="//*[@id='user_email']")
	public WebElement sUseremail;
	
	@FindBy 
	(how=How.XPATH, using ="//*[@id='user_name']")
	public WebElement sUsername;
	
	@FindBy 
	(how=How.XPATH, using ="//*[@id='user_email']")
	public WebElement sMailId;
	
	@FindBy 
	(how=How.XPATH, using ="//*[@id='user_password']")
	public WebElement sPassword;
	
	@FindBy 
	(how=How.XPATH, using ="/*[@id='user_password_confirmation']")
	public WebElement sConfPassword;
	
	@FindBy 
	(how=How.XPATH, using ="//input[@type='submit']")
	public WebElement SignUpbtn;
	
	@FindBy 
	(how=How.XPATH, using ="//div[contains(text(),'Welcome to Rails sample app')]")
	public WebElement sSuccesspage;
	
}



