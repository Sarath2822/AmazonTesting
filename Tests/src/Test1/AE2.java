package Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.usuage.com.Locators;
import com.usuage.com.Locators1;
import com.usuage.com.ReportExtent;
import com.usuage.com.Screenshots;
import com.usuage.com.Utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AE2 extends ReportExtent{

//	 public static WebDriver driver;
	 public static	WebDriverWait wait;
	 public static	Properties pro;
	 public static AppiumDriver<MobileElement> driver;
	 
		
		public AE2() throws IOException{
		try {
			pro = new Properties();
			FileInputStream fis = new FileInputStream("C:\\eclipse-workspace\\Tests\\src\\com\\usuage\\com\\Data.properties");
			pro.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
		
		@BeforeTest
		public void setupANDevice() { 
			logger = extent.createTest("Setup and Login");
			
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "ce0117110c490c3204");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "8.0.0");
		caps.setCapability("appPackage", "com.sportingbet.sportbook_beta");
		caps.setCapability("appActivity", "com.bwinlabs.wrapper_sportingbetcom.ui.activity.HomeActivitySWSBCOM");
		caps.setCapability("noReset", true);
		
//		AppiumDriver<MobileElement> driver = null;
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test(priority=0)
	public void Login() {
			
		logger = extent.createTest("Login");
			
		wait = new WebDriverWait(driver, 20);
		
		Utility.visibilityOfEleLocatedBy(wait, Locators1.HOMEPAGELOGIN).click();
		
		logger.log(Status.PASS, "Login clicked");
		
		MobileElement E1 = (MobileElement) Utility.id(driver, Locators1.USERNAME);
		E1.click();
		E1.clear();
		
		E1.setValue("sbcm6@yopmail.com");
		
		Utility.logPass(logger, "Entered Username");
		
		MobileElement E2 = (MobileElement) Utility.id(driver, Locators1.PASSWORD);
		E2.click();
		E2.clear();
		
		E2.setValue("qwe123");
		
		Utility.logPass(logger, "Entered Password");
		
		Utility.xpath(driver, Locators1.LOGIN).click();
		
		Utility.logPass(logger, "Login clicked");

		}
		
		@Test(priority=1)
		public void lhn() throws IOException, InterruptedException {
			
			logger = extent.createTest("LHN checks");
			
			wait = new WebDriverWait(driver, 20);
			
			FileUtils.delete(new File("C:\\eclipse-workspace\\Tests\\Screenshot"));
			
			Utility.logPass(logger, "Screenshot Folder deleted");
				
			Utility.logPass(logger, "SS folder updated");
				
			Utility.visibilityOfEleLocatedBy(wait, Locators1.DLHEADER);
				
			Utility.xpath(driver, Locators1.DEPOSITLIMITSCONFIR).click();
			
			Utility.logPass(logger, "Clicked on Remind me later");
			
			Utility.visibilityOfEleLocatedBy(wait, Locators1.MYBETSHOME);
				
			if(Utility.xpath(driver, Locators1.MYBETSHOME).isDisplayed()==true){
					
				Utility.logPass(logger, "Sucessfully logged in");
					
			}else {
				Utility.logFail(logger, "Login Failed");				
			}
			
			String SS1 = Screenshots.captureScreenshot(driver, "Home1");
				
			Utility.passLog(logger, "Home page", SS1);

			WebElement E1 = Utility.elementToBeClickable(wait, Locators1.HEADERBAL);
			
			if(E1.isDisplayed()==true) {
					
				Utility.logInfo(logger, E1.getText());
					
				Utility.xpath(driver, Locators1.HEADERBAL).click();
					
				Utility.logPass(logger, "Click on header balance");
					
			}else {
					
				Utility.logFail(logger, "Header balance text is missing in home page");
			}

			WebElement E2 = Utility.visibilityOfEleLocatedBy(wait, Locators1.MYBAL);
				
				Assert.assertEquals(E2.getText(), "My Balance");
				
				String SS2 = Screenshots.captureScreenshot(driver, "Header Balance");
				
				Utility.passLog(logger, "Header Balance", SS2);
				
				Utility.xpath(driver, Locators1.DEPOSIT).click();
				
				Utility.logPass(logger, "Click on Deposit");
				
				WebElement E3 = Utility.xpath(driver, Locators1.DEPOSITTEXT);
				
				if(E3.isDisplayed()==true) {
				driver.navigate().back();
				
				Utility.xpath(driver, Locators1.DEPOSITCLOSE).click();
				
				Utility.logPass(logger, "Click on browser back");
				}else {
					Utility.logFail(logger, "Not landed on Deposit page");
				}
				
				Utility.xpath(driver, Locators1.LOGIN).click();
				
				Utility.logPass(logger, "Click on Logo");
				
				home();
				
	//-----------Clicking on promotions 
//				driver.findElement(By.xpath(Locators.PROMOTIONS)).click();
				
				Utility.xpath(driver, Locators.PROMOTIONS).click();
				
				logger.log(Status.PASS, "Click on Promotions in LHN");
				
//				WebElement L3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.TOPPROMO)));
				WebElement L3 = Utility.visibilityOfEleLocatedBy(wait, Locators.TOPPROMO);
				
				if(L3.isDisplayed()==true) {
					
					logger.log(Status.PASS, L3.getText());
					
					String lhn3 = Screenshots.captureScreenshot(driver,"Promotions page");
					
					logger.pass("Promotions page", MediaEntityBuilder.createScreenCaptureFromPath(lhn3).build());
					
				}else {
				
					logger.log(Status.FAIL, "Promotions page is not landed");
				}
	//-----------'X' mark in Promotions page
//				driver.findElement(By.xpath(Locators.PROMOCLOSE)).click();
				Utility.xpath(driver, Locators.PROMOCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' mark in Promotions page");
				
				home();
				
	//-----------Clicking on My Bonuses
//				driver.findElement(By.xpath(Locators.MYBONUSES)).click();
				Utility.xpath(driver, Locators.MYBONUSESLHN).click();
				
				logger.log(Status.PASS, "Click on My Bonuses in LHN");
				
//				WebElement L4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MYBONUSES)));
				WebElement L4 = Utility.visibilityOfEleLocatedBy(wait, Locators.MYBONUSES);
				
				if(L4.isDisplayed()==true) {
					
					logger.log(Status.PASS, L4.getText());
					
					String lhn4 = Screenshots.captureScreenshot(driver,"My Bonuses page");
					
					logger.pass("My Bonuses page", MediaEntityBuilder.createScreenCaptureFromPath(lhn4).build());
					
				}else {
					
					logger.log(Status.FAIL, "Bonuses page is not landed");
				}
	//-----------'X' mark in bonuses page
//				driver.findElement(By.xpath(Locators.MYBONUSCLOSE)).click();
				Utility.xpath(driver, Locators.MYBONUSCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' mark in bonuses page");
				
				home();
				
	//-----------Clicking on bonus history
//				driver.findElement(By.xpath(Locators.BONUSHISTORY)).click();
				Utility.xpath(driver, Locators.BONUSHISTORY).click();
//				WebElement BH = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Bonus History')]")));
				
//				BH.click();
				
				logger.log(Status.PASS, "Click on Bonus History");
				
//				WebElement L5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.BHTEXT)));
				WebElement L5 = Utility.visibilityOfEleLocatedBy(wait, Locators.BHTEXT);
				
				if(L5.isDisplayed()==true) {
					
					logger.log(Status.PASS, L5.getText());
					
					String lhn5 = Screenshots.captureScreenshot(driver,"BonusHistory page");
					
					logger.pass("BonusHistory page", MediaEntityBuilder.createScreenCaptureFromPath(lhn5).build());
					
				}else {
					logger.log(Status.PASS, "BonusHistory is not landed");
				}
	//-----------'X' mark in bonus history page
//				driver.findElement(By.xpath(Locators.BHCLOSE)).click();
				Utility.xpath(driver, Locators.BHCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' mark in bonus history page");
				
				home();
	//-----------Clicking on My bets
//				driver.findElement(By.xpath(Locators.MYBETS1)).click();
				Utility.xpath(driver, Locators.MYBETSLHN).click();
				
				logger.log(Status.PASS, "Click on My bets in LHN");
				
//				WebElement L6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MYBETS1)));
				WebElement L6 = Utility.visibilityOfEleLocatedBy(wait, Locators.MYBETS);
				
				if(L6.isDisplayed()==true) {
					
					logger.log(Status.PASS, L6.getText());
					
					String lhn6 = Screenshots.captureScreenshot(driver,"My Bets");
					
					logger.pass("My Bets", MediaEntityBuilder.createScreenCaptureFromPath(lhn6).build());
					
				}else {
					logger.log(Status.FAIL, "My bets page is not landed");
				}
	//-----------'X' mark in My Bets page
//				driver.findElement(By.xpath(Locators.MYBETSCLOSE)).click();
				Utility.xpath(driver, Locators.MYBETSCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' mark in My Bets page");
				
//				WebElement l1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.HOMEPAGE)));
				WebElement l1 = Utility.visibilityOfEleLocatedBy(wait, Locators.HOMEPAGE);
				
				if(l1.isDisplayed()==true) {
					
					logger.log(Status.PASS, l1.getText());
					
					logger.log(Status.INFO, "Landed on home page");
					
				}else {
					
					logger.log(Status.FAIL, "Not landed on home page");
				}
				
				home();
	//-----------Clicking on My FreeBets
//				driver.findElement(By.xpath(Locators.MYFREEBETS)).click();
				Utility.xpath(driver, Locators.MYFREEBETS).click();
				
				logger.log(Status.PASS, "Click on My FreeBets in LHN");
				
//				WebElement L7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MYFREEBETS)));
//				WebElement L7 = Utility.visibilityOfEleLocatedBy(wait, Locators.MYFREEBETS);
				
//				if(L7.isDisplayed()==true) {
					
//					logger.log(Status.PASS, L7.getText());
					
//					WebElement FB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MFBTNC)));
					WebElement FB = Utility.visibilityOfEleLocatedBy(wait, Locators.MFBTNC);
					FB.click();
					logger.log(Status.PASS, "Clicked on TnC");
					
					String lhn7 = Screenshots.captureScreenshot(driver,"My FreeBets");
					
					logger.pass("MY FREEBETS", MediaEntityBuilder.createScreenCaptureFromPath(lhn7).build());
					
				/*}else {
					
					logger.log(Status.FAIL, "My FreeBets page is not landed");
				}*/
	//-----------'X' mark in My FreeBets page
//				driver.findElement(By.xpath(Locators.MYFREEBETSCLOSE)).click();
				Utility.xpath(driver, Locators.MYFREEBETSCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' mark in My FreeBets page");
				
				home();
				/*//Clicking on My FreeSpins
				driver.findElement(By.xpath("//span[contains(text(),'My Freespins')]")).click();
				
				WebElement L8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='myfreespins']/h1/span[1]")));
				
				if(L8.isDisplayed()==true) {
					
					System.out.println(L8.getText());
					
					Screenshots.captureScreenshot(driver,driver, "My Freespins");
				}else {
					System.out.println("My Freespins page is not landed");
				}
				//'X' mark in My Freespins page
				driver.findElement(By.xpath("//div[@id='myfreespins']/h1/span[2]")).click();
				
				home();
				*/
	//-----------Clicking on Betting preferences
//				driver.findElement(By.xpath(Locators.BS)).click();
				Utility.xpath(driver, Locators.BS).click();
				
				logger.log(Status.PASS, "Click on Betting preferences in LHN");
				
//				WebElement L9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.BS1)));	
				WebElement L9 = Utility.visibilityOfEleLocatedBy(wait, Locators.BS1);
				
				if(L9.isDisplayed()==true) {
					
					logger.log(Status.PASS, L9.getText());
					
					String lhn9 = Screenshots.captureScreenshot(driver,"Betting Preferences");
					
					logger.pass(L9.getText(), MediaEntityBuilder.createScreenCaptureFromPath(lhn9).build());
				}else {
					
					logger.log(Status.FAIL, "Betting Preferences page is not landed");
				}
	//-----------'X' Mark in betting preferences page
//				driver.findElement(By.xpath(Locators.BSCLOSE)).click();
				Utility.xpath(driver, Locators.BSCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' Mark in betting preferences page");
				
				home();
				
	//-----------Clicking on Contact 
//				driver.findElement(By.xpath(Locators.CONTACT)).click();
				Utility.xpath(driver, Locators.CONTACT).click();
				
				logger.log(Status.PASS, "Click on Contact in LHN");
				
//				WebElement L10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.CONTACT1)));
				WebElement L10 = Utility.visibilityOfEleLocatedBy(wait, Locators.CONTACT1);
				
				if(L10.isDisplayed()==true) {
					
					logger.log(Status.PASS, L10.getText());
					
					String lhn10 = Screenshots.captureScreenshot(driver,"Contact page");
					
					logger.pass("Contact page", MediaEntityBuilder.createScreenCaptureFromPath(lhn10).build());
				}else {
					
					logger.log(Status.FAIL, "Contact page is not landed");
				}
	//-----------Clicking on logo
//				driver.findElement(By.xpath("//a[@class='brand-logo']")).click();
				Utility.xpath(driver, Locators.LOGO).click();
				
				logger.log(Status.PASS, "Click on Logo");
				
				home();
				
	//-----------Clicking on personal details
//				driver.findElement(By.xpath("//span[contains(text(),'Personal Details')]")).click();
				Utility.xpath(driver, Locators.PERSONALDETAILS).click();
				
				logger.log(Status.PASS, "Click on personal details");
				
//				WebElement L11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Personal details')]")));
				WebElement L11 = Utility.visibilityOfEleLocatedBy(wait, Locators.PERSONALDETAILSTEXT);
				
				if(L11.isDisplayed()==true) {
					
					logger.log(Status.PASS, L11.getText());
					
					String lhn11 = Screenshots.captureScreenshot(driver,"Personal details page");
					
					logger.pass("Personal details page", MediaEntityBuilder.createScreenCaptureFromPath(lhn11).build());
				}else {
					logger.log(Status.FAIL, "Personal details page is not landed");
					
				}
				
	//-----------'X' mark in personal details page
//				driver.findElement(By.xpath("//div[@id='main-content']/pt-personal-details/div/lh-header-bar/h1/span")).click();
				Utility.xpath(driver, Locators.PDCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' mark in personal details page");
				
				home();
				
	//-----------Clicking on Transactions
//				driver.findElement(By.xpath("//span[contains(text(),'Transactions')]")).click();
				Utility.xpath(driver, Locators.TRANSACTIONS).click();
				
				logger.log(Status.PASS, "Click on Transactions in LHN");
				
//				WebElement L12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Transaction History')]")));
				WebElement L12 = Utility.visibilityOfEleLocatedBy(wait, Locators.TRANSACTIONSHISTORY);
				
				if(L12.isDisplayed()==true) {
					
					logger.log(Status.PASS, L12.getText());
					
					String lhn12 = Screenshots.captureScreenshot(driver,"TransactionHistory page");
					
					logger.pass("TransactionHistory page", MediaEntityBuilder.createScreenCaptureFromPath(lhn12).build());
				}else {
					logger.log(Status.FAIL, "Transaction History page is not landed");
				}
				
	//----------'X' mark in Transaction history page
//				driver.findElement(By.xpath("//div[@id='main-content']/pt-transactions/lh-header-bar/h1/span")).click();
				Utility.xpath(driver, Locators.TRANSACTIONCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' mark in Transaction history page");
				
				home();
				
	//-----------Clicking on Settings
//				WebElement S = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[6]/a/span")));
				WebElement S = Utility.visibilityOfEleLocatedBy(wait, Locators.SETTINGS);
				
				S.click();
				
				logger.log(Status.PASS, "Click on settings in LHN");
				
//				WebElement L13 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/lh-marquee/span")));
				WebElement L13 = Utility.visibilityOfEleLocatedBy(wait, Locators.SETTINGSPAGE);
				
				if(L13.isDisplayed()==true) {
					
					logger.log(Status.PASS, L13.getText());
					
					String lhn13 = Screenshots.captureScreenshot(driver,"Settings");
					
					logger.pass(L13.getText(), MediaEntityBuilder.createScreenCaptureFromPath(lhn13).build());
					
					settings();
					
				}else {
				
					logger.log(Status.FAIL, "Settings page is not landed");
				}
				
	//-----------'X' Mark in settings page
//				driver.findElement(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/span")).click();
				Utility.xpath(driver, Locators.SETTINGSCLOSE).click();
				
				logger.log(Status.PASS, "Click on 'X' Mark in settings page");
				
				home();
				
	//-----------Clicking on Cashier page
//				driver.findElement(By.xpath("//span[contains(text(),'Cashier')]")).click();
				Utility.xpath(driver, Locators.CASHIER).click();
				
				logger.log(Status.PASS, "Click on Cashier in LHN");
				
				/*WebElement L14 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Verification pending')]")));
				
				if(L14.isDisplayed()==true) {
					
					logger.log(Status.PASS, L14.getText());*/
					
					String lhn14 = Screenshots.captureScreenshot(driver,"Cashier");
					
					logger.pass("Cashier", MediaEntityBuilder.createScreenCaptureFromPath(lhn14).build());
				/*	
				}else {
					logger.log(Status.FAIL, "Cashier page is not landed");
				}
				*/
	//-----------'X' Mark in Cashier page
//				driver.findElement(By.xpath("html/body/header/a")).click();
				
				driver.navigate().back();
				
				logger.log(Status.PASS, "Click on 'X' Mark in Cashier page");
				
	//-----------Clicking on LHN menu
//				driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
				Utility.xpath(driver, Locators.LHN).click();
				
				logger.log(Status.INFO, "Click on LHN menu");
	//-----------Clicking on Logout
//				driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-logout-menu-item/a")).click();
				Utility.xpath(driver, Locators.LOGOUT).click();
				
				logger.log(Status.INFO, "Click on Logout");
				
				logger.log(Status.PASS, "Successfully Logged out!");
				
				driver.quit();
		}
		
		@AfterTest
	    public void testCaseTearDown()
	    {
	        driver.quit();
	    }
		public void home () {
			
			WebElement E = Utility.visibilityOfEleLocatedBy(wait, Locators1.MYBETSHOME);
			if(E.isDisplayed()==true) {
				
				logger.log(Status.PASS, "Page landed on home page");
			}else {
				logger.log(Status.FAIL, "Page is not landed on home page");
			}
			//Clicking on LHN menu
//			driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
			Utility.xpath(driver, Locators.LHN).click();
			
			logger.log(Status.INFO, "Click on LHN menu");
		}
		
		public void settings () throws IOException {
	//-------Click on Changing password
//			driver.findElement(By.xpath("//a[contains(text(),'Change Password')]")).click();
			Utility.xpath(driver, Locators.CP).click();
			
			logger.log(Status.PASS, "Click on Change Password");
			
//			WebElement S1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Change Password')]")));
			WebElement S1 = Utility.visibilityOfEleLocatedBy(wait, Locators.CPPAGE);
			
			if(S1.isDisplayed()==true) {
				
				logger.log(Status.PASS, S1.getText());
				
				String lhn15 = Screenshots.captureScreenshot(driver,"Change password page");	
				
				logger.pass("Change password page", MediaEntityBuilder.createScreenCaptureFromPath(lhn15).build());
			}else {
				logger.log(Status.FAIL, "Page is not landed on change password page");
			}
			
	//-------Back arrow in change password page
			driver.navigate().back();
			
			logger.log(Status.INFO, "Click on browser back");
	//-------Clicking on Deposit limits
//			WebElement S = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/lh-marquee/span")));
			WebElement S = Utility.visibilityOfEleLocatedBy(wait, Locators.SETTINGSPAGE);
			
			if(S.isDisplayed()==true) {
				
//				driver.findElement(By.xpath("//a[contains(text(),'Deposit Limits')]")).click();
				Utility.xpath(driver, Locators.DL).click();
				
				logger.log(Status.PASS, "Deposit Limits");
				
//				Boolean S2 = wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(text(),'Deposit limits')]"), "Deposit limits"));
				Boolean S2 = Utility.textToBe(wait, Locators.DLPAGE, "Deposit limits");
				
				logger.log(Status.PASS, S2.toString());
				
				String lhn16 = Screenshots.captureScreenshot(driver,"Deposit limits page");
				
				logger.pass("Deposit limits page", MediaEntityBuilder.createScreenCaptureFromPath(lhn16).build());
				
			}else {
				logger.log(Status.FAIL, "Deposit limits page is not landed");
			}
	//-------Back arrow in deposit limits page
			driver.navigate().back();
			
			logger.log(Status.INFO, "Click on browser back");
			
	//-------Clicking on Communication pref 		
//			WebElement CP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Communication')]")));
			WebElement CP = Utility.visibilityOfEleLocatedBy(wait, Locators.COMP);
			
			if(CP.isDisplayed()==true) {
				
				CP.click();
				
//				driver.findElement(By.xpath("//a[contains(text(),'Communication')]")).click();
				
				logger.log(Status.PASS, "Click on Communicaiton in settings page");
				
//				Boolean S3 = wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(text(),'Communication Preferences')]"), "Communication Preferences"));
				Boolean S3 = Utility.textToBe(wait, Locators.COMPPAGE, "Communication Preferences");
				
				logger.log(Status.PASS, S3.toString());
				
				String lhn17 = Screenshots.captureScreenshot(driver,"Communication pref page");
				
				logger.pass("Communication pref page", MediaEntityBuilder.createScreenCaptureFromPath(lhn17).build());
				
			}else {
				logger.log(Status.PASS, "Communication pref page is not landed");
			}
	//-------Back arrow in communication pref page
			driver.navigate().back();
			
			logger.log(Status.INFO, "Click on browser back");
	//-------Clicking on Service closure
			
//			WebElement SC = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Service Closure')]")));
			WebElement SC = Utility.visibilityOfEleLocatedBy(wait, Locators.SERVICECLOSURE);
			
			if(SC.isDisplayed()==true) {
				
				SC.click();
				
				logger.log(Status.PASS, "Click on Service Closure in settings page");
				
//				Boolean S4 = wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(text(),'Service Closure')]"), "Service Closure"));
				Boolean S4 = Utility.textToBe(wait, Locators.SCPAGE, "Service Closure");
				
				logger.log(Status.PASS, S4.toString());
				
				String lhn18 = Screenshots.captureScreenshot(driver,"Service closure page");
				
				logger.pass("Service closure page", MediaEntityBuilder.createScreenCaptureFromPath(lhn18).build());
			}else {
				logger.log(Status.PASS, "Service closure page is not landed");
			}
	//-------Back arrow in service closure page
					driver.navigate().back();	
					
					logger.log(Status.INFO, "Click on browser back");
		}


}
