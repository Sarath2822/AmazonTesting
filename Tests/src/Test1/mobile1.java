package Test1;

import java.io.File;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.usuage.com.Screenshots;

import junit.framework.Assert;

public class mobile1 {
	
	WebDriver driver;
	WebDriverWait wait;
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	ExtentTest logger;

	@BeforeMethod
	public void browserLaunch()
	{
		htmlReporter = new ExtentHtmlReporter("./Reports/AutomationResults.html");
		
		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		
		logger = extent.createTest("browserLaunch");
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\ssannala\\Downloads\\Chromedriver\\chromedriver.exe");
				
		ChromeOptions  options = new ChromeOptions();
		options.addExtensions(new File ("C:\\Users\\ssannala\\Downloads\\modHeader.crx"));

		driver = new ChromeDriver(options);


		driver.get("chrome://extensions/?id=idgpnmonknjnojddfkpgkljpfnnfcklj/settings.tmpl.html");
	

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
		wait = new WebDriverWait(driver,20);
		driver.get("http://test.m.bwin.com/site/version");
		logger.log(Status.PASS, "Site version");
//		Thread.sleep(3000);
		WebElement s1 = driver.findElement(By.xpath("xhtml:html/xhtml:body/xhtml:pre"));
//		System.out.println(s1.getText());
		logger.log(Status.INFO, s1.getText());
		driver.get("http://test.m.bwin.com/health");
//		Thread.sleep(2000);
		String s2 = driver.getTitle();
//		String s3 = driver.getPageSource();
		System.out.println(s2);
	
//		System.out.println(s3);
		driver.get("http://test.m.bwin.com");
		
		System.out.println("Application launched");
	}
		
		@Test
		public void register() throws InterruptedException {
			
		logger = extent.createTest("Registration");
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
//		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, 20);
			
		WebElement  E1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Register now!')]")));
		E1.click();
		
//		WebElement ele1 = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(),'Hyderabad')]")));
//		ele1.click();
		
		WebElement H1 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Register securely in 3 easy steps')]")));
		
		System.out.println(H1.getText());
		
		Assert.assertEquals(H1.getText(), "Register securely in 3 easy steps");
		
		WebElement C1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='addresscountrycode']/select/option[28]")));
		
		C1.click();
		
//		Select state = new Select(driver.findElement(By.xpath("//div[@id='addresscountrycode']/select")));
		
//		state.selectByVisibleText("United Kingdom");
		
		Random rand = new Random();
		
		int randomInt = rand.nextInt(1000);
		
		System.out.println("Email ID:" + "sbusername"+ randomInt +"@yopmail.com");
		
		driver.findElement(By.name("emailaddress")).sendKeys("sbusername"+ randomInt +"@yopmail.com");
		
		driver.findElement(By.xpath("//div[@id='password']/input[3]")).sendKeys("qwe123");
		
		if(driver.findElement(By.xpath("//div[@id='register']/div[1]/div/button")).isEnabled()==true) {
		
		driver.findElement(By.xpath("//div[@id='register']/div[1]/div/button")).click();
		
		System.out.println("Button clicked");
		} else {
			System.out.println("Failed in 1st step");
		}

		WebElement H2 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Secure your account')]")));
		
		System.out.println(H2.getText());
		
		String s = RandomStringUtils.randomAlphabetic(3);
		
		driver.findElement(By.xpath("//div[@id='firstname']/input")).sendKeys(s);
		
		driver.findElement(By.xpath("//div[@id='lastname']/input")).sendKeys(s);
		
		/*if (driver.findElement(By.xpath("//div[@id='register']/div[1]/div/button")).isEnabled()==true) {
			Screenshots.captureScreenshot(driver, "Registration 1st step -1");
			driver.findElement(By.xpath("//*[@id='register']/div[1]/div/button")).click();			
		}else {
			System.out.println("Failed to proceed registration");
		}*/

//		WebElement CTA1 = (WebElement) wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='register']/div[1]/div/button"))));
//		
//		CTA1.click();		
		
		/*String U = "sben"+ randomInt;
		
		System.out.println("username: "+U);
		
		driver.findElement(By.name("username")).sendKeys(U);*/

		driver.findElement(By.xpath("//div[@id='day']/select/option[2]")).click();
		
		driver.findElement(By.xpath("//div[@id='month']/select/option[2]")).click();
		
		driver.findElement(By.xpath("//input[@name='year']")).sendKeys("1990");
		
		driver.findElement(By.xpath("//div[@id='securityquestion']/select/option[2]")).click();
		
		driver.findElement(By.xpath("//input[@name='securityanswer']")).sendKeys("dog");
				
		Screenshots.captureScreenshot(driver, "Registration step - 2");
		
		driver.findElement(By.xpath("//div[@id='register']/div[2]/div/button")).click();
		
		WebElement H3 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Contact details')]")));
		
		System.out.println(H3.getText());
		
		driver.findElement(By.xpath("//a[contains(text(),'Enter manually')]")).click();
		
		driver.findElement(By.xpath("//input[@name='addressline1']")).sendKeys(s+s+s+s);
		
		driver.findElement(By.xpath("//select[@name='addressstate']/option[2]")).click();
		
		driver.findElement(By.xpath("//div[@id='addresszip']/input")).sendKeys("400qwe");
		
		driver.findElement(By.xpath("//div[@id='addresscity']/input")).sendKeys("asjfasd");
		
		String N1 = RandomStringUtils.randomNumeric(9);
		
		driver.findElement(By.xpath("//div[@id='mobilenumber']/input")).sendKeys("7"+N1);
		
//		driver.findElement(By.xpath("//input[@id='No']")).click();
		
		Screenshots.captureScreenshot(driver, "Registration step - 3");
		
		driver.findElement(By.xpath("//button[@id='submit']")).click();
		
//		wait.until(ExpectedConditions.e)
		
//		WebElement S4 = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[contains(text(),'Sorry, we no longer accept new players from your country. Thank you.')]"))));
				
//		System.out.println(S4.getText());

		driver.findElement(By.xpath("html/body/header/a")).click();
		
		WebElement S4 = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='main-header']/section/a[3]")));
		
		System.out.println("Successfully logged in");
		
		System.out.println(S4.getText());
		
		driver.quit();
		
	//E7: username558@gmail.com,username537@gmail.com, sbusername673@yopmail.com
//	QA2: username675@gmail.com,sbusername567/570/897@yopmail.com

	}

}
