package Test1;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.usuage.com.Screenshots;





public class Betboo2 {

	WebDriver driver;
	WebDriverWait wait;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeMethod
	public void setup() throws IOException {
		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
		
		htmlReporter = new ExtentHtmlReporter("./Reports/BetbooLHN.html");
		
		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
	}
	
	@Test(priority=0)
	public void browserLaunch() {
		
		logger = extent.createTest("browserLaunch");
		
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone 6/7/8");
		
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\ssannala\\\\Downloads\\\\Chromedriver\\\\chromedriver.exe");
		
		ChromeOptions  options = new ChromeOptions();
		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		options.addExtensions(new File ("C:\\Users\\ssannala\\Downloads\\modHeader.crx"));
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
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
			
			logger.log(Status.INFO, "Broswer launched successsfully with Mod IP");
			
			driver.get("http://m.br.betboo.com");
			
			System.out.println("Application launched");
			
			logger.log(Status.PASS, "Application launced successfully");
			
			/*for (String winhandle: driver.getWindowHandles()) {
			    driver.switchTo().window(winhandle);
			    System.out.println("Window Switch");
			    logger.log(Status.PASS, "Window switched to Popup");

			    driver.findElement(By.xpath("html/body/modal-window/div/div/ms-modal-dialog/div[1]/div/i")).click();
			}
//				logger.log(Status.INFO, "Carousel is not displayed");
				
				logger.log(Status.PASS, "Carousel is closed");*/
	}
	
	@Test(priority=1)
	public void LHN1() throws IOException {
		
			logger = extent.createTest("LHN checks1");
		
			wait = new WebDriverWait(driver, 20);
			
			FileUtils.deleteDirectory(new File("C:\\eclipse-workspace\\Tests\\Screenshot"));
			
			logger.log(Status.INFO, "Screenshot Folder deleted");
			
			logger.log(Status.PASS, "SS folder updated");
			
			driver.findElement(By.xpath("//a[@class='btn login']")).click();
			
			driver.findElement(By.xpath("//div[@id='language-switch']/div[3]/a")).click();
			
			logger.log(Status.PASS, "language selection");
			
			driver.findElement(By.xpath("//div[@id='language-switch']/div[2]/div/ul/li[1]/a")).click();
			
			logger.log(Status.PASS, "PTBR lang selected");
			
			driver.findElement(By.xpath("//a[@class='brand-logo']")).click();
			
			WebElement Login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btn login']")));
			
			Login.click();
			
			logger.log(Status.PASS, "Login clicked");
			
			driver.findElement(By.xpath("//div[@id='username']/input")).sendKeys("ptbrusername700@yopmail.com");
			
			logger.log(Status.PASS, "Entered Username");
			
			driver.findElement(By.xpath("//div[@id='password']/input")).sendKeys("qwe123");
			
			logger.log(Status.PASS, "Entered Password");
			
			driver.findElement(By.xpath("//div[@id='login']/form/fieldset/section/div[2]/button")).click();
			
			logger.log(Status.PASS, "Login clicked");
			
		/*	if(driver.findElement(By.xpath("//span[contains(text(),'Verification required')]")).isDisplayed()==true) {
				
				driver.findElement(By.xpath("//button[contains(text(),'SEND NEW DOCUMENTS')]")).click();
				
				driver.findElement(By.xpath("//button[contains(text(),'Use driving licence')]")).click();
				
			}
			*/
			if(driver.findElement(By.xpath("//header[@id='main-header']/section/vn-dynamic-layout-single-slot[1]/ms-my-bets-button/a")).isDisplayed()==true){
				
				String lhn1 = Screenshots.captureScreenshot(driver, "Home1");
				
				logger.pass("Home page", MediaEntityBuilder.createScreenCaptureFromPath(lhn1).build());
				
				logger.log(Status.PASS, "Successfully logged in");
				
			}else {
				
				logger.log(Status.FAIL, "Login failed");
			}
			
			driver.findElement(By.xpath("//header[@id='main-header']/section/vn-dynamic-layout-single-slot[1]/ms-my-bets-button/a")).click();
			
			String lhn2 = Screenshots.captureScreenshot(driver, "My Bets");
			
			logger.pass("My Bets", MediaEntityBuilder.createScreenCaptureFromPath(lhn2).build());
			
			WebElement  L1 = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//header[@id='main-header']/section/a[3]")));
			
			if(L1.isDisplayed()==true) {
				
				logger.log(Status.PASS, L1.getText());
				
				driver.findElement(By.xpath("//header[@id='main-header']/section/a[3]")).click();
				
				logger.log(Status.PASS, "Click on header balance");
				
			}else {
				
				logger.log(Status.FAIL, "Header balance text is missing in home page");
			}
						
			WebElement L2 = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Meu saldo')]")));
			
			Assert.assertEquals(L2.getText(), "Meu saldo");
			
			String lhn3 = Screenshots.captureScreenshot("Header Balance");
			
			logger.pass("Header Balance", MediaEntityBuilder.createScreenCaptureFromPath(lhn3).build());

			driver.findElement(By.xpath("//button[@class='btn prominent']")).click();
			
			logger.log(Status.PASS, "Click on Deposit");
			
			driver.findElement(By.xpath("/html/body/header/h1")).isDisplayed();
			
			driver.navigate().back();
			
			logger.log(Status.PASS, "Click on browser back");
		
			driver.findElement(By.xpath("//div[@id='main-content']/pt-balance-breakdown/lh-cross-product-layout/pt-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in Balancebreakdown page");
			
			home();
			
//----------Atis page
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[1]/div/vn-default-menu-item/a")).click();
			
			logger.log(Status.PASS, "Click on Atis");
			
			WebElement L3 = driver.findElement(By.xpath("//a[contains(text(),'Liberaçao Antecipada')]"));
			
			if(L3.isDisplayed()==true) {
				
				logger.log(Status.PASS, L3.getText());
				
				String lhn4 = Screenshots.captureScreenshot("Atis page");
				
				logger.pass("Atis page", MediaEntityBuilder.createScreenCaptureFromPath(lhn4).build());
			}else {
				logger.log(Status.FAIL, "Not landed on Atis page");
			}
			
			driver.findElement(By.xpath("//div[@id='main-content']/pt-atis-integration/div/div[1]/pt-go-back/div/a")).click();
			
			logger.log(Status.PASS, "Click on Back arrow in Atis page");
			
			home();
			
//-----------Clicking on My bets
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[3]/div/vn-default-menu-item[1]/a")).click();
			
			logger.log(Status.PASS, "Click on My bets in LHN");
			
			WebElement L4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Minhas Apostas')]")));
			
			if(L4.isDisplayed()==true) {
				
				logger.log(Status.PASS, L4.getText());
				
				String lhn5 = Screenshots.captureScreenshot("My Bets");
				
				logger.pass("My Bets", MediaEntityBuilder.createScreenCaptureFromPath(lhn5).build());
				
			}else {
				logger.log(Status.FAIL, "My bets page is not landed");
			}
//-----------'X' mark in My Bets page
			driver.findElement(By.xpath("//div[@id='mybets']/div[1]/ms-my-bets-title/div/div[1]/i")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in My Bets page");
			
			home();
			
//-----------Clicking on My FreeBets
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[3]/div/vn-default-menu-item[2]/a/span")).click();
			
			logger.log(Status.PASS, "Click on My FreeBets in LHN");
			
			WebElement L5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'MINHAS FREEBETS')]")));
			
			if(L5.isDisplayed()==true) {
				
				logger.log(Status.PASS, L5.getText());
				
				WebElement FB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='freebets']/ms-freebet-tac/div/div[1]/span")));
				FB.click();
				logger.log(Status.PASS, "Clicked on TnC");
				
				String lhn6 = Screenshots.captureScreenshot("My FreeBets");
				
				logger.pass(L5.getText(), MediaEntityBuilder.createScreenCaptureFromPath(lhn6).build());
				
			}else {
				
				logger.log(Status.FAIL, "My FreeBets page is not landed");
			}
//-----------'X' mark in My FreeBets page
			driver.findElement(By.xpath("//div[@id='freebets']/ms-freebet-header/div/div[1]/i")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in My FreeBets page");
			
			home();
			
//----------Clicking on My FreeSpins
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[3]/div/vn-default-menu-item[3]/a/span")).click();
			
			WebElement L6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='myfreespins']/h1/span[1]")));
			
			if(L6.isDisplayed()==true) {
				
				System.out.println(L6.getText());
				
				String lhn7 = Screenshots.captureScreenshot("My Freespins");
				
				logger.pass("My Freespins", MediaEntityBuilder.createScreenCaptureFromPath(lhn7).build());
				
			}else {
				
				logger.log(Status.FAIL, "My Freespins page is not landed");
			}
			//'X' mark in My Freespins page
			driver.findElement(By.xpath("//div[@id='myfreespins']/h1/span[2]")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in freespins page");
			
			driver.findElement(By.xpath("//header[@id='main-header']/section/a[2]")).click();
			
			home();
			
//-----------Clicking on My Bonuses
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[3]/div/vn-default-menu-item[4]/a")).click();
			
			logger.log(Status.PASS, "Click on My Bonuses in LHN");
			
			WebElement L7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-my-bonuses/lh-header-bar/h1/lh-marquee/span")));
			
			if(L7.isDisplayed()==true) {
				
				logger.log(Status.PASS, L7.getText());
				
				String lhn8 = Screenshots.captureScreenshot("My Bonuses page");
				
				logger.pass("My Bonuses page", MediaEntityBuilder.createScreenCaptureFromPath(lhn8).build());
				
			}else {
				
				logger.log(Status.FAIL, "Bonuses page is not landed");
			}
//-----------'X' mark in bonuses page
			driver.findElement(By.xpath("//div[@id='main-content']/pt-my-bonuses/lh-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in bonuses page");
			
			home();
			
//-----------Clicking on promotions 
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[3]/div/vn-default-menu-item[5]/a")).click();
			
			logger.log(Status.PASS, "Click on Promotions in LHN");
			
			WebElement L8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lh-navigation-layout-page[@id='mypromotions-page']/lh-header-bar/h1/lh-marquee/span")));
			
			if(L8.isDisplayed()==true) {
				
				logger.log(Status.PASS, L8.getText());
				
				String lhn9 = Screenshots.captureScreenshot("Promotions page");
				
				logger.pass("Promotions page", MediaEntityBuilder.createScreenCaptureFromPath(lhn9).build());
				
			}else {
			
				logger.log(Status.FAIL, "Promotions page is not landed");
			}
//-----------'X' mark in Promotions page
			driver.findElement(By.xpath("//lh-navigation-layout-page[@id='mypromotions-page']/lh-header-bar/h1/span[2]")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in Promotions page");
			
			home();
			
//-----------Clicking on bonus history
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[3]/div/vn-default-menu-item[6]/a")).click();
			
			logger.log(Status.PASS, "Click on Bonus History");
			
			WebElement L9 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-bonus-history/pt-bonus-history-legacy/lh-header-bar/h1/lh-marquee/span")));
			
			if(L9.isDisplayed()==true) {
				
				logger.log(Status.PASS, L9.getText());
				
				String lhn10 = Screenshots.captureScreenshot("BonusHistory page");
				
				logger.pass("BonusHistory page", MediaEntityBuilder.createScreenCaptureFromPath(lhn10).build());
				
			}else {
				logger.log(Status.PASS, "BonusHistory is not landed");
			}
//-----------'X' mark in bonus history page
			driver.findElement(By.xpath("//div[@id='main-content']/pt-bonus-history/pt-bonus-history-legacy/lh-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in bonus history page");
			
			home();
	}

		@Test(priority=2)
		public void LHN2() throws IOException {
			
			logger = extent.createTest("LHN checks2");
			
			wait = new WebDriverWait(driver, 20);
			
//-----------Clicking on Betting preferences
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[1]/a")).click();
			
			logger.log(Status.PASS, "Click on Betting preferences in LHN");
			
			WebElement L10 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-view']/ms-betting-preferences/div/div[1]/div/div[2]")));	
			
			if(L10.isDisplayed()==true) {
				
				logger.log(Status.PASS, L10.getText());
				
				String lhn11 = Screenshots.captureScreenshot("Betting Preferences");
				
				logger.pass(L10.getText(), MediaEntityBuilder.createScreenCaptureFromPath(lhn11).build());
			}else {
				
				logger.log(Status.FAIL, "Betting Preferences page is not landed");
			}
//-----------'X' Mark in betting preferences page
			driver.findElement(By.xpath("//div[@id='main-view']/ms-betting-preferences/div/div[1]/div/div[1]/i")).click();
			
			logger.log(Status.PASS, "Click on 'X' Mark in betting preferences page");
			
			home();
			
//-----------Clicking on Contact 
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[6]/a")).click();
			
			logger.log(Status.PASS, "Click on Contact in LHN");
			
			WebElement L11 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-contact/div/div/lh-header-bar/h1/lh-marquee/span")));

			if(L11.isDisplayed()==true) {
				
				logger.log(Status.PASS, L11.getText());
				
				String lhn12 = Screenshots.captureScreenshot("Contact page");
				
				logger.pass("Contact page", MediaEntityBuilder.createScreenCaptureFromPath(lhn12).build());
			}else {
				
				logger.log(Status.FAIL, "Contact page is not landed");
			}
//-----------Clicking on logo
			driver.findElement(By.xpath("//a[@class='brand-logo']")).click();
			
			logger.log(Status.PASS, "Click on Logo");
			
			home();
			
//-----------Clicking on personal details
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[3]/a")).click();
			
			logger.log(Status.PASS, "Click on personal details");
			
			WebElement L12 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-personal-details/div/lh-header-bar/h1/lh-marquee/span")));
			
			if(L12.isDisplayed()==true) {
				
				logger.log(Status.PASS, L12.getText());
				
				String lhn13 = Screenshots.captureScreenshot("Personal details page");
				
				logger.pass("Personal details page", MediaEntityBuilder.createScreenCaptureFromPath(lhn13).build());
			}else {
				logger.log(Status.FAIL, "Personal details page is not landed");
				
			}
			
//-----------'X' mark in personal details page
			driver.findElement(By.xpath("//div[@id='main-content']/pt-personal-details/div/lh-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in personal details page");
			
			home();
			
//-----------Clicking on Transactions
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[4]/a")).click();
			
			logger.log(Status.PASS, "Click on Transactions in LHN");
			
			WebElement L13 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-transactions/lh-header-bar/h1/lh-marquee/span")));
			
			if(L13.isDisplayed()==true) {
				
				logger.log(Status.PASS, L13.getText());
				
				String lhn14 = Screenshots.captureScreenshot("TransactionHistory page");
				
				logger.pass("TransactionHistory page", MediaEntityBuilder.createScreenCaptureFromPath(lhn14).build());
			}else {
				logger.log(Status.FAIL, "Transaction History page is not landed");
			}
			
//----------'X' mark in Transaction history page
			driver.findElement(By.xpath("//div[@id='main-content']/pt-transactions/lh-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in Transaction history page");
			
			home();
			
//-----------Clicking on Settings
			WebElement S = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[5]/a")));

			S.click();
			
			logger.log(Status.PASS, "Click on settings in LHN");
			
			WebElement L14 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/lh-marquee/span")));
			
			if(L14.isDisplayed()==true) {
				
				logger.log(Status.PASS, L14.getText());
				
				String lhn15 = Screenshots.captureScreenshot("Settings");
				
				logger.pass(L14.getText(), MediaEntityBuilder.createScreenCaptureFromPath(lhn15).build());
				
				settings();
				
			}else {
			
				logger.log(Status.FAIL, "Settings page is not landed");
			}
			
//-----------'X' Mark in settings page
			driver.findElement(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "Click on 'X' Mark in settings page");
			
			home();
			
//-----------Clicking on Cashier page
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[8]/a")).click();
			
			logger.log(Status.PASS, "Click on Cashier in LHN");
			
			WebElement L15 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/header/h1")));
			
			if(L15.isDisplayed()==true) {
				
				logger.log(Status.PASS, L15.getText());
				
				String lhn16 = Screenshots.captureScreenshot("Cashier");
				
				logger.pass("Cashier", MediaEntityBuilder.createScreenCaptureFromPath(lhn16).build());
				
			}else {
				logger.log(Status.FAIL, "Cashier page is not landed");
			}
			
			driver.navigate().back();
			
			logger.log(Status.PASS, "Click back arrow on cashier page");
			
//-----------Clicking on LHN menu
			driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
			
			logger.log(Status.INFO, "Click on LHN menu");
//-----------Clicking on Logout
			driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-logout-menu-item/a")).click();
			
			logger.log(Status.INFO, "Click on Logout");
			
			logger.log(Status.PASS, "Successfully Logged out!");
			
			driver.quit();
	}
	
	public void home () {
		//Checking My Bets icon from home page
		WebElement L = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='main-header']/section/vn-dynamic-layout-single-slot[1]/ms-my-bets-button/a")));
		if(L.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Page landed on home page");
		}else {
			logger.log(Status.FAIL, "Page is not landed on home page");
		}
		//Clicking on LHN menu
		driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
		
		logger.log(Status.INFO, "Click on LHN menu");
	}
	
	public void settings () throws IOException {
//-------Click on Changing password
		driver.findElement(By.xpath("//div[@id='settings']/div[1]/pt-vertical-menu/ul/li[1]/a")).click();

		logger.log(Status.PASS, "Click on Change Password");
		
		WebElement S1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-change-password/lh-navigation-layout-page/lh-header-bar/h1/lh-marquee/span")));

		if(S1.isDisplayed()==true) {
			
			logger.log(Status.PASS, S1.getText());
			
			String lhn17 = Screenshots.captureScreenshot("Change password page");	
			
			logger.pass("Change password page", MediaEntityBuilder.createScreenCaptureFromPath(lhn17).build());
		}else {
			logger.log(Status.FAIL, "Page is not landed on change password page");
		}
		
//-------Back arrow in change password page
		driver.navigate().back();
		
		logger.log(Status.INFO, "Click on browser back");
//-------Clicking on Deposit limits
		
		driver.findElement(By.xpath("//div[@id='settings']/div[1]/pt-vertical-menu/ul/li[3]/a")).click();
		
		logger.log(Status.PASS, "Click on Advanced settings");
		
		WebElement S2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='settings']/div/pt-vertical-menu/ul/li[1]/a")));
		
		if(S2.isDisplayed()==true) {
			
			S2.click();
			
			logger.log(Status.PASS, "Click on Deposit Limits");
			
			WebElement S3 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-deposit-limits-page/div/lh-header-bar/h1/lh-marquee/span")));
			
			logger.log(Status.PASS, S3.getText());
			
			String lhn18 = Screenshots.captureScreenshot("Deposit limits page");
			
			logger.pass("Deposit limits page", MediaEntityBuilder.createScreenCaptureFromPath(lhn18).build());
			
		}else {
			logger.log(Status.FAIL, "Deposit limits page is not landed");
		}
//-------Back arrow in deposit limits page
		driver.navigate().back();
		
		logger.log(Status.INFO, "Click on browser back");
		
//-------Clicking on Service closure
		
		WebElement S4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='settings']/div/pt-vertical-menu/ul/li[2]/a")));
				
		if(S4.isDisplayed()==true) {
					
			S4.click();
					
			logger.log(Status.PASS, "Click on Service Closure in settings page");
					
			WebElement S5 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-service-closure/lh-navigation-layout-page/lh-header-bar/h1/lh-marquee/span")));
					
			logger.log(Status.PASS, S5.getText());
					
			String lhn19 = Screenshots.captureScreenshot("Service closure page");
					
			logger.pass("Service closure page", MediaEntityBuilder.createScreenCaptureFromPath(lhn19).build());
			}else {
			logger.log(Status.PASS, "Service closure page is not landed");
			}
//-------Back arrow in service closure page
			driver.navigate().back();	
						
			logger.log(Status.INFO, "Click on browser back");
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/lh-marquee/span")));
			
			logger.log(Status.PASS, "Landed on Advanced settings page");
			
			driver.findElement(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "Click on 'X' mark in advanced settings page");
			
			home();
			
//-----------Clicking on Settings
			WebElement S6 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[5]/a")));
			
			S6.click();
			
//-------Clicking on Communication pref 		
		WebElement S7 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='settings']/div[1]/pt-vertical-menu/ul/li[2]/a")));
		
		if(S7.isDisplayed()==true) {
			
			S7.click();
			
//			driver.findElement(By.xpath("//a[contains(text(),'Communication')]")).click();
			
			logger.log(Status.PASS, "Click on Communicaiton in settings page");
			
			WebElement S8 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-communication/lh-navigation-layout-page/lh-header-bar/h1/lh-marquee/span")));
			
			logger.log(Status.PASS, S8.getText());
			
			String lhn20 = Screenshots.captureScreenshot("Communication pref page");
			
			logger.pass("Communication pref page", MediaEntityBuilder.createScreenCaptureFromPath(lhn20).build());
			
		}else {
			logger.log(Status.PASS, "Communication pref page is not landed");
		}
//-------Back arrow in communication pref page
		driver.navigate().back();
		
		logger.log(Status.INFO, "Click on browser back");

	}
	
	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String SS = Screenshots.captureScreenshot(result.getMethod().getMethodName());
			
			 logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(SS).build());
			 
			 logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"Test case Fail", ExtentColor.RED));
		       
		}else if(result.getStatus()==ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"Test case Passed",ExtentColor.GREEN));
			
		}else {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"Test case skipped", ExtentColor.ORANGE));
		}
		
		extent.flush();
		
	}

}
