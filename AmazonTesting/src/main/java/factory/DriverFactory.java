package factory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConstantsElements;

public class DriverFactory {

	public WebDriver driver;

	// ThreadLocal is used allow to store data which only be accessible by a
	// specific thread.
	// It will help in parallel excecution to achieve synchronization and thread
	// safe.
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public WebDriver initDriver(String browser) {
		System.out.println(browser + " browser is invoked");
		if (browser.equalsIgnoreCase(ConstantsElements.CHROMEBROWSER)) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		} else if (browser.equalsIgnoreCase(ConstantsElements.FIREFOXBROWSER)) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		} else {
			System.out.println("Please enter valid browser i.e. chrome or firefox");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		return getDriver();
	}

	// To get the driver
	// To use the driver with the threadlocal
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

}
