package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import action.WebKeyword;

public class BasePage {
	protected WebDriver driver;
	protected WebKeyword keyword;
	protected WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		super();
		this.driver = driver;
		this.keyword = new WebKeyword(driver);
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}
	
}
