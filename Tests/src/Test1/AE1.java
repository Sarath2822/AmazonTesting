package Test1;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.usuage.com.Locators1;
import com.usuage.com.Utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class AE1 {

	public static	WebDriverWait wait;
	public static	Properties pro;
	public static AppiumDriver<MobileElement> driver;
	 
	public AE1() throws IOException{

		try {
			pro = new Properties();
			FileInputStream fis = new FileInputStream("C:\\eclipse-workspace\\Tests\\src\\com\\usuage\\com\\Data.properties");
			pro.load(fis);
			System.out.println(pro.getProperty("PASS"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		}
	@Test
		public static void main(String[] args) throws InterruptedException {
			
			//Set the Desired Capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "My Phone");
//			caps.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
			caps.setCapability("udid", "ce0117110c490c3204");
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "8.0.0");
//			caps.setCapability("browserName", "Chrome");
			/*caps.setCapability("appPackage", "com.partypoker.poker.dev");
			caps.setCapability("appActivity", "com.bwinparty.ui.container.SplashActivityContainer");*/
			caps.setCapability("appPackage", "com.sportingbet.sportbook_beta");
			caps.setCapability("appActivity", "com.bwinlabs.wrapper_sportingbetcom.ui.activity.HomeActivitySWSBCOM");
			caps.setCapability("noReset", true);
			
		/*	 "appPackage": "com.android.chrome",
			  "appActivity": "com.google.android.apps.chrome.Main"*/
				  
			//Set ChromeDriver location
//			System.setProperty("webdriver.chrome.driver","C:\\Users\\ssannala\\Downloads\\chromedriver_win32\\chromedriver.exe");
			
			//Instantiate Appium Driver
			AppiumDriver<MobileElement> driver = null;
			try {
				driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
				
			} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
			}
					
			//Open URL in Chrome Browser
//			driver.get("http://www.google.com");
			
			wait = new WebDriverWait(driver, 20);
			
			/*MobileElement element0 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.ListView/android.widget.CheckedTextView[2]");
			Thread.sleep(2000);
			element0.click();
	
			MobileElement element = (MobileElement) driver.findElementsById("com.partypoker.poker.dev:id/login_name_field");
			Thread.sleep(3000);
			//element.click();
			//element.clear();
			element.sendKeys("ap10"); 
			
			MobileElement element1 = (MobileElement) driver.findElementsById("com.partypoker.poker.dev:id/login_password_field");
			element1.click();
			element1.clear();
			element1.sendKeys("123123"); 
			
			
			MobileElement element2 = (MobileElement) driver.findElementsById("com.partypoker.poker.dev:id/login_login_button ");
			element2.click();*/
			Utility.visibilityOfEleLocatedBy(wait, Locators1.HOMEPAGELOGIN).click();
			
			MobileElement E1 = (MobileElement) Utility.id(driver, Locators1.USERNAME);
			E1.click();
			E1.clear();
//			driver.findElement(By.id("username")).sendKeys("sbcm6@yopmail.com");
//			driver.findElement(By.id("username")).click();
//			driver.getKeyboard().sendKeys("sbcm6@yopmail.com");
			
			E1.setValue("sbcm6@yopmail.com");
//			Utility.id(driver, Locators1.USERNAME).sendKeys("sbcm6@yopmail.com");
			
			MobileElement E2 = (MobileElement) Utility.id(driver, Locators1.PASSWORD);
			E2.click();
			E2.clear();
//			System.out.println(pro.getProperty("PASS"));
//			String password = pro.getProperty("PASS"); 
//			System.out.println(password);
			E2.setValue("qwe123");
			
//			Utility.xpath(driver, Locators1.PASSWORD).sendKeys(pro.getProperty("PASS"));
			
			Utility.xpath(driver, Locators1.LOGIN).click();

		}
}