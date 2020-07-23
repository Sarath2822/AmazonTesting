import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.openqa.selenium.support.locators.RelativeLocator.withTagName;

public class RelativeLoc {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssannala\\Downloads\\Chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/angularpractice/");
		
		WebElement aboveElement = driver.findElement(By.cssSelector("[name='name']"));
		System.out.println(driver.findElement(withTagName("label").above(aboveElement)).getText());
		
		WebElement doB = driver.findElement(By.cssSelector("[for='dateofBirth']"));
		driver.findElement(withTagName("input").below(doB)).sendKeys("22/08/1992");
		
		WebElement checkBox = driver.findElement(By.xpath("//div[4]/label"));
		driver.findElement(withTagName("input").toLeftOf(checkBox)).click();
		
		WebElement radio = driver.findElement(By.cssSelector("#inlineRadio1"));
		driver.findElement(withTagName("label").toRightOf(radio)).getText();
	}

}
