package Test1;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class Mobile3 {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;

	
	@BeforeMethod
	public void setup() throws IOException {
		
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM HH:mm:ss");
		
		htmlReporter = new ExtentHtmlReporter("./Reports/AutomationResults3.html");
		
		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
	}
	
	@Test(priority=0)
	public void browserLaunch() throws IOException {
		
		logger = extent.createTest("browserLaunch");
		
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone 6/7/8");
		
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\ssannala\\\\Downloads\\\\Chromedriver\\\\chromedriver.exe");
		
//		driver.navigate().refresh();
		
		ChromeOptions  options = new ChromeOptions();
		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		options.addExtensions(new File ("C:\\Users\\ssannala\\Downloads\\modHeader.crx"));
		driver = new ChromeDriver(options);
		
		driver.navigate().refresh();
		
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
			
			driver.get("http://m.sportingbet.com");
			
			System.out.println("Application launched");
			
			logger.log(Status.PASS, "Application launced successfully");
			
			for (String winhandle: driver.getWindowHandles()) {
			    driver.switchTo().window(winhandle);
			    System.out.println("Window Switch");
			    logger.log(Status.PASS, "Window switched to Popup");
			    WebElement pop = driver.findElement(By.xpath("html/body/modal-window/div/div/ms-modal-dialog/div[1]/div/i"));
			    if(pop.isDisplayed()==true) {
			    
			    driver.findElement(By.xpath("html/body/modal-window/div/div/ms-modal-dialog/div[1]/div/i")).click();
			    logger.log(Status.PASS, "Carousel is closed");
			}else {
				logger.log(Status.INFO, "Carousel is not displayed");
			}
			}	
	}
	
	@Test(priority=1)
	public void personalDetails() throws IOException {
		
		logger = extent.createTest("personalDetails");
		
		wait = new WebDriverWait(driver, 30);
		
//		FileUtils.deleteDirectory(new File("./Screenshot"));
		
		FileUtils.cleanDirectory(new File("C:\\eclipse-workspace\\Tests\\Screenshot"));
		
		logger.log(Status.INFO, "Screenshot Folder deleted");
		
		logger.log(Status.PASS, "SS folder updated");
		
		WebElement Login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Log in')]")));
		
		Login.click();
		
		logger.log(Status.PASS, "Login page loaded");
		
		driver.findElement(By.xpath("//div[@id='username']/input")).sendKeys("sbcm6@yopmail.com");
		
		logger.log(Status.INFO, "User name entered");
		
		String SS = Screenshots.captureScreenshot(driver, "Loginpage");
		
		logger.addScreenCaptureFromPath(SS);
		
//		logger.log(Status.PASS, SS);
		
		logger.pass("Login page", MediaEntityBuilder.createScreenCaptureFromPath(SS).build());
		
		driver.findElement(By.xpath("//div[@id='password']/input")).sendKeys("qwe123");
		
		logger.log(Status.INFO, "Password entered");
		
		driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
		
		logger.log(Status.PASS, "Login clicked");
		
		WebElement LH = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'My Bets')]")));
		
		if(LH.isDisplayed()==true) {
			System.out.println("Login successful");
			
			logger.log(Status.PASS, "Landed on home page");
		}else {
			System.out.println("Login Failed");
		}
		
		String SS1 = Screenshots.captureScreenshot(driver, "AfterLogin");
		
//		logger.addScreenCaptureFromPath(SS, null);
		
		logger.pass(LH.getText(), MediaEntityBuilder.createScreenCaptureFromPath(SS1).build());
		
//		logger.pass(SS1);
		
		driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
		
		logger.log(Status.INFO, "LHN clicked");
		
		driver.findElement(By.xpath("//span[contains(text(),'Personal Details')]")).click();
		
		logger.log(Status.INFO, "Personal details is clicked");
		
		WebElement PD = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Personal details')]")));
		
		if(PD.isDisplayed()==true) {
			
			logger.log(Status.INFO, "Perosnal details page landed");
			
		driver.findElement(By.xpath("//div[@id='mobilenumber']/input")).clear();
		
		logger.log(Status.INFO, "TelefonNummer ist klar");
		
		String N1 = RandomStringUtils.randomNumeric(9);
		
		driver.findElement(By.xpath("//div[@id='mobilenumber']/input")).sendKeys("7"+N1);
		
		logger.log(Status.INFO, "Neu nummer ist updated");
		
		WebElement SAVE = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='submit']")));
		
		SAVE.click();
		
		logger.log(Status.INFO, "Saved successfully");
		
		WebElement SUCCESS = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-personal-details/div/vn-message-panel/div/div/div")));
		
		logger.log(Status.PASS, SUCCESS.getText());
		
		}
	}
	
	@Test(priority=2)
	public void docUpload() throws IOException {
		
		logger = extent.createTest("Document upload");
		
		logger.log(Status.INFO, "Document upload from personal details page");
		
		wait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.xpath("//a[contains(text(),'file upload page')]")).click();
		
		logger.log(Status.PASS, "Doc uploaded clicked");
		
		WebElement Doc = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Document upload')]")));
		
		if(Doc.isDisplayed()==true) {
			
			String SS1 = Screenshots.captureScreenshot(driver, "Doc Upload page");
			
//			logger.addScreenCaptureFromPath(Screenshots.captureScreenshot(driver, "Doc upload page"));
			
			logger.pass(Doc.getText(), MediaEntityBuilder.createScreenCaptureFromPath(SS1).build());
			
			logger.log(Status.PASS, "Doc upload landed");
			
		}else {
			logger.log(Status.FAIL, "Doc upload page not landed");
		}
		
		driver.findElement(By.xpath("//div[@id='main-content']/pt-doc-upload/lh-header-bar/h1/span")).click();
		
		logger.log(Status.PASS, "X Mark click on doc upload page");
		
	/*	WebElement PD = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Personal details')]")));
		
		if(PD.isDisplayed()==true) {
			
			driver.findElement(By.xpath("//div[@id='main-content']/pt-personal-details/div/lh-header-bar/h1/span")).click();
			
			logger.log(Status.PASS, "X-Mark clicked on personal details page ");
		}*/
		home();
	}
	
	@Test(priority=3)
	public void changePassword() throws IOException {
		
		logger = extent.createTest("Change Password");
		
		wait = new WebDriverWait(driver, 20);
		
		settings();
		
		driver.findElement(By.xpath("//a[contains(text(),'Change Password')]")).click();
		
		WebElement S1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Change Password')]")));
		
		if(S1.isDisplayed()==true) {
			
			logger.log(Status.PASS, S1.getText());
			
			String SS2 = Screenshots.captureScreenshot(driver, "Change password page");
			
			logger.addScreenCaptureFromPath(SS2);
			
			logger.pass("Change Password", MediaEntityBuilder.createScreenCaptureFromPath(SS2).build());
			
		}else {
			logger.log(Status.FAIL, "Page is not landed on change password page");
		}
		//New & Old pass same
		driver.findElement(By.xpath("//div[@id='oldpassword']/input")).sendKeys("qwe123");
		
		logger.log(Status.PASS, "Old password entered");
		
		driver.findElement(By.xpath("//div[@id='newpassword']/input")).sendKeys("qwe123");
		
		logger.log(Status.PASS,"Old password given as New Password");
		
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		
		String Error = driver.findElement(By.xpath("//div[contains(text(),'Your new password cannot match your old password!')]")).getText();
		
		logger.log(Status.PASS, Error);
		
		String SS2 = Screenshots.captureScreenshot(driver, "CP1");
		
		logger.pass(Error, MediaEntityBuilder.createScreenCaptureFromPath(SS2).build());
		//Pass change
		driver.findElement(By.xpath("//div[@id='oldpassword']/input")).clear();
		
		driver.findElement(By.xpath("//div[@id='oldpassword']/input")).sendKeys("qwe123");
		
		driver.findElement(By.xpath("//div[@id='newpassword']/input")).clear();
		
		logger.log(Status.PASS, "Old password entered");
		
		driver.findElement(By.xpath("//div[@id='newpassword']/input")).sendKeys("qwe1234");
		
		logger.log(Status.PASS,"New Password entered");
		
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		
		String UpdatePass = driver.findElement(By.xpath("//div[contains(text(),'Password changed successfully!')]")).getText();
		
		logger.log(Status.PASS, UpdatePass);
		
		String SS3 = Screenshots.captureScreenshot(driver, "CP2");
		
		logger.pass(UpdatePass, MediaEntityBuilder.createScreenCaptureFromPath(SS3).build());
		//Pass change to existing pass
		driver.findElement(By.xpath("//div[@id='oldpassword']/input")).sendKeys("qwe1234");
		
		logger.log(Status.PASS, "Changed password entered");
		
		driver.findElement(By.xpath("//div[@id='newpassword']/input")).sendKeys("qwe123");
		
		logger.log(Status.PASS,"Old Password entered");
		
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		
		String UpdatePass1 = driver.findElement(By.xpath("//div[contains(text(),'Password changed successfully!')]")).getText();
		
		logger.log(Status.PASS, UpdatePass1);
		
		String SS4 = Screenshots.captureScreenshot(driver, "CP3");
		
		logger.pass(UpdatePass, MediaEntityBuilder.createScreenCaptureFromPath(SS4).build());
		
		driver.findElement(By.xpath("//div[@id='main-content']/pt-change-password/lh-navigation-layout-page/lh-header-bar/h1/span")).click();
	}
	
	@Test(priority=4)
	public void depositLimits() throws IOException {
		
		home();
		
		logger = extent.createTest("Deposit limits");
		
		settings();
		
		driver.findElement(By.xpath("//a[contains(text(),'Deposit Limits')]")).click();
		
		WebElement dl = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Deposit limits')]")));
		
		if(dl.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Landed on deposit limits page");
			
			String SS2 = Screenshots.captureScreenshot(driver, "DepositLimits");
			
			logger.pass("Deposit Limits", MediaEntityBuilder.createScreenCaptureFromPath(SS2).build());
			
		}else {
			logger.log(Status.FAIL, "Not landed on Deposit Limits page");
		}
		
//		WebElement CR = driver.findElement(By.xpath("//a[contains(text(),'Cancel my request.')]"));
		
		WebElement CL = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Clear limits')]")));
		
		if(CL.isEnabled()==false) {
			logger.info("Limits are not set earlier");
			
			WebElement CR = driver.findElement(By.xpath("//a[contains(text(),'Cancel my request.')]"));
			
			if (CR.isDisplayed()==true) {
			driver.findElement(By.xpath("//a[contains(text(),'Cancel my request.')]")).click();
			
			driver.findElement(By.xpath("//button[contains(text(),'Yes, cancel it')]")).click();
			
			String CancelRequest = Screenshots.captureScreenshot(driver, "Cancel Req");
			
			logger.pass("Cancel Req", MediaEntityBuilder.createScreenCaptureFromPath(CancelRequest).build());
			}else {
				logger.info("");
			}
		}else {
			
			logger.info("Limits are not updated earlier, but requested");
			
			CL.click();
			
			logger.log(Status.PASS, "Limits are cleared");
		}
		
		limits();
		
//		driver.findElement(By.xpath("//button[contains(text(),'Clear limits')]")).click();
		
//		logger.log(Status.PASS, "Limits are cleared");
		
//		String Deposit1 = Screenshots.captureScreenshot(driver, "ClearLimits");
		
//		logger.pass("ClearLimits", MediaEntityBuilder.createScreenCaptureFromPath(Deposit1).build());
		
		String D1 = RandomStringUtils.randomNumeric(3);
		
		driver.findElement(By.xpath("//div[@class='deposit-limit DAILY']/div/input")).sendKeys(D1);
		
		logger.log(Status.PASS, "Daily limit value is entered");
		
		String W1 = RandomStringUtils.randomNumeric(3);
		
		driver.findElement(By.cssSelector("#accountlimits > form > div > fieldset:nth-child(3) > div > div > input")).sendKeys(W1);
		
		logger.log(Status.PASS, "Weekly limit value is entered");
		
		String M1 = RandomStringUtils.randomNumeric(3);
		
		driver.findElement(By.xpath("//div[@id='accountlimits']/form/div/fieldset[3]/div/div/input")).sendKeys(M1);
		
		logger.log(Status.PASS, "Monthly limit value is entered");
		
		String Deposit2 = Screenshots.captureScreenshot(driver, "LimitsEntered");
		
		logger.pass("Limits entered", MediaEntityBuilder.createScreenCaptureFromPath(Deposit2).build());
		
		driver.findElement(By.xpath("//button[contains(text(),'Confirm')]")).click();
		
		logger.log(Status.PASS, "Limits set");
		
		String Deposit3 = Screenshots.captureScreenshot(driver, "Limits set");
		
		logger.pass("Limits set", MediaEntityBuilder.createScreenCaptureFromPath(Deposit3).build());
		
		driver.findElement(By.xpath("//div[@id=\"main-content\"]/pt-deposit-limits-page/div/lh-header-bar/h1/span")).click();
		
		logger.log(Status.PASS, "X-mark clicked on Deposit limits page ");
	}
	
	@Test(priority=5)
	public void communications() throws IOException {
		
		home();
		
		logger = extent.createTest("Communication page");
		
		settings();
		
		driver.findElement(By.xpath("//a[contains(text(),'Communication')]")).click();
		
		WebElement CP1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Communication Preferences')]")));
		
		if(CP1.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Communication preferences page");
			
			String CP2 = Screenshots.captureScreenshot(driver, "Communication Pref");
			
			logger.pass(CP1.getText(), MediaEntityBuilder.createScreenCaptureFromPath(CP2).build());
		}else {
				logger.log(Status.FAIL, "Page not landed on Communication Preferences page");
			}
			
		driver.findElement(By.xpath("//div[@id=\"communication\"]/form/fieldset[2]/div/div/div[5]/div/label")).click();
		
		logger.log(Status.PASS, "Notifications is checked");
		
		String CP3 = Screenshots.captureScreenshot(driver, "Communication Checked");
		
		logger.pass("Communication checked", MediaEntityBuilder.createScreenCaptureFromPath(CP3).build());
		
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
		
		logger.log(Status.PASS, "Clik on Save");
		
		String CP4 = Screenshots.captureScreenshot(driver, "ComminicationSuccessmsg");
		
		logger.pass("Communication preferences success msg", MediaEntityBuilder.createScreenCaptureFromPath(CP4).build());
		
		driver.findElement(By.xpath("//div[@id=\"main-content\"]/pt-communication/lh-navigation-layout-page/lh-header-bar/h1/span")).click();
		
		logger.log(Status.PASS, "X-mark clicked on comminication page");
	}
	
	@Test(priority=6)
	public void service() throws IOException {
		
		home();
		
		logger = extent.createTest("Service closure");
		
		settings();
		
		driver.findElement(By.xpath("//a[contains(text(),'Service Closure')]")).click();
		
		WebElement SC1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Service Closure')]")));
		
		if(SC1.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Service Closure page");
			
			String SC2 = Screenshots.captureScreenshot(driver, SC1.getText());
			
			logger.pass(SC1.getText(), MediaEntityBuilder.createScreenCaptureFromPath(SC2).build());
			
		}else {
			logger.fail("Page not landed on Service Closure page");
		}
		
		driver.findElement(By.xpath("//div[@id=\"main-content\"]/pt-service-closure/lh-navigation-layout-page/lh-header-bar/h1/span")).click();
		
		logger.log(Status.PASS, "X-mark in service closure page");
	}
	
	@Test(priority=7)
	public void selfexclusion() throws IOException {
		home();
		
		logger = extent.createTest("Self-Exclusion");
		
		settings();
		
		driver.findElement(By.xpath("//a[contains(text(),'Time-out & Self-exclusion')]")).click();
		
		WebElement SE = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Time-out and self-exclusion')]")));
		
		if(SE.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Self-Exclusion page");
		}
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
		
	}
	
	public void settings() throws IOException {
		
		driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-default-menu-item[6]/a/span")).click();
		
		logger.log(Status.PASS, "Settings is clicked");
		
		WebElement set = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='main-content']/pt-settings/lh-header-bar/h1/lh-marquee/span")));
		
		if(set.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Landed on settings page");
			
			String SS1 = Screenshots.captureScreenshot(driver, "Settings page");
			
			logger.pass("details", MediaEntityBuilder.createScreenCaptureFromPath(SS1).build());
			
		}else {
			logger.log(Status.FAIL, "Settings is not loaded");
		}
	}
	
	
	public void limits() throws IOException {
		driver.findElement(By.xpath("//div[@id='accountlimits']/form/div/fieldset[1]/span/a/i")).click();
		
		logger.log(Status.INFO, "Clicked on Daily limit Tool tip");
		
		String Dayinfo = Screenshots.captureScreenshot(driver, "DailyLimitTT");
		
		logger.pass("Daily Limits Tool tip", MediaEntityBuilder.createScreenCaptureFromPath(Dayinfo).build());
		
		driver.findElement(By.cssSelector("#accountlimits > form > div > div.welcome-layer-container.ng-star-inserted > div.welcome-layer.width-restricted-small > div.close-welcome-layer > a")).click();
		
		logger.log(Status.PASS, "Daily limit tool tip is closed");
		
		driver.findElement(By.cssSelector("#accountlimits > form > div > fieldset:nth-child(3) > span > a > i")).click();
		
		logger.log(Status.INFO, "Clicked on Weekly limit tool tip");
		
		String Weekinfo = Screenshots.captureScreenshot(driver, "WeeklyLimitTT");
		
		logger.pass("Weekly Limit Tool tip", MediaEntityBuilder.createScreenCaptureFromPath(Weekinfo).build());
		
		driver.findElement(By.cssSelector("#accountlimits > form > div > div.welcome-layer-container.ng-star-inserted > div.welcome-layer.width-restricted-small > div.close-welcome-layer > a")).click();
		
		logger.log(Status.PASS, "Weekly limit tool tip is closed");
		
		driver.findElement(By.cssSelector("#accountlimits > form > div > fieldset:nth-child(4) > span > a > i")).click();
		
		logger.log(Status.INFO, "Clicked on Monthly limit tool tip");
		
		String Monthinfo = Screenshots.captureScreenshot(driver, "MonthlyLimitTT");
		
		logger.pass("Monthly Limit Tool tip", MediaEntityBuilder.createScreenCaptureFromPath(Monthinfo).build());
		
		driver.findElement(By.cssSelector("#accountlimits > form > div > div.welcome-layer-container.ng-star-inserted > div.welcome-layer.width-restricted-small > div.close-welcome-layer > a")).click();
		
		logger.log(Status.PASS, "Monthly limit tool tip is closed");
		}
		
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			String SS = Screenshots.captureScreenshot(driver, result.getMethod().getMethodName());
			
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
