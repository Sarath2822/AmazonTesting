package Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
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

public class ReadnWrite {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeMethod
	public void setup() {
		
		htmlReporter = new ExtentHtmlReporter("./Reports/AutomationResults1.html");
		
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
//	options.addExtensions(new File ("C:\\Users\\ssannala\\Downloads\\modHeader.crx"));
	
	driver = new ChromeDriver(options);                                                            
	
	driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
	
//	logger.log(Status.INFO, "Broswer launched successsfully with Mod IP");
	}
	
	@Test
	public void write() throws IOException {
		
		logger = extent.createTest("Registration");
		
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
	
//		sh1.getRow(i+1).getCell(i+1).setCellValue(countrycount.get(i).getText());
			
//		String C = countrycount.get(i).getText();

//		sh1.getRow(i).createCell(i).setCellValue(C);
		
	//	logger.log(Status.INFO, sh1.getRow(i).createCell(i).setCellValue(C));
		
		logger.log(Status.INFO, countrycount.get(i).getText());
		}
		
		fis.close();
		
		FileOutputStream fos = new FileOutputStream(new File("C:\\eclipse-workspace\\Excel\\excel1.xlsx"));
		
		wb.write(fos);
		
		fos.close();
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
