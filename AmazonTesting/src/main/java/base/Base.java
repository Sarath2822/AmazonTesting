package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Base {
	WebDriver driver;
	@Test
	public void browserLaunch() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssannala\\Downloads\\Chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	@Test
	public void siteLaunch() {
		System.out.println();
	}
}
