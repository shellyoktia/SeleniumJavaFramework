package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
	WebDriver driver = null;
	By card = By.xpath("//div[contains(text(),'SPK Rework Request Approval')]");
	By tab_sales = By.cssSelector(".rootmenu-sales");
	By tab_production = By.cssSelector(".rootmenu-production");
	By tab_qualityCheck = By.cssSelector(".rootmenu-quality-check");
	By tab_warehouse = By.xpath("//span[contains(text(),'Warehouse')]");
	
	public Home(WebDriver driver) {
		this.driver = driver;
	}
	public void waitCardToBeClickable() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(card)); 
	}
	public void click_rootmenuSales() {
		driver.findElement(tab_sales).click();
	}
	public void click_rootmenuProduction() {
		driver.findElement(tab_production).click();
	}
	public void click_rootmenuQualityCheck() {
		driver.findElement(tab_qualityCheck).click();
	}
	public void click_rootmenuWarehouse() {
		driver.findElement(tab_warehouse).click();
	}

}
