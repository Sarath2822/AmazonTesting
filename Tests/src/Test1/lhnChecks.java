package Test1;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.usuage.com.Screenshots;

public class lhnChecks {
	
	WebDriver driver;
	WebDriverWait wait;
//	String URL = "https://m.sportingbet.com";
//	String driverPath = "C:\\\\\\\\Users\\\\\\\\ssannala\\\\\\\\Downloads\\\\\\\\Chromedriver\\\\\\\\chromedriver.exe";
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeMethod
	public void browserLaunch() {
//		System.setProperty("webdriver.chrome.driver", driverPath);
		System.setProperty("webdriver.chrome.driver", "C:\\\\Users\\\\ssannala\\\\Downloads\\\\Chromedriver\\\\chromedriver.exe");
		
		ChromeOptions  options = new ChromeOptions();
		options.addExtensions(new File ("C:\\Users\\ssannala\\Downloads\\modHeader.crx"));
		driver = new ChromeDriver(options);
		
		extent = new ExtentReports (System.getProperty("user.dir") +"ExtentReport.html", true);
		
		logger = extent.startTest("After Login");
		
		logger.log(LogStatus.INFO, "ModHeader retrived");

		driver.get("chrome://extensions/?id=idgpnmonknjnojddfkpgkljpfnnfckljs/settings.tmpl.html");
		

			((JavascriptExecutor)driver).executeScript(
				    "localStorage.setItem('profiles', JSON.stringify([{                " +
				    "  title: 'Selenium', hideComment: true, appendMode: '',           " +
				    "  headers: [                                                      " +
				    "    {enabled: true, name: 'X-Forwarded-For', value: '179.54.161.81', comment: 'Betboo ip'}, " +
				    "  ],                                                              " +
				    "  respHeaders: [],                                                " +
				    "  filters: []                                                     " +
				    "}]));                                                             " );
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
			
//			ExtentReports extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/ExtentReport.html", true);

//			ExtentTest test = extent.startTest("Verify page title", "checking title");
			
//			logger.log(LogStatus.INFO, "Broswer launched successsfully with Mod IP");
			
			
			
			driver.get("https://qa2.m.sportingbet.com");

	}
	
	@Test
	public void lhn() {
		
		new WebDriverWait(driver, 10);
		
//		driver.get("https://m.sportingbet.com");
		
		if(driver.findElement(By.xpath("html/body/modal-window/div/div/ms-modal-dialog/div[1]/div/i")).isDisplayed()==true) {
			
			driver.findElement(By.xpath("html/body/modal-window/div/div/ms-modal-dialog/div[1]/div/i")).click();
		}else {
			System.out.println("Overlay is not present");
		}
		
		driver.findElement(By.xpath("//a[@class='btn login']")).click();
		
		WebElement S1 = driver.findElement(By.xpath("//span[@class='content control-header-marquee']"));
		
		System.out.println(S1.getText());
		
		driver.findElement(By.xpath("//div[@id='username']/input")).sendKeys("brsl2@yopmail.com");
		
		driver.findElement(By.xpath("//div[@id='password']/input")).sendKeys("qwe123");
		
		Screenshots.captureScreenshot(driver, "Login");
		
		driver.findElement(By.xpath("//div[@id='login']/form/fieldset/section/div[2]/button")).click();
		
		if (driver.findElement(By.xpath("//header[@id='main-header']/section/vn-dynamic-layout-single-slot[2]/lh-inbox-icon/a")).isDisplayed()==true) {
			
//		 WebElement E1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//header[@id='main-header']/section/vn-dynamic-layout-single-slot[2]/lh-inbox-icon/a")));
		 
		System.out.println("Successfully logged in");
		
		} else {
			System.out.println("Login Failed");
		}
		
		Screenshots.captureScreenshot(driver, "Home Screen");
		
		driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
		
		System.out.println("LHN Opened");
		
		driver.findElement(By.xpath("//a[@class='link-element theme-badge ng-star-inserted']")).click();			
		//a[contains(@href,'https://m.sportingbet.com/pt-br/mobileportal/promotions')]"))
				
		System.out.println("Promotions page");
		
		driver.navigate().back();
		
		driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
		
		driver.findElement(By.xpath("//a[contains(@href,'mobileportal/details')]")).click();
		
		System.out.println("Personal details page");
		
		Screenshots.captureScreenshot(driver, "Personal details");
		
		WebElement S2 = driver.findElement(By.xpath("//span[@class='content control-header-marquee']"));
		
		System.out.println(S2.getText());
		
		driver.findElement(By.xpath("//div[@id='mobilenumber']/input")).clear();
		
		String MN = RandomStringUtils.randomNumeric(9);
		
		driver.findElement(By.xpath("//div[@id='mobilenumber']/input")).sendKeys("5"+MN);
		
		
	}
	 
	@AfterMethod
	public void broserClose() {
	 
		  // Close the driver
	 
	      driver.quit();
	}
}
