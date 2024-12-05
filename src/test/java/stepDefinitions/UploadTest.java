package stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import action.WebKeyword;
import constants.FrameworkConstants;
import driver.TargetFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page.HomePage;
import page.Login;

public class UploadTest {
	WebDriver driver;
	WebKeyword keyword;
	TargetFactory targetFactory;
	Login login;
	HomePage homepage;

	@Given("User access to URL browser")
	public void user_access_to_url_browser() throws Exception {
		targetFactory = new TargetFactory();
		driver = targetFactory.createInstance();
		login = new Login(driver);
		homepage = new HomePage(driver);
		keyword = new WebKeyword(driver);
		keyword.openUrl(FrameworkConstants.URL);
	}

	@When("User set text {string} to Email Address field")
	public void user_set_text_to_email_address_field(String string) {
		login.inputEmailAddress(string);
	}

	@When("User set text {string} to Password field")
	public void user_set_text_to_password_field(String string) {
		login.inputPassword(string);
	}

	@When("User click on submit button")
	public void user_click_on_submit_button() {
		login.clickOnSubmit();
	}

	@Then("Verify User is landed to Homepage")
	public void verify_user_is_landed_to_homepage() {
		assertTrue(homepage.isHomepageVisible());
	}

}
