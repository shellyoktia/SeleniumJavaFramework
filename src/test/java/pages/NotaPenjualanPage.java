package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotaPenjualanPage {
	WebDriver driver = null;
	private WebDriverWait wait;
	private Actions actions;
	By menu_notaPenjualan = By.linkText("Nota Penjualan");
	By view_NotaPenjualan = By.xpath("(//a[contains(text(), 'View')])[1]");
	By filter = By.className("ant-collapse-header-text");
	By submit_filter = By.name("submit");
	By filterByNoSalesOrder = By.cssSelector(".no-salesorder .ant-select-selection-search-input");
	By noSO_inNotaPenjualan = By.xpath("(//tbody//td[10])[2]");
	
	public NotaPenjualanPage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		actions = new Actions(driver);
	}
	public void click_menuNotaPenjualan() {
		wait.until(ExpectedConditions.elementToBeClickable(menu_notaPenjualan));
		driver.findElement(menu_notaPenjualan).click();
	}
	public void click_filter() throws InterruptedException {
		Thread.sleep(5000);
		wait.until(ExpectedConditions.elementToBeClickable(view_NotaPenjualan));
		driver.findElement(filter).click();
	}
	public void filter_byNoSalesOrder(String no_so) throws InterruptedException {
		WebElement filterbyNoSO = driver.findElement(filterByNoSalesOrder);
		actions.moveToElement(filterbyNoSO).click().sendKeys(no_so).build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[contains(@class, 'ant-select-item-option-content') and contains(text(), '"+no_so+"')]")).click();
	}
	public void click_submitFilter() throws InterruptedException {
		driver.findElement(submit_filter).click();
		wait.until(ExpectedConditions.elementToBeClickable(view_NotaPenjualan));
		Thread.sleep(5000);
	}
	public String getNoSO_inNotaPenjualan() {
		return driver.findElement(noSO_inNotaPenjualan).getText();
	}

}
