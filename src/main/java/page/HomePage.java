package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public Boolean isHomepageVisible() {
		if (driver.getTitle().equals("Automation Exercise")) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
