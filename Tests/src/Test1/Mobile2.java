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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.usuage.com.Locators;
import com.usuage.com.ReportExtent;
import com.usuage.com.Screenshots;
import com.usuage.com.Utility;


public class Mobile2 extends ReportExtent{

 public static	WebDriver driver;
 public static	WebDriverWait wait;
// public static	ExtentHtmlReporter htmlReporter;
// public static	ExtentReports extent;
// public static	ExtentTest logger;
 public static	Properties pro;
	
	public Mobile2() throws IOException{
//	Data = new Properties();
	try {
		pro = new Properties();
		FileInputStream fis = new FileInputStream("C:\\eclipse-workspace\\Tests\\src\\com\\usuage\\com\\Data.properties");
		pro.load(fis);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	}
	
//	@BeforeMethod
//	public void setup() throws IOException {
		
//		ExtentReports report = ReportExtent.setup("Mobile2");
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
		
		/*htmlReporter = new ExtentHtmlReporter("./Reports/AutomationResults2.html");
		
		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);*/
//	}
	
	@Test(priority=0)
	public void browserLaunch() {
		
		logger = extent.createTest("browserLaunch");
		
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone 6/7/8");
		
		System.setProperty("webdriver.chrome.driver", Locators.DRIVERPATH);
		
		ChromeOptions  options = new ChromeOptions();
		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		options.addExtensions(new File (Locators.MODHEADER));
		driver = new ChromeDriver(options);
		
//		FileUtils.cleanDirectory(new File("./Reports/"));
		
//		System.out.println("Folder cleaned successfully");
		
		logger.log(Status.PASS, "ModHeader retrived");
		
		driver.get("chrome://extensions/?id=idgpnmonknjnojddfkpgkljpfnnfckljs/settings.tmpl.html");
		

			((JavascriptExecutor)driver).executeScript(
				    "localStorage.setItem('profiles', JSON.stringify([{                " +
				    "  title: 'Selenium', hideComment: true, appendMode: '',           " +
				    "  headers: [                                                      " +
				    "    {enabled: true, name: 'X-Forwarded-For', value: '178.62.58.25', comment: 'UK ip'}, " +
				    "  ],                                                              " +
				    "  respHeaders: [],                                                " +
				    "  filters: []                                                     " +
				    "}]));                                                             " );
//			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
			
			logger.log(Status.INFO, "Broswer launched successsfully with Mod IP");
			
			/*String url = pro.getProperty("APPURL");
			
			driver.get("http://"+url);
			*/
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
//			driver.get("http://m.sportingbet.com");
			
			Utility.get(driver, pro.getProperty("APPURL"));
			
			System.out.println("Application launched");
			
			logger.log(Status.PASS, "Application launced successfully");
			
			for (String winhandle: driver.getWindowHandles()) {
			    driver.switchTo().window(winhandle);
			    System.out.println("Window Switch");
			    logger.log(Status.PASS, "Window switched to Popup");

//			    driver.findElement(By.xpath(Locators.LANDINGPOPUP)).click();
			    Utility.xpath(driver, Locators.LANDINGPOPUP).click();
			}
//				logger.log(Status.INFO, "Carousel is not displayed");
				
				logger.log(Status.PASS, "Carousel is closed");
	}
	
	@Test(priority=1)
	public void lhn() throws IOException, InterruptedException {
		
		logger = extent.createTest("LHN checks");
		
			wait = new WebDriverWait(driver, 20);
			
			FileUtils.deleteDirectory(new File("C:\\eclipse-workspace\\Tests\\Screenshot"));
			
			logger.log(Status.INFO, "Screenshot Folder deleted");
			
			logger.log(Status.PASS, "SS folder updated");
			
//			WebElement Login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Properties.LOGIN1)));
			
//			WebElement Login = Utility.visibilityOfEleLocatedBy(driver, Locators.LOGIN1);
			
//			Login.click();
			
			Utility.visibilityOfEleLocatedBy(wait, Locators.LOGIN1).click();
			
			logger.log(Status.PASS, "Login clicked");
			
//			WebElement user = driver.findElement(By.xpath(Properties.USERNAME));
			
//			WebElement user = Utility.xpath(driver, Locators.USERNAME);
			
//			user.sendKeys(pro.getProperty("USERNAME"));
			
			Utility.xpath(driver, Locators.USERNAME).sendKeys(pro.getProperty("USERNAME"));
			
			logger.log(Status.PASS, "Entered Username");
			
			Utility.xpath(driver, Locators.PASSWORD).sendKeys(pro.getProperty("PASS"));
//			driver.findElement(By.xpath(Locators.PASSWORD)).sendKeys(pro.getProperty("PASS"));
			
			logger.log(Status.PASS, "Entered Password");
			
//			driver.findElement(By.xpath(Locators.LOGIN)).click();
			Utility.xpath(driver, Locators.LOGIN).click();
			
			logger.log(Status.PASS, "Login clicked");
			
		/*	if(driver.findElement(By.xpath("//span[contains(text(),'Verification required')]")).isDisplayed()==true) {
				
				driver.findElement(By.xpath("//button[contains(text(),'SEND NEW DOCUMENTS')]")).click();
				
				driver.findElement(By.xpath("//button[contains(text(),'Use driving licence')]")).click();
				
			}
			*/
			/*if(driver.findElement(By.xpath(Locators.MYBETS)).isDisplayed()==true){
				
				driver.findElement(By.xpath(Locators.MYBETS)).click();
				
				logger.log(Status.PASS, "Successfully logged in");
				
			}else {
				
				logger.log(Status.FAIL, "Login failed");
			}*/
			
			String DPLC = Screenshots.captureScreenshot(null, "DepositLimitCONF");
			
			logger.pass("Deposit Limits Interceptor", MediaEntityBuilder.createScreenCaptureFromPath(DPLC).build());
			
			Utility.xpath(driver, Locators.DEPOSITLIMITSCONFIR).click();
			
			if(Utility.xpath(driver, Locators.MYBETSHOME).isDisplayed()==true){
				
//				Utility.xpath(driver, Locators.MYBETS).click();
				
				logger.log(Status.PASS, "Sucessfully logged in");
				
			}else {
				logger.log(Status.FAIL, "Login Failed");				
			}
			
			/*TakesScreenshot ts = (TakesScreenshot) driver;
			
			File src = ts.getScreenshotAs(OutputType.FILE);
			
			FileUtils.copyFile(src, new File("./Screenshots"+System.currentTimeMillis()+".png"));*/
			
			String lhn1 = Screenshots.captureScreenshot(null, "Home1");
			
			logger.pass("Home page", MediaEntityBuilder.createScreenCaptureFromPath(lhn1).build());
			
//			WebElement  L1 = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//header[@id='main-header']/section/a[3]")));
			
			WebElement L1 = Utility.elementToBeClickable(wait, Locators.HEADERBAL);
			if(L1.isDisplayed()==true) {
				
				logger.log(Status.PASS, L1.getText());
				
//				driver.findElement(By.xpath(Locators.HEADERBAL)).click();
				
				Utility.xpath(driver, Locators.HEADERBAL).click();
				
				logger.log(Status.PASS, "Click on header balance");
				
			}else {
				
				logger.log(Status.FAIL, "Header balance text is missing in home page");
			}
						
//			WebElement L2 = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MYBAL)));
			
			WebElement L2 = Utility.visibilityOfEleLocatedBy(wait, Locators.MYBAL);
			
			Assert.assertEquals(L2.getText(), "My Balance");
			
			String lhn2 = Screenshots.captureScreenshot(null, "Header Balance");
			
			logger.pass("Header Balance", MediaEntityBuilder.createScreenCaptureFromPath(lhn2).build());

//			driver.findElement(By.xpath(Locators.DEPOSIT)).click();
			
			Utility.xpath(driver, Locators.DEPOSIT).click();
			
			logger.log(Status.PASS, "Click on Deposit");
			
//			driver.findElement(By.xpath("//span[contains(text(),'Verification pending')]")).isDisplayed();
			
			WebElement L = Utility.xpath(driver, Locators.DEPOSITTEXT);
			
			if(L.isDisplayed()==true) {
			driver.navigate().back();
			
			logger.log(Status.PASS, "Click on browser back");
			}else {
				logger.log(Status.FAIL, "Not landed on Deposit page");
			}
//			driver.findElement(By.xpath(Locators.BALCLOSE)).click();
			
			Utility.xpath(driver, Locators.BALCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in Balancebreakdown page");
			
			home();
			
//-----------Clicking on promotions 
//			driver.findElement(By.xpath(Locators.PROMOTIONS)).click();
			
			Utility.xpath(driver, Locators.PROMOTIONS).click();
			
			logger.log(Status.PASS, "Click on Promotions in LHN");
			
//			WebElement L3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.TOPPROMO)));
			WebElement L3 = Utility.visibilityOfEleLocatedBy(wait, Locators.TOPPROMO);
			
			if(L3.isDisplayed()==true) {
				
				logger.log(Status.PASS, L3.getText());
				
				String lhn3 = Screenshots.captureScreenshot(null,"Promotions page");
				
				logger.pass("Promotions page", MediaEntityBuilder.createScreenCaptureFromPath(lhn3).build());
				
			}else {
			
				logger.log(Status.FAIL, "Promotions page is not landed");
			}
//-----------'X' mark in Promotions page
//			driver.findElement(By.xpath(Locators.PROMOCLOSE)).click();
			Utility.xpath(driver, Locators.PROMOCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in Promotions page");
			
			home();
			
//-----------Clicking on My Bonuses
//			driver.findElement(By.xpath(Locators.MYBONUSES)).click();
			Utility.xpath(driver, Locators.MYBONUSESLHN).click();
			
			logger.log(Status.PASS, "Click on My Bonuses in LHN");
			
//			WebElement L4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MYBONUSES)));
			WebElement L4 = Utility.visibilityOfEleLocatedBy(wait, Locators.MYBONUSES);
			
			if(L4.isDisplayed()==true) {
				
				logger.log(Status.PASS, L4.getText());
				
				String lhn4 = Screenshots.captureScreenshot(null,"My Bonuses page");
				
				logger.pass("My Bonuses page", MediaEntityBuilder.createScreenCaptureFromPath(lhn4).build());
				
			}else {
				
				logger.log(Status.FAIL, "Bonuses page is not landed");
			}
//-----------'X' mark in bonuses page
//			driver.findElement(By.xpath(Locators.MYBONUSCLOSE)).click();
			Utility.xpath(driver, Locators.MYBONUSCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in bonuses page");
			
			home();
			
//-----------Clicking on bonus history
//			driver.findElement(By.xpath(Locators.BONUSHISTORY)).click();
			Utility.xpath(driver, Locators.BONUSHISTORY).click();
//			WebElement BH = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Bonus History')]")));
			
//			BH.click();
			
			logger.log(Status.PASS, "Click on Bonus History");
			
//			WebElement L5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.BHTEXT)));
			WebElement L5 = Utility.visibilityOfEleLocatedBy(wait, Locators.BHTEXT);
			
			if(L5.isDisplayed()==true) {
				
				logger.log(Status.PASS, L5.getText());
				
				String lhn5 = Screenshots.captureScreenshot(null,"BonusHistory page");
				
				logger.pass("BonusHistory page", MediaEntityBuilder.createScreenCaptureFromPath(lhn5).build());
				
			}else {
				logger.log(Status.PASS, "BonusHistory is not landed");
			}
//-----------'X' mark in bonus history page
//			driver.findElement(By.xpath(Locators.BHCLOSE)).click();
			Utility.xpath(driver, Locators.BHCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in bonus history page");
			
			home();
//-----------Clicking on My bets
//			driver.findElement(By.xpath(Locators.MYBETS1)).click();
			Utility.xpath(driver, Locators.MYBETSLHN).click();
			
			logger.log(Status.PASS, "Click on My bets in LHN");
			
//			WebElement L6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MYBETS1)));
			WebElement L6 = Utility.visibilityOfEleLocatedBy(wait, Locators.MYBETS);
			
			if(L6.isDisplayed()==true) {
				
				logger.log(Status.PASS, L6.getText());
				
				String lhn6 = Screenshots.captureScreenshot(null,"My Bets");
				
				logger.pass("My Bets", MediaEntityBuilder.createScreenCaptureFromPath(lhn6).build());
				
			}else {
				logger.log(Status.FAIL, "My bets page is not landed");
			}
//-----------'X' mark in My Bets page
//			driver.findElement(By.xpath(Locators.MYBETSCLOSE)).click();
			Utility.xpath(driver, Locators.MYBETSCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in My Bets page");
			
//			WebElement l1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.HOMEPAGE)));
			WebElement l1 = Utility.visibilityOfEleLocatedBy(wait, Locators.HOMEPAGE);
			
			if(l1.isDisplayed()==true) {
				
				logger.log(Status.PASS, l1.getText());
				
				logger.log(Status.INFO, "Landed on home page");
				
			}else {
				
				logger.log(Status.FAIL, "Not landed on home page");
			}
			
			home();
//-----------Clicking on My FreeBets
//			driver.findElement(By.xpath(Locators.MYFREEBETS)).click();
			Utility.xpath(driver, Locators.MYFREEBETS).click();
			
			logger.log(Status.PASS, "Click on My FreeBets in LHN");
			
//			WebElement L7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MYFREEBETS)));
//			WebElement L7 = Utility.visibilityOfEleLocatedBy(wait, Locators.MYFREEBETS);
			
//			if(L7.isDisplayed()==true) {
				
//				logger.log(Status.PASS, L7.getText());
				
//				WebElement FB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.MFBTNC)));
				WebElement FB = Utility.visibilityOfEleLocatedBy(wait, Locators.MFBTNC);
				FB.click();
				logger.log(Status.PASS, "Clicked on TnC");
				
				String lhn7 = Screenshots.captureScreenshot(null,"My FreeBets");
				
				logger.pass("MY FREEBETS", MediaEntityBuilder.createScreenCaptureFromPath(lhn7).build());
				
			/*}else {
				
				logger.log(Status.FAIL, "My FreeBets page is not landed");
			}*/
//-----------'X' mark in My FreeBets page
//			driver.findElement(By.xpath(Locators.MYFREEBETSCLOSE)).click();
			Utility.xpath(driver, Locators.MYFREEBETSCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in My FreeBets page");
			
			home();
			/*//Clicking on My FreeSpins
			driver.findElement(By.xpath("//span[contains(text(),'My Freespins')]")).click();
			
			WebElement L8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='myfreespins']/h1/span[1]")));
			
			if(L8.isDisplayed()==true) {
				
				System.out.println(L8.getText());
				
				Screenshots.captureScreenshot(null,driver, "My Freespins");
			}else {
				System.out.println("My Freespins page is not landed");
			}
			//'X' mark in My Freespins page
			driver.findElement(By.xpath("//div[@id='myfreespins']/h1/span[2]")).click();
			
			home();
			*/
//-----------Clicking on Betting preferences
//			driver.findElement(By.xpath(Locators.BS)).click();
			Utility.xpath(driver, Locators.BS).click();
			
			logger.log(Status.PASS, "Click on Betting preferences in LHN");
			
//			WebElement L9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.BS1)));	
			WebElement L9 = Utility.visibilityOfEleLocatedBy(wait, Locators.BS1);
			
			if(L9.isDisplayed()==true) {
				
				logger.log(Status.PASS, L9.getText());
				
				String lhn9 = Screenshots.captureScreenshot(null,"Betting Preferences");
				
				logger.pass(L9.getText(), MediaEntityBuilder.createScreenCaptureFromPath(lhn9).build());
			}else {
				
				logger.log(Status.FAIL, "Betting Preferences page is not landed");
			}
//-----------'X' Mark in betting preferences page
//			driver.findElement(By.xpath(Locators.BSCLOSE)).click();
			Utility.xpath(driver, Locators.BSCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' Mark in betting preferences page");
			
			home();
			
//-----------Clicking on Contact 
//			driver.findElement(By.xpath(Locators.CONTACT)).click();
			Utility.xpath(driver, Locators.CONTACT).click();
			
			logger.log(Status.PASS, "Click on Contact in LHN");
			
//			WebElement L10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locators.CONTACT1)));
			WebElement L10 = Utility.visibilityOfEleLocatedBy(wait, Locators.CONTACT1);
			
			if(L10.isDisplayed()==true) {
				
				logger.log(Status.PASS, L10.getText());
				
				String lhn10 = Screenshots.captureScreenshot(null,"Contact page");
				
				logger.pass("Contact page", MediaEntityBuilder.createScreenCaptureFromPath(lhn10).build());
			}else {
				
				logger.log(Status.FAIL, "Contact page is not landed");
			}
//-----------Clicking on logo
//			driver.findElement(By.xpath("//a[@class='brand-logo']")).click();
			Utility.xpath(driver, Locators.LOGO).click();
			
			logger.log(Status.PASS, "Click on Logo");
			
			home();
			
//-----------Clicking on personal details
//			driver.findElement(By.xpath("//span[contains(text(),'Personal Details')]")).click();
			Utility.xpath(driver, Locators.PERSONALDETAILS).click();
			
			logger.log(Status.PASS, "Click on personal details");
			
//			WebElement L11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Personal details')]")));
			WebElement L11 = Utility.visibilityOfEleLocatedBy(wait, Locators.PERSONALDETAILSTEXT);
			
			if(L11.isDisplayed()==true) {
				
				logger.log(Status.PASS, L11.getText());
				
				String lhn11 = Screenshots.captureScreenshot(null,"Personal details page");
				
				logger.pass("Personal details page", MediaEntityBuilder.createScreenCaptureFromPath(lhn11).build());
			}else {
				logger.log(Status.FAIL, "Personal details page is not landed");
				
			}
			
//-----------'X' mark in personal details page
//			driver.findElement(By.xpath("//div[@id='main-content']/pt-personal-details/div/lh-header-bar/h1/span")).click();
			Utility.xpath(driver, Locators.PDCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in personal details page");
			
			home();
			
//-----------Clicking on Transactions
//			driver.findElement(By.xpath("//span[contains(text(),'Transactions')]")).click();
			Utility.xpath(driver, Locators.TRANSACTIONS).click();
			
			logger.log(Status.PASS, "Click on Transactions in LHN");
			
//			WebElement L12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Transaction History')]")));
			WebElement L12 = Utility.visibilityOfEleLocatedBy(wait, Locators.TRANSACTIONSHISTORY);
			
			if(L12.isDisplayed()==true) {
				
				logger.log(Status.PASS, L12.getText());
				
				String lhn12 = Screenshots.captureScreenshot(null,"TransactionHistory page");
				
				logger.pass("TransactionHistory page", MediaEntityBuilder.createScreenCaptureFromPath(lhn12).build());
			}else {
				logger.log(Status.FAIL, "Transaction History page is not landed");
			}
			
//----------'X' mark in Transaction history page
//			driver.findElement(By.xpath("//div[@id='main-content']/pt-transactions/lh-header-bar/h1/span")).click();
			Utility.xpath(driver, Locators.TRANSACTIONCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in Transaction history page");
			
			home();
			
//-----------Clicking on Settings
//			WebElement S = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[6]/a/span")));
			WebElement S = Utility.visibilityOfEleLocatedBy(wait, Locators.SETTINGS);
			
			S.click();
			
			logger.log(Status.PASS, "Click on settings in LHN");
			
//			WebElement L13 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/lh-marquee/span")));
			WebElement L13 = Utility.visibilityOfEleLocatedBy(wait, Locators.SETTINGSPAGE);
			
			if(L13.isDisplayed()==true) {
				
				logger.log(Status.PASS, L13.getText());
				
				String lhn13 = Screenshots.captureScreenshot(null,"Settings");
				
				logger.pass(L13.getText(), MediaEntityBuilder.createScreenCaptureFromPath(lhn13).build());
				
				settings();
				
			}else {
			
				logger.log(Status.FAIL, "Settings page is not landed");
			}
			
//-----------'X' Mark in settings page
//			driver.findElement(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/span")).click();
			Utility.xpath(driver, Locators.SETTINGSCLOSE).click();
			
			logger.log(Status.PASS, "Click on 'X' Mark in settings page");
			
			home();
			
//-----------Clicking on Cashier page
//			driver.findElement(By.xpath("//span[contains(text(),'Cashier')]")).click();
			Utility.xpath(driver, Locators.CASHIER).click();
			
			logger.log(Status.PASS, "Click on Cashier in LHN");
			
			/*WebElement L14 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Verification pending')]")));
			
			if(L14.isDisplayed()==true) {
				
				logger.log(Status.PASS, L14.getText());*/
				
				String lhn14 = Screenshots.captureScreenshot(null,"Cashier");
				
				logger.pass("Cashier", MediaEntityBuilder.createScreenCaptureFromPath(lhn14).build());
			/*	
			}else {
				logger.log(Status.FAIL, "Cashier page is not landed");
			}
			*/
//-----------'X' Mark in Cashier page
//			driver.findElement(By.xpath("html/body/header/a")).click();
			
			driver.navigate().back();
			
			logger.log(Status.PASS, "Click on 'X' Mark in Cashier page");
			
//-----------Clicking on LHN menu
//			driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
			Utility.xpath(driver, Locators.LHN).click();
			
			logger.log(Status.INFO, "Click on LHN menu");
//-----------Clicking on Logout
//			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-logout-menu-item/a")).click();
			Utility.xpath(driver, Locators.LOGOUT).click();
			
			logger.log(Status.INFO, "Click on Logout");
			
			logger.log(Status.PASS, "Successfully Logged out!");
			
			driver.quit();
	}
	
	public void home () {
		//Checking My Bets icon from home page
//		WebElement L = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='main-header']/section/vn-dynamic-layout-single-slot[1]/ms-my-bets-button/a")));
		WebElement L = Utility.visibilityOfEleLocatedBy(wait, Locators.MYBETSHOME);
		if(L.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Page landed on home page");
		}else {
			logger.log(Status.FAIL, "Page is not landed on home page");
		}
		//Clicking on LHN menu
//		driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
		Utility.xpath(driver, Locators.LHN).click();
		
		logger.log(Status.INFO, "Click on LHN menu");
	}
	
	public void settings () throws IOException {
//-------Click on Changing password
//		driver.findElement(By.xpath("//a[contains(text(),'Change Password')]")).click();
		Utility.xpath(driver, Locators.CP).click();
		
		logger.log(Status.PASS, "Click on Change Password");
		
//		WebElement S1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Change Password')]")));
		WebElement S1 = Utility.visibilityOfEleLocatedBy(wait, Locators.CPPAGE);
		
		if(S1.isDisplayed()==true) {
			
			logger.log(Status.PASS, S1.getText());
			
			String lhn15 = Screenshots.captureScreenshot(null,"Change password page");	
			
			logger.pass("Change password page", MediaEntityBuilder.createScreenCaptureFromPath(lhn15).build());
		}else {
			logger.log(Status.FAIL, "Page is not landed on change password page");
		}
		
//-------Back arrow in change password page
		driver.navigate().back();
		
		logger.log(Status.INFO, "Click on browser back");
//-------Clicking on Deposit limits
//		WebElement S = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/lh-marquee/span")));
		WebElement S = Utility.visibilityOfEleLocatedBy(wait, Locators.SETTINGSPAGE);
		
		if(S.isDisplayed()==true) {
			
//			driver.findElement(By.xpath("//a[contains(text(),'Deposit Limits')]")).click();
			Utility.xpath(driver, Locators.DL).click();
			
			logger.log(Status.PASS, "Deposit Limits");
			
//			Boolean S2 = wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(text(),'Deposit limits')]"), "Deposit limits"));
			Boolean S2 = Utility.textToBe(wait, Locators.DLPAGE, "Deposit limits");
			
			logger.log(Status.PASS, S2.toString());
			
			String lhn16 = Screenshots.captureScreenshot(null,"Deposit limits page");
			
			logger.pass("Deposit limits page", MediaEntityBuilder.createScreenCaptureFromPath(lhn16).build());
			
		}else {
			logger.log(Status.FAIL, "Deposit limits page is not landed");
		}
//-------Back arrow in deposit limits page
		driver.navigate().back();
		
		logger.log(Status.INFO, "Click on browser back");
		
//-------Clicking on Communication pref 		
//		WebElement CP = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Communication')]")));
		WebElement CP = Utility.visibilityOfEleLocatedBy(wait, Locators.COMP);
		
		if(CP.isDisplayed()==true) {
			
			CP.click();
			
//			driver.findElement(By.xpath("//a[contains(text(),'Communication')]")).click();
			
			logger.log(Status.PASS, "Click on Communicaiton in settings page");
			
//			Boolean S3 = wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(text(),'Communication Preferences')]"), "Communication Preferences"));
			Boolean S3 = Utility.textToBe(wait, Locators.COMPPAGE, "Communication Preferences");
			
			logger.log(Status.PASS, S3.toString());
			
			String lhn17 = Screenshots.captureScreenshot(null,"Communication pref page");
			
			logger.pass("Communication pref page", MediaEntityBuilder.createScreenCaptureFromPath(lhn17).build());
			
		}else {
			logger.log(Status.PASS, "Communication pref page is not landed");
		}
//-------Back arrow in communication pref page
		driver.navigate().back();
		
		logger.log(Status.INFO, "Click on browser back");
//-------Clicking on Service closure
		
//		WebElement SC = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Service Closure')]")));
		WebElement SC = Utility.visibilityOfEleLocatedBy(wait, Locators.SERVICECLOSURE);
		
		if(SC.isDisplayed()==true) {
			
			SC.click();
			
			logger.log(Status.PASS, "Click on Service Closure in settings page");
			
//			Boolean S4 = wait.until(ExpectedConditions.textToBe(By.xpath("//span[contains(text(),'Service Closure')]"), "Service Closure"));
			Boolean S4 = Utility.textToBe(wait, Locators.SCPAGE, "Service Closure");
			
			logger.log(Status.PASS, S4.toString());
			
			String lhn18 = Screenshots.captureScreenshot(null,"Service closure page");
			
			logger.pass("Service closure page", MediaEntityBuilder.createScreenCaptureFromPath(lhn18).build());
		}else {
			logger.log(Status.PASS, "Service closure page is not landed");
		}
//-------Back arrow in service closure page
				driver.navigate().back();	
				
				logger.log(Status.INFO, "Click on browser back");
	}
	
	/*@AfterMethod
	public void tearDown(ITestResult result) throws IOException
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String temp = Screenshots.captureScreenshot(null,driver, "Home");
			
//			logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
		}
		
//		extent.flush();
		driver.quit();
		
	}*/
	
}
