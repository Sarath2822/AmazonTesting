package Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

import junit.framework.Assert;

public class Mobile4 {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeMethod
	public void setup() {
		
		htmlReporter = new ExtentHtmlReporter("./Reports/AutomationResults4.html");
		
		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
		
	}
		@Test
		public void browserLaunch() {

		logger = extent.createTest("Launch Browser");
		
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone 6/7/8");
		
		System.setProperty("webdriver.chrome.driver","C:\\\\Users\\\\ssannala\\\\Downloads\\\\Chromedriver\\\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		options.addExtensions(new File ("C:\\Users\\ssannala\\Downloads\\modHeader.crx"));
		
		driver = new ChromeDriver(options);
		
		logger.log(Status.INFO, "Mode header retrived");
		
		logger.log(Status.INFO, "iPhone emulator set");
		
		driver.get("chrome://extensions/?id=idgpnmonknjnojddfkpgkljpfnnfckljs/settings.tmpl.html");
		//Setting Mod IP to UK
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
		}
		
	@Test(priority=0)
	public void listofCountries() throws IOException {

		logger = extent.createTest("listofCountries");
		
		driver.get("http://m.bwin.com");
		
		logger.log(Status.PASS, "Application launched successfully");
		
		wait = new WebDriverWait(driver, 20);
		
		WebElement  E1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Register now!')]")));
		
		E1.click();

		WebElement H1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Register securely in 3 easy steps')]")));
		
		logger.log(Status.INFO, H1.getText());
		
		Assert.assertEquals(H1.getText(), "Register securely in 3 easy steps");
		
		WebElement C1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='addresscountrycode']/select")));
		
		Select dropdown = new Select(C1);
		
		List<WebElement> countrycount = dropdown.getOptions();
		
		logger.log(Status.INFO, "No.of counties:" +countrycount.size());
		
		File src = new File("C:\\eclipse-workspace\\Excel\\excel1.xlsx");
	
		FileInputStream fis = new FileInputStream(src);
	
		XSSFWorkbook wb = new XSSFWorkbook(fis);
	
		XSSFSheet sh1 = wb.getSheetAt(0);
		
		Row row = sh1.getRow(0);

		logger.log(Status.INFO, sh1.getRow(0).getCell(0).getStringCellValue());
		
		logger.log(Status.INFO, sh1.getRow(0).getCell(1).getStringCellValue());
		
		for(int i=0; i<countrycount.size();i++) {		
		
		Row newrow = sh1.createRow(i+1);
		
		newrow.createCell(0).setCellValue(i+1);
		
		Cell cell = newrow.createCell(1);
		
		cell.setCellValue(countrycount.get(i).getText());
		
		logger.log(Status.INFO, countrycount.get(i).getText());
		}
		
		fis.close();
		
		FileOutputStream fos = new FileOutputStream(new File("C:\\eclipse-workspace\\Excel\\excel1.xlsx"));
		
		wb.write(fos);
		
		fos.close();
	
	}
	
	@Test(priority=1)
	public void registration() throws IOException {
		
//		listofCountries();
		
		logger = extent.createTest("Registrations");
		
		driver.findElement(By.xpath("//a[@class='brand-logo']")).click();
		
		File src = new File("C:\\eclipse-workspace\\Excel\\excel1.xlsx");
		
		FileInputStream fis = new FileInputStream(src);
	
		XSSFWorkbook wb = new XSSFWorkbook(fis);
	
		XSSFSheet sh1 = wb.getSheetAt(0);
		
		int rowcount = sh1.getLastRowNum() - sh1.getFirstRowNum();
		
		logger.info("Row count:" + rowcount);
		
		for(int i=0;i<rowcount;i++)
		{
			
			wait = new WebDriverWait(driver, 20);
			
			WebElement  E1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Register now!')]")));
			
			E1.click();

			WebElement H1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Register securely in 3 easy steps')]")));
			
			logger.log(Status.INFO, H1.getText());
			
			Assert.assertEquals(H1.getText(), "Register securely in 3 easy steps");

			logger.log(Status.PASS, "looping starting");
			
			String Country = sh1.getRow(i+2).getCell(1).getStringCellValue();
			
			System.out.println(Country);
			
			String xpath= "//div[contains(text(),"+"'"+Country+"'"+")]";
			
			System.out.println(xpath);
			
//			WebElement C1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='addresscountrycode']/select")));
			
//			WebElement C1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath1)));	
			
//			C1.click();
			
			Select state = new Select(driver.findElement(By.xpath("//div[@id='addresscountrycode']/select")));
			
			state.selectByVisibleText(Country);
			
//			driver.findElement(By.name(Country)).click();
					
			String S1 = Screenshots.captureScreenshot(driver, "reg");
			
			logger.pass("REG", MediaEntityBuilder.createScreenCaptureFromPath(S1).build());
			
			Random rand = new Random();
			
			int randomInt = rand.nextInt(1000);
			
			System.out.println("Email ID:" + "bzusername"+ randomInt +"@yopmail.com");
			
			driver.findElement(By.name("emailaddress")).sendKeys("bzusername"+ randomInt +"@yopmail.com");
			
			logger.log(Status.PASS, "Email entered");
			
			driver.findElement(By.name("username")).sendKeys("bzuser"+randomInt);
			
			logger.log(Status.PASS, "User name entered");
			
			driver.findElement(By.xpath("//div[@id='password']/input[3]")).sendKeys("qwe123");
			
			logger.log(Status.PASS, "Password entered");
			
			if(driver.findElement(By.xpath("//div[@id='register']/div[1]/div/button")).isEnabled()==true) {
			
			driver.findElement(By.xpath("//div[@id='register']/div[1]/div/button")).click();

			logger.log(Status.PASS, "Continue to step 2");
			} else {
				String S2 = Screenshots.captureScreenshot(driver, "Step1");
				
				logger.fail("Failed to click on Continue in Step 1", MediaEntityBuilder.createScreenCaptureFromPath(S2).build());
			}

			WebElement H2 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Secure your account')]")));
			
			logger.log(Status.INFO, H2.getText());
			
			String s = RandomStringUtils.randomAlphabetic(3);
			
			driver.findElement(By.xpath("//div[@id='firstname']/input")).sendKeys(s);
			
			logger.log(Status.PASS, "First name entered");
			
			driver.findElement(By.xpath("//div[@id='lastname']/input")).sendKeys(s);

			logger.log(Status.PASS, "last name entered");
			
			driver.findElement(By.xpath("//div[@id='day']/select/option[2]")).click();
			
			logger.log(Status.PASS, "Date selected from DOB picker");
			
			driver.findElement(By.xpath("//div[@id='month']/select/option[2]")).click();
			
			logger.log(Status.PASS, "Month selected from DOB picker");
			
			driver.findElement(By.xpath("//input[@name='year']")).sendKeys("1990");
			
			logger.log(Status.PASS, "Year selected from DOB picker");
			
			driver.findElement(By.xpath("//div[@id='securityquestion']/select/option[2]")).click();
			
			logger.log(Status.PASS, "Security question selected from dropdown");
			
			driver.findElement(By.xpath("//input[@name='securityanswer']")).sendKeys("dog");
			
			logger.log(Status.PASS, "Password entered for SQ");
					
			String S3 = Screenshots.captureScreenshot(driver, "step-2");
			
			logger.pass("Registration step - 2", MediaEntityBuilder.createScreenCaptureFromPath(S3).build());
			
			driver.findElement(By.xpath("//div[@id='register']/div[2]/div/button")).click();
			
			logger.log(Status.PASS, "Continue to step 3");
			
			WebElement H3 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Contact details')]")));
			
			logger.log(Status.INFO, H3.getText());
			
			if(driver.findElement(By.xpath("//a[contains(text(),'Enter manually')]")).isDisplayed()==true) {
				
				driver.findElement(By.xpath("//a[contains(text(),'Enter manually')]")).click();
				
				logger.log(Status.PASS, "Manual link is clicked below address field ");
			}else {
			
				logger.log(Status.INFO, "No Address finder link for:"+Country);
			}
			
			driver.findElement(By.xpath("//input[@name='addressline1']")).sendKeys(s+s+s+s);
			
			logger.log(Status.PASS, "Address is entered");
			
//			driver.findElement(By.xpath("//select[@name='addressstate']/option[2]")).click();
			
			driver.findElement(By.xpath("//div[@id='addresscity']/input")).sendKeys("asjfasd");
			
			logger.log(Status.PASS, "City entered");
			
			driver.findElement(By.xpath("//div[@id='addresszip']/input")).sendKeys("400qwe");
			
			logger.log(Status.PASS, "Postal code entered");
			
//			driver.findElement(By.xpath("//div[@id='addresscity']/input")).sendKeys("asjfasd");
			
			String N1 = RandomStringUtils.randomNumeric(9);
			
			driver.findElement(By.xpath("//div[@id='mobilenumber']/input")).sendKeys("7"+N1);
			
			logger.log(Status.PASS, "telefonnummer entered");
			
			String S4 = Screenshots.captureScreenshot(driver, "Registration step - 3");
			
			logger.pass("Registration step - 3", MediaEntityBuilder.createScreenCaptureFromPath(S4).build());
			
			driver.findElement(By.xpath("//button[@id='submit']")).click();
			
			logger.log(Status.PASS, "Submit is clicked");

			driver.findElement(By.xpath("html/body/header/a")).click();
			
			WebElement H4 = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='main-header']/section/a[3]")));
			
			logger.log(Status.PASS, H4.getText());
			
			String S5 = Screenshots.captureScreenshot(driver, "Successfully logged in");
			
			logger.pass("Successfully logged in", MediaEntityBuilder.createScreenCaptureFromPath(S5).build());
			
			driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
			
			driver.findElement(By.xpath("//a[contains(text(),'Log out')]")).click();
			
			driver.findElement(By.cssSelector("#main-header > vn-login-bar > div > a.btn.login"));
			
			logger.log(Status.PASS, "Landed on home page");
		}
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
