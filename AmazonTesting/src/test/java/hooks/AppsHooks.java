package hooks;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utility.ConfigReader;

public class AppsHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	
	@Before(order = 0)
	public void getProperty() throws IOException {
		configReader = new ConfigReader();
		prop = configReader.initProperties();
	}
	
	@Before(order = 1)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		driverFactory = new DriverFactory();
		driver = driverFactory.initDriver(browserName);
		
		String urlAmazon = prop.getProperty("url");
		DriverFactory.getDriver().get(urlAmazon);
	}
	
	@After(order = 0)
	public void teardown() {
		driver.quit();
	}
	
	@After(order = 1) 
	public void scenarioFailed(Scenario scenario) {
		if (scenario.isFailed()) {
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			
		}
	}
}
