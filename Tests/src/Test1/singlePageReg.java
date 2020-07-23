package Test1;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

public class singlePageReg {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;
	
	@BeforeMethod
	public void setup() throws IOException {
		
		htmlReporter = new ExtentHtmlReporter("./Reports/SinglepageReg1.html");
		
		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		extent.setAnalysisStrategy(AnalysisStrategy.TEST);
	}

	@Test
	public void browserLaunch() throws IOException, InterruptedException {
		
		logger = extent.createTest("browserLaunch");
		
		Map<String, String> mobileEmulation = new HashMap<>();

		mobileEmulation.put("deviceName", "iPhone 6/7/8");
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssannala\\Downloads\\Chromedriver\\chromedriver.exe");
		
		ChromeOptions  options = new ChromeOptions();
		options.setExperimentalOption("mobileEmulation", mobileEmulation);
		options.addExtensions(new File ("C:\\Users\\ssannala\\Downloads\\modHeader.crx"));
		driver = new ChromeDriver(options);
		
		logger.log(Status.PASS, "ModHeader retrived");
		
		driver.get("chrome://extensions/?id=idgpnmonknjnojddfkpgkljpfnnfckljs/settings.tmpl.html");

		((JavascriptExecutor)driver).executeScript(
			    "localStorage.setItem('profiles', JSON.stringify([{                " +
			    "  title: 'Selenium', hideComment: true, appendMode: '',           " +
			    "  headers: [                                                      " +
			    "    {enabled: true, name: 'X-Forwarded-For', value: '179.54.161.81', comment: 'Brasil ip'}, " +
			    "  ],                                                              " +
			    "  respHeaders: [],                                                " +
			    "  filters: []                                                     " +
			    "}]));                                                             " );
                                                           

		driver.manage().timeouts().implicitlyWait(2000, TimeUnit.SECONDS);
		
		logger.log(Status.INFO, "Broswer launched successsfully with Mod IP");
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		
		driver.get("http://m.br.betboo.com/site/version");
		
		logger.log(Status.INFO, "SiteVersion");
//		Thread.sleep(3000);
		WebElement SV = driver.findElement(By.xpath("xhtml:html/xhtml:body/xhtml:pre"));
		
		logger.log(Status.PASS, SV.getText());

		driver.get("http://m.br.betboo.com/health");
		
		logger.log(Status.INFO, "Health Report");
//		Thread.sleep(2000);
		String HR = driver.getTitle();
		
		logger.log(Status.PASS, HR.toString());
//		String s3 = driver.getPageSource();	
//		System.out.println(s3);
		driver.get("http://m.br.betboo.com");
		
		logger.log(Status.PASS, "Application launched");
		
		/*for (String winhandle: driver.getWindowHandles()) {
		    driver.switchTo().window(winhandle);
		    System.out.println("Window Switch");
		    logger.log(Status.PASS, "Window switched to Popup");

		    driver.findElement(By.xpath("html/body/modal-window/div/div/ms-modal-dialog/div[1]/div/i")).click();
		}
			
		logger.log(Status.PASS, "Carousel is closed");*/
		
	}
	
	@Test
	public void registration() throws InterruptedException, IOException {
		
		wait = new WebDriverWait(driver, 20);
		
		logger = extent.createTest("Registration");
		
		driver.findElement(By.xpath("//a[@class='btn login']")).click();
		
		driver.findElement(By.xpath("//div[@id='language-switch']/div[3]/a")).click();
		
		logger.log(Status.PASS, "language selection");
		
		driver.findElement(By.xpath("//div[@id='language-switch']/div[2]/div/ul/li[1]/a")).click();
		
		logger.log(Status.PASS, "PTBR lang selected");
		
//		Langdropdown.click();
		
		driver.findElement(By.xpath("//a[@class='brand-logo']")).click();
		
		WebElement E1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Registre-se agora')]")));
		
		logger.log(Status.INFO, E1.getText());
		
		Assert.assertEquals(E1.getText(), "REGISTRE-SE AGORA");
		 
		E1.click();
		
		logger.log(Status.PASS, "click on register");
		
		driver.findElement(By.xpath("//button[contains(text(),'ABRIR MINHA CONTA')]")).click();
		
		logger.log(Status.PASS, "Click on create button");
		
		String SC1 = Screenshots.captureScreenshot(driver, "Registration");
		
		logger.pass("Registration error messages", MediaEntityBuilder.createScreenCaptureFromPath(SC1).build());
		
/*//		WebElement H1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Registre-se com segurança em 3 fáceis etapas')]")));
		
		WebElement H1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Registre-se com segurança em uma única etapa')]")));
		
//		Assert.assertEquals(H1.getText(), "Registre-se com segurança em 3 fáceis etapas");
		
		Assert.assertEquals(H1.getText(), "Registre-se com segurança em uma única etapa");
		
		logger.log(Status.PASS, H1.getText());
		
		System.out.println(H1.getText());*/
				
//		Select state = new Select(driver.findElement(By.xpath("//select[@name='addresscountrycode']")));
		
//		state.selectByVisibleText("Brasil");
		
//		driver.findElement(By.xpath(".//*[@id='register']/div[1]/div/button")).click();
		
		Random rand = new Random();
		
		int randomInt = rand.nextInt(1000);
		
		System.out.println("Email ID:" + "ptbrusername"+ randomInt +"@yopmail.com");
		
		logger.log(Status.INFO, "Email ID:" + "ptbrusername"+ randomInt +"@yopmail.com");
		
		driver.findElement(By.name("emailaddress")).sendKeys("ptbrusername"+ randomInt +"@yopmail.com");
		
		logger.log(Status.INFO, "Email entered");
		
		driver.findElement(By.xpath("//div[@id='password']/input[3]")).sendKeys("qwe123");
		
		logger.log(Status.INFO, "Password entered");
				
		String s = RandomStringUtils.randomAlphabetic(3);
		
		driver.findElement(By.xpath("//div[@id='firstname']/input")).sendKeys(s);
		
		logger.log(Status.INFO, "First name entered");
		
		driver.findElement(By.xpath("//div[@id='lastname']/input")).sendKeys(s);
		
		logger.log(Status.INFO, "Last name entered");
		
		driver.findElement(By.xpath("//div[@id='day']/select/option[2]")).click();
		
		logger.log(Status.INFO, "Date selected");
		
		driver.findElement(By.xpath("//div[@id='month']/select/option[2]")).click();
		
		logger.log(Status.INFO, "Month selected");
		
		driver.findElement(By.xpath("//input[@name='year']")).sendKeys("1990");
		
		logger.log(Status.INFO, "Year entered");
				
//		Screenshots.captureScreenshot(driver, "Registration step - 2");
		
//		driver.findElement(By.xpath(".//*[@id='register']/div[2]/div/button")).click();
//		
//		WebElement H3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Detalhes de contato')]")));
//		
//		System.out.println(H3.getText());
		
		driver.findElement(By.name("idcardnumber")).sendKeys(RandomStringUtils.randomNumeric(11));
		
		logger.log(Status.INFO, "CPF num entered");
		
		driver.findElement(By.xpath("//div[@id='addressline1']/input")).sendKeys(s+s+s);
		
		logger.log(Status.INFO, "Address entered");
		
		String MN = RandomStringUtils.randomNumeric(9);
		
		driver.findElement(By.xpath("//div[@id='mobilenumber']/input")).sendKeys("6"+MN);
		
		logger.log(Status.INFO, "Mobile number entered");
		
//		Thread.sleep(3000);
		
		/*driver.findElement(By.xpath("//div[@id='addressfinder']/input")).sendKeys(s+s+s);
		
		System.out.println("address entered second time");
		
		Thread.sleep(3000);*/
		
		WebElement E2 = driver.findElement(By.xpath("//div[@id='register']/div/div[2]/label[1]/span"));
		
		E2.getText();
		
		String SC2 = Screenshots.captureScreenshot(driver, "Registration before submit");
		
		logger.pass("Registration before submit", MediaEntityBuilder.createScreenCaptureFromPath(SC2).build());
				
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		
		WebElement E3 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Método de depósito')]")));
		
		logger.log(Status.PASS, E3.getText());
		
		logger.log(Status.PASS, "Registered Successfully!");
		
		driver.findElement(By.xpath("/html/body/header/a")).click();
		
		logger.log(Status.PASS, "click on X-mark in deposits page");
		
		WebElement E4 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='main-header']/section/vn-dynamic-layout-single-slot[1]/ms-my-bets-button/a")));
		
		if(E4.isDisplayed()==true) {
			
			logger.log(Status.PASS, "Page landed on home page");
		}else {
			logger.log(Status.FAIL, "Page is not landed on home page");
		}
		
		logger.log(Status.PASS, E4.getText());
		
		driver.findElement(By.xpath("//header[@id='main-header']/section/a[1]")).click();
		
		logger.log(Status.INFO, "Click on LHN menu");
		
		driver.findElement(By.xpath("//nav[@id='main-menu']/div/vn-menu-section[4]/div/vn-logout-menu-item/a")).click();
		
		logger.log(Status.INFO, "Click on Logout");
		
		logger.log(Status.PASS, "Successfully Logged out!");
		
		driver.quit();
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
