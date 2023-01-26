package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
	WebDriver driver = null;
	private Actions actions;
	By card = By.xpath("//div[contains(text(),'SPK Rework Request Approval')]");
	By tab_sales = By.cssSelector(".rootmenu-sales");
	By tab_production = By.cssSelector(".rootmenu-production");
	By tab_qualityCheck = By.cssSelector(".rootmenu-quality-check");
	By tab_warehouse = By.xpath("//span[contains(text(),'Warehouse')]");
	By tab_freight =  By.cssSelector(".rootmenu-freight");
	By home = By.xpath("(//span[contains(@class, 'layout-header__brand-title')])[2]");
	By search_menu = By.xpath("(//input[contains(@class, 'ant-select-selection-search-input')])[1]");
	
	public Home(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
	}
	public void waitCardToBeClickable() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
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
	public void click_rootmenuFreight() {
		driver.findElement(tab_freight).click();
	}
	public void click_rootmenuHome() {
		driver.findElement(home).click();
	}
	public void click_searchMenu(String nama_menu) {
		WebElement search = driver.findElement(search_menu); 
		actions.moveToElement(search).click().sendKeys(nama_menu).build().perform();
		driver.findElement(By.xpath("//div[contains(text(),'"+nama_menu+"')]")).click();
		
	}

}
