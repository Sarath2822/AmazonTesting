package Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.usuage.com.Locators;
import com.usuage.com.ReportExtent;
import com.usuage.com.Screenshots;
import com.usuage.com.Utility;

import junit.framework.Assert;

public class Mobile5 extends ReportExtent{
	
	WebDriver driver;
	WebDriverWait wait;
	Properties pro;

	public Mobile5() throws IOException {
		try {
			pro = new Properties();
			FileInputStream fis = new FileInputStream("C:\\eclipse-workspace\\Tests\\src\\com\\usuage\\com\\Data.properties");
			pro.load(fis);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=0)
	public void browserLaunch() throws IOException {
		
		logger = extent.createTest("browserLaunch");
		
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone 6/7/8");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssannala\\Downloads\\Chromedriver\\chromedriver.exe");
		
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
			
			Utility.get(driver, pro.getProperty("APPURL1"));
			
			System.out.println("Application launched");
			
			logger.log(Status.PASS, "Application launced successfully");
			
			/*for (String winhandle: driver.getWindowHandles()) {
			    driver.switchTo().window(winhandle);
			    System.out.println("Window Switch");
			    logger.log(Status.PASS, "Window switched to Popup");

			    driver.findElement(By.xpath("html/body/modal-window/div/div/ms-modal-dialog/div[1]/div/i")).click();
			}
				
				logger.log(Status.PASS, "Carousel is closed");*/
	}

	@Test(priority=1)
	public void registration() throws IOException {
	
		wait = new WebDriverWait(driver, 20);
		
		logger = extent.createTest("Registration");
		
		Utility.xpath(driver, Locators.REG).click();
		
		WebElement E1 = Utility.presenceOfElementLocated(wait, Locators.RESPONSIBLEGAMING);
		
		logger.log(Status.INFO, E1.getText());
		
		Assert.assertEquals(E1.getText(), "Responsible Gaming");
		
		Random rand = new Random();
		
		int randomInt = rand.nextInt(1000);
		
		System.out.println("Email ID:" + "fcusername"+ randomInt +"@yopmail.com");
		
		Utility.name(driver, Locators.EMAIL).sendKeys("fcusername"+randomInt+"@yopmail.com");
		
		Utility.xpath(driver, Locators.PASS).sendKeys("qwe123");
		
		String R1 = Screenshots.captureScreenshot(driver, "Reg 1");
		
		Utility.passLog(logger, "Registration Step - 1", R1);
//		logger.pass("Registration Step - 1", MediaEntityBuilder.createScreenCaptureFromPath(R1).build());
		
		if(Utility.xpath(driver, Locators.CONTINUE).isEnabled()==true) {
		
			Utility.xpath(driver, Locators.CONTINUE).click();
		
//		logger.log(Status.PASS, "Button clicked");
			Utility.logPass(logger, "Button clicked");
		
		}else {
			
			Utility.logFail(logger, "Button not clicked");
//			logger.log(Status.FAIL, "Button not clicked");
		}

		String s = RandomStringUtils.randomAlphabetic(3);
		
		Utility.xpath(driver, Locators.FIRSTNAME).sendKeys(s);
		
		logger.log(Status.INFO, "First name entered");
		
		Utility.xpath(driver, Locators.LASTNAME).sendKeys(s);
		
		logger.log(Status.INFO, "Last name entered");
		
		Utility.xpath(driver, Locators.DATE).click();
		
		logger.log(Status.INFO, "Date selected");
		
		Utility.xpath(driver, Locators.MONTH).click();
		
		logger.log(Status.INFO, "Month selected");
		
		Utility.xpath(driver, Locators.YEAR).sendKeys("1990");
		
		logger.log(Status.INFO, "Year entered");
		
		/*driver.findElement(By.xpath("//div[@id='securityquestion']/select/option[2]")).click();
		
		logger.log(Status.INFO, "SQ selected");
		
		driver.findElement(By.xpath("//input[@name='securityanswer']")).sendKeys("dog");
		
		logger.log(Status.INFO, "SA entered");*/
				
		String R2 = Screenshots.captureScreenshot(driver, "Reg 2");
		
		logger.pass("Registration step - 2", MediaEntityBuilder.createScreenCaptureFromPath(R2).build());
		
		Utility.xpath(driver, Locators.CONTINUE1).click();
		
		logger.log(Status.PASS, "Step - 2 is done");
		
		Utility.xpath(driver, Locators.ADDRESSFINDER).click();
		
		logger.log(Status.INFO, "Entering address manually");
		
		Utility.xpath(driver, Locators.ADDRESS).sendKeys(s+s+s+s);
		
		logger.log(Status.INFO, "Enter address");
		
		Utility.xpath(driver, Locators.CITY).sendKeys("adsfds");
		
		logger.log(Status.INFO, "Enter City");
		
		Utility.xpath(driver, Locators.COUNTRYCODE).sendKeys("400qwe");
		
		logger.log(Status.INFO, "Zip entered");
		
		String N1 = RandomStringUtils.randomNumeric(9);
		
		Utility.xpath(driver, Locators.MOBILENUM).sendKeys("7"+N1);
		
		String R3 = Screenshots.captureScreenshot(driver, "Reg 3");
		
		logger.pass("Registration step - 3", MediaEntityBuilder.createScreenCaptureFromPath(R3).build());
		
		Utility.xpath(driver, Locators.SUBMIT).click();
		
		logger.log(Status.PASS, "Step - 3 is done");
		
		WebElement E2 = Utility.xpath(driver, Locators.FUNDSREG);
		
		Assert.assertEquals(E2.getText(), "SET YOUR DEPOSIT LIMIT");
		
		Utility.xpath(driver, Locators.LIMIT).click();
		
		Utility.logPass(logger, "Selected deposit limits as no");
		
		Utility.xpath(driver, Locators.FUNDTNC).click();
		
		Utility.logPass(logger, "Funds TnC Check box selected");
		
		Utility.xpath(driver, Locators.SUBMITFUNDREG).click();
		
		/*WebElement E2 = Utility.xpath(driver, Locators.SCREENNAMETEXT);
		
		Assert.assertEquals(E2.getText(), "Select your nickname");
		
		logger.log(Status.PASS, "Landed on Screen name page");
		
		String s1 = RandomStringUtils.randomAlphabetic(4);
		
		Utility.xpath(driver, Locators.SCREENNAME).sendKeys(s1);
		
		logger.log(Status.INFO, "Setting screen name");
		
		Utility.xpath(driver, Locators.SUBMITSCREENNAME).click();
		
		logger.log(Status.PASS, "Screen name set successfull");*/
		
		Utility.xpath(driver, Locators.CONTINUESCRENNAME).click();
		
		logger.log(Status.PASS, "Click on continue in UK 1+1");
		
		Utility.xpath(driver, Locators.DRIVINGLICENSE).click();
		
		
		
		/*driver.findElement(By.xpath("html/body/header/a")).click();
		
		logger.log(Status.PASS, "Click on 'X' mark in cashier");
		
		WebElement E2 = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='main-header']/section/a[3]")));
		
		logger.log(Status.PASS, "Successfully logged in");
		
		logger.log(Status.PASS, E2.getText());*/
		
		driver.quit();
	
	}
	
}
