package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Login extends BasePage {

	@FindBy(xpath = "//form[@action=\"/login\"]//input[@type=\"email\"]")
	@CacheLookup
	WebElement txtEmailAddress;
	@FindBy(xpath = "//form[@action=\"/login\"]//input[@type=\"password\"]")
	@CacheLookup
	WebElement txtPassword;
	@FindBy(xpath = "//form[@action=\"/login\"]//button[@type=\"submit\"]")
	@CacheLookup
	WebElement btnSubmit;

	public Login(WebDriver driver) {
		super(driver);
	}
	

	public Login inputEmailAddress(String username) {
		keyword.setText(txtEmailAddress, username);
		return this;
	}

	public Login inputPassword(String password) {
		keyword.setText(txtPassword, password);
		return this;
	}
	
	
	public void clickOnSubmit() {
		keyword.click(btnSubmit);
	}

	public HomePage login(String username, String password) {
		inputEmailAddress(username).inputPassword(password).clickOnSubmit();
		return new HomePage(driver);
	}

}
