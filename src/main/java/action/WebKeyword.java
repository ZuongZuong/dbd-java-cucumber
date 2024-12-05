package action;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebKeyword {
	private WebDriver driver;
	private WebDriverWait wait;
	public JavascriptExecutor executor;


	public WebKeyword(WebDriver driver){
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void openUrl(String url) throws Exception {
		if(!(url.startsWith("http://") || url.startsWith("https://"))){
			throw new Exception("Invalid URL format");
		}
		driver.get(url);
	}

	public void navigate(String url) throws Exception {
		if(!(url.startsWith("http://") || url.startsWith("https://"))){
			throw new Exception("Invalid URL format");
		}
		driver.navigate().to(url);
	}

	public void setText(WebElement elem, String text){
		try{
			elem.clear();
			elem.sendKeys(text);
		}
		catch (WebDriverException e){
			throw new WebDriverException("Element is not enable to set text");
		}
	}

	public  void click(WebElement elem){
		Actions actions = new Actions(this.driver);
		actions.moveToElement(elem).build().perform();
		elem.click();
	}

	public void clickWithJs(WebElement element) {
		executor = (JavascriptExecutor) this.driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void select(WebElement element, SelectType type, String options) throws Exception {
		Select select = new Select(element);
		switch (type)
		{
		case SELECT_BY_INDEX:
			try
			{
				select.selectByIndex(Integer.parseInt(options));
			}
			catch (Exception e)
			{
				throw new Exception("Please input numberic on selectOption for SelectType.SelectByIndex");
			}
			break;
		case SELECT_BY_TEXT:
			select.selectByVisibleText(options);
			break;
		case SELECT_BY_VALUE:
			select.selectByValue(options);
			break;
		default:
			throw new Exception("Get error in using Selected");
		}
	}

	public enum SelectType {
		SELECT_BY_INDEX, SELECT_BY_TEXT, SELECT_BY_VALUE;
	}

	public String iFrameHandle(WebElement iFrameNodeSelector, WebElement iFrameBodySelector) {
		wait.until(ExpectedConditions.visibilityOf(iFrameNodeSelector));
		driver.switchTo().frame(iFrameNodeSelector);
		wait.until(ExpectedConditions.visibilityOf(iFrameBodySelector));
		return iFrameBodySelector.getText();
	}

	public void dnd(WebElement From, WebElement To) {
		Actions actions = new Actions(this.driver);
		actions.dragAndDrop(From, To).build().perform();		
	}

	public void rightClick(WebElement  element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}

	public void scrollToElement(WebElement element) {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollToBottom() {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void pickDate(WebElement btnPickDate, WebElement getMonth, WebElement btnNextMonth, By allDate, String month, String date) throws InterruptedException {
		btnPickDate.click();
		while (true) {
			String txtMonth = getMonth.getText();
			if (txtMonth.equals(month)) {
				break;
			}else {
				btnNextMonth.click();
			}
		}
		List<WebElement> listDate =  driver.findElements(allDate);
		for (WebElement ele : listDate) {
			if (ele.getText().equals(date)) {
				ele.click();
				Thread.sleep(1000);
				Actions actions = new Actions(driver);
				actions.sendKeys(ele, Keys.ENTER);
			}
		}
	}

	public void mouseHover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public void JsPomptAlert(WebElement jsPromtSelector, String sendtext) throws InterruptedException {
		jsPromtSelector.click();
		Thread.sleep(1000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.sendKeys(sendtext);
		Thread.sleep(1000);
		alert.accept();
	}

	public void JsConfirmAlert(WebElement jsConfirmbtnSelector, String choose) throws InterruptedException {
		jsConfirmbtnSelector.click();
		Thread.sleep(1000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert = wait.until(ExpectedConditions.alertIsPresent());
		switch (choose) {
		case "accept":
			alert.accept();
			Thread.sleep(1000);
			break;
		case "dismiss":
			alert.dismiss();;
			Thread.sleep(1000);
			break;
		default:
			break;
		}
	}
}
