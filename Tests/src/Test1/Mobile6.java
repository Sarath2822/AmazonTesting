package Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.usuage.com.Locators;
import com.usuage.com.ReportExtent;
import com.usuage.com.Screenshots;
import com.usuage.com.Utility;

public class Mobile6 extends ReportExtent{

	public static	WebDriver driver;
	public static	WebDriverWait wait;
	public static	Properties pro;
	
//	String className = this.getClass().getName();
		
		public Mobile6() throws IOException{
//		Data = new Properties();
		try {
			pro = new Properties();
			FileInputStream fis = new FileInputStream("C:\\eclipse-workspace\\Tests\\src\\com\\usuage\\com\\Data.properties");
			pro.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
		
		@Test(priority=0)
		public void browserLaunch() {
			
			logger = extent.createTest("browserLaunch");
			
			Map<String, String> mobileEmulation = new HashMap<>();

			mobileEmulation.put("deviceName", "iPhone 6/7/8");
			
			System.setProperty("webdriver.chrome.driver", Locators.DRIVERPATH);
			
			ChromeOptions  options = new ChromeOptions();
			options.setExperimentalOption("mobileEmulation", mobileEmulation);
			driver = new ChromeDriver(options);
			
			Utility.logInfo(logger, "Broswer launched successsfully");
			
//			logger.log(Status.INFO, "Broswer launched successsfully with Mod IP");

			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			
			Utility.get(driver, pro.getProperty("APPURL2"));
			
			System.out.println("Application launched");
			
			Utility.logPass(logger, "Application launced successfully");
		}
		
		@Test(priority=1)
		public void login() throws IOException, InterruptedException {
			
			logger = extent.createTest("Login");
			
			wait = new WebDriverWait(driver, 20);
				
			FileUtils.deleteDirectory(new File("C:\\eclipse-workspace\\Tests\\Screenshot"));
				
			Utility.logInfo(logger, "Screenshot Folder deleted");
				
			Utility.logPass(logger, "SS folder updated");
				
			Utility.visibilityOfEleLocatedBy(wait, Locators.LOGINHOME).click();
				
			Utility.logPass(logger, "Login clicked");
				
			Utility.xpath(driver, Locators.USERNAME).sendKeys(pro.getProperty("USERNAME1"));
				
			Utility.logPass(logger, "Entered Username");
				
			Utility.xpath(driver, Locators.PASSWORD).sendKeys(pro.getProperty("PASS"));
				
			Utility.logPass(logger, "Entered Password");
			
			Utility.xpath(driver, Locators.LOGIN).click();
				
			Utility.logPass(logger, "Login clicked");
			
		}
		
		@Test(priority=2)
		public void lhn() throws IOException {
			
			logger = extent.createTest("LHN checks");
			
			wait = new WebDriverWait(driver, 20);
			
			if(Utility.xpath(driver, Locators.AVATAR).isDisplayed()==true) {
				
				Utility.logPass(logger, "Sucessfully logged in");
				
			}else {
				Utility.logFail(logger, "Login Failed");	
			}
			
			String SS1 = Screenshots.captureScreenshot(driver, "Home1");
			
			Utility.passLog(logger, "Home page", SS1);
			
			Utility.xpath(driver, Locators.AVATAR).click();
			
			WebElement E1 = Utility.visibilityOfEleLocatedBy(wait, Locators.MAINHEADERMENU);
			if(E1.isDisplayed()==true) {
				
				Utility.logPass(logger, "Page landed Main menu header");				
			}else {
				
				Utility.logPass(logger, "Page is not landed on Main menu header");
			}
				
			//-----------Clicking on Account Details
			Utility.xpath(driver, Locators.ACCOUNT).click();
			
			Utility.logPass(logger, "Click on Account");
			
			WebElement E2 = Utility.visibilityOfEleLocatedBy(wait, Locators.ACCOUNTSETTINGS);
			
			if(E2.isDisplayed()==true) {
				
				E2.click();
				
				Utility.logPass(logger, "Click on Account Details in Account menu page");
				
				Boolean E3 = Utility.textToBe(wait, Locators.ACCOUNTDETAILS, "Account Details");
				
				logger.log(Status.PASS, E3.toString());
				
				String SS2 = Screenshots.captureScreenshot(driver,"Account Details page");
				
				Utility.passLog(logger, "Account Details page", SS2);
				
			}else {
				Utility.logPass(logger, "Account Details page is not landed");
			}
			
			//------------Back arrow in account details page--------------
			Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
			
			Utility.logPass(logger, "Click on Back arrow in account details page");
			
			
			//--------------------Transactions
			WebElement E4 = Utility.visibilityOfEleLocatedBy(wait, Locators.TRANSACTION);
			
			if(E4.isDisplayed() == true) {
				E4.click();
				
				Utility.logPass(logger, "Clicked on Transaction in Account menu");
				
				Transaction();
				
				Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
				
				Utility.logPass(logger, "Click on Back arrow in account details page");
			}else {
				Utility.logPass(logger, "Account menu page is not landed");
			}
			
				
			//--------------------Settings
			WebElement E9 = Utility.visibilityOfEleLocatedBy(wait, Locators.SETTINGSV6);
				
			if(E9.isDisplayed() == true) {
					E9.click();
					
					Utility.logPass(logger, "Clicked on Settings in Account menu");
					
					Settings();
					
					Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
					
					Utility.logPass(logger, "Click on Back arrow in account details page");
			}
		else {
			Utility.logPass(logger, "Account menu page is not landed");
		}
	}
		
		
		public void Transaction() throws IOException {
			
			WebElement E5 = Utility.visibilityOfEleLocatedBy(wait, Locators.GAMINGTRANS);
			
			if(E5.isDisplayed()==true) {
				
				E5.click();
				
				Utility.logPass(logger, "Click on Gaming Transaction in Transactions page");
				
				Boolean E6 = Utility.textToBe(wait, Locators.GAMINGTRANSACTIONS, "Gaming Transactions");
				
				Utility.logPass(logger, E6.toString());
				
				String SS3 = Screenshots.captureScreenshot(driver,"Gaming Transactions");
				
				Utility.passLog(logger, "Gaming Transactions", SS3);
				
			}else {
				Utility.logPass(logger, "Gaming Transactions page is not landed");
			}
			//-------Back arrow in Gaming Transaction page
			Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
			
			Utility.logPass(logger, "Click on Back arrow in Gaming Transactions page");
			
			//---------------Tournament Dollars---------
			WebElement E7 = Utility.visibilityOfEleLocatedBy(wait, Locators.TOURNAMENT);
			
			if(E7.isDisplayed()==true) {
				
				E7.click();
				
				Utility.logPass(logger, "Click on Tournamnet dollars in Transactions page");
				
				Boolean E8 = Utility.textToBe(wait, Locators.TOURNAMENTDOLLARS, "Tournament Dollars");
				
				Utility.logPass(logger, E8.toString());
				
				String SS4 = Screenshots.captureScreenshot(driver,"Tournament Dollars");
				
				Utility.passLog(logger, "Tournament Dollars'", SS4);
			}else {
				Utility.logPass(logger, "Tournament Dollars page is not landed");
			}
				
			//-------Back arrow in Gaming Transaction page
				Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
				
				Utility.logPass(logger, "Click on Back arrow in Gaming Transactions page");
			}
		
		public void Settings() throws IOException {
			//-----------Change Password page
			WebElement E10 = Utility.visibilityOfEleLocatedBy(wait, Locators.CHANGEPASS);
			
			if(E10.isDisplayed()==true) {
				
				E10.click();
				
				Utility.logPass(logger, "Click on Change Password in Settings page");
				
				Boolean E11 = Utility.textToBe(wait, Locators.CHANGEPASSWORD, "Change Password");
				
				Utility.logPass(logger, E11.toString());
				
				String SS5 = Screenshots.captureScreenshot(driver,"Change Password");
				
				Utility.passLog(logger, "Change Password", SS5);
				
			}else {
				Utility.logPass(logger, "Change Password page is not landed");
			}
			//-------Back arrow in change password page
			Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
			
			Utility.logPass(logger, "Click on Back arrow in Change Password page");
			
			//----------Communication preferences page
			WebElement E12 = Utility.visibilityOfEleLocatedBy(wait, Locators.COMMUNICATION);
			
			if(E12.isDisplayed()==true) {
				
				E12.click();
				
				Utility.logPass(logger, "Click on Communicaiton Preferences in Settings page");
				
				Boolean E13 = Utility.textToBe(wait, Locators.COMMUICATIONHEADER, "Communication Preferences");
				
				Utility.logPass(logger, E13.toString());
				
				String SS6 = Screenshots.captureScreenshot(driver,"Communication Preferences");
				
				Utility.passLog(logger, "Communication Preferences", SS6);
				
			}else {
				Utility.logPass(logger, "Communication Preferences page is not landed");
			}
			//-------Back arrow in Communication Preference page
			Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
			
			Utility.logPass(logger, "Click on Back arrow in Communication Preferences page");
			
			//--------Language & Time Zone page
			WebElement E14 = Utility.visibilityOfEleLocatedBy(wait, Locators.LANGUAGE);
			
			if(E14.isDisplayed()==true) {
				
				E14.click();
				
				Utility.logPass(logger, "Click on Language & Time Zone in Settings page");
				
				Boolean E15 = Utility.textToBe(wait, Locators.LANGUAGEHEADER, "Language & Time Zone");
				
				Utility.logPass(logger, E15.toString());
				
				String SS7 = Screenshots.captureScreenshot(driver,"Language & Time Zone");
				
				Utility.passLog(logger, "Language & Time Zone", SS7);
				
			}else {
				Utility.logPass(logger, "Language & Time Zone page is not landed");
			}
			//-------Back arrow in Language & Time Zone page
			Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
			
			Utility.logPass(logger, "Click on Back arrow in Language & Time Zone page");
			
			//--------Betting Settings page
			WebElement E16 = Utility.visibilityOfEleLocatedBy(wait, Locators.BETTING);
			
			if(E16.isDisplayed()==true) {
				
				E16.click();
				
				Utility.logPass(logger, "Click on Betting Settings in Settings page");
				
				Boolean E17 = Utility.textToBe(wait, Locators.BETTINGHEADER, "Betting Settings");
				
				Utility.logPass(logger, E17.toString());
				
				String SS8 = Screenshots.captureScreenshot(driver,"Betting Settings");
				
				Utility.passLog(logger, "Betting Settings", SS8);
				
			}else {
				Utility.logPass(logger, "Betting Settings page is not landed");
			}
			//-------Back arrow in Betting Settings page
			Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
			
			Utility.logPass(logger, "Click on Back arrow in Betting Settings page");
			
			//--------Betting Settings page
			WebElement E18 = Utility.visibilityOfEleLocatedBy(wait, Locators.BETTING);
			
			if(E18.isDisplayed()==true) {
				
				E18.click();
				
				Utility.logPass(logger, "Click on Betting Settings in Settings page");
				
				Boolean E19 = Utility.textToBe(wait, Locators.BETTINGHEADER, "Betting Settings");
				
				Utility.logPass(logger, E19.toString());
				
				String SS9 = Screenshots.captureScreenshot(driver,"Betting Settings");
				
				Utility.passLog(logger, "Betting Settings", SS9);
				
			}else {
				Utility.logPass(logger, "Betting Settings page is not landed");
			}
			//-------Back arrow in Betting Settings page
			Utility.xpath(driver, Locators.ACCOUNTDETAILSCLOSE).click();
			
			Utility.logPass(logger, "Click on Back arrow in Betting Settings page");
		}
	}
