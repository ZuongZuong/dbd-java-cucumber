package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static WebDriver getDriver(String type) {
		String currentPath = System.getProperty("user.dir");
		DesiredCapabilities caps = new DesiredCapabilities();
		switch (type) {
		case "chrome":
//			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", currentPath + "/src/main/resources/driver/chromedriver.exe");
			ChromeOptions chromeOption = new ChromeOptions();
			chromeOption.addArguments("start-maximized");
			chromeOption.addArguments("Incognito");
			caps.setCapability(ChromeOptions.CAPABILITY, chromeOption);
			return new ChromeDriver(chromeOption);
		case "firefox":
//			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOption = new FirefoxOptions();
			firefoxOption.addArguments("--start-maximized");
			firefoxOption.addArguments("-private");
			caps.setCapability("moz:firefoxOptions", firefoxOption);
			return new FirefoxDriver(firefoxOption);
		default:
			return new ChromeDriver();
		}
	}
}
