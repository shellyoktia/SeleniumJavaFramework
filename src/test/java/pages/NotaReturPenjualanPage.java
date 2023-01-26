package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotaReturPenjualanPage {
	WebDriver driver = null;
	private WebDriverWait wait;
	private Actions actions;
	By filter = By.className("ant-collapse-header-text");
	By submit_filter = By.name("submit");
	By filterByNoSJR = By.name("noSj");
	By view_NotaReturPenjualan = By.xpath("(//a[contains(text(), 'View')])[1]");
	By noSJR_inNotaReturPenjualan = By.xpath("(//tbody//td[6])[2]");

	public NotaReturPenjualanPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	public void click_filter() throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(view_NotaReturPenjualan));
		driver.findElement(filter).click();
	}
	public void filter_byNoSJR(String no_sjr) throws InterruptedException {
		WebElement filterbyNoSJR = driver.findElement(filterByNoSJR);
		actions.moveToElement(filterbyNoSJR).click().sendKeys(no_sjr).build().perform();
		Thread.sleep(5000);	
	}
	public void click_submitFilter() throws InterruptedException {
		driver.findElement(submit_filter).click();
		wait.until(ExpectedConditions.elementToBeClickable(view_NotaReturPenjualan));
		Thread.sleep(3000);
	}
	public String getNoSJR_inNotaPenjualan() {
		return driver.findElement(noSJR_inNotaReturPenjualan).getText();
	}
}
