package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StockBalancePage {
	WebDriver driver = null;
	private WebDriverWait wait;
	private Actions actions;
	By menu_StockBalance = By.xpath("//span[contains(text(),'Laporan Stok Level (Stock Balance)')]");
	By menu_StockBalance_finishedgood = By.xpath("(//a[contains(text(),'Finished Good')])[1]");
	By filter = By.className("ant-collapse-header-text");
	By no_SKU = By.name("sku");
	By lot_name = By.xpath("(//input[contains(@class, 'ant-select-selection-search-input')])[1]");
	By submit_filter = By.name("submit");
	By balanceBox = By.xpath("//tbody//td[5][contains(text(),'0')]//following::td[2]");
	By balancePartisi1 = By.xpath("//tbody//td[5][contains(text(),'1')]//following::td[2]");
	By balancePartisi2 = By.xpath("//tbody//td[5][contains(text(),'2')]//following::td[2]");
	
	public StockBalancePage(WebDriver driver) {
		this.driver = driver;
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	}
	public void click_menuStockBalance() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(menu_StockBalance));
		driver.findElement(menu_StockBalance).click();
	}
	
	public void click_menuStockBalanceFinishedGood() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(menu_StockBalance_finishedgood);
	}
	public void click_filter() throws InterruptedException {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(loadingPage));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingPage));
		Thread.sleep(5000);
		driver.findElement(filter).click();
	}
	public void filter_bySKU(String noSKU) {
		driver.findElement(no_SKU).sendKeys(noSKU);
	}
	public void filter_byLot(String lot) {
		driver.findElement(lot_name).sendKeys(lot);
		driver.findElement(By.xpath("//div[contains(text(),'"+lot+"')]")).click();
	}
	public void click_submitFilter() throws InterruptedException {
		driver.findElement(submit_filter).click();
		Thread.sleep(3000);
	}
	public String get_balanceBox() {
		return driver.findElement(balanceBox).getText();
	}
	public String get_balancePartisi1() {
		return driver.findElement(balancePartisi1).getText();
	}
	public String get_balancePartisi2() {
		return driver.findElement(balancePartisi2).getText();
	}
}
